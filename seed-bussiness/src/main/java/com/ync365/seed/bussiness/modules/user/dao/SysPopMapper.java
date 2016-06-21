package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysPop;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysPopMapper {
	
	/**
	 * 根据id删除信息，物理删除
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 添加信息
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(SysPop record);

	/**
	 * 根据id获取信息
	 * 
	 * @param adminNum
	 * @return
	 */
	SysPop selectByPrimaryKey(Integer id);
	/**
	 * 根据id获取信息
	 * 
	 * @param adminNum
	 * @return
	 */
	SysPop selectByPopNum(String popNum);

	/**
	 * 根据id修改信息
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(SysPop record);

	/**
	 * 多功能查询，分页添加pageIndex,pageSize参数
	 * 
	 * @param map
	 * @return
	 */
	List<SysPop> selectPageByMap(Map<String, Object> map);

	/**
	 * 条件获取数量
	 * 
	 * @param map
	 * @return
	 */
	int selectPageCount(Map<String, Object> map);
	/**
	 * 根据 popStoreNum(商铺id) 修改供应商信息[仅适用于一对一的时刻可用]
	 * @author xieang
	 * 2015年11月2日
	 * @param popStoreNum
	 * @param popLoginName
	 * @return
	 */
	int updateByPopStoreNum(SysPop record);

	/**
	 * 功能描述：根据 popStoreNum 查询 供应商
	 * @author liukai
	 * @param popStoreNum
	 * @return
	 */
	SysPop selectByPopStoreNum(String popStoreNum);

	/**
	 * 功能描述：根据sysPop查询对象
	 * @author liukai
	 * @param sysPop
	 * @return
	 */
	SysPop selectBySysPop(SysPop sysPop);

}