package com.ync365.seed.bussiness.modules.user.dao;

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
import org.springframework.util.Assert;

import com.ync365.seed.bussiness.modules.user.entity.SysUserGrowKindRelationship;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-bussiness.xml")
@FixMethodOrder(MethodSorters.JVM)
public class SysUserGrowKindRelationshipMapperTest {
	@Autowired
	private SysUserGrowKindRelationshipMapper mapper;

	@Test
	public void testInsert() {
		SysUserGrowKindRelationship record = new SysUserGrowKindRelationship();
		record.setUserNum("1");
		record.setGrowKind("1");
		int n = mapper.insertSelective(record);
		Assert.isTrue(n == 1);
	}

	@Test
	public void testUpdate() {
		SysUserGrowKindRelationship record = new SysUserGrowKindRelationship();
		int n = mapper.update(record);
		Assert.isTrue(n == 1);
	}

	@Test
	public void testSearch() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lcNum", 1);
		List<SysUserGrowKindRelationship> list = mapper.selectPageByMap(map);
		System.out.println(">>>>>>" + list.size());
		Assert.isTrue(list.size() == 1);
	}

	@Test
	public void testDelete() {
		int n = mapper.deleteByUserNumm("1");
		Assert.isTrue(n == 1);
	}
}
