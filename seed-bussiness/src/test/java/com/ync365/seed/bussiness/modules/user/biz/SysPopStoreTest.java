package com.ync365.seed.bussiness.modules.user.biz;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ync365.seed.bussiness.modules.goods.entity.Brand;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreBO;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreSearchBO;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStore;
import com.ync365.seed.utils.DateUtils;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={ "classpath:spring-context-bussiness.xml","classpath:spring-context-redis.xml"})
public class SysPopStoreTest {
	@Autowired
	private SysPopStoreBiz biz;
	
	@Test
	public void selectPageByMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		List list = biz.selectPageByMap(map);
		assertEquals(2, list.size());
	}
	
	@Test
	public void selectPageBySearchBO() {
		Date d = DateUtils.stringToDate("2015-10-05 16:46:45", "yyyy-MM-dd HH:mm:ss");
		
		PopStoreSearchBO popStoreSearchBo = new PopStoreSearchBO();
		popStoreSearchBo.setPopStoreNameSearch("ceshi");
		//popStoreSearchBo.setBeginTimeSearch(d);
		List<PopStoreBO> list = biz.selectPageBySearchBO(popStoreSearchBo);
		System.out.println(list.size());
	}
	
	
	@Test
	public void testSelectById() {
	    PopStoreBO popStore = biz.selectPopStoreById(18);
	//	assertNotNull(popStore);
		System.out.println(popStore.getPopStoreName());
	}
	
	@Test
	public void testSelectBrandByChName() {
		Brand brand = biz.selectBrandByChName("金正大");
		assertNotNull(brand);
	}
}
