package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysUsVsRelationship;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysUsVsRelationshipMapper {
	
    
    /*int insert(SysUsVsRelationship record);*/

    /**
     * 添加信息
     * 
     * @param record
     * @return
     */
    int insertSelective(SysUsVsRelationship record);
    
    /**
   	 * 查询
   	 * 
   	 * @param map
   	 * @return
   	 */
   	List<SysUsVsRelationship> selectPageByMap(Map<String, Object> map);
   	
   	/**
   	* 物理  通过 vsNum删除数据
   	* 
   	* @param vsNum
   	* @return
   	*/
   	int deleteSysUsVsByVsNum(String vsNum);
   	
   	/**
   	* 通过 usNum查询数据
   	* 
   	* @param vsNum
   	* @return
   	*/
   	SysUsVsRelationship selectSysUsVsByUsNum(String usNum);
   	
   	/**
   	* 物理  通过 usNum删除数据
   	* 
   	* @param usNum
   	* @return
   	*/
   	int deleteSysUsVsByUsNum(String usNum);
   	
   	/**
   	 * usNum为条件更新
   	 * 
   	 * @param record
   	 * @return
   	 */
   	int update(SysUsVsRelationship record);
   	
	/**
	 * 条件获取数量
	 * 
	 * @param map
	 * @return
	 */
	int selectPageCount(Map<String, Object> map);
}