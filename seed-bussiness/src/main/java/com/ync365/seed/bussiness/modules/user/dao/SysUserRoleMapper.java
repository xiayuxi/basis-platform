package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysUserRole;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysUserRoleMapper {
	/**
     * 添加信息
     * 
     * @param record
     * @return
     */
	int insert(SysUserRole record);
	
    /**
     * 添加信息
     * 
     * @param record
     * @return
     */
    int insertSelective(SysUserRole record);
    
    /**
	 * 查询
	 * 
	 * @param map
	 * @return
	 */
	List<SysUserRole> selectPageByMap(Map<String, Object> map);
	
	/**
	* 物理  通过 roleId删除数据
	* 
	* @param roleId
	* @return
	*/
	int deleteUserRoleByRoleId(Integer roleId);
	
	/**
	* 物理  通过 userNum 删除数据
	* 
	* @param userNum
	* @return
	*/
	int deleteUserRoleByuserNum(String userNum);
	
	/**
	 * roleId为条件更新
	 * 
	 * @param record
	 * @return
	 */
	int update(SysUserRole record);

}