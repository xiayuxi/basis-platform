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
import com.ync365.seed.admin.vo.base.Json;
import com.ync365.seed.admin.vo.base.PageFilter;
import com.ync365.seed.admin.vo.goods.FeatureListVo;
import com.ync365.seed.bussiness.modules.goods.biz.CategoryBiz;
import com.ync365.seed.bussiness.modules.goods.entity.Category;
import com.ync365.seed.web.vo.ZTreeVo;
import com.ync365.seed.web.vo.ZTreeVoList;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryBiz categoryBiz;

	@RequestMapping("/list")
	@LogAnnotation(operatingContent="商品管理模块>分类管理>展示分类列表")
	public String list(HttpServletRequest request) {
		return "goods/categoryList";
	}

	@RequestMapping("/grid")
	@ResponseBody
	@LogAnnotation(operatingContent="商品管理模块>分类管理>查询")
	public Grid dataGrid(String qName, PageFilter filter) {
		Grid grid = new Grid();
		List<Category> list = new ArrayList<Category>();
		// 用于树状展示封装的model
		List<ZTreeVoList> listTree = new ArrayList<ZTreeVoList>();
		long count = 0;
		
		try {
			list = categoryBiz.selectPage(qName, filter.getStartIndex(), filter.getRows());
			// list树状展示 开始封装数据 begin----
			int level = 1;
			for (Category temp : list) {
				ZTreeVoList zTreeVoList = new ZTreeVoList();
				//第一级的
				if (temp.getParentId() != null && temp.getParentId().intValue() == 0) {
					
					zTreeVoList.setCategoryId(temp.getCategoryId());
					zTreeVoList.setName(temp.getName());
					zTreeVoList.setCode(temp.getCode());
					zTreeVoList.setRemark(temp.getRemark());
					zTreeVoList.setKeywords(temp.getKeywords());
					zTreeVoList.setExpanded(true);
					zTreeVoList.setLoaded(true);
					zTreeVoList.setId(String.valueOf(temp.getCategoryId()));
					zTreeVoList.setLevel(0);
					zTreeVoList.setParent(null);
					zTreeVoList.setIsLeaf(false);
					
					listTree.add(zTreeVoList);
					// 查询该分类节点下是否还存在子分类 存在的话进行递归循环 查询数据 插入到 listTree中
					listTree = this.tt(listTree, list, temp.getCategoryId(),
							level);
				}
			}
			// list 数状展示 -----end
			count = categoryBiz.selectPageCount(qName);
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		//搜索时不展示树状
		if(null != qName && "" != qName){
			grid.setRows(list); //此处返回时 不展示树状
			grid.setRecords(count);
			grid.setPageSize(filter.getRows());
			grid.setPage(filter.getPage());
		}else{
			grid.setRows(listTree);
			grid.setRecords(count);
			grid.setPageSize(filter.getRows());
			grid.setPage(filter.getPage());
		}

		return grid;
	}

	/**
	 * 递归查找数据
	 * @param listTree
	 * @param list
	 * @param id
	 * @param level
	 * @return
	 */
	private List<ZTreeVoList> tt(List<ZTreeVoList> listTree,
			List<Category> list, Integer id, int level) {
		// List<Category> listTemp = categoryBiz.getCategoryByParentId(id);
		
		//第一级下面取第二级的 
		if (!list.isEmpty()) {
			for (Category temp : list) {
				ZTreeVoList zTreeVoListTemp = new ZTreeVoList();

				if (null != id && null != temp.getParentId()
						&& temp.getParentId().intValue() == id.intValue()) {

					zTreeVoListTemp.setCategoryId(temp.getCategoryId());
					zTreeVoListTemp.setName(temp.getName());
					zTreeVoListTemp.setCode(temp.getCode());
					zTreeVoListTemp.setRemark(temp.getRemark());
					zTreeVoListTemp.setKeywords(temp.getKeywords());
					zTreeVoListTemp.setExpanded(true);
					zTreeVoListTemp.setLoaded(true);
					zTreeVoListTemp.setId(String.valueOf(temp.getCategoryId()));
					zTreeVoListTemp.setLevel(level);
					zTreeVoListTemp
							.setParent(String.valueOf(temp.getParentId()));
					zTreeVoListTemp.setIsLeaf(true);
					// 用于排除当前分类是否为叶子节点 不是叶子节点的话 修改
					// zTreeVoListTemp.setIsLeaf(false);
					// Integer count =
					// categoryBiz.selectCountByParentId(temp.getCategoryId());
					for (Category te : list) {
						if (null != temp.getCategoryId()
								&& null != te.getParentId()
								&& temp.getCategoryId() == te.getParentId()) {
							zTreeVoListTemp.setIsLeaf(false);
						}
					}
					// 将zTreeVoListTemp对象放入listTree中返回
					listTree.add(zTreeVoListTemp);
					// 用于递归循环时 下一循环是下一等级 需要在 level+1
					level = level + 1;
					listTree = this.tt(listTree, list, temp.getCategoryId(),
							level);
					// 退出递归循环是要等级退回上一级别 level-1
					level = level - 1;
				}

			}
		}
		return listTree;
	}

	/*
	 * @Transactional private int dd(Integer parentId, int level) { int temp =
	 * level; Category category = categoryBiz.selectByPrimaryKey(parentId);
	 * if(category.getParentId()!=0){ int ee = temp +1;
	 * this.dd(category.getParentId(), level); } return temp; }
	 */

	/**
	 * 新增
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	@LogAnnotation(operatingContent="商品管理模块>分类管理>转入新增页面")
	public String addPage(HttpServletRequest request) {
		return "goods/categoryAdd";
	}

	/**
	 *  新增保存
	 * @param category
	 * @return
	 */
	@RequestMapping("/add")
	@LogAnnotation(operatingContent="商品管理模块>分类管理>保存新增分类")
	public ModelAndView add(Category category,FeatureListVo fList) {
		int temp = 0;
		if (null == category.getParentId()) {
			category.setParentId(0);
		}
		try {
			temp = categoryBiz.add(category,fList.getFeatureList());
			categoryBiz.updatePathByCategoryId(temp);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/category/list");
	}

	/**
	 * 编辑
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/editPage")
	@LogAnnotation(operatingContent="商品管理模块>分类管理>编辑页面")
	public String editPage(@RequestParam("id") int id,
			HttpServletRequest request) {
		Category category;
		try {
			category = categoryBiz.selectByPrimaryKey(id);
			if (null != category.getParentId() && 0 != category.getParentId()
					&& category.getCategoryId() > 0) {
				try {
					Category cc = categoryBiz.selectByPrimaryKey(category
							.getParentId());
					category.setPuName(cc.getName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 针对父分类id为null 或者 等于小于0 时 展示“无”
			} else if ((null == category.getParentId())
					|| (null != category.getParentId() && category
							.getParentId() <= 0)) {
				category.setPuName("无");
			}
			request.setAttribute("category", category);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/goods/categoryEdit";
	}

	/**
	 * 编辑保存
	 * @param category
	 * @return
	 */
	@RequestMapping("/edit")
	@LogAnnotation(operatingContent="商品管理模块>分类管理>保存编辑")
	public ModelAndView edit(Category category) {
		try {
			categoryBiz.updateByPrimaryKey(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/category/list");
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@LogAnnotation(operatingContent="商品管理模块>分类管理>删除分类")
	public ModelAndView delete(@RequestParam("id") int id) {
		try {
			categoryBiz.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/category/list");
	}

	/**
	 * 删除时查看此分类下是否有子分类存在
	 * @param id
	 * @return
	 */
	@RequestMapping("/getchild")
	@ResponseBody
	public Json getchild(@RequestParam("id") int id) {
		Json json = new Json();
		int temp = 0;
		try {
			temp = categoryBiz.selectCountByParentId(id);
			if (temp == 0) {
				json.setSuccess(true);
				json.setMsg("ok");
			} else {
				json.setSuccess(false);
				json.setMsg("no");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 分类请求tree 封装成ZTreeVo
	 * @return
	 */
	@RequestMapping("/getree")
	@ResponseBody
	public List<ZTreeVo> allAjax() {
		List<ZTreeVo> list = new ArrayList<ZTreeVo>();

		try {
			List<Category> listCategory = categoryBiz.selectCategoryAll();
			for (Category c : listCategory) {
				ZTreeVo vo = new ZTreeVo();
				vo.setId(String.valueOf(c.getCategoryId()));
				vo.setName(c.getName());
				vo.setpId(String.valueOf(c.getParentId()));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping("/getCategory")
	@ResponseBody
	public String getCategory(@RequestParam("id") int id){
		Category c=categoryBiz.selectByPrimaryKey(id);
		return c.getName();
	}
	
	//三级联动用到此方法，根据parentId查询下级分类
	@RequestMapping("/getCategoryList")
	@ResponseBody
	public List<Category> getCategoryList(Integer id  ) {
		return categoryBiz.getCategoryByParentId(id);
	}
}
