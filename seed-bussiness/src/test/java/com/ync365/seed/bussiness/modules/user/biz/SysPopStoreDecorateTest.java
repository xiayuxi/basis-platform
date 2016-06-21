package com.ync365.seed.bussiness.modules.user.biz;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ync365.seed.bussiness.modules.user.entity.SysPopStoreDecorateInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-bussiness.xml")
public class SysPopStoreDecorateTest {
	
	@Autowired
	SysPopStoreDecorateInfoBiz biz;
	
	@Test
	public void selectByPopStoreNum() {
		SysPopStoreDecorateInfo info = biz.selectByPopStoreNum("1");
		assertNotNull(info);
		System.err.println(info.getPopStoreNum());
	}
	
}
