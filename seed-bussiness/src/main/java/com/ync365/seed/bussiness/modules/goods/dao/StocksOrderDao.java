package com.ync365.seed.bussiness.modules.goods.dao;

import java.util.Map;

import com.ync365.seed.bussiness.modules.goods.entity.StocksOrder;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface StocksOrderDao {
    int deleteByPrimaryKey(Integer stocksOrderId);
 
    int insertSelective(StocksOrder record);

    StocksOrder selectByPrimaryKey(Integer stocksOrderId);

    int updateByPrimaryKeySelective(StocksOrder record);

    int updateByPrimaryKey(StocksOrder record);
    
    /**
     * 根据订单号、状态查询对应的库存订单记录表
     * @Title: selectByOrderId
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月20日 下午3:39:06       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    StocksOrder selectByOrderId(Map<String,Object> map);
}