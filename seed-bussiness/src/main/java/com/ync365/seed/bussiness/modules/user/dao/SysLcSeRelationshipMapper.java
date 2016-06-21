package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysLcSeRelationship;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysLcSeRelationshipMapper {
	/**
	 * 物理删除数据
	 * 
	 * @param lcNum
	 * @return
	 */
	int deleteByLcNum(String lcNum);

	/**
	 * LcNum为条件更新
	 * 
	 * @param record
	 * @return
	 */
	int update(SysLcSeRelationship sysLcSeRelationship);

	/**
	 * 查询
	 * 
	 * @param map
	 * @return
	 */
	List<SysLcSeRelationship> selectPageByMap(Map<String, Object> map);

	/**
	 * 添加
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(SysLcSeRelationship record);
	
	/**
	 * 条件获取数量
	 * 
	 * @param map
	 * @return
	 */
	public int selectPageCount(Map<String, Object> map);
}