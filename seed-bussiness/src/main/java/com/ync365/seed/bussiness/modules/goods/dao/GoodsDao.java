package com.ync365.seed.bussiness.modules.goods.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.goods.bo.GoodsBO;
import com.ync365.seed.bussiness.modules.goods.entity.Goods;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface GoodsDao {
	
	//int deleteByPrimaryKey(Integer goodsId);

	int insert(Goods record);

	int insertSelective(Goods record);

	Goods selectByPrimaryKey(Integer productId);

	int updateByPrimaryKeySelective(Goods record);

	int updateByPrimaryKey(Goods record);

	public List<Goods> selectPage(Map<String, Object> map);// 产品管理的分页查询功能

	public int selectPageCount(Map<String, Object> map);
	
 
	/**
     * 审核列表
     * @param map
     * @return
     */
    List<GoodsBO> selectAuditPage(Map<String, Object> map);
    
    /**
     * 获得条件查询的记录条数
     * @Title: selectAuditPageCount
     * @Description: TODO    ：    
     * @author: xudawei/14070696    
     * @date: 2015年9月23日 下午5:55:31       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    int selectAuditPageCount(Map<String, Object> map);
    
    /**
     * 商品上下架列表
     */
    List<GoodsBO> selectReleasePage(Map<String ,Object> map);
    
     /***
      * 获得条件查询的记录条数
      * @Title: selectReleasePageCount
      * @Description: TODO    ：    
      * @author: guanfl    
      * @date: 2015年10月11日 下午2:52:26       
      * @version: 
      *
      * @param map
      * @return
      *
      */
    int selectReleasePageCount(Map<String ,Object > map); 

    /**
     * 商品审核（上下架）
     * @param map
     * @return
     */
    int grounding(Map<String,Object> map);
 
    /**
     * 商品回收站条件查询列表
     * @Title: selectRecyclePage
     * @Description: TODO    ： 
     * @author: liugl    
     * @date: 2015年10月12日 下午1:50:03       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    List<Goods> selectRecyclePage(Map<String, Object> map);

    /**
     * 商品回收站条件查询记录条数
     * @Title: selectRecyclePageCount
     * @Description: TODO    ：    
     * @author: liugl    
     * @date: 2015年10月12日 下午1:52:09       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    int selectRecyclePageCount(Map<String, Object> map);

    /**
     * 商品从回收站恢复is_Del改为0
     * @Title: recover
     * @Description: TODO    ：    
     * @author: liugl    
     * @date: 2015年10月12日 下午5:28:00       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    int recover(Map<String, Object> map);

    /**
     * 
     * 逻辑删除
     * @Title: logicDel
     * @Description: TODO    ：    
     * @author: guanfl    
     * @date: 2015年10月12日 下午6:18:58       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    int logicDel(Integer goodsId);

    int selectGoodsByCode(String code);
    
    /**
     * 店铺商品列表
     * @Title: selectStoreGoodsListPage
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月31日 下午8:05:16       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    List<GoodsBO> selectStoreGoodsListPage(Map<String, Object> map);
    
    
    int selectStoreGoodsListPageCount(Map<String,Object> map);

}