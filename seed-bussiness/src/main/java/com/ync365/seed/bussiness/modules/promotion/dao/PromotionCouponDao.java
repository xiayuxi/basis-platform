package com.ync365.seed.bussiness.modules.promotion.dao;

import com.ync365.seed.bussiness.modules.promotion.entity.PromotionCoupon;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface PromotionCouponDao {
    int deleteByPrimaryKey(Integer promotionId);

    int insert(PromotionCoupon record);

    int insertSelective(PromotionCoupon record);

    PromotionCoupon selectByPrimaryKey(Integer promotionId);

    int updateByPrimaryKeySelective(PromotionCoupon record);

    int updateByPrimaryKey(PromotionCoupon record);

	int updatePromotionStatusToDelete(int promotionId);
}