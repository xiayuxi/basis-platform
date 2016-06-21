package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderStore;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface OrderStoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderStore record);

    int insertSelective(OrderStore record);

    OrderStore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderStore record);

    int updateByPrimaryKey(OrderStore record);
}