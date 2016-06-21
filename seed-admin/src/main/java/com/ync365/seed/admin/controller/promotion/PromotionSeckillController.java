package com.ync365.seed.admin.controller.promotion;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.admin.vo.promotion.PromotionSeckillVo;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionBiz;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionSeckillBiz;
import com.ync365.seed.bussiness.modules.promotion.entity.Promotion;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionSeckill;
import com.ync365.seed.bussiness.modules.promotion.po.PromotionSearch;
import com.ync365.seed.utils.CloneUtils;
import com.ync365.seed.utils.Constants;

/**
 * 秒杀的控制层
 * @author lyh
 *
 */
@Controller
@RequestMapping("/promotionSeckill")
public class PromotionSeckillController {
	@Autowired
	private PromotionBiz promotionBiz;
	@Autowired
	private PromotionSeckillBiz promotionSeckillBiz;
	@InitBinder
	public void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}
	/**
	 * 创建秒杀活动
	 * @param promotionSeckillVo
	 * @return
	 */
	@RequestMapping("add")
	public String addSeckillPromotion(PromotionSeckillVo promotionSeckillVo,Model model){
		Promotion promotion = new Promotion();
		PromotionSeckill promotionSeckill = new PromotionSeckill();
		CloneUtils.cloneObject(promotionSeckillVo, promotion);
		CloneUtils.cloneObject(promotionSeckillVo, promotionSeckill);
	
		promotionSeckillBiz.create(promotion,promotionSeckill);
		model.addAttribute("promotionType", Constants.PromotionType.Seckill.v());//放活动类型是为了增加完新活动跳转到列表页
		return "/promotion/list";
	}
	/***
	 * 去修改页
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("editPage")
	public String toEdit(String id,String opera,Model model){
		Promotion promotion = promotionBiz.selectById(Integer.parseInt(id));
		PromotionSeckill promotionSeckill = promotionSeckillBiz.selectByPromotionId(Integer.parseInt(id));
		PromotionSeckillVo promotionSeckillVo = new PromotionSeckillVo();
		CloneUtils.cloneObject(promotion, promotionSeckillVo);
		CloneUtils.cloneObject(promotionSeckill, promotionSeckillVo);
		
		model.addAttribute("promotionSeckillVo", promotionSeckillVo);
		model.addAttribute("opera", opera);//放此标志是为了在页面判断该操作是编辑或查看	
		Integer popStoreId = promotion.getStoreId();
		model.addAttribute("popStoreId", popStoreId);
		return "/promotion/promotionSeckill/edit";
	}
	@RequestMapping("edit")
	public String edit(PromotionSeckillVo promotionSeckillVo,Model model){
		Promotion promotion = new Promotion();
		PromotionSeckill promotionSeckill = new PromotionSeckill();
		CloneUtils.cloneObject(promotionSeckillVo, promotion);
		CloneUtils.cloneObject(promotionSeckillVo, promotionSeckill);
		
		promotionSeckillBiz.update(promotion,promotionSeckill);
		model.addAttribute("promotionType", Constants.PromotionType.Seckill.v());//放活动类型是为了增加完新活动跳转到列表页
		return "/promotion/list";
	}
	/**
	 * 删除秒杀活动(逻辑删除)
	 * @param id
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody//此注解的作用是解析返回的result
	public String delete(@RequestParam("id") int id){
		String result = "OK";//加一个删除成功的标记
		try {
			promotionSeckillBiz.deleteById(id);			
		
		} catch (Exception e) {
			result = "Failed";
			e.printStackTrace();
		}
		return result;
	}
}
