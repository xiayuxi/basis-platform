package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderPromotion;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface OrderPromotionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderPromotion record);

    int insertSelective(OrderPromotion record);

    OrderPromotion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderPromotion record);

    int updateByPrimaryKey(OrderPromotion record);
}