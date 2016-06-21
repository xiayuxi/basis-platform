package com.ync365.seed.admin.controller.promotion;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.ync365.seed.admin.utils.LoginUserUtils;
import com.ync365.seed.admin.vo.promotion.PromotionCouponVo;
import com.ync365.seed.bussiness.modules.promotion.biz.CouponBiz;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionBiz;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionCouponBiz;
import com.ync365.seed.bussiness.modules.promotion.entity.Coupon;
import com.ync365.seed.bussiness.modules.promotion.entity.Promotion;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionCoupon;
import com.ync365.seed.bussiness.modules.user.biz.SysPopStoreBiz;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreBO;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreSearchBO;
import com.ync365.seed.utils.CloneUtils;
import com.ync365.seed.utils.Constants;


/**
 * 活动红包的控制层
 * @author lyh
 *
 */
@Controller
@RequestMapping("/promotionCoupon")
public class PromotionCouponController {
	@Autowired
	private PromotionBiz promotionBiz;
	@Autowired
	private PromotionCouponBiz promotionCouponBiz;
	@Autowired
	private CouponBiz couponBiz;
	@Autowired
	private SysPopStoreBiz sysPopStoreBiz;
	@InitBinder
	public void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}	
	
	/**
	 * 创建红包活动
	 * @param promotionCouponVo
	 * @return
	 */
	@RequestMapping("add")
	public String addCouponPromotion(PromotionCouponVo promotionCouponVo,Model model){
		Promotion promotion = new Promotion();
		PromotionCoupon promotionCoupon = new PromotionCoupon();
		CloneUtils.cloneObject(promotionCouponVo, promotion);
		CloneUtils.cloneObject(promotionCouponVo, promotionCoupon);
		promotion.setIsAllSku(promotionCouponVo.getCouponType());
		//设置活动创建人
		promotion.setCreateUserNum(LoginUserUtils.getUser().getAdminNum());		
		promotionCouponBiz.create(promotion,promotionCoupon);
		model.addAttribute("promotionType", Constants.PromotionType.Coupon.v());//放活动类型是为了增加完新活动跳转到列表页
		return "/promotion/list";
	}
	/**
	 * 删除红包活动(逻辑删除)
	 * @param id
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody//此注解的作用是解析返回的result
	public String delete(@RequestParam("id") int id){
		String result = "OK";//加一个删除成功的标记
		try {
			promotionCouponBiz.deleteById(id);			
		
		} catch (Exception e) {
			result = "Failed";
			e.printStackTrace();
		}
		return result;
	}
	
	/***
	 * 去修改页
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("editPage")
	public String toEdit(String id,String opera,Model model){
		//获取店铺列表
		List<PopStoreBO> popStoreList = sysPopStoreBiz.selectPageBySearchBO(new PopStoreSearchBO());
		model.addAttribute("popStoreList", popStoreList);		
		Promotion promotion = promotionBiz.selectById(Integer.parseInt(id));
		PromotionCoupon promotionCoupon = promotionCouponBiz.selectByPromotionId(Integer.parseInt(id));
		//Coupon coupon = couponBiz.selectByPromotionId(Integer.parseInt(id));
		PromotionCouponVo promotionCouponVo = new PromotionCouponVo();
		CloneUtils.cloneObject(promotion, promotionCouponVo);
		CloneUtils.cloneObject(promotionCoupon, promotionCouponVo);
		model.addAttribute("promotionCouponVo", promotionCouponVo);	
		model.addAttribute("opera", opera);//放此标志是为了在页面判断该操作是编辑或查看	
		Integer popStoreId = promotion.getStoreId();
		model.addAttribute("popStoreId", popStoreId);
		return "/promotion/promotionCoupon/edit";
	}
	@RequestMapping("edit")
	public String edit(PromotionCouponVo promotionCouponVo,Model model){
		Promotion promotion = new Promotion();
		PromotionCoupon promotionCoupon = new PromotionCoupon();
		Coupon coupon = new Coupon();
		CloneUtils.cloneObject(promotionCouponVo, promotion);
		CloneUtils.cloneObject(promotionCouponVo, promotionCoupon);
		CloneUtils.cloneObject(promotionCouponVo, coupon);
		promotion.setIsAllSku(promotionCouponVo.getCouponType());
		coupon.setValidStart(promotionCouponVo.getStartTime());
		coupon.setValidEnd(promotionCouponVo.getEndTime());
		//活动修改人
		promotion.setUpdateUserNum(LoginUserUtils.getUser().getAdminNum());	
		promotionCouponBiz.update(promotion,promotionCoupon,coupon);
		model.addAttribute("promotionType", Constants.PromotionType.Coupon.v());//放活动类型是为了编辑完活动跳转到列表页
		return "/promotion/list";
	}	
}
