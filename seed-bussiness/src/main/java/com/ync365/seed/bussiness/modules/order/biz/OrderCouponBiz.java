package com.ync365.seed.bussiness.modules.order.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.order.dao.OrderCouponMapperManual;
import com.ync365.seed.bussiness.modules.order.entity.OrderCoupon;
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class OrderCouponBiz {
	
	@Autowired
	private OrderCouponMapperManual orderCouponMapperManual;
	
	public List <OrderCoupon> selectByPrimaryKey(String orderNo){
	    
	    return orderCouponMapperManual.selectOrderCouponByOrderNo(orderNo);
	}
	
}
