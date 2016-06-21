package com.ync365.seed.bussiness.modules.promotion.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.promotion.dao.PromotionGroupbuyRegDao;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGroupbuyReg;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class PromotionGroupbuyRegBiz {
	@Autowired
	private PromotionGroupbuyRegDao promotionGroupbuyRegDao;
	//根据活动id查询活动规则
	public List<PromotionGroupbuyReg> selectByPromotionId(int promotionId) {
		
		return promotionGroupbuyRegDao.selectByPromotionId(promotionId);
	}

}
