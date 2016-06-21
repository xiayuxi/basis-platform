package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsExpenseInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface OrderGoodsExpenseInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderGoodsExpenseInfo record);

    int insertSelective(OrderGoodsExpenseInfo record);

    OrderGoodsExpenseInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderGoodsExpenseInfo record);

    int updateByPrimaryKey(OrderGoodsExpenseInfo record);
}