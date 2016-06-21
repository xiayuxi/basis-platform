package com.ync365.seed.service.order.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.Assert;
import com.alibaba.fastjson.JSONObject;
import com.ync365.seed.bussiness.modules.goods.biz.StocksBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderContactBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderEngineerBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderExpenseBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderGoodsBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderGoodsExpenseBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderInfoBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderOptLogBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderPayBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderStoreBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderSubInfoBiz;
import com.ync365.seed.bussiness.modules.order.biz.UserCouponBiz;
import com.ync365.seed.bussiness.modules.order.biz.UserExpenseBiz;
import com.ync365.seed.bussiness.modules.order.bo.OrderPayBO;
import com.ync365.seed.bussiness.modules.order.entity.OrderContact;
import com.ync365.seed.bussiness.modules.order.entity.OrderEngineer;
import com.ync365.seed.bussiness.modules.order.entity.OrderExpense;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoods;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsExpenseManual;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsSub;
import com.ync365.seed.bussiness.modules.order.entity.OrderInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderOptLog;
import com.ync365.seed.bussiness.modules.order.entity.OrderPay;
import com.ync365.seed.bussiness.modules.order.entity.OrderStore;
import com.ync365.seed.bussiness.modules.order.entity.OrderSubInfo;
import com.ync365.seed.bussiness.modules.order.entity.UserCoupon;
import com.ync365.seed.bussiness.modules.order.entity.UserExpense;
import com.ync365.seed.bussiness.modules.order.entity.UserExpenseLog;
import com.ync365.seed.bussiness.modules.user.biz.SysUserInfoBiz;
import com.ync365.seed.bussiness.modules.user.bo.UserInfoBO;
import com.ync365.seed.commons.mapper.JsonMapper;
import com.ync365.seed.dto.base.ResponseCode;
import com.ync365.seed.dto.base.ResponseDTO;
import com.ync365.seed.dto.order.input.UserCouponDTO;
import com.ync365.seed.service.order.PayService;
import com.ync365.seed.utils.Configuration;
import com.ync365.seed.utils.Constants;

public class PayServiceImpl  implements PayService {
	
    private static final Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);
    
    @Autowired
    private UserCouponBiz userCouponBiz;
    
    @Autowired
    private OrderBiz orderBiz;

    @Autowired
    private OrderOptLogBiz orderOptLogBiz;

    @Autowired
    private OrderGoodsBiz orderGoodsBiz;

    @Autowired
    private OrderInfoBiz orderInfoBiz;

    @Autowired
    private SysUserInfoBiz sysUserInfoBiz;

    @Autowired
    private OrderEngineerBiz orderEngineerBiz;

    @Autowired
    private StocksBiz stocksBiz;

    @Autowired
    private OrderExpenseBiz orderExpenseBiz;

    @Autowired
    private OrderGoodsExpenseBiz orderGoodsExpenseBiz;

    @Autowired
    private OrderContactBiz orderContactBiz;

    @Autowired
    private OrderStoreBiz orderStoreBiz;

    @Autowired
    private Configuration configuration;
    
    @Autowired
    private UserExpenseBiz userExpenseBiz;
    
    @Autowired
    private OrderSubInfoBiz orderSubInfoBiz;
    
    @Autowired
    private OrderPayBiz orderPayBiz;
    

	@Override
	public ResponseDTO getRedPackets(String orderNo,Integer amount) {
		ResponseDTO responseDTO = new ResponseDTO() ;
		int status = userCouponBiz.selectUserCouponStatusByOrderNo(orderNo);
		responseDTO.setCode(ResponseCode.CommonCode.OK.v());
		responseDTO.setMsg(ResponseCode.CommonCode.OK.c());
		responseDTO.setData(status);
		return responseDTO;
	}

	@Override
	public ResponseDTO reduceRedPackets(List<UserCouponDTO> list) {
 
		List<Map<String,Object>> paraList = new ArrayList<Map<String,Object>>();
		for(UserCouponDTO ucDto:list) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userCouponId", ucDto.getUserCouponId());
			map.put("amout", ucDto.getAmount());
			paraList.add(map);
		}
		
		ResponseDTO responseDTO = new ResponseDTO() ;
		int update =  userCouponBiz.updateUserCouponBatchReduce(paraList) ;
		if(update == 1 ) {
			responseDTO.setCode(ResponseCode.CommonCode.OK.v());
			responseDTO.setMsg(ResponseCode.CommonCode.OK.c());
			responseDTO.setData(update);
		}else if(update == 0 ) {
			responseDTO.setCode(ResponseCode.UserCouponCode.UPDATE_FAIL.v());
			responseDTO.setMsg(ResponseCode.UserCouponCode.UPDATE_FAIL.c());
			responseDTO.setData(update);
		}else{
			responseDTO.setCode(ResponseCode.UserCouponCode.AMOUNT_DIFFER.v());
			responseDTO.setMsg(ResponseCode.UserCouponCode.AMOUNT_DIFFER.c());
		}
		return responseDTO;
	}
	
	public ResponseDTO cancelRedPackets(String orderNo,Integer amount){
 

		ResponseDTO responseDTO = new ResponseDTO() ;
		
		List<UserCoupon> userCouponList = userCouponBiz.selectUserCouponListByOrderNo(orderNo);
		
		List<Integer> paraList = new ArrayList<Integer>();
		for(UserCoupon uc:userCouponList){
		 
			paraList.add(uc.getUserCouponId());
		}
		
		int update  = userCouponBiz.updateUserCouponBatchCancel(paraList) ;
		if(update == 1 ) {
			responseDTO.setCode(ResponseCode.CommonCode.OK.v());
			responseDTO.setMsg(ResponseCode.CommonCode.OK.c());
			responseDTO.setData(update);
		}else if(update == 0 ) {
			responseDTO.setCode(ResponseCode.UserCouponCode.UPDATE_FAIL.v());
			responseDTO.setMsg(ResponseCode.UserCouponCode.UPDATE_FAIL.c());
			responseDTO.setData(update);
		}else{
			responseDTO.setCode(ResponseCode.UserCouponCode.AMOUNT_DIFFER.v());
			responseDTO.setMsg(ResponseCode.UserCouponCode.AMOUNT_DIFFER.c());
		}
		return responseDTO;
	}

	@Override
    public ResponseDTO asynNotifyPayOrder(Map<String, Object> map) {
        ResponseDTO response = new ResponseDTO();
        String orderNo = "";
        String tradeStatus = "";
        String innerTradeNo = "";
        try{
            // 校验签名
            // String sign = (String) map.get("sign");
            map.remove("sign");
            map.remove("sign_type");
            Set<Entry<String, Object>> set = map.entrySet();
            List<String> list = new ArrayList<String>(set.size());
            Iterator<Entry<String, Object>> it = set.iterator();
            while (it.hasNext()) {
                Entry<String, Object> str = it.next();
                list.add(str.toString());
            }
            Collections.sort(list);
            StringBuffer strbody = new StringBuffer();
            for (String str : list) {
                strbody.append(str);
                strbody.append("&");
            }
            if (strbody.length() != 0) {
                strbody.deleteCharAt(strbody.length() - 1);
            }
            logger.info("订单支付，异步回调验签数据，param，{}", strbody.toString());
            /**
            if (!RSASignature.doCheck(strbody.toString(), sign,
                    RSAEncrypt.loadPublicKeyByFile(configuration.getPublickeyStore()))) {
                logger.error("订单支付，验签失败，param，{}", strbody.toString());
                response.setCode(ResponseCode.PayCode.RSA_VERFIY_FAIL.v());
                response.setMsg(ResponseCode.PayCode.RSA_VERFIY_FAIL.c());
                return response;
            }*/
            // 数据解析
            JSONObject synJosnObject = new JSONObject(map);
            logger.info("订单支付，云农宝支付异步通知参数，param，{}", synJosnObject);
            logger.info("订单支付，商户网站唯一订单号，outer_trade_no，{}", synJosnObject.getString("outer_trade_no"));
            logger.info("订单支付，支付系统交易号，inner_trade_no，{}", synJosnObject.getString("inner_trade_no"));
            tradeStatus = synJosnObject.getString("trade_status");
            
            logger.info("订单支付，云农宝支付异步状态 ", tradeStatus);
            orderNo = synJosnObject.getString("outer_trade_no");
            innerTradeNo = synJosnObject.getString("inner_trade_no");
            /**
            PAY_FINISHED    买家已付款
            TRADE_SUCCESS   交易成功
            TRADE_FINISHED  交易结束
            TRADE_CLOSED    交易关闭
            TRADE_FAILED    交易失败
             */
            Assert.notNull(tradeStatus, "交易状态空值");
            if(StringUtils.isNoneBlank(tradeStatus)){
            	
	            switch (tradeStatus) {
	            
		            case "PAY_FINISHED":
		            	/**业务逻辑处理*/
		            	response = excutePayFinished(response, orderNo);
		    			break;
		    			
		            case "TRADE_SUCCESS":
		                response.setCode(ResponseCode.CommonCode.OK.v());
		                response.setMsg(ResponseCode.CommonCode.OK.c());
		            	break;
		            	
		            case "TRADE_FINISHED":
		            	/**业务逻辑处理*/
	                      response = readeSuccess(response,orderNo);
		            	break;
		            	
		            case "TRADE_FAILED":
		            	/**业务逻辑处理*/
		    			break;
	    		}
            }
                         
        }catch(Exception e){
            response.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
            response.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
            logger.error("订单支付，失败，orderNo，{}，tradeStatus，{}，innerTradeNo，{}，异常原因，{}", orderNo,tradeStatus,innerTradeNo,e.getMessage());
            e.printStackTrace();
        }
        
        return response;
    }

	private ResponseDTO readeSuccess(ResponseDTO response, String orderNo) {
	    logger.info("订单佣金回调：orderNom,{}",orderNo);
	    List<UserExpense> userExpenseList = new ArrayList<UserExpense>();
        if (Constants.OrderType.MAIN.v() .equals( Integer.parseInt(orderNo.substring(0, 1)))) {
            List<OrderInfo> orderInfoList = orderBiz.selectOrderByOrderNo(orderNo);
            OrderInfo orderInfo = orderInfoList.get(0);
            userExpenseList = userExpenseBiz.selectUserExpenseStatusByOrderId(orderInfo.getId(), 
                    Constants.OrderExpenseStatus.Settle.v(),Constants.OrderType.MAIN.v());
            for(UserExpense userExpense : userExpenseList){
                UserExpenseLog userExpenseLog = new UserExpenseLog();
                userExpenseLog.setOrderId(orderInfo.getId());
                userExpenseLog.setOrderType(Constants.OrderType.MAIN.v());
                userExpenseLog.setUserExpenseId(userExpense.getId());
                userExpense.setStatus(Constants.OrderExpenseStatus.Settle.v());
                userExpense.setSettleTime(new Date());
                userExpenseBiz.updateByPrimaryKey(userExpense);
                userExpenseLog.setCreateTime(new Date());
                userExpenseLog.setOrderNo(orderNo);
                userExpenseLog.setStatus(Constants.OrderExpenseStatus.Settle.v());
                userExpenseLog.setUseNum(userExpense.getUserNum());
                userExpenseBiz.insertUseExpenseLog(userExpenseLog);
            }
        }
        if (Constants.OrderType.SUB.v() == Integer.parseInt(orderNo.substring(0, 1))) { // 子订单
           OrderSubInfo orderSubInfoList = orderSubInfoBiz.selectSubOrdersByOrderNo(orderNo);
           userExpenseList = userExpenseBiz.selectUserExpenseStatusByOrderId(orderSubInfoList.getId(), 
                   Constants.OrderExpenseStatus.Settle.v(),Constants.OrderType.SUB.v());
           for(UserExpense userExpense : userExpenseList){
               UserExpenseLog userExpenseLog = new UserExpenseLog();
               userExpenseLog.setOrderId(orderSubInfoList.getId());
               userExpenseLog.setOrderType(Constants.OrderType.SUB.v());
               userExpense.setSettleTime(new Date());
               userExpense.setStatus(Constants.OrderExpenseStatus.Settle.v());
               userExpenseBiz.updateByPrimaryKey(userExpense);
               userExpenseLog.setUserExpenseId(userExpense.getId());
               userExpenseLog.setCreateTime(new Date());
               userExpenseLog.setOrderNo(orderNo);
               userExpenseLog.setStatus(Constants.OrderExpenseStatus.Settle.v());
               userExpenseLog.setUseNum(userExpense.getUserNum());
               userExpenseBiz.insertUseExpenseLog(userExpenseLog);
           }
        }
        response.setCode(ResponseCode.CommonCode.OK.v());
        response.setMsg(ResponseCode.CommonCode.OK.c());
        
        return response ;
	}
	
	private ResponseDTO excutePayFinished(ResponseDTO response, String orderNo) {
		// String userNum = "system";
	    
	    // 支付方式
        //OrderPayBO orderPayBO = orderPayBiz.getOrderPayInfo(orderNo);
	    
		if (Constants.OrderType.MAIN.v() == Integer.parseInt(orderNo.substring(0, 1))) { // 主订单
		    // 修改订单支付状态
		    List<OrderInfo> orderInfoList = orderBiz.selectOrderByOrderNo(orderNo);
		    OrderInfo orderInfo = orderInfoList.get(0);
		    // 判断是否已经支付
		    if(orderInfo.getOrderStatus() >= Constants.OrderStatus.Paid.v().intValue()){
		        response.setCode(ResponseCode.CommonCode.OK.v());
		        response.setMsg(ResponseCode.CommonCode.OK.c());
		        return response;
		    }
		    orderInfo.setOrderStatus(Constants.OrderStatus.Paid.v().intValue());
		    orderInfo.setPayTime(new Date());
		    orderInfo.setPayWay(Constants.PayWay.YNB.v());
		    // orderInfo.setPayType(orderPayBO.getPayType());
		    OrderStore orderStore = orderStoreBiz.selectOrderStoreList(orderInfo.getId()).get(0);
		    // 订单商品佣金
		    OrderExpense orderExpense = this.getOrderExpense(orderInfo.getId(), Constants.OrderType.MAIN.v(),
		            orderInfo.getOrderNo(), orderStore.getStoreId());
		    // 订单支付信息
		    OrderPay orderPay = this.getOrderPayInfo(orderInfo.getOrderNo());
		    
		    // 入库
		    orderBiz.payOrder(orderInfo, orderExpense,orderPay);
		    try{
		        // 减少库存
	            List<Map<String, Object>> stockList = new ArrayList<Map<String, Object>>();
	            List<OrderGoods> orderGoodsList = orderGoodsBiz.getOrderGoodsListByOrderId(orderInfo.getId());
	            for (OrderGoods orderGoods : orderGoodsList) {
	                Map<String, Object> qyMap = new HashMap<String, Object>();
	                qyMap.put("skuId", orderGoods.getSkuId());
	                qyMap.put("num", orderGoods.getGoodsCount());
	                qyMap.put("orderId", orderInfo.getId());
	                stockList.add(qyMap);
	            }
	            stocksBiz.stockNumByOrder(stockList);
	            // 记录日志
	            UserInfoBO userInfoBO = sysUserInfoBiz.getUserInfoByUserNum(orderPay.getUserNum());
	            OrderOptLog orderOptLog = new OrderOptLog();
	            orderOptLog.setOrderId(orderInfo.getId());
	            orderOptLog.setOrderNo(orderInfo.getOrderNo());
	            orderOptLog.setOrderStatus(Constants.OrderStatus.Paid.v().intValue());
	            orderOptLog.setOptNum(orderPay.getUserNum());
	            //orderOptLog.setOptName(userInfoBO.getSysUserRealName());
	            orderOptLog.setOptName(userInfoBO.getSysUserRealName());
	            orderOptLog.setOptTime(new Date());
	            orderOptLog.setOrderType(Constants.OrderType.MAIN.v());
	            orderOptLog.setDelStatus(Constants.Status.Disabled.v());
	            orderOptLogBiz.insertSelective(orderOptLog);
		    }catch(Exception e){
		        logger.error("订单支付，减库存异常，orderNo，{}，异常原因，{}", orderNo,e.getMessage());
		        e.printStackTrace();
		    }
		}

		if (Constants.OrderType.SUB.v() == Integer.parseInt(orderNo.substring(0, 1))) { // 子订单
		    List<OrderStore> orderStoreList = orderStoreBiz.selectOrderStoreListByOrderSubNo(orderNo);
		    OrderStore orderStore = orderStoreList.get(0);
		    // 是否已经支付
		    OrderSubInfo temp = orderSubInfoBiz.selectSubOrdersByOrderNo(orderStore.getOrderSubNo());
		    if(temp != null){
		        response.setCode(ResponseCode.CommonCode.OK.v());
                response.setMsg(ResponseCode.CommonCode.OK.c());
                return response;
		    }
		    
		    // 订单
		    OrderInfo orderInfo = orderInfoBiz.selectByPrimaryKey(orderStore.getOrderId());
		    orderInfo.setOrderStatus(Constants.OrderStatus.Paid.v().intValue());
		    orderInfo.setPayTime(new Date());
		    orderInfo.setPayWay(Constants.PayWay.YNB.v());
		    orderInfo.setHasSub(Constants.Whether.Yes.v());
		    // orderInfo.setPayType(orderPayBO.getPayType());
		    // 子订单
		    OrderSubInfo orderSubInfo = new OrderSubInfo();
		    orderSubInfo.setOrderSubNo(orderStore.getOrderSubNo());
		    orderSubInfo.setOrderInfoId(orderInfo.getId());
		    orderSubInfo.setOrderNo(orderInfo.getOrderNo());
		    orderSubInfo.setUserNum(orderInfo.getUserNum());
		    orderSubInfo.setCreateTime(new Date());
		    orderSubInfo.setOrderStatus(Constants.OrderStatus.Paid.v().intValue());
		    orderSubInfo.setTotalAmount(orderStore.getTotalAmount());
		    orderSubInfo.setCouponAmount(orderStore.getCouponAmount());
		    orderSubInfo.setActualAmount(orderStore.getActualAmount());
		    orderSubInfo.setSaveAmount(orderStore.getSaveAmount());
		    orderSubInfo.setOrderStatus(Constants.OrderStatus.Paid.v().intValue());
		    orderSubInfo.setPayWay(orderInfo.getPayWay());
		    orderSubInfo.setIsDel(Constants.Status.Disabled.v());
		    orderSubInfo.setIsRecDel(Constants.Status.Disabled.v());
		    orderSubInfo.setOrderSource(Constants.OrderSource.PC.v().intValue());
		    orderSubInfo.setPayStatus(Constants.PayStatus.PaySuccess.v());
		    orderSubInfo.setPayTime(new Date());
		    // orderSubInfo.setPayType(orderPayBO.getPayType());
		    // 子订单商品
		    Map<String, Integer> selParam = new HashMap<String, Integer>();
		    selParam.put("orderId", orderInfo.getId());
		    selParam.put("storeId", orderStore.getStoreId());
		    List<OrderGoods> orderGoodsList = orderGoodsBiz.getGoodsByOrderIdAndStoreId(selParam);
		    List<OrderGoodsSub> orderGoodsSubList = new ArrayList<OrderGoodsSub>();
		    for (OrderGoods orderGoods : orderGoodsList) {
		        OrderGoodsSub orderGoodsSub = new OrderGoodsSub();
		        orderGoodsSub.setOrderGoodsId(orderGoods.getId());
		        orderGoodsSub.setCreateTime(new Date());
		        orderGoodsSubList.add(orderGoodsSub);
		    }
		    OrderExpense orderExpense = this.getOrderExpense(orderInfo.getId(), Constants.OrderType.SUB.v(),
		            orderStore.getOrderSubNo(), orderStore.getStoreId());
		    // 入库
		    OrderPay orderPay = this.getOrderPayInfo(orderSubInfo.getOrderSubNo());
		    Integer orderSubId = orderBiz.paySubOrder(orderInfo, orderExpense, orderSubInfo, orderGoodsSubList,orderPay);
		    
		    try{
		        // 减少库存
	            List<Map<String, Object>> stockList = new ArrayList<Map<String, Object>>();
	            for (OrderGoods orderGoods : orderGoodsList) {
	                Map<String, Object> qyMap = new HashMap<String, Object>();
	                qyMap.put("skuId", orderGoods.getSkuId());
	                qyMap.put("num", orderGoods.getGoodsCount());
	                qyMap.put("orderId", orderSubId);
	                stockList.add(qyMap);
	            }
	            stocksBiz.stockNumByOrder(stockList);
	            // 记录日志
	            UserInfoBO userInfoBO = sysUserInfoBiz.getUserInfoByUserNum(orderPay.getUserNum());
	            OrderOptLog orderOptLog = new OrderOptLog();
	            orderOptLog.setOrderId(orderSubId);
	            orderOptLog.setOrderNo(orderStore.getOrderSubNo());
	            orderOptLog.setOrderStatus(Constants.OrderStatus.Paid.v().intValue());
	            /*orderOptLog.setOptNum(userNum);
	            orderOptLog.setOptName(userInfoBO.getSysUserRealName());*/
	            orderOptLog.setOptNum(orderPay.getUserNum());
	            orderOptLog.setOptName(userInfoBO.getSysUserRealName());
	            orderOptLog.setOptTime(new Date());
	            orderOptLog.setOrderType(Constants.OrderType.SUB.v());
	            orderOptLog.setDelStatus(Constants.Status.Disabled.v());
	            orderOptLogBiz.insertSelective(orderOptLog);
            }catch(Exception e){
                logger.error("订单支付，减库存异常，orderNo，{}，异常原因，{}", orderNo,e.getMessage());
                e.printStackTrace();
            }
		}
		response.setCode(ResponseCode.CommonCode.OK.v());
		response.setMsg(ResponseCode.CommonCode.OK.c());
		
		return response;
	}
    
    private OrderExpense getOrderExpense(Integer mainOrderId,Integer orderType,String orderNo,Integer storeId){
        BigDecimal villageExpense = new BigDecimal(0);
        BigDecimal platformExpense = new BigDecimal(0);
        BigDecimal lcExpense = new BigDecimal(0);
        BigDecimal integralExpense = new BigDecimal(0);
        BigDecimal planExpense = new BigDecimal(0);
        BigDecimal seExpense = new BigDecimal(0);
        BigDecimal aExpense = new BigDecimal(0);
        List <OrderGoodsExpenseManual> orderGoodsExpenseManualList = orderGoodsExpenseBiz.selectOrderGoodsExpenseManual(mainOrderId,storeId);
        for(OrderGoodsExpenseManual orderGoodsExpenseManual : orderGoodsExpenseManualList){
            villageExpense = villageExpense.add(orderGoodsExpenseManual.getVillageExpense().multiply(new BigDecimal(orderGoodsExpenseManual.getGoodsCount())));
            platformExpense = platformExpense.add(orderGoodsExpenseManual.getPlatformExpense().multiply(new BigDecimal(orderGoodsExpenseManual.getGoodsCount())));
            lcExpense = lcExpense.add(orderGoodsExpenseManual.getLcExpense().multiply(new BigDecimal(orderGoodsExpenseManual.getGoodsCount())));
            integralExpense = integralExpense.add(orderGoodsExpenseManual.getIntegralExpense().multiply(new BigDecimal(orderGoodsExpenseManual.getGoodsCount())));
            planExpense = planExpense.add(orderGoodsExpenseManual.getPlanExpense().multiply(new BigDecimal(orderGoodsExpenseManual.getGoodsCount())));
            seExpense = seExpense.add(orderGoodsExpenseManual.getSeExpense().multiply(new BigDecimal(orderGoodsExpenseManual.getGoodsCount())));
            aExpense = aExpense.add(orderGoodsExpenseManual.getaExpense().multiply(new BigDecimal(orderGoodsExpenseManual.getGoodsCount())));
        }
        // 订单联系人
        OrderContact orderContact = orderContactBiz.selectOrderContactById(mainOrderId);
        // 订单工程师
        OrderEngineer orderEngineer = orderEngineerBiz.selectByOrderId(mainOrderId);
        // 订单佣金
        OrderExpense orderExpense = new OrderExpense();
        
        if(orderType == Constants.OrderType.MAIN.v()){
            orderExpense.setOrderId(mainOrderId);
        }
        orderExpense.setOrderNo(orderNo);
        orderExpense.setOrderType(orderType);
        orderExpense.setVillageExpense(villageExpense);
        orderExpense.setPlatformExpense(platformExpense);
        orderExpense.setLcExpense(lcExpense);
        orderExpense.setIntegralExpense(integralExpense);
        orderExpense.setPlanExpense(planExpense);
        orderExpense.setSeExpense(seExpense);
        orderExpense.setaExpense(aExpense);
        orderExpense.setVsNum(orderContact.getVsNum());
        orderExpense.setLcNum(orderContact.getLcNum());
        if(orderEngineer != null){
            orderExpense.setSeNum(orderEngineer.getEngineerNum());
        }
        orderExpense.setCreateTime(new Date());
        return orderExpense;
    }
    
    private OrderPay getOrderPayInfo(String orderNo){
        OrderPayBO orderPayBO = orderPayBiz.getOrderPayInfo(orderNo);
        OrderPay orderPay = new OrderPay();
        orderPay.setOrderno(orderPayBO.getOrderNo());
        orderPay.setOrderType(orderPayBO.getOrderType());
        orderPay.setUserNum(orderPayBO.getUserNum());
        orderPay.setPayStatus(Constants.PayStatus.PaySuccess.v());
        
        return orderPay;
    }
    
}
