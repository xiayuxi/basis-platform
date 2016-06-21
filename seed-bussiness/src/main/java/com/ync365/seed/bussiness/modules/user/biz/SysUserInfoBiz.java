package com.ync365.seed.bussiness.modules.user.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.common.biz.RegionBiz;
import com.ync365.seed.bussiness.modules.user.bo.LcBO;
import com.ync365.seed.bussiness.modules.user.bo.UsVsLcBO;
import com.ync365.seed.bussiness.modules.user.bo.UserInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.UserSearchBO;
import com.ync365.seed.bussiness.modules.user.bo.UserUsInfoSearchBo;
import com.ync365.seed.bussiness.modules.user.bo.VsBO;
import com.ync365.seed.bussiness.modules.user.dao.SysAdminServiceMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysLcInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysReceiveGoodsInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysUsVsRelationshipMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysUserGrowKindRelationshipMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysUserInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysUserMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysVsInfoMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysLcInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysReceiveGoodsInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysUsVsRelationship;
import com.ync365.seed.bussiness.modules.user.entity.SysUser;
import com.ync365.seed.bussiness.modules.user.entity.SysUserGrowKindRelationship;
import com.ync365.seed.bussiness.modules.user.entity.SysUserInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysVsInfo;
import com.ync365.seed.utils.Constants;

@Service
public class SysUserInfoBiz {

    @Autowired
    SysUserInfoMapper sysUserInfoMapper;
    @Autowired
    SysVsInfoMapper sysVsInfoMapper;
    @Autowired
    SysLcInfoMapper sysLcInfoMapper;
    @Autowired
    SysAdminServiceMapper sysAdminServiceMapper;
    @Autowired
    SysUserGrowKindRelationshipMapper sysUserGrowKindRelationshipMapper;
    @Autowired
    SysUsVsRelationshipMapper sysUsVsRelationshipMapper;
    @Autowired
    RegionBiz sysRegionBiz;
    @Autowired
    SysReceiveGoodsInfoMapper sysReceiveGoodsInfoMapper;
    @Autowired
    SysUserMapper sysUserMapper;

    /**
     * 编号删除信息，物理删除
     * 
     * @param String userNum
     * @return
     */
    public int deleteByPrimaryKeyUserNum(String userNum) {
        return sysUserInfoMapper.deleteByPrimaryKeyUserNum(userNum);
    }

    /**
     * 添加信息
     * 
     * @param record
     * @return
     */
    public int insertSelective(SysUserInfo record) {
        return sysUserInfoMapper.insertSelective(record);
    }

    /**
     * 编号获取信息
     * 
     * @param userNum
     * @return
     */
    public SysUserInfo selectByPrimaryKeyUserNum(String userNum) {
        return sysUserInfoMapper.selectByPrimaryKeyUserNum(userNum);
    }

    /**
     * 编号修改信息
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(SysUserInfo record) {
        return sysUserInfoMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 多功能查询，分页添加pageIndex,pageSize参数
     * 
     * @param map
     * @return
     */
    public List<SysUserInfo> selectPageByMap(Map<String, Object> map) {
        return sysUserInfoMapper.selectPageByMap(map);
    }

    /**
     * 条件获取数量
     * 
     * @param map
     * @return
     */
    public int selectPageCount(Map<String, Object> map) {
        return sysUserInfoMapper.selectPageCount(map);
    }

    /**根据userNum有无判断是否增加修改
     * @author xieang
     * 2015年9月21日
     * @param record
     * @return
     */
    public int insertOrUpdateUserInfo(SysUserInfo record) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("userNum", record.getUserNum());
        int i = sysUserInfoMapper.selectPageCount(map);
        if (i > 0) {
            return sysUserInfoMapper.updateByPrimaryKeySelective(record);
        } else {
            return sysUserInfoMapper.insertSelective(record);
        }
    }

    /**
     * 通过vs 编号vsNum  查询us 列表
     * @param vsNum
     * @param record
     * @return
     */
    public List<SysUserInfo> selectSysUsListByVsNum(String vsNum, UserSearchBO bo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("vsNum", vsNum);
        map.put("userMobile", bo.getUserMobile());
        map.put("beginTime", bo.getBeginTime());
        map.put("endTime", bo.getEndTime());
        map.put("sysUserRealName", bo.getSysUserRealName());
        return sysUserInfoMapper.selectSysUsListByVsNum(map);
    }

    /**
     * 通过vs 编号vsNum  查询us 列表
     * @param vsNum
     * @param record
     * @return
     */
    public int selectCountSysUsByVsNum(String vsNum, UserSearchBO bo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("vsNum", vsNum);
        map.put("userMobile", bo.getUserMobile());
        map.put("beginTime", bo.getBeginTime());
        map.put("endTime", bo.getEndTime());
        map.put("sysUserRealName", bo.getSysUserRealName());
        return sysUserInfoMapper.selectCouontSysUsByVsNum(map);
    }

    /**
     * 通过lc 编号lcNum  连表 查询 us 列表
     * @param lcNum
     * @param record
     * @return
     */
    public List<SysUserInfo> selectSysUsInfoListByLcNum(String lcNum, UserSearchBO bo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("lcNum", lcNum);
        return sysUserInfoMapper.selectSysUsInfoListByLcNum(map);
    }

    /**
     * 通过Lc 编号 lcNum 连接 查询 us count
     * @param lcNum
     * @param record
     * @return
     */
    public int selectCountSysUsByLcNum(String lcNum, UserSearchBO bo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("lcNum", lcNum);
        return sysUserInfoMapper.selectCouontSysUsByLcNum(map);
    }

    /**
     * us编号获取vs、lc及vs、lc服务区域
     * @Title: getUsVsLcBO
     * @Description: us编号获取vs、lc及vs、lc服务区域    ：    
     * @author: duan.zhao.qian
     * @date: 2015年9月30日 下午2:26:31       
     * @version: 
     *
     * @param usernum
     * @return
     *
     */
    public UsVsLcBO getUsVsLcBO(String usernum) {
        UsVsLcBO bo = new UsVsLcBO();
        VsBO vsbo = new VsBO();
        LcBO lcbo = new LcBO();
        bo.setUs(selectByPrimaryKeyUserNum(usernum));
        bo.setLc(lcbo);
        bo.setVs(vsbo);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userNum", usernum);
        SysVsInfo vs = sysVsInfoMapper.selectSysVsInfoByUserNum(map);
        SysLcInfo lc = sysLcInfoMapper.selectSysLcInfoByVsNum(vs.getUserNum());
        vsbo.setVs(vs);
        lcbo.setLc(lc);
        Map<String, Object> servicemap = new HashMap<String, Object>();
        servicemap.put("adminNum", vs.getUserNum());
        vsbo.setServices(sysAdminServiceMapper.selectPageByMap(servicemap));
        servicemap.put("adminNum", lc.getUserNum());
        lcbo.setServices(sysAdminServiceMapper.selectPageByMap(servicemap));
        return bo;
    }

    /**
     * By userNum 查询 us信息
     * @param userNum
     * @return
     */
    public UserInfoBO selectUserInfoByUserNum(String userNum) {
        return sysUserInfoMapper.getUserInfoByUserNum(userNum);
    }

    /**
     * 完善US信息
     * @Title: perfectUsInfo
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月8日 下午3:01:09       
     * @version: 
     *
     * @param bo
     * @return
     *
     */
    public int perfectUsInfo(UserInfoBO bo) {
        if (!StringUtils.isNotEmpty(bo.getUserNum())) {
            return 0;
        }
        SysUser u = sysUserMapper.selectByPrimaryKeyUserNum(bo.getUserNum());
        if(u==null||!StringUtils.isNotEmpty(u.getUserNum())){
            return 0;
        }
        SysUserInfo info = new SysUserInfo();
        BeanUtils.copyProperties(bo, info);
        List<String> listGrowKinds = bo.getSysUserGrowKinds();
        sysUserGrowKindRelationshipMapper.deleteByUserNumm(bo.getUserNum());
        for (String integer : listGrowKinds) {
            SysUserGrowKindRelationship record = new SysUserGrowKindRelationship();
            record.setGrowKind(integer);
            record.setUserNum(bo.getUserNum());
            sysUserGrowKindRelationshipMapper.insertSelective(record);
        }
        sysUsVsRelationshipMapper.deleteSysUsVsByUsNum(bo.getUserNum());
        SysUsVsRelationship record = new SysUsVsRelationship();
        record.setUsNum(bo.getUserNum());
        record.setVsNum(bo.getVsNum());
        if (info.getSysUserAddress() != null && com.ync365.seed.utils.StringUtils.isNotEmpty(info.getSysUserAddressDetail())) {
            SysReceiveGoodsInfo receiveGoodsInfo = new SysReceiveGoodsInfo();
            receiveGoodsInfo.setSysReceiveGoodsAddress(info.getSysUserAddress());
            receiveGoodsInfo.setSysReceiveGoodsAddressDetail(info.getSysUserAddressDetail());
            receiveGoodsInfo.setSysReceiveGoodsIsDefault(1);
            receiveGoodsInfo.setSysReceiveGoodsMobile(u.getUserMobile());
            receiveGoodsInfo.setSysReceiveGoodsName(info.getSysUserRealName());
            receiveGoodsInfo.setUserNum(info.getUserNum());
            sysReceiveGoodsInfoMapper.insertSelective(receiveGoodsInfo);
        }
        sysUsVsRelationshipMapper.insertSelective(record);
        return insertOrUpdateUserInfo(info);
    }

    /**
     * 更新US信息
     * @Title: updateUsInfo
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月8日 下午5:52:59       
     * @version: 
     *
     * @param bo
     * @return
     *
     */
    public int updateUsInfo(UserInfoBO bo) {
        if (!StringUtils.isNotEmpty(bo.getUserNum())) {
            return 0;
        }
        SysUser u = sysUserMapper.selectByPrimaryKeyUserNum(bo.getUserNum());
        if (u == null || !StringUtils.isNotEmpty(u.getUserNum())) {
            return 0;
        }
        int i = 0;
        SysUserInfo info = new SysUserInfo();
        info.setUserNum(bo.getUserNum());
        info.setSysUserBirthday(bo.getSysUserBirthday());
        info.setSysUserAddress(bo.getSysUserAddress());
        info.setSysUserAddressDetail(bo.getSysUserAddressDetail());
        info.setSysUserIdCard(bo.getSysUserIdCard());
        info.setSysNickname(bo.getSysNickname());
        info.setSysUserFarmArea(bo.getSysUserFarmArea());
        info.setSysUserGender(bo.getSysUserGender());
        info.setSysUserPhotoPath(bo.getSysUserPhotoPath());
        info.setSysUserRealName(bo.getSysUserRealName());
        List<String> listGrowKinds = bo.getSysUserGrowKinds();
        if (listGrowKinds != null && listGrowKinds.size() > 0) {
            sysUserGrowKindRelationshipMapper.deleteByUserNumm(bo.getUserNum());
            for (String integer : listGrowKinds) {
                SysUserGrowKindRelationship record = new SysUserGrowKindRelationship();
                if(null == integer || "" == integer){
                    continue;
                }else{
                    
                    record.setGrowKind(integer);
                    record.setUserNum(bo.getUserNum());
                    sysUserGrowKindRelationshipMapper.insertSelective(record);
                }
            }
        }
        if (StringUtils.isNotEmpty(bo.getVsNum())) {
            sysUsVsRelationshipMapper.deleteSysUsVsByUsNum(bo.getUserNum());
            SysUsVsRelationship record = new SysUsVsRelationship();
            record.setUsNum(bo.getUserNum());
            record.setVsNum(bo.getVsNum());
            sysUsVsRelationshipMapper.insertSelective(record);
            i = 1;
        }
        if (StringUtils.isNotBlank(bo.getaMobile()) || bo.getRegisterLocation() != null) {
            SysUser sysUser = new SysUser();
            sysUser.setUserNum(bo.getUserNum());
            sysUser.setUserMobile(bo.getUserMobile());
            sysUser.setRegisterLocation(bo.getRegisterLocation());
            sysUserMapper.updateByPrimaryKeySelective(sysUser);
        }
        int sum = insertOrUpdateUserInfo(info);
        return sum == 0 ? i : sum;
        //        return sysUserInfoMapper.updateByPrimaryKeySelective(info);
    }

    /**查询
     * @author xieang
     * 2015年10月9日
     * @param userUsInfoSearchBo
     * @return
     */
    public List<UserInfoBO> selectUserInfoByParam(UserUsInfoSearchBo userUsInfoSearchBo) {
        return sysUserInfoMapper.selectUserInfoByParam(userUsInfoSearchBo);
    }

    /**查询
     * @author xieang
     * 2015年10月9日
     * @param userUsInfoSearchBo
     * @return
     */
    public int selectUserInfoByParamCount(UserUsInfoSearchBo userUsInfoSearchBo) {
        return sysUserInfoMapper.selectUserInfoByParamCount(userUsInfoSearchBo);
    }

    /**
     * 通过us编号查询所有的关联信息
     * @author xieang
     * 2015年10月9日
     * @param userUsInfoSearchBo
     * @return
     */
    public UserInfoBO getUserInfoByUserNum(String userNum) {
        return sysUserInfoMapper.getUserInfoByUserNum(userNum);
    }

    /**
     * 查询测土培肥 列表  by leixc 20151011
     * @param userUsInfoSearchBo
     * @return
     */
    public List<UserInfoBO> selectUserInfoCtpfByParamy(UserUsInfoSearchBo userUsInfoSearchBo) {
        return sysUserInfoMapper.selectUserInfoCtpfByParamy(userUsInfoSearchBo);
    }

    /**
     * 测土配肥 count by leixc 20151011
     * @param userUsInfoSearchBo
     * @return
     */
    public int selectUserInfoCtpfByParamyCount(UserUsInfoSearchBo userUsInfoSearchBo) {
        return sysUserInfoMapper.selectUserInfoCtpfByParamyCount(userUsInfoSearchBo);
    }

    /**获取详细信息，并将地址、种植等id信息处理出来数据
     * @author xieang
     * 2015年10月15日
     * @param userNum
     * @return
     */
    public UserInfoBO getUserInfoAllData(String userNum) {
        UserInfoBO userInfoBO = sysUserInfoMapper.getUserInfoByUserNum(userNum);
        if (userInfoBO != null && StringUtils.isNotBlank(userInfoBO.getUserNum())) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("userNum", userInfoBO.getUserNum());
            map.put("sysReceiveGoodsIsDefault", Constants.Status.Enabled.v());
            List<SysReceiveGoodsInfo> sysReceiveGoodsInfoList = sysReceiveGoodsInfoMapper.selectPageByMap(map);
            userInfoBO.setSysReceiveGoodsInfos(sysReceiveGoodsInfoList);
            userInfoBO.setSysUserGrowKinds(
                    sysUserGrowKindRelationshipMapper.selectGrowKindByUserNum(userInfoBO.getUserNum()));
        }
        return userInfoBO;
    }
}
