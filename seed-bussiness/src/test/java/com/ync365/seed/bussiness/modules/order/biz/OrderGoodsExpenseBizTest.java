package com.ync365.seed.bussiness.modules.order.biz;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsExpenseManual;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context-redis.xml","classpath:spring-context-bussiness.xml"})
public class OrderGoodsExpenseBizTest {

    @Autowired
    private OrderGoodsExpenseBiz orderGoodsExpenseBiz;
    
    @Test
    public void testSelectOrderGoodsExpenseManualByOrderId() {
        Integer orderId = 10009;
        /*List <OrderGoodsExpenseManual> list = orderGoodsExpenseBiz.selectOrderGoodsExpenseManualByOrderId(orderId);
        System.out.println(list.size());*/
    }

}
