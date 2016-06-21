package com.ync365.seed.bussiness.modules.order.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsExpenseManual;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface OrderGoodsExpenseMapperManual {
    
    List <OrderGoodsExpenseManual> selectOrderGoodsExpenseManual(Map<String,Object> param);
    
}