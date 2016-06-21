package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ync365.seed.bussiness.modules.user.entity.SysPopStoreCategoryRelationship;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysPopStoreCategoryRelationshipMapper {
	
	
    /**
	 * 添加信息
	 * 
	 * @param record
	 * @return
	 */
    int insertSelective(SysPopStoreCategoryRelationship record);
    
    /**
	 * 多功能查询
	 * 
	 * @param map
	 * @return
	 */
    List<SysPopStoreCategoryRelationship> selectPageByMap(Map<String, Object> map);
    
    /**
	 * 条件获取数量
	 * 
	 * @param map
	 * @return
	 */
	int selectPageCount(Map<String, Object> map);

	/**
	 * 编号删除信息，物理删除
	 * 
	 * @param popNum
	 * @return
	 */
	int deleteByPrimaryKey(String popNum);

	/**
	 * 功能描述：根据 popStoreNum 和 categoryId 删除关系
	 * @author liukai
	 * @param popStoreNum
	 * @param categoryId
	 * @return
	 */
	int deleteByPopstoreNumAndCategoryId(@Param("popStoreNum") String popStoreNum, @Param("categoryId") String categoryId);
}