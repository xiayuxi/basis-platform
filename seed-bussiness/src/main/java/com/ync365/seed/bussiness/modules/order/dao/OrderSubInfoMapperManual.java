package com.ync365.seed.bussiness.modules.order.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.order.entity.OrderSubInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface OrderSubInfoMapperManual {
		
	int updateSubOrderStatusByOrderId(Map<String, Object> map);

	List<OrderSubInfo> selectSubOrdersBySubIds(List<Integer> list);
	
	OrderSubInfo selectSubOrdersByOrderNo(Map<String, Object> map);

	List<OrderSubInfo> getOrderSubInfoListByOrderInfoId(Integer orderId);

	OrderSubInfo getOrderSubInfoByOrderSubNo(String orderSubNo);
	
}
