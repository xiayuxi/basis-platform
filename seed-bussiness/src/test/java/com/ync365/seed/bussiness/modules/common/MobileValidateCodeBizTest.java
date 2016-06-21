package com.ync365.seed.bussiness.modules.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ync365.seed.bussiness.modules.common.biz.MobileValidateCodeBiz;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context-bussiness.xml", "classpath:spring-context-redis.xml" })
public class MobileValidateCodeBizTest {
    @Autowired
    MobileValidateCodeBiz biz;

    @Test
    public void testGetMobileValidateCode() {
        String code = biz.getMobileValidateCode("18638305155");
        System.out.println(code);
    }

    @Test
    public void testValidateMobileValidateCode() {
        Boolean result = biz.validateMobileValidateCode("18638305155", "865409");
        System.out.println(result);
    }

    @Test
    public void testSendValidateCode() {
        String code = biz.sendValidateCode("18638305155");
        System.out.println(code);
    }

}
