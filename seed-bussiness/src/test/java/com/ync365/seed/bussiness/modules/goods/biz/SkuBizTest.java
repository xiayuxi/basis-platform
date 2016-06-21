package com.ync365.seed.bussiness.modules.goods.biz;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ync365.seed.bussiness.modules.Base.BaseTest;
import com.ync365.seed.bussiness.modules.goods.bo.SkuBO;
import com.ync365.seed.bussiness.modules.goods.entity.Sku;
import com.ync365.seed.utils.DateUtils;

public class SkuBizTest extends BaseTest {

	@Autowired
	SkuBiz skuBiz ;
	
	@Test
	public void testSearchPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearchAuditPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearchReleasePage() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearchPageCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testAudit() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearchSkuById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisPassAudit() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectSkuPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectSkuPageCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectSkuListByIds() {
		
		List<Integer> skuIds = new ArrayList<Integer>() ;
		skuIds.add(131);
		skuIds.add(132);
		
		List<SkuBO> list = skuBiz.selectSkuListByIds(skuIds);
		
		System.out.println(list.size());
	}
	
	@Test
	public void testSelectSkuStockPage(){
		String name = "";
		String subtitle = "";
		Integer categoryId = null;
		String sku ="" ;
		Double startQty = null ;
		Double endQty = null;
		int startIndex= 0 ;
		int pageSize = 10;
		
		List<Sku> list = skuBiz.selectSkuStockPage(name, subtitle, categoryId, sku, startQty, endQty, startIndex, pageSize);
		for(Sku entity:list){
			System.out.println("------------:"+entity.getAttrValue());
		}
		System.out.println(list.size());
		
	}
	
	/**
	 * 促销活动查询SKU列表
	 * @Title: selectSkuPage
	 * @Description: TODO    
	 * void    
	 * @throws
	 */
	@Test
	public void selectSkuPromotionPage(){
		String name = "";
		 
		Integer categoryId = null;
		List<Integer> sku = new ArrayList<Integer>() ;
		Double startPrice = null ;
		Double endPrice = null;
		int startIndex= 0 ;
		int pageSize = 10;
		Integer storeId = null;
		Integer brandId = null ;
		List<Integer> listsku = new ArrayList<Integer>();
		int sortColumn = 0 ;
		int sortDiraction =  0 ;
		
		List<Sku> list = skuBiz.selectSkuPromotionPage(startIndex, pageSize, storeId, categoryId, brandId, sku, startPrice, endPrice, name,listsku,sortColumn,sortDiraction);
		System.out.println("--------------"+list.size());
		for(Sku entity:list){
			System.out.println("------------:"+entity.getAttrValue());
		}
	}
	
	/**
	 * 促销活动查询SKU列表总记录数
	 * @Title: selectSkuPageCount
	 * @Description: TODO    
	 * void    
	 * @throws
	 */
	@Test
	public void selectSkuPageCount(){
		Integer categoryId = null;
		List<Integer> sku = new ArrayList<Integer>() ;
		Double startPrice = null ;
		Double endPrice = null;
		int startIndex= 0 ;
		int pageSize = 10;
		Integer storeId = 1;
		Integer brandId = null ;
		String name = "";
		List<Integer> listsku = new ArrayList<Integer>();

		int count = skuBiz.selectSkuPromotionPageCount(storeId, categoryId, brandId, sku, startPrice, endPrice, name,listsku);
		System.out.println("--------------"+count);
		 
	}
	
	@Test
	public void selectWeekStar(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("regionId", 2);
		map.put("startDate", DateUtils.getFrontDate(30));
		map.put("endDate", Calendar.getInstance().getTime());
		Integer regionId= 2;
		Date startDate = DateUtils.getFrontDate(30) ;
		Date endDate = Calendar.getInstance().getTime() ;
		
		SkuBO skuBO = skuBiz.selectWeekStar(regionId, startDate, endDate);
		
		System.out.println(skuBO.getFullName());
	}
	
	@Test
	public void selectSkuByRegion(){
		List<Integer> skuIds = new ArrayList<Integer>();
		skuIds.add(360);
		
		Integer regionId = 2 ;
		Map<Integer,SkuBO>  map = skuBiz.getSkuByIdAndRegionId(skuIds,regionId);
		
		System.out.println(map);
	}
	
	public void payOrder(){
		

	}

}
