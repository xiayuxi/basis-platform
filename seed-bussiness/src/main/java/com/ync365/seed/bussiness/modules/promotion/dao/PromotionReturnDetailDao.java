package com.ync365.seed.bussiness.modules.promotion.dao;

import java.util.List;

import com.ync365.seed.bussiness.modules.promotion.entity.PromotionReturnDetail;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface PromotionReturnDetailDao {
	int deleteByPrimaryKey(Integer id);

    int insert(PromotionReturnDetail record);

    int insertSelective(PromotionReturnDetail record);

    PromotionReturnDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PromotionReturnDetail record);

    int updateByPrimaryKey(PromotionReturnDetail record);

	List<PromotionReturnDetail> selectByPromotionId(int promotionId);

	int updatePromotionStatusToDelete(int promotionId);
}