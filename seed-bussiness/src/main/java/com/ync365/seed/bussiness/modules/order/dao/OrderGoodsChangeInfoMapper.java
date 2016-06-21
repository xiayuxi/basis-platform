package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsChangeInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface OrderGoodsChangeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderGoodsChangeInfo record);

    int insertSelective(OrderGoodsChangeInfo record);

    OrderGoodsChangeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderGoodsChangeInfo record);

    int updateByPrimaryKey(OrderGoodsChangeInfo record);
}