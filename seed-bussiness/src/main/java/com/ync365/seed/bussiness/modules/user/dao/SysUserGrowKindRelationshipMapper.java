package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysUserGrowKindRelationship;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysUserGrowKindRelationshipMapper {

	/**
	 * 添加信息
	 * 
	 * @param record
	 * @return
	 */
    int insertSelective(SysUserGrowKindRelationship record);
    
    /**
   	 * 物理  通过 userNum删除数据
   	 * 
   	 * @param userNum
   	 * @return
   	 */
   	int deleteByUserNumm(String userNum);
   	
   	/**
	 * userNum为条件更新
	 * 
	 * @param record
	 * @return
	 */
	int update(SysUserGrowKindRelationship record);
	
	/**
	 * 查询
	 * 
	 * @param map
	 * @return
	 */
	List<SysUserGrowKindRelationship> selectPageByMap(Map<String, Object> map);
	
	/**根据用户编号查询下面所有内容
	 * @author xieang
	 * 2015年10月15日
	 * @param userNum
	 * @return
	 */
	List<SysUserGrowKindRelationship> selectDataByUserNum(String userNum);
	/**根据用户编号查询下面名称
	 * @author xieang
	 * 2015年10月15日
	 * @param userNum
	 * @return
	 */
	List<String> selectGrowKindByUserNum(String userNum);
}