package com.ync365.seed.bussiness.modules.user.biz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.bo.LcInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.LcInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.bo.VsInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.VsInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.dao.SysAdminInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysAdminServiceMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysAuthenInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysLcInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysLcSeRelationshipMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysUsVsRelationshipMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysUserInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysUserMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysUserWorkRelationshipMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysVsInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysVsLcRelationshipMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysAdminService;
import com.ync365.seed.bussiness.modules.user.entity.SysAuthenInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysLcInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysLcSeRelationship;
import com.ync365.seed.bussiness.modules.user.entity.SysUsVsRelationship;
import com.ync365.seed.bussiness.modules.user.entity.SysUser;
import com.ync365.seed.bussiness.modules.user.entity.SysUserInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysUserWorkRelationship;
import com.ync365.seed.bussiness.modules.user.entity.SysVsInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysVsLcRelationship;
import com.ync365.seed.utils.Constants;
import com.ync365.seed.utils.StringUtils;

@Service
public class SysLcInfoBiz {
    @Autowired
    SysLcInfoMapper sysLcInfoMapper;

    @Autowired
    SysUserInfoMapper sysUserInfoMapper;

    @Autowired
    SysVsInfoMapper sysVsInfoMapper;

    @Autowired
    SysAdminInfoMapper sysAdminInfoMapper;

    @Autowired
    SysUserWorkRelationshipMapper sysUserWorkRelationshipMapper;
    @Autowired
    SysAdminServiceMapper sysAdminServiceMapper;
    @Autowired
    SysLcSeRelationshipMapper sysLcSeRelationshipMapper;
    @Autowired
    SysVsLcRelationshipMapper sysVsLcRelationshipMapper;
    @Autowired
    SysUsVsRelationshipMapper sysUsVsRelationshipMapper;
    @Autowired
    SysUserInfoBiz sysUserInfoBiz;
    @Autowired
    SysUserMapper sysUserMapper;
    
    @Autowired
    SysAuthenInfoMapper sysAuthenInfoMapper;

    /**
     * 功能描述：添加用户
     * 
     * @param record
     * @return
     */
    public int insertSelective(SysLcInfo record) {
        return sysLcInfoMapper.insertSelective(record);
    }

    /**
     * 功能描述：删除 信息 by user_num
     * 
     * @param
     * @return
     */
    public int deleteByPrimaryKey(String userNum) {
        return sysLcInfoMapper.deleteByPrimaryKey(userNum);
    }

    /**
     * 功能描述：查询信息by usernum
     * 
     * @param
     * @return
     */
    public SysLcInfo selectByPrimaryKey(String userNum) {
        return sysLcInfoMapper.selectByPrimaryKey(userNum);
    }

    /**
     * 获取所有 list 通过map对象 传递参数
     * 
     * @return
     */
    public List<SysLcInfo> selectPageByMap(Map<String, Object> map) {
        return sysLcInfoMapper.selectPageByMap(map);
    }

    /**
     * update
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(SysLcInfo record) {
        return sysLcInfoMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据userNum有无判断是否增加修改
     * 
     * @author xieang 2015年9月21日
     * @param record
     * @return
     */
    public int insertOrUpdateUserInfo(SysLcInfo record) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("userNum", record.getUserNum());
        int i = sysLcInfoMapper.selectPageCount(map);
        if (i > 0) {
            return sysLcInfoMapper.updateByPrimaryKeySelective(record);
        } else {
            return sysLcInfoMapper.insertSelective(record);
        }
    }

    /**
     * 根据用户编号查询LC
     * @param userNum
     * @return
     */
    public List<SysLcInfo> getSysLcInfoByUserNum(String userNum) {
        // lc权限，并且审核通过
        return sysLcInfoMapper.getSysLcInfoByUserNum(userNum, Constants.UserRole.LC.v(),
                Constants.AuthenticationState.Success.v());
    }

    /**
     * 根据A+编号获取此A+用户下 所有lc用户信息
     * 
     * @param map
     * @return
     */
    public int selectCountSysLcInfoByAnum(Map<String, Object> map) {
        return sysLcInfoMapper.selectCountSysLcInfoByAnum(map);
    }

    /**
     * by Vs编号查询此vs的绑定Lc
     * 
     * @param userNum
     * @return
     */
    public SysLcInfo selectSysLcInfoByVsNum(String userNum) {
        return sysLcInfoMapper.selectSysLcInfoByVsNum(userNum);
    }

    /**
     * by us编号查询此us的绑定Lc
     * 
     * @param userNum
     * @return
     */
    public SysLcInfo selectSysLcInfoByUsNum(String userNum) {
        return sysLcInfoMapper.selectSysLcInfoByUsNum(userNum);
    }

    /**
     * 根据se编号获取此se用户下 所有lc用户信息 count
     * 
     * @param map
     * @return
     */
    public int selectCountSysLcInfoBySenum(Map<String, Object> map) {
        return sysLcInfoMapper.selectCountSysLcInfoBySenum(map);
    }

    /**
     * 完善LC信息
     * @Title: perfectLcInfo
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月9日 上午9:45:04       
     * @version: 
     *
     * @param bo
     * @return
     *
     */
    public int perfectLcInfo(LcInfoBO bo) {
        if (!StringUtils.isNotEmpty(bo.getUserNum())) {
            return 0;
        }
        SysUser u = sysUserMapper.selectByPrimaryKeyUserNum(bo.getUserNum());
        if (u == null || !StringUtils.isNotEmpty(u.getUserNum())) {
            return 0;
        }
        SysLcInfo info = new SysLcInfo();
        info.setAuthenticationState(Constants.AuthenticationState.Audit.v());
        info.setCreateTime(Calendar.getInstance().getTime());
        info.setLocalCroplandArea(bo.getLocalCroplandArea());
        info.setLocalFarmerCount(bo.getLocalFarmerCount());
        info.setTelephone(bo.getTelephone());
        info.setUserNum(bo.getUserNum());
        info.setWorkYear(bo.getWorkYear());
        SysUserInfo uinfo = new SysUserInfo();
        uinfo.setUserNum(bo.getUserNum());
        uinfo.setSysUserBirthday(bo.getBirthday());
        uinfo.setSysUserAddress(bo.getAddress());
        uinfo.setSysUserAddressDetail(bo.getAddressDetail());
        uinfo.setSysUserIdCard(bo.getIdCard());
        uinfo.setSysNickname(bo.getNickName());
        uinfo.setSysUserFarmArea(bo.getFarmArea());
        uinfo.setSysUserGender(bo.getGender());
        uinfo.setSysUserPhotoPath(bo.getPhotoPath());
        uinfo.setSysUserRealName(bo.getName());
        List<String> doWorks = bo.getDoworks();
        for (String integer : doWorks) {
            if (StringUtils.isNotBlank(integer)) {
                SysUserWorkRelationship ship = new SysUserWorkRelationship();
                ship.setUserNum(bo.getUserNum());
                ship.setWork(integer);
                sysUserWorkRelationshipMapper.insertSelective(ship);
            }
        }
        List<Integer> services = bo.getServiceDistincts();
        for (Integer integer : services) {
            if (integer != null) {
                SysAdminService service = new SysAdminService();
                service.setAdminNum(bo.getUserNum());
                service.setAdminServiceDistinct(integer);
                sysAdminServiceMapper.insertSelective(service);
            }
        }
        if (bo.getApplyVs()) {
            SysVsInfo vsinfo = new SysVsInfo();
            vsinfo.setAuthenticationState(Constants.AuthenticationState.Audit.v());
            vsinfo.setCreateTime(Calendar.getInstance().getTime());
            vsinfo.setLocalCroplandArea(bo.getLocalCroplandArea());
            vsinfo.setLocalFarmerCount(bo.getLocalFarmerCount());
            vsinfo.setTelephone(bo.getTelephone());
            vsinfo.setUserNum(bo.getUserNum());
            vsinfo.setWorkYear(bo.getWorkYear());
            sysVsInfoMapper.insertSelective(vsinfo);//TODO 同时是vs时，vs绑定是否为本lc
        }
        SysLcSeRelationship sysLcSeRelationship = new SysLcSeRelationship();
        sysLcSeRelationship.setLcNum(bo.getUserNum());
        sysLcSeRelationship.setSeNum(bo.getSeNum());
        sysLcSeRelationshipMapper.insertSelective(sysLcSeRelationship);
        sysUserInfoBiz.insertOrUpdateUserInfo(uinfo);
        return insertOrUpdateUserInfo(info);
    }

    /**
     * 修改LC信息
     * @Title: updateSysLcInfo
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月9日 上午9:56:33       
     * @version: 
     *
     * @param bo
     *
     */
    public int updateSysLcInfo(LcInfoBO bo) {
        if (!StringUtils.isNotEmpty(bo.getUserNum())) {
            return 0;
        }
        SysUser u = sysUserMapper.selectByPrimaryKeyUserNum(bo.getUserNum());
        if (u == null || !StringUtils.isNotEmpty(u.getUserNum())) {
            return 0;
        }
        SysLcInfo info = new SysLcInfo();
        info.setAuthenticationState(Constants.AuthenticationState.Audit.v());
        //        info.setCreateTime(Calendar.getInstance().getTime());
        info.setLocalCroplandArea(bo.getLocalCroplandArea());
        info.setLocalFarmerCount(bo.getLocalFarmerCount());
        info.setTelephone(bo.getTelephone());
        info.setUserNum(bo.getUserNum());
        info.setWorkYear(bo.getWorkYear());
        SysUserInfo uinfo = new SysUserInfo();
        uinfo.setUserNum(bo.getUserNum());
        uinfo.setSysUserBirthday(bo.getBirthday());
        uinfo.setSysUserAddress(bo.getAddress());
        uinfo.setSysUserAddressDetail(bo.getAddressDetail());
        uinfo.setSysUserIdCard(bo.getIdCard());
        uinfo.setSysNickname(bo.getNickName());
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
            if (bo.getApplyVs() != null && bo.getApplyVs()) {//如果有vs权限时，全部清理
                sysAdminServiceMapper.deleteByAdminNum(bo.getUserNum());
            } else {
                sysAdminServiceMapper.deleteByAdminNumAndLevel(bo.getUserNum(), 4);
            }
            for (Integer integer : services) {
                if (integer != null) {
                    SysAdminService service = new SysAdminService();
                    service.setAdminNum(bo.getUserNum());
                    service.setAdminServiceDistinct(integer);
                    sysAdminServiceMapper.insertSelective(service);
                }
            }
        }
        if (bo.getApplyVs() == null) {

        } else if (bo.getApplyVs()) {
            SysVsInfo vsinfo = new SysVsInfo();
            vsinfo.setAuthenticationState(Constants.AuthenticationState.Audit.v());
            vsinfo.setCreateTime(Calendar.getInstance().getTime());
            vsinfo.setLocalCroplandArea(bo.getLocalCroplandArea());
            vsinfo.setLocalFarmerCount(bo.getLocalFarmerCount());
            vsinfo.setTelephone(bo.getTelephone());
            vsinfo.setUserNum(bo.getUserNum());
            vsinfo.setWorkYear(bo.getWorkYear());
            sysVsInfoMapper.update(vsinfo);
        } else {
            sysVsInfoMapper.deleteSysVsInfoByUserNum(bo.getUserNum());
        }
        sysUserInfoBiz.insertOrUpdateUserInfo(uinfo);
        return insertOrUpdateUserInfo(info);
    }

    /**
     *  by Primary查询lc列表
     * @param lcInfoSearchBO
     * @return
     */
    public List<LcInfoBO> selectSysLcInfoListByPrimary(LcInfoSearchBO lcInfoSearchBO) {
        return sysLcInfoMapper.selectSysLcInfoListByPrimary(lcInfoSearchBO);
    }

    /**
     * by Primary 查询 lc count
     * @param lcInfoSearchBO
     * @return
     */
    public int selectCountSysLcInfoByPrimary(LcInfoSearchBO lcInfoSearchBO) {
        return sysLcInfoMapper.selectCountSysLcInfoByPrimary(lcInfoSearchBO);
    }

    /**根据编号或者手机号查询
     * @author xieang
     * 2015年10月11日
     * @param userNum
     * @return
     */
    public LcInfoBO selectLcInfoByNumOrMobile(String userNum) {
        LcInfoSearchBO bo = new LcInfoSearchBO();
        bo.setUserMobileSearch(userNum);
        List<LcInfoBO> list = selectSysLcInfoListByPrimary(bo);
        if (list == null || list.size() < 1) {
            bo.setUserMobileSearch(null);
            bo.setUserNumSearch(userNum);
            list = selectSysLcInfoListByPrimary(bo);
        }
        return (list != null && list.size() > 0) ? list.get(0) : new LcInfoBO();
    }

    /**根据用户编号获取Lc相信信息（包括绑定关系）
     * @author xieang
     * 2015年10月11日
     * @param userNum
     * @return
     */
    public LcInfoBO getLcInfoByUserNum(String userNum) {
        return sysLcInfoMapper.getLcInfoByUserNum(userNum);
    }

    /**获取详细信息，信息处理出来数据
     * @author xieang
     * 2015年10月15日
     * @param userNum
     * @return
     */
    public LcInfoBO getUserInfoAllData(String userNum, Integer... levels) {
        LcInfoBO lcInfoBO = sysLcInfoMapper.getLcInfoByUserNum(userNum);
        if (lcInfoBO != null && StringUtils.isNotBlank(lcInfoBO.getUserNum())) {
            lcInfoBO.setDoworks(sysUserWorkRelationshipMapper.selectWorkByUserNum(lcInfoBO.getUserNum()));
            List<SysAdminService> sysAdminServiceList;
            if (levels != null && levels.length == 1) {
                sysAdminServiceList = sysAdminServiceMapper.selectServiceListByNumLevel(lcInfoBO.getUserNum(),
                        levels[0]);
            } else {
                sysAdminServiceList = sysAdminServiceMapper.selectServiceListByNum(lcInfoBO.getUserNum());
            }
            List<Integer> list = new ArrayList<Integer>();
            for (SysAdminService sysAdminService : sysAdminServiceList) {
                list.add(sysAdminService.getAdminServiceDistinct());
            }
            lcInfoBO.setServiceDistincts(list);
        }
        return lcInfoBO;
    }

    /**lc绑定se
     * @author xieang
     * 2015年10月11日
     * @param sysLcSeRelationship
     * @return
     */
    @Transactional
    public int bindingSe(SysLcSeRelationship sysLcSeRelationship) {
        sysLcSeRelationshipMapper.deleteByLcNum(sysLcSeRelationship.getLcNum());
        return sysLcSeRelationshipMapper.insertSelective(sysLcSeRelationship);
    }

    /**转移LC下的VS
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
    public int lcChange(String stateC, VsInfoSearchBO vsInfoSearchBO, List<String> userNumList, String oldNum,
            String newNum) {
        int i = 0;
        if ("all".equals(stateC) || "search".equals(stateC)) {
            if ("all".equals(stateC)) {
                vsInfoSearchBO = new VsInfoSearchBO();
            }
            vsInfoSearchBO.setLcNum(oldNum);
            List<VsInfoBO> userList = sysVsInfoMapper.selectSysVsInfoListByPrimary(vsInfoSearchBO);
            for (VsInfoBO vsInfoBO : userList) {
                SysVsLcRelationship sysVsLcRelationship = new SysVsLcRelationship();
                sysVsLcRelationship.setLcNum(newNum);
                sysVsLcRelationship.setVsNum(vsInfoBO.getUserNum());
                sysVsLcRelationshipMapper.update(sysVsLcRelationship);
                i++;
            }
        } else if ("data".equals(stateC)) {
            for (String userNum : userNumList) {
                SysVsLcRelationship sysVsLcRelationship = new SysVsLcRelationship();
                sysVsLcRelationship.setLcNum(newNum);
                sysVsLcRelationship.setVsNum(userNum);
                sysVsLcRelationshipMapper.update(sysVsLcRelationship);
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
     * @param bo 
     * @param infoMeg 
     */
    @Transactional
    public void isAuthentication(String userNum, Boolean authenticationState, String infoMeg, AdminBO bo) {
        //审核记录表
        SysAuthenInfo sysAuthenInfo = new SysAuthenInfo();
        
        SysLcInfo record = new SysLcInfo();
        record.setUserNum(userNum);
        if (authenticationState) {
            record.setAuthenticationState(Constants.AuthenticationState.Success.v());
            sysAuthenInfo.setAuthenState(Constants.AuthenticationState.Success.v());
        } else {
            record.setAuthenticationState(Constants.AuthenticationState.Failure.v());
            sysAuthenInfo.setAuthenState(Constants.AuthenticationState.Failure.v());
        }
        record.setAuthenticationTime(new Date());
        sysLcInfoMapper.updateByPrimaryKeySelective(record);
        SysVsInfo vsInfo = sysVsInfoMapper.selectByPrimaryKeyUserNum(userNum);
        if (vsInfo != null && StringUtils.isNotBlank(vsInfo.getUserNum())) {
            SysVsInfo sysVsInfo = new SysVsInfo();
            sysVsInfo.setUserNum(userNum);
            if (authenticationState) {
                sysVsInfo.setAuthenticationState(Constants.AuthenticationState.Success.v());
            } else {
                sysVsInfo.setAuthenticationState(Constants.AuthenticationState.Failure.v());
            }
            sysVsInfo.setAuthenticationTime(new Date());
            sysVsInfoMapper.update(sysVsInfo);
            if (authenticationState) {
                //开始lc/vs关系绑定
                SysVsLcRelationship sysVsLcRelationship = new SysVsLcRelationship();
                sysVsLcRelationship.setLcNum(userNum);
                sysVsLcRelationship.setVsNum(userNum);
                sysVsLcRelationshipMapper.deleteSysVsLcByVsNum(userNum);
                sysVsLcRelationshipMapper.insertSelective(sysVsLcRelationship);
                //开始vs/us关系绑定
                SysUsVsRelationship sysUsVsRelationship = new SysUsVsRelationship();
                sysUsVsRelationship.setUsNum(userNum);
                sysUsVsRelationship.setVsNum(userNum);
                sysUsVsRelationshipMapper.deleteSysUsVsByUsNum(userNum);
                sysUsVsRelationshipMapper.insertSelective(sysUsVsRelationship);
            }
        }
        //添加记录
        if (null != userNum && null != userNum ) {
            sysAuthenInfo.setSource(Constants.LargeCustomerState.LC.v());
            sysAuthenInfo.setUserNum(userNum);
            sysAuthenInfo.setAuthenReason(infoMeg);
            sysAuthenInfo.setAuthenTime(Calendar.getInstance().getTime());
            sysAuthenInfo.setAuthenAdminName(bo.getAdminName());
            sysAuthenInfo.setAuthenAdminNum(bo.getAdminNum());
            sysAuthenInfoMapper.insertSelective(sysAuthenInfo);
        }
    }
}
