package com.ync365.seed.service.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ync365.seed.bussiness.modules.common.biz.MobileValidateCodeBiz;
import com.ync365.seed.bussiness.modules.order.biz.UserCouponBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysAdminBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysFarmInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysLargeCustomerInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysLargeCustomerPropertyInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysSsInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUserAttentionGoodsBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUserAttentionPopStoreBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUserBankBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUserBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUserBrowseBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUserYnbBiz;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.bo.LargeCustomerInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.UserBO;
import com.ync365.seed.bussiness.modules.user.bo.UserRegisterBO;
import com.ync365.seed.bussiness.modules.user.entity.SysAuthenInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysFarmInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysLargeCustomerPropertyInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysLargeCustomerValueInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysSsInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysUserAttentionGoods;
import com.ync365.seed.bussiness.modules.user.entity.SysUserAttentionPopStore;
import com.ync365.seed.bussiness.modules.user.entity.SysUserBank;
import com.ync365.seed.bussiness.modules.user.entity.SysUserBrowse;
import com.ync365.seed.bussiness.modules.user.entity.SysUserYnb;
import com.ync365.seed.dto.base.ResponseCode;
import com.ync365.seed.dto.base.ResponseDTO;
import com.ync365.seed.dto.user.LargeCustomerInfoDTO;
import com.ync365.seed.dto.user.LargeCustomerPropertyInfoDTO;
import com.ync365.seed.dto.user.SysFarmInfoDTO;
import com.ync365.seed.dto.user.SysUserBrowseDTO;
import com.ync365.seed.dto.user.SysUserYnbDTO;
import com.ync365.seed.dto.user.UserAttentionDTO;
import com.ync365.seed.dto.user.UserBankInfoDTO;
import com.ync365.seed.dto.user.UserLoginDTO;
import com.ync365.seed.dto.user.UserRegisterDTO;
import com.ync365.seed.service.annotation.FormTokenAnnotation;
import com.ync365.seed.service.user.IUserService;
import com.ync365.seed.utils.StringUtils;

/**    
 *     
 * @Title：UserServiceImpl  
 * @Description: TODO   
 * @author: duan.zhao.qian     
 * @date: 2015年10月13日 上午11:40:31      
 * @version     
 *     
 */
public class UserServiceImpl implements IUserService {
    @Autowired
    SysUserBiz sysUserBiz;
    @Autowired
    SysAdminBiz sysAdminBiz;
    @Autowired
    SysUserAttentionGoodsBiz sysUserAttentionGoodsBiz;
    @Autowired
    SysUserAttentionPopStoreBiz sysUserAttentionPopStoreBiz;
    @Autowired
    SysLargeCustomerInfoBiz sysLargeCustomerInfoBiz;
    @Autowired
    MobileValidateCodeBiz mobileValidateCodeBiz;
    @Autowired
    SysUserBankBiz sysUserBankBiz;
    @Autowired
    SysLargeCustomerPropertyInfoBiz sysLargeCustomerPropertyInfoBiz;
    @Autowired
    SysSsInfoBiz sysSsInfoBiz;
    @Autowired
    SysFarmInfoBiz sysFarmInfoBiz;

    @Autowired
    SysUserBrowseBiz sysUserBrowseBiz;
    @Autowired
    SysUserYnbBiz sysUserYnbBiz;
    @Override
    public ResponseDTO sayHello(String str) {
        System.out.println(str);
        ResponseDTO dto = new ResponseDTO();
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        dto.setData("hello  " + str);
        return dto;
    }

    /* 注册
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserService#registerUs(com.ync365.seed.dto.user.UserDTO)
     */
    @Override
    @FormTokenAnnotation
    public ResponseDTO register(UserRegisterDTO vo) {
        ResponseDTO dto = new ResponseDTO();
        if (!vo.getUserPassword().equals(vo.getUserRePassword())) {
            dto.setCode(ResponseCode.UserCode.WRONG_PASSWD.v());
            dto.setMsg(ResponseCode.UserCode.WRONG_PASSWD.c());
        } else if (!mobileValidateCodeBiz.validateMobileValidateCode(vo.getUserMobile(), vo.getMobileValidateCode())) {
            dto.setCode(ResponseCode.UserCode.WRONG_VALID_CODE.v());
            dto.setMsg(ResponseCode.UserCode.WRONG_VALID_CODE.c());
        } else {
            UserRegisterBO bo = new UserRegisterBO();
            BeanUtils.copyProperties(vo, bo);
            int sum = sysUserBiz.insertSysUser(bo);
            if (sum == 1) {
                dto.setCode(ResponseCode.CommonCode.OK.v());
                dto.setMsg(ResponseCode.CommonCode.OK.c());
            } else {
                dto.setCode(ResponseCode.UserCode.ALREADY_REG.v());
                dto.setMsg(ResponseCode.UserCode.ALREADY_REG.c());
            }
            dto.setData(bo);
        }
        return dto;
    }

    /* US大客户认证材料添加
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserService#perfectUsLargeCustomerInfo(com.ync365.seed.dto.user.LargeCustomerInfoDTO)
     */
    @Override
    @FormTokenAnnotation
    public ResponseDTO perfectUsLargeCustomerInfo(LargeCustomerInfoDTO largeCustomerInfoDTO) {
        ResponseDTO dto = new ResponseDTO();
        LargeCustomerInfoBO bo = new LargeCustomerInfoBO();
        BeanUtils.copyProperties(largeCustomerInfoDTO, bo);
        List<LargeCustomerPropertyInfoDTO> listPropertys = largeCustomerInfoDTO.getPropertys();
        Map<SysLargeCustomerPropertyInfo, SysLargeCustomerValueInfo> map = new HashMap<>();
        for (LargeCustomerPropertyInfoDTO largeCustomerPropertyInfoDTO : listPropertys) {
            SysLargeCustomerPropertyInfo p = new SysLargeCustomerPropertyInfo();
            SysLargeCustomerValueInfo v = new SysLargeCustomerValueInfo();
            v.setPropertyId(largeCustomerPropertyInfoDTO.getPropertyId());
            v.setPropertyValue(largeCustomerPropertyInfoDTO.getPropertyValue());
            v.setUserNum(largeCustomerInfoDTO.getUserNum());
            map.put(p, v);
        }
        bo.setSysLargeCustomerValueInfo(map);
        int sum = sysLargeCustomerInfoBiz.addOrUpdateLargeCustomerInfo(bo);//修改为新增或修改
//        int sum = sysLargeCustomerInfoBiz.addLargeCustomerInfo(bo);
        if (sum == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.CommonCode.PARAM_WRONG.v());
            dto.setMsg(ResponseCode.CommonCode.PARAM_WRONG.c());
        }
        return dto;
    }

    @Override
    public ResponseDTO updateUsLargeCustomerInfo(LargeCustomerInfoDTO largeCustomerInfoDTO) {
        ResponseDTO dto = new ResponseDTO();
        LargeCustomerInfoBO bo = new LargeCustomerInfoBO();
        BeanUtils.copyProperties(largeCustomerInfoDTO, bo);
        List<LargeCustomerPropertyInfoDTO> listPropertys = largeCustomerInfoDTO.getPropertys();
        Map<SysLargeCustomerPropertyInfo, SysLargeCustomerValueInfo> map = new HashMap<>();
        for (LargeCustomerPropertyInfoDTO largeCustomerPropertyInfoDTO : listPropertys) {
            SysLargeCustomerPropertyInfo p = new SysLargeCustomerPropertyInfo();
            SysLargeCustomerValueInfo v = new SysLargeCustomerValueInfo();

            v.setPropertyId(largeCustomerPropertyInfoDTO.getPropertyId());
            v.setPropertyValue(largeCustomerPropertyInfoDTO.getPropertyValue());
            v.setUserNum(largeCustomerInfoDTO.getUserNum());
            v.setId(largeCustomerPropertyInfoDTO.getValueId());
            map.put(p, v);
        }
        bo.setSysLargeCustomerValueInfo(map);
        int sum = sysLargeCustomerInfoBiz.addOrUpdateLargeCustomerInfo(bo);//改为修改或者新增
//        int sum = sysLargeCustomerInfoBiz.updateLargeCustomerInfo(bo);
        if (sum == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.CommonCode.PARAM_WRONG.v());
            dto.setMsg(ResponseCode.CommonCode.PARAM_WRONG.c());
        }
        return dto;
    }

    /* US获取大客户认证信息
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserService#getUsLargeCustomerInfo(java.lang.String)
     */
    @Override
    public ResponseDTO getUsLargeCustomerInfo(String userNum) {
        ResponseDTO dto = new ResponseDTO();
        LargeCustomerInfoBO sysLargeCustomerInfo = sysLargeCustomerInfoBiz.getUsLargeCustomerInfo(userNum);
        if (sysLargeCustomerInfo != null) {
            LargeCustomerInfoDTO dto1 = new LargeCustomerInfoDTO();
            List<LargeCustomerPropertyInfoDTO> listPropertys = new ArrayList<>();
            BeanUtils.copyProperties(sysLargeCustomerInfo, dto1);
            Map<SysLargeCustomerPropertyInfo, SysLargeCustomerValueInfo> map = sysLargeCustomerInfo
                    .getSysLargeCustomerValueInfo();
            for (Entry<SysLargeCustomerPropertyInfo, SysLargeCustomerValueInfo> entry : map.entrySet()) {
                LargeCustomerPropertyInfoDTO largeCustomerPropertyInfoDTO = new LargeCustomerPropertyInfoDTO();
                largeCustomerPropertyInfoDTO.setPropertyId(entry.getKey().getId());
                largeCustomerPropertyInfoDTO.setPropertyName(entry.getKey().getPropertyName());
                largeCustomerPropertyInfoDTO.setValueId(entry.getValue().getId());
                largeCustomerPropertyInfoDTO.setPropertyValue(entry.getValue().getPropertyValue());
                largeCustomerPropertyInfoDTO.setPropertyHint(entry.getKey().getPropertyHint());
                largeCustomerPropertyInfoDTO.setPropertyType(entry.getKey().getPropertyType());
                listPropertys.add(largeCustomerPropertyInfoDTO);
            }
            dto1.setPropertys(listPropertys);
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(dto1);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    /* US获取关注的商品
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserService#getUserAttentionGoods(java.lang.String)
     */
    @Override
    public ResponseDTO getUserAttentionGoods(String userNum) {
        ResponseDTO dto = new ResponseDTO();
        List<SysUserAttentionGoods> list = sysUserAttentionGoodsBiz.selectPageByMap(userNum);
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

    /* US删除关注的商品
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserService#deleteUserAttentionGoods(com.ync365.seed.dto.user.UserAttentionDTO)
     */
    @Override
    public ResponseDTO deleteUserAttentionGoods(UserAttentionDTO userAttentionDTO) {
        ResponseDTO dto = new ResponseDTO();
        SysUserAttentionGoods sysUserAttentionGoods = new SysUserAttentionGoods();
        sysUserAttentionGoods.setGoodsId(userAttentionDTO.getGoodsId());
        sysUserAttentionGoods.setUserNum(userAttentionDTO.getUserNum());

        int sum = sysUserAttentionGoodsBiz.deleteByBean(sysUserAttentionGoods);
        if (sum == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(sum);
        } else {
            dto.setCode(ResponseCode.CommonCode.PARAM_WRONG.v());
            dto.setMsg(ResponseCode.CommonCode.PARAM_WRONG.c());
        }
        return dto;
    }

    /* US获取关注的店铺
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserService#getUserAttentionPopStore(java.lang.String)
     */
    @Override
    public ResponseDTO getUserAttentionPopStore(String userNum) {
        ResponseDTO dto = new ResponseDTO();
        List<SysUserAttentionPopStore> list = sysUserAttentionPopStoreBiz.selectPageByMap(userNum);
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

    /* US删除关注的店铺
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserService#deleteUserAttentionPopStore(com.ync365.seed.dto.user.UserAttentionDTO)
     */
    @Override
    public ResponseDTO deleteUserAttentionPopStore(UserAttentionDTO userAttentionDTO) {
        ResponseDTO dto = new ResponseDTO();
        SysUserAttentionPopStore sysUserAttentionPopStore = new SysUserAttentionPopStore();
        sysUserAttentionPopStore.setPopStoreNum(userAttentionDTO.getPopStoreNum());
        sysUserAttentionPopStore.setUserNum(userAttentionDTO.getUserNum());

        int sum = sysUserAttentionPopStoreBiz.deleteByBean(sysUserAttentionPopStore);
        if (sum == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.CommonCode.PARAM_WRONG.v());
            dto.setMsg(ResponseCode.CommonCode.PARAM_WRONG.c());
        }
        return dto;
    }

    /* US/VS/LC解绑手机号
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserService#unbundlingMobile(java.lang.String)
     */
    @Override
    public ResponseDTO unbundlingMobile(String userNum) {
        ResponseDTO dto = new ResponseDTO();
        Boolean state = sysUserBiz.unbundlingMobile(userNum);
        if (state) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    /* US/VS/LC修改手机号
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserService#modifyMobile(com.ync365.seed.dto.user.UserLoginDTO)
     */
    @Override
    public ResponseDTO modifyMobile(UserLoginDTO userLoginDTO) {
        ResponseDTO dto = new ResponseDTO();
        Boolean state = sysUserBiz.modifyMobile(userLoginDTO.getUserNum(), userLoginDTO.getUserMobile());
        if (state) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    /* SE解绑手机号
     * (non-Javadoc)
     * @see com.ync365.seed.service.user.IUserService#unbundlingMobileAdmin(java.lang.String)
     */
    @Override
    public ResponseDTO unbundlingMobileAdmin(String userNum) {
        ResponseDTO dto = new ResponseDTO();
        Boolean state = sysAdminBiz.unbundlingMobile(userNum);
        if (state) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    @Override
    public ResponseDTO modifyMobileAdmin(UserLoginDTO userLoginDTO) {
        ResponseDTO dto = new ResponseDTO();
        Boolean state = sysAdminBiz.modifyMobile(userLoginDTO.getUserNum(), userLoginDTO.getUserMobile());
        if (state) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    @Override
    public ResponseDTO getUserByUserName(String username) {
        ResponseDTO dto = new ResponseDTO();
        UserBO bo = sysUserBiz.getUserByUserName(username);
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

    @Override
    public ResponseDTO getSEByUserName(String username) {
        ResponseDTO dto = new ResponseDTO();
        AdminBO bo = sysAdminBiz.getSEByUserName(username);
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
    @FormTokenAnnotation
    public ResponseDTO addUserBankInfo(UserBankInfoDTO userBankInfoDTO) {
        ResponseDTO dto = new ResponseDTO();
        SysUserBank userbank = new SysUserBank();
        BeanUtils.copyProperties(userBankInfoDTO, userbank);
        int n = sysUserBankBiz.addUserBankInfo(userbank);
        if (n == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(n);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    @Override
    public ResponseDTO updateUserBankInfo(UserBankInfoDTO userBankInfoDTO) {
        ResponseDTO dto = new ResponseDTO();
        SysUserBank userbank = new SysUserBank();
        BeanUtils.copyProperties(userBankInfoDTO, userbank);
        int n = sysUserBankBiz.updateUserBankInfo(userbank);
        if (n == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(n);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    @Override
    public ResponseDTO getUserBankInfo(String usernum) {
        ResponseDTO dto = new ResponseDTO();
        List<SysUserBank> userbanks = sysUserBankBiz.getUserBankInfo(usernum);
        if (userbanks != null && userbanks.size() > 0) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(userbanks);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    @Override
    public ResponseDTO getUsLargeCustomerProperty() {
        ResponseDTO dto = new ResponseDTO();
        List<SysLargeCustomerPropertyInfo> list = sysLargeCustomerPropertyInfoBiz.selectAll();
        List<LargeCustomerPropertyInfoDTO> dtos = new ArrayList<>();
        for (SysLargeCustomerPropertyInfo info : list) {
            LargeCustomerPropertyInfoDTO d = new LargeCustomerPropertyInfoDTO();
            d.setPropertyHint(info.getPropertyHint());
            d.setPropertyId(info.getId());
            d.setPropertyName(info.getPropertyName());
            d.setPropertyType(info.getPropertyType());
            dtos.add(d);
        }
        if (list != null && list.size() > 0) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(dtos);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    @Override
    public ResponseDTO getSsInfoByMobileOrNum(String mobileOrNum) {
        ResponseDTO dto = new ResponseDTO();
        SysSsInfo info = sysSsInfoBiz.getSsInfoByMobileOrNum(mobileOrNum);
        if (info != null && StringUtils.isNotBlank(info.getUserNum())) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(info);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    /* (non-Javadoc)    
     * @see com.ync365.seed.service.user.IUserService#getFarmInfoByUserNum(java.lang.String)    
     */
    @Override
    public ResponseDTO getFarmInfoByUserNum(String userNum) {
        ResponseDTO dto = new ResponseDTO();
        List<SysFarmInfo> list = sysFarmInfoBiz.selectFarmInfoByUserNum(userNum);
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

    /* (non-Javadoc)    
     * @see com.ync365.seed.service.user.IUserService#addFarmInfoByUserNum(com.ync365.seed.dto.user.SysFarmInfoDTO)    
     */
    @Override
    @FormTokenAnnotation
    public ResponseDTO addFarmInfo(SysFarmInfoDTO sysFarmInfoDTO) {
        ResponseDTO dto = new ResponseDTO();
        SysFarmInfo record = new SysFarmInfo();
        BeanUtils.copyProperties(sysFarmInfoDTO, record);
        int n = sysFarmInfoBiz.insertSelective(record);
        if (n == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(n);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    /* (non-Javadoc)    
     * @see com.ync365.seed.service.user.IUserService#updateFarmInfoByUserNum(com.ync365.seed.dto.user.SysFarmInfoDTO)    
     */
    @Override
    public ResponseDTO updateFarmInfo(SysFarmInfoDTO sysFarmInfoDTO) {
        ResponseDTO dto = new ResponseDTO();
        SysFarmInfo record = new SysFarmInfo();
        BeanUtils.copyProperties(sysFarmInfoDTO, record);
        int n = sysFarmInfoBiz.updateByPrimaryKeySelective(record);
        if (n == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(n);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    @Override
    @FormTokenAnnotation
    public ResponseDTO addUserAttentionGoods(UserAttentionDTO userAttentionDTO) {
        ResponseDTO dto = new ResponseDTO();
        SysUserAttentionGoods record = new SysUserAttentionGoods();
        record.setGoodsId(userAttentionDTO.getGoodsId());
        record.setUserNum(userAttentionDTO.getUserNum());
        int n = sysUserAttentionGoodsBiz.insert(record);
        if (n == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(n);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    @Override
    @FormTokenAnnotation
    public ResponseDTO addUserAttentionPopStore(UserAttentionDTO userAttentionDTO) {
        ResponseDTO dto = new ResponseDTO();
        SysUserAttentionPopStore record = new SysUserAttentionPopStore();
        record.setUserNum(userAttentionDTO.getUserNum());
        record.setPopStoreNum(userAttentionDTO.getPopStoreNum());
        int n = sysUserAttentionPopStoreBiz.insert(record);
        if (n == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(n);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    @Override
    public ResponseDTO addUserBrowse(SysUserBrowseDTO sysUserBrowseDTO) {
        ResponseDTO dto = new ResponseDTO();
        SysUserBrowse record = new SysUserBrowse();
        BeanUtils.copyProperties(sysUserBrowseDTO, record);
        int n = sysUserBrowseBiz.insertSelective(record);
        if (n == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(n);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    @Override
    public ResponseDTO addUserBrowses(List<SysUserBrowseDTO> sysUserBrowseDTOs) {
        ResponseDTO dto = new ResponseDTO();
        int n = 0;
        for (SysUserBrowseDTO sysUserBrowseDTO : sysUserBrowseDTOs) {
            SysUserBrowse record = new SysUserBrowse();
            BeanUtils.copyProperties(sysUserBrowseDTO, record);
            int m = sysUserBrowseBiz.insertSelective(record);
            n += m;
        }
        if (n > 0) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(n);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    @Override
    public ResponseDTO getUserBrowses(String userNum, Integer count) {
        ResponseDTO dto = new ResponseDTO();
        List<SysUserBrowse> list = sysUserBrowseBiz.selectByUserNum(userNum, count);
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

    @Override
    public ResponseDTO deleteAllBrowses(String userNum) {
        ResponseDTO dto = new ResponseDTO();
        int n = sysUserBrowseBiz.deleteAllBrowses(userNum);
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        dto.setData(n);
        return dto;
    }

    @Override
    @FormTokenAnnotation
    public ResponseDTO addUserYnb(SysUserYnbDTO sysUserYnbDTO) {
        ResponseDTO dto = new ResponseDTO();
        SysUserYnb info = new SysUserYnb();
        BeanUtils.copyProperties(sysUserYnbDTO, info);
        int n = sysUserYnbBiz.insertSelective(info);
        if (n == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(n);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    @Override
    public ResponseDTO getUserYnbByUserNum(String userNum) {
        ResponseDTO dto = new ResponseDTO();
        SysUserYnb info = sysUserYnbBiz.selectByUserNum(userNum);
        if (info != null && StringUtils.isNotEmpty(info.getYnbId())) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(info);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    @Override
    public ResponseDTO checkUserAttentionGoods(String userNum, Integer goodsId) {
        ResponseDTO dto = new ResponseDTO();
        Boolean result = sysUserAttentionGoodsBiz.checkUserAttentionGoods(userNum, goodsId);
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        dto.setData(result);
        return dto;
    }

    @Override
    public ResponseDTO checkUserAttentionPopStore(String userNum, String popStoreNum) {
        ResponseDTO dto = new ResponseDTO();
        Boolean result = sysUserAttentionPopStoreBiz.checkUserAttentionGoods(userNum, popStoreNum);
        dto.setCode(ResponseCode.CommonCode.OK.v());
        dto.setMsg(ResponseCode.CommonCode.OK.c());
        dto.setData(result);
        return dto;
    }

    @Override
    public ResponseDTO deleteUserYnbById(Integer id) {
        ResponseDTO dto = new ResponseDTO();
        int n = sysUserYnbBiz.deleteByPrimaryKey(id);
        if (n == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(n);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    @Override
    public ResponseDTO updateUserYnbById(SysUserYnbDTO sysUserYnbDTO) {
        ResponseDTO dto = new ResponseDTO();
        SysUserYnb record = new SysUserYnb();
        BeanUtils.copyProperties(sysUserYnbDTO, record);
        int n = sysUserYnbBiz.updateByPrimaryKeySelective(record);
        if (n == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(n);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    @Override
    public ResponseDTO deleteFarmInfoById(Integer id) {
        ResponseDTO dto = new ResponseDTO();
        int n = sysFarmInfoBiz.deleteByPrimaryKey(id);
        if (n == 1) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(n);
        } else {
            dto.setCode(ResponseCode.UserCode.USER_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.UserCode.USER_NOT_EXISTS.c());
        }
        return dto;
    }

    @Override
    public ResponseDTO getAuthenticationFailed(String usernum, Integer count) {
        ResponseDTO dto = new ResponseDTO();
        List<SysAuthenInfo> list = sysLargeCustomerInfoBiz.selectSysAuthenInfoListByPrimary(usernum,count);
        if (null != list && list.size() > 0) {
            dto.setCode(ResponseCode.CommonCode.OK.v());
            dto.setMsg(ResponseCode.CommonCode.OK.c());
            dto.setData(list);
        } else {
            dto.setCode(ResponseCode.CommonCode.OBJECT_NOT_EXISTS.v());
            dto.setMsg(ResponseCode.CommonCode.OBJECT_NOT_EXISTS.c());
        }
        return dto;
    }

}
