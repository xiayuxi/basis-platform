package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderShippingInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface OrderShippingInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderShippingInfo record);

    int insertSelective(OrderShippingInfo record);

    OrderShippingInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderShippingInfo record);

    int updateByPrimaryKey(OrderShippingInfo record);
}