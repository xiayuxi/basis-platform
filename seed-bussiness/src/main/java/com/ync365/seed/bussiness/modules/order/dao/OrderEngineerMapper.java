package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderEngineer;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface OrderEngineerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderEngineer record);

    int insertSelective(OrderEngineer record);

    OrderEngineer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderEngineer record);

    int updateByPrimaryKey(OrderEngineer record);
}