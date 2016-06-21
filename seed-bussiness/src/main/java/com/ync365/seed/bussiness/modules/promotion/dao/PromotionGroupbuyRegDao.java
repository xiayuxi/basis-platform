package com.ync365.seed.bussiness.modules.promotion.dao;

import java.util.List;

import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGroupbuyReg;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface PromotionGroupbuyRegDao {
    int insert(PromotionGroupbuyReg record);

    int insertSelective(PromotionGroupbuyReg record);

	List<PromotionGroupbuyReg> selectByPromotionId(int promotionId);

	int updatePromotionStatusToDelete(Integer promotionId);
}