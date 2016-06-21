package com.ync365.seed.bussiness.modules.promotion.biz;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.ync365.seed.bussiness.modules.promotion.dao.PromotionDao;
import com.ync365.seed.bussiness.modules.promotion.dao.PromotionReduceDao;
import com.ync365.seed.bussiness.modules.promotion.dao.PromotionReduceRegDao;
import com.ync365.seed.bussiness.modules.promotion.entity.Promotion;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionReduce;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionReduceReg;
import com.ync365.seed.utils.Constants;
import com.ync365.seed.utils.UUIDGenerator;






@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class PromotionReduceBiz{

	private static final Logger log =Logger.getLogger(PromotionReduceBiz.class);
	
	@Autowired
	private  PromotionDao  promotionDao;
	
	@Autowired
	private  PromotionReduceDao  promotionReduceDao;
	
	@Autowired
	private  PromotionReduceRegDao  promotionReduceRegDao;
	

	/**
	 * 查找单个 
	 * @param id 
	 * @return PromotionReduce 
	 */
	public PromotionReduce find(Integer promotionId){
		return promotionReduceDao.selectByPrimaryKey(promotionId);	
	}

	
	/**
	 * 创建 
	 * @param po 
	 */
	@Transactional
	public void create(Promotion promotion,PromotionReduce promotionReduce,List<PromotionReduceReg> promotionReduceRegList){
		
		//设置活动类型为满减
		promotion.setPromotionType(Constants.PromotionType.Reduce.v());
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
		
		promotionReduce.setPromotionId(promotion.getPromotionId());
		//设置满减活动是否删除(默认0不删除)
		promotionReduce.setIsDelete(Constants.PromotionIsDelete.Enabled.v());	
		promotionReduceDao.insertSelective(promotionReduce);		
		if(promotionReduceRegList != null && promotionReduceRegList.size() > 0){			
			for(PromotionReduceReg promotionReduceReg : promotionReduceRegList){	
				promotionReduceReg.setPromotionId(promotion.getPromotionId());
				//设置满减活动规则是否删除(默认0不删除)
				promotionReduceReg.setIsDelete(Constants.PromotionIsDelete.Enabled.v());				
				//保存阶梯满减活动规则
				promotionReduceRegDao.insertSelective(promotionReduceReg);
			}
		}
	}

	
	/**
	 * 删除 
	 * @param id 
	 */
	@Transactional(readOnly = false)
	public void deleteById(Integer promotionId) {			
		
		 promotionReduceRegDao.updatePromotionStatusToDelete(promotionId);
		 
		 promotionReduceDao.updatePromotionStatusToDelete(promotionId);
		 
		 promotionDao.delByIdLogic(promotionId);
	}	
	
	/**
	 * 根据活动ID获取活动信息
	 */
	public PromotionReduce selectById(Integer promotionId) {
		return promotionReduceDao.selectByPrimaryKey(promotionId);
	}

	@Transactional(readOnly = false)
	public void update(Promotion promotion, PromotionReduce promotionReduce,List<PromotionReduceReg> regList) {
		if (promotion.getPromotionId() != null){			
			//设置活动修改时间
			promotion.setUpdateTime(new Date());
			//设置活动修改的版本号
			promotion.setVersion(UUIDGenerator.getUUID());
			promotionDao.updateByPrimaryKeySelective(promotion);	
			
			promotionReduceDao.updateByPrimaryKeySelective(promotionReduce);					
			
			if(promotionReduce.getReduceType().byteValue()==Constants.PromotionReduceType.JieTiReduce.v()){					
				//修改阶梯满减活动状态为删除状态
				promotionReduceRegDao.updatePromotionStatusToDelete(promotion.getPromotionId());
				for (PromotionReduceReg reg : regList) {					
					reg.setPromotionId(promotion.getPromotionId());
					reg.setIsDelete(Constants.PromotionIsDelete.Enabled.v());
					promotionReduceRegDao.insertSelective(reg);
				}
				
			}			
		}
		
	}
}