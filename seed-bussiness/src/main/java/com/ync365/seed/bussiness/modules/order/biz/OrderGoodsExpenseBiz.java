package com.ync365.seed.bussiness.modules.order.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.order.dao.OrderGoodsExpenseMapperManual;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsExpenseManual;


@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class OrderGoodsExpenseBiz {
    
    @Autowired
    private OrderGoodsExpenseMapperManual orderGoodsExpenseMapperManual;
    
    
    public List <OrderGoodsExpenseManual> selectOrderGoodsExpenseManual(Integer orderId,Integer storeId){
        Map <String,Object> param = new HashMap<String,Object>();
        param.put("orderId", orderId);
        param.put("storeId", storeId);
        return orderGoodsExpenseMapperManual.selectOrderGoodsExpenseManual(param);
    }
    
}
