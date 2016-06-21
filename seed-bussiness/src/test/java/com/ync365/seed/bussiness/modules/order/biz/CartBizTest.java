package com.ync365.seed.bussiness.modules.order.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context-redis.xml","classpath:spring-context-bussiness.xml"})
public class CartBizTest {
    
    private String userNum = "8888";
    
    @Autowired
    private CartBiz cartBiz;
    
    @Test
    public void testAddCart() {
        // cartBiz.addCart(userNum, storeId, skuId, num);
        cartBiz.addCart(userNum, 1, 2, 5);
        //cartBiz.addCart(userNum, 2, 11, 3);
    }
    
    @Test
    public void testSelCartAll(){
        cartBiz.selCartAll(userNum, 0);
    }

    @Test
    public void testSelCartStore(){
        // cartBiz.selCartStore(userNum, storeId, isSelect);
        cartBiz.selCartStore(userNum, 2, 1);
    }
    
    @Test
    public void testSelCartSku(){
        // cartBiz.selCartSku(userNum, storeId, skuId, isSelect);
        cartBiz.selCartSku(userNum, 1, 2, 0);
    }
    
    @Test
    public void testNumCart(){
        // cartBiz.numCart(userNum, storeId, skuId, num);
        cartBiz.numCart(userNum, 1, 2, 111);
    }
    
    @Test
    public void testDelCart(){
        // cartBiz.delCart(userNum, storeSkus);
        Map<Integer, List<Integer>> storeSkus = new HashMap<Integer, List<Integer>>();
        List <Integer> list = new ArrayList<Integer>();
        list.add(2);
        storeSkus.put(1, list);
        cartBiz.delCart(userNum, storeSkus);
    }
}
