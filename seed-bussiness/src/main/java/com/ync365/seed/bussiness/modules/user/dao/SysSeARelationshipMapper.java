package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysSeARelationship;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysSeARelationshipMapper {

	/**
	 * 添加信息
	 * 
	 * @param record
	 * @return
	 */
    int insertSelective(SysSeARelationship record);
    /**
     * 修改信息
     * 
     * @param record
     * @return
     */
    int update(SysSeARelationship record);
    
    /**
	 * 编号 aNum 删除信息，物理删除
	 * 
	 * @param aNum
	 * @return
	 */
	int deleteByPrimaryKeyByaNum(String aNum);
	
	/**
	 * 编号 seNum 删除信息，物理删除
	 * 
	 * @param seNum
	 * @return
	 */
	int deleteByPrimaryKeyBySeNum(String seNum);
	
	/**
	 * 多功能查询
	 * 
	 * @param map
	 * @return
	 */
	List<SysSeARelationship> selectPageByMap(Map<String, Object> map);

	/**
	 * 条件获取数量
	 * 
	 * @param map
	 * @return
	 */
	int selectPageCount(Map<String, Object> map);
}