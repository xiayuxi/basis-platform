package com.ync365.seed.admin.controller.order;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ync365.seed.admin.Interceptor.LogAnnotation;
import com.ync365.seed.admin.utils.LoginUserUtils;
import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.admin.vo.order.OrderGoodsVO;
import com.ync365.seed.bussiness.modules.goods.biz.StocksBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderGoodsBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderInfoBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderOptLogBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderSubInfoBiz;
import com.ync365.seed.bussiness.modules.order.bo.OrderBO;
import com.ync365.seed.bussiness.modules.order.bo.OrderInfoGoodsBO;
import com.ync365.seed.bussiness.modules.order.bo.OrderSearch;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoods;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsChangeInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsExpenseInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderInfo;
import com.ync365.seed.bussiness.modules.user.biz.SysLcInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUserInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysVsInfoBiz;
import com.ync365.seed.bussiness.modules.user.bo.LcInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.LcInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.bo.UserInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.UserUsInfoSearchBo;
import com.ync365.seed.bussiness.modules.user.bo.VsInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.VsInfoSearchBO;
import com.ync365.seed.service.order.OrderService;
import com.ync365.seed.utils.CloneUtils;
import com.ync365.seed.utils.Constants;

/**
 * 订单的控制层
 * @author lyh
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderInfoBiz orderInfoBiz;
	@Autowired
	private OrderSubInfoBiz orderSubInfoBiz;
	@Autowired
	private OrderGoodsBiz orderGoodsBiz;
	@Autowired
	private SysUserInfoBiz sysUserInfoBiz;
	@Autowired
	private SysLcInfoBiz sysLcInfoBiz;
	@Autowired
	private SysVsInfoBiz sysVsInfoBiz;
	@Autowired
	private OrderService orderService;
	@Autowired
    private OrderBiz orderBiz;
	@Autowired
    private OrderOptLogBiz orderOptLogBiz;
    @Autowired
    private StocksBiz stocksBiz;
	@InitBinder
	public void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}	
	/**去订单列表页面 */
	@RequestMapping("/list")
	@LogAnnotation(operatingContent="订单管理模块>订单列表>订单列表页面")
	public String list() {			
		return "/order/orderList";
	}

	/** 获取订单列表信息 */
	@RequestMapping("/grid")
	@ResponseBody
	@LogAnnotation(operatingContent="订单管理模块>订单列表>查询")
	public Grid dataGrid(OrderSearch search) {			
		// 初始化一个列表，用来接收查询结果。
		List<OrderSearch> list = new ArrayList<OrderSearch>(search.getPageSize());
		long count = 0;
		try {				
			//获取us用户编号
			UserUsInfoSearchBo usInfoBo = new UserUsInfoSearchBo();
			Boolean flag1 = false;			
			if(StringUtils.isNotBlank(search.getUsName())){				
				usInfoBo.setUserRealName(search.getUsName());
				flag1 = true;
			}
			if(StringUtils.isNoneBlank(search.getUsTel())){				
				usInfoBo.setUserMobile(search.getUsTel());
				flag1 = true;
			}		
			if(flag1){
				List<UserInfoBO> usInfoBoList = sysUserInfoBiz.selectUserInfoByParam(usInfoBo);
				if(usInfoBoList !=null && usInfoBoList.size()>0){
					List<String> usNumList = new ArrayList<String>();
					for (UserInfoBO usBO : usInfoBoList) {
						usNumList.add(usBO.getUserNum());
					}				
					search.setUsNumList(usNumList);
				}
			}			
			
			//得到vs用户编号
			Boolean flag2 = false;
			VsInfoSearchBO vsInfoBO = new VsInfoSearchBO();
			if(StringUtils.isNotBlank(search.getVsName())){
				vsInfoBO.setNameSearch(search.getVsName());
				flag2 = true;
			}
			if(StringUtils.isNotBlank(search.getVsTel())){
				vsInfoBO.setUserMobileSearch(search.getVsTel());
				flag2 = true;
			}	
			if(flag2){
				List<VsInfoBO> vsInfoList = sysVsInfoBiz.selectSysVsInfoListByPrimary(vsInfoBO);
				if(vsInfoList !=null && vsInfoList.size()>0){
					List<String> vsNumList = new ArrayList<String>();
					for (VsInfoBO vsBO : vsInfoList) {
						vsNumList.add(vsBO.getUserNum());
					}				
					search.setVsNumList(vsNumList);
				}
			}			
			
			//得到lc用户编号
			Boolean flag3 = false;
			LcInfoSearchBO lcInfoBO = new LcInfoSearchBO();
			if(StringUtils.isNotBlank(search.getLcName())){
				lcInfoBO.setLcNameSearch(search.getLcName());
				flag3 = true;
			}
			if(StringUtils.isNotBlank(search.getLcName())){
				lcInfoBO.setUserMobileSearch(search.getLcTel());
				flag3 = true;
			}
			if(flag3){
				List<LcInfoBO> lcInfoList = sysLcInfoBiz.selectSysLcInfoListByPrimary(lcInfoBO);
				if(lcInfoList !=null && lcInfoList.size()>0){
					List<String> lcNumList = new ArrayList<String>();
					for(LcInfoBO lcBO : lcInfoList){
						lcNumList.add(lcBO.getUserNum());
					}
					search.setLcNumList(lcNumList);
				}			
			}
			
			list = orderInfoBiz.search(search);
			count = orderInfoBiz.searchCount(search);// 查询总条数。
		} catch (Exception e) {
			e.printStackTrace();
		}
		Grid grid = new Grid();
		grid.setRows(list);
		grid.setPage(search.getPageIndex());
		grid.setPageSize(search.getPageSize());
		grid.setRecords(count);
		return grid;
	}
	@Autowired
	private SysUserInfoBiz usInfoBiz;	
	
	//定义一个map集合存放用户的订单信息和订单商品信息
	private Map<String, Object> orderMap;
	/**
	 * 提取一个方法：根据用户编号(usNum,vsNum,lcNum)查询出用户的具体信息
	 * @param orderId
	 * @return
	 */
	private OrderSearch getUserInfoByNum(String orderId){
		//先根据订单id获取订单信息
		orderMap = orderInfoBiz.getOrderInfoByOrderId(orderId);		
		OrderSearch orderSearch = (OrderSearch)orderMap.get("orderInfo");
		
		//调用用户的biz根据用户编号(usNum,vsNum,lcNum)查询出用户的具体信息
		UserInfoBO usInfo = usInfoBiz.getUserInfoByUserNum(orderSearch.getUsNum());	
		
		if(usInfo!=null){
			//用户信息
			orderSearch.setUsName(usInfo.getSysUserRealName());
			orderSearch.setUsTel(usInfo.getUserMobile());
			orderSearch.setUsAddress(usInfo.getSysUserAddressDetail());
			//村级服务站
			orderSearch.setVsName(usInfo.getVsName());
			orderSearch.setVsTel(usInfo.getVsMobile());
			orderSearch.setVsAddress(usInfo.getVsAddressDetail());	
			//服务工程师
			orderSearch.setEngineerName(usInfo.getSeName());
			orderSearch.setEngineerPhone(usInfo.getSeMobile());
		}
		
		
		return orderSearch;
	}
	//去订单改价列表页面
	@RequestMapping("/toOrderChange")
	@LogAnnotation(operatingContent="订单管理模块>订单列表>订单评价页面")
	public String toOrderChange(String orderId, Model model){
		OrderSearch orderInfo = getUserInfoByNum(orderId);
		orderMap.put("orderInfo", orderInfo);
		model.addAttribute("orderMap", orderMap);
		return "/order/orderChange";
	}
	//订单商品改价弹窗
	@RequestMapping("/toOrderGoodsChange")
	@LogAnnotation(operatingContent="订单管理模块>订单列表>打开审核改价窗口")
	public String toOrderGoodsChange(String orderId, String skuId,String goodsId, Model model){		
		OrderInfoGoodsBO orderGoodsBo = orderGoodsBiz.getOrderGoodsByOrderIdAndSkuId(orderId, skuId, goodsId);
		model.addAttribute("orderGoods", orderGoodsBo);
		return "/order/orderGoodsChange";
	}
	//保存订单商品改价
	@RequestMapping("/orderGooodsChangeSave")
	@ResponseBody
	public String orderGooodsChangeSave(OrderGoodsVO orderGoodsVO){
		String result = "";		
		try {			
			OrderGoods orderGoods = new OrderGoods();
			CloneUtils.cloneObject(orderGoodsVO, orderGoods);
			//订单商品改价记录
			OrderGoodsChangeInfo goodsChangeInfo = new OrderGoodsChangeInfo();
			goodsChangeInfo.setOrderGoodsId(orderGoodsVO.getGoodsId());
			if(orderGoodsVO.getGoodsPriceChange()==null){
				//如果未输入改价后的单价，则取商品原来的单价
				goodsChangeInfo.setAfterGoodsPrice(orderGoodsVO.getGoodsPrice());
			}else{
				goodsChangeInfo.setAfterGoodsPrice(orderGoodsVO.getGoodsPriceChange());//改价后商品单价	
			}			
			goodsChangeInfo.setChangeTime(new Date());//改价时间
			goodsChangeInfo.setChangeNum(LoginUserUtils.getUser().getAdminNum());//改价人编号
			goodsChangeInfo.setChangeName(LoginUserUtils.getUser().getAdminName());//改价人姓名			
			
			//订单商品佣金改价记录
			OrderGoodsExpenseInfo expenseInfo = new OrderGoodsExpenseInfo();
			expenseInfo.setOrderGoodsId(orderGoodsVO.getGoodsId());
			if(orderGoodsVO.getVillageExpenseChange()==null){
				//如果未输入改价后的佣金，则取之前的佣金
				expenseInfo.setVillageExpense(orderGoodsVO.getVillageExpense());
			}else{
				expenseInfo.setVillageExpense(orderGoodsVO.getVillageExpenseChange());
			}			
			if(orderGoodsVO.getLcExpenseChange()==null){
				expenseInfo.setLcExpense(orderGoodsVO.getLcExpense());
			}else{
				expenseInfo.setLcExpense(orderGoodsVO.getLcExpenseChange());
			}
			if(orderGoodsVO.getPlanExpenseChange()==null){
				expenseInfo.setPlanExpense(orderGoodsVO.getPlanExpense());
			}else{
				expenseInfo.setPlanExpense(orderGoodsVO.getPlanExpenseChange());
			}			
			expenseInfo.setCreateTime(new Date());//改价时间
			orderGoodsBiz.saveOrderGooodsChange(orderGoods,goodsChangeInfo,expenseInfo);
			result = "OK";
		} catch (Exception e) {
			result = "Failed";
			e.printStackTrace();
		}		
		return result;		
	}
	
	//去订单商品改价审核页面
	@RequestMapping("/toOrderChangeCheck")
	public String toOrderChangeCheck(String orderId, Model model){
		OrderSearch orderInfo = getOrderCheckInfoByNum(orderId);
		orderMap.put("orderInfo", orderInfo);
		model.addAttribute("orderMap", orderMap);
		return "/order/orderChangeCheck";
	}	
	private OrderSearch getOrderCheckInfoByNum(String orderId){
		//先根据订单id获取订单信息
		orderMap = orderInfoBiz.getOrderCheckInfoByOrderId(orderId);	
		OrderSearch orderSearch = (OrderSearch)orderMap.get("orderInfo");
		
		//调用用户的biz根据用户编号(usNum,vsNum,lcNum)查询出用户的具体信息
		UserInfoBO usInfo = usInfoBiz.getUserInfoByUserNum(orderSearch.getUsNum());	
		
		if(usInfo!=null){
			//用户信息
			orderSearch.setUsName(usInfo.getSysUserRealName());
			orderSearch.setUsTel(usInfo.getUserMobile());
			orderSearch.setUsAddress(usInfo.getSysUserAddressDetail());
			//村级服务站
			orderSearch.setVsName(usInfo.getVsName());
			orderSearch.setVsTel(usInfo.getVsMobile());
			orderSearch.setVsAddress(usInfo.getVsAddressDetail());	
			//服务工程师
			orderSearch.setEngineerName(usInfo.getSeName());
			orderSearch.setEngineerPhone(usInfo.getSeMobile());
		}
		
		
		return orderSearch;
	}
	
	//订单商品改价审核弹窗
	@RequestMapping("/orderGoodsChangeCheck")
	public String orderGoodsChangeCheck(String orderId,String skuId,String goodsId, Model model){		
		OrderInfoGoodsBO orderGoodsBo = orderGoodsBiz.getOrderGoodsByOrderIdAndSkuId(orderId, skuId, goodsId);
		model.addAttribute("orderGoods", orderGoodsBo);
		return "/order/orderGoodsChangeCheck";
	}
	
	//订单商品改价详情
	@RequestMapping("/orderGoodsChangeDetail")
	public String orderGoodsChangeDetail(String orderId,String skuId, String goodsId,Model model){		
		OrderInfoGoodsBO orderGoodsBo = orderGoodsBiz.getOrderGoodsByOrderIdAndSkuId(orderId, skuId, goodsId);
		model.addAttribute("orderGoods", orderGoodsBo);
		return "/order/orderGoodsChangeDetail";
	}
	
	//订单商品改价确认审核
	@RequestMapping("/orderGooodsChangeConfirmCheck")
	@ResponseBody
	public String orderGooodsChangeConfirmCheck(OrderGoodsVO orderGoodsVO){
		String result = "";	
		OrderGoods orderGoods = new OrderGoods();
		CloneUtils.cloneObject(orderGoodsVO, orderGoods);
		//订单商品改价记录
		OrderGoodsChangeInfo goodsChangeInfo = new OrderGoodsChangeInfo();
		goodsChangeInfo.setOrderGoodsId(orderGoodsVO.getGoodsId());		
		goodsChangeInfo.setAuditTime(new Date());
		goodsChangeInfo.setAuditNum(LoginUserUtils.getUser().getAdminNum());//审核人编号
		goodsChangeInfo.setAuditName(LoginUserUtils.getUser().getAdminName());//审核人姓名					
	
		//订单商品佣金改价记录
		OrderGoodsExpenseInfo expenseInfo = new OrderGoodsExpenseInfo();
		expenseInfo.setOrderGoodsId(orderGoodsVO.getGoodsId());		
		try {
			if(orderGoodsVO.getAuditStatus() == Constants.OrderChangePriceAuditStatus.AuditRefused.v()){				
				orderGoodsBiz.AuditRefused(orderGoods,goodsChangeInfo);
				result = "Refused";
			}
			if(orderGoodsVO.getAuditStatus() == Constants.OrderChangePriceAuditStatus.AuditPass.v()){					
				orderGoodsBiz.orderGooodsChangeConfirmCheck(orderGoods, goodsChangeInfo, expenseInfo);
				result = "OK";				
			}		
			
		} catch (Exception e) {
			result = "Failed";
			e.printStackTrace();
		}		
		return result;	
	}
	//*****************根据订单id查询订单详情***************************
	
	private OrderSearch getOrderDetail(String orderId,String orderType){
		OrderSearch orderSearch = new OrderSearch();
		//先根据订单id获取订单信息
		if(Integer.parseInt(orderType)==Constants.OrderType.MAIN.v().intValue()){
			orderMap = orderInfoBiz.getOrderDetailInfoByOrderId(orderId);		
			orderSearch = (OrderSearch)orderMap.get("orderInfo");			
			//调用用户的biz根据用户编号(usNum,vsNum,lcNum)查询出用户的具体信息
			UserInfoBO usInfo = usInfoBiz.getUserInfoByUserNum(orderSearch.getUsNum());				
			if(usInfo!=null){
				//用户信息
				orderSearch.setUsName(usInfo.getSysUserRealName());
				orderSearch.setUsTel(usInfo.getUserMobile());
				orderSearch.setUsAddress(usInfo.getSysUserAddressDetail());
				//村级服务站
				orderSearch.setVsName(usInfo.getVsName());
				orderSearch.setVsTel(usInfo.getVsMobile());
				orderSearch.setVsAddress(usInfo.getVsAddressDetail());	
				//服务工程师
				orderSearch.setEngineerName(usInfo.getSeName());
				orderSearch.setEngineerPhone(usInfo.getSeMobile());
			}
			return orderSearch;
		}
		if(Integer.parseInt(orderType)==Constants.OrderType.SUB.v().intValue()){
			orderMap = orderSubInfoBiz.getOrderDetailInfoByOrderId(orderId);		
			orderSearch = (OrderSearch)orderMap.get("orderInfo");
			
			//调用用户的biz根据用户编号(usNum,vsNum,lcNum)查询出用户的具体信息
			UserInfoBO usInfo = usInfoBiz.getUserInfoByUserNum(orderSearch.getUsNum());				
			if(usInfo!=null){
				//用户信息
				orderSearch.setUsName(usInfo.getSysUserRealName());
				orderSearch.setUsTel(usInfo.getUserMobile());
				orderSearch.setUsAddress(usInfo.getSysUserAddressDetail());
				//村级服务站
				orderSearch.setVsName(usInfo.getVsName());
				orderSearch.setVsTel(usInfo.getVsMobile());
				orderSearch.setVsAddress(usInfo.getVsAddressDetail());	
				//服务工程师
				orderSearch.setEngineerName(usInfo.getSeName());
				orderSearch.setEngineerPhone(usInfo.getSeMobile());
			}
			return orderSearch;
		}
		return orderSearch;
	}
	@RequestMapping("/orderDetail")
	public String orderDetail(String orderId,String orderType, Model model){
		OrderSearch orderInfo = getOrderDetail(orderId,orderType);
		orderMap.put("orderInfo", orderInfo);
		model.addAttribute("orderMap", orderMap);
		return "/order/orderDetail";
	}
	//取消订单cancleOrder
	@RequestMapping("/cancleOrder")
	@ResponseBody
	public String cancleOrder(String orderId){
	       String result = "";     
	    try {
    	    OrderBO orderBO = new OrderBO();
            OrderInfo orderInfo = new OrderInfo();
            List<OrderBO> orderBOList = new ArrayList<OrderBO>();
            List<Map<String, Object>> orderList = new ArrayList<Map<String, Object>>();
            List<OrderGoods> goodsList = new ArrayList<OrderGoods>();
            UserInfoBO userInfoBO = new UserInfoBO();
            userInfoBO.setSysUserRealName(LoginUserUtils.getUser().getAdminName());
            userInfoBO.setUserNum(LoginUserUtils.getUser().getAdminNum());
            orderBO.setOrderId(Integer.parseInt(orderId));
            orderBOList.add(orderBO);
            orderInfo = orderInfoBiz.selectByPrimaryKey(orderBO.getOrderId());
    
            goodsList = orderGoodsBiz.getOrderGoodsListByOrderId(orderBO.getOrderId());
            for (OrderGoods orderGood : goodsList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("skuId", orderGood.getSkuId());
                map.put("orderId", orderBO.getOrderId());
                map.put("num", orderGood.getGoodsCount());
                orderList.add(map);
            }
            orderBiz.updateOrderStatusByOrderId(orderBO.getOrderId(), orderBO.getOrderType(), orderInfo.getOrderStatus(),
                    Constants.OrderStatus.Cancled.v().intValue());
            orderOptLogBiz.insertOrderOptLog(orderBOList, LoginUserUtils.getUser().getAdminNum(), userInfoBO);
            stocksBiz.lockQtyReduction(orderList);
	    } catch (Exception e) {
            result = "Failed";
            e.printStackTrace();
        }       
		return result;
	}
	//退货标记
	@RequestMapping("/returnMarker")
	public String returnMarker(String orderId,String orderType,Model model){
		OrderInfo returnOrder = orderBiz.queryReturnOrder(orderId);
		OrderSearch orderSearch = getReturnOrderDetail(orderId,orderType);		
		orderMap.put("orderInfo", orderSearch);
		model.addAttribute("orderMap", orderMap);
		model.addAttribute("returnOrder", returnOrder);
		return "/order/returnOrderDetail";
	}
	
	//*****************根据订单id查询退货订单的详情***************************
	
		private OrderSearch getReturnOrderDetail(String orderId,String orderType){
			OrderSearch orderSearch = new OrderSearch();
			//先根据订单id获取订单信息
			if(Integer.parseInt(orderType)==Constants.OrderType.MAIN.v().intValue()){
				orderMap = orderInfoBiz.getReturnOrderDetailInfoByOrderId(orderId);		
				orderSearch = (OrderSearch)orderMap.get("orderInfo");			
				//调用用户的biz根据用户编号(usNum,vsNum,lcNum)查询出用户的具体信息
				UserInfoBO usInfo = usInfoBiz.getUserInfoByUserNum(orderSearch.getUsNum());				
				if(usInfo!=null){
					//用户信息
					orderSearch.setUsName(usInfo.getSysUserRealName());
					orderSearch.setUsTel(usInfo.getUserMobile());
					orderSearch.setUsAddress(usInfo.getSysUserAddressDetail());
					//村级服务站
					orderSearch.setVsName(usInfo.getVsName());
					orderSearch.setVsTel(usInfo.getVsMobile());
					orderSearch.setVsAddress(usInfo.getVsAddressDetail());	
					//服务工程师
					orderSearch.setEngineerName(usInfo.getSeName());
					orderSearch.setEngineerPhone(usInfo.getSeMobile());
				}
				return orderSearch;
			}
			if(Integer.parseInt(orderType)==Constants.OrderType.SUB.v().intValue()){
				orderMap = orderSubInfoBiz.getReturnOrderDetailInfoByOrderId(orderId);		
				orderSearch = (OrderSearch)orderMap.get("orderInfo");
				
				//调用用户的biz根据用户编号(usNum,vsNum,lcNum)查询出用户的具体信息
				UserInfoBO usInfo = usInfoBiz.getUserInfoByUserNum(orderSearch.getUsNum());				
				if(usInfo!=null){
					//用户信息
					orderSearch.setUsName(usInfo.getSysUserRealName());
					orderSearch.setUsTel(usInfo.getUserMobile());
					orderSearch.setUsAddress(usInfo.getSysUserAddressDetail());
					//村级服务站
					orderSearch.setVsName(usInfo.getVsName());
					orderSearch.setVsTel(usInfo.getVsMobile());
					orderSearch.setVsAddress(usInfo.getVsAddressDetail());
					//服务工程师
					orderSearch.setEngineerName(usInfo.getSeName());
					orderSearch.setEngineerPhone(usInfo.getSeMobile());
				}
				return orderSearch;
			}
			return orderSearch;
		}
	
}
