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

import com.ync365.seed.bussiness.modules.user.entity.SysUserAttentionPopStore;
import com.ync365.seed.bussiness.modules.user.util.NumGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-bussiness.xml")
public class SysUserAttentionPopStoreBizTest {
	@Autowired
	private SysUserAttentionPopStoreBiz biz;

	@Test
	public void testInsert() {
		SysUserAttentionPopStore record = new SysUserAttentionPopStore();
		//record.setPopStoreNum(NumGenerator.getPoPNum());
		record.setPopStoreNum("111");
		record.setUserNum("eee");
		int amount =biz.insert(record);
		Assert.assertEquals(1, amount);
	}

	@Test
	public void testOne() {
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("userNum", "eee");
		List<SysUserAttentionPopStore> list = biz.selectPageByMap(map);
		if(null != list&&  list.size()>0 ){
			for(SysUserAttentionPopStore temp : list){
				System.out.println("============"+temp.getUserNum()+"===========");
			}
		}else{
			System.out.println("list--------------为空");
		}
	}
	
	
	@Test
	public void testDel() {
		
		int a = biz.deleteByUserNumm("eee");
		
		System.out.println("list--------"+a);
		
	}
}
