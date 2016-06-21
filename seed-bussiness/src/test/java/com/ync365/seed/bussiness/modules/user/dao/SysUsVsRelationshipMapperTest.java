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






import com.ync365.seed.bussiness.modules.user.entity.SysUsVsRelationship;
import com.ync365.seed.bussiness.modules.user.util.NumGenerator;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-bussiness.xml")
@FixMethodOrder(MethodSorters.JVM)
public class SysUsVsRelationshipMapperTest {
	
	@Autowired
	private SysUsVsRelationshipMapper infoMapper;
	private static String USER_NUM = "12aasfasdfasdf";
	
	@Test
	public void testInsertSelective() {
		SysUsVsRelationship record = new SysUsVsRelationship();
		record.setUsNum("22");
		record.setVsNum("555");
		
		int amount = infoMapper.insertSelective(record);
		System.out.println(amount);
		assertEquals(1, amount);
	}
	
	@Test
	public void testSelectPageByMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usNum", "2255");
		List<SysUsVsRelationship>  s = infoMapper.selectPageByMap(map);
		for(SysUsVsRelationship tem : s ){
			System.out.println("------------------------"+tem.getUsNum());
		}
	}
	
	@Test
	public void teee(){
		int a = infoMapper.deleteSysUsVsByUsNum("22");
		System.out.println("==============="+a);
	}
	@Test
	public void teeevsNum(){
		int a = infoMapper.deleteSysUsVsByVsNum("555");
		System.out.println("==============="+a);
	}
	
	@Test
	public void testUpdateByPrimaryKeySelective () {
		SysUsVsRelationship record = new SysUsVsRelationship();
		record.setUsNum("22ffffff");
		record.setVsNum("555");
		
		int amount = infoMapper.update(record);
		System.out.println("--------------"+amount);
	}
	
	
	
}
