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





import com.ync365.seed.bussiness.modules.user.entity.SysUserWorkRelationship;
import com.ync365.seed.bussiness.modules.user.util.NumGenerator;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-bussiness.xml")
@FixMethodOrder(MethodSorters.JVM)
public class SysUserWorkRelationshipMapperTest {
	
	@Autowired
	private SysUserWorkRelationshipMapper infoMapper;
	private static String USER_NUM = "12aasfasdfasdf";
	
	@Test
	public void testInsertSelective() {
		SysUserWorkRelationship userInfo = new SysUserWorkRelationship();
		userInfo.setUserNum(NumGenerator.getUserNum());
		userInfo.setWork("0");
		
		int amount = infoMapper.insertSelective(userInfo);
		System.out.println(amount);
		assertEquals(1, amount);
	}
	
	@Test
	public void testSelectPageByMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", "f21eaa4cbc3d49f18099eedc650bf94c");
		List<SysUserWorkRelationship>  s = infoMapper.selectPageByMap(map);
		for(SysUserWorkRelationship tem : s ){
			System.out.println("------------------------"+tem.getUserNum());
		}
	}
	
	@Test
	public void testDeleteByPrimaryKey() {
		int amount = infoMapper.deleteSysUserWorkByUserNum("f21eaa4cbc3d49f18099eedc650bf94c");
		assertEquals(1, amount);
	}
	
	
	@Test
	public void testUpdateByPrimaryKeySelective () {
		SysUserWorkRelationship userInfo = new SysUserWorkRelationship();
		userInfo.setUserNum("f21eaa4cbc3d49f18099eedc650bf94c");
		userInfo.setWork("5555");
		
		int amount = infoMapper.update(userInfo);
		System.out.println("--------------"+amount);
	}
	
	
	
}
