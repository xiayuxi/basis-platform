package com.ync365.seed.bussiness.modules.promotion.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.promotion.dao.PromotionReturnDao;
import com.ync365.seed.bussiness.modules.promotion.dao.PromotionReturnDetailDao;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionReturnDetail;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class PromotionReturnDetailBiz {
	
	@Autowired
	private PromotionReturnDetailDao promotionDetailDao;
	public List<PromotionReturnDetail> selectByPromotionId(int promotionId) {
		// TODO Auto-generated method stub
		return promotionDetailDao.selectByPromotionId(promotionId);
	}

}
