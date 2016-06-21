package com.ync365.seed.bussiness.modules.user.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ync365.seed.commons.redis.JedisTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context-bussiness.xml","classpath:spring-context-redis.xml"})
public class RedisTemplateTest {
	@Autowired
	private JedisTemplate template;

	@Test
	public void test() {
		template.set("test", "aaa");
		String aa = template.get("test");
		System.out.println("template >>>>>>"+  aa);
		Assert.assertTrue(aa.equals("aaa"));
	}

}
