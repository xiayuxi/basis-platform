package com.ync365.seed.bussiness.modules.order.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.order.entity.OrderOptLog;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface OrderOptLogMapperManual {

	List<OrderOptLog> selectSubListByIds(Integer orderId,Integer orderType);

	List<OrderOptLog> getOrderLogListByIdType(Map<String, Object> map);


}
