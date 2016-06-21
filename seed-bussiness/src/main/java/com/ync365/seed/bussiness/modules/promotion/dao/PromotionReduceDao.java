package com.ync365.seed.bussiness.modules.promotion.dao;

import com.ync365.seed.bussiness.modules.promotion.entity.PromotionReduce;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface PromotionReduceDao {
    int deleteByPrimaryKey(Integer promotionId);

    int insert(PromotionReduce record);

    int insertSelective(PromotionReduce record);

    PromotionReduce selectByPrimaryKey(Integer promotionId);

    int updateByPrimaryKeySelective(PromotionReduce record);

    int updateByPrimaryKey(PromotionReduce record);

	int updatePromotionStatusToDelete(Integer promotionId);
}