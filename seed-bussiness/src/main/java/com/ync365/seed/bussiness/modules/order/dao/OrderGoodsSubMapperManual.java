package com.ync365.seed.bussiness.modules.order.dao;

import java.util.List;

import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsSub;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface OrderGoodsSubMapperManual {

	List<OrderGoodsSub> getOrderGoodsSubListByOrderSubId(int parseInt);

}
