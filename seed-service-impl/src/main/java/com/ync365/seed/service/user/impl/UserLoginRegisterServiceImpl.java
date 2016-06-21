package com.ync365.seed.service.user.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ync365.seed.bussiness.modules.common.biz.MobileValidateCodeBiz;
import com.ync365.seed.bussiness.modules.order.biz.UserCouponBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysAdminBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysAdminInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysLcInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUserBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUserInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysVsInfoBiz;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.bo.LcInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.SysAdminInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.UserInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.UserRegisterBO;
import com.ync365.seed.bussiness.modules.user.bo.VsInfoBO;
import com.ync365.seed.bussiness.modules.user.entity.SysAdmin;
import com.ync365.seed.bussiness.modules.user.entity.SysAdminInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysLcInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysUser;
import com.ync365.seed.dto.base.ResponseCode;
import com.ync365.seed.dto.base.ResponseDTO;
import com.ync365.seed.dto.user.AdminRegisterDTO;
import com.ync365.seed.dto.user.LcInfoDTO;
import com.ync365.seed.dto.user.UserAdminInfoDTO;
import com.ync365.seed.dto.user.UserInfoDTO;
import com.ync365.seed.dto.user.UserLoginDTO;
import com.ync365.seed.dto.user.UserLoginReturnDTO;
import com.ync365.seed.dto.user.UserRegisterDTO;
import com.ync365.seed.dto.user.VsInfoDTO;
import com.ync365.seed.service.annotation.FormTokenAnnotation;
import com.ync365.seed.service.user.IUserLoginRegisterService;
import com.ync365.seed.utils.Constants;
import com.ync365.seed.utils.StringUtils;

/**    
 *     
 * @Title：UserLoginRegisterServiceImpl  
 * @Description:    
 * @author: duan.zhao.qian     
 * @date: 2015年10月12日 下午5:01:16      
 * @version     
 *     
 */
public class UserLoginRegisterServiceImpl implements IUserLoginRegisterService {

    @Autowired
    SysUserBiz sysUserBiz;
    @Autowired
    SysUserInfoBiz sysUserInfoBiz;
    @Autowired
    SysVsInfoBiz sysVsInfoBiz;
    @Autowired
    SysLcInfoBiz sysLcInfoBiz;
    @Autowired
    SysAdminInfoBiz sysAdminInfoBiz;
    @Autowired
    SysAdminBiz sysAdminBiz;
    @Autowired
    MobileValidateCodeBiz mobileValidateCodeBiz;
    @Autowired
    UserCouponBiz userCouponBiz;

    /* US/VS/LC登录
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserLoginRegisterService#login(com.ync365.seed.dto.user.UserLoginDTO)
     */
    @Override
    public ResponseDTO login(UserLoginDTO userLoginDTO) {
        ResponseDTO dto = new ResponseDTO();
        SysUser user = null;
        if (StringUtils.isNotBlank(userLoginDTO.getUserLoginName())) {
            user = sysUserBiz.verificationPasswordByLoginName(userLoginDTO.getUserLoginName(),
                    userLoginDTO.getPassword());
        } else if (StringUtils.isNotBlank(userLoginDTO.getUserMobile())) {
            user = sysUserBiz.verificationPasswordByMobile(userLoginDTO.getUserMobile(), userLoginDTO.getPassword());
        }
        if (user != null) {
            Boolean state = StringUtils.isNotEmpty(user.getUserNum());
            List<Integer> userRoleIds = sysUserBiz.getUserRole(user.getUserNum());
            UserLoginReturnDTO vo = new UserLoginReturnDTO();
            vo.setLoginName(user.getUserLoginName());
            vo.setUserMobile(user.getUserMobile());
            vo.setUsernum(user.getUserNum());
            vo.setLoginState(state.toString());
            vo.setRoleId(Collections.min(userRoleIds));
            vo.setIsFrozen(user.getIsFrozen());
            if(userRoleIds.contains(Constants.UserRole.VS.v())){
                vo.setVsRole(Constants.UserRole.VS.v());
            }
            vo.setCreateTime(user.getCreateTime());
            if (state) {
                final String userNum = user.getUserNum();
                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            userCouponBiz.loginGrantCoupon(userNum);
                        } catch (Exception e) {
                        }
                    }
                });
                thread.setDaemon(true);
                thread.start();
                dto.setCode(ResponseCode.CommonCode.OK.v());
                dto.setMsg(ResponseCode.CommonCode.OK.c());
            } else {
                dto.setCode(ResponseCode.UserCode.WRONG_PASSWD.v());
                dto.setMsg(ResponseCode.UserCode.WRONG_PASSWD.c());
            }
            dto.setData(vo);
        }
        return dto;
    }

    /* US添加完善资料
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserLoginRegisterService#perfectUsInfo(com.ync365.seed.dto.user.UserInfoDTO)
     */
    @Override
    @FormTokenAnnotation
    public ResponseDTO perfectUsInfo(UserInfoDTO userInfoDTO) {
        ResponseDTO dto = new ResponseDTO();
        UserInfoBO bo = new UserInfoBO();
        BeanUtils.copyProperties(userInfoDTO, bo);
        int sum = sysUserInfoBiz.perfectUsInfo(bo);
        if (sum == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    /* US修改资料
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserLoginRegisterService#perfectUsInfo(com.ync365.seed.dto.user.UserInfoDTO)
     */
    @Override
    public ResponseDTO updateUserRegister(UserRegisterDTO userDTO, String userNum) {
        ResponseDTO dto = new ResponseDTO();
        UserRegisterBO bo = new UserRegisterBO();
        BeanUtils.copyProperties(userDTO, bo);
        int sum = sysUserBiz.updateUserRegister(bo, userNum);
        if (sum == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(sum);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    /* VS添加完善资料
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserLoginRegisterService#perfectVsInfo(com.ync365.seed.dto.user.VsInfoDTO)
     */
    @Override
    @FormTokenAnnotation
    public ResponseDTO perfectVsInfo(VsInfoDTO vsInfoDTO) {
        ResponseDTO dto = new ResponseDTO();
        VsInfoBO bo = new VsInfoBO();
        BeanUtils.copyProperties(vsInfoDTO, bo);
        int sum = sysVsInfoBiz.perfectVsInfo(bo);
        if (sum == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    /* LC添加完善资料
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserLoginRegisterService#perfectVsInfo(com.ync365.seed.dto.user.VsInfoDTO)
     */
    @Override
    @FormTokenAnnotation
    public ResponseDTO perfectLcInfo(LcInfoDTO lcInfoDTO) {
        ResponseDTO dto = new ResponseDTO();
        LcInfoBO bo = new LcInfoBO();
        BeanUtils.copyProperties(lcInfoDTO, bo);
        int sum = sysLcInfoBiz.perfectLcInfo(bo);
        if (sum == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    /* 根据地址编号查询村站
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserLoginRegisterService#selectVsByAddress(java.lang.String)
     */
    @Override
    public ResponseDTO selectVsByAddress(String addressCode) {
        ResponseDTO dto = new ResponseDTO();
        List<VsInfoBO> list = sysVsInfoBiz.getSysVsInfoByAddressCode(addressCode);
        if (list != null && list.size() > 0) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(list);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    /* 根据编号查询LC
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserLoginRegisterService#selectLcByNum(java.lang.String)
     */
    @Override
    public ResponseDTO selectLcByNum(String userNum) {
        ResponseDTO dto = new ResponseDTO();
        List<SysLcInfo> list = sysLcInfoBiz.getSysLcInfoByUserNum(userNum);
        if (list != null && list.size() > 0) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(list);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    /* 根据编号查询SE
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserLoginRegisterService#selectSeByNum(java.lang.String)
     */
    @Override
    public ResponseDTO selectSeByNum(String userNum) {
        ResponseDTO dto = new ResponseDTO();
        List<SysAdminInfo> list = sysAdminInfoBiz.getSysSeInfoByUserNum(userNum);
        if (list != null && list.size() > 0) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(list);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    /* 重新设置US/VS/LC密码
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserLoginRegisterService#resetPassword(com.ync365.seed.dto.user.UserLoginDTO)
     */
    @Override
    @FormTokenAnnotation
    public ResponseDTO resetPassword(UserLoginDTO userLoginDTO) {
        ResponseDTO dto = new ResponseDTO();
        Boolean state = sysUserBiz.resetPassword(userLoginDTO.getUserNum(), userLoginDTO.getPassword(),
                userLoginDTO.getNewPassword(),userLoginDTO.getSafeScore());
        UserLoginReturnDTO vo = new UserLoginReturnDTO();
        vo.setLoginName(userLoginDTO.getUserLoginName());
        vo.setLoginState(state.toString());
        if (state) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.WRONG_PASSWD.v());
            dto.setMsg(ResponseCode.UserCode.WRONG_PASSWD.c());
        }
        dto.setData(vo);
        return dto;
    }

    /* SE登陆
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserLoginRegisterService#loginAdmin(com.ync365.seed.dto.user.UserLoginDTO)
     */
    @Override
    public ResponseDTO loginAdmin(UserLoginDTO userLoginDTO) {
        ResponseDTO dto = new ResponseDTO();
        SysAdmin user = sysAdminBiz.verificationLogin(userLoginDTO.getUserLoginName(), userLoginDTO.getPassword());
        Boolean state = StringUtils.isNotEmpty(user.getAdminNum());
        UserLoginReturnDTO vo = new UserLoginReturnDTO();
        vo.setLoginName(userLoginDTO.getUserLoginName());
        vo.setUsernum(user.getAdminNum());
        vo.setLoginState(state.toString());
        if (state) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.WRONG_PASSWD.v());
            dto.setMsg(ResponseCode.UserCode.WRONG_PASSWD.c());
        }
        dto.setData(vo);
        return dto;
    }

    /* SE注册
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserLoginRegisterService#registerAdmin(com.ync365.seed.dto.user.UserAdminDTO)
     */
    @Override
    @FormTokenAnnotation
    public ResponseDTO registerAdmin(AdminRegisterDTO userAdminDTO) {
        ResponseDTO dto = new ResponseDTO();
        if (!userAdminDTO.getAdminPassword().equals(userAdminDTO.getAdminRePassword())) {
            dto.setCode(ResponseCode.UserCode.WRONG_PASSWD.v());
            dto.setMsg(ResponseCode.UserCode.WRONG_PASSWD.c());
        } else if (!mobileValidateCodeBiz.validateMobileValidateCode(userAdminDTO.getAdminMobile(),
                userAdminDTO.getMobileValidateCode())) {
            dto.setCode(ResponseCode.UserCode.WRONG_VALID_CODE.v());
            dto.setMsg(ResponseCode.UserCode.WRONG_VALID_CODE.c());
        } else {
            AdminBO bo = new AdminBO();
            BeanUtils.copyProperties(userAdminDTO, bo);
            int sum = sysAdminBiz.registerAdmin(bo);
            if (sum == 1) {
                dto.setCode(ResponseCode.CommonCode.OK.v());
                dto.setMsg(ResponseCode.CommonCode.OK.c());
                dto.setData(bo);
            } else {
                dto.setCode(ResponseCode.UserCode.ALREADY_REG.v());
                dto.setMsg(ResponseCode.UserCode.ALREADY_REG.c());
            }
        }
        return dto;
    }

    /* SE添加完善资料
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserLoginRegisterService#perfectSeInfo(com.ync365.seed.dto.user.UserAdminInfoDTO)
     */
    @Override
    @FormTokenAnnotation
    public ResponseDTO perfectSeInfo(UserAdminInfoDTO userAdminInfoDTO) {
        ResponseDTO dto = new ResponseDTO();
        SysAdminInfoBO bo = new SysAdminInfoBO();
        BeanUtils.copyProperties(userAdminInfoDTO, bo);
        int sum = sysAdminInfoBiz.perfectSeInfo(bo);
        if (sum == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    /* 编号选择A+
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserLoginRegisterService#selectAPlusByNum(java.lang.String)
     */
    @Override
    public ResponseDTO selectAPlusByNum(String userNum) {
        ResponseDTO dto = new ResponseDTO();
        List<SysAdminInfo> list = sysAdminInfoBiz.getSysAPlusInfoByUserNum(userNum);
        if (list != null && list.size() > 0) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(list);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    /* 重置SE/A+密码
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserLoginRegisterService#resetPasswordAdmin(com.ync365.seed.dto.user.UserLoginDTO)
     */
    @Override
    public ResponseDTO resetPasswordAdmin(UserLoginDTO userLoginDTO) {
        ResponseDTO dto = new ResponseDTO();
        Boolean state = sysAdminBiz.resetPassword(userLoginDTO.getUserNum(), userLoginDTO.getPassword(),
                userLoginDTO.getNewPassword());
        UserLoginReturnDTO vo = new UserLoginReturnDTO();
        vo.setLoginName(userLoginDTO.getUserLoginName());
        vo.setLoginState(state.toString());
        if (state) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.WRONG_PASSWD.v());
            dto.setMsg(ResponseCode.UserCode.WRONG_PASSWD.c());
        }
        dto.setData(vo);
        return dto;
    }

    /* 用户名是否重复
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserLoginRegisterService#validateLoginName(java.lang.String)
     */
    @Override
    public ResponseDTO validateLoginName(String loginName) {
        ResponseDTO dto = new ResponseDTO();
        Boolean state = sysUserBiz.validateLoginName(loginName);
        if (state) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.ALREADY_REG.v());
            dto.setMsg(ResponseCode.UserCode.ALREADY_REG.c());
        }
        return dto;
    }

    /* 手机号是否重复
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserLoginRegisterService#validateUserMobile(java.lang.String)
     */
    @Override
    public ResponseDTO validateUserMobile(String userMobile) {
        ResponseDTO dto = new ResponseDTO();
        Boolean state = sysUserBiz.validateUserMobile(userMobile);
        if (state) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.ALREADY_REG.v());
            dto.setMsg(ResponseCode.UserCode.ALREADY_REG.c());
        }
        return dto;
    }

    /*
    * (non-Javadoc)    
    * @see com.ync365.seed.service.user.IUserLoginRegisterService#selectVsByNumOrMobile(java.lang.String)
    */
    @Override
    public ResponseDTO selectVsByNumOrMobile(String userNumOrMobile) {
        ResponseDTO dto = new ResponseDTO();
        VsInfoBO bo = sysVsInfoBiz.selectVsInfoByNumOrMobile(userNumOrMobile);
        if (bo != null && StringUtils.isNotBlank(bo.getUserNum())) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(bo);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    /* (non-Javadoc)    
     * @see com.ync365.seed.service.user.IUserLoginRegisterService#selectLcByNumOrMobile(java.lang.String)    
     */
    @Override
    public ResponseDTO selectLcByNumOrMobile(String userNumOrMobile) {
        ResponseDTO dto = new ResponseDTO();
        LcInfoBO bo = sysLcInfoBiz.selectLcInfoByNumOrMobile(userNumOrMobile);
        if (bo != null && StringUtils.isNotBlank(bo.getUserNum())) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(bo);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    /* (non-Javadoc)    
     * @see com.ync365.seed.service.user.IUserLoginRegisterService#selectSeByNumOrMobile(java.lang.String)    
     */
    @Override
    public ResponseDTO selectSeByNumOrMobile(String userNumOrMobile) {
        ResponseDTO dto = new ResponseDTO();
        SysAdminInfoBO bo = sysAdminInfoBiz.getSeInfoByUserNumOrMobile(userNumOrMobile);
        if (bo != null && StringUtils.isNotBlank(bo.getAdminNum())) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(bo);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    /* (non-Javadoc)    
     * @see com.ync365.seed.service.user.IUserLoginRegisterService#selectAPlusByNumOrMobile(java.lang.String)    
     */
    @Override
    public ResponseDTO selectAPlusByNumOrMobile(String userNumOrMobile) {
        ResponseDTO dto = new ResponseDTO();
        SysAdminInfoBO bo = sysAdminInfoBiz.selectAPlusByNumOrMobile(userNumOrMobile);
        if (bo != null && StringUtils.isNotBlank(bo.getAdminNum())) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(bo);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    @Override
    public ResponseDTO updateAdminRegister(AdminRegisterDTO userAdminDTO, String adminNum) {
        ResponseDTO dto = new ResponseDTO();
        AdminBO bo = new AdminBO();
        BeanUtils.copyProperties(userAdminDTO, bo);
        int sum = sysAdminBiz.updateAdminRegister(bo, adminNum);
        if (sum == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(sum);
        } else {
            dto.setCode(ResponseCode.UserCode.ALREADY_REG.v());
            dto.setMsg(ResponseCode.UserCode.ALREADY_REG.c());
        }
        return dto;
    }

    /* 用户名是否重复
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserLoginRegisterService#validateLoginName(java.lang.String)
     */
    @Override
    public ResponseDTO validateSeLoginName(String loginName) {
        ResponseDTO dto = new ResponseDTO();
        Boolean state = sysAdminBiz.validateSeLoginName(loginName);
        if (state) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.ALREADY_REG.v());
            dto.setMsg(ResponseCode.UserCode.ALREADY_REG.c());
        }
        return dto;
    }

    /* 手机号是否重复
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserLoginRegisterService#validateUserMobile(java.lang.String)
     */
    @Override
    public ResponseDTO validateSeUserMobile(String userMobile) {
        ResponseDTO dto = new ResponseDTO();
        Boolean state = sysAdminBiz.validateSeUserMobile(userMobile);
        if (state) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.ALREADY_REG.v());
            dto.setMsg(ResponseCode.UserCode.ALREADY_REG.c());
        }
        return dto;
    }
}
