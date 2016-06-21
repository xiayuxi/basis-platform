package com.ync365.seed.bussiness.modules.order.dao;

import com.ync365.seed.bussiness.modules.order.entity.OrderMergeInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface OrderMergeInfoMapperManual {

	OrderMergeInfo getOrderMergeInfoByOrderMergeNo(String orderMergeNo);

}
