package com.ync365.seed.bussiness.modules.promotion.dao;

import com.ync365.seed.bussiness.modules.promotion.entity.PromotionSeckill;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface PromotionSeckillDao {
    int deleteByPrimaryKey(Integer promotionId);

    int insert(PromotionSeckill record);

    int insertSelective(PromotionSeckill record);

    PromotionSeckill selectByPrimaryKey(Integer promotionId);

    int updateByPrimaryKeySelective(PromotionSeckill record);

    int updateByPrimaryKey(PromotionSeckill record);

	int updatePromotionStatusToDelete(int promotionId);
}