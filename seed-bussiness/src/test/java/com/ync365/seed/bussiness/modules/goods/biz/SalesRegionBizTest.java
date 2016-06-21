package com.ync365.seed.bussiness.modules.goods.biz;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ync365.seed.bussiness.modules.Base.BaseTest;
import com.ync365.seed.bussiness.modules.goods.entity.SaleRegion;

public class SalesRegionBizTest extends BaseTest {

	SaleRegionBiz saleRegionBiz;
	
	@Test
	public void getSaleRegionBySkuIdsAndRegion(){
		
		Integer regionId = 110100 ;
		List<Integer> skuIds = new ArrayList<Integer>();
		skuIds.add(376);
		skuIds.add(374);
		List<SaleRegion>  list  =saleRegionBiz.getSaleRegionBySkuIdsAndRegion(skuIds, regionId);
		
	}
	
	
	@Test
	public void selectSaleRegionList(){
		Integer skuId = 370 ;
		Integer regionId = 2 ;
		
		List<SaleRegion> list = saleRegionBiz.selectSaleRegionListFront(skuId, regionId);
		System.out.println(list.size());
	}
}
