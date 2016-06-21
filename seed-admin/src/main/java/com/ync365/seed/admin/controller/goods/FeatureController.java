package com.ync365.seed.admin.controller.goods;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ync365.seed.admin.Interceptor.LogAnnotation;
import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.admin.vo.base.PageFilter;
import com.ync365.seed.admin.vo.goods.FeatureVo;
import com.ync365.seed.bussiness.modules.goods.biz.CategoryBiz;
import com.ync365.seed.bussiness.modules.goods.biz.FeatureBiz;
import com.ync365.seed.bussiness.modules.goods.bo.FeatureBO;
import com.ync365.seed.bussiness.modules.goods.entity.Category;
import com.ync365.seed.bussiness.modules.goods.entity.Feature;
import com.ync365.seed.bussiness.modules.goods.entity.FeatureValue;

@Controller
@RequestMapping("/feature")
public class FeatureController {

	@Autowired
	private FeatureBiz featureBiz;
	
	@Autowired
	private CategoryBiz categoryBiz;

	@RequestMapping("/list")
	@LogAnnotation(operatingContent="用户管理模块>属性管理>属性列表页面")
	public String list(HttpServletRequest request) {
		return "goods/featureList";
	}

	@RequestMapping("/grid")
	@ResponseBody
	@LogAnnotation(operatingContent="用户管理模块>属性管理>查询")
	public Grid dataGrid(String qName, PageFilter filter) {
		Grid grid = new Grid();
		List<FeatureBO> list = new ArrayList<FeatureBO>();
		long count = 0;
		try {
			list = featureBiz.selectPageList(qName, filter.getStartIndex(), filter.getRows());
			//System.out.println(list.toString());
			count = featureBiz.selectPageCount(qName);
			//System.out.println(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		grid.setRows(list);
		grid.setRecords(count);
		grid.setPageSize(filter.getRows());
		grid.setPage(filter.getPage());
		return grid;
	}

	@RequestMapping("/getfeature")
	@ResponseBody
	public List<Feature> getFeature(Integer CategoryId) {
		List<Feature> feature = featureBiz.getFeaturesBySpecAndCategoryId(1, CategoryId);
		return feature;
	}

	/* 跳转到添加页面 */
	@RequestMapping("/addPage")
	@LogAnnotation(operatingContent="用户管理模块>属性管理>新增")
	public String addPage(HttpServletRequest request) {
		return "/goods/featureAdd";
	}

	// 新增
	@RequestMapping("/add")
	@LogAnnotation(operatingContent="用户管理模块>属性管理>新增>保存")
	public String add(Feature  feature , FeatureVo vo  ) {
		  
		try {
			featureBiz.add(feature,vo.getfVList(),vo.getCategoryIds());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:list";
	}

	/** 跳转到编辑页面 */
	@RequestMapping("/editPage")
	@LogAnnotation(operatingContent="用户管理模块>属性管理>转至编辑")
	public String editPage(HttpServletRequest request, Integer id) {

		Feature feature = new Feature();
		Category category = null;
		List<FeatureValue> fVList = null;
		List<Category> categoryChain = null;
		try {
			if (null!=id) {
				feature = featureBiz.selectById(id);
				//查找三级联动所需属性
				if(null!=feature.getCategoryId()){
    				category = categoryBiz.selectByPrimaryKey(feature.getCategoryId());
    				categoryChain = categoryBiz.selectCategoryChain(category); 
				}
				//feature.setCategoryIds(categoryIds);
				fVList = featureBiz.getFeatureValueByFeatureId(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		request.setAttribute("feature", feature);
		//request.setAttribute("categroyName", category.getName());
		request.setAttribute("fVList", fVList);
		request.setAttribute("categoryChain", categoryChain);
		return "goods/featureEdit";
	}

	/**
	 * 编辑
	 */
	@RequestMapping("/edit")
	@LogAnnotation(operatingContent="用户管理模块>属性管理>保存编辑")
	public ModelAndView edit(Feature feature,FeatureVo vo) {
		 
		try {
			featureBiz.edit(feature,vo.getfVList(),vo.getCategoryIds());
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return new ModelAndView("redirect:/feature/list");
	}
	
	/* 跳转到添加属性值页面 */
	@RequestMapping("/addValuePage")
	public String addValuePage(HttpServletRequest request, Integer id) {
		return "goods/attr_values";
	}
	

	// 删除
	@RequestMapping("/delete")
	@LogAnnotation(operatingContent="用户管理模块>属性管理>删除")
	public String delete(HttpServletRequest reques,
			@RequestParam("featureId") Integer featureId) {
        try {
            featureBiz.deleteById(featureId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/feature/list";

	}
}



function formatPayAccProfileAcType(row){
	if( row == 1 ){
		return "个人用户";
	}else if( row == 2){
		return "商户账号";
	}else {
		return "";
	}
}
function formatPayAccProfileAcStatus(row){
	//0-正常；1-未激活；2-冻结；9-已销户
	if(row == "0"){
		return "正常";
	}else if(row == "1"){
		return "";
	}else if(row == "2"){
		return "冻结";
	}else if(row == "9"){
		return "已销户";
	}
}

data-options="formatter:formatPayAccProfileAcType"
data-options="formatter:formatPayAccProfileAcStatus"