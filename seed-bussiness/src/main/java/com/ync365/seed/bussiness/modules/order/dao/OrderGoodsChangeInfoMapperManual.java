package com.ync365.seed.bussiness.modules.order.dao;

import java.util.List;

import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsChangeInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface OrderGoodsChangeInfoMapperManual {

	List<OrderGoodsChangeInfo> getOrderGoodsChangeLogsByOrderGoodsId(Integer goodsId);

	int updateStatusByOrderGoodsId(OrderGoodsChangeInfo orderGoodsChangeInfo);

}
