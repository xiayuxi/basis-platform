package com.ync365.seed.admin.controller.goods;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ync365.seed.admin.Interceptor.LogAnnotation;
import com.ync365.seed.admin.vo.base.PageFilter;
import com.ync365.seed.bussiness.modules.goods.biz.CategoryBiz;
import com.ync365.seed.bussiness.modules.goods.biz.GoodsQueryBiz;
import com.ync365.seed.bussiness.modules.goods.entity.Category;
import com.ync365.seed.commons.solr.GoodsQueryBean;
import com.ync365.seed.web.vo.Grid;


@Controller
@RequestMapping("/goodsQuery")
public class GoodsQueryController {

	@Autowired
	GoodsQueryBiz goodsQueryBiz ;
	
	@Autowired
	CategoryBiz categoryBiz;
	
	@RequestMapping("/list")
	@LogAnnotation(operatingContent="商品管理模块>商品查询>商品查询列表页")
	public String list(HttpServletRequest request){
	    
	    /**
         * 第一级分类列表
         */
        List<Category> catList = categoryBiz.getCategoryByParentId(0);
        request.setAttribute("catList", catList);
		
		return "goods/goodsQueryList";
	}
	
	@RequestMapping("/grid")
	@ResponseBody
	@LogAnnotation(operatingContent="商品管理模块>商品查询>查询")
	public Grid  dataGrid(GoodsQueryBean bean,String content,PageFilter filter){
		Grid grid = goodsQueryBiz.searchPage(bean,content, filter.getRows(), filter.getStartIndex()) ;
		return grid;
	}
	
	/*@RequestMapping("/datagrid")
	@ResponseBody
	public Grid dataGrid(PageFilter filter){
	    Grid grid = new Grid();
	    List<Sku> list=new ArrayList<Sku>();
        long count = 0;
        
        try {
            
            //list=skuBiz.selectSkuStockPage(name, subtitle, categoryId, sn, startQty, endQty, filter.getStartIndex(), filter.getRows());
            //count = skuBiz.selectSkuStockPageCount(name, subtitle, categoryId, sn, startQty, endQty);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        grid.setRows(list);
         
        grid.setRecords(count);
        grid.setPageSize(filter.getRows());
        grid.setPage(filter.getPage());
        return grid;
	}*/
}
