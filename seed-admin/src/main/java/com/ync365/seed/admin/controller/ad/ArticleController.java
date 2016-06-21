package com.ync365.seed.admin.controller.ad;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.admin.vo.base.PageFilter;
import com.ync365.seed.bussiness.modules.ad.biz.ArticleBiz;
import com.ync365.seed.bussiness.modules.ad.biz.ArticleCategoryBiz;
import com.ync365.seed.bussiness.modules.ad.biz.ArticleContentBiz;
import com.ync365.seed.bussiness.modules.ad.entity.Article;
import com.ync365.seed.bussiness.modules.ad.entity.ArticleCategory;
import com.ync365.seed.bussiness.modules.ad.entity.ArticleContent;

/**
 * 文章控制器
 * 
 * @ClassName: ArticleController
 * @Description: TODO
 * @author robo
 * @date 2015年9月25日 下午4:18:13
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {


    @Autowired
    ArticleBiz articleBiz ;
    
    @Autowired
    ArticleCategoryBiz articleCategoryBiz;
    
    @Autowired
    ArticleContentBiz articleContentBiz;
    
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
        return "ad/articleList";
    }

    @RequestMapping("/grid")
    @ResponseBody
    public Grid dataGrid(String qName, PageFilter filter) {
        Grid grid = new Grid();
        List<Article> list = null;
        long count = 0;

        try {
             list = articleBiz.selectArticlePageList(qName, filter.getStartIndex(), filter.getRows());
             count = articleBiz.selectArticleListCount(qName, filter.getStartIndex(), filter.getRows());
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
    public String addPage(HttpServletRequest request) {
        try {
            List<ArticleCategory> aCList = articleCategoryBiz.selectArticleCategoryList();
            request.setAttribute("aCList", aCList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ad/articleAdd";
    }
    
    @RequestMapping("/add")
    public String add(Article ac,ArticleContent articleContent){
        try {
            articleBiz.insert(ac,articleContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ad/articleList";
    }


   @RequestMapping("/editPage")
    public String editPage(@RequestParam("articleId") int articleId, HttpServletRequest request,Model model) {
        Article article;
        String content=null;
        List<ArticleCategory> aCList;
        try {
            article = articleBiz.selectByPrimaryKey(articleId);
            content = articleContentBiz.selectByArticleId(articleId);
            aCList = articleCategoryBiz.selectArticleCategoryList();
            request.setAttribute("article", article);
            request.setAttribute("aCList", aCList);
            request.setAttribute("content", content);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ad/articleEdit";
    }
   
   @RequestMapping("/edit")
   public String edit(Article ac,ArticleContent articleContent){
       try {
           articleBiz.updateByPrimaryKey(ac,articleContent);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return "ad/articleList";
   }
   
    @RequestMapping("/delete")
    public ModelAndView delete(@RequestParam("articleId") int articleId) {
        try {
            articleBiz.deleteByArticleId(articleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return new ModelAndView("redirect:/article/list");
      
    }
}
