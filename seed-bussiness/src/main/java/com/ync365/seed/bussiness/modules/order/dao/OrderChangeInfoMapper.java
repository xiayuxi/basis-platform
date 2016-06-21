package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderChangeInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface OrderChangeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderChangeInfo record);

    int insertSelective(OrderChangeInfo record);

    OrderChangeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderChangeInfo record);

    int updateByPrimaryKey(OrderChangeInfo record);
}