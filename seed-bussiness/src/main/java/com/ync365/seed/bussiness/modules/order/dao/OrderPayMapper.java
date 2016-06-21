package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderPay;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface OrderPayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderPay record);

    int insertSelective(OrderPay record);

    OrderPay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderPay record);

    int updateByPrimaryKey(OrderPay record);
}