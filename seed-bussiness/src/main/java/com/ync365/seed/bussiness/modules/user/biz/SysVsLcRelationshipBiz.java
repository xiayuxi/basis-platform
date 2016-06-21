package com.ync365.seed.bussiness.modules.user.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.user.dao.SysUsVsRelationshipMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysUserInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysVsInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysVsLcRelationshipMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysVsLcRelationship;




@Service
public class SysVsLcRelationshipBiz {
	@Autowired
	SysVsLcRelationshipMapper  sysVsLcRelationshipMapper ;

	@Autowired
	SysUsVsRelationshipMapper sysUsVsRelationshipMapper;
	
	@Autowired
	SysVsInfoMapper sysVsInfoMapper ;
	
	@Autowired
	SysUserInfoMapper sysUserInfoMapper;
	
	/**
     * 功能描述：添加用户
     * @param record
     * @return
     */
    public int insertSelective(SysVsLcRelationship record) {
    	return sysVsLcRelationshipMapper.insertSelective(record);
    }
    
    /**
     * 功能描述：删除 信息 by lcNum
     * @param 
     * @return
     */
    public int deleteSysVsLcByLcNum(String lcNum) {
    	return sysVsLcRelationshipMapper.deleteSysVsLcByLcNum(lcNum);
    }
    
    /**
     * 功能描述：删除 信息 by vsNum
     * @param 
     * @return
     */
    public int deleteSysVsLcByVsNum(String vsNum) {
    	return sysVsLcRelationshipMapper.deleteSysVsLcByVsNum(vsNum);
    }
    
    /**
     * 功能描述：查询信息by lcNum 查询存在多少us
     * @param 
     * @return
     */
    public int selectPageCount(Map<String, Object> map) {
    	return sysVsLcRelationshipMapper.selectPageCount(map);
    }
    
    /**
	 * 获取所有 list  通过map对象 传递参数
	 * @return
	 */
	public List<SysVsLcRelationship> selectPageByMap(Map<String, Object> map) {
		return sysVsLcRelationshipMapper.selectPageByMap(map);
	}
	
	/**
	 * update 
	 * @param record by lcNUnm
	 * @return
	 */
	public int update(SysVsLcRelationship record){
		return sysVsLcRelationshipMapper.update(record);
	}

	/**
	 * 通过LcNum查询sysVsLcRelationship
	 * @param lcNum
	 * @return
	 */
	private List<SysVsLcRelationship> selectSysVsLcRelationshipList(String lcNum) {
		//封装map对象  通过 lcNum  到sysVsLcRelationship 查询list 
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("lcNum",lcNum);
		List<SysVsLcRelationship> list = sysVsLcRelationshipMapper.selectPageByMap(map);
		return list;
	}

}
