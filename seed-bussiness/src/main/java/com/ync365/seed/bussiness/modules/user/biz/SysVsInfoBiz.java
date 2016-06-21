package com.ync365.seed.bussiness.modules.user.biz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.common.biz.RegionBiz;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.bo.UserInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.UserSearchBO;
import com.ync365.seed.bussiness.modules.user.bo.UserUsInfoSearchBo;
import com.ync365.seed.bussiness.modules.user.bo.VsInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.VsInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.dao.SysAdminServiceMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysAuthenInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysUsVsRelationshipMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysUserInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysUserMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysUserWorkRelationshipMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysVsInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysVsLcRelationshipMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysAdminService;
import com.ync365.seed.bussiness.modules.user.entity.SysAuthenInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysLcInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysUsVsRelationship;
import com.ync365.seed.bussiness.modules.user.entity.SysUser;
import com.ync365.seed.bussiness.modules.user.entity.SysUserInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysUserWorkRelationship;
import com.ync365.seed.bussiness.modules.user.entity.SysVsInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysVsLcRelationship;
import com.ync365.seed.utils.Constants;

@Service
public class SysVsInfoBiz {
    @Autowired
    SysVsInfoMapper sysVsInfoMapper;
    @Autowired
    SysUserWorkRelationshipMapper sysUserWorkRelationshipMapper;
    @Autowired
    SysAdminServiceMapper sysAdminServiceMapper;
    @Autowired
    SysVsLcRelationshipMapper sysVsLcRelationshipMapper;
    @Autowired
    SysUsVsRelationshipMapper sysUsVsRelationshipMapper;
    @Autowired
    RegionBiz sysRegionBiz;
    @Autowired
    SysUserInfoMapper sysUserInfoMapper;
    @Autowired
    SysUserInfoBiz sysUserInfoBiz;
    @Autowired
    SysUserMapper sysUserMapper;
    
    @Autowired
    SysAuthenInfoMapper sysAuthenInfoMapper;

    /**
     * 功能描述：添加用户
     * @param record
     * @return
     */
    public int insertSelective(SysVsInfo record) {
        return sysVsInfoMapper.insertSelective(record);
    }

    /**
     * 功能描述：删除 信息 by user_num
     * @param 
     * @return
     */
    public int deleteUserRoleByuserNum(String userNum) {
        return sysVsInfoMapper.deleteSysVsInfoByUserNum(userNum);
    }

    /**根据userNum有无判断是否增加修改
     * @author xieang
     * 2015年9月27日
     * @param record
     * @return
     */
    public int insertOrUpdateVsInfo(SysVsInfo record) {
        SysVsInfo i = sysVsInfoMapper.selectByPrimaryKeyUserNum(record.getUserNum());
        if (i != null && StringUtils.isNotBlank(i.getUserNum())) {
            return sysVsInfoMapper.update(record);
        } else {
            return sysVsInfoMapper.insertSelective(record);
        }
    }

    /**
     * 功能描述：查询信息by usernum
     * @param 
     * @return
     */
    public SysVsInfo selectByPrimaryKeyUserNum(String userNum) {
        return sysVsInfoMapper.selectByPrimaryKeyUserNum(userNum);
    }

    /**
     * 获取所有 list  通过map对象 传递参数
     * @return
     */
    public List<SysVsInfo> selectPageByMap(Map<String, Object> map) {
        return sysVsInfoMapper.selectPageByMap(map);
    }

    /**
     * update 
     * @param record
     * @return
     */
    public int update(SysVsInfo record) {
        return sysVsInfoMapper.update(record);
    }

    /**
     * 根据地址code查询村站
     * @author xieang
     * 2015年9月23日
     * @param addressCode
     * @return
     */
    public List<VsInfoBO> getSysVsInfoByAddressCode(String addressCode) {
        //vs权限，并且审核通过
        VsInfoSearchBO bo = new VsInfoSearchBO();
        bo.setServiceDistinct(Integer.valueOf(addressCode));
        List<VsInfoBO> list = selectSysVsInfoListByPrimary(bo);
        return list;
    }

    /**
     * 通过 us 编号usNum 查询vs 信息
     * @param userNum
     * @return
     */
    public SysVsInfo selectSysVsInfoByUserNum(String userNum) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userNum", userNum);
        return sysVsInfoMapper.selectSysVsInfoByUserNum(map);
    }

    /**
     * 完善VS信息
     * @Title: perfectVsInfo
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月9日 上午9:35:11       
     * @version: 
     *
     * @param bo
     * @return
     *
     */
    @Transactional
    public int perfectVsInfo(VsInfoBO bo) {
        if (!StringUtils.isNotEmpty(bo.getUserNum())) {
            return 0;
        }
        SysUser u = sysUserMapper.selectByPrimaryKeyUserNum(bo.getUserNum());
        if (u == null || !StringUtils.isNotEmpty(u.getUserNum())) {
            return 0;
        }
        SysVsInfo info = new SysVsInfo();
        info.setUserNum(bo.getUserNum());
        info.setLocalCroplandArea(bo.getLocalCroplandArea());
        info.setLocalFarmerCount(bo.getLocalFarmerCount());
        info.setTelephone(bo.getTelephone());
        info.setWorkYear(bo.getWorkYear());
        info.setAuthenticationState(Constants.AuthenticationState.Audit.v());
        info.setCreateTime(Calendar.getInstance().getTime());
        SysUserInfo uinfo = new SysUserInfo();
        uinfo.setUserNum(bo.getUserNum());
        uinfo.setSysUserBirthday(bo.getBirthday());
        uinfo.setSysUserAddress(bo.getAddress());
        uinfo.setSysUserAddressDetail(bo.getAddressDetail());
        uinfo.setSysUserIdCard(bo.getIdCard());
        uinfo.setSysNickname(bo.getSysNickname());
        uinfo.setSysUserFarmArea(bo.getFarmArea());
        uinfo.setSysUserGender(bo.getGender());
        uinfo.setSysUserPhotoPath(bo.getPhotoPath());
        uinfo.setSysUserRealName(bo.getName());
        List<String> doWorks = bo.getDoworks();
        for (String integer : doWorks) {
            SysUserWorkRelationship ship = new SysUserWorkRelationship();
            ship.setUserNum(bo.getUserNum());
            ship.setWork(integer);
            sysUserWorkRelationshipMapper.insertSelective(ship);
        }
        List<Integer> services = bo.getServiceDistincts();
        for (Integer integer : services) {
            SysAdminService service = new SysAdminService();
            service.setAdminNum(bo.getUserNum());
            service.setAdminServiceDistinct(integer);
            sysAdminServiceMapper.insertSelective(service);
        }
        sysUserInfoBiz.insertOrUpdateUserInfo(uinfo);
        SysVsLcRelationship sysVsLcRelationship = new SysVsLcRelationship();
        sysVsLcRelationship.setVsNum(bo.getUserNum());
        sysVsLcRelationship.setLcNum(bo.getLcNum());
        sysVsLcRelationshipMapper.insertSelective(sysVsLcRelationship);
        return insertOrUpdateVsInfo(info);
    }

    /**
     * 修改VS信息
     * @Title: updateVsInfo
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月9日 上午10:04:10       
     * @version: 
     *
     * @param bo
     * @return
     *
     */
    public int updateVsInfo(VsInfoBO bo) {
        if (!StringUtils.isNotEmpty(bo.getUserNum())) {
            return 0;
        }
        SysUser u = sysUserMapper.selectByPrimaryKeyUserNum(bo.getUserNum());
        if (u == null || !StringUtils.isNotEmpty(u.getUserNum())) {
            return 0;
        }
        SysVsInfo info = new SysVsInfo();
        info.setUserNum(bo.getUserNum());
        info.setLocalCroplandArea(bo.getLocalCroplandArea());
        info.setLocalFarmerCount(bo.getLocalFarmerCount());
        info.setTelephone(bo.getTelephone());
        info.setWorkYear(bo.getWorkYear());
        SysUserInfo uinfo = new SysUserInfo();
        uinfo.setUserNum(bo.getUserNum());
        uinfo.setSysUserBirthday(bo.getBirthday());
        uinfo.setSysUserAddress(bo.getAddress());
        uinfo.setSysUserAddressDetail(bo.getAddressDetail());
        uinfo.setSysUserIdCard(bo.getIdCard());
        uinfo.setSysNickname(bo.getSysNickname());
        uinfo.setSysUserFarmArea(bo.getFarmArea());
        uinfo.setSysUserGender(bo.getGender());
        uinfo.setSysUserPhotoPath(bo.getPhotoPath());
        uinfo.setSysUserRealName(bo.getName());
        List<String> doWorks = bo.getDoworks();
        if (doWorks != null && doWorks.size() > 0) {
            sysUserWorkRelationshipMapper.deleteSysUserWorkByUserNum(bo.getUserNum());
            for (String integer : doWorks) {
                if (StringUtils.isNotBlank(integer)) {
                    SysUserWorkRelationship ship = new SysUserWorkRelationship();
                    ship.setUserNum(bo.getUserNum());
                    ship.setWork(integer);
                    sysUserWorkRelationshipMapper.insertSelective(ship);
                }
            }
        }
        List<Integer> services = bo.getServiceDistincts();
        if (services != null && services.size() > 0) {
            sysAdminServiceMapper.deleteByAdminNumAndLevel(bo.getUserNum(), 5);
            for (Integer integer : services) {
                if (integer != null) {
                    SysAdminService service = new SysAdminService();
                    service.setAdminNum(bo.getUserNum());
                    service.setAdminServiceDistinct(integer);
                    sysAdminServiceMapper.insertSelective(service);
                }
            }
        }
        sysUserInfoBiz.insertOrUpdateUserInfo(uinfo);
        return insertOrUpdateVsInfo(info);
    }

    /**
     * 查询vsinfo列表
     * @param vsInfoSearchBO
     * @return
     */
    public List<VsInfoBO> selectSysVsInfoListByPrimary(VsInfoSearchBO vsInfoSearchBO) {
        return sysVsInfoMapper.selectSysVsInfoListByPrimary(vsInfoSearchBO);
    }

    /**
     * count vsinfo
     * @param vsInfoSearchBO
     * @return
     */
    public int selectCountSysVsInfoByPrimary(VsInfoSearchBO vsInfoSearchBO) {
        return sysVsInfoMapper.selectCountSysVsInfoByPrimary(vsInfoSearchBO);
    }

    /**根据编号或者手机号搜索
     * @author xieang
     * 2015年10月10日
     * @param userNum
     * @return
     */
    public VsInfoBO selectVsInfoByNumOrMobile(String userNum) {
        VsInfoSearchBO bo = new VsInfoSearchBO();
        bo.setUserMobileSearch(userNum);
        List<VsInfoBO> list = selectSysVsInfoListByPrimary(bo);
        if (list == null || list.size() < 1) {
            bo.setUserMobileSearch(null);
            bo.setUserNumSearch(userNum);
            list = selectSysVsInfoListByPrimary(bo);
        }
        return (list != null && list.size() > 0) ? list.get(0) : new VsInfoBO();
    }

    /**vs绑定lc
     * @author xieang
     * 2015年10月11日
     * @param sysVsLcRelationship
     * @return
     */
    @Transactional
    public int bindingLc(SysVsLcRelationship sysVsLcRelationship) {
        sysVsLcRelationshipMapper.deleteSysVsLcByVsNum(sysVsLcRelationship.getVsNum());
        return sysVsLcRelationshipMapper.insertSelective(sysVsLcRelationship);
    }

    /**
     * 更具编号查询vs详细信息 包括绑定lc se a+
     * @author xieang
     * 2015年10月10日
     * @param userNum
     * @return
     */
    public VsInfoBO selectVsInfoByUserNum(String userNum) {
        return sysVsInfoMapper.selectVsInfoByUserNum(userNum);
    }

    /**获取详细信息，信息处理出来数据
     * @author xieang
     * 2015年10月15日
     * @param userNum
     * @return
     */
    public VsInfoBO getUserInfoAllData(String userNum, Integer... levels) {
        VsInfoBO vsInfoBO = sysVsInfoMapper.selectVsInfoByUserNum(userNum);
        if (vsInfoBO != null && StringUtils.isNotBlank(vsInfoBO.getUserNum())) {
            vsInfoBO.setDoworks(sysUserWorkRelationshipMapper.selectWorkByUserNum(vsInfoBO.getUserNum()));
            List<SysAdminService> sysAdminServiceList;
            if (levels != null && levels.length == 1) {
                sysAdminServiceList = sysAdminServiceMapper.selectServiceListByNumLevel(vsInfoBO.getUserNum(),
                        levels[0]);
            } else {
                sysAdminServiceList = sysAdminServiceMapper.selectServiceListByNum(vsInfoBO.getUserNum());
            }

            List<Integer> list = new ArrayList<Integer>();
            for (SysAdminService sysAdminService : sysAdminServiceList) {
                list.add(sysAdminService.getAdminServiceDistinct());
            }
            vsInfoBO.setServiceDistincts(list);
        }
        return vsInfoBO;
    }

    /**转移VS下的US
     * @author xieang
     * 2015年10月18日
     * @param stateC
     * @param userUsInfoSearchBo
     * @param userNumList
     * @param oldNum
     * @param newNum
     * @return
     */
    @Transactional
    public int vsChange(String stateC, UserUsInfoSearchBo userUsInfoSearchBo, List<String> userNumList, String oldNum,
            String newNum) {
        int i = 0;
        if ("all".equals(stateC) || "search".equals(stateC)) {
            if ("all".equals(stateC)) {
                userUsInfoSearchBo = new UserUsInfoSearchBo();
            }
            userUsInfoSearchBo.setVsNum(oldNum);
            List<UserInfoBO> userList = sysUserInfoMapper.selectUserInfoByParam(userUsInfoSearchBo);
            for (UserInfoBO userInfoBO : userList) {
                SysUsVsRelationship sysUsVsRelationship = new SysUsVsRelationship();
                sysUsVsRelationship.setUsNum(userInfoBO.getUserNum());
                sysUsVsRelationship.setVsNum(newNum);
                sysUsVsRelationshipMapper.update(sysUsVsRelationship);
                i++;
            }
        } else if ("data".equals(stateC)) {
            for (String userNum : userNumList) {
                SysUsVsRelationship sysUsVsRelationship = new SysUsVsRelationship();
                sysUsVsRelationship.setUsNum(userNum);
                sysUsVsRelationship.setVsNum(newNum);
                sysUsVsRelationshipMapper.update(sysUsVsRelationship);
                i++;
            }
        }

        return i;
    }

    /**审核的方法
     * 如果审核通过并且有vs权限，对自己的vs和us进行绑定
     * @author xieang
     * 2015年10月28日
     * @param userNum
     * @param authenticationState
     * @param infoMeg 
     * @param bo 
     */
    @Transactional
    public void isAuthentication(String userNum, Boolean authenticationState, String infoMeg, AdminBO bo) {
        SysVsInfo sysVsInfo = new SysVsInfo();
        //审核记录表
        SysAuthenInfo sysAuthenInfo = new SysAuthenInfo();
        sysVsInfo.setUserNum(userNum);
        if (authenticationState) {
            sysVsInfo.setAuthenticationState(Constants.AuthenticationState.Success.v());
            sysAuthenInfo.setAuthenState(Constants.AuthenticationState.Success.v());
        } else {
            sysVsInfo.setAuthenticationState(Constants.AuthenticationState.Failure.v());
            sysAuthenInfo.setAuthenState(Constants.AuthenticationState.Failure.v());
        }
        sysVsInfo.setAuthenticationTime(new Date());
        sysVsInfoMapper.update(sysVsInfo);
        if (authenticationState) {
            //开始vs/us关系绑定
            SysUsVsRelationship sysUsVsRelationship = new SysUsVsRelationship();
            sysUsVsRelationship.setUsNum(userNum);
            sysUsVsRelationship.setVsNum(userNum);
            sysUsVsRelationshipMapper.deleteSysUsVsByUsNum(userNum);
            sysUsVsRelationshipMapper.insertSelective(sysUsVsRelationship);
        }
        //添加记录
        if (null != userNum && null != userNum ) {
            sysAuthenInfo.setSource(Constants.LargeCustomerState.VS.v());
            sysAuthenInfo.setUserNum(userNum);
            sysAuthenInfo.setAuthenReason(infoMeg);
            sysAuthenInfo.setAuthenTime(Calendar.getInstance().getTime());
            sysAuthenInfo.setAuthenAdminName(bo.getAdminName());
            sysAuthenInfo.setAuthenAdminNum(bo.getAdminNum());
            sysAuthenInfoMapper.insertSelective(sysAuthenInfo);
        }
    }
}
