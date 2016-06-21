package com.ync365.seed.bussiness.modules.promotion.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.promotion.dao.PromotionReduceRegDao;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionReduce;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionReduceReg;






@Service
public class PromotionReduceRegBiz{

	private static final Logger log =Logger.getLogger(PromotionReduceRegBiz.class);
	
	@Resource
	private  PromotionReduceRegDao  promotionReduceRegDao;
	

	

	
	/**
	 * 创建 
	 * @param po 
	 */
	public void create(PromotionReduceReg po) throws Exception{
		
			if( po != null )
				 promotionReduceRegDao.insert(po);
	}

	/**
	 * 修改 
	 * @param po
	 */
	/*public void update(PromotionReduceReg po) throws Exception{

			if( po != null )
				 promotionReduceRegDao.updateByPrimaryKeySelective(po);

	}*/

	/**
	 * 删除 
	 * @param id 
	 */
	/*public void delete(Integer id) throws Exception{
		 promotionReduceRegDao.deleteByPrimaryKey(id);
	}*/
	/**
	 * 根据活动ID获取阶梯满减活动规则列表信息
	 */
	public List<PromotionReduceReg> selectByPromotionId(Integer promotionId) {
		return promotionReduceRegDao.selectByPromotionId(promotionId);
	}
}