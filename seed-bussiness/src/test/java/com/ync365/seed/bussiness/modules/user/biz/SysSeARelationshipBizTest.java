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



import com.ync365.seed.bussiness.modules.user.entity.SysSeARelationship;
import com.ync365.seed.bussiness.modules.user.util.NumGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-bussiness.xml")
public class SysSeARelationshipBizTest {
	@Autowired
	private SysSeARelationshipBiz biz;

	@Test
	public void testInsert() {
		SysSeARelationship record = new SysSeARelationship();
		//record.setPopStoreNum(NumGenerator.getPoPNum());
		record.setaNum("eee");
		record.setSeNum("aaa");
		int amount =biz.insert(record);
		Assert.assertEquals(1, amount);
	}

	@Test
	public void testOne() {
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("seNum", "aaa");
		List<SysSeARelationship> list = biz.selectPageByMap(map);
		if(null != list&&  list.size()>0 ){
			for(SysSeARelationship temp : list){
				System.out.println("============"+temp.getaNum()+"===========");
			}
		}else{
			System.out.println("list--------------为空");
		}
	}
	
	@Test
	public void testCount() {
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("seNum", "aaa");
		int a = biz.selectPageCount(map);
		
		System.out.println("list--------"+a);
		
	}
	
	@Test
	public void testDel() {
		
		int a = biz.deleteByPrimaryKeyBySeNum("aaa");
		
		System.out.println("list--------"+a);
		
	}
}
