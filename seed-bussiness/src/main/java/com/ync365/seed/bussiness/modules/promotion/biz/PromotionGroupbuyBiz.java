package com.ync365.seed.bussiness.modules.promotion.biz;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.promotion.dao.PromotionDao;
import com.ync365.seed.bussiness.modules.promotion.dao.PromotionGroupbuyDao;
import com.ync365.seed.bussiness.modules.promotion.dao.PromotionGroupbuyRegDao;
import com.ync365.seed.bussiness.modules.promotion.entity.Promotion;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGroupbuy;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGroupbuyReg;
import com.ync365.seed.utils.Constants;
import com.ync365.seed.utils.UUIDGenerator;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class PromotionGroupbuyBiz {
	@Autowired
	private PromotionDao promotionDao;
	@Autowired
	private PromotionGroupbuyDao promotionGroupbuyDao;
	@Autowired
	private PromotionGroupbuyRegDao promotionGroupbuyRegDao;
	public void create(Promotion promotion,	PromotionGroupbuy promotionGroupbuy,List<PromotionGroupbuyReg> groupbuyRegList) {
		//设置活动类型,状态，是否删除，活动版本号
		promotion.setPromotionType(Constants.PromotionType.Group.v());
		promotion.setStatus(Constants.PromotionStatus.New.v());
		promotion.setCreateTime(new Date());
		promotion.setIsDelete(Constants.PromotionIsDelete.Enabled.v());
		promotion.setVersion(UUIDGenerator.getUUID());
		promotionDao.insertSelective(promotion);
		
		promotionGroupbuy.setPromotionId(promotion.getPromotionId());
		promotionGroupbuy.setIsDelete(Constants.PromotionIsDelete.Enabled.v());
		promotionGroupbuyDao.insertSelective(promotionGroupbuy);
		
		if(groupbuyRegList!=null&&groupbuyRegList.size()>0){
			for (PromotionGroupbuyReg promotionGroupbuyReg : groupbuyRegList) {
				promotionGroupbuyReg.setPromotionId(promotion.getPromotionId());
				promotionGroupbuyReg.setIsDelete(Constants.PromotionIsDelete.Enabled.v());
				promotionGroupbuyRegDao.insertSelective(promotionGroupbuyReg);
			}
		}
	}
	public PromotionGroupbuy selectById(int promotionId) {
		
		return promotionGroupbuyDao.selectByPrimaryKey(promotionId);
	}
	public void update(Promotion promotion,	PromotionGroupbuy promotionGroupbuy,List<PromotionGroupbuyReg> regList) {
		//设置活动修改时间
		promotion.setUpdateTime(new Date());
		//设置活动修改的版本号
		promotion.setVersion(UUIDGenerator.getUUID());
		promotionDao.updateByPrimaryKeySelective(promotion);
		
		promotionGroupbuyDao.updateByPrimaryKeySelective(promotionGroupbuy);
		
		if(promotionGroupbuy.getGroupbuyType().byteValue()==Constants.PromotionGroupbuyType.MoneyRequire.v()){	
			//修改活动状态为删除状态
			promotionGroupbuyRegDao.updatePromotionStatusToDelete(promotion.getPromotionId());
			for (PromotionGroupbuyReg reg : regList) {
				reg.setPromotionId(promotion.getPromotionId());
				reg.setIsDelete(Constants.PromotionIsDelete.Enabled.v());
				promotionGroupbuyRegDao.insertSelective(reg);
			}
		}
	}
	public void delete(int promotionId) {
		promotionDao.delByIdLogic(promotionId);
		promotionGroupbuyDao.updatePromotionStatusToDelete(promotionId);
		promotionGroupbuyRegDao.updatePromotionStatusToDelete(promotionId);
	}
	
	
}
