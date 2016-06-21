package com.ync365.seed.bussiness.modules.promotion.biz;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context-redis.xml","classpath:spring-context-bussiness.xml"})
public class PromotionBizTest {
    
    @Autowired
    private PromotionBiz promotionBiz;
    
    /**
     * @Title: testStartPromotion
     * @Description: 启动活动
     * @author: Ken    
     * @date: 2015年9月25日 下午2:55:07       
     * @version: 
     *
     *
     */
    @Test
    public void testStartPromotion() {
        Integer promotionId = 8;
        boolean result = promotionBiz.startPromotion(promotionId);
        Assert.assertTrue(result);
    }

}
