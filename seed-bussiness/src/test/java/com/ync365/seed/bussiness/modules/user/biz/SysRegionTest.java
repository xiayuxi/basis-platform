package com.ync365.seed.bussiness.modules.user.biz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ync365.seed.bussiness.modules.common.biz.RegionBiz;
import com.ync365.seed.bussiness.modules.common.entity.Region;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-bussiness.xml")

public class SysRegionTest {
	
	@Autowired
	RegionBiz regionBiz;
	
	@Test
	public void selectRegionAndParentRegionById() {
		Region[] re = regionBiz.selectRegionAndParentRegionById(1);
		System.out.println(re.toString());
	}
}
