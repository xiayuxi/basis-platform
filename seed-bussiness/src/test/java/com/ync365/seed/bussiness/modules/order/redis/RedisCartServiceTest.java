package com.ync365.seed.bussiness.modules.order.redis;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context-redis.xml","classpath:spring-context-bussiness.xml"})
public class RedisCartServiceTest {

    @Autowired
    private RedisCartService redisCartService;
    
    
    @Before
    public void before(){
        System.out.println(redisCartService);
    }
    
   /* @Test
    public void testSaveCart() {
        CartPO cart = new CartPO();
        cart.setUserNum("0001");
        Map<String,CartSkuPO> skuMap = new HashMap<String,CartSkuPO>();
        CartSkuPO sku1 = new CartSkuPO();
        sku1.setSkuId(123);
        sku1.setNum(5);
        CartSkuPO sku2 = new CartSkuPO();
        sku2.setSkuId(456);
        sku2.setNum(6);
        skuMap.put(String.valueOf(sku1.getSkuId()), sku1);
        skuMap.put(String.valueOf(sku2.getSkuId()), sku2);
        cart.setSkuMap(skuMap);
        redisCartService.saveCart(cart);
    }*/

    @Test
    public void testGetCart() {
       /* String  str = redisCartService.getCart("123");
        System.out.println("str-----" +  str);*/
       /* System.out.println("---" + cart);
        Assert.assertEquals("0001",cart.getUserNum());*/
    }

    /*@Test
    public void testExistCart() {
        Boolean result = redisCartService.existCart("0001");
        System.out.println("---" + result);
        Assert.assertEquals(true,result);
    }*/

}
