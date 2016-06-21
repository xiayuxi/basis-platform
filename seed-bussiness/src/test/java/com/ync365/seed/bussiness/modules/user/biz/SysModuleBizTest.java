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

import com.ync365.seed.bussiness.modules.user.entity.SysModule;
import com.ync365.seed.bussiness.modules.user.util.NumGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-bussiness.xml")
public class SysModuleBizTest {
	@Autowired
	private SysModuleBiz biz;

	@Test
	public void testInsert() {
		SysModule record = new SysModule();
		record.setId(999);
		record.setModuleName("ceshi");
		record.setModuleOrder(null);
		record.setParentId(0);
		record.setUrl("/usje/lll");
		record.setDescription("ceshi");
		int amount =biz.insert(record);
		Assert.assertEquals(1, amount);
	}

	@Test
	public void testOne() {
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("moduleName", "ceshi");
		List<SysModule> list = biz.selectSysModuleAllByMap(map);
		if(null != list&&  list.size()>0 ){
			for(SysModule temp : list){
				System.out.println("============"+temp.getModuleName()+"===========");
			}
		}else{
			System.out.println("list--------------为空");
		}
	}
	
	@Test
	public void testCount() {
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("moduleName", "ceshi");
		int a = biz.selectPageCount(map);
		
		System.out.println("list--------"+a);
		
	}
	
	@Test
	public void testDel() {
		
		int a = biz.deleteByPrimaryKey(999);
		
		System.out.println("list--------"+a);
		
	}
}
