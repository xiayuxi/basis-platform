package com.ync365.seed.bussiness.modules.goods.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.goods.dao.HoldGoldDao;
import com.ync365.seed.bussiness.modules.goods.entity.HoldGold;

@Service
public class HoldGoldBiz {

	@Autowired
	HoldGoldDao holdGoldDao ;
	
	public HoldGold selectByPrimaryKey(Integer skuId){
		
		return holdGoldDao.selectByPrimaryKey(skuId);
	}
	
	
	public HoldGold selectBySkuId(Integer skuId){
	    return holdGoldDao.selectBySkuId(skuId);
	}
	
	public int insert(HoldGold holdGold){
	    return holdGoldDao.insert(holdGold);
	}
}
