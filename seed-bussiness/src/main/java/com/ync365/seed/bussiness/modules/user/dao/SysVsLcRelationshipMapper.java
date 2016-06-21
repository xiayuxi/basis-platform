package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysVsLcRelationship;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysVsLcRelationshipMapper {
	
    /**
     * 添加信息
     * 
     * @param record
     * @return
     */
    int insertSelective(SysVsLcRelationship record);
    
    /**
   	 * 查询
   	 * 
   	 * @param map
   	 * @return
   	 */
   	List<SysVsLcRelationship> selectPageByMap(Map<String, Object> map);
   	
   	/**
   	* 物理  通过 vsNum删除数据
   	* 
   	* @param vsNum
   	* @return
   	*/
   	int deleteSysVsLcByVsNum(String vsNum);
   	
   	/**
   	* 物理  通过 lcNum删除数据
   	* 
   	* @param lcNum
   	* @return
   	*/
   	int deleteSysVsLcByLcNum(String lcNum);
   	
   	/**
   	 * lcNum为条件更新
   	 * 
   	 * @param record
   	 * @return
   	 */
   	int update(SysVsLcRelationship record);
   	
   	/**
	 * 条件获取数量
	 * 
	 * @param map
	 * @return
	 */
	int selectPageCount(Map<String, Object> map);
}