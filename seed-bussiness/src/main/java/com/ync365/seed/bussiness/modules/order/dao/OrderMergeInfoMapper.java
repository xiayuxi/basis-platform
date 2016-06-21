package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderMergeInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface OrderMergeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderMergeInfo record);

    int insertSelective(OrderMergeInfo record);

    OrderMergeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderMergeInfo record);

    int updateByPrimaryKey(OrderMergeInfo record);
}