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
import com.ync365.seed.bussiness.modules.order.biz.OrderBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderExpenseBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderGoodsBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderInfoBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderOptLogBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderSubInfoBiz;
import com.ync365.seed.bussiness.modules.order.bo.OrderBO;
import com.ync365.seed.bussiness.modules.order.bo.OrderSearch;
import com.ync365.seed.bussiness.modules.order.dao.OrderSubInfoMapper;
import com.ync365.seed.bussiness.modules.order.entity.OrderExpense;
import com.ync365.seed.bussiness.modules.order.entity.OrderInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderSubInfo;
import com.ync365.seed.bussiness.modules.user.biz.SysUserInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysVsInfoBiz;
import com.ync365.seed.bussiness.modules.user.bo.LcInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.LcInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.bo.UserInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.UserUsInfoSearchBo;
import com.ync365.seed.bussiness.modules.user.bo.VsInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.VsInfoSearchBO;
import com.ync365.seed.dto.base.ResponseCode;
import com.ync365.seed.dto.base.ResponseDTO;
import com.ync365.seed.dto.order.output.OrderDTO;
import com.ync365.seed.utils.Constants;

/**
 * 已发货订单的控制层
 * @author lyh
 *
 */
@Controller
@RequestMapping("/deliveryOrder")
public class DeliveryOrderController {
	@Autowired
	private OrderInfoBiz orderInfoBiz;
	@Autowired
	private OrderGoodsBiz orderGoodsBiz;
	@Autowired
	private SysUserInfoBiz sysUserInfoBiz;
	@Autowired
	private SysVsInfoBiz sysVsInfoBiz;
	@Autowired
	private OrderBiz orderBiz;
	@Autowired
	private OrderSubInfoBiz orderSubInfoBiz;
	@Autowired
	private OrderExpenseBiz orderExpenseBiz;
	@Autowired
	private OrderOptLogBiz orderOptLogBiz;	
	@InitBinder
	public void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}
	/**去已发货的订单列表页面 */
	@RequestMapping("/list")
	public String list() {			
		return "/orderDelivery/deliveryOrder";
	}
	/** 获取已发货的订单列表信息 */
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
				
			search.setOrderStatus(Constants.OrderStatus.Delivery.v().intValue());
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
	//收货确认
	@RequestMapping("/confirmAcceptGoods")
	@ResponseBody
	public String confirmAcceptGoods(String orderId){
		String result="";
		try {
			OrderInfo orderInfo = orderInfoBiz.selectByPrimaryKey(Integer.parseInt(orderId));
			OrderSubInfo orderSubInfo = orderSubInfoBiz.selectByPrimaryKey(Integer.parseInt(orderId));
			List<OrderBO> orderBOList = new ArrayList<OrderBO>();
			OrderBO orderBO = new OrderBO();
			OrderDTO orderDTO = new OrderDTO();
			if(orderInfo!=null){//说明是主订单
				orderBO.setOrderNo(orderInfo.getOrderNo());
			    orderBO.setOrderType(Constants.OrderType.MAIN.v());
			    orderBO.setOrderId(orderInfo.getId());
			    orderBOList.add(orderBO);
			    
			    orderDTO.setOrderId(orderInfo.getId());
			    orderDTO.setOrderType(Constants.OrderType.MAIN.v());
			    
			    orderInfo.setSigninTime(new Date());
			    orderInfoBiz.updateByPrimaryKeySelective(orderInfo);
			    UserInfoBO userInfoBO = sysUserInfoBiz.getUserInfoByUserNum(LoginUserUtils.getUser().getAdminNum());       
				orderDTO.setStatus(orderInfo.getOrderStatus());
				orderBiz.updateOrderStatusByOrderId(orderDTO.getOrderId(), orderDTO.getOrderType(),
				        orderDTO.getStatus(), Constants.OrderStatus.Completed.v().intValue());              
				orderOptLogBiz.insertOrderOptLog(orderBOList,  LoginUserUtils.getUser().getAdminNum(), userInfoBO);
				result = "OK";
			}
			if(orderSubInfo!=null){//说明是子订单
				orderBO.setOrderNo(orderSubInfo.getOrderSubNo());
			    orderBO.setOrderType(Constants.OrderType.SUB.v());
			    orderBO.setOrderId(orderSubInfo.getId());
			    orderBOList.add(orderBO);
			    
			    orderDTO.setOrderId(orderSubInfo.getId());
			    orderDTO.setOrderType(Constants.OrderType.SUB.v());
			    
			    orderSubInfo.setSigninTime(new Date());
			    orderSubInfoBiz.updateByPrimaryKeySelective(orderSubInfo);
			    UserInfoBO userInfoBO = sysUserInfoBiz.getUserInfoByUserNum(LoginUserUtils.getUser().getAdminNum());       
				orderDTO.setStatus(orderSubInfo.getOrderStatus());
				orderBiz.updateOrderStatusByOrderId(orderDTO.getOrderId(), orderDTO.getOrderType(),
				        orderDTO.getStatus(), Constants.OrderStatus.Completed.v().intValue());              
				orderOptLogBiz.insertOrderOptLog(orderBOList,  LoginUserUtils.getUser().getAdminNum(), userInfoBO);
				result = "OK";
			}        
			
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
		public String orderDetail(String orderId,String orderType, Model model){
			OrderSearch orderInfo = getOrderDetail(orderId,orderType);
			orderMap.put("orderInfo", orderInfo);
			model.addAttribute("orderMap", orderMap);
			return "/orderDelivery/orderDetail";
		}
}


