
package com.ync365.seed.admin.controller.goods;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ync365.seed.admin.Interceptor.LogAnnotation;
import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.admin.vo.base.PageFilter;
import com.ync365.seed.bussiness.modules.goods.biz.BrandBiz;
import com.ync365.seed.bussiness.modules.goods.biz.BrandCategoryBiz;
import com.ync365.seed.bussiness.modules.goods.biz.CategoryBiz;
import com.ync365.seed.bussiness.modules.goods.entity.Brand;
import com.ync365.seed.bussiness.modules.goods.entity.BrandCategory;
import com.ync365.seed.bussiness.modules.goods.entity.Category;
import com.ync365.seed.web.vo.ZTreeVo;

@Controller
@RequestMapping("/brand")
public class BrandController {
 
	@Autowired
	private BrandBiz brandBiz;

	@Autowired
	private CategoryBiz categoryBiz;

	@Autowired
	BrandCategoryBiz brandCategoryBiz;

	@RequestMapping("/list")
	@LogAnnotation(operatingContent="商品管理模块>品牌管理>展示品牌列表")
	public String list(HttpServletRequest request) {
		return "goods/brandList";
	}

	@RequestMapping("/grid")
	@ResponseBody
	@LogAnnotation(operatingContent="商品管理模块>品牌管理>查询")
	public Grid dataGrid(String qChName, PageFilter filter) {
		Grid grid = new Grid();
		List<Brand> list = new ArrayList<Brand>();
		long count = 0;

		try {
			list = brandBiz.selectPage(qChName, filter.getStartIndex(), filter.getRows());
			count = brandBiz.selectPageCount(qChName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		grid.setRows(list);
		grid.setPageSize(filter.getRows());
		grid.setRecords(count);
		grid.setPage(filter.getPage());
		return grid;
	}

	@RequestMapping("/addPage")
	@LogAnnotation(operatingContent="商品管理模块>品牌管理>添加品牌页面")
	public String addPage(HttpServletRequest request) {
		return "goods/brandAdd";
	}

	@RequestMapping("/add")
	@LogAnnotation(operatingContent="商品管理模块>品牌管理>保存新增品牌")
	public ModelAndView add(Brand brand) {
		try {
			brandBiz.addBrand(brand);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/brand/list");
	}

	@RequestMapping("/editPage")
	@LogAnnotation(operatingContent="商品管理模块>品牌管理>编辑页面")
	public String editPage(String id, HttpServletRequest request) {
		Brand brand;
		try {
			brand = brandBiz.selectById(Integer.parseInt(id));
			request.setAttribute("brand", brand);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/goods/brandEdit";
	}

	@RequestMapping("/edit")
	@LogAnnotation(operatingContent="商品管理模块>品牌管理>展示品牌列表")
	public ModelAndView edit(Brand brand) {
		try {
			brand.setCreateTime(new Timestamp(System.currentTimeMillis()));
			brandBiz.edit(brand);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/brand/list");
	}

	@RequestMapping("/delete")
	@LogAnnotation(operatingContent="商品管理模块>品牌管理>删除品牌")
	public ModelAndView delete(@RequestParam("id") int id) {
		try {
			brandBiz.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/brand/list");
	}

	@RequestMapping("/brandCategory")
	@LogAnnotation(operatingContent="商品管理模块>品牌管理>关联关系页面")
	public String brandCategory(@RequestParam("brandId") int brandId,
			HttpServletRequest request) {
		request.setAttribute("brandId", brandId);
		return "/goods/brandCategory";
	}

	@RequestMapping("/getCategoryTree")
	@ResponseBody
	public List<ZTreeVo> getCategoryTree(HttpServletRequest request,
			Integer brandId) {
		List<ZTreeVo> list = new ArrayList<ZTreeVo>();
		try {
			List<Category> categoryList = categoryBiz.selectCategoryAll();
			for (Category c : categoryList) {
				ZTreeVo vo = new ZTreeVo();
				vo.setId(String.valueOf(c.getCategoryId()));
				vo.setName(c.getName());
				vo.setpId(String.valueOf(c.getParentId()));
				list.add(vo);

			}

			List<BrandCategory> bcList = brandCategoryBiz
					.searchBrandCategoryList(brandId);
			if (bcList != null && bcList.size() > 0) {
				for (BrandCategory bc : bcList) {
					for (ZTreeVo t : list) {
						if (String.valueOf(bc.getCategoryId())
								.equals(t.getId())) {
							System.out.println("------------------"
									+ t.getId().equals(bc.getCategoryId()));
							t.setChecked(true);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = "/addBrandCategory")
	@ResponseBody
	@LogAnnotation(operatingContent="商品管理模块>品牌管理>保存关联关系")
	public ModelAndView addBrandCategory(int brandId, String categoryIds) {

		try {
			if (StringUtils.isNotBlank(categoryIds)) {
				brandCategoryBiz.addBrandCategorys(brandId, categoryIds);
			} else {
				brandCategoryBiz.deleteById(brandId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/brand/list");
	}
	
	@RequestMapping("/getbrandByCategoryId")
	@ResponseBody
	public List<Brand> getbrandByCategoryId(@RequestParam("categoryId") int categoryId){
		List<Brand> brandList =brandBiz.selectBrandbyCategoryId(categoryId);
		 return brandList;
	}
	
	@RequestMapping("/getbrandBybrandId")
	@ResponseBody
	public Brand getbrandBybrandId(@RequestParam("brandId") int brandId){
		return brandBiz.selectById(brandId);
	}
}