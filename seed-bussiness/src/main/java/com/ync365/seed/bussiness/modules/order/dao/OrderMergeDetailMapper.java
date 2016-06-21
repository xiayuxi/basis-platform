package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderMergeDetail;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface OrderMergeDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderMergeDetail record);

    int insertSelective(OrderMergeDetail record);

    OrderMergeDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderMergeDetail record);

    int updateByPrimaryKey(OrderMergeDetail record);
}