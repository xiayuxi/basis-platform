package com.ync365.seed.admin.controller.promotion;

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
import com.ync365.seed.admin.vo.base.PageFilter;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionBiz;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionCouponBiz;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionDownBiz;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionGiftBiz;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionReduceBiz;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionReturnBiz;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionSeckillBiz;
import com.ync365.seed.bussiness.modules.promotion.entity.Promotion;
import com.ync365.seed.bussiness.modules.promotion.po.PromotionSearch;
import com.ync365.seed.bussiness.modules.user.biz.SysPopStoreBiz;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreBO;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreSearchBO;
import com.ync365.seed.utils.Constants;

@Controller
@RequestMapping("/promotion")
public class PromotionController {
	@Autowired
	private PromotionBiz promotionBiz;
	@Autowired
	private PromotionCouponBiz promotionCouponBiz;
	@Autowired
	private PromotionReduceBiz promotionReduceBiz;
	@Autowired
	private PromotionReturnBiz promotionReturnBiz;
	@Autowired
	private PromotionGiftBiz promotionGiftBiz;
	@Autowired
	private PromotionSeckillBiz promotionSeckillBiz;
	@Autowired
	private PromotionDownBiz promotionDownBiz;
	@Autowired
	private SysPopStoreBiz sysPopStoreBiz;
	@InitBinder
	public void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) throws Exception {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}
	
	/** 去活动列表页面 */
	@RequestMapping("/list")
	public String list(Integer promotionType,Model model) {		
		model.addAttribute("promotionType", promotionType);
		return "/promotion/list";
	}
	/** 加载活动列表 */
	@RequestMapping("/grid")
	@ResponseBody
	public Grid dataGrid(PromotionSearch search,PageFilter filter,Integer promotionType) {		
		// 初始化一个列表，用来接收查询结果。
		List<Promotion> list = new ArrayList<Promotion>(search.getPageSize());
		long count = 0;
		try {
			// 设置活动类型			
			search.setPromotionType(promotionType);
			list = promotionBiz.search(search,filter.getStartIndex(),filter.getRows());// 查询列表。
			count = promotionBiz.searchCount(search);// 查询总条数。
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Grid grid = new Grid();
		grid.setRows(list);
		grid.setRecords(count);
		grid.setPageSize(filter.getRows());
		grid.setPage(filter.getPage());
		return grid;	
	}
	/**
	 * 去新增活动页面
	 * @param promotionType
	 * @param model
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(Integer promotionType,Model model) {
		//获取店铺列表
		List<PopStoreBO> popStoreList = sysPopStoreBiz.selectPageBySearchBO(new PopStoreSearchBO());
		model.addAttribute("popStoreList", popStoreList);		
		String result = "";		
		switch (promotionType){ 
		case 2:
			result = "/promotion/promotionCoupon/add";
			break;
		case 3:
			result = "/promotion/promotionReduce/add";
			break;
		case 4:
			result = "/promotion/promotionReturn/add";
			break;
		case 5:
			result = "/promotion/promotionGift/add";
			break;
		case 6:
			result = "/promotion/promotionSeckill/add";
			break;
		case 7:
			result = "/promotion/promotionDown/add";
			break;
		default:
			break;			
		}
		return result;		
	}
	
	/**
	 * 去编辑活动页面
	 * @param promotionType
	 * @param model
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(Integer promotionType,Model model) {
		//获取店铺列表
		List<PopStoreBO> popStoreList = sysPopStoreBiz.selectPageBySearchBO(new PopStoreSearchBO());
		model.addAttribute("popStoreList", popStoreList);
		String result = "";		
		switch (promotionType){ 
		case 2:
			result = "/promotion/promotionCoupon/edit";
			break;
		case 3:
			result = "/promotion/promotionReduce/edit";
			break;
		case 4:
			result = "/promotion/promotionReturn/edit";
			break;
		case 5:
			result = "/promotion/promotionGift/edit";
			break;
		case 6:
			result = "/promotion/promotionSeckill/edit";
			break;
		case 7:
			result = "/promotion/promotionDown/edit";
			break;
		default:
			break;			
		}
		return result;	
	}
	
	/**
	 * 删除活动(逻辑删除)
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String deletePromotionReduce(@RequestParam("id") int id,Integer promotionType){
		String result = "";//加一个删除成功的标记
		try {			
			if(promotionType==Constants.PromotionType.Coupon.v()){//红包
				promotionCouponBiz.deleteById(id);	
				result = "OK";
			}else if(promotionType==Constants.PromotionType.Reduce.v()){//满减
				promotionReduceBiz.deleteById(id);
				result = "OK";
			}else if(promotionType==Constants.PromotionType.Retrun.v()){//满返
				promotionReturnBiz.deleteById(id);
				result = "OK";
			}else if(promotionType==Constants.PromotionType.Gift.v()){//满赠
				promotionGiftBiz.deleteById(id);
				result = "OK";
			}else if(promotionType==Constants.PromotionType.Seckill.v()){//秒杀
				promotionSeckillBiz.deleteById(id);
				result = "OK";
			}else if(promotionType==Constants.PromotionType.Down.v()){//直降
				promotionDownBiz.deleteById(id);
				result = "OK";
			}else{
				result = "Failed";
			}	
		} catch (Exception e) {
			result = "Failed";
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据活动id,启动活动 
	 * @param id
	 * @return
	 */
	@RequestMapping("/start")
	@ResponseBody
	public String start(int id,Integer promotionType) {
		//判断活动是否已经添加商品(只有添加了商品才可以启动)
		String result = "";
		Boolean havaGoods = promotionBiz.checkPromotionGoodsIsAdd(id);		
		if(havaGoods){
			Boolean flag = false;
			if(promotionType==2){//启动红包活动
				flag = promotionBiz.startCouponPromotion(id);
			}else{//启动其他类型的活动
				flag = promotionBiz.startPromotion(id);
			}			
			if(flag){
				result = "OK";
			}else{
				result = "Failed";
			}			
		}else{
			result = "NoGoods";//未添加活动商品的标志
		}		
		return result;
	}
	/**
	 * 根据活动id,终止活动 
	 * @param id
	 * @return
	 */
	@RequestMapping("/end")
	@ResponseBody
	public boolean end(int id,Integer promotionType) {
		boolean flag = false;
		if(promotionType==2){//终止红包活动
			flag = promotionBiz.endCouponPromotion(id);
		}else{//终止其他类型的活动
			flag = promotionBiz.endPromotion(id);			
		}		
		return flag;
	}	
}
