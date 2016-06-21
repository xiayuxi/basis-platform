package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysUserWorkRelationship;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysUserWorkRelationshipMapper {
	
   /* int insert(SysUserWorkRelationship record);*/

    /**
     * 添加信息
     * 
     * @param record
     * @return
     */
    int insertSelective(SysUserWorkRelationship record);
    
    /**
	 * 查询
	 * 
	 * @param map
	 * @return
	 */
	List<SysUserWorkRelationship> selectPageByMap(Map<String, Object> map);
	
	/**
	* 物理  通过 userNum删除数据
	* 
	* @param userNum
	* @return
	*/
	int deleteSysUserWorkByUserNum(String userNum);
	
	/**
	 * userNum为条件更新
	 * 
	 * @param record
	 * @return
	 */
	int update(SysUserWorkRelationship record);
	
	/**根据userNum查询work
	 * @author xieang
	 * 2015年10月15日
	 * @param userNum
	 * @return
	 */
	List<String> selectWorkByUserNum(String userNum);
}