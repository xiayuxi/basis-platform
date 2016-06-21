package com.ync365.seed.bussiness.modules.user.biz;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.user.dao.SysUsVsRelationshipMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysUserInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysVsInfoMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysUsVsRelationship;

@Service
public class SysUsVsRelationshipBiz {

	@Autowired
	SysUsVsRelationshipMapper sysUsVsRelationshipMapper;
	
	@Autowired
	SysVsInfoMapper sysVsInfoMapper ;
	
	@Autowired
	SysUserInfoMapper sysUserInfoMapper;
	
	/**
	 * 通过数量
	 * @param map
	 * @return
	 */
	public int selectPageCount(Map<String,Object> map){
		return sysUsVsRelationshipMapper.selectPageCount(map);
	}
	
	/**绑定操作
	 * @author xieang
	 * 2015年10月8日
	 * @param usNum
	 * @param vsNum
	 */
	@Transactional
	public void insertAndUpdate(String usNum,String vsNum){
		if(StringUtils.isNotBlank(usNum)&&StringUtils.isNotBlank(vsNum)){//增加绑定关系操作，未考虑到并发操作 TODO
    		SysUsVsRelationship sysUsVsRelationship = sysUsVsRelationshipMapper.selectSysUsVsByUsNum(usNum);
    		if(sysUsVsRelationship!=null&&StringUtils.isNotBlank(sysUsVsRelationship.getUsNum())){
    			sysUsVsRelationship.setUsNum(usNum);
            	sysUsVsRelationship.setVsNum(vsNum);
            	sysUsVsRelationshipMapper.update(sysUsVsRelationship);
    		}else{
	    		sysUsVsRelationship = new SysUsVsRelationship();
	        	sysUsVsRelationship.setUsNum(usNum);
	        	sysUsVsRelationship.setVsNum(vsNum);
	        	sysUsVsRelationshipMapper.insertSelective(sysUsVsRelationship);
    		}
    	}
	}
	
}
