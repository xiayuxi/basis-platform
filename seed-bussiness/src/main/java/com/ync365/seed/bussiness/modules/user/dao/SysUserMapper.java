package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysUser;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysUserMapper {
	
	/**
	 * 删除信息，物理删除
	 * @param userNum
	 * @return
	 */
	int deleteByPrimaryKeyUserNum(String userNum);
	
	/**
	 * 添加信息
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(SysUser record);
	
	/**
	 * 获取编号信息
	 * 
	 * @param userNum
	 * @return
	 */
	SysUser selectByPrimaryKeyUserNum(String userNum);
	
	/**
	 * 根据登录名获取
	 * @author xieang
	 * 2015年9月25日
	 * @param userLoginName
	 * @return
	 */
	SysUser selectByPrimaryKeyUserLoginName(String userLoginName);
	
	/**
	 * 编号修改信息
	 * 
	 * @param record  通过userNum
	 * @return
	 */
	int updateByPrimaryKeySelective(SysUser record);

	
	/*int updateByPrimaryKey(SysUser record);*/
	
	/**
	 * 多功能查询，分页添加pageIndex,pageSize参数
	 * 
	 * @param map
	 * @return
	 */
	List<SysUser> selectPageByMap(Map<String, Object> map);

	/**
	 * 条件获取数量
	 * 
	 * @param map
	 * @return
	 */
	int selectPageCount(Map<String, Object> map);
}