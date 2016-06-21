package com.ync365.seed.bussiness.modules.order.dao;

import java.util.List;

import com.ync365.seed.bussiness.modules.order.entity.OrderCoupon;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface OrderCouponMapperManual {
    List <OrderCoupon> selectOrderCouponByOrderNo(String orderNo);
}