package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysDoWork;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysDoWorkMapper {
	
	/**
	 * id删除信息，物理删除
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
    int insert(SysDoWork record);

    /**
     * 添加信息，剔除空字符
     * 
     * @param record
     * @return
     */
    int insertSelective(SysDoWork record);

    /**
     * 根据id获取信息
     * 
     * @param id
     * @return
     */
    SysDoWork selectByPrimaryKey(Integer id);

    /**
     * 根据id编辑信息，剔除空字段
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SysDoWork record);

    /**
     * 根据id编辑信息
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(SysDoWork record);
    
    /**
	 * 多功能查询，分页添加pageIndex,pageSize参数
	 * 
	 * @param map
	 * @return
	 */
	List<SysDoWork> selectPageByMap(Map<String, Object> map);
	
	/**
	 * 条件获取数量
	 * 
	 * @param map
	 * @return
	 */
	int selectPageCount(Map<String, Object> map);
}