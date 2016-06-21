package com.ync365.seed.service.goods.impl;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ync365.seed.bussiness.modules.goods.biz.BlobBiz;
import com.ync365.seed.bussiness.modules.goods.biz.BrandBiz;
import com.ync365.seed.bussiness.modules.goods.biz.CategoryBiz;
import com.ync365.seed.bussiness.modules.goods.biz.FeatureBiz;
import com.ync365.seed.bussiness.modules.goods.biz.GoodsBiz;
import com.ync365.seed.bussiness.modules.goods.biz.PicInfoBiz;
import com.ync365.seed.bussiness.modules.goods.biz.SaleRegionBiz;
import com.ync365.seed.bussiness.modules.goods.biz.SkuBiz;
import com.ync365.seed.bussiness.modules.goods.bo.SkuBO;
import com.ync365.seed.bussiness.modules.goods.entity.Blob;
import com.ync365.seed.bussiness.modules.goods.entity.Brand;
import com.ync365.seed.bussiness.modules.goods.entity.Category;
import com.ync365.seed.bussiness.modules.goods.entity.PicInfo;
import com.ync365.seed.bussiness.modules.goods.entity.SaleRegion;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysPopStoreBiz;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreBO;
import com.ync365.seed.dto.base.ResponseCode;
import com.ync365.seed.dto.base.ResponseDTO;
import com.ync365.seed.dto.base.ResponseListDTO;
import com.ync365.seed.dto.goods.AttrValue;
import com.ync365.seed.dto.goods.BrowseSearchDTO;
import com.ync365.seed.dto.goods.CatGoodsDTO;
import com.ync365.seed.dto.goods.CategoryDTO;
import com.ync365.seed.dto.goods.FeatureDTO;
import com.ync365.seed.dto.goods.GoodsMemberDTO;
import com.ync365.seed.dto.goods.GoodsSearchDTO;
import com.ync365.seed.dto.goods.SkuDetailDTO;
import com.ync365.seed.dto.goods.StoreGoodsSearchDTO;
import com.ync365.seed.service.goods.GoodsService;
import com.ync365.seed.utils.DateUtils;

public class GoodsServiceImpl implements GoodsService {

	@Autowired
	CategoryBiz categoryBiz ;
	
	@Autowired
	GoodsBiz goodsBiz ;
	
	@Autowired
	BlobBiz blobBiz;
	
	@Autowired
	SkuBiz skuBiz ;
	
	@Autowired
	FeatureBiz featureBiz;
	
	@Autowired
	BrandBiz  brandBiz ;
	
	@Autowired
	PromotionBiz promotionBiz ;
	
	@Autowired
	PicInfoBiz picInfoBiz ;
	
	@Autowired
	SaleRegionBiz saleRegionBiz ;
 
	@Autowired
	SysPopStoreBiz  sysPopStoreBiz ;
	
	public ResponseDTO getCategoryList() {
		ResponseDTO dto = new ResponseDTO() ;
		try{
			List<Category> list = categoryBiz.selectCategoryAll() ;
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
			dto.setData(list);
		}catch(Exception e){
			dto.setCode(1);
		}
		return dto ;
	}

	public ResponseDTO getHotGoodsList(int pageSize,Integer regionId) {
	 
		ResponseDTO dto = new ResponseDTO() ;
		try{
			List<SkuBO> list = skuBiz.selectHotGoodsList(pageSize,regionId);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
			dto.setData(list);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dto;
	}

	 
	public ResponseDTO getNewGoodsList(int pageSize,Integer regionId) {
		ResponseDTO dto = new ResponseDTO() ;
		try{
			List<SkuBO> list = skuBiz.selectNewGoodsList(pageSize,regionId);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
			dto.setData(list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return dto;
	}

	
	public ResponseDTO getGoodsByCategoryList(int pageSize,int categoryId,Integer regionId) {
		
		ResponseDTO dto = new ResponseDTO() ;
		try{
			List<SkuBO> list = skuBiz.selectSkuListByCategoryId(pageSize, categoryId, regionId);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
			dto.setData(list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return dto;
	}

	public ResponseDTO getBrandListByCategoryId(Integer categoryId) {
		
		ResponseDTO dto = new ResponseDTO() ;
		try{
			List<Brand> brandList = brandBiz.selectBrandbyCategoryId(categoryId);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
			dto.setData(brandList);
		}catch(Exception e){
			e.printStackTrace();
			dto.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
			dto.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c() + e.getMessage());
		}
		return dto;
	}
	
	public ResponseDTO getCategoryListByCategoryId(Integer categoryId){
		ResponseDTO dto = new ResponseDTO() ;
		try{
			List<Category> categoryList = categoryBiz.getCategoryByParentId(categoryId);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
			dto.setData(categoryList);
		}catch(Exception e){
			e.printStackTrace();
			dto.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
			dto.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c() + e.getMessage());
		}
		return dto;
	}

	public ResponseDTO getChildCategoryList(Integer categoryId, int layer) {
		ResponseDTO dto = new ResponseDTO() ;
		try{
			List<Category> list = categoryBiz.getCategoryByParentId(categoryId);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
			dto.setData(list);
		}catch(Exception e){
			e.printStackTrace();
			dto.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
			dto.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c() + e.getMessage());
		}
		
		return dto;
	}
	
	/**根据地区得到本周销售明星*/
	public ResponseDTO getWeekStar(Integer regionId){
		ResponseDTO dto = new ResponseDTO() ;
		try{
			Date startDate = DateUtils.getFrontDate(30) ;
			Date endDate = Calendar.getInstance().getTime() ;
			SkuBO skuBO  = skuBiz.selectWeekStar(regionId, startDate, endDate);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
			dto.setData(skuBO);
		}catch(Exception e){
			e.printStackTrace();
			dto.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
			dto.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c() + e.getMessage());
		}
		return dto;
	}
	
	
	public ResponseDTO getCategoryProductList(GoodsSearchDTO dto){
	
		ResponseDTO responseDTO = new ResponseDTO() ;
		try{
			//商品列表
			List<SkuBO>  skuList = skuBiz.selectSkuIndexList(dto.getBrandId(), dto.getCategoryId(), dto.getMoq(), dto.getStartPrice(), dto.getEndPrice(), dto.getSortColumn(), dto.getSortDirection(), dto.getRegionId() ,dto.getPageSize(), dto.getStartIndex());
			int count = skuBiz.selectSkuIndexListCount(dto.getBrandId(), dto.getCategoryId(), dto.getMoq(), dto.getStartPrice(), dto.getEndPrice(),dto.getRegionId());
			
			List<Integer> skuIdsList = new ArrayList<Integer>() ;
			for(SkuBO sku:skuList){
				skuIdsList.add(sku.getSkuId());
			}
			
			List<SkuDetailDTO> dtoList = new ArrayList<SkuDetailDTO>();
			Map<Integer,Integer> activityMap = promotionBiz.findSkuIdBeyondPromotionType(skuIdsList);
			for(SkuBO sku:skuList){
				SkuDetailDTO skuDetailDTO = new SkuDetailDTO() ;
				 BeanUtils.copyProperties(sku, skuDetailDTO);
				 
				 //商品列表里组装对应的活动类型 ：如直降，直减
				 if(activityMap != null ){
					 Integer activityType = activityMap.get(sku.getSkuId());
					 skuDetailDTO.setActivityType(activityType);
				 }
				 
				 dtoList.add(skuDetailDTO);
			}
			
			//组装列表与总记录数
			ResponseListDTO  listDTO = new ResponseListDTO() ;
			listDTO.setDatas(dtoList);
			listDTO.setCount(count);
			
			responseDTO.setCode(ResponseCode.CommonCode.OK.v());
			responseDTO.setMsg(ResponseCode.CommonCode.OK.c());
			responseDTO.setData(listDTO);
		}catch(Exception e){
			e.printStackTrace();
			responseDTO.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
			responseDTO.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c() + e.getMessage());
		}
		
		return responseDTO;
	}
	
	 
	public ResponseDTO getProductListByQuery(GoodsSearchDTO searchDto){
		ResponseDTO dto = new ResponseDTO() ;
		try{
			
			String keywords = searchDto.getKeywords();
			if(StringUtils.isNotBlank(keywords)){
				if("%".equals(keywords.trim())){
					
					dto.setCode(ResponseCode.CommonCode.OK.v());
					dto.setMsg(ResponseCode.CommonCode.OK.c());
					dto.setData(null);
					return dto;
					 
				}else{
					keywords = StringEscapeUtils.escapeSql(keywords);
				}
			}
			
			List<SkuBO>  skuList = skuBiz.selectSkuQueryIndexList(searchDto.getBrandId(), searchDto.getCategoryId(), searchDto.getMoq(), searchDto.getStartPrice(), searchDto.getEndPrice(), searchDto.getSortColumn(), searchDto.getSortDirection(), 
					searchDto.getRegionId() ,keywords  ,searchDto.getPageSize(), searchDto.getStartIndex());
			int count = skuBiz.selectSkuQueryIndexListCount(searchDto.getBrandId(), searchDto.getCategoryId(), searchDto.getMoq(), searchDto.getStartPrice(), searchDto.getEndPrice(),searchDto.getRegionId(),searchDto.getKeywords());
			
			
			List<Integer> skuIdsList = new ArrayList<Integer>() ;
			List<SkuDetailDTO> dtoList = new ArrayList<SkuDetailDTO>();
			if(skuList != null ){
				for(SkuBO sku:skuList){
					skuIdsList.add(sku.getSkuId());
				}
				Map<Integer,Integer> activityMap = promotionBiz.findSkuIdBeyondPromotionType(skuIdsList);
				for(SkuBO sku:skuList){
					SkuDetailDTO skuDetailDTO = new SkuDetailDTO() ;
					 BeanUtils.copyProperties(sku, skuDetailDTO);
					 
					 //商品列表里组装对应的活动类型 ：如直降，直减
					 if(activityMap != null ){
						 Integer activityType = activityMap.get(sku.getSkuId());
						 skuDetailDTO.setActivityType(activityType);
					 }
					 
					 dtoList.add(skuDetailDTO);
				}
			}
 		
			//组装列表与总记录数
			ResponseListDTO  listDTO = new ResponseListDTO() ;
			listDTO.setDatas(dtoList);
			listDTO.setCount(count);
			
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
			dto.setData(listDTO);
		}catch(Exception e){
			e.printStackTrace();
			dto.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
			dto.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
		}
		
		return dto;
	}
	
	 
	public ResponseDTO getCategoryListByQuery(String keywords,Integer regionId){
		ResponseDTO dto = new ResponseDTO() ;
		try{
			if(StringUtils.isNotBlank(keywords)){
				keywords = URLDecoder.decode(keywords);
				if("%".equals(keywords.trim())){
					
					dto.setCode(ResponseCode.CommonCode.OK.v());
					dto.setMsg(ResponseCode.CommonCode.OK.c());
					dto.setData(null);
					return dto;
					 
				}else{
					keywords = StringEscapeUtils.escapeSql(keywords);
				}
			}
			List<Category> categoryList = categoryBiz.selectCategoryListByKeywords(keywords, regionId);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
			dto.setData(categoryList);
		}catch(Exception e){
			e.printStackTrace();
			dto.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
			dto.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
		}
		
		return dto;
	}
	
	 
	public ResponseDTO getBrandListByQuery(String keywords,Integer regionId){
		ResponseDTO dto = new ResponseDTO() ;
		try{
			if(StringUtils.isNotBlank(keywords)){
				keywords = URLDecoder.decode(keywords);
				if("%".equals(keywords.trim())){
					
					dto.setCode(ResponseCode.CommonCode.OK.v());
					dto.setMsg(ResponseCode.CommonCode.OK.c());
					dto.setData(null);
					return dto;
					 
				}else{
					keywords = StringEscapeUtils.escapeSql(keywords);
				}
			}
			
			List<Brand> brandList = brandBiz.selectBrandListByQuery(keywords, regionId);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
			dto.setData(brandList);
		}catch(Exception e){
			e.printStackTrace();
			dto.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
			dto.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
		}
		
		return dto;
	}
	
	public ResponseDTO getBrandListByIds(List<Integer> brandList){
		ResponseDTO dto = new ResponseDTO() ;
		try{
			List<Brand> list = brandBiz.selectBrandListByIds(brandList);
			 
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
			dto.setData(list);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return dto;
	}
	

	public ResponseDTO getManufactorList(Integer regionId,Integer pageSize){
		
		//直营接口
		ResponseDTO dto = new ResponseDTO() ;
		try{
			
			/**
			 * 得到第一级分类，再根据第一级分类，查询对应的所有商品
			 */
			List<Category> cateList = categoryBiz.selectCategoryAll();
			List<Category> rootList = new ArrayList<Category>() ;
			for(Category c:cateList ){
				if(c.getParentId() == 0 ){
					rootList.add(c);
				}
			}
			
			List<CatGoodsDTO> catGoodsList = new ArrayList<CatGoodsDTO>();
			for(Category c:rootList){
				CommonCategoryUtil util = new CommonCategoryUtil() ;
				List<Integer> catList = util.getChildCategorys(cateList, c.getCategoryId());
				
				//查询所有分类对应的商品
				List<SkuBO> goodsList = skuBiz.selectSkuListByCatAndRegionId(regionId, catList,pageSize);
				List<SkuDetailDTO> skuList = new ArrayList<SkuDetailDTO>();
				for(SkuBO skuBO :goodsList){
					SkuDetailDTO skuDetailDTO = new SkuDetailDTO() ;
					BeanUtils.copyProperties(skuBO, skuDetailDTO);
					skuList.add(skuDetailDTO);
				}
				 
				
				/**
				 * 查询到对应的商品列表才显示出这个大类，如果没有不显示 
				 */
				if(goodsList != null && goodsList.size() > 0 ) {
					//返回给前台的一个整个DTO
					CatGoodsDTO cgDto = new CatGoodsDTO()  ;
					//组装分类DTO
					CategoryDTO  catDto = new CategoryDTO();
					catDto.setCategoryId(c.getCategoryId());
					catDto.setCategoryName(c.getName());
					catDto.setParentId(c.getParentId());
					cgDto.setCategoryDTO(catDto);
					
					cgDto.setGoodsList(skuList);
					catGoodsList.add(cgDto);
				}
				
			}
			
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
			dto.setData(catGoodsList);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public ResponseDTO getManufactorListNext(Integer regionId,Integer categoryId,Integer pageSize,Integer pageNum){
		ResponseDTO dto = new ResponseDTO() ;
		try{
			
			/**
			 * 所有分类
			 */
			List<Category> cateList = categoryBiz.selectCategoryAll();
			CommonCategoryUtil util = new CommonCategoryUtil() ;
			List<Integer> catList = util.getChildCategorys(cateList, categoryId);
			//起始位置
			int startIndex = (pageNum - 1) * pageSize;
			
			//查询列表
			List<SkuBO> skuList = skuBiz.selectSkuListByCatAndRegionIdNext(catList, regionId, startIndex, pageSize);
			
			int count = skuBiz.selectSkuListByCatAndRegionIdNextCount(catList, regionId);
			//组装列表与总记录数
			ResponseListDTO  listDTO = new ResponseListDTO() ;
			listDTO.setDatas(skuList);
			listDTO.setCount(count);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
			dto.setData(listDTO);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return dto;
	}
	
	/**测土配肥接口*/
	public ResponseDTO getSoilDistributionList(Integer categoryId,Integer regionId,Integer pageSize){
		
		ResponseDTO dto = new ResponseDTO() ;
		try{
			List<SkuBO> skuList = skuBiz.selectSoilDistributionList(categoryId, regionId,pageSize);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
			dto.setData(skuList);
		}catch(Exception e){
			e.printStackTrace();
			dto.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
			dto.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
		}
		
		return dto;
	}
	
	/**海外购接口*/
	public ResponseDTO getOverseasGoodsList(Integer categoryId,Integer regionId,
			 Integer pageSize){
		ResponseDTO dto = new ResponseDTO() ;
		try{
			List<Integer> categoryList = categoryBiz.selectCategoryListAllByParentId(categoryId);
			List<SkuBO> skuList = skuBiz.selectOverseasGoodsList(categoryList, regionId,pageSize);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
			dto.setData(skuList);
		}catch(Exception e){
			e.printStackTrace();
			dto.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
			dto.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
		}
		return dto;
		
	}
	
	
	/**
	 * 商品详情
	 */
	public ResponseDTO getSkuDetail(Integer skuId) {
		ResponseDTO dto = new ResponseDTO() ;
		try{
			SkuBO sku = skuBiz.selectSkuAndGoodsById(skuId) ;
			if(sku != null ) {
				//根据sku得到对应的规格属性
				List<Map<String,Object>> specList =  featureBiz.selectFeaturesMap(1, skuId);
				
				List<FeatureDTO> featureDTOList = new ArrayList<FeatureDTO>();
				
				for(Iterator it = specList.iterator(); it.hasNext();) {
				    Map<String,Object> map = (Map<String,Object>) it.next();
				    FeatureDTO featureDTO = new FeatureDTO();
				    featureDTO.setFeatureId((Integer)map.get("feature_id"));
				    featureDTO.setFeatureName((String)map.get("name"));
				    
				    String values = (String)map.get("spec_value");
				    List<AttrValue> attrList = new ArrayList<AttrValue>();
				    if(StringUtils.isNotBlank(values)){
				    	String [] vs = values.split(",");
				    	for(String s:vs){
				    		 AttrValue av = new AttrValue() ;
				    		 av.setAttrName(s);
				    		 attrList.add(av);
				    	}
				    }
				    featureDTO.setValueList(attrList);
				    featureDTOList.add(featureDTO);
				}
				
				SkuDetailDTO skuDetailDTO = new SkuDetailDTO();
				BeanUtils.copyProperties(sku, skuDetailDTO);
				
				if(sku != null && sku.getStoreId() != null ){
					PopStoreBO popStoreBo = sysPopStoreBiz.selectPopStoreById(sku.getStoreId());
					
					if(popStoreBo != null ) {
						skuDetailDTO.setStoreName(popStoreBo.getPopStoreName());
						skuDetailDTO.setPopStoreNum(popStoreBo.getPopStoreNum());
					}
					
				}
				
				skuDetailDTO.setSpecList(featureDTOList);
				
				dto.setCode(ResponseCode.CommonCode.OK.v());
				dto.setMsg(ResponseCode.CommonCode.OK.c());
				dto.setData(skuDetailDTO);
			}else {
				dto.setCode(ResponseCode.CommonCode.OBJECT_NOT_EXISTS.v());
				dto.setMsg(ResponseCode.CommonCode.OBJECT_NOT_EXISTS.c());
				dto.setData(null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dto;
	
	}
	
	public ResponseDTO getSaleRegionListBySkuId(Integer skuId){
		ResponseDTO dto = new ResponseDTO() ;
		try{
			List<SaleRegion> list = saleRegionBiz.getSaleRegionsBySkuId(skuId);
			dto.setData(list);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
		}catch(Exception e){
			dto.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
			dto.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c() + e.getMessage());
		}
		return dto;
	}
	
	public ResponseDTO getSaleRegionList(Integer skuId, Integer regionId){
		ResponseDTO dto = new ResponseDTO() ;
		try{
			List<SaleRegion> list = saleRegionBiz.selectSaleRegionListFront(skuId,regionId);
			dto.setData(list);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
		}catch(Exception e){
			dto.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
			dto.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c() + e.getMessage());
		}
		return dto;
	}
	
	public ResponseDTO getGoodsContent(Integer skuId){
		ResponseDTO dto = new ResponseDTO() ;
		try{
			Blob blob = blobBiz.selectBlobByskuId(skuId);
			dto.setData(blob);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
		}catch(Exception e){
			dto.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
			dto.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c() + e.getMessage());
		}
		return dto;
	}
	
	public ResponseDTO getImageList( Integer skuId){
		ResponseDTO dto = new ResponseDTO() ;
		try{
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("skuId", skuId);
			List<PicInfo> picList = picInfoBiz.selectPicInfoByUuid(map);
			dto.setData(picList);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
		}catch(Exception e){
			dto.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
			dto.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c() + e.getMessage());
		}
		return dto;
	}

	@Override
	public ResponseDTO getBrowseRecord(BrowseSearchDTO browseSearchDTO) {
		 
		ResponseDTO dto = new ResponseDTO() ;
		try{
			List<SkuBO> list = skuBiz.getSkuByIdAndRegion(browseSearchDTO.getSkuList(),browseSearchDTO.getRegionId());
			dto.setData(list);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
		}catch(Exception e){
			dto.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
			dto.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c() + e.getMessage());
		}
		return dto;
	}
	
	
	public ResponseDTO getRandomSkuList(Integer pageSize,Integer regionId){
		ResponseDTO dto = new ResponseDTO() ;
		try{
			List<SkuBO> list = skuBiz.selectRandomSkuList(pageSize,regionId);
			dto.setData(list);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
		}catch(Exception e){
			dto.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
			dto.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c() + e.getMessage());
		}
		return dto;
	}
	
	
	public ResponseDTO getProductListByStoreId(Integer storeId,Integer pageSize,Integer regionId) {
		ResponseDTO dto = new ResponseDTO() ;
		try{
			List<SkuBO> list = skuBiz.selectProductListByStoreId(storeId,pageSize,regionId);
			dto.setData(list);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
		}catch(Exception e){
			dto.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
			dto.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c() + e.getMessage());
		}
		return dto;
	}
	
	public ResponseDTO getProductListByStore(StoreGoodsSearchDTO searchDto){
		ResponseDTO dto = new ResponseDTO() ;
		try{
			List<SkuBO> list = skuBiz.selectProductListByStore(searchDto.getStoreId(), searchDto.getCategoryId(), searchDto.getMoq(), searchDto.getSortColumn(), searchDto.getSortDirection(), searchDto.getStartIndex(), searchDto.getPageSize());
			int count = skuBiz.selectProductListByStoreCount(searchDto.getStoreId(), searchDto.getCategoryId(), searchDto.getMoq());
			
			List<Integer> skuIdsList = new ArrayList<Integer>() ;
			for(SkuBO sku:list){
				skuIdsList.add(sku.getSkuId());
			}
			
			List<SkuDetailDTO> dtoList = new ArrayList<SkuDetailDTO>();
			
			Map<Integer,Integer> activityMap = promotionBiz.findSkuIdBeyondPromotionType(skuIdsList);
		
			for(SkuBO sku:list){
				SkuDetailDTO skuDetailDTO = new SkuDetailDTO() ;
				 BeanUtils.copyProperties(sku, skuDetailDTO);
				 
				 //商品列表里组装对应的活动类型 ：如直降，直减
				 if(activityMap != null ){
					 Integer activityType = activityMap.get(sku.getSkuId());
					 skuDetailDTO.setActivityType(activityType);
				 }
				 
				 dtoList.add(skuDetailDTO);
			}
			
			
			ResponseListDTO  listDTO = new ResponseListDTO() ;
			listDTO.setDatas(dtoList);
			listDTO.setCount(count);
			dto.setData(listDTO);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
		}catch(Exception e){
			dto.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
			dto.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c() + e.getMessage());
		}
		return dto;
	}
	
	public ResponseDTO getCategoryListByStore(Integer storeId){
		ResponseDTO dto = new ResponseDTO() ;
		try{
			List<Category> list = categoryBiz.selectCategoryListByStoreId(storeId);
			dto.setData(list);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
		}catch(Exception e){
			dto.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
			dto.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c() + e.getMessage());
		}
		return dto;
	}
	
	public ResponseDTO getCategoryListByIds(List<Integer> categoryIds){
		ResponseDTO dto = new ResponseDTO() ;
		try{
			List<Category> list = categoryBiz.selectCategoryListByIds(categoryIds);
			dto.setData(list);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
		}catch(Exception e){
			dto.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
			dto.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c() + e.getMessage());
		}
		return dto;
	}
	
	
	public ResponseDTO getMemberGoodsList(GoodsMemberDTO memberDTO){
		ResponseDTO dto = new ResponseDTO() ;
		try{
			List<SkuBO> list =  skuBiz.selectProductListByStore(memberDTO.getStoreId(), memberDTO.getCategoryId(), memberDTO.getMoq(), memberDTO.getSortColumn(), memberDTO.getSortDirection(), memberDTO.getStartIndex(), memberDTO.getPageSize());
			int count = skuBiz.selectProductListByStoreCount(memberDTO.getStoreId(), memberDTO.getCategoryId(), memberDTO.getMoq());
			
			ResponseListDTO  listDTO = new ResponseListDTO() ;
			listDTO.setDatas(list);
			listDTO.setCount(count);
			dto.setData(listDTO);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setMsg(ResponseCode.CommonCode.OK.c());
		}catch(Exception e){
			dto.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
			dto.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c() + e.getMessage());
		}
		return dto;
	}

}
