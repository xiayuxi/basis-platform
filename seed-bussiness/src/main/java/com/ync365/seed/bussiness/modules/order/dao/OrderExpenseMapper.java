package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderExpense;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface OrderExpenseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderExpense record);

    int insertSelective(OrderExpense record);

    OrderExpense selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderExpense record);

    int updateByPrimaryKey(OrderExpense record);
}