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

import com.ync365.seed.bussiness.modules.user.entity.SysRole;
import com.ync365.seed.bussiness.modules.user.util.NumGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-bussiness.xml")
public class SysRoleBizTest {
	@Autowired
	private SysRoleBiz biz;

	@Test
	public void testInsert() {
		SysRole record = new SysRole();
		record.setId(9999);
		record.setRoleName("ceshi");
		record.setDescription("ceshi");
		int amount =biz.insert(record);
		
		SysRole tt = biz.selectByPrimaryKey(999);
		System.out.println("-------------"+tt.getRoleName());
		Assert.assertEquals(1, amount);
	}

	@Test
	public void testOne() {
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("moduleName", "ceshi");
		List<SysRole> list = biz.searchPage(map,0,10);
		if(null != list&&  list.size()>0 ){
			for(SysRole temp : list){
				System.out.println("============"+temp.getRoleName()+"===========");
			}
		}else{
			System.out.println("list--------------为空");
		}
	}
	
	@Test
	public void testCount() {
		Map<String ,Object> map=new HashMap<String,Object>();
		
		int a = biz.selectPageCount(map);
		
		System.out.println("list--------"+a);
		
	}
	
	@Test
	public void testDel() {
		
		int a = biz.deleteByPrimaryKey(999);
		
		System.out.println("list--------"+a);
		
	}
}
