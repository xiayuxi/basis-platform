package com.ync365.seed.bussiness.modules.goods.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.goods.bo.SkuBO;
import com.ync365.seed.bussiness.modules.goods.entity.Goods;
import com.ync365.seed.bussiness.modules.goods.entity.Sku;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface SkuDao {
	
	int deleteByPrimaryKey(Integer skuId);

    int insert(Sku record);

    int insertSelective(Sku record);

    /**
     * 查询SKU信息
     * @Title: selectByPrimaryKey
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月13日 下午6:23:29       
     * @version: 
     *
     * @param skuId
     * @return
     *
     */
    Sku selectByPrimaryKey(Integer skuId);
    
    /**
     * 查询sku信息与商品信息
     * @Title: selectSkuAndGoodsById
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月13日 下午6:23:10       
     * @version: 
     *
     * @param skuId
     * @return
     *
     */
    SkuBO selectSkuAndGoodsById(Integer  skuId);

    int updateByPrimaryKeySelective(Sku record);

    int updateByPrimaryKey(Sku record);
 
    List<Sku> selectByGoodsId(Integer goodsId);
   
    int updateByGoodsId(Sku record);
    
    //逻辑删除
    int deleteByGoodsId(Integer goodsId);
    
    List<Sku> searchPage(Map<String ,Object> map);
	
	int searchPageCount(Map<String,Object> map);
	
	int ReleaseSearchPageCount(Map<String,Object> map);
 
	/**
	 * 上下架
	 * @param map
	 * @return
	 */
	int grounding(Map<String,Object> map);

	/**
	 * 审核列表
	 * @param map
	 * @return
	 */
	List<Sku> searchAuditPage(Map<String, Object> map);

	/**
	 * 发布列表
	 * @param map
	 * @return
	 */
	List<Sku> searchReleasePage(Map<String, Object> map);
	
	/**
	 * 根据条件查询sku列表 [促销活动]
	 * @Title: selectSkuPage
	 * @Description: TODO
	 * @param map
	 * @return List<Sku>    
	 * @throws
	 */
	List<Sku> selectSkuPromotionPage(Map<String,Object> map) ;
	
	/**
	 * 根据条件查询SKU列表总记录数 [促销活动]
	 * @Title: selectSkuPageCount
	 * @Description: TODO
	 * @param map
	 * @return int    
	 * @throws
	 */
	int selectSkuPromotionPageCount(Map<String,Object> map);
	
	/**
	 * 根据sku的id集合查询sku列表
	 * @Title: selectSkuListByIds
	 * @Description: TODO
	 * @param list
	 * @return List<Sku>    
	 * @throws
	 */
	List<SkuBO> selectSkuListByIds(List<Integer> list) ;
	
	/**
	 * 查询库存列表
	 * @Title: selectSkuStockPage
	 * @Description: TODO
	 * @param map
	 * @return    
	 * List<Sku>    
	 * @throws
	 */
	List<Sku> selectSkuStockPage(Map<String,Object> map);
	
	/**
	 * 查询库存列表总记录数
	 * @Title: selectSkuStockPageCount
	 * @Description: TODO
	 * @param map
	 * @return int    
	 * @throws
	 */
	int selectSkuStockPageCount(Map<String,Object> map);

	/**
	 * 获得条件查询的记录条数
	 * @Title: searchAuditPageCount
	 * @Description: TODO    ：    
	 * @author: xudawei/14070696    
	 * @date: 2015年9月23日 下午5:55:31       
	 * @version: 
	 *
	 * @param map
	 * @return
	 *
	 */
    int searchAuditPageCount(Map<String, Object> map);
    
    /**
     * 商品列表查询 
     * @Title: selectSkuIndexList
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月10日 下午3:47:18       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    List<SkuBO> selectSkuIndexList(Map<String, Object> map);
    
    /**
     * 商品列表查询总记录数
     * @Title: selectSkuIndexListCount
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月10日 下午3:50:35       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    int  selectSkuIndexListCount(Map<String,Object> map);
    
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
    List<SkuBO> selectSkuQueryIndexList(Map<String,Object> map);
    
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
    int selectSkuQueryIndexListCount(Map<String,Object> map);
    
    /**
     * 根据skuId集合、地区查询对应的sku信息列表
     * @Title: selectSkuByIdAndRegion
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月14日 下午2:36:15       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    List<SkuBO> selectSkuByIdAndRegion(Map<String,Object> map) ;
    
    /**
     * 根据sku集合查询对应的sku信息列表
     * @Title: selectSkuListBySkuIds
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月14日 下午3:03:42       
     * @version: 
     *
     * @param skuList
     * @return
     *
     */
    List<SkuBO> selectSkuListBySkuIds(List<Integer> skuList);
    
    /**
     * 测土配肥查询接口
     * @Title: selectSoilDistributionList
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月15日 下午2:43:25       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    List<SkuBO> selectSoilDistributionList(Map<String,Object> map);
    
    /**
     * 海外购接口
     * @Title: selectOverseasGoodsList
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月15日 下午3:46:41       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    List<SkuBO> selectOverseasGoodsList(Map<String,Object> map);
    
    /**
     * 根据地区查询本周销售明星
     * @Title: selectWeekStar
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月16日 上午10:44:35       
     * @version: 
     *
     * @param map   regionId,startDate,endDate
     * @return
     *
     */
    SkuBO selectWeekStar(Map<String,Object> map);
    
	/**
	 * 查询最热商品列表 [前台首页]
	 * @Title: selectHotGoodsList
	 * @Description: TODO
	 * @param pageSize
	 * @return  List<Goods>    
	 * @throws
	 */
	List<SkuBO> selectHotGoodsList(Map<String,Object> map) ;
	
	/**
	 * 查询最新商品列表 [前台首页]
	 * @Title: selectNewGoodsList
	 * @Description: TODO
	 * @param pageSize
	 * @return  List<Goods>    
	 * @throws
	 */
	List<SkuBO> selectNewGoodsList(Map<String,Object> map) ;
	
	
	/**
	 * 根据分类查询对应的商品 [前台首页]
	 * @Title: getGoodsByCategoryList
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年9月29日 上午11:45:51       
	 * @version: 
	 *
	 * @param map
	 * @return
	 *
	 */
	List<SkuBO> selectSkuListByCategoryId(Map<String,Object> map); 
	
	/**
	 * 根据分类与地区得到厂家直供商品
	 * @Title: selectSkuListByCatAndRegionId
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月17日 上午11:37:23       
	 * @version: 
	 *
	 * @param map
	 * @return
	 *
	 */
	List<SkuBO> selectSkuListByCatAndRegionId(Map<String,Object> map);
	
	/**
	 * 厂家直供更多页面查询
	 * @Title: selectSkuListByCatAndRegionIdPage
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月17日 下午6:46:50       
	 * @version: 
	 *
	 * @param map
	 * @return
	 *
	 */
	List<SkuBO> selectSkuListByCatAndRegionIdNext(Map<String,Object> map);
	
	/**
	 * 厂家直供更多页面查询 记录条数
	 * @Title: selectSkuListByCatAndRegionIdPageCount
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月17日 下午6:47:36       
	 * @version: 
	 *
	 * @param map
	 * @return
	 *
	 */
	int selectSkuListByCatAndRegionIdNextCount(Map<String,Object> map);
	
	/**
	 * 随机取sku数据
	 * @Title: selectRandomSkuList
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月21日 下午2:37:52       
	 * @version: 
	 *
	 * @param pageSize
	 * @return
	 *
	 */
	List<SkuBO> selectRandomSkuList(Map<String,Object> map);
	
	/**
	 * 根据店铺查询对应的商品
	 * @Title: selectProductListByStoreId
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月27日 下午6:56:59       
	 * @version: 
	 *
	 * @param map
	 * @return
	 *
	 */
	List<SkuBO> selectProductListByStoreId(Map<String,Object> map);
	
	/**
	 * 店铺全部商品
	 * @Title: selectProductListByStore
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月27日 下午8:56:00       
	 * @version: 
	 *
	 * @param map
	 * @return
	 *
	 */
	List<SkuBO> selectProductListByStore(Map<String,Object> map);
	
	/**
	 * 店铺全部商品总数
	 * @Title: selectProductListByStoreCount
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月27日 下午9:02:35       
	 * @version: 
	 *
	 * @param map
	 * @return
	 *
	 */
	int selectProductListByStoreCount(Map<String,Object> map);
	
	/**
	 * 根据goodsId从回收站中恢复商品
	 * @Title: recoverByGoodsId
	 * @Description: TODO    ：    
	 * @author: liugl    
	 * @date: 2015年10月26日 下午5:11:28       
	 * @version: 
	 *
	 * @param parseInt
	 * @return 
	 *
	 */
    int recoverByGoodsId(int parseInt);

}