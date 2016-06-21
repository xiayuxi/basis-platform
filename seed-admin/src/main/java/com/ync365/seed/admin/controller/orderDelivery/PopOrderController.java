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

import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.bussiness.modules.common.biz.MobileValidateCodeBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderInfoBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderSubInfoBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderUserBiz;
import com.ync365.seed.bussiness.modules.order.bo.OrderSearch;
import com.ync365.seed.bussiness.modules.order.entity.OrderInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderSubInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderUser;
import com.ync365.seed.bussiness.modules.user.biz.SysPopBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysPopStoreBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUserInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysVsInfoBiz;
import com.ync365.seed.bussiness.modules.user.bo.UserInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.UserUsInfoSearchBo;
import com.ync365.seed.bussiness.modules.user.bo.VsInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.VsInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.entity.SysVsInfo;
import com.ync365.seed.utils.Constants;


/**
 * 入驻商订单的控制层
 * @author lyh
 *
 */
@Controller
@RequestMapping("/popOrder")
public class PopOrderController {
	@Autowired
	private OrderInfoBiz orderInfoBiz;
	@Autowired
	private OrderSubInfoBiz orderSubInfoBiz;
	@Autowired
	private SysUserInfoBiz sysUserInfoBiz;
	@Autowired
	private SysVsInfoBiz sysVsInfoBiz;
	@Autowired
	private SysPopStoreBiz sysPopStoreBiz;
	@Autowired
	private OrderUserBiz orderUserBiz;
	@InitBinder
	public void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}
	/**去入驻商订单列表页面 */
	@RequestMapping("/list")
	public String list() {			
		return "/orderDelivery/popOrder";
	}
	/** 获取入驻商订单列表信息 */
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
			
			list = orderInfoBiz.searchPopOrder(search);
			count = orderInfoBiz.searchPopOrderCount(search);// 查询总条数。
			System.out.println(count);
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
	//入驻商管理订单查询popOrderQuery
	@RequestMapping("/popOrderQuery")
	public String popOrderQuery() {			
		return "/orderDelivery/popOrderQuery";
	}
	private MobileValidateCodeBiz mobileValidateCodeBiz;
	//*********************批量发货提醒******************
	@RequestMapping("/batchSendGoodsRemind")
	@ResponseBody
	public String batchSendGoodsRemind(String orderIds,String storeIds){
		String[] split1=null;
		String[] split2=null;
		String result = "";
		try {
			//根据订单id查询订单和用户信息
			if(orderIds!=null && orderIds!=""){
				 split1 = orderIds.split(",");			
				if(storeIds!=null&&storeIds!=""){
					split2 = orderIds.split(",");				
					for (String orderId : split1) {					
						OrderInfo orderInfo = orderInfoBiz.selectByPrimaryKey(Integer.parseInt(orderId));
						OrderSubInfo orderSubInfo = orderSubInfoBiz.selectByPrimaryKey(Integer.parseInt(orderId));
						if(orderInfo!=null){//主订单						
							for (String storeId : split2) {
								String storeMobile = sysPopStoreBiz.selectPopStoreById(Integer.parseInt(storeId)).getPopStoreMobile();//商家电话
								String storeName = sysPopStoreBiz.selectPopStoreById(Integer.parseInt(storeId)).getPopStoreName();//商家名称
								OrderUser orderUser = orderUserBiz.getOrderUserByOrderNo(orderInfo.getOrderNo());//获取订单用户
								String payTime = new SimpleDateFormat("yyyyMMddhhmmss").format(orderInfo.getPayTime());//用户支付时间 
								//给入驻商发消息提醒入驻商开始发货
								mobileValidateCodeBiz.sendGoodsRemindMsg(storeMobile, storeName, orderUser.getUsername(), orderInfo.getOrderNo(), payTime);
								result = "OK";
								
							}						
						}
						if(orderSubInfo!=null){//子订单
							for (String storeId : split2) {
								String storeMobile = sysPopStoreBiz.selectPopStoreById(Integer.parseInt(storeId)).getPopStoreMobile();//商家电话
								String storeName = sysPopStoreBiz.selectPopStoreById(Integer.parseInt(storeId)).getPopStoreName();//商家名称
								OrderUser orderUser = orderUserBiz.getOrderUserByOrderNo(orderSubInfo.getOrderNo());//获取订单用户
								String payTime = new SimpleDateFormat("yyyyMMddhhmmss").format(orderSubInfo.getPayTime());//用户支付时间 
								//给入驻商发消息提醒入驻商开始发货
								mobileValidateCodeBiz.sendGoodsRemindMsg(storeMobile, storeName, orderUser.getUsername(), orderSubInfo.getOrderNo(), payTime);
								result = "OK";
							}		
						}
					}
				}				
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
		
		//发货确认
		@RequestMapping("/sendGoodsConfirm")
		@ResponseBody
		public String sendGoodsConfirm(String orderId){
			String result="";
			try {
				OrderInfo orderInfo = orderInfoBiz.selectByPrimaryKey(Integer.parseInt(orderId));
				OrderSubInfo orderSubInfo = orderSubInfoBiz.selectByPrimaryKey(Integer.parseInt(orderId));				
				if(orderInfo!=null){//说明是主订单
					orderInfo.setOrderStatus(Constants.OrderStatus.Delivery.v().intValue());
				    orderInfoBiz.updateByPrimaryKeySelective(orderInfo);
				}
				if(orderSubInfo!=null){//说明是子订单
					orderSubInfo.setOrderStatus(Constants.OrderStatus.Delivery.v().intValue());
				    orderSubInfoBiz.updateByPrimaryKeySelective(orderSubInfo);
				} 			
				result = "OK";
			} catch (Exception e) {
				result = "Failed";
				e.printStackTrace();
			}    
			
			return result;
	}
}
