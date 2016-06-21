package com.ync365.seed.bussiness.modules.order.biz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ync365.seed.bussiness.modules.order.entity.OrderInfo;
import com.ync365.seed.bussiness.modules.order.entity.UserCoupon;
//import com.ync365.seed.bussiness.modules.order.entity.Order;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionGiftBiz;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context-redis.xml","classpath:spring-context-bussiness.xml","classpath:spring-context.xml"})
public class OrderBizTest {


    @Autowired
    private OrderBiz orderBiz;
    @Autowired
    private PromotionGiftBiz promotionGiftBiz;
    @Autowired
    private UserCouponBiz userCouponBiz;
    @Autowired
    private OrderNoGenBiz orderNoGenBiz;
    
    @Autowired
    private OrderInfoBiz orderInfoBiz;
    
    @Test
    public void testAddCart() {
        // cartBiz.addCart(userNum, storeId, skuId, num);
//    	List<Order> orderList = orderBiz.searchTest();
//    	String jsonStr = JSON.toJSONString(orderList);
//    	System.out.println(jsonStr);
//    	promotionGiftBiz.selectById(1);
        //cartBiz.addCart(userNum, 2, 11, 3);
    }
    @Test
    public void testAddUserCoupon() {
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setCouponId(1);
        userCoupon.setPromotionId(26);
        userCoupon.setCouponno(orderNoGenBiz.getCoupnoNo(1));
        userCoupon.setCouponAmount(new BigDecimal("5"));
        userCoupon.setUserNum("0115101700000001");
        userCoupon.setStatus(1);
        userCoupon.setIsLock(0);
        userCoupon.setIsValid(0);
        userCoupon.setCreateTime(new Date());
        userCoupon.setVersion(1);
        userCouponBiz.insertUserCoupon(userCoupon);
    }
    @Test
    public void testRegisterGrantCoupon() {
        String userNum = "1115102400000002";
        userCouponBiz.loginGrantCoupon(userNum);
    }
    @Test
    public void testSelectOrderNeedCancel() {
        List<OrderInfo> list =new ArrayList<OrderInfo>();
        list = orderInfoBiz.selectOrderNeedCancel();
    }
    
  
}
