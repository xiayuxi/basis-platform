package com.ync365.seed.bussiness.modules.promotion.biz;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.promotion.dao.PromotionDao;
import com.ync365.seed.bussiness.modules.promotion.dao.PromotionSeckillDao;
import com.ync365.seed.bussiness.modules.promotion.entity.Promotion;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionSeckill;
import com.ync365.seed.utils.Constants;
import com.ync365.seed.utils.UUIDGenerator;

@Service
@Transactional
public class PromotionSeckillBiz {
	@Autowired
	private PromotionDao promotionDao;
	@Autowired
	private PromotionSeckillDao promotionSeckillDao;
	public void create(Promotion promotion, PromotionSeckill promotionSeckill) {
		promotion.setPromotionType(Constants.PromotionType.Seckill.v());
		promotion.setCreateTime(new Date());
		//设置活动状态
		promotion.setStatus(Constants.PromotionStatus.New.v());
		//设置活动是否删除(默认0不删除)
		promotion.setIsDelete(Constants.PromotionIsDelete.Enabled.v());
		//设置活动版本号
		promotion.setVersion(UUIDGenerator.getUUID());		
		promotionDao.insertSelective(promotion);
		
		promotionSeckill.setPromotionId(promotion.getPromotionId());
		promotionSeckill.setIsDelete(Constants.PromotionIsDelete.Enabled.v());
		promotionSeckillDao.insertSelective(promotionSeckill);
	}
	public PromotionSeckill selectByPromotionId(int promotionId) {
		return promotionSeckillDao.selectByPrimaryKey(promotionId);		
	}
	public void update(Promotion promotion, PromotionSeckill promotionSeckill) {
		//修改活动版本号
		promotion.setVersion(UUIDGenerator.getUUID());
		//设置活动修改时间
		promotion.setUpdateTime(new Date());
		promotionDao.updateByPrimaryKeySelective(promotion);
		
		promotionSeckillDao.updateByPrimaryKeySelective(promotionSeckill);		
		
	}
	public void deleteById(int promotionId) {
		promotionDao.delByIdLogic(promotionId);
		promotionSeckillDao.updatePromotionStatusToDelete(promotionId);
	}

}
