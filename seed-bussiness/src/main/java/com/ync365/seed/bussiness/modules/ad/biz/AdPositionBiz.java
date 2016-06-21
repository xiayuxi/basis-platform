package com.ync365.seed.bussiness.modules.ad.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.ad.dao.AdPositionDao;
import com.ync365.seed.bussiness.modules.ad.entity.AdPosition;

/**
 * 广告位业务类
 * 
 * @ClassName: AdPositionBiz
 * @Description: 
 * @author robo
 * @date 2015年9月25日 上午11:26:56
 *
 */
@Service
public class AdPositionBiz {

	@Autowired
	AdPositionDao adPositionDao ;
	
	/**
	 * 查询所有广告位列表
	 * @Title: selectAdPositionAllList
	 * @Description: 
	 * @return List<AdPosition>    
	 * @throws
	 */
	public List<AdPosition> selectAdPositionAllList(){
		return adPositionDao.selectAdPositionAllList() ;
	}
}
