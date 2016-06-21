package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.bo.LargeCustomerInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.LargeCustomerInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.entity.SysLargeCustomerInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysLargeCustomerInfoMapper {
	
    /**
	 * 添加信息
	 * 
	 * @param record
	 * @return
	 */
    Integer insertSelective(SysLargeCustomerInfo record);
    
    /**
	 * 编号删除信息，物理删除
	 * 
	 * @param userNum
	 * @return
	 */
    Integer deleteByPrimaryKey(String userNum);
	
	/**
	 * 编号获取信息
	 * 
	 * @param userNum
	 * @return
	 */
	SysLargeCustomerInfo selectByPrimaryKey(String userNum);

	/**
	 * 多功能查询，分页添加pageIndex,pageSize参数
	 * 
	 * @param map
	 * @return
	 */
	List<SysLargeCustomerInfo> selectPageByMap(Map<String, Object> map);

	/**
	 * 条件获取数量
	 * 
	 * @param map
	 * @return
	 */
	Integer selectPageCount(Map<String, Object> map);
	
	/**
	 * 编号修改信息
	 * 
	 * @param record
	 * @return
	 */
	Integer updateByPrimaryKeySelective(SysLargeCustomerInfo record);

	/**
	 * 查询大客户列表
	 * @param largeCustomerInfoSearchBO
	 * @return
	 */
    List<LargeCustomerInfoBO> selectlargeCustomerInfoByPrimary(LargeCustomerInfoSearchBO largeCustomerInfoSearchBO);

    /**
     * 查询大客户列表 count
     * @param largeCustomerInfoSearchBO
     * @return
     */
    int selectlargeCustomerInfoByPrimaryCount(LargeCustomerInfoSearchBO largeCustomerInfoSearchBO);
}