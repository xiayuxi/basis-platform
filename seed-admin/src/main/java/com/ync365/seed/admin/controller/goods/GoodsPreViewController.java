package com.ync365.seed.admin.controller.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ync365.seed.bussiness.modules.goods.biz.FeatureBiz;
import com.ync365.seed.bussiness.modules.goods.biz.GoodsBiz;
import com.ync365.seed.bussiness.modules.goods.biz.SkuBiz;
import com.ync365.seed.bussiness.modules.goods.entity.Goods;
import com.ync365.seed.bussiness.modules.goods.entity.Sku;
import com.ync365.seed.commons.mapper.JsonMapper;

@Controller
@RequestMapping("/goodsview")
public class GoodsPreViewController {
	@Autowired
	private SkuBiz skuBiz;
	@Autowired
	private FeatureBiz featureBiz;
	@Autowired
	private GoodsBiz goodsBiz;

	@RequestMapping("/sku/{skuId}")
	public String preView(@PathVariable("skuId")Integer skuId, Model model) {
		Sku sku=skuBiz.selectSkuById(skuId);
		Goods goods = goodsBiz.selectByPrimaryKey(sku.getGoodsId());
		for (Sku s : goods.getSkus()) {
			if(s.getSkuId()==skuId){
				sku.setSkuFeatures(s.getSkuFeatures());
			}
		}
		JsonMapper js=new JsonMapper();
		String json=js.toJson(goods);
		model.addAttribute("sku", sku);
		model.addAttribute("goods", goods);
		model.addAttribute("json", json);
		return "goods/skuPreView";
	}
}
