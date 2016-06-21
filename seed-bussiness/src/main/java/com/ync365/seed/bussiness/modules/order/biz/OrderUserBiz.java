package com.ync365.seed.bussiness.modules.order.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.order.dao.OrderUserMapperManual;
import com.ync365.seed.bussiness.modules.order.entity.OrderUser;

/**
 * 订单用户的biz
 * @author lyh
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class OrderUserBiz {
	@Autowired
	private OrderUserMapperManual orderUserMapperManual;
	public OrderUser getOrderUserByOrderNo(String orderNo) {		
		return orderUserMapperManual.selectOrderUserByOrderNo(orderNo);
	}

	
}
