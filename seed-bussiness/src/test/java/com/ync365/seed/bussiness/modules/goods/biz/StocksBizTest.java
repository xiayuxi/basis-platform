package com.ync365.seed.bussiness.modules.goods.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ync365.seed.bussiness.modules.Base.BaseTest;

public class StocksBizTest extends BaseTest{
	
	@Autowired
	StocksBiz stockBiz ;
	@Test
	public void lockQyAddTest(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("skuId", 373);
		map.put("orderId", 2);
		map.put("num", 5);
		
		list.add(map);
		int update = stockBiz.lockQyAdd(list);
		
		System.out.println("update-----------"+update);
		
	}

}
