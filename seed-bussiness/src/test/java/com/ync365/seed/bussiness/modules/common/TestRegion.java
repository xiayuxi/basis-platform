package com.ync365.seed.bussiness.modules.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ync365.seed.bussiness.modules.common.biz.RegionBiz;
import com.ync365.seed.bussiness.modules.common.entity.Region;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-bussiness.xml")
public class TestRegion {
	@Autowired
	private RegionBiz regionBiz;

	@Test
	public void test() {
		// ClassPathXmlApplicationContext con = new
		// ClassPathXmlApplicationContext("spring-context-bussiness.xml");
		// RegionBiz biz = con.getBean(RegionBiz.class);
		Region[] regions = regionBiz.selectRegionAndParentRegionById(742099);
		System.out.println(regions.length);
	}

}
