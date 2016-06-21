package com.ync365.seed.admin.controller.promotion;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.admin.vo.base.PageFilter;
import com.ync365.seed.admin.vo.promotion.PromotionGiftVo;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionBiz;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionGiftBiz;
import com.ync365.seed.bussiness.modules.promotion.entity.Promotion;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGift;
import com.ync365.seed.bussiness.modules.promotion.po.PromotionSearch;
import com.ync365.seed.bussiness.modules.user.biz.SysAdminBiz;
import com.ync365.seed.utils.CloneUtils;
import com.ync365.seed.utils.Constants;
import com.ync365.seed.utils.UUIDGenerator;

@Controller
@RequestMapping("/promotion/promotionGift")
public class PromotionGiftController {

	@Autowired
	private PromotionGiftBiz promotionGiftBiz;
	@Autowired
	private PromotionBiz promotionBiz;
	@InitBinder
	public void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}		

	// 新增
	@RequestMapping("/add")
	public ModelAndView add(PromotionGiftVo promotionGiftVo, Errors errors) {
		Promotion promotion = new Promotion();
		PromotionGift promotionGift = new PromotionGift();
		
		//数据校验
		
		
//		if (!promotionBiz.checkStartTimeEarlyEndTime(promotion)) {
//			errors.rejectValue("startTime", "活动开始时间不能早于活动结束时间");
//			
//		}
		//补充数据
		
		promotionGiftVo.setCreateTime(new Date());;
		promotionGiftVo.setPromotionType(Constants.PromotionType.Gift.v());
		promotionGiftVo.setIsDelete(Constants.Whether.No.v());
		promotionGiftVo.setIsAllUser(Constants.Whether.Yes.v());
		promotionGiftVo.setIsAllSku(Constants.Whether.Yes.v());
		promotionGiftVo.setStatus(Constants.PromotionStatus.New.v());
		
		 /***sunyftest**/
		promotionGiftVo.setCreateUserId("test");
		promotionGiftVo.setVersion(UUIDGenerator.getUUID());
	    /**sunyftest stop*/
		
		CloneUtils.cloneObject( promotionGiftVo,promotion);
		CloneUtils.cloneObject(promotionGiftVo,promotionGift);
		
		//设置活动类型
		promotionGiftBiz.create(promotion,promotionGift);
		return new ModelAndView("redirect:/promotion/list.html?promotionType=5");
	}
	
	@RequestMapping("/delete")
	public ModelAndView deleteById(@RequestParam("id") int id) {
			promotionGiftBiz.deleteById(id);
		return new ModelAndView("redirect:promotion/list.html?promotionType=5");
		
	} 
	
	@RequestMapping("/editPage")
	public String editPage(String id, String opera,Model model) {
		Promotion promotion;
		PromotionGift promotionGift;
		PromotionGiftVo promotionGiftVo =new PromotionGiftVo();
			promotionGift = promotionGiftBiz.selectById(Integer.parseInt(id));
			promotion = promotionBiz.selectById(Integer.parseInt(id));
			CloneUtils.cloneObject(promotion, promotionGiftVo);
			CloneUtils.cloneObject(promotionGift,promotionGiftVo);
			model.addAttribute("promotionGiftVo", promotionGiftVo);
			model.addAttribute("opera", opera);
		return "/promotion/promotionGift/promotionGiftEdit";
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(PromotionGiftVo promotionGiftVo) {
		Promotion promotion = new Promotion();
		PromotionGift promotionGift = new PromotionGift();
		
		CloneUtils.cloneObject( promotionGiftVo,promotion);
		CloneUtils.cloneObject(promotionGiftVo,promotionGift);		
		promotion.setPromotionType(Constants.PromotionType.Gift.v());		
		promotionGiftBiz.update(promotion,promotionGift);
		
		return new ModelAndView("redirect:/promotionGift/list.html?promotionType=5");	}
}
