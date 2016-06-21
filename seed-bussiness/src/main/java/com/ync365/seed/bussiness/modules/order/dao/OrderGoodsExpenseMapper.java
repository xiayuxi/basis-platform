package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsExpense;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface OrderGoodsExpenseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderGoodsExpense record);

    int insertSelective(OrderGoodsExpense record);

    OrderGoodsExpense selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderGoodsExpense record);

    int updateByPrimaryKey(OrderGoodsExpense record);
}