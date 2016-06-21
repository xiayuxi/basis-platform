package com.ync365.seed.bussiness.modules.promotion.dao;

import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGroupbuy;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface PromotionGroupbuyDao {
    int deleteByPrimaryKey(Integer promotionId);

    int insert(PromotionGroupbuy record);

    int insertSelective(PromotionGroupbuy record);

    PromotionGroupbuy selectByPrimaryKey(Integer promotionId);

    int updateByPrimaryKeySelective(PromotionGroupbuy record);

    int updateByPrimaryKey(PromotionGroupbuy record);

	int updatePromotionStatusToDelete(int promotionId);
}