package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderSubInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface OrderSubInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderSubInfo record);

    int insertSelective(OrderSubInfo record);

    OrderSubInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderSubInfo record);

    int updateByPrimaryKey(OrderSubInfo record);
}