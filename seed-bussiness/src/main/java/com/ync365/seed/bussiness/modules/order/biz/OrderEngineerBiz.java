package com.ync365.seed.bussiness.modules.order.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.order.dao.OrderEngineerMapper;
import com.ync365.seed.bussiness.modules.order.entity.OrderEngineer;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class OrderEngineerBiz {

	private static final Logger logger = LoggerFactory.getLogger(OrderEngineerBiz.class);
	
	@Autowired
	private OrderEngineerMapper orderEngineerMapper;
	
	public OrderEngineer selectByOrderId(Integer orderId){
		return orderEngineerMapper.selectByPrimaryKey(orderId);
	}
	
	
}
