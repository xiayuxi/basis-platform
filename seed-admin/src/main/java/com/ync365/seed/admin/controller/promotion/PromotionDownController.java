package com.ync365.seed.admin.controller.promotion;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ync365.seed.admin.utils.LoginUserUtils;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionBiz;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionDownBiz;
import com.ync365.seed.bussiness.modules.promotion.entity.Promotion;
import com.ync365.seed.bussiness.modules.user.biz.SysPopStoreBiz;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreBO;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreSearchBO;
import com.ync365.seed.utils.Constants;

@Controller
@RequestMapping("/promotionDown")
public class PromotionDownController {

	@Autowired
	private PromotionBiz promotionBiz;
	@Autowired
	private SysPopStoreBiz sysPopStoreBiz;
	@Autowired
	private PromotionDownBiz promotionDownBiz;	

	/** 新增直降 */
	@RequestMapping("/add")
	@ResponseBody
	public int add(Promotion promotion,Model model) {
		int res;
		//设置活动创建人
		promotion.setCreateUserNum(LoginUserUtils.getUser().getAdminNum());	
		res = promotionDownBiz.create(promotion);
		model.addAttribute("promotionType", Constants.PromotionType.Down.v());//放活动类型是为了编辑完活动跳转到列表页
		return res;
	}

	/** 根据活动id，进行逻辑删除 */
	@RequestMapping("/delete")
	@ResponseBody
	public int delete(int id) {
		int res;
		res = promotionDownBiz.deleteById(id);
		return res;
	}

	/** 编辑或查看直降页面
	 * @id：活动id
	 * @opera：1编辑 2查看
	 *  */
	@RequestMapping("/editPage")
	public String editPage(int id,String opera,Model model) {
		//获取店铺列表
		List<PopStoreBO> popStoreList = sysPopStoreBiz.selectPageBySearchBO(new PopStoreSearchBO());
		model.addAttribute("popStoreList", popStoreList);		
		Promotion promotion = promotionDownBiz.getById(id);
		model.addAttribute("promotion", promotion);
		model.addAttribute("opera", opera);//放此标志是为了在页面判断该操作是编辑或查看	
		Integer popStoreId = promotion.getStoreId();
		model.addAttribute("popStoreId", popStoreId);
		return "/promotion/promotionDown/edit";
	}
	/** 修改直降活动 */
	@RequestMapping("/edit")
	@ResponseBody
	public int edit(Promotion promotion,Model model) {
		int res = 0;
		//活动修改人
		promotion.setUpdateUserNum(LoginUserUtils.getUser().getAdminNum());	
		res = promotionDownBiz.update(promotion);
		model.addAttribute("promotionType", Constants.PromotionType.Down.v());//放活动类型是为了编辑完活动跳转到列表页
		return res;
	}
	
}
