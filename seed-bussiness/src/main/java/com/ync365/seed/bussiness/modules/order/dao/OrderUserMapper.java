package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderUser;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface OrderUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderUser record);

    int insertSelective(OrderUser record);

    OrderUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderUser record);

    int updateByPrimaryKey(OrderUser record);
}