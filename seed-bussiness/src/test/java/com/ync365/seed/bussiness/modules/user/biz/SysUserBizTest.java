package com.ync365.seed.bussiness.modules.user.biz;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ync365.seed.bussiness.modules.user.bo.UserRegisterBO;
import com.ync365.seed.bussiness.modules.user.entity.SysUser;
import com.ync365.seed.bussiness.modules.user.util.NumGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={ "classpath:spring-context-bussiness.xml","classpath:spring-context-redis.xml"})
public class SysUserBizTest {
	@Autowired
	private SysUserBiz biz;

	@Test
	public void testInsert() {
		UserRegisterBO bo= new UserRegisterBO();
		bo.setUserMobile("123123123");
		bo.setRecommendedNum("1");
		bo.setRegisterLocation(1);
		bo.setUserName("123");
		bo.setUserPassword("123");
		int amount = biz.insertSysUser(bo);
		Assert.assertEquals(1, amount);
	}
	@Test
	public void testOne() {
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("userNum", "8e48f967239b4b8982e09934e8a20265");
		List<SysUser> list = biz.selectPageByMap(map);
		if(null != list&&  list.size()>0 ){
			for(SysUser temp : list){
				System.out.println("============"+temp.getUserLoginName()+"===========");
			}
		}else{
			System.out.println("list--------------为空");
		}
	}
	
	@Test
	public void testCount() {
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("userNum", "8e48f967239b4b8982e09934e8a20265");
		int a = biz.selectPageCount(map);
		
		System.out.println("list--------"+a);
		
	}
	
	@Test
	public void testSelectByNum() {
		SysUser t = biz.selectByPrimaryKeyUserNum("8e48f967239b4b8982e09934e8a20265");
		System.out.println("------------------"+t.getUserLoginName());
	}
	
	@Test
	public void testdel() {
		int a = biz.deleteByPrimaryKeyUserNum("8e48f967239b4b8982e09934e8a20265");
		System.out.println("==========="+a);
	}
	@Test
	public void testverificationLogin() {
	    SysUser a = biz.verificationLogin("123","123");
	    System.out.println(a.getUserNum());
	    Assert.assertTrue(a.getUserNum()!=null);
	    SysUser b = biz.verificationPasswordByMobile("123123123", "1231");
        System.out.println(b.getUserNum());
        Assert.assertTrue(b.getUserNum()!=null);
	}
}
