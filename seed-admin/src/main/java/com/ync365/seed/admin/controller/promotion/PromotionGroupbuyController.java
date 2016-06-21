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
import com.ync365.seed.admin.vo.promotion.PromotionGroupbuyRegVo;
import com.ync365.seed.admin.vo.promotion.PromotionGroupbuyVo;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionBiz;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionGroupbuyBiz;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionGroupbuyRegBiz;
import com.ync365.seed.bussiness.modules.promotion.entity.Promotion;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGroupbuy;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGroupbuyReg;
import com.ync365.seed.bussiness.modules.promotion.po.PromotionSearch;
import com.ync365.seed.utils.CloneUtils;
import com.ync365.seed.utils.Constants;

/**
 * 团购的控制层
 * @author lyh
 *
 */
@Controller
@RequestMapping("/promotion/promotionGroupbuy")
public class PromotionGroupbuyController {
	@Autowired
	private PromotionBiz promotionBiz;
	@Autowired
	private PromotionGroupbuyBiz promotionGroupbuyBiz;
	@Autowired
	private PromotionGroupbuyRegBiz promotionGroupbuyRegBiz;
	@InitBinder
	public void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}	
	/** 团购活动列表页面 */
	@RequestMapping("/list")
	public String list() {		
		return "/promotion/promotionGroupbuy/list";
	}

	/** 团购活动列表 */
	@RequestMapping("/grid")
	@ResponseBody
	public Grid dataGrid(PromotionSearch search) {
		
		try {// 查询关键字传到后台中文乱码，所以做一个解码操作。
			search.setKey(URLDecoder.decode(search.getKey(), "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		// 初始化一个列表，用来接收查询结果。
		List<Promotion> list = new ArrayList<Promotion>(search.getPageSize());
		long count = 0;
		try {
			// 设置活动类型
			search.setPromotionType(Constants.PromotionType.Group.v());
			//list = promotionBiz.search(search);// 查询列表。
			count = promotionBiz.searchCount(search);// 查询总条数。
		} catch (Exception e) {
			e.printStackTrace();
		}
		Grid grid = new Grid();
		grid.setRows(list);
		grid.setPage(search.getPageIndex());
		grid.setPageSize(search.getPageSize());
		grid.setRecords(count);

		return grid;
	}
	
	/**
	 * 新增页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		return "/promotion/promotionGroupbuy/add";
	}
	/**
	 * 添加团购活动
	 * @param promotionGroupbuyVo
	 * @return
	 */
	@RequestMapping("add")
	public String addPromotionGroupbuy(PromotionGroupbuyVo promotionGroupbuyVo){
		Promotion promotion = new Promotion();
		PromotionGroupbuy promotionGroupbuy = new PromotionGroupbuy();
		CloneUtils.cloneObject(promotionGroupbuyVo, promotion);
		CloneUtils.cloneObject(promotionGroupbuyVo, promotionGroupbuy);
		
		List<PromotionGroupbuyRegVo> regListVo = promotionGroupbuyVo.getPromotionGroupbuyRegList();
		List<PromotionGroupbuyReg> groupbuyRegList = new ArrayList<PromotionGroupbuyReg>();
		
		if(promotionGroupbuyVo.getGroupbuyType().byteValue()==Constants.PromotionGroupbuyType.MoneyRequire.v()){
			if(regListVo.size()>0){
				for (PromotionGroupbuyRegVo regVo : regListVo) {
					PromotionGroupbuyReg promotionGroupbuyReg = new PromotionGroupbuyReg();
					CloneUtils.cloneObject(regVo,promotionGroupbuyReg);
					groupbuyRegList.add(promotionGroupbuyReg);
				}
			}
		}
		
		promotionGroupbuyBiz.create(promotion,promotionGroupbuy,groupbuyRegList);
		
		return "redirect:list";
	}
	/**
	 * 去修改页
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("editPage")
	public String editPage(String id,Model model){
		PromotionGroupbuyVo promotionGroupbuyVo = new PromotionGroupbuyVo();
		Promotion promotion = promotionBiz.selectById(Integer.parseInt(id));
		PromotionGroupbuy promotionGroupbuy = promotionGroupbuyBiz.selectById(Integer.parseInt(id));
		CloneUtils.cloneObject(promotion, promotionGroupbuyVo);
		CloneUtils.cloneObject(promotionGroupbuy, promotionGroupbuyVo);
		List<PromotionGroupbuyRegVo> regVoList = new ArrayList<PromotionGroupbuyRegVo>();
		
		if(promotionGroupbuyVo.getGroupbuyType().byteValue()==Constants.PromotionGroupbuyType.MoneyRequire.v()){
			List<PromotionGroupbuyReg> regList = promotionGroupbuyRegBiz.selectByPromotionId(Integer.parseInt(id));
			if(regList!=null&&regList.size()>0){
				for (PromotionGroupbuyReg reg : regList) {
					PromotionGroupbuyRegVo promotionGroupbuyRegVo = new PromotionGroupbuyRegVo();
					CloneUtils.cloneObject(reg,promotionGroupbuyRegVo);
					regVoList.add(promotionGroupbuyRegVo);
				}
				promotionGroupbuyVo.setPromotionGroupbuyRegList(regVoList);
			}
		}
		model.addAttribute("promotionGroupbuyVo", promotionGroupbuyVo);
		return "/promotion/promotionGroupbuy/edit";
	}
	/**
	 * 修改团购活动
	 * @param promotionGroupbuyVo
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(PromotionGroupbuyVo promotionGroupbuyVo){
		Promotion promotion = new Promotion();
		PromotionGroupbuy promotionGroupbuy = new PromotionGroupbuy();
		
		promotion.setPromotionType(Constants.PromotionType.Group.v());
		List<PromotionGroupbuyRegVo> regVoList = promotionGroupbuyVo.getPromotionGroupbuyRegList();
		List<PromotionGroupbuyReg> regList = new ArrayList<PromotionGroupbuyReg>();
		if(regVoList.size()>0){
			for (PromotionGroupbuyRegVo regVo : regVoList) {
				PromotionGroupbuyReg reg = new PromotionGroupbuyReg();
				reg.setPromotionId(promotionGroupbuyVo.getPromotionId());
				CloneUtils.cloneObject(regVo, reg);
				regList.add(reg);
			}
		}
		CloneUtils.cloneObject(promotionGroupbuyVo, promotion);
		CloneUtils.cloneObject(promotionGroupbuyVo, promotionGroupbuy);
		promotionGroupbuyBiz.update(promotion,promotionGroupbuy,regList);
		
		return "/promotion/promotionGroupbuy/list";
	}
	/**
	 * 删除团购活动(逻辑删除)
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String deletePromotionReduce(@RequestParam("id") int id){
		String result = "OK";//加一个删除成功的标记
		try {
			promotionGroupbuyBiz.delete(id);		
		
		} catch (Exception e) {
			result = "Failed";
			e.printStackTrace();
		}
		return result;
	}
}
