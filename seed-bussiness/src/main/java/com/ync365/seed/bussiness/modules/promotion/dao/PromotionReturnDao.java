package com.ync365.seed.bussiness.modules.promotion.dao;

import com.ync365.seed.bussiness.modules.promotion.entity.PromotionReturn;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface PromotionReturnDao {
    int deleteByPrimaryKey(Integer promotionId);

    int insert(PromotionReturn record);

    int insertSelective(PromotionReturn record);

    PromotionReturn selectByPrimaryKey(Integer promotionId);

    int updateByPrimaryKeySelective(PromotionReturn record);

    int updateByPrimaryKey(PromotionReturn record);

	PromotionReturn selectByPromotionId(int promotionId);

	int updatePromotionStatusToDelete(Integer promotionId);
}