package com.ync365.seed.bussiness.modules.promotion.dao;

import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGift;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface PromotionGiftDao {
    int deleteByPrimaryKey(Integer promotionId);

    int insert(PromotionGift record);

    int insertSelective(PromotionGift record);

    PromotionGift selectByPrimaryKey(Integer promotionId);

    int updateByPrimaryKeySelective(PromotionGift record);

    int updateByPrimaryKey(PromotionGift record);
	/**
	 * 删除同时添加增加活动主表和满减活动表
	 */
	public void deleteById(int id);
	/**
	 * 根据活动IP查询活动信息
	 */
	public PromotionGift selectById(int id);
}