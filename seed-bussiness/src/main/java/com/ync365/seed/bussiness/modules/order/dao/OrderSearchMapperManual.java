package com.ync365.seed.bussiness.modules.order.dao;

import java.util.List;

import com.ync365.seed.bussiness.modules.order.bo.OrderSearch;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface OrderSearchMapperManual {

	List<OrderSearch> getListByMulti(OrderSearch search);

	int getCountByMulti(OrderSearch search);

	OrderSearch getOrderInfoByOrderId(int orderId);

	List<OrderSearch> searchOrderByOrderStatus(OrderSearch search);

	int searchCountOrderByOrderStatus(OrderSearch search);

	List<OrderSearch> searchPopOrder(OrderSearch search);

	int searchPopOrderCount(OrderSearch search);

	OrderSearch getSubOrderInfoByOrderId(int subOrderId);


}
