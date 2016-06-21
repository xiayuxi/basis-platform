package com.ync365.seed.bussiness.modules.user.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



import com.ync365.seed.bussiness.modules.user.entity.SysVsLcRelationship;
import com.ync365.seed.bussiness.modules.user.util.NumGenerator;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-bussiness.xml")
@FixMethodOrder(MethodSorters.JVM)
public class SysVsLcRelationshipMapperTest {
	
	@Autowired
	private SysVsLcRelationshipMapper infoMapper;
	private static String USER_NUM = "12aasfasdfasdf";
	
	@Test
	public void testInsertSelective() {
		SysVsLcRelationship record = new SysVsLcRelationship();
		record.setLcNum("22");
		record.setVsNum("555");
		
		int amount = infoMapper.insertSelective(record);
		System.out.println(amount);
		assertEquals(1, amount);
	}
	
	@Test
	public void testSelectPageByMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vsNum", "555");
		List<SysVsLcRelationship>  s = infoMapper.selectPageByMap(map);
		for(SysVsLcRelationship tem : s ){
			System.out.println("------------------------"+tem.getLcNum());
		}
	}
	
	@Test
	public void teee(){
		int a = infoMapper.deleteSysVsLcByVsNum("555");
		System.out.println("==============="+a);
	}
	@Test
	public void teeevsNum(){
		int a = infoMapper.deleteSysVsLcByLcNum("22");
		System.out.println("==============="+a);
	}
	
	@Test
	public void testUpdateByPrimaryKeySelective () {
		SysVsLcRelationship record = new SysVsLcRelationship();
		record.setLcNum("22");
		record.setVsNum("5554444");
		
		int amount = infoMapper.update(record);
		System.out.println("--------------"+amount);
	}
	
	
	
}
