package com.ync365.seed.bussiness.modules.user.biz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.user.bo.AdminInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.bo.LcInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.LcInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.bo.SysAdminInfoBO;
import com.ync365.seed.bussiness.modules.user.dao.SysAdminInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysAdminMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysAdminServiceMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysLcInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysLcSeRelationshipMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysSeARelationshipMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysUserRoleMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysAdmin;
import com.ync365.seed.bussiness.modules.user.entity.SysAdminInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysAdminService;
import com.ync365.seed.bussiness.modules.user.entity.SysLcSeRelationship;
import com.ync365.seed.bussiness.modules.user.entity.SysSeARelationship;
import com.ync365.seed.bussiness.modules.user.entity.SysUserRole;
import com.ync365.seed.bussiness.modules.user.util.NumGenerator;
import com.ync365.seed.utils.Constants;
import com.ync365.seed.utils.MD5Utils;

@Service
public class SysAdminInfoBiz {

    @Autowired
    private SysAdminMapper sysAdminMapper;
    @Autowired
    private SysAdminInfoMapper sysAdminInfoMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysAdminServiceMapper sysAdminServiceMapper;
    @Autowired
    private SysSeARelationshipMapper sysSeARelationshipMapper;
    @Autowired
    private SysLcSeRelationshipMapper sysLcSeRelationshipMapper;
    @Autowired
    private SysLcInfoMapper sysLcInfoMapper;

    public int insertSelective(SysAdminInfo record) {
        return sysAdminInfoMapper.insertSelective(record);
    }

    /**
     * 根据用户编号查询SE
     * @author xieang
     * 2015年9月23日
     * @param userNum
     * @return
     */
    public List<SysAdminInfo> getSysSeInfoByUserNum(String userNum) {
        //SE权限，并且审核通过
        return sysAdminInfoMapper.getSysAdminInfoByUserNum(userNum, Constants.UserRole.SE.v(),
                Constants.AuthenticationState.Success.v());
    }

    /**
     * 根据用户编号查询A+
     * @author xieang
     * 2015年9月24日
     * @param userNum
     * @return
     */
    public List<SysAdminInfo> getSysAPlusInfoByUserNum(String userNum) {
        //A+权限，并且审核通过
        return sysAdminInfoMapper.getSysAdminInfoByUserNum(userNum, Constants.UserRole.APlus.v(),
                Constants.AuthenticationState.Success.v());
    }

    /**
     * 根据SE编号查询SE信息
     * @author xieang
     * 2015年10月11日
     * @param userNum
     * @return
     */
    public SysAdminInfoBO getSeInfoByUserNumOrMobile(String userNum) {
        AdminInfoSearchBO bo=new AdminInfoSearchBO();
       bo.setMobile(userNum);
        List<SysAdminInfoBO> list=selectSysSeInfoByParam(bo);
        if(list==null||list.size()<1){
            bo.setMobile(null);
            bo.setAdminNum(userNum);
            list=selectSysSeInfoByParam(bo);
        }
        return (list != null && list.size() > 0) ? list.get(0) : new SysAdminInfoBO();
    }

    /**
     * 根据A+编号查询A+信息
     * @author xieang
     * 2015年10月11日
     * @param userNum
     * @return
     */
    public SysAdminInfoBO getAPlusInfoByUserNumOrMobile(String userNum) {
        //A+权限
        return sysAdminInfoMapper.getAdminInfoByUserNumOrMobile(userNum, Constants.UserRole.APlus.v());
    }

    /**编辑
     * @author xieang
     * 2015年10月12日
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(SysAdminInfo record) {
        return sysAdminInfoMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据adminNum有无判断是否增加修改
     * @author xieang
     * 2015年9月21日
     * @param record
     * @return
     */
    public int insertOrUpdateAdminInfo(SysAdminInfo record) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("adminNum", record.getAdminNum());
    	int i = sysAdminInfoMapper.selectPageCount(map);
        if (i>0) {
            return sysAdminInfoMapper.updateByPrimaryKeySelective(record);
        } else {
            return sysAdminInfoMapper.insertSelective(record);
        }
    }

    /**
     * by vs 编号查询 se信息
     * @param userNum
     * @return
     */
    public SysAdminInfo selectSysAdminInfoByVsNum(String userNum) {
        return sysAdminInfoMapper.selectSysAdminInfoByVsNum(userNum);
    }

    /**
     * by us 编号查询 se信息
     * @param userNum
     * @return
     */
    public SysAdminInfo selectSysAdminInfoByUsNum(String userNum) {
        return sysAdminInfoMapper.selectSysAdminInfoByUsNum(userNum);
    }

    /**
     * by lc 编号 查询 se 信息
     * @param userNum
     * @return
     */
    public SysAdminInfo selectSysAdminInfoByLcNum(String userNum) {
        return sysAdminInfoMapper.selectSysAdminInfoByLcNum(userNum);
    }

    /**
     * 完善SE信息
     * @Title: perfectSeInfo
     * @author: duan.zhao.qian
     * @date: 2015年10月9日 下午9:25:11       
     * @version: 
     *
     * @param bo
     * @return
     *
     */
    @Transactional
    public int perfectSeInfo(SysAdminInfoBO bo) {
        SysAdmin admin=sysAdminMapper.selectByPrimaryKey(bo.getAdminNum());
        if(admin==null||!com.ync365.seed.utils.StringUtils.isNoneEmpty(bo.getAdminNum())){
            return 0;
        }
        SysAdminInfo info = new SysAdminInfo();
        BeanUtils.copyProperties(bo, info);
        info.setCreateTime(Calendar.getInstance().getTime());
        List<Integer> listServices = bo.getServiceserviceDistincts();
        for (Integer integer : listServices) {
            SysAdminService service = new SysAdminService();
            service.setAdminNum(bo.getAdminNum());
            service.setAdminServiceDistinct(integer);
            sysAdminServiceMapper.insert(service);
        }
        SysSeARelationship ship = new SysSeARelationship();
        ship.setaNum(bo.getaNum());
        ship.setSeNum(bo.getAdminNum());
        sysSeARelationshipMapper.insertSelective(ship);
       /* SysUserRole record =new SysUserRole();
        record.setUserNum(info.getAdminNum());
        record.setRoleId(Constants.UserRole.SE.v());
        record.setIsAuthentication(0);
        sysUserRoleMapper.insertSelective(record);*/
        return insertOrUpdateAdminInfo(info);
    }
    
    /**
     * 修改SE信息
     * @Title: perfectSeInfo
     * @author: duan.zhao.qian
     * @date: 2015年10月9日 下午9:25:11       
     * @version: 
     *
     * @param bo
     * @return
     *
     */
    public int updateSeInfo(SysAdminInfoBO bo) {
        SysAdmin admin=sysAdminMapper.selectByPrimaryKey(bo.getAdminNum());
        if(admin==null||!com.ync365.seed.utils.StringUtils.isNoneEmpty(bo.getAdminNum())){
            return 0;
        }
    	SysAdminInfo info = new SysAdminInfo();
    	SysAdmin sysAdmin = new SysAdmin();
    	BeanUtils.copyProperties(bo, info);
    	BeanUtils.copyProperties(bo, sysAdmin);
    	info.setCreateTime(null);
    	List<Integer> listServices = bo.getServiceserviceDistincts();
    	if (listServices != null && listServices.size() > 0) {
    		sysAdminServiceMapper.deleteByAdminNum(bo.getAdminNum());
	    	for (Integer integer : listServices) {
	    		SysAdminService service = new SysAdminService();
	    		service.setAdminNum(bo.getAdminNum());
	    		service.setAdminServiceDistinct(integer);
	    		sysAdminServiceMapper.insert(service);
	    	}
    	}
    	sysAdminMapper.updateByPrimaryKeySelective(sysAdmin);
    	return insertOrUpdateAdminInfo(info);
    }

    /**
     * 编号或手机号获取A+
     * @Title: selectAPlusByNumOrMobile
     * @author: duan.zhao.qian
     * @date: 2015年10月12日 下午5:07:29       
     * @version: 
     *
     * @param userNumOrMobile
     * @return
     *
     */
    public SysAdminInfoBO selectAPlusByNumOrMobile(String userNumOrMobile) {

        AdminInfoSearchBO bo=new AdminInfoSearchBO();
        bo.setMobile(userNumOrMobile);
         List<SysAdminInfoBO> list=selectSysAInfoByParam(bo);
         if(list==null||list.size()<1){
             bo.setMobile(null);
             bo.setAdminNum(userNumOrMobile);
             list=selectSysAInfoByParam(bo);
         }
         return (list != null && list.size() > 0) ? list.get(0) : new SysAdminInfoBO();
    }

    /**
     * 查询SE列表
     * @author xieang
     * 2015年10月12日
     * @param adminInfoSearchBO
     * @return
     */
    public List<SysAdminInfoBO> selectSysSeInfoByParam(AdminInfoSearchBO adminInfoSearchBO) {
        adminInfoSearchBO.setRoleId(Constants.UserRole.SE.v());//查询se权限的数据
        return sysAdminInfoMapper.selectSysSeInfoByParam(adminInfoSearchBO);
    }

    /**
     * 查询SE列表统计
     * @author xieang
     * 2015年10月12日
     * @param adminInfoSearchBO
     * @return
     */
    public int selectSysSeInfoByParamCount(AdminInfoSearchBO adminInfoSearchBO) {
        adminInfoSearchBO.setRoleId(Constants.UserRole.SE.v());//查询se权限的数据
        return sysAdminInfoMapper.selectSysSeInfoByParamCount(adminInfoSearchBO);
    }

    /**
     * 查询A+列表
     * @author xieang
     * 2015年10月12日
     * @param adminInfoSearchBO
     * @return
     */
    public List<SysAdminInfoBO> selectSysAInfoByParam(AdminInfoSearchBO adminInfoSearchBO) {
        adminInfoSearchBO.setRoleId(Constants.UserRole.APlus.v());//查询A+权限的数据
        return sysAdminInfoMapper.selectSysAInfoByParam(adminInfoSearchBO);
    }

    /**
     * 查询A+列表统计
     * @author xieang
     * 2015年10月12日
     * @param adminInfoSearchBO
     * @return
     */
    public int selectSysAInfoByParamCount(AdminInfoSearchBO adminInfoSearchBO) {
        adminInfoSearchBO.setRoleId(Constants.UserRole.APlus.v());//查询A+权限的数据
        return sysAdminInfoMapper.selectSysAInfoByParamCount(adminInfoSearchBO);
    }

    /**
     * adminNum查询
     * @author xieang
     * 2015年10月12日
     * @param adminInfoSearchBO
     * @return
     */
    public SysAdminInfoBO selectSysSeInfoByNum(String adminNum) {
        return sysAdminInfoMapper.selectSysSeInfoByNum(adminNum, Constants.UserRole.SE.v());
    }

    /**
     * adminNum查询A+
     * @author xieang
     * 2015年10月12日
     * @param adminInfoSearchBO
     * @return
     */
    public SysAdminInfoBO selectSysAInfoByNum(String adminNum) {
        return sysAdminInfoMapper.selectSysAInfoByNum(adminNum, Constants.UserRole.APlus.v());
    }

    /**后台添加A+
     * @author xieang
     * 2015年10月13日
     * @param sysAdminInfoBO
     * @return
     */
    @Transactional
    public int addAUser(SysAdminInfoBO sysAdminInfoBO) {
       //生成编号
        String admin_num = NumGenerator.getUserNum();
        SysAdmin admin = new SysAdmin();
        admin.setIsAuthentication(false);
        admin.setIsDel(false);
        admin.setIsFrozen(false);
        admin.setState(0);
        admin.setAdminNum(admin_num);
        admin.setAdminLoginName(sysAdminInfoBO.getAdminLoginName());
        admin.setAdminPassword(MD5Utils.getMD5Str("123456"));
        admin.setCreateTime(Calendar.getInstance().getTime());
        admin.setUpdateTime(Calendar.getInstance().getTime());
        admin.setAdminMobile(sysAdminInfoBO.getMobile());
        int a = sysAdminMapper.insertSelective(admin);
        
        //添加信息
        SysAdminInfo sysAdminInfo = new SysAdminInfo();
        sysAdminInfo.setAddress(sysAdminInfoBO.getAddress());
        sysAdminInfo.setAddressDetail(sysAdminInfoBO.getAddressDetail());
        sysAdminInfo.setCreateTime(Calendar.getInstance().getTime());
        sysAdminInfo.setIdCard(sysAdminInfoBO.getIdCard());
        sysAdminInfo.setName(sysAdminInfoBO.getaName());
        sysAdminInfo.setTelephone(sysAdminInfoBO.getTelephone());
        sysAdminInfo.setAdminNum(admin.getAdminNum());
        sysAdminInfoMapper.insertSelective(sysAdminInfo);
        
        
        //创建SysAdminService 封装数据 添加编号及服务区域
        SysAdminService adminService = new SysAdminService();
        List<Integer> serviceList = sysAdminInfoBO.getServiceserviceDistincts();
        if (serviceList != null && serviceList.size() > 0) {
            for (Integer integer : serviceList) {
                if(null != integer){
                    adminService.setAdminNum(admin_num);
                    adminService.setAdminServiceDistinct(integer);
                    sysAdminServiceMapper.insertSelective(adminService);
                }
            }
        }
        
        //添加角色
        SysUserRole record =new SysUserRole();
        record.setUserNum(admin.getAdminNum());
        record.setRoleId(Constants.UserRole.APlus.v());
        record.setIsAuthentication(0);
        sysUserRoleMapper.insertSelective(record);
        return  a ;
    }
    
    /**转移se下的lc
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
    public int seChange(String stateC,LcInfoSearchBO lcInfoSearchBO,List<String> userNumList,String oldNum,String newNum){
    	int i=0;
		if("all".equals(stateC)||"search".equals(stateC)){
			if("all".equals(stateC)){
				lcInfoSearchBO = new LcInfoSearchBO();
			}
			lcInfoSearchBO.setSeAdminNum(oldNum);
			List<LcInfoBO> userList = sysLcInfoMapper.selectSysLcInfoListByPrimary(lcInfoSearchBO);
			for(LcInfoBO lcInfoBO:userList){
				SysLcSeRelationship sysVsLcRelationship = new SysLcSeRelationship();
				sysVsLcRelationship.setSeNum(newNum);
				sysVsLcRelationship.setLcNum(lcInfoBO.getUserNum());
				sysLcSeRelationshipMapper.update(sysVsLcRelationship);
				i++;
			}
		}else if("data".equals(stateC)){
			for(String userNum:userNumList){
				SysLcSeRelationship sysVsLcRelationship = new SysLcSeRelationship();
				sysVsLcRelationship.setSeNum(newNum);
				sysVsLcRelationship.setLcNum(userNum);
				sysLcSeRelationshipMapper.update(sysVsLcRelationship);
				i++;
			}
		}
		
    	return i;
    }

    /**转移a+下的se
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
    public int aChange(String stateC,AdminInfoSearchBO vsInfoSearchBO,List<String> userNumList,String oldNum,String newNum){
    	int i=0;
		if("all".equals(stateC)||"search".equals(stateC)){
			if("all".equals(stateC)){
				vsInfoSearchBO = new AdminInfoSearchBO();
			}
			vsInfoSearchBO.setaAiAdminNum(oldNum);
			vsInfoSearchBO.setRoleId(Constants.UserRole.SE.v());
			List<SysAdminInfoBO> userList = sysAdminInfoMapper.selectSysSeInfoByParam(vsInfoSearchBO);
			for(SysAdminInfoBO sysAdminInfoBO:userList){
				SysSeARelationship sysSeARelationship = new SysSeARelationship();
				sysSeARelationship.setaNum(newNum);
				sysSeARelationship.setSeNum(sysAdminInfoBO.getAdminNum());
				sysSeARelationshipMapper.update(sysSeARelationship);
				i++;
			}
		}else if("data".equals(stateC)&&userNumList!=null){
			for(String userNum:userNumList){
				SysSeARelationship sysSeARelationship = new SysSeARelationship();
				sysSeARelationship.setaNum(newNum);
				sysSeARelationship.setSeNum(userNum);
				sysSeARelationshipMapper.update(sysSeARelationship);
				i++;
			}
		}
		
    	return i;
    }
    
    /**SE绑定A+
     * @author xieang
     * 2015年10月11日
     * @param sysLcSeRelationship
     * @return
     */
    @Transactional
    public int bindingA(SysSeARelationship sysSeARelationship) {
        sysSeARelationshipMapper.deleteByPrimaryKeyBySeNum(sysSeARelationship.getSeNum());
        return sysSeARelationshipMapper.insertSelective(sysSeARelationship);
    }
    
    /**获取详细信息，信息处理出来数据
     * @author xieang
     * 2015年10月15日
     * @param userNum
     * @return
     */
    public SysAdminInfoBO getSeUserInfoAllData(String adminNum) {
    	SysAdminInfoBO sysAdminInfoBO = sysAdminInfoMapper.selectSysSeInfoByNum(adminNum, Constants.UserRole.SE.v());
    	if(sysAdminInfoBO!=null&&StringUtils.isNotBlank(sysAdminInfoBO.getAdminNum())){
	        List<SysAdminService> sysAdminServiceList = sysAdminServiceMapper.selectServiceListByNum(sysAdminInfoBO.getAdminNum());
	        List<Integer> list = new ArrayList<Integer>();
	        for (SysAdminService sysAdminService : sysAdminServiceList) {
	            list.add(sysAdminService.getAdminServiceDistinct());
	        }
	        sysAdminInfoBO.setServiceserviceDistincts(list);
    	}
        return sysAdminInfoBO;
    }
    
    /**获取详细信息，信息处理出来数据
     * @author xieang
     * 2015年10月15日
     * @param userNum
     * @return
     */
    public SysAdminInfoBO getAUserInfoAllData(String adminNum) {
    	SysAdminInfoBO sysAdminInfoBO = sysAdminInfoMapper.selectSysAInfoByNum(adminNum, Constants.UserRole.APlus.v());
    	if(sysAdminInfoBO!=null&&StringUtils.isNotBlank(sysAdminInfoBO.getAdminNum())){
	    	List<SysAdminService> sysAdminServiceList = sysAdminServiceMapper.selectServiceListByNum(sysAdminInfoBO.getAdminNum());
	    	List<Integer> list = new ArrayList<Integer>();
	    	for (SysAdminService sysAdminService : sysAdminServiceList) {
	    		list.add(sysAdminService.getAdminServiceDistinct());
	    	}
	    	sysAdminInfoBO.setServiceserviceDistincts(list);
    	}
    	return sysAdminInfoBO;
    }
    
    /**
     * adminNum获取 adminInfo
     */
    public SysAdminInfo selectByPrimaryKey(String adminNum){
    	return sysAdminInfoMapper.selectByPrimaryKey(adminNum);
    }

    /**
     * a+编辑保存方法
     * @param sysAdminInfoBO
     * @return
     */
    @Transactional
    public int updateByPrimary(SysAdminInfoBO sysAdminInfoBO) {
        
        SysAdmin admin = new SysAdmin();
        admin.setAdminNum(sysAdminInfoBO.getAdminNum());
        admin.setAdminMobile(sysAdminInfoBO.getMobile());
        int a = sysAdminMapper.updateByPrimaryKeySelective(admin);
        
        //添加信息
        SysAdminInfo sysAdminInfo = new SysAdminInfo();
        sysAdminInfo.setAddress(sysAdminInfoBO.getAddress());
        sysAdminInfo.setAddressDetail(sysAdminInfoBO.getAddressDetail());
        sysAdminInfo.setIdCard(sysAdminInfoBO.getIdCard());
        sysAdminInfo.setName(sysAdminInfoBO.getName());
        sysAdminInfo.setAdminNum(admin.getAdminNum());
        sysAdminInfo.setTelephone(sysAdminInfoBO.getTelephone());
        sysAdminInfoMapper.updateByPrimaryKeySelective(sysAdminInfo);
        
        //删除服务相关区域
        int b = sysAdminServiceMapper.deleteByAdminNum(sysAdminInfoBO.getAdminNum());
        //创建SysAdminService 封装数据 添加编号及服务区域
        SysAdminService adminService = new SysAdminService();
        List<Integer> serviceList = sysAdminInfoBO.getServiceserviceDistincts();
        if (serviceList != null && serviceList.size() > 0) {
            for (Integer integer : serviceList) {
                if(null != integer){
                    adminService.setAdminNum(sysAdminInfoBO.getAdminNum());
                    adminService.setAdminServiceDistinct(integer);
                    sysAdminServiceMapper.insertSelective(adminService);
                }
            }
        }
        return  a ;
    }
}