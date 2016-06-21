package com.ync365.seed.bussiness.modules.user.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ync365.seed.bussiness.modules.Base.BaseTest;
import com.ync365.seed.bussiness.modules.user.entity.SysUserInfo;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-bussiness.xml")
@FixMethodOrder(MethodSorters.JVM)
public class SysUserInfoTest extends BaseTest{
	
	@Autowired
	private SysUserInfoMapper infoMapper;
	private static String USER_NUM = "12aasfasdfasdf";
	
	@Test
	public void testInsertSelective() {
		SysUserInfo userInfo = new SysUserInfo();
		userInfo.setSysUserAddress(111);
		userInfo.setSysUserAddressDetail("ddd");
		userInfo.setSysUserBirthday(new Date());
		userInfo.setSysUserFarmArea(1);
		userInfo.setSysUserGender(1);
		userInfo.setSysUserIdCard("1");
		userInfo.setSysUserRealName("222");
		userInfo.setUserNum(USER_NUM);
		
		int amount = infoMapper.insertSelective(userInfo);
		System.out.println(amount);
		assertEquals(1, amount);
	}
	
	@Test
	public void testDeleteByPrimaryKey() {
		int amount = infoMapper.deleteByPrimaryKeyUserNum(USER_NUM);
		assertEquals(1, amount);
	}
	
	@Test
	public void testSelectByPrimaryKey() {
		SysUserInfo userInfo = infoMapper.selectByPrimaryKeyUserNum(USER_NUM);
		assertNotNull(userInfo);
	}
	
	@Test
	public void testUpdateByPrimaryKeySelective () {
		SysUserInfo userInfo = new SysUserInfo();
//		userInfo.setSysUserAddress(1111);
//		userInfo.setSysUserAddressDetail("dddsaf");
//		userInfo.setSysUserBirthday(new Date());
//		userInfo.setSysUserFarmArea(1);
//		userInfo.setSysUserGender(1);
//		userInfo.setSysUserIdCard("1d");
//		userInfo.setSysUserRealName("22d1232");
		userInfo.setUserNum(USER_NUM);
		
		int amount = infoMapper.updateByPrimaryKeySelective(userInfo);
		assertEquals(1, amount);
	}
	
	@Test
	public void testSelectPageByMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", "12aasfasdfasdf");
		int amount = infoMapper.selectPageCount(map);
		assertEquals(1, amount);
	}
	
	@Test
	public void testSelectPageCount() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		int amount = infoMapper.selectPageCount(map);
		assertEquals(1, amount);
	}
}
