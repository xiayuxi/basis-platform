package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderContact;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface OrderContactMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderContact record);

    int insertSelective(OrderContact record);

    OrderContact selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderContact record);

    int updateByPrimaryKey(OrderContact record);
}