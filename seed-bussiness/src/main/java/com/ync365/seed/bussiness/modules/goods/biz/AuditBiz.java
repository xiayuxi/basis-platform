package com.ync365.seed.bussiness.modules.goods.biz;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.goods.bo.GoodsBO;
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
import com.ync365.seed.bussiness.modules.goods.entity.Sku;
import com.ync365.seed.bussiness.modules.goods.entity.SkuFeature;
import com.ync365.seed.bussiness.modules.user.biz.SysPopStoreBiz;
import com.ync365.seed.bussiness.modules.user.dao.SysAdminInfoMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysAdminInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStore;
import com.ync365.seed.commons.solr.SolrUtil;
import com.ync365.seed.utils.GoodsConstants;
import com.ync365.seed.utils.StringUtils;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreBO;

@Service
public class AuditBiz {
	
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
	SysPopStoreBiz sysPopStoreBiz;
	
	@Autowired
	SysAdminInfoMapper adminInfoMapper;
	
	/**
	 * TODO
	 * 商品审核列表
	 * @param map
	 * @param storeName 
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<GoodsBO> selectAuditPage(Map<String, Object> map, String storeName, int startIndex,
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
		
		gList = goodsDao.selectAuditPage(map);
        for(GoodsBO gb:gList){
            if(gb.getStoreId() !=null){
                PopStoreBO storeBO =  sysPopStoreBiz.selectPopStoreById(gb.getStoreId());
                if(null!=storeBO){
                    String popStoreName=storeBO.getPopStoreName();
                    gb.setPopStoreName(popStoreName);
                }
            }
        }
		return gList;

	}
	
    /**
     * 商品审核列表
     * @param map
     * @param storeName 
     * @param startIndex
     * @param pageSize
     * @return
     */
    public int selectAuditPageCount(Map<String, Object> map, String storeName) {
        if(storeName!=""){
            List<SysPopStore> storeList = sysPopStoreBiz.selectPopStoreByStoreNamePrimary(storeName);
            if(storeList.size()>0){
                map.put("storeList", storeList);
            }else{
                return 0;
            }
        }
        return goodsDao.selectAuditPageCount(map);
    }
    
    public Goods selectGoodsByGoodsId(Integer goodsId) {
        return goodsDao.selectByPrimaryKey(goodsId);
    }
    
    
   
	@Transactional
	public int updateStatus(String skuId,int status){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("skuId", skuId);
		map.put("status", status);
		int update = skuDao.grounding(map) ;
		
		Operate record = new Operate();
		record.setSkuId(Integer.parseInt(skuId));
		if(status==GoodsConstants.GoodsStatus.GROUNDING.v()){
			record.setReleaseTime(new Date());
		}else if(status==GoodsConstants.GoodsStatus.UN_GROUNDING.v()){
			record.setCancelReleaseTime(new Date());
		}
	 
		if(operateDao.selectByPrimaryKey(Integer.parseInt(skuId))!=null){
			update  = operateDao.updateByPrimaryKeySelective(record);
		}else{
			update  = operateDao.insert(record);
		}
		/**
		 * 状态为1并且 更新成功update=1 则建立索引
		 */
		if(GoodsConstants.GoodsStatus.GROUNDING.v()== status  && update == 1 ){
			
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
				e.printStackTrace();
			}
		}
		return update;
		
	}
	
	/**
	 * 商品审核
	 * @param skuId
	 * @param status
	 * @return
	 */
	@Transactional
	public int audit(String goodsId,int status,AdminBO adminBo){
		
		//更新goods表状态
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("goodsId", goodsId);
		map.put("status", status);
		int update =  goodsDao.grounding(map) ;
		
		//写sku表状态
		List<Sku> slist = skuDao.selectByGoodsId(Integer.parseInt(goodsId));
		for (Sku sku : slist) {
		    Map<String,Object> skuMap = new HashMap<String,Object>();
		    skuMap.put("skuId", sku.getSkuId());
		    skuMap.put("status", status);
            skuDao.grounding(skuMap);
        }
		
		//写goods_oprate表
		Operate record = new Operate();
		record.setGoodsId(Integer.parseInt(goodsId));
		record.setAuditTime(new Date(System.currentTimeMillis()));
		record.setAuditUserId(adminBo.getAdminNum());
		//reason这里是假数据
		record.setReason("审核通过！");
		if(operateDao.selectByGoodsId(Integer.parseInt(goodsId))!=null){
			update = operateDao.updateByGoodsIdSelective(record);
		}else{
			update = operateDao.insert(record);
		}
		return  update ;
	}

	@Transactional
	public int disPassAudit(String goodsId, String status, String reason,AdminBO adminBo) {
		//更新goods表状态
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("goodsId", goodsId);
		map.put("status", status);
		int update =  goodsDao.grounding(map) ;
		if(update!=1){
			return update;
		}
		
	    //更新sku表状态
        List<Sku> slist = skuDao.selectByGoodsId(Integer.parseInt(goodsId));
        for (Sku sku : slist) {
            Map<String,Object> skuMap = new HashMap<String,Object>();
            skuMap.put("skuId", sku.getSkuId());
            skuMap.put("status", status);
            skuDao.grounding(skuMap);
        }
        
		//写goods_oprate表
		Operate record = new Operate();
		record.setGoodsId(Integer.parseInt(goodsId));
		record.setAuditTime(new Date(System.currentTimeMillis()));
		record.setAuditUserId(adminBo.getAdminNum());
		record.setReason(reason);
		if(operateDao.selectByGoodsId(Integer.parseInt(goodsId))!=null){
			update = operateDao.updateByGoodsIdSelective(record);
		}else{
			update = operateDao.insert(record);
		}
		return  update ;
	}

	/**
	 * 查询回收站数据分页
	 * @Title: selectRecyclePage
	 * @Description: TODO    ：    
	 * @author: liugl    
	 * @date: 2015年10月12日 上午11:56:37       
	 * @version: 
	 *
	 * @param map
	 * @param storeName 
	 * @param releaseUser 
	 * @param startIndex
	 * @param rows
	 * @return
	 *
	 */
    public List<Goods> selectRecyclePage(Map<String, Object> map, String releaseUser, String storeName, int startIndex, int pageSize) {
        List<Goods> gList = null;
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
        
        gList = goodsDao.selectRecyclePage(map);
        /*for (Goods goods : gList) {
            goods.setPreView(goods.getGoodsId());
        }*/
        return gList;
    }

    /**
     * 查询回收站记录条数
     * @Title: selectRecyclePageCount
     * @Description: TODO    ：    
     * @author: liugl    
     * @date: 2015年10月12日 上午11:56:49    
     * @version: 
     *
     * @param map
     * @param storeName 
     * @param releaseUser 
     * @return`
     *
     */
    public int selectRecyclePageCount(Map<String, Object> map, String releaseUser, String storeName) {
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
        
        return goodsDao.selectRecyclePageCount(map);
    }

    /**
     * 将商品从回收站恢复
     * @Title: recover
     * @Description: TODO    ：    
     * @author: liugl    
     * @date: 2015年10月12日 下午5:20:45       
     * @version: 
     *
     * @param goodsId
     * @return
     *
     */
    @Transactional
    public int recover(String goodsId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("goodsId", goodsId);
        int rtn =  goodsDao.recover(map);
        if(rtn==0){
            return rtn;
        }
        rtn = skuDao.recoverByGoodsId(Integer.parseInt(goodsId));
        
        return rtn;
    }
}
