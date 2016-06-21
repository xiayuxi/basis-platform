package com.ync365.seed.bussiness.modules.goods.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.ync365.seed.bussiness.modules.goods.bo.SkuBO;
import com.ync365.seed.bussiness.modules.goods.dao.BrandDao;
import com.ync365.seed.bussiness.modules.goods.dao.CategoryDao;
import com.ync365.seed.bussiness.modules.goods.dao.FeatureDao;
import com.ync365.seed.bussiness.modules.goods.dao.GoodsDao;
import com.ync365.seed.bussiness.modules.goods.dao.OperateDao;
import com.ync365.seed.bussiness.modules.goods.dao.SkuDao;
import com.ync365.seed.bussiness.modules.goods.dao.SkuFeatureDao;
import com.ync365.seed.bussiness.modules.goods.entity.Brand;
import com.ync365.seed.bussiness.modules.goods.entity.Category;
import com.ync365.seed.bussiness.modules.goods.entity.Feature;
import com.ync365.seed.bussiness.modules.goods.entity.Goods;
import com.ync365.seed.bussiness.modules.goods.entity.Operate;
import com.ync365.seed.bussiness.modules.goods.entity.SaleRegion;
import com.ync365.seed.bussiness.modules.goods.entity.Sku;
import com.ync365.seed.bussiness.modules.goods.entity.SkuFeature;
import com.ync365.seed.commons.solr.SolrUtil;
import com.ync365.seed.utils.Constants;
import com.ync365.seed.utils.GoodsConstants;
import com.ync365.seed.utils.StringUtils;

@Service
public class SkuBiz {
	
	 private static final Logger logger = LoggerFactory.getLogger(SkuBiz.class);
	 
	@Autowired
	SkuDao skuDao;
	
	@Autowired
	BrandDao brandDao ;
	
	@Autowired
	GoodsDao goodsDao;
	
	@Autowired
	SkuFeatureDao skuFeatureDao ;
	
	@Autowired
	FeatureDao featureDao ;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	OperateDao operateDao;
	
	@Autowired
	SaleRegionBiz saleRegionBiz ;
	
 
	public List<Sku> searchPage(Map<String, Object> map, int startIndex,
			int pageSize) {
		map.put("pageIndex", startIndex);
		map.put("pageSize", pageSize);
		return skuDao.searchPage(map);
	}

	/**
	 * 商品审核列表
	 * @param map
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<Sku> searchAuditPage(Map<String, Object> map, int startIndex,
			int pageSize) {
		map.put("startIndex", startIndex);
		map.put("pageSize", pageSize);
		return skuDao.searchAuditPage(map);
	}
	
    /**
     * 商品审核列表
     * @param map
     * @param startIndex
     * @param pageSize
     * @return
     */
    public int searchAuditPageCount(Map<String, Object> map) {
        return skuDao.searchAuditPageCount(map);
    }
	
	/**
	 * 商品发布列表
	 * @param map
	 * @param startIndex
	 * @param rows
	 * @return
	 */
	public List<Sku> searchReleasePage(Map<String, Object> map, int startIndex,
			int pageSize) {
		map.put("pageIndex", startIndex);
		map.put("pageSize", pageSize);
		return skuDao.searchReleasePage(map);
	}
	
	public int searchPageCount(Map<String, Object> map) {
		return skuDao.searchPageCount(map);
	}
	
	public int ReleaseSearchPageCount(Map<String,Object> map){
		return skuDao.ReleaseSearchPageCount(map);
	}
	
	@Transactional
	public int updateStatus(String skuId,int status){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("skuId", skuId);
		map.put("status", status);
		int update = skuDao.grounding(map) ;
		
		Operate record = new Operate();
		record.setSkuId(Integer.parseInt(skuId));
		if(status== GoodsConstants.GoodsStatus.GROUNDING.v()){
			record.setReleaseTime(new Date());
		}else if(status==GoodsConstants.GoodsStatus.UN_GROUNDING.v()){
			record.setCancelReleaseTime(new Date());
		}
		//record.setReason("");
		
	 
		if(operateDao.selectByPrimaryKey(Integer.parseInt(skuId))!=null){
			update  = operateDao.updateByPrimaryKeySelective(record);
		}else{
			update  = operateDao.insert(record);
		}
		/**
		 * 状态为1并且 更新成功update=1 则建立索引
		 */
		if(GoodsConstants.GoodsStatus.GROUNDING.v() == status  && update == 1 ){
			
			Sku sku = skuDao.selectByPrimaryKey(Integer.parseInt(skuId));
			
			//商品
			Goods goods = goodsDao.selectByPrimaryKey(sku.getGoodsId());
			
			//分类
			Category category = categoryDao.selectByPrimaryKey(goods.getCategoryId());
			String categoryName = "";
			if(category != null ){
				categoryName = category.getName() ;
			}
			
			//品牌
			Brand brand = brandDao.selectByPrimaryKey(goods.getBrandId());
			 
			
			Map<String,Object> solrMap = new HashMap<String, Object>();
			
			
			//索引字段：skuId,商品名称、属性、属性值、品牌、商品编码
			solrMap.put("id", sku.getSkuId());
			solrMap.put("name", sku.getGoodsName());
			
			Map<String,Object> skuFeatureMap = new HashMap<String,Object>();
			skuFeatureMap.put("skuId", skuId);
			
			//sku对应的所有属性列表
			List<SkuFeature> skuFeatureList = skuFeatureDao.selectListBySkuId(skuFeatureMap) ;
			String featureValue = "";
			String featureName = "";
			for(SkuFeature sf:skuFeatureList){
				featureValue += sf.getSpecValue() +"," ;
				Feature feature =  featureDao.selectByPrimaryKey(sf.getFeatureId());
				if(feature != null ){
					featureName += feature.getName() +"," ;
				}
			}
			
			//属性值合集
			if(StringUtils.isNotBlank(featureValue)){
				featureValue = featureValue.substring(0,featureValue.length()-1 );
			}
			
			//属性名称合集
			if(StringUtils.isNotBlank(featureName)){
				featureName = featureName.substring(0,featureName.length()-1);
			}
			
			solrMap.put("featureName", featureName);
			solrMap.put("featureValue", featureValue);
			
			if(brand != null ){
				solrMap.put("brandName", brand.getChName());
			}
			
			if(sku != null ){
				solrMap.put("code", sku.getGoodsCode());
			}
			solrMap.put("categoryName", categoryName);
 
			SolrUtil.addObject(solrMap );
		}else{
			try {
				SolrUtil.deleteDocByQuery(skuId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return update;
		
	}
	/***
	 * 商品复制sku
	 */
	 public int insert(Sku sku){
	     return skuDao.insert(sku);
	 }
	/**
	 * 商品审核
	 * @param skuId
	 * @param status
	 * @return
	 */
	@Transactional
	public int audit(String skuId,int status){
		
		//更新SKU表状态
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("skuId", skuId);
		map.put("status", status);
		int update =  skuDao.grounding(map) ;
		
		//写SKU操作表
		Operate record = new Operate();
		record.setSkuId(Integer.parseInt(skuId));
		record.setAuditTime(new Date());
		record.setReason("");
		if(operateDao.selectByPrimaryKey(Integer.parseInt(skuId))!=null){
			update = operateDao.updateByPrimaryKeySelective(record);
		}else{
			update = operateDao.insert(record);
		}
		return  update ;
	}


	/**
	 * 得到sku对象
	 * @Title: selectSkuById
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月2日 上午11:20:52       
	 * @version: 
	 *
	 * @param skuId
	 * @return
	 *
	 */
	public Sku selectSkuById(Integer skuId) {
		return skuDao.selectByPrimaryKey(skuId);
	} 
	
	public SkuBO selectSkuAndGoodsById(Integer skuId){
		return skuDao.selectSkuAndGoodsById(skuId) ;
	}

	@Transactional
	public int disPassAudit(String skuId, String status, String reason) {
		//更新SKU表状态
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("skuId", skuId);
		map.put("status", status);
		int update =  skuDao.grounding(map) ;
		if(update!=1){
			return update;
		}
		//写SKU操作表
		Operate record = new Operate();
		record.setSkuId(Integer.parseInt(skuId));
		record.setAuditTime(new Date());
		record.setReason(reason);
		if(operateDao.selectByPrimaryKey(Integer.parseInt(skuId))!=null){
			update = operateDao.updateByPrimaryKeySelective(record);
		}else{
			update = operateDao.insert(record);
		}
		return  update ;
	}


	/**
	 * 促销活动：根据参数查询商品列表
	 * @Title: selectSkuListPage
	 * @Description: TODO
	 * @param startIndex      起始位置
	 * @param pageSize        每页条数
	 * @param storeId         分类 【自营，pop】
	 * @param categoryId      商品分类
	 * @param brandId         品牌
	 * @param sku             商品编码 
	 * @param startPrice      价格区间
	 * @param endPrice        价格区间
	 * @param name            商品名称 
	 * @return List<Sku>    
	 * @throws
	 */
	public List<Sku> selectSkuPromotionPage(int startIndex,int pageSize,Integer storeId,
			Integer categoryId,Integer brandId,List<Integer> inSkuList,
			Double startPrice,Double endPrice,String name,List<Integer> skuList,
			int sortColumn,int sortDirection){
		
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("startIndex", startIndex);
		map.put("pageSize", pageSize);
		
		if(storeId != null && storeId.intValue() == -1 ){
			map.put("goodsType", 1);
		}else{
			map.put("storeId", storeId);
		}
		map.put("categoryId", categoryId);
		map.put("brandId", brandId);
		map.put("inSkuList", inSkuList);
		map.put("startPrice", startPrice);
		map.put("endPrice", endPrice);
        map.put("name", name);
        map.put("skuList", skuList);
        
        //排序字段
        if(sortColumn == Constants.PromotionQuerySort.CostPrice.v() ){
			map.put("sortColumn", "a."+Constants.PromotionQuerySort.CostPrice.c());
		}else if(sortColumn ==Constants.PromotionQuerySort.SalesCount.v()   ){
			map.put("sortColumn", "a."+Constants.PromotionQuerySort.SalesCount.c());
		}else if(sortColumn == Constants.PromotionQuerySort.ReleaseTime.v() ){
			map.put("sortColumn", "d."+Constants.PromotionQuerySort.ReleaseTime.c());
		}else {
			map.put("sortColumn", "a."+Constants.PromotionQuerySort.SalesCount.c());
		}
	 
		//升序
		if(sortDirection == Constants.SortDirection.ASC.v()){
			map.put("sortDirection", Constants.SortDirection.ASC.c());
		}else{
			map.put("sortDirection", Constants.SortDirection.DESC.c());
		}
         
        return skuDao.selectSkuPromotionPage(map);
		
	}
	
	/**
	 * 根据参数查询商品列表  总记录数
	 * @Title: selectSkuPageCount
	 * @Description: TODO
	 * @param storeId         分类 【自营，pop】
	 * @param categoryId      商品分类
	 * @param brandId         品牌
	 * @param sku             商品编码 
	 * @param startPrice      价格区间
	 * @param endPrice        价格区间
	 * @param name            商品名称 
	 * @return  int    
	 * @throws
	 */
	public int selectSkuPromotionPageCount(Integer storeId,Integer categoryId,Integer brandId,
			List<Integer> inSkuList,Double startPrice,Double endPrice,String name,List<Integer> skuList){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("storeId", storeId);
		map.put("categoryId", categoryId);
		map.put("brandId", brandId);
		map.put("inSkuList", inSkuList);
		map.put("startPrice", startPrice);
		map.put("endPrice", endPrice);
		map.put("name", name);
		map.put("skuList", skuList);
		
		return skuDao.selectSkuPromotionPageCount(map);
	}

	/**
	 * 根据商品集合id查询商品详细信息
	 * @Title: selectSkuList
	 * @Description: TODO
	 * @param skuIds
	 * @return List<Sku>    
	 * @throws
	 */
	public List<SkuBO>  selectSkuListByIds(List<Integer> skuIds){
		return skuDao.selectSkuListByIds(skuIds);
	}
	
	public List<Sku> selectSkuStockPage(String name,String subtitle,Integer categoryId,
			String sn,Double startQty,Double endQty,int startIndex,int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", name);
		map.put("subtitle", subtitle);
		map.put("categoryId", categoryId);
		map.put("sn", sn);
		map.put("startQty", startQty);
		map.put("endQty", endQty);
		map.put("startIndex", startIndex);
		map.put("pageSize", pageSize);
		return skuDao.selectSkuStockPage(map);
	}
	
	public int selectSkuStockPageCount(String name,String subtitle,Integer categoryId,
			String sn,Double startQty,Double endQty){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", name);
		map.put("subtitle", subtitle);
		map.put("categoryId", categoryId);
		map.put("sn", sn);
		map.put("startQty", startQty);
		map.put("endQty", endQty);
		 
		return skuDao.selectSkuStockPageCount(map);
	}
	
 
	/**
	 * 前台PHP -->通过分类点击查询商品列表查询
	 * @Title: selectSkuIndexList
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月14日 下午3:44:44       
	 * @version: 
	 *
	 * @param brandId
	 * @param categoryId
	 * @param moq
	 * @param startPrice
	 * @param endPrice
	 * @param sortColumn
	 * @param sortDirection
	 * @param regionId
	 * @param pageSize
	 * @param startIndex
	 * @return
	 *
	 */
	public List<SkuBO> selectSkuIndexList(Integer brandId,Integer categoryId,Integer moq,
				Double startPrice,Double endPrice,int sortColumn,
				int sortDirection,Integer regionId,int pageSize,int startIndex){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("brandId", brandId);
		map.put("categoryId", categoryId);
		map.put("moq", moq);
		map.put("startPrice", startPrice);
		map.put("endPrice", endPrice);
 
		if(sortColumn == 1 ){
			map.put("sortColumn", "b.cost_price");
		}else if(sortColumn ==2 ){
			map.put("sortColumn", "b.sales_count");
		}else if(sortColumn == 3 ){
			map.put("sortColumn", "c.release_time");
		}else {
			map.put("sortColumn", "b.cost_price");
		}
		
		//升序
		if(sortDirection == Constants.SortDirection.ASC.v()){
			map.put("sortDirection", Constants.SortDirection.ASC.c());
		}else{
			map.put("sortDirection", Constants.SortDirection.DESC.c());
		}
		
		map.put("regionId", regionId);
		 
		
		map.put("startIndex", startIndex);
		map.put("pageSize", pageSize);
		
		return skuDao.selectSkuIndexList(map);
		
	}
	
	public int selectSkuIndexListCount(Integer brandId,Integer categoryId,Integer moq,
			Double startPrice, Double endPrice,Integer regionId){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("brandId", brandId);
		map.put("categoryId", categoryId);
		map.put("moq", moq);
		map.put("startPrice", startPrice);
		map.put("endPrice", endPrice);
		map.put("regionId", regionId);
		return skuDao.selectSkuIndexListCount(map);
	}
	
	 /**
     * php 搜索商品
     * @Title: selectSkuQueryIndexList
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月16日 下午2:45:56       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    public List<SkuBO> selectSkuQueryIndexList(Integer brandId,Integer categoryId,Integer moq,
			Double startPrice,Double endPrice,int sortColumn,
			int sortDirection,Integer regionId,String keywords,int pageSize,int startIndex){
    	Map<String, Object> map = new HashMap<String,Object>();
		map.put("brandId", brandId);
		map.put("categoryId", categoryId);
		map.put("moq", moq);
		map.put("startPrice", startPrice);
		map.put("endPrice", endPrice);
		if(sortColumn == 1 ){
			map.put("sortColumn", "b.cost_price");
		}else if(sortColumn ==2 ){
			map.put("sortColumn", "b.sales_count");
		}else if(sortColumn == 3 ){
			map.put("sortColumn", "c.release_time");
		}else {
			map.put("sortColumn", "b.cost_price");
		}
		map.put("regionId", regionId);
		
		//升序
		if(sortDirection == Constants.SortDirection.ASC.v()){
			map.put("sortDirection", Constants.SortDirection.ASC.c());
		}else{
			map.put("sortDirection", Constants.SortDirection.DESC.c());
		}
		
		map.put("startIndex", startIndex);
		map.put("pageSize", pageSize);
		
		if(StringUtils.isNotBlank(keywords)){
			String key[] = keywords.split(" ");
			
			String sql = "";
			for(String s:key){
				
				if(StringUtils.isNoneBlank(s)){
					sql += "   b.full_name like   concat('%','"+s+"','%' )     or a.keywords like  concat('%','"+s+"','%' )  " +" or "; 
				}
			}
			int position  = sql.lastIndexOf("or");
			sql = sql.substring(0,position);
			map.put("keywords","("+ sql +")");
		}else{
			map.put("keywords", keywords);
		}
		
		return skuDao.selectSkuQueryIndexList(map);
    }
    
    /**
     * php 搜索商品总记录数
     * @Title: selectSkuQueryIndexListCount
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月16日 下午2:46:41       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    public int selectSkuQueryIndexListCount(Integer brandId,Integer categoryId,Integer moq,
			Double startPrice, Double endPrice,Integer regionId,String keywords){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("brandId", brandId);
		map.put("categoryId", categoryId);
		map.put("moq", moq);
		map.put("startPrice", startPrice);
		map.put("endPrice", endPrice);
		map.put("regionId", regionId);
		if(StringUtils.isNotBlank(keywords)){
			String key[] = keywords.split(" ");
			
			String sql = "";
			for(String s:key){
				
				if(StringUtils.isNoneBlank(s)){
					sql += "  b.full_name like   concat('%','"+s+"','%' )     or a.keywords like  concat('%','"+s+"','%' )  " +" or "; 
				}
			}
			int position  = sql.lastIndexOf("or");
			sql = sql.substring(0,position);
			map.put("keywords","( "+sql+" )");
		}else{
			map.put("keywords", keywords);
		}
		return skuDao.selectSkuQueryIndexListCount(map);
    }
    

	/**
	 * 根据sku集合Id，地区id查询对应的sku列表与销售价格列表
	 * @Title: getSkuByIdAndRegion
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月12日 下午4:09:56       
	 * @version: 
	 *
	 * @param skuIds
	 * @param regionMap
	 * @return
	 *
	 */
	public Map<Integer,SkuBO> getSkuByIdAndRegion(List<Integer> skuIds,Map<Integer,Integer> regionMap){
		Map<Integer,SkuBO> skuMap = new HashMap<Integer,SkuBO>();
		List<SkuBO> skuList = selectSkuListByIds(skuIds);
		
		if(regionMap != null ){
			//得到市，省的regionId
			Integer province = regionMap.get(1);
			Integer city = regionMap.get(2);
			
			if(province != null ) {
				//省价格
				List<SaleRegion> provinceList = saleRegionBiz.getSaleRegionBySkuIdsAndRegion(skuIds, province);
				for(SkuBO sku:skuList){
					Integer skuId = sku.getSkuId() ;
					for(SaleRegion saleRegion:provinceList){
						if(skuId.intValue() == saleRegion.getSkuId().intValue()){
							if(saleRegion.getPrice() != null && saleRegion.getPrice().intValue() > 0 ){
								sku.setCostPrice(saleRegion.getPrice());
								break;
							}
						}
					}
				}
			}
			
			if(city != null ){
				//市价格
				List<SaleRegion> cityList = saleRegionBiz.getSaleRegionBySkuIdsAndRegion(skuIds, city);
				for(SkuBO sku:skuList){
					Integer skuId = sku.getSkuId() ;
					for(SaleRegion saleRegion:cityList){
						if(skuId.intValue() == saleRegion.getSkuId().intValue()){
							if(saleRegion.getPrice() != null && saleRegion.getPrice().intValue() > 0 ){
								sku.setCostPrice(saleRegion.getPrice());
								break;
							}
						}
					}
				}
			}
		}
		
		for(Integer skuId :skuIds) {
			for(SkuBO sku:skuList){
				if(skuId.intValue() == sku.getSkuId().intValue()){
					skuMap.put(skuId, sku);
					break;
				}
			}
			
		}
		
		return skuMap ;
	}
	
	/**
	 * 根据市Id查询对应的SKU列表信息
	 * @Title: getSkuByIdAndRegionId
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月17日 上午10:36:42       
	 * @version: 
	 *
	 * @param skuIds
	 * @param regionId
	 * @return
	 *
	 */
	public Map<Integer,SkuBO> getSkuByIdAndRegionId(List<Integer> skuIds,Integer regionId){
		
		Assert.notNull(skuIds,"skuIds传入参数为空");
		Assert.notNull(regionId,"regionId传入参数为空");
		
		Map<Integer,SkuBO> skuMap = new HashMap<Integer,SkuBO>();
		List<SkuBO> skuList = selectSkuListByIds(skuIds);
		
		if(regionId != null ){
			
			//市价格
			List<SaleRegion> cityList = saleRegionBiz.getSaleRegionBySkuIdsAndRegion(skuIds, regionId);
			for(SkuBO sku:skuList){
				Integer skuId = sku.getSkuId() ;
				for(SaleRegion saleRegion:cityList){
					if(skuId.intValue() == saleRegion.getSkuId().intValue()){
						if(saleRegion.getPrice() != null && saleRegion.getPrice().intValue() > 0 ){
							sku.setCostPrice(saleRegion.getPrice());
							break;
						}
					}
				}
			}
			 
		}
		
		for(Integer skuId :skuIds) {
			for(SkuBO sku:skuList){
				if(skuId.intValue() == sku.getSkuId().intValue()){
					skuMap.put(skuId, sku);
					break;
				}
			}
			
		}
		
		return skuMap ;
	}
	
	
	/**
	 * 根据sku集合，地区查询对应的sku信息   促销活动
	 * @Title: getSkuByIdAndRegion
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月14日 下午2:28:17       
	 * @version: 
	 *
	 * @param skuIds
	 * @param regionId
	 * @return
	 *
	 */
	public List<SkuBO> getSkuByIdAndRegion(List<Integer> skuIds,Integer regionId){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("skuList", skuIds) ;
		map.put("regionId", regionId);
		 
		return skuDao.selectSkuByIdAndRegion(map);
	}
	
	public List<SkuBO> getSkuBySkuIdsAndRegion(List<SkuBO> skuIds,Integer regionId){

		List<Integer> skuIdList = new ArrayList<Integer>();
		for(SkuBO skuBO :skuIds){
			skuIdList.add(skuBO.getSkuId());
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("skuList", skuIdList) ;
		map.put("regionId", regionId);
		 
		List<SkuBO> skuList =  skuDao.selectSkuByIdAndRegion(map);
		
		for(SkuBO sku:skuList){
			Integer skuId = sku.getSkuId() ;
		
			for(SkuBO skuBO:skuIds){
				if(skuId != null && skuId.intValue() == skuBO.getSkuId().intValue()){
					sku.setActivityType(skuBO.getActivityType());
					break;
				}
			}
		}
		
		return skuList;
	}
	
	/**
	 * 测土配肥接口
	 * @Title: selectSoilDistributionList
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月15日 下午2:40:20       
	 * @version: 
	 *
	 * @param categoryId
	 * @param regionId
	 * @return
	 *
	 */
	public List<SkuBO>  selectSoilDistributionList(int type,Integer regionId,Integer pageSize){
		Map<String, Object> map = new HashMap<String,Object>();
		
		/**
		 * PACKAGE(1,"超值套餐"),FATCARD(2,"精准配肥卡"),SUPPLYAREA(3,"综合供应专区");
		 */
		if(type == Constants.SoilTest.PACKAGE.v()){
			map.put("isVirtual", 1);
		}else if(type == Constants.SoilTest.FATCARD.v()){
			map.put("isVirtual", 2);
		}else {
			map.put("isSupplyArea", 1);
		}
		map.put("regionId", regionId);
		map.put("pageSize", pageSize);
		return skuDao.selectSoilDistributionList(map);
	}
	
	/**
	 * 海外购接口
	 * @Title: selectOverseasGoodsList
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月15日 下午3:42:26       
	 * @version: 
	 *
	 * @param categoryList
	 * @param regionId
	 * @param pageSize
	 * @return
	 *
	 */
	public List<SkuBO> selectOverseasGoodsList(List<Integer> categoryList,Integer regionId,Integer pageSize){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("categoryList", categoryList);
		map.put("regionId", regionId);
		map.put("pageSize", pageSize);
		return skuDao.selectOverseasGoodsList(map);
	}
	
	public SkuBO selectWeekStar(Integer regionId,Date startDate,Date endDate){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("regionId", regionId);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		
		return skuDao.selectWeekStar(map);
	}
	
	/**
	 * 前台查询最热商品
	 * @Title: selectHotGoodsList
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月17日 上午11:16:54       
	 * @version: 
	 *
	 * @param pageSize
	 * @param regionId
	 * @return
	 *
	 */
	public List<SkuBO>  selectHotGoodsList(Integer pageSize,Integer regionId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pageSize", pageSize);
		map.put("regionId", regionId);
		List<SkuBO> goodsList = skuDao.selectHotGoodsList(map);
		return goodsList;
	}
	
	/**
	 * 前台查询最新商品
	 * @Title: selectNewGoodsList
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月17日 上午11:16:41       
	 * @version: 
	 *
	 * @param pageSize
	 * @param regionId
	 * @return
	 *
	 */
	public List<SkuBO>  selectNewGoodsList(Integer pageSize,Integer regionId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pageSize", pageSize);
		map.put("regionId", regionId);
		List<SkuBO> goodsList = skuDao.selectNewGoodsList(map);
		return goodsList;
	}
	
	public List<SkuBO> selectSkuListByCategoryId(Integer pageSize,Integer categoryId,Integer regionId){
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("pageSize", pageSize) ;
		map.put("categoryId", categoryId) ;
		map.put("regionId", regionId);
		
		return skuDao.selectSkuListByCategoryId(map);
	}
	
	public List<SkuBO> selectSkuListByCatAndRegionId(Integer regionId,List<Integer> catList,Integer pageSize){
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("regionId", regionId) ;
		map.put("list", catList) ;
		map.put("pageSize", pageSize);
		
		return skuDao.selectSkuListByCatAndRegionId(map);
	}
	
	/**
	 * 厂家直供二级页面查询接口
	 * @Title: selectSkuListByCatAndRegionIdPage
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月17日 下午6:52:04       
	 * @version: 
	 *
	 * @param categoryIds
	 * @param regionId
	 * @param startIndex
	 * @param pageSize
	 * @return
	 *
	 */
	public List<SkuBO> selectSkuListByCatAndRegionIdNext(List<Integer> categoryIds,Integer regionId,int startIndex,int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("regionId", regionId);
		map.put("list", categoryIds);
		map.put("startIndex", startIndex);
		map.put("pageSize", pageSize);
		
		return skuDao.selectSkuListByCatAndRegionIdNext(map);
	}
	
	public int selectSkuListByCatAndRegionIdNextCount(List<Integer> categoryIds,Integer regionId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("regionId", regionId);
		map.put("list", categoryIds);
		 
		
		return skuDao.selectSkuListByCatAndRegionIdNextCount(map);
	}
	
	/**
	 * 随机取sku列表
	 * @Title: selectRandomSkuList
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月21日 下午3:21:16       
	 * @version: 
	 *
	 * @param pageSize
	 * @param regionId
	 * @return
	 *
	 */
	public List<SkuBO> selectRandomSkuList(Integer pageSize,Integer regionId){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("regionId", regionId);
		map.put("pageSize", pageSize);
		return skuDao.selectRandomSkuList(map);
	}
	
	/**
	 * 查询店铺对应的商品
	 * @Title: selectProductListByStoreId
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月27日 下午7:16:30       
	 * @version: 
	 *
	 * @param storeId   店铺Id
	 * @param pageSize  记录数
	 * @param regionId  地区
	 * @return
	 *
	 */
	public List<SkuBO> selectProductListByStoreId(Integer storeId,Integer pageSize,Integer regionId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("storeId", storeId);
		map.put("pageSize", pageSize);
		map.put("regionId", regionId);
		return skuDao.selectProductListByStoreId(map);
	}
	
	 
	public List<SkuBO> selectProductListByStore(Integer storeId,Integer categoryId,Integer moq ,
			int sortColumn,int sortDirection,int startIndex,int pageSize){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("storeId", storeId);
		map.put("categoryId", categoryId);
		map.put("moq", moq);
		 //排序字段
        if(sortColumn == Constants.PromotionQuerySort.CostPrice.v() ){
			map.put("sortColumn", "b."+Constants.PromotionQuerySort.CostPrice.c());
		}else if(sortColumn ==Constants.PromotionQuerySort.SalesCount.v()   ){
			map.put("sortColumn", "b."+Constants.PromotionQuerySort.SalesCount.c());
		}else if(sortColumn == Constants.PromotionQuerySort.ReleaseTime.v() ){
			map.put("sortColumn", "d."+Constants.PromotionQuerySort.ReleaseTime.c());
		}else {
			map.put("sortColumn", "b."+Constants.PromotionQuerySort.SalesCount.c());
		}
	 
		//升序
		if(sortDirection == Constants.SortDirection.ASC.v()){
			map.put("sortDirection", Constants.SortDirection.ASC.c());
		}else{
			map.put("sortDirection", Constants.SortDirection.DESC.c());
		}
		map.put("startIndex", startIndex);
		map.put("pageSize", pageSize);
		
		return skuDao.selectProductListByStore(map);
		
	} 
	
	public int selectProductListByStoreCount(Integer storeId,Integer categoryId,Integer moq ){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("storeId", storeId);
		map.put("categoryId", categoryId);
		map.put("moq", moq);
		
		return skuDao.selectProductListByStoreCount(map);
		
	} 
	
	/***
	 * 通过goodsId查找到sku
	 */

	public List<Sku> selectByGoodsId(Integer goodsId){
		return skuDao.selectByGoodsId(goodsId);
	}
	
	/**
	 * 逻辑删除
	 */
	public int logicDel(Integer skuId){
		return skuDao.deleteByPrimaryKey(skuId);
	}
}
