package com.ync365.seed.bussiness.modules.promotion.biz;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ync365.seed.bussiness.modules.order.bo.CartBO;
import com.ync365.seed.bussiness.modules.order.bo.CartSkuBO;
import com.ync365.seed.bussiness.modules.order.redis.RedisCartService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context-redis.xml","classpath:spring-context-bussiness.xml"})
public class PromotionReduceBizTest {
    
    @Autowired
    RedisCartService redisCartService;

	@Test
	public void testFind() {
	    CartBO cart = new CartBO();
	    cart.setUserNum("0001");
	    List<CartSkuBO> skus = new ArrayList<CartSkuBO>();
	    CartSkuBO sku1 = new CartSkuBO();
	    sku1.setSkuId(123);
	    sku1.setNum(5);
	    CartSkuBO sku2 = new CartSkuBO();
        sku2.setSkuId(456);
        sku2.setNum(6);
        skus.add(sku1);
        skus.add(sku2);
	    // redisCartService.saveCart(cart);
	}

	@Test
	public void testFindAll() {
	    
	   /* CartPO cart = redisCartService.getCart("0001");
	    System.out.println("--------------------");
	    System.out.println(cart);*/
	}

	@Test
	public void testCreate() {
		
		
	}

	@Test
	public void testUpdate() {
	}

	@Test
	public void testDelete() {
	}

	@Test
	public void testAddPromotionReduceGoods() {
	}

}
