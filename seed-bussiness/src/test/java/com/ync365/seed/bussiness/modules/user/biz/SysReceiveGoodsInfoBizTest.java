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

import com.ync365.seed.bussiness.modules.user.entity.SysReceiveGoodsInfo;
import com.ync365.seed.bussiness.modules.user.util.NumGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-bussiness.xml")
public class SysReceiveGoodsInfoBizTest {
	@Autowired
	private SysReceiveGoodsInfoBiz biz;

	@Test
	public void testInsert() {
		SysReceiveGoodsInfo record = new SysReceiveGoodsInfo();
		
		record.setUserNum(NumGenerator.getPoPNum());
		record.setSysReceiveGoodsName("dddddddddddd");
		record.setSysReceiveGoodsMobile("33333333333333");
		record.setSysReceiveGoodsIsDefault(0);
		record.setSysReceiveGoodsAddressDetail("eeeeeeeeeeee");
		record.setSysReceiveGoodsAddress(33333);
		int amount =biz.insert(record);
		Assert.assertEquals(1, amount);
	}

	@Test
	public void testOne() {
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("userNum", "3ce728e3c08f45d8ba5bb3db35a9a320");
		List<SysReceiveGoodsInfo> list = biz.selectPageByMap(map);
		if(null != list&&  list.size()>0 ){
			for(SysReceiveGoodsInfo temp : list){
				System.out.println("============"+temp.getSysReceiveGoodsMobile()+"===========");
			}
		}else{
			System.out.println("list--------------为空");
		}
	}
	
	@Test
	public void testCount() {
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("userNum", "3ce728e3c08f45d8ba5bb3db35a9a320");
		int a = biz.selectPageCount(map);
		
		System.out.println("list--------"+a);
		
	}
	
	@Test
	public void selectbyId(){
		SysReceiveGoodsInfo s = biz.selectByPrimaryKey(15);
		System.out.println("============="+s.getUserNum());
	}
	
	@Test
	public void testDel() {
		
		int a = biz.deleteByPrimaryKey(15);
		
		System.out.println("list--------"+a);
		
	}
}
