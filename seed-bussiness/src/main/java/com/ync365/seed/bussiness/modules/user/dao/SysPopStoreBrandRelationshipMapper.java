package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ync365.seed.bussiness.modules.user.entity.SysPopStoreBrandRelationship;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysPopStoreBrandRelationshipMapper {

    /**
	 * 添加信息
	 * 
	 * @param record
	 * @return
	 */
    int insertSelective(SysPopStoreBrandRelationship record);
    
    /**
	 * 编号popStoreNum删除信息，物理删除
	 * 
	 * @param popStoreNum
	 * @return
	 */
	int deleteByPopStoreNum(String popStoreNum);
	
	/**
	 * 编号brandId删除信息，物理删除
	 * 
	 * @param brandId
	 * @return
	 */
	int deleteByBrandId(String brandId);

	/**
	 * 多功能查询，分页添加pageIndex,pageSize参数
	 * 
	 * @param map
	 * @return
	 */
	List<SysPopStoreBrandRelationship> selectPageByMap(Map<String, Object> map);

	/**
	 * 功能描述：根据 popStoreNum 和 brandId 删除
	 * @author liukai
	 * @param popStoreNum
	 * @param brandId
	 * @return
	 */
	int deleteByPopStoreNumAndBrandId(@Param("popStoreNum") String popStoreNum, @Param("brandId") String brandId);

}