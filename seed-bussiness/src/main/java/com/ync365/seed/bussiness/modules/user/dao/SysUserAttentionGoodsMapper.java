package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysUserAttentionGoods;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysUserAttentionGoodsMapper {

	/**
	 * 添加信息
	 * 
	 * @param record
	 * @return
	 */
    int insertSelective(SysUserAttentionGoods record);
    
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
	int update(SysUserAttentionGoods record);
	
	/**
	 * 查询
	 * 
	 * @param map
	 * @return
	 */
	List<SysUserAttentionGoods> selectPageByMap(Map<String, Object> map);
	
	/**根据条件删除收藏
	 * @author xieang
	 * 2015年9月28日
	 * @param sysUserAttentionGoods
	 * @return
	 */
	int deleteByBean(SysUserAttentionGoods sysUserAttentionGoods);
}