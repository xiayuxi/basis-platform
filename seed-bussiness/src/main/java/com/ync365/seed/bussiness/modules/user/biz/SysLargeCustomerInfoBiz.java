package com.ync365.seed.bussiness.modules.user.biz;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.bo.LargeCustomerInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.LargeCustomerInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.bo.LargeCustomerValueInfoBO;
import com.ync365.seed.bussiness.modules.user.dao.SysAuthenInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysLargeCustomerInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysLargeCustomerPropertyInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysLargeCustomerValueInfoMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysAuthenInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysLargeCustomerInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysLargeCustomerPropertyInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysLargeCustomerValueInfo;
import com.ync365.seed.utils.Constants;
import com.ync365.seed.utils.StringUtils;

@Service
public class SysLargeCustomerInfoBiz {

    @Autowired
    SysLargeCustomerInfoMapper sysLargeCustomerInfoMapper;
    @Autowired
    SysLargeCustomerPropertyInfoMapper sysLargeCustomerPropertyInfoMapper;
    @Autowired
    SysLargeCustomerValueInfoMapper sysLargeCustomerValueInfoMapper;
    @Autowired
    SysAuthenInfoMapper sysAuthenInfoMapper;
    
    /**新增或修改大客户认证
     * @author xieang
     * 2015年11月5日
     * @param bo
     * @return
     */
    @Transactional
    public Integer addOrUpdateLargeCustomerInfo(LargeCustomerInfoBO bo){
    	if(StringUtils.isNotBlank(bo.getUserNum())){
    		SysLargeCustomerInfo largeCustomerInfo = sysLargeCustomerInfoMapper.selectByPrimaryKey(bo.getUserNum());
    		if(largeCustomerInfo!=null&&StringUtils.isNotBlank(largeCustomerInfo.getUserNum())){
    			return updateLargeCustomerInfo(bo);
    		}else{
    		    return addLargeCustomerInfo(bo);
    		}
    	}else{
    	    return 0;
    	}
    	
    }

    /**
     * 添加认证信息
     * @Title: addLargeCustomerInfo
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月10日 下午4:26:52       
     * @version: 
     *
     * @param bo
     * @return
     *
     */
    @Transactional
    public Integer addLargeCustomerInfo(LargeCustomerInfoBO bo) {
        SysLargeCustomerInfo info = new SysLargeCustomerInfo();
        info.setCreateTime(Calendar.getInstance().getTime());
        info.setState(0);
        info.setUserNum(bo.getUserNum());
        Map<SysLargeCustomerPropertyInfo, SysLargeCustomerValueInfo> map = bo.getSysLargeCustomerValueInfo();
        for (java.util.Map.Entry<SysLargeCustomerPropertyInfo, SysLargeCustomerValueInfo> entry : map.entrySet()) {
            SysLargeCustomerValueInfo valueinfo = entry.getValue();
            valueinfo.setUserNum(bo.getUserNum());
            sysLargeCustomerValueInfoMapper.insertSelective(valueinfo);
        }
        return sysLargeCustomerInfoMapper.insertSelective(info);
    }

    /**
     * 修改认证信息
     * @Title: updateLargeCustomerInfo
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月10日 下午4:27:06       
     * @version: 
     *
     * @param bo
     * @return
     *
     */
    @Transactional
    public Integer updateLargeCustomerInfo(LargeCustomerInfoBO bo) {
        SysLargeCustomerInfo info = new SysLargeCustomerInfo();
        info.setUserNum(bo.getUserNum());
        info.setState(0);
        Map<SysLargeCustomerPropertyInfo, SysLargeCustomerValueInfo> map = bo.getSysLargeCustomerValueInfo();
        for (java.util.Map.Entry<SysLargeCustomerPropertyInfo, SysLargeCustomerValueInfo> entry : map.entrySet()) {
            SysLargeCustomerValueInfo valueinfo = entry.getValue();
            valueinfo.setUserNum(bo.getUserNum());
            sysLargeCustomerValueInfoMapper.updateByPrimaryKeySelective(valueinfo);
        }
        return sysLargeCustomerInfoMapper.updateByPrimaryKeySelective(info);
    }

    /**
     * 获取认证信息
     * @Title: getUsLargeCustomerInfo
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月10日 下午4:31:04       
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    public LargeCustomerInfoBO getUsLargeCustomerInfo(String userNum) {
        LargeCustomerInfoBO bo = new LargeCustomerInfoBO();
        SysLargeCustomerInfo info = sysLargeCustomerInfoMapper.selectByPrimaryKey(userNum);
        if (info != null) {
            BeanUtils.copyProperties(info, bo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("userNum", userNum);
            List<SysLargeCustomerValueInfo> values = sysLargeCustomerValueInfoMapper.selectPageByMap(map);
            List<SysLargeCustomerPropertyInfo> list = sysLargeCustomerPropertyInfoMapper
                    .selectPageByMap(new HashMap<String, Object>());
            HashMap<SysLargeCustomerPropertyInfo, SysLargeCustomerValueInfo> hashMap = new HashMap<>();
            for (SysLargeCustomerPropertyInfo sysLargeCustomerPropertyInfo : list) {
                if (sysLargeCustomerPropertyInfo.getIsDel()) {
                    continue;
                }
                for (SysLargeCustomerValueInfo sysLargeCustomerValueInfo : values) {
                    if (sysLargeCustomerPropertyInfo.getId().equals(sysLargeCustomerValueInfo.getPropertyId())) {
                        hashMap.put(sysLargeCustomerPropertyInfo, sysLargeCustomerValueInfo);
                    }
                }
            }
            bo.setSysLargeCustomerValueInfo(hashMap);
        }
        return bo;
    }

    /**
     * 获取大客户列表信息
     * @Title: selectlargeCustomerInfoByPrimary
     * @Description: TODO    ：    
     * @author: leixc
     * @date: 2015 10 11      
     * @version:
     * 
     * @param largeCustomerInfoSearchBO
     * @return
     */
    public List<LargeCustomerInfoBO> selectlargeCustomerInfoByPrimary(
            LargeCustomerInfoSearchBO largeCustomerInfoSearchBO) {
        return sysLargeCustomerInfoMapper.selectlargeCustomerInfoByPrimary(largeCustomerInfoSearchBO);
    }

    /**
     * 获取大客户列表信息 count
     * @Title: selectlargeCustomerInfoByPrimaryCount
     * @Description: TODO    ：    
     * @author: leixc
     * @date: 2015 10 11      
     * @version:
     * 
     * @param largeCustomerInfoSearchBO
     * @return
     */
    public int selectlargeCustomerInfoByPrimaryCount(LargeCustomerInfoSearchBO largeCustomerInfoSearchBO) {
        return sysLargeCustomerInfoMapper.selectlargeCustomerInfoByPrimaryCount(largeCustomerInfoSearchBO);
    }

    /**
     * 审核 通过 拒绝方法
     * @param record
     * @param failInfo 
     * @param stat 
     * @param  
     * @return
     */
    @Transactional
    public int updateLargeCustomerPrimary(SysLargeCustomerInfo record, String failInfo, AdminBO bo, String stat) {
        SysAuthenInfo sysAuthenInfo = new SysAuthenInfo();
        if(null != stat && "" != stat ){
            //拒绝
            if("1".equals(stat)){
                record.setState(Constants.AuthenticationState.Failure.v());
                sysAuthenInfo.setAuthenState(Constants.AuthenticationState.Failure.v());
            }
            //通过
            if("2".equals(stat)){
                record.setState(Constants.AuthenticationState.Success.v());
                sysAuthenInfo.setAuthenState(Constants.AuthenticationState.Success.v());
            }
        //添加未审核字段
        }else{
            record.setState(Constants.AuthenticationState.Audit.v());
        }
        
        int a = sysLargeCustomerInfoMapper.updateByPrimaryKeySelective(record);
        //未审核的不添加记录
        if (null != record && null != record.getUserNum() && "" != record.getUserNum()
                && null != record.getState() && Constants.AuthenticationState.Audit.v() != record.getState()) {
            sysAuthenInfo.setSource(Constants.LargeCustomerState.Large.v());
            sysAuthenInfo.setUserNum(record.getUserNum());
            sysAuthenInfo.setAuthenReason(failInfo);
            sysAuthenInfo.setAuthenTime(Calendar.getInstance().getTime());
            sysAuthenInfo.setAuthenAdminName(bo.getAdminName());
            sysAuthenInfo.setAuthenAdminNum(bo.getAdminNum());
            sysAuthenInfoMapper.insertSelective(sysAuthenInfo);
        }
        return a;
    }

    /**
     * 查询认证信息 by userNum
     * @param userNum
     * @return
     */
    public List<LargeCustomerValueInfoBO> getLargeCustomerInfoByUserNum(String userNum) {
        List<LargeCustomerValueInfoBO> list = sysLargeCustomerValueInfoMapper.getLargeCustomerInfoByUserNum(userNum);
        String[] str = null;
        if (null != list && list.size() > 0) {
            for (LargeCustomerValueInfoBO temp : list) {
                if (null != temp.getPropertyType() && "" != temp.getPropertyType()
                        && "time".equals(temp.getPropertyType())) {
                    str = temp.getPropertyValue().split(",");
                    temp.setPropertyDateBegin(str[0]);
                    temp.setPropertyDateEnd(str[1]);
                }
            }
        }
        return list;
    }

    /**
     * by 编号查询审核原因信息
     * @param userNum
     * @return
     */
    public List<SysAuthenInfo> selectSysAuthenInfoListByPrimary(String userNum,Integer count) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userNum", userNum);
        map.put("authenState", Constants.AuthenticationState.Failure.v());
        map.put("source", Constants.LargeCustomerState.Large.v());
        if(0 != count){
            map.put("pageIndex", 0);
            map.put("pageSize", count);
             
        }
        return sysAuthenInfoMapper.selectPageByMap(map);
    }
}