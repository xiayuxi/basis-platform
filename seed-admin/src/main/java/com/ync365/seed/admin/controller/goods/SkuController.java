package com.ync365.seed.admin.controller.goods;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ync365.seed.admin.Interceptor.LogAnnotation;
import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.admin.vo.base.Json;
import com.ync365.seed.admin.vo.base.PageFilter;
import com.ync365.seed.bussiness.modules.goods.biz.BrandBiz;
import com.ync365.seed.bussiness.modules.goods.biz.CategoryBiz;
import com.ync365.seed.bussiness.modules.goods.biz.SkuBiz;
import com.ync365.seed.bussiness.modules.goods.biz.StocksBiz;
import com.ync365.seed.bussiness.modules.goods.entity.Category;
import com.ync365.seed.bussiness.modules.goods.entity.Sku;
import com.ync365.seed.utils.StringUtils;

@Controller
@RequestMapping("/sku")
public class SkuController {
	
	@Autowired
	private SkuBiz skuBiz;
	
	@Autowired
	private BrandBiz brandBiz;
	
	@Autowired
	private CategoryBiz categoryBiz;
	
	@Autowired
	private StocksBiz stocksBiz; 

	/**
	 * 跳转到库存列表页面
	 * @Title: list
	 * @Description: TODO
	 * @param request
	 * @return  String    
	 * @throws
	 */
	@RequestMapping("/list")
	@LogAnnotation(operatingContent="商品管理模块>库存管理>库存列表页面")
	public String list(HttpServletRequest request) {
		
		/**
		 * 第一级分类列表
		 */
		List<Category> catList = categoryBiz.getCategoryByParentId(0);
		request.setAttribute("catList", catList);
		
		return "goods/skuList";
	}

	/**
	 * 商品库存列表
	 * @Title: dataGrid
	 * @Description: TODO
	 * @param name
	 * @param filter
	 * @return Grid    
	 * @throws
	 */
	@RequestMapping("/grid")
	@ResponseBody
	@LogAnnotation(operatingContent="商品管理模块>库存管理>查询")
	public Grid dataGrid(String name,String subtitle,Integer categoryId,
			     String sn,Double startQty,Double endQty, PageFilter filter) {
		Grid grid = new Grid();
		List<Sku> list = new ArrayList<Sku>();
		long count = 0;
		
		try {
		
			list=skuBiz.selectSkuStockPage(name, subtitle, categoryId, sn, startQty, endQty, filter.getStartIndex(), filter.getRows());
			count = skuBiz.selectSkuStockPageCount(name, subtitle, categoryId, sn, startQty, endQty);
		} catch (Exception e) {
			e.printStackTrace();
		}
		grid.setRows(list);		 
		grid.setRecords(count);
		grid.setPageSize(filter.getRows());
		grid.setPage(filter.getPage());
		return grid;
	}
	
	/**
	 * 跳转到修改库存页面
	 * @Title: stockEditPage
	 * @Description: TODO
	 * @param skuId     多个skuId
	 * @param request
	 * @return String    
	 * @throws
	 */
	@RequestMapping("/stockEditPage")
	@LogAnnotation(operatingContent="商品管理模块>库存管理>批量修改库存页面")
	public String stockEditPage(String skuIds,HttpServletRequest request){
		request.setAttribute("skuIds", skuIds);
		               
		return "goods/skuStockEdit";
	}
	
	/**
	 * 修改库存
	 * @Title: stockEdit
	 * @Description: TODO
	 * @param skuId
	 * @param opertorType
	 * @param stockNum
	 * @return  String    
	 * @throws
	 */
	@RequestMapping("/stockEdit")
    @ResponseBody
    @LogAnnotation(operatingContent="商品管理模块>库存管理>保存批量修改库存")
    public Json stockEdit(String skuIds,Integer opertorType,Integer stockNum){
        Json json = new Json();
        if(StringUtils.isBlank(skuIds) || null==opertorType){
            json.setSuccess(false);
            json.setMsg("参数错误");
            return json ;
        }
        try{
            int rtn = stocksBiz.stocksEdit(stockNum, skuIds, opertorType);
            if(rtn > 0 ){
                json.setSuccess(true);
            }else{
                json.setSuccess(false);
            }
        }catch(Exception e){
            e.printStackTrace();
            json.setSuccess(false);
        }
        
        return json;
    }
}


