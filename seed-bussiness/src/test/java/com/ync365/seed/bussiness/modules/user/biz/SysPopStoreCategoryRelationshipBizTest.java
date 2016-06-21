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

import com.ync365.seed.bussiness.modules.user.entity.SysPopStoreCategoryRelationship;
import com.ync365.seed.bussiness.modules.user.util.NumGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-bussiness.xml")
public class SysPopStoreCategoryRelationshipBizTest {
	@Autowired
	private SysPopStoreCategoryRelationshipBiz biz;

	@Test
	public void testInsert() {
		SysPopStoreCategoryRelationship record = new SysPopStoreCategoryRelationship();
		record.setCategoryId(22);
		record.setPopStoreNum("uuid333333");
		int amount =biz.insert(record);
		Assert.assertEquals(1, amount);
	}

	@Test
	public void testOne() {
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("popStoreNum", "uuid333333");
		List<SysPopStoreCategoryRelationship> list = biz.selectPageByMap(map);
		if(null != list&&  list.size()>0 ){
			for(SysPopStoreCategoryRelationship temp : list){
				System.out.println("============"+temp.getPopStoreNum()+"==========="+temp.getCategoryId());
			}
		}else{
			System.out.println("list--------------为空");
		}
	}
	
	@Test
	public void testCount() {
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("popStoreNum", "uuid333333");
		int a = biz.selectPageCount(map);
		
		System.out.println("list--------"+a);
		
	}
	
	@Test
	public void testDel() {
		
		int a = biz.deleteByPrimaryKey("uuid333333");
		
		System.out.println("list--------"+a);
		
	}
}
