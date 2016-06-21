package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderAddress;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface OrderAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderAddress record);

    int insertSelective(OrderAddress record);

    OrderAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderAddress record);

    int updateByPrimaryKey(OrderAddress record);
}