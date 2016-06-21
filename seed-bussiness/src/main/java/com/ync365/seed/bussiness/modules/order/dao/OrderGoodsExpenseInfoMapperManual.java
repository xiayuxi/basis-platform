package com.ync365.seed.bussiness.modules.order.dao;

import java.util.List;

import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsExpenseInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface OrderGoodsExpenseInfoMapperManual {

	List<OrderGoodsExpenseInfo> getHoldGoldByOrderGoodsId(int orderGoodsId);
    
    
}