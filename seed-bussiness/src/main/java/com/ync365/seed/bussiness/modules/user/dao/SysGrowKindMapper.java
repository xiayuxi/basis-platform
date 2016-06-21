package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysGrowKind;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysGrowKindMapper {
    /**
     * 根据id删除信息
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
    int insertSelective(SysGrowKind record);

    /**
     * 根据id获取信息
     * 
     * @param id
     * @return
     */
    SysGrowKind selectByPrimaryKey(Integer id);

    /**
     * 根据id修改信息
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SysGrowKind record);

    /**
	 * 多功能查询，分页添加pageIndex,pageSize参数
	 * 
	 * @param map
	 * @return
	 */
	List<SysGrowKind> selectPageByMap(Map<String, Object> map);

	/**
	 * 条件获取数量
	 * 
	 * @param map
	 * @return
	 */
	int selectPageCount(Map<String, Object> map);
}