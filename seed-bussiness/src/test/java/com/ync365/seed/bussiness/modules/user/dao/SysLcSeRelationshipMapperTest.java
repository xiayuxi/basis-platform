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
import com.ync365.seed.bussiness.modules.user.entity.SysLcSeRelationship;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-bussiness.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SysLcSeRelationshipMapperTest {
	@Autowired
	private SysLcSeRelationshipMapper mapper;

	@Test
	public void testInsert() {
		SysLcSeRelationship record = new SysLcSeRelationship();
		record.setLcNum("1");
		record.setSeNum("2");
		int n = mapper.insertSelective(record);
		Assert.isTrue(n == 1);
	}

	@Test
	public void testUpdate() {
		SysLcSeRelationship record = new SysLcSeRelationship();
		record.setLcNum("1");
		record.setSeNum("3");
		int n = mapper.update(record);
		Assert.isTrue(n == 1);
	}

	@Test
	public void testSearch() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lcNum", 1);
		List<SysLcSeRelationship> list = mapper.selectPageByMap(map);
		System.out.println(">>>>>>" + list.size());
		Assert.isTrue(list.size() == 1);
	}

	@Test
	public void testDelete() {
		int n = mapper.deleteByLcNum("1");
		Assert.isTrue(n == 1);
	}
}
