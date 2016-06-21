package com.ync365.seed.bussiness.modules.promotion.dao;

import java.util.List;

import com.ync365.seed.bussiness.modules.promotion.entity.PromotionReduceReg;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface PromotionReduceRegDao {
    int insert(PromotionReduceReg record);

    int insertSelective(PromotionReduceReg record);

	int updatePromotionStatusToDelete(Integer promotionId);

	List<PromotionReduceReg> selectByPromotionId(Integer promotionId);

	void deleteById(Integer promotionId);
}