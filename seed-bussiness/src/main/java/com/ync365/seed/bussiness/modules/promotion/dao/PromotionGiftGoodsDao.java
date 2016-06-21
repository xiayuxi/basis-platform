package com.ync365.seed.bussiness.modules.promotion.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGiftGoods;
import com.ync365.seed.commons.annotation.MyBatisRepository;

/**
 *  
 * @author codegen 2015-09-15 18:21:55 
 */
@MyBatisRepository
public interface PromotionGiftGoodsDao{

	/**
	 * 查找单个 
	 * @param id 
	 * @return PromotionGiftGoods 
	 */
	public PromotionGiftGoods find(Integer id);

	/**
	 * 查找所有 
	 * @return List<PromotionGiftGoods> 
	 */
	public List<PromotionGiftGoods> findAll();

	/**
	 * 创建 
	 * @param po 
	 */
	public void create(PromotionGiftGoods po);

	/**
	 * 修改 
	 * @param po 
	 */
	public void update(PromotionGiftGoods po);

	/**
	 * 删除 
	 * @param id 
	 */
	public void delete(Integer id);
	
	/**
	 * 查找所有 
	 * 注此方法后台用
	 */
	public List<PromotionGiftGoods> listForPage(Map<String, Object> conds);
	
	/**
	 * 组删除 
	 * @param id 
	 */
	public void deletes(Integer[] id);

}