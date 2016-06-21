package com.ync365.seed.admin.controller.ad;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ync365.seed.admin.controller.base.BaseController;
import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.admin.vo.base.Json;
import com.ync365.seed.admin.vo.base.PageFilter;
import com.ync365.seed.bussiness.modules.ad.biz.ArticleCategoryBiz;
import com.ync365.seed.bussiness.modules.ad.entity.ArticleCategory;

/**
 * 文章分类控制器
 * 
 * @ClassName: ArticleCategoryController
 * @Description: 
 * @author robo
 * @date 2015年9月25日 下午4:16:50
 *
 */
@Controller
@RequestMapping("articleCategory")
public class ArticleCategoryController extends BaseController {

	@Autowired
	ArticleCategoryBiz articleCategoryBiz ;
	
	/**
	 * 跳转到列表页面
	 * @Title: list
	 * @Description:     
	 * @author: liugl    
	 * @date: 2015年9月28日 下午5:51:18       
	 * @version: 
	 *
	 * @param request
	 * @return
	 *
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		return "ad/articleCategoryList";
	}

	@RequestMapping("/grid")
	@ResponseBody
	public Grid dataGrid(String qName, PageFilter filter) {
		Grid grid = new Grid();
		List<ArticleCategory> list = new ArrayList<ArticleCategory>();
		long count = 0;

		try {
			 list = articleCategoryBiz.selectPage(qName, filter.getStartIndex(), filter.getRows());
			 count = articleCategoryBiz.selectPageCount(qName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		grid.setRows(list);
	 
		grid.setRecords(count);
		grid.setPageSize(filter.getRows());
		return grid;
	}

	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
	    try {
            List<ArticleCategory> aCList = articleCategoryBiz.selectArticleCategoryList();
            request.setAttribute("aCList", aCList);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return "ad/articleCategoryAdd";
	}
	
	@RequestMapping("/add")
	public ModelAndView add(ArticleCategory ac){
	    try {
            articleCategoryBiz.insert(ac);
        } catch (Exception e) {
            e.printStackTrace();
        }
	    return new ModelAndView("redirect:/articleCategory/list");
	}
	
   @RequestMapping("/editPage")
    public String editPage(String id, HttpServletRequest request) {
        ArticleCategory articleCategory;
        List<ArticleCategory> aCList;
        try {
            articleCategory = articleCategoryBiz.selectByPrimaryKey(Integer.parseInt(id));
            aCList = articleCategoryBiz.selectArticleCategoryList();
            request.setAttribute("aCList", aCList);
            request.setAttribute("articleCategory", articleCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "ad/articleCategoryEdit";
    }

    @RequestMapping("/edit")
    public ModelAndView edit(ArticleCategory   ac) {
        try {
            articleCategoryBiz.updateByPrimaryKey(ac);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/articleCategory/list");
       
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Json delete(@RequestParam("id") int id) {
        Json json = new Json();
        int temp = 0;
        try {
            //改is_del为0
            temp = articleCategoryBiz.deleteByPrimaryKey(id);
            if (temp == 1) {
                json.setSuccess(true);
                json.setMsg("yes");
            } else if(temp == 2){
                json.setSuccess(false);
                json.setMsg("无法删除正在使用的类别");
            }else {
                json.setSuccess(false);
                json.setMsg("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
