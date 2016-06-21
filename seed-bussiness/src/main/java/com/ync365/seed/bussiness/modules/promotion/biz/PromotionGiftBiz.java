package com.ync365.seed.bussiness.modules.promotion.biz;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.promotion.dao.PromotionDao;
import com.ync365.seed.bussiness.modules.promotion.dao.PromotionGiftDao;
import com.ync365.seed.bussiness.modules.promotion.entity.Promotion;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGift;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class PromotionGiftBiz {

	private static final Logger log = Logger.getLogger(PromotionGiftBiz.class);

	@Resource
	private PromotionGiftDao promotionGiftDao;

	@Resource
	private PromotionDao promotionDao;

	/**
	 * 
	 * @Title: create
	 * @Description: TODO    ：    创建同时添加增加活动主表和满减活动表
	 * @author: sunyf    
	 * @date: 2015年9月21日 下午4:55:26       
	 * @version: 
	 *
	 * @param promotion
	 * @param po
	 *
	 */
	@Transactional(readOnly = false)
	public void create(Promotion promotion, PromotionGift po) {

		if (promotion != null)
			promotionDao.insert(promotion);
		po.setPromotionId(promotion.getPromotionId());
		if (po != null)
			promotionGiftDao.insert(po);
	}


	/**
	 * 
	 * @Title: deleteById
	 * @Description: TODO    ：     删除同时添加增加活动主表和满减活动表
	 * @author: sunyf    
	 * @date: 2015年9月21日 下午4:55:38       
	 * @version: 
	 *
	 * @param id
	 *
	 */
	@Transactional(readOnly = false)
	public void deleteById(int id) {
			promotionDao.deleteByPrimaryKey(id);
			promotionGiftDao.deleteByPrimaryKey(id);
	}
	
	/**
	 * 
	 * @Title: selectById
	 * @Description: TODO    ：     查找当个满赠活动信息
	 * @author: sunyf    
	 * @date: 2015年9月21日 下午4:55:47       
	 * @version: 
	 *
	 * @param id
	 * @return
	 *
	 */
	public PromotionGift selectById(Integer id) {
		return promotionGiftDao.selectByPrimaryKey(id);
	}
	/**
	 * 
	 * @Title: update
	 * @Description: TODO    ：    跟新满减活动
	 * @author: sunyf    
	 * @date: 2015年9月21日 下午4:55:57       
	 * @version: 
	 *
	 * @param promotion
	 * @param po
	 *
	 */
	@Transactional(readOnly = false)
	public void update(Promotion promotion, PromotionGift po) {

		if (promotion != null)
			promotionDao.updateByPrimaryKeySelective(promotion);
		po.setPromotionId(promotion.getPromotionId());
		if (po != null)
			promotionGiftDao.updateByPrimaryKeySelective(po);

	}

}