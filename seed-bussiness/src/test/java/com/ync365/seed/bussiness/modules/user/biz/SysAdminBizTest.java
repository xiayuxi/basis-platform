package com.ync365.seed.bussiness.modules.user.biz;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ync365.seed.bussiness.modules.user.entity.SysAdmin;
import com.ync365.seed.bussiness.modules.user.util.NumGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-bussiness.xml")
public class SysAdminBizTest {
	@Autowired
	private SysAdminBiz biz;

	@Test
	public void testInsert() {
		SysAdmin record = new SysAdmin();
		record.setAdminLoginName("测试名1");
		record.setAdminMobile("11111112");
		record.setAdminNum(NumGenerator.getUserNum());
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		record.setLastOptName("xxxx");
		record.setAdminPassword("********");
		record.setState(0);
		record.setIsDel(true);
		record.setIsFrozen(false);
		record.setIsAuthentication(false);
		int amount = biz.insert(record);
		Assert.assertEquals(1, amount);
	}

	@Test
	public void testSelectByNum() {
		String adminNum = "9513456e93c147bd855f60d7cc17401b";
		// SysAdmin admin = biz.selectByNum(adminNum);
		// Assert.assertNotNull(admin);
		//SysAdmin admin = biz.selectByAdminLoginName("测试名");
		//System.out.println(admin.getAdminLoginName());
	}
}
