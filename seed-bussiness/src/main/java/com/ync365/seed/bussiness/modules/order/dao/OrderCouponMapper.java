package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderCoupon;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface OrderCouponMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderCoupon record);

    int insertSelective(OrderCoupon record);

    OrderCoupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderCoupon record);

    int updateByPrimaryKey(OrderCoupon record);
}