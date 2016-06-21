package com.ync365.seed.bussiness.modules.promotion.biz;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.promotion.dao.CouponMapper;
import com.ync365.seed.bussiness.modules.promotion.dao.CouponMapperManual;
import com.ync365.seed.bussiness.modules.promotion.dao.PromotionCouponDao;
import com.ync365.seed.bussiness.modules.promotion.dao.PromotionDao;
import com.ync365.seed.bussiness.modules.promotion.entity.Coupon;
import com.ync365.seed.bussiness.modules.promotion.entity.Promotion;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionCoupon;
import com.ync365.seed.utils.Constants;
import com.ync365.seed.utils.UUIDGenerator;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class PromotionCouponBiz {
	@Autowired
	private PromotionDao promotionDao;
	@Autowired
	private PromotionCouponDao promotionCouponDao;	
	@Autowired
	private CouponMapper couponMapper;
	public void create(Promotion promotion, PromotionCoupon promotionCoupon) {
		promotion.setPromotionType(Constants.PromotionType.Coupon.v());
		promotion.setCreateTime(new Date());
		//设置活动状态
		promotion.setStatus(Constants.PromotionStatus.New.v());
		//设置活动是否删除(默认0不删除)
		promotion.setIsDelete(Constants.PromotionIsDelete.Enabled.v());
		//设置活动版本号
		promotion.setVersion(UUIDGenerator.getUUID());		
		promotionDao.insertSelective(promotion);
		
		promotionCoupon.setPromotionId(promotion.getPromotionId());
		promotionCoupon.setIsDelete(Constants.PromotionIsDelete.Enabled.v());		
		promotionCouponDao.insertSelective(promotionCoupon);
	}
	public PromotionCoupon selectByPromotionId(int promotionId) {
		
		return promotionCouponDao.selectByPrimaryKey(promotionId);
	}
	public void update(Promotion promotion, PromotionCoupon promotionCoupon,Coupon coupon) {
		//修改活动版本号
		promotion.setVersion(UUIDGenerator.getUUID());
		//设置活动修改时间
		promotion.setUpdateTime(new Date());
		promotionDao.updateByPrimaryKeySelective(promotion);
		promotionCouponDao.updateByPrimaryKeySelective(promotionCoupon);
		couponMapper.updateByPrimaryKeySelective(coupon);
	}
	public void deleteById(int promotionId) {
		promotionDao.delByIdLogic(promotionId);
		promotionCouponDao.updatePromotionStatusToDelete(promotionId);
		//couponMapperManual.updatePromotionStatusToDelete(promotionId);
	}

}
