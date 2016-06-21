package com.ync365.seed.bussiness.modules.goods.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ync365.seed.bussiness.modules.common.dao.RegionDao;
import com.ync365.seed.bussiness.modules.common.entity.Region;
import com.ync365.seed.bussiness.modules.goods.dao.SaleRegionDao;
import com.ync365.seed.bussiness.modules.goods.entity.SaleRegion;

@Service
public class SaleRegionBiz {

    @Autowired
    SaleRegionDao saleregionDao;
    
    @Autowired
    RegionDao  regionDao ;
    
    public List<SaleRegion> getSaleRegionsBySkuId(Integer skuId ){
        return saleregionDao.getSaleRegionsBySkuId(skuId );
    }
    
    
      
    
   public SaleRegion getSaleRegionsBySkuIdAndRegionId(Integer skuId, Integer regionId){
       Map<String, Object> map=new HashMap<String,Object>();
       map.put("skuId",skuId);
       map.put("regionId", regionId);
       return saleregionDao.getSaleRegionsBySkuIdAndRegionId(map);
   }

   
   public int insert(SaleRegion saleRegion){
       return saleregionDao.insert(saleRegion);
   }

   
   public List<SaleRegion> getSaleRegionBySkuIdsAndRegion(List<Integer> skuIds,Integer regionId){
	   Map<String, Object> map=new HashMap<String,Object>();
       map.put("skuIds",skuIds);
       map.put("regionId", regionId);
       return saleregionDao.getSaleRegionBySkuIdsAndRegion(map);
	
   }
   
   /**
    * 前台查询销售区域
    * @Title: selectSaleRegionListFront
    * @Description: TODO    ：    
    * @author: robo   
    * @date: 2015年10月24日 下午4:40:56       
    * @version: 
    *
    * @param skuId
    * @param regionId
    * @return
    *
    */
   public List<SaleRegion> selectSaleRegionListFront(Integer skuId,Integer regionId){
	  
	   Assert.notNull(skuId ,"skuId参数为空");
	   Assert.notNull(regionId ,"regionId参数为空");
	   Map<String, Object> map=new HashMap<String,Object>();
       map.put("skuId",skuId);
       
       /**
       List<Region> regionList = regionDao.selectRegionByParentId(regionId);
       List<Integer> regionIds = new ArrayList<Integer>();
       for(Region region:regionList ){
    	   regionIds.add(region.getId()) ;
       }
       
       map.put("regionIds", regionIds);
       */
       map.put("regionId", regionId);
       
       return saleregionDao.selectSaleRegionListFront(map);
   }

}
