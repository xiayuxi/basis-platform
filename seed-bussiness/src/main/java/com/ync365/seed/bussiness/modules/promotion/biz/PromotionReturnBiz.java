package com.ync365.seed.bussiness.modules.promotion.biz;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.promotion.dao.CouponMapper;
import com.ync365.seed.bussiness.modules.promotion.dao.PromotionDao;
import com.ync365.seed.bussiness.modules.promotion.dao.PromotionReturnDao;
import com.ync365.seed.bussiness.modules.promotion.dao.PromotionReturnDetailDao;
import com.ync365.seed.bussiness.modules.promotion.entity.Coupon;
import com.ync365.seed.bussiness.modules.promotion.entity.Promotion;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionReturn;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionReturnDetail;
import com.ync365.seed.utils.Constants;
import com.ync365.seed.utils.UUIDGenerator;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class PromotionReturnBiz {
	@Autowired
	private PromotionDao promotionDao; 
	@Autowired
	private PromotionReturnDao promotionReturnDao;
	@Autowired
	private PromotionReturnDetailDao promotionReturnDetailDao;
	@Autowired
	private CouponMapper couponMapper;
	//创建满返活动
	public void create(Promotion promotion, PromotionReturn promotionReturn,List<PromotionReturnDetail> list) {
		//设置活动类型为满返
		promotion.setPromotionType(Constants.PromotionType.Retrun.v());
		//设置活动创建时间
		promotion.setCreateTime(new Date());
		//设置活动状态
		promotion.setStatus(Constants.PromotionStatus.New.v());		
		//设置活动是否删除(默认0不删除)
		promotion.setIsDelete(Constants.PromotionIsDelete.Enabled.v());
		//设置活动版本号
		promotion.setVersion(UUIDGenerator.getUUID());
		//保存活动
		promotionDao.insertSelective(promotion);
		promotionReturn.setPromotionId(promotion.getPromotionId());
		promotionReturn.setIsDelete(Constants.PromotionIsDelete.Enabled.v());
		//保存满返活动
		promotionReturnDao.insertSelective(promotionReturn);
		//设置满返活动是否删除(默认0不删除)
		if(list!=null&&list.size()>0){
			for (PromotionReturnDetail returnDetail : list) {
				returnDetail.setPromotionId(promotion.getPromotionId());
				if(returnDetail.getCouponAmount()!=null){
					Coupon coupon = new Coupon();
					coupon.setCouponAmount(returnDetail.getCouponAmount());
					//设置红包有效的开始日期
					//coupon.setValidStart(validStart);
					//设置红包有效的结束日期
					coupon.setValidEnd(returnDetail.getValidDate());
					//设置红包的使用数量(每条满返优惠规则中默认为1张)
					//coupon.setCouponUsedCount(1);
					//coupon.setIsDelete(0);
					couponMapper.insertSelective(coupon);					
					returnDetail.setCouponId(coupon.getCouponId());
				}
				returnDetail.setIsDelete(Constants.PromotionIsDelete.Enabled.v());
				//保存满返详情
				promotionReturnDetailDao.insertSelective(returnDetail);
			}
		}
		
	}
	public PromotionReturn selectByPromotionId(int promotionId) {
		// TODO Auto-generated method stub
		return promotionReturnDao.selectByPromotionId(promotionId);
	}
	//修改满返活动
	public void update(Promotion promotion, PromotionReturn pReturn,List<PromotionReturnDetail> detalisList) {
		//修改活动版本号
		promotion.setVersion(UUIDGenerator.getUUID());
		//设置活动修改时间
		promotion.setUpdateTime(new Date());
		promotionDao.updateByPrimaryKeySelective(promotion);
		
		promotionReturnDao.updateByPrimaryKeySelective(pReturn);		
		
		if(detalisList.size()>0){
			promotionReturnDetailDao.updatePromotionStatusToDelete(promotion.getPromotionId());
			for (PromotionReturnDetail returnDetail : detalisList) {
				returnDetail.setPromotionId(promotion.getPromotionId());
				//判断红包(优惠券金额是否修改)
				if(returnDetail.getCouponId()==null||"".equals(returnDetail.getCouponId())){//说明修改了
					Coupon coupon = new Coupon();
					coupon.setCouponAmount(returnDetail.getCouponAmount());
					//设置红包有效的开始日期
					//coupon.setValidStart(validStart);
					//设置红包有效的结束日期
					coupon.setValidEnd(returnDetail.getValidDate());
					//设置红包的使用数量(每条满返优惠规则中默认为1张)
					//coupon.setCouponUsedCount(1);
					//coupon.setIsDelete(0);
					couponMapper.insertSelective(coupon);	
				}
				//新插入数据之前，把满返详情设置为正常状态
				returnDetail.setIsDelete(Constants.PromotionIsDelete.Enabled.v());
				promotionReturnDetailDao.insertSelective(returnDetail);
			}
		}		
	}
	/**
	 * 删除 (逻辑删除)
	 * @param id 
	 */
	@Transactional(readOnly = false)
	public void deleteById(int id) {
		promotionDao.delByIdLogic(id);		
		promotionReturnDao.updatePromotionStatusToDelete(id);			
		promotionReturnDetailDao.updatePromotionStatusToDelete(id);
	}	
	

}
