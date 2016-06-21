package com.ync365.seed.admin.controller.orderDelivery;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.ync365.seed.admin.utils.LoginUserUtils;
import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.bussiness.modules.order.biz.OrderGoodsBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderInfoBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderSubInfoBiz;
import com.ync365.seed.bussiness.modules.order.bo.OrderSearch;
import com.ync365.seed.bussiness.modules.order.entity.OrderOptLog;
import com.ync365.seed.bussiness.modules.user.biz.SysUserInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysVsInfoBiz;
import com.ync365.seed.bussiness.modules.user.bo.UserInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.UserUsInfoSearchBo;
import com.ync365.seed.bussiness.modules.user.bo.VsInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.VsInfoSearchBO;
import com.ync365.seed.utils.Constants;

/**
 * 已出库订单的控制层
 * @author lyh
 *
 */
@Controller
@RequestMapping("/stockOutOrder")
public class StockOutOrderController {
	@Autowired
	private OrderInfoBiz orderInfoBiz;
	@Autowired
	private OrderSubInfoBiz orderSubInfoBiz;
	@Autowired
	private OrderGoodsBiz orderGoodsBiz;
	@Autowired
	private SysUserInfoBiz sysUserInfoBiz;
	@Autowired
	private SysVsInfoBiz sysVsInfoBiz;
	@InitBinder
	public void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}
	/**去出库的订单列表页面 */
	@RequestMapping("/list")
	public String list() {			
		return "/orderDelivery/stockOutOrder";
	}
	/** 获取已出库的订单列表信息 */
	@RequestMapping("/grid")
	@ResponseBody
	public Grid dataGrid(OrderSearch search) {		
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
			
			search.setOrderStatus(Constants.OrderStatus.StockOut.v().intValue());
			list = orderInfoBiz.searchOrderByOrderStatus(search);
			count = orderInfoBiz.searchCountOrderByOrderStatus(search);// 查询总条数。
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
	//批量生成发货单
	@RequestMapping("/batchCreateDeliveryOrder")
	@ResponseBody
	public String batchCreateDeliveryOrder(String orderIds,String orderTypes){
		String result = "";	
		try {			
			if(orderIds!=null && orderIds!=""){//订单id			
				String[] split1 = orderIds.split(",");
				List<Integer> orderIdList = new ArrayList<Integer>();
				List<Integer> orderTypeList = new ArrayList<Integer>();
				for (String orderId : split1) {
					orderIdList.add(Integer.parseInt(orderId));
				}			
				if(orderTypes!=null&&orderTypes!=""){//订单类型
					String[] split2 = orderTypes.split(",");					
					for (String orderType : split2) {					
						orderTypeList.add(Integer.parseInt(orderType));
					}
				}
				//获取操作人的编号和姓名
				OrderOptLog orderOptLog = new OrderOptLog();
				orderOptLog.setOptNum(LoginUserUtils.getUser().getAdminNum());
				orderOptLog.setOptName(LoginUserUtils.getUser().getAdminName());
				orderOptLog.setOptTime(new Date());
				orderInfoBiz.updateOrderStatusByOrderId(orderIdList, orderTypeList, orderOptLog, Constants.OrderStatus.Delivery.v().intValue());
				result = "OK";
			}	
		} catch (Exception e) {
			result = "Failed";
			e.printStackTrace();
		}				
		return result;
	}
	@RequestMapping("/createDeliveryOrder")
	@ResponseBody
	public String createDeliveryOrder(String orderId){	
		String result = "OK";
		try {
			List<Integer> orderIdList = new ArrayList<Integer>();			
			orderIdList.add(Integer.parseInt(orderId));	
			//获取操作人的编号和姓名
			OrderOptLog orderOptLog = new OrderOptLog();
			orderOptLog.setOptNum(LoginUserUtils.getUser().getAdminNum());
			orderOptLog.setOptName(LoginUserUtils.getUser().getAdminName());
			orderOptLog.setOptTime(new Date());
			//orderInfoBiz.updateOrderStatusByOrderId(orderIdList,orderOptLog,Constants.OrderStatus.Delivery.v().intValue());
		} catch (Exception e) {
			result = "Failed";
			e.printStackTrace();
		}			
		return result;
	}
	
	//*****************根据订单id查询订单详情***************************
		//定义一个map集合存放用户的订单信息和订单商品信息
		@Autowired
		private SysUserInfoBiz usInfoBiz;	

		private Map<String, Object> orderMap;
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
		public String orderDetail(String orderId, String orderType,Model model){
			OrderSearch orderInfo = getOrderDetail(orderId,orderType);
			orderMap.put("orderInfo", orderInfo);
			model.addAttribute("orderMap", orderMap);
			return "/orderDelivery/orderDetail";
		}
}

