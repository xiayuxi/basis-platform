package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderOptLog;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface OrderOptLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderOptLog record);

    int insertSelective(OrderOptLog record);

    OrderOptLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderOptLog record);

    int updateByPrimaryKey(OrderOptLog record);
}