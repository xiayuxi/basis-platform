package com.ync365.seed.bussiness.modules.Base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
 
@ContextConfiguration(locations = {"classpath:spring-context-bussiness.xml", "classpath:spring-context-redis.xml"})
public class BaseTest {

}
