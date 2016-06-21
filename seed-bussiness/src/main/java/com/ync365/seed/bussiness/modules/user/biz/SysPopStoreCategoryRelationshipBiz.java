package com.ync365.seed.bussiness.modules.user.biz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.user.dao.SysPopStoreCategoryRelationshipMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStoreCategoryRelationship;



@Service
public class SysPopStoreCategoryRelationshipBiz {
	@Autowired
	SysPopStoreCategoryRelationshipMapper sysPopStoreCategoryRelationshipMapper;
	
	/**
	 * 添加对象 insert
	 * 
	 * @param record
	 * @return
	 */
	public int insert(SysPopStoreCategoryRelationship record){
		return sysPopStoreCategoryRelationshipMapper.insertSelective(record);
	}
	
	/**
	 * 通过popNum参数删除对象
	 * 
	 * @param popNum
	 * @return
	 */
	public int deleteByPrimaryKey(String popNum){
		return  sysPopStoreCategoryRelationshipMapper.deleteByPrimaryKey(popNum);
	}
	
	/**
	 * 多功能查询 所有List<SysPopCategoryRelationship>
	 * 
	 * @param popNum
	 * @return
	 */
	public List<SysPopStoreCategoryRelationship> selectPageByMap(Map<String, Object> map){
		return sysPopStoreCategoryRelationshipMapper.selectPageByMap(map);
	}
	
	/**
	 * 条件获取数量
	 * 
	 * @param map
	 * @return
	 */
	public int selectPageCount(Map<String, Object> map){
		return sysPopStoreCategoryRelationshipMapper.selectPageCount(map);
	}
	
	/**
	 * 功能描述：根据 popStoreNum 和 categoryId 删除关系
	 * @author liukai
	 * @param popStoreNum
	 * @param categoryId
	 * @return
	 */
	public int deleteByPopstoreNumAndCategoryId(String popStoreNum, String categoryId) {
		return sysPopStoreCategoryRelationshipMapper.deleteByPopstoreNumAndCategoryId(popStoreNum, categoryId);
	}
}
