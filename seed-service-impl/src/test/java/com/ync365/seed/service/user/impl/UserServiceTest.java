package com.ync365.seed.service.user.impl;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {

	@Test
	public void test() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context-dubbox.xml","spring-context-bussiness.xml","spring-context-redis.xml","spring-context.xml");
		context.start();
		System.in.read();
	}

}
