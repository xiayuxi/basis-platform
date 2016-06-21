package com.ync365.seed.admin.controller.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ync365.seed.bussiness.modules.goods.biz.HoldGoldBiz;
import com.ync365.seed.bussiness.modules.goods.entity.HoldGold;

/**
 * 佣金控制类
 * 
 * @ClassName: HoldGoldController
 * @Description: TODO
 * @author robo
 * @date 2015年9月25日 上午11:30:03
 *
 */
@Controller
@RequestMapping("/holdGold")
public class HoldGoldController {

	@Autowired
	HoldGoldBiz holdGoldBiz ;
	
	
	/**
	 * 根据skuId得到对应的佣金
	 * @Title: getHoldGold
	 * @Description: TODO
	 * @param skuId
	 * @return    
	 * HoldGold    
	 * @throws
	 */
	@RequestMapping("getHoldGold")
	@ResponseBody
	public HoldGold getHoldGold(Integer skuId) {
		return holdGoldBiz.selectByPrimaryKey(skuId);
	}
}
