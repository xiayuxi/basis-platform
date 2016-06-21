package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysReceiveGoodsInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysReceiveGoodsInfoMapper {

	/**
	 * 添加信息
	 * 
	 * @param record
	 * @return
	 */
    int insertSelective(SysReceiveGoodsInfo record);
    
    /**
     * 查询 by id
     * @param id
     * @return
     */
    SysReceiveGoodsInfo selectByPrimaryKey(Integer id);
    
    /**
     * by id 更新信息
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SysReceiveGoodsInfo record);
    
   /**
    * 删除
    * @param id
    * @return
    */
    int deleteByPrimaryKey(Integer id);
    
    /**
	 * 多功能查询，
	 * 
	 * @param map
	 * @return
	 */
	List<SysReceiveGoodsInfo> selectPageByMap(Map<String, Object> map);

	/**
	 * 条件获取数量
	 * 
	 * @param map
	 * @return
	 */
	int selectPageCount(Map<String, Object> map);
}