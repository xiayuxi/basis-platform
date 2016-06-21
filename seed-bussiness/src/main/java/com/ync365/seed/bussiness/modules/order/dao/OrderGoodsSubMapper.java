package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsSub;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface OrderGoodsSubMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderGoodsSub record);

    int insertSelective(OrderGoodsSub record);

    OrderGoodsSub selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderGoodsSub record);

    int updateByPrimaryKey(OrderGoodsSub record);
}