package com.ync365.seed.bussiness.modules.promotion.dao;

import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGoodsSku;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface PromotionGoodsSkuMapper {
    int deleteByPrimaryKey(Integer promotionGoodsSkuId);

    int insert(PromotionGoodsSku record);

    int insertSelective(PromotionGoodsSku record);

    PromotionGoodsSku selectByPrimaryKey(Integer promotionGoodsSkuId);

    int updateByPrimaryKeySelective(PromotionGoodsSku record);

    int updateByPrimaryKey(PromotionGoodsSku record);
}