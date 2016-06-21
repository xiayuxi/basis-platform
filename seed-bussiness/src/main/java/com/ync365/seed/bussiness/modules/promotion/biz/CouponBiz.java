package com.ync365.seed.bussiness.modules.promotion.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.promotion.dao.CouponMapper;
import com.ync365.seed.bussiness.modules.promotion.dao.CouponMapperManual;
import com.ync365.seed.bussiness.modules.promotion.entity.Coupon;
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class CouponBiz {
	@Autowired
	private CouponMapper couponMapper;
	
	@Autowired
	private CouponMapperManual couponMapperManual;
	
	public Coupon selectByPromotionId(int promotionId) {
		
		return couponMapperManual.selectByPromotionId(promotionId);
	}


}
