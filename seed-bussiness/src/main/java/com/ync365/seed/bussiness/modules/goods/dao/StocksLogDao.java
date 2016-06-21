package com.ync365.seed.bussiness.modules.goods.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.goods.entity.StocksLog;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface StocksLogDao {
    int deleteByPrimaryKey(Integer logId);

    int insert(StocksLog record);

    int insertSelective(StocksLog record);

    StocksLog selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(StocksLog record);

    int updateByPrimaryKey(StocksLog record);
    
    List<StocksLog> selectPage(Map<String , Object> map);
    
    int selectPageCount();
}