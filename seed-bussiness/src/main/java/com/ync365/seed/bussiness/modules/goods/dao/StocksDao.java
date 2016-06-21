package com.ync365.seed.bussiness.modules.goods.dao;

import java.util.Map;

import com.ync365.seed.bussiness.modules.goods.entity.Stocks;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface StocksDao {
    int deleteByPrimaryKey(Integer stocksId);

    int insertSelective(Stocks record);

    Stocks selectByPrimaryKey(Integer stocksId);
    
    Stocks selectStocksBySkuId(Integer skuId);

    int updateByPrimaryKeySelective(Stocks record);

    int updateByPrimaryKey(Stocks record);
    
    /**
     * 设置为新的库存
     * @Title: updateStocksNew
     * @Description: TODO
     * @param map
     * @return  int    
     * @throws
     */
    int updateStocksNew(Map<String,Object> map) ;
    
    /**
     * 增加库存
     * @Title: updateStocksAdd
     * @Description: TODO
     * @param map
     * @return int    
     * @throws
     */
    int updateStocksAdd(Map<String,Object> map) ;
    
    /**
     * 减少库存
     * @Title: updateStocksAdd
     * @Description: TODO
     * @param map
     * @return int    
     * @throws
     */
    int updateStocksReduce(Map<String,Object> map);
    
    /**
     * 更新锁定库存
     * @Title: updateStocksLockQty
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月20日 下午3:56:57       
     * @version: 
     *
     * @param stocks
     * @return
     *
     */
    int updateStocksLockQty(Stocks stocks);
    
    /**
     * 订单减库存
     * @Title: updateStocksByOrder
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月20日 下午4:13:20       
     * @version: 
     *
     * @param stocks
     * @return
     *
     */
    int updateStocksNumByOrder(Stocks stocks);
}