package com.ync365.seed.admin.controller.ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ync365.seed.bussiness.modules.ad.biz.AdPositionBiz;
import com.ync365.seed.bussiness.modules.ad.entity.AdPosition;

/**
 * 广告位控制类
 * 
 * @ClassName: AdPositionController
 * @Description: TODO
 * @author robo
 * @date 2015年9月25日 上午11:42:54
 *
 */
@Controller
@RequestMapping("/adPosition")
public class AdPositionController {

    @Autowired
	private AdPositionBiz adPositionBiz;

	/**
	 * 添加
	 */
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addPage(ModelMap model) {
		return "ad/adPositionAdd";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(AdPosition adPosition, RedirectAttributes redirectAttributes) {
		 
		return "redirect:list";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/editPage", method = RequestMethod.GET)
	public String editPage(Integer id, ModelMap model) {
		 
		return "ad/adPositionEdit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(AdPosition adPosition, RedirectAttributes redirectAttributes) {
		 
		return "redirect:list";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list( ModelMap model) {
		 
		return "/ad/adPositionList";
	}

	/**
	 * 删除
	 */
	 
}
