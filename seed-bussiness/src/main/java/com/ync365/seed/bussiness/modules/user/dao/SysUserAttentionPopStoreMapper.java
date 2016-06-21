package com.ync365.seed.bussiness.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysUserAttentionPopStore;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SysUserAttentionPopStoreMapper {

	/**
	 * 添加信息
	 * 
	 * @param record
	 * @return
	 */
    int insertSelective(SysUserAttentionPopStore record);
    
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
	int update(SysUserAttentionPopStore record);
	
	/**
	 * 查询
	 * 
	 * @param map
	 * @return
	 */
	List<SysUserAttentionPopStore> selectPageByMap(Map<String, Object> map);
	
	/**多条件删除
	 * @author xieang
	 * 2015年9月28日
	 * @param sysUserAttentionGoods
	 * @return
	 */
	int deleteByBean(SysUserAttentionPopStore sysUserAttentionPopStore);
}