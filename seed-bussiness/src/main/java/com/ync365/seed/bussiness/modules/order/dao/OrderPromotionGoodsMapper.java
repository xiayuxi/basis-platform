package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderPromotionGoods;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface OrderPromotionGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderPromotionGoods record);

    int insertSelective(OrderPromotionGoods record);

    OrderPromotionGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderPromotionGoods record);

    int updateByPrimaryKey(OrderPromotionGoods record);
}