package com.ync365.seed.bussiness.modules.goods.biz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.common.dao.RegionDao;
import com.ync365.seed.bussiness.modules.goods.bo.GoodsBO;
import com.ync365.seed.bussiness.modules.goods.dao.BlobDao;
import com.ync365.seed.bussiness.modules.goods.dao.CategoryDao;
import com.ync365.seed.bussiness.modules.goods.dao.GoodsDao;
import com.ync365.seed.bussiness.modules.goods.dao.HoldGoldDao;
import com.ync365.seed.bussiness.modules.goods.dao.OperateDao;
import com.ync365.seed.bussiness.modules.goods.dao.SaleRegionDao;
import com.ync365.seed.bussiness.modules.goods.dao.SkuDao;
import com.ync365.seed.bussiness.modules.goods.dao.SkuFeatureDao;
import com.ync365.seed.bussiness.modules.goods.dao.StocksDao;
import com.ync365.seed.bussiness.modules.goods.entity.Blob;
import com.ync365.seed.bussiness.modules.goods.entity.Feature;
import com.ync365.seed.bussiness.modules.goods.entity.Goods;
import com.ync365.seed.bussiness.modules.goods.entity.HoldGold;
import com.ync365.seed.bussiness.modules.goods.entity.Operate;
import com.ync365.seed.bussiness.modules.goods.entity.SaleRegion;
import com.ync365.seed.bussiness.modules.goods.entity.Sku;
import com.ync365.seed.bussiness.modules.goods.entity.SkuFeature;
import com.ync365.seed.bussiness.modules.goods.entity.Stocks;
import com.ync365.seed.bussiness.modules.user.biz.SysPopStoreBiz;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreBO;
import com.ync365.seed.bussiness.modules.user.dao.SysAdminInfoMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysAdminInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStore;
import com.ync365.seed.utils.DateUtils;
import com.ync365.seed.utils.GoodsConstants;
import com.ync365.seed.utils.StringUtils;

@Service
public class GoodsBiz {

	@Autowired
	private GoodsDao goodsDao;

	@Autowired
	private SkuDao skuDao;

	@Autowired
	private SkuFeatureDao skuFeatureDao;
	
	@Autowired
	private HoldGoldDao holdGoldDao ;
	
	@Autowired
	private BlobDao blobDao;
	
	@Autowired
	private StocksDao stocksDao;
	
	@Autowired
	private SaleRegionDao saleRegionDao;
	
	@Autowired
	private RegionDao regionDao;
	
	@Autowired
	private OperateDao operateDao; 
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private SysPopStoreBiz sysPopStoreBiz;
	
	@Autowired
	private SysAdminInfoMapper adminInfoMapper;
	
	
	/**
	 * 功能描述:添加 商品 和 sku
	 * @return
	 * @version 1.0.0
	 * @author 贾昌强
	 */
	@Transactional
	public int addProductAndSku(Goods goods) {
		int n = goodsDao.insertSelective(goods);
		addSkusByGoods(goods);
		return n;
	}
	
	/**
	 * 新增商品
	 * @param goods
	 * @param holdGold
	 * @param blod
	 * @return
	 */
	@Transactional
	public int addGoods(Goods goods,Blob blod,AdminBO adminBo ,HttpServletRequest request){
		
		goods.setOptUser(adminBo.getAdminNum());
		goods.setOptDate(new Date());
		goods.setOptTerm(request.getRemoteAddr());
	    goods.setStatus(GoodsConstants.GoodsStatus.UN_AUDIT.v());
	    goods.setIsDel(GoodsConstants.LogDel.NO.v());
	    
		if(goods.getFertilizerStation()==""){
            goods.setFertilizerStation(null);
        }
		
		if(goods.getBrandId() == -1){
			goods.setBrandId(null);
		}
	      
        if(goods.getIsNew() == null){
            goods.setIsNew(0);
        }
        
        if(goods.getIsHot() == null){
            goods.setIsHot(0);
        }
        
		int n = goodsDao.insertSelective(goods);	
		addSkusByGoods(goods);
		
		/**
		 * 商品详情
		 */
		if(blod!=null){
			blod.setGoodsId(goods.getGoodsId());
			n=blobDao.insert(blod);
		}
		return n;
	}

	private void addSkusByGoods(Goods goods) {
		List<Sku> skuList = goods.getSkus();
		if (skuList == null || skuList.isEmpty()) {
			return;
		}

		for (int i = 0; i < skuList.size(); i++) {
			Sku sku = skuList.get(i);
			
			if(sku.getSalesCount() == null){
				sku.setSalesCount(0l);
			}
			sku.setGoodsId(goods.getGoodsId());
			sku.setGoodsName(goods.getName());
			sku.setGoodsCode(goods.getCode());
			sku.setSubtitle(goods.getSubtitle());
			sku.setStatus(goods.getStatus());/** 启用 */
			sku.setCreateTime(DateUtils.stringToDate(DateUtils.getSystemTime(),
					"yyyy-MM-dd HH:mm:ss"));
			sku.setOptDate(DateUtils.stringToDate(DateUtils.getSystemTime(),
					"yyyy-MM-dd HH:mm:ss"));
			String fullName=goods.getName()+sku.getWeight()+goods.getUnit();
			
			/**
             * 规格 
             */
            for (SkuFeature sf : sku.getSkuFeatures()) {
                sf.setIsSpec(1);
            }
            
            sku.setFullName(fullName);
      
            if (!StringUtils.isNotEmpty(sku.getSn())) {
                continue;
            }
			
			skuDao.insert(sku);
			   
			/***
			 * 把第一个sku的cost_price复制给商品的cost_price
			 * 
			 */
			 if(i==0){
    			 Goods g=goodsDao.selectByPrimaryKey(goods.getGoodsId());
    			 g.setCostPrice(skuList.get(0).getCostPrice());
    			 goodsDao.updateByPrimaryKey(g);
			 }
			/**
			 * 新增佣金
			 */
			HoldGold holdGold=sku.getHoldGold();
			holdGold.setGoodsId(goods.getGoodsId());
			holdGold.setSkuId(sku.getSkuId());
			holdGoldDao.insert(holdGold);
			
			/**
			 * 新增区域
			 */
			List<Integer> regionId=sku.getRegionId();
		 
			if(regionId != null ){
    			for(int x =0 ; x < regionId.size();x++){  
				if(regionId.get(x)!=null){
    			    SaleRegion saleregion=new SaleRegion();
    	            saleregion.setGoodsId(goods.getGoodsId());
    	            saleregion.setRegionId(regionId.get(x));
    	            saleregion.setSkuId(sku.getSkuId());
    	            saleRegionDao.insert(saleregion);
    			}
			}
						  }
			/**
			 * 新增库存,sku对应的库存放如库存表中
			 */
			Stocks stocks = new Stocks();
			stocks.setSkuId(sku.getSkuId());
			stocks.setGoodsId(goods.getGoodsId());
			stocks.setStockNum(sku.getStockNum());
			stocks.setOptDate(new Date());
			stocksDao.insertSelective(stocks);
			
			
			 /**
             * 非规格
             */
            if(goods.getSkuFeatures()!=null){
                sku.getSkuFeatures().addAll(goods.getSkuFeatures());
            }
            
			List<SkuFeature> skuFeatureList = sku.getSkuFeatures();
			if (skuFeatureList != null && !skuFeatureList.isEmpty()) {
				for (SkuFeature skuFeature : skuFeatureList) {
					skuFeature.setSkuId(sku.getSkuId());
					if (skuFeature.getFeatureId() != null
							&& skuFeature.getFeatureId() > 0
							&& StringUtils.isNotEmpty(skuFeature.getSpecValue())) {
						skuFeature.setTid(null);
						skuFeatureDao.insert(skuFeature);
					}
					/*设置sku中的fullName*/
					if(skuFeature.getIsSpec()==1){
					    sku.setFullName(goods.getName()+" "+sku.getWeight()+" "+goods.getUnit());
					}
				}
			}
		}
	}

	/**
	 * 功能描述:修改 商品 和 sku
	 * 
	 * @return
	 * @version 1.0.0
	 * @author 贾昌强
	 */
	@Transactional
	public int updateGoodsAndSku(Goods goods,Blob blob ,AdminBO adminBo,HttpServletRequest request) {
		
		if(goods.getStoreId()!=null){
			if(goods.getStoreId() == -1){
				goods.setStoreId(null);
			}
		}
		
		goods.setOptUser(adminBo.getAdminNum());
		goods.setOptTerm(request.getRemoteAddr());
		goods.setOptDate(new Date());
		
		int n = goodsDao.updateByPrimaryKey(goods);
		blob.setGoodsId(goods.getGoodsId());
		n=blobDao.updateByPrimaryKeySelective(blob);	
		if (goods.getSkus() == null || goods.getSkus().isEmpty()) {
			return 0;
		}
		
		for (Sku sku : goods.getSkus()) {
			if(sku==null){
				continue;
			}
			if (sku.getSkuId()==null||sku.getSkuId() == 0) {
				//原来为物理删除改为逻辑删除
				deleteSkuAndFeatureByGoodId(goods.getGoodsId());
				addSkusByGoods(goods);
			} else {
				if(sku.getSalesCount() == null ){
					sku.setSalesCount(0l);
				}
				sku.setOptDate(DateUtils.stringToDate(DateUtils.getSystemTime(),
						"yyyy-MM-dd HH:mm:ss"));
				sku.setGoodsId(goods.getGoodsId());
				sku.setSubtitle(goods.getSubtitle());
				sku.setGoodsCode(goods.getCode());
				sku.setGoodsName(goods.getName());
				sku.setStatus(GoodsConstants.GoodsStatus.UN_AUDIT.v());
	            sku.setCreateTime(DateUtils.stringToDate(DateUtils.getSystemTime(),
	                    "yyyy-MM-dd HH:mm:ss"));
 	            String fullName=goods.getName()+sku.getWeight()+goods.getUnit();
 	            
 	           /***
 				 * 把第一个sku的cost_price复制给商品的cost_price
 				 * 
 				 */
 			
			 Goods g=goodsDao.selectByPrimaryKey(goods.getGoodsId());
			 g.setCostPrice(goods.getSkus().get(0).getCostPrice());
			 goodsDao.updateByPrimaryKey(g);
 				 
 	            
 	           if(goods.getSkuFeatures()!=null){
                   for(SkuFeature skuFeature:goods.getSkuFeatures()){
                       if(skuFeature.getSpecValue()!=null&&!"".equals(skuFeature.getSpecValue())){
                           skuFeature.setSkuId(sku.getSkuId());
                           if(skuFeature.getTid()!=null){
                               skuFeatureDao.updateByPrimaryKeySelective(skuFeature);
                           }else{
                               skuFeatureDao.insert(skuFeature);
                           }
                       }else{
                           if(skuFeature.getTid()!=null){
                               skuFeatureDao.deleteByPrimaryKey(skuFeature.getTid());
                           }
                       }
                   }
               }
 	           
 	            List<SkuFeature> sfs=sku.getSkuFeatures();
				if (sku.getSkuFeatures() != null && !sku.getSkuFeatures().isEmpty()) {
					for (SkuFeature skuFeature : sfs) {
						skuFeatureDao.updateByPrimaryKeySelective(skuFeature);
					}
				}
				
				sku.setFullName(fullName);
				skuDao.updateByPrimaryKey(sku);
				
				//更新库存
				Stocks st=stocksDao.selectStocksBySkuId(sku.getSkuId());
				st.setStockNum(sku.getStockNum());
				st.setOptDate(new Date());
				stocksDao.updateByPrimaryKeySelective(st);
				
				//更新佣金
				HoldGold holdGold=sku.getHoldGold();
				holdGold.setGoodsId(sku.getGoodsId());
				holdGold.setSkuId(sku.getSkuId());
				holdGoldDao.updateByPrimaryKey(holdGold);
				//更新区域表
				List<Integer> regionList = sku.getRegionId() ;//3 , 22
			
				saleRegionDao.deleteBySkuId(sku.getSkuId());
				if(regionList != null && regionList.size() > 0 ){
				    for(int i = 0 ; i< regionList.size();i++){
			                SaleRegion s=new SaleRegion();
			                s.setGoodsId(goods.getGoodsId());
			                s.setSkuId(sku.getSkuId());
			                s.setRegionId(regionList.get(i));
			                saleRegionDao.insert(s);
				    }
				}
			}
		}

		return n;
	}

	/**
	 * 
	 * 功能描述:根据商品id查询SKU
	 * 
	 * @date 2015年7月30日13:23:36
	 * @return
	 * @version 1.0.0
	 * @author 贾昌强
	 */
	public List<Sku> selectSKUByProductId(Integer goodsId) {
		return skuDao.selectByGoodsId(goodsId);
	}

	/**
	 * 功能描述:商品添加
	 * 
	 * @return
	 * @version 1.0.0
	 * @author 贾昌强
	 */
	public int add(Goods goods) {
		return goodsDao.insert(goods);
	}

	/**
	 * 功能描述:商品修改
	 * 
	 * @return
	 * @version 1.0.0
	 * @author 贾昌强
	 */
	public int update(Goods goods) {
		return goodsDao.updateByPrimaryKeySelective(goods);
	}

	/**
	 * 功能描述:商品SKU修改
	 * 
	 * @return
	 * @version 1.0.0
	 * @author 贾昌强
	 */
	public int updateByProductId(Sku sku) {
		return skuDao.updateByGoodsId(sku);
	}

	/**
	 * 
	 * 功能描述:分页查询
	 * 
	 * @return
	 * @version 1.0.0
	 * @author 贾昌强
	 */
	public List<Goods> selectPage(Map<String, Object> map, int startIndex,
			int pageSize) {
		map.put("pageIndex", startIndex);
		map.put("pageSize", pageSize);
		return goodsDao.selectPage(map);
	}

	/**
	 * 
	 * 功能描述:统计总记录数
	 * 
	 * @return
	 * @version 1.0.0
	 * @author 贾昌强
	 */
	public int selectPageCount(Map<String, Object> map) {
		return goodsDao.selectPageCount(map);
	}

	/**
	 * 
	 * 功能描述:根据商品id查询商品信息
	 * 
	 * @date 2015年7月30日13:23:36
	 * @return
	 * @version 1.0.0
	 * @author 贾昌强
	 */
	public Goods selectByPrimaryKey(int productId) {
		Goods good = goodsDao.selectByPrimaryKey(productId);
		List<Sku> listSkus = skuDao.selectByGoodsId(productId);
		for (Sku sku : listSkus) {
			List<SkuFeature> listSkuFeatures = selectPruFeatureBySkuId(Long
					.valueOf(sku.getSkuId()));
			List<SkuFeature> listSpecFeatures = new ArrayList<SkuFeature>();
			List<SkuFeature> listGoodsFeatures = new ArrayList<SkuFeature>();
			for (SkuFeature skuFeature : listSkuFeatures) {
				if (skuFeature.getIsSpec() == 1) {
					listSpecFeatures.add(skuFeature);
				} else {
					listGoodsFeatures.add(skuFeature);
				}
			}
			sku.setSkuFeatures(listSpecFeatures);
			good.setSkuFeatures(listGoodsFeatures);
		}

		good.setSkus(listSkus);

		return good;
	}

	/**
	 * 
	 * 功能描述:保存商品SKU
	 * 
	 * @date 2015年7月31日09:18:43
	 * @return
	 * @version 1.0.0
	 * @author 贾昌强
	 */
	public int insert(Sku record) {
		return skuDao.insert(record);
	}

	/**
	 * 
	 * 功能描述:根据编号修改商品SKU
	 * 
	 * @date 2015年7月31日09:18:43
	 * @return
	 * @version 1.0.0
	 * @author 贾昌强
	 */
	public int updateByPrimaryKeySelective(Sku record) {
		return skuDao.updateByPrimaryKeySelective(record);
	}

	/**
	 * 
	 * 功能描述:根据编号删除商品SKU
	 * 
	 * @date 2015年7月31日09:18:43
	 * @return
	 * @version 1.0.0
	 * @author 贾昌强
	 */
	public int deleteByPrimaryKey(Integer goodid) {
		deleteSkuAndFeatureByGoodId(goodid);
		return goodsDao.logicDel(goodid);
	}

	/**逻辑删除
	 * 
	 * sku已经逻辑删除，skuFeature查询是根据skuid的，所有
	 * 无需在删除skuFeature
	 * 
	 * @param goodid
	 */
	private void deleteSkuAndFeatureByGoodId(Integer goodid) {
		skuDao.deleteByPrimaryKey(goodid);
	}

	public List<SkuFeature> selectPruFeatureBySkuId(Long skuId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("skuId", skuId);
		return skuFeatureDao.selectListBySkuId(map);
	}

	public List<Map<String, Object>> selectAllFeaturesBySkuId(
			List<Long> skuIdList, int isSpec) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listIds", skuIdList);
		map.put("isSpec", isSpec);
		return skuFeatureDao.selectAllFeaturesBySkuId(map);
	}

	/**
	 * 根据goodsId查询商品下的所有属性id以及属性值
	 * @author xieang
	 * 2015年8月26日
	 * @param goodsId
	 * @param isSpec
	 * @return
	 */
	public List<Feature> selectFeaturesIdByGoodsId(Integer goodsId,Integer isSpec){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("goodsId", goodsId);
		map.put("isSpec", isSpec);
		List<Feature> featureList = skuFeatureDao.selectFeaturesIdByGoodsId(map);
		return featureList;
	}
 
	/***
	 * 
	 * 商品上架
	 */
	@Transactional
	public int groundingFun(String goodsId,Integer status,AdminBO adminBo){
	    //更新goods表状态
	    Map<String ,Object> map=new HashMap<String,Object>();
	    map.put("goodsId", goodsId);
	    map.put("status", status);
	    
	    int update = goodsDao.grounding(map);
	    if(update!=1){
	        return update;
	    }
	    
	    //更新sku表状态
	    List<Sku> sList = skuDao.selectByGoodsId(Integer.parseInt(goodsId));
	    for (Sku sku : sList) {
	        Map<String,Object> skuMap = new HashMap<String,Object>();
            skuMap.put("skuId", sku.getSkuId());
            skuMap.put("status", status);
            update = skuDao.grounding(skuMap);
            if(update!=1){
                return update;
            }
        }
	    
	    //写goods_operate表
	    Operate record = new Operate();
	    record.setGoodsId(Integer.parseInt(goodsId));

	    //状态为3是上架操作，状态为4是下架操作
	    if(status == GoodsConstants.GoodsStatus.GROUNDING.v()){
		    record.setReleaseUserId(adminBo.getAdminNum());
	        record.setReleaseTime(DateUtils.stringToDate(DateUtils.getSystemTime(),
                    "yyyy-MM-dd HH:mm:ss"));
	    }else if(status == GoodsConstants.GoodsStatus.UN_GROUNDING.v()){
            record.setCancelReleaseUserId(adminBo.getAdminNum());
	        record.setCancelReleaseTime(DateUtils.stringToDate(DateUtils.getSystemTime(),
                    "yyyy-MM-dd HH:mm:ss"));
	    }

        if(operateDao.selectByGoodsId(Integer.parseInt(goodsId))!=null){
            update = operateDao.updateByGoodsIdSelective(record);
            if(update!=1){
                return update;
            }
        }else{
            update = operateDao.insert(record);
            if(update!=1){
                return update;
            }
        }
        
	    return update;
	}

	/**
	 * 逻辑删除
	 * @Title: logicDel
	 * @Description: TODO    ：    
	 * @author: guanfl    
	 * @date: 2015年10月12日 下午6:18:01       
	 * @version: 
	 *
	 * @param goodId
	 * @return 
	 *
	 */
    public int logicDel(Integer goodsId) {
        int status = goodsDao.selectByPrimaryKey(goodsId).getStatus();
        if(status == GoodsConstants.GoodsStatus.PASS.v()||status== GoodsConstants.GoodsStatus.GROUNDING.v()){
            return 2;
        }else{
            Map<String ,Object> map=new HashMap<String,Object>();
            map.put("is_del", GoodsConstants.LogDel.YES);
            skuDao.deleteByGoodsId(goodsId);
            goodsDao.logicDel(goodsId);
            return goodsDao.logicDel(goodsId);
        }
    }
    
    /**
     * 商品上下架
     * @param goodsVo 
     */
    public List<GoodsBO> selectReleasePage(Map<String, Object> map, String releaseUser,String storeName , int startIndex,
            int pageSize) {
        List<GoodsBO> gList = null;
        
        map.put("startIndex", startIndex);
        map.put("pageSize", pageSize);
        
        //根据店铺名称查询对应的店铺ID列表
        if(storeName!=""){
            List<SysPopStore> storeList = sysPopStoreBiz.selectPopStoreByStoreNamePrimary(storeName);
            if(storeList.size()>0){
                map.put("storeList", storeList);
            }else{
                return gList;
            }
        }
        
        //根据发布人查询相应的人员
        if(releaseUser!=""){
            List<SysAdminInfo> releaseUserList = adminInfoMapper.selectAdminNumListByAdminName(releaseUser);
            if(releaseUserList.size()>0){
                map.put("releaseUserList", releaseUserList);
            }else{
                return gList;
            }
        }
       
        gList = goodsDao.selectReleasePage(map);
        
        for(GoodsBO gb:gList){
			if (gb.getStoreId() != null) {
				PopStoreBO popStoreBO = sysPopStoreBiz.selectPopStoreById(gb.getStoreId());
				if (popStoreBO != null) {
					gb.setPopStoreName(popStoreBO.getPopStoreName());
				}
			}
        	if(gb.getReleaseUserId()!=null){
	        	String adminNum=gb.getReleaseUserId();
	        	SysAdminInfo sysAdminInfo = adminInfoMapper.selectByPrimaryKey(String.valueOf(adminNum));
	        	if(sysAdminInfo!=null){
	        		gb.setAdminName(sysAdminInfo.getName());
	        	}
        	}
        }
        
        
        return gList;
    }
    
    /***
     * 获得记录条数
     */
    public int selectReleasePageCount(Map<String, Object> map,String storeName,String releaseUser) {      
        //根据店铺名称查询对应的店铺ID列表
        if(storeName!=""){
            List<SysPopStore> storeList = sysPopStoreBiz.selectPopStoreByStoreNamePrimary(storeName);
            if(storeList.size()>0){
                map.put("storeList", storeList);
            }else{
                return 0;
            }
        }
        
        //根据发布人查询相应的人员
        if(releaseUser!=""){
            List<SysAdminInfo> releaseUserList = adminInfoMapper.selectAdminNumListByAdminName(releaseUser);
            if(releaseUserList.size()>0){
                map.put("releaseUserList", releaseUserList);
            }else{
                return 0;
            }
        }
        return goodsDao.selectReleasePageCount(map);
    }
    
    public int validateGoodsCode(String code){
        return goodsDao.selectGoodsByCode(code);
    }
    
    
    /**
     * 店铺商品列表
     * @param goodsVo 
     */
    public List<GoodsBO> selectStoreGoodsListPage(Map<String, Object> map, String releaseUser, int startIndex,
            int pageSize) {
 
        map.put("startIndex", startIndex);
        map.put("pageSize", pageSize);
   
        return goodsDao.selectStoreGoodsListPage(map);
    }
    
    /***
     * 获得记录条数
     */
    public int selectStoreGoodsListPageCount(Map<String, Object> map,String storeName,String releaseUser) {
 
        return goodsDao.selectStoreGoodsListPageCount(map);
    }
}
