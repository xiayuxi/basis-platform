package com.ync365.seed.admin.controller.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.bussiness.modules.goods.biz.SkuBiz;
import com.ync365.seed.bussiness.modules.goods.entity.Sku;

@Controller
@RequestMapping("/stock")
public class StockController {

	@Autowired
	private SkuBiz skuBiz;

	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		return "goods/StockList";
	}

	@RequestMapping("/grid")
	@ResponseBody
	public Grid dataGrid(String name, String code, @RequestParam("page") int page,
			@RequestParam("rows") int rowNum,String stockStart,String stockEnd) {
		Grid grid = new Grid();
		List<Sku> list = new ArrayList<Sku>();
		long count = 9;
		try {
			System.out.println("goods:"+name+"code:"+code+"stockStart:"+stockStart+"stockEnd:"+stockEnd);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("name", name);
			map.put("code", code);
			map.put("stockStart",stockStart);
			map.put("stockEnd", stockEnd);
			
			list=skuBiz.searchPage(map, page, rowNum);
			count = skuBiz.searchPageCount(map);
			
			System.out.println(list.toString());
			count = skuBiz.searchPageCount(map);
			System.out.println(count);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		grid.setRows(list);
		grid.setTotal(count);
		return grid;
	}

}
