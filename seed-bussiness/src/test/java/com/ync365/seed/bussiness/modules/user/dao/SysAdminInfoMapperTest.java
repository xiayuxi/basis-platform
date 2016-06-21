package com.ync365.seed.bussiness.modules.user.dao;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.noggit.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.alibaba.druid.support.json.JSONParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ync365.seed.bussiness.modules.user.entity.SysAdminInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-bussiness.xml")
@FixMethodOrder(MethodSorters.JVM)
public class SysAdminInfoMapperTest {
	
	@Autowired
	SysAdminInfoMapper sysAdminInfoMapper;
	
	@Test
	public void test(){
		testInsert();
		testSearch();
		testUpdate();
		testSearch();
		testDelete();
	}
	
	public void testInsert() {
		SysAdminInfo sysAdminInfo = new SysAdminInfo();
		sysAdminInfo.setAdminNum("123321");
		sysAdminInfo.setAddress(123);
		sysAdminInfo.setAddressDetail("123");
		sysAdminInfo.setCreateTime(new Date());
		sysAdminInfo.setIdCard("123");
		sysAdminInfo.setTelephone("123");
		sysAdminInfo.setName("123");
		int i = sysAdminInfoMapper.insertSelective(sysAdminInfo);
		Assert.isTrue(i==1);
	}
	public void testUpdate() {
		SysAdminInfo sysAdminInfo = new SysAdminInfo();
		sysAdminInfo.setAdminNum("123321");
		sysAdminInfo.setAddress(123);
		sysAdminInfo.setAddressDetail("123");
		sysAdminInfo.setCreateTime(new Date());
		sysAdminInfo.setIdCard("123");
		sysAdminInfo.setTelephone("123");
		sysAdminInfo.setName("123");
		int i = sysAdminInfoMapper.updateByPrimaryKeySelective(sysAdminInfo);
		Assert.isTrue(i==1);
	}
	public void testSearch() {
		Map<String,String> map = new HashMap<String, String>(); 
		map.put("adminNum", "123321");
		List<SysAdminInfo> sysAdminInfo = sysAdminInfoMapper.selectPageByMap(map);
		System.out.println(JSONUtil.toJSON(sysAdminInfo));
		Assert.isTrue(sysAdminInfo.size()==1);
	}
	public void testDelete() {
		int i = sysAdminInfoMapper.deleteByPrimaryKey("123321");
		Assert.isTrue(i==1);
	}

}
