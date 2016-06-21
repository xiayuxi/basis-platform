package com.ync365.seed.bussiness.modules.order.biz;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.order.bo.OrderBO;
import com.ync365.seed.bussiness.modules.order.bo.OrderGoodsBO;
import com.ync365.seed.bussiness.modules.order.bo.OrderInfoBO;
import com.ync365.seed.bussiness.modules.order.bo.OrderInfoGoodsBO;
import com.ync365.seed.bussiness.modules.order.bo.OrderOptLogBO;
import com.ync365.seed.bussiness.modules.order.bo.OrderPromotionBO;
import com.ync365.seed.bussiness.modules.order.bo.OrderSearch;
import com.ync365.seed.bussiness.modules.order.bo.OrderStoreBO;
import com.ync365.seed.bussiness.modules.order.dao.OrderAddressMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderContactMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderCouponMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderEngineerMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderGoodsChangeInfoMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderGoodsExpenseInfoMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderGoodsExpenseInfoMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderGoodsExpenseMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderGoodsMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderGoodsMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderInfoMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderInfoMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderOptLogMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderOptLogMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderPromotionGoodsMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderPromotionMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderSearchMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderShippingInfoMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderShippingInfoMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderStoreMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderSubInfoMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderSubInfoMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderUserMapper;
import com.ync365.seed.bussiness.modules.order.dao.UserCouponMapper;
import com.ync365.seed.bussiness.modules.order.entity.OrderAddress;
import com.ync365.seed.bussiness.modules.order.entity.OrderContact;
import com.ync365.seed.bussiness.modules.order.entity.OrderCoupon;
import com.ync365.seed.bussiness.modules.order.entity.OrderEngineer;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoods;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsChangeInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsExpense;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsExpenseInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderOptLog;
import com.ync365.seed.bussiness.modules.order.entity.OrderPromotion;
import com.ync365.seed.bussiness.modules.order.entity.OrderPromotionGoods;
import com.ync365.seed.bussiness.modules.order.entity.OrderShippingInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderStore;
import com.ync365.seed.bussiness.modules.order.entity.OrderSubInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderUser;
import com.ync365.seed.bussiness.modules.order.entity.UserCoupon;
import com.ync365.seed.bussiness.modules.user.dao.SysUserInfoMapper;
import com.ync365.seed.bussiness.modules.user.dao.SysUserMapper;
import com.ync365.seed.utils.CloneUtils;
import com.ync365.seed.utils.Constants;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class OrderInfoBiz { 
    
    @Autowired
    private OrderSearchMapperManual orderSearchMapperManual;
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;
    @Autowired
    private OrderGoodsMapperManual orderGoodsMapperManual;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private OrderInfoMapperManual orderInfoMapperManual;
    @Autowired
    private OrderOptLogMapperManual orderOptLogMapperManual;
    @Autowired
    private OrderSubInfoMapper orderSubInfoMapper;
    @Autowired
    private OrderSubInfoMapperManual orderSubInfoMapperManual;  
    @Autowired
    private OrderPromotionMapper orderPromotionMapper;
    @Autowired
    private OrderContactMapper orderContactMapper;
    @Autowired
    private OrderEngineerMapper orderEngineerMapper;
    @Autowired
    private OrderGoodsExpenseMapper orderGoodsExpenseMapper;
    @Autowired
    private OrderPromotionGoodsMapper orderPromotionGoodsMapper;
    @Autowired
    private OrderStoreMapper orderStoreMapper;
    @Autowired
    private OrderUserMapper orderUserMapper;
    @Autowired
    private OrderAddressMapper orderAddressMapper;
    @Autowired
    private OrderMapperManual orderMapperManual;
    @Autowired
    private OrderGoodsExpenseInfoMapper orderGoodsExpenseInfoMapper;
    @Autowired
    private OrderGoodsChangeInfoMapperManual orderGoodsChangeInfoMapperManual;
    @Autowired
    private OrderGoodsExpenseInfoMapperManual orderGoodsExpenseInfoMapperManual;

    @Autowired
    private SysUserInfoMapper sysUserInfoMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private OrderCouponMapper orderCouponMapper;
    @Autowired
    private UserCouponMapper userCouponMapper;

    @Autowired
    private OrderShippingInfoMapperManual orderShippingInfoMapperManual;

    @Autowired
    private OrderOptLogMapper orderOptLogMapper;
    @Autowired
    private OrderShippingInfoMapper orderShippingInfoMapper;
    //查询订单列表
    public List<OrderSearch> search(OrderSearch search) {       
        List<OrderSearch> list = orderSearchMapperManual.getListByMulti(search);
        return list;
    }
    //查询总记录数
    public int searchCount(OrderSearch search) {        
        int count = orderSearchMapperManual.getCountByMulti(search);
        return count;
    }   
    
    /**
     * 根据订单id查询订单及订单商品信息(此方法是用于加载订单改价列表页面的数据)
     * @param orderId
     * @return orderMap
     */    
    public Map<String, Object> getOrderInfoByOrderId(String orderId) {
        OrderSearch orderSearch = orderSearchMapperManual.getOrderInfoByOrderId(Integer.parseInt(orderId));
        List<OrderGoods> orderGoodsList = orderGoodsMapperManual.getOrderGoodsListByOrderId(Integer.parseInt(orderId));
        //创建一个list集合用于存储订单商品改价列表
        List<OrderGoods> orderGoodsChangeList = new ArrayList<OrderGoods>();
        //创建一个list集合用于存储订单商品佣金的改价列表
        List<OrderInfoGoodsBO> goodsExpenseInfoList = new ArrayList<OrderInfoGoodsBO>();
        //存放查询条件
        Map<String, Object> orderMap = new HashMap<String, Object>();  
        
        //如果有改价则按照以下数据进行显示        
        if(orderSearch.getOrderStatus()== Constants.OrderStatus.ChangePriceNotCheck.v().intValue()){        	
        	for (OrderGoods orderGoods : orderGoodsList) {
        	    //创建一个对象用于保存改价详情数据
                OrderInfoGoodsBO orderInfoGoodsBO = new OrderInfoGoodsBO();
    			if(orderGoods.getIsChange() == Constants.OrderGoodsIsChangePrice.YES.v()){//说明商品有改价
    				//从商品改价记录中取出改价后商品的价格
    				List<OrderGoodsChangeInfo> goodsChangeLogs = orderGoodsChangeInfoMapperManual.getOrderGoodsChangeLogsByOrderGoodsId(orderGoods.getId());
    				if(goodsChangeLogs!=null&&goodsChangeLogs.size()>0){
    					//按时间取出最后一次的改价
        				OrderGoodsChangeInfo goodsChangeInfo = goodsChangeLogs.get(goodsChangeLogs.size()-1);				
        				BigDecimal actualPrice = new BigDecimal(goodsChangeInfo.getAfterGoodsPrice().doubleValue());
        				orderGoods.setActualPrice(actualPrice);
        				BigDecimal count = new BigDecimal(orderGoods.getGoodsCount());
        				if(count !=null && count.intValue()>0){
        					BigDecimal goodsActualAmount =  count.multiply(actualPrice);
        					orderGoods.setActualAmount(goodsActualAmount);//改价后商品的总金额
        				}
        				orderGoodsChangeList.add(orderGoods);  
        				orderInfoGoodsBO.setChangeName(goodsChangeInfo.getChangeName());//改价人
        				orderInfoGoodsBO.setChangeTime(goodsChangeInfo.getChangeTime());//改价时间
        				if(goodsChangeInfo.getAuditName()!=null&&goodsChangeInfo.getAuditTime()!=null){
        					orderInfoGoodsBO.setAuditName(goodsChangeInfo.getAuditName());//审核人
        					orderInfoGoodsBO.setAuditTime(goodsChangeInfo.getAuditTime());//审核时间
        				}		
    				}    				
    				//根据订单商品id获取商品的佣金改价记录			
    				List<OrderGoodsExpenseInfo> list = orderGoodsExpenseInfoMapperManual.getHoldGoldByOrderGoodsId(orderGoods.getId());
    				if(list !=null && list.size()>0){    					
    					//改价前商品的佣金				
    					OrderGoodsExpenseInfo goodsExpenseInfo = list.get(0);
    					orderInfoGoodsBO.setVillageExpense(goodsExpenseInfo.getVillageExpense());
    					orderInfoGoodsBO.setPlatformExpense(goodsExpenseInfo.getPlatformExpense());
    					orderInfoGoodsBO.setLcExpense(goodsExpenseInfo.getLcExpense());
    					orderInfoGoodsBO.setIntegralExpense(goodsExpenseInfo.getIntegralExpense());
    					orderInfoGoodsBO.setPlanExpense(goodsExpenseInfo.getPlanExpense());
    					//改价后商品的佣金
    					OrderGoodsExpenseInfo expnese2 = list.get(list.size()-1);
    					if(expnese2 !=null ){
    						orderInfoGoodsBO.setVillageExpenseChange(expnese2.getVillageExpense());
    						orderInfoGoodsBO.setPlatformExpenseChange(expnese2.getPlatformExpense());
    						orderInfoGoodsBO.setLcExpenseChange(expnese2.getLcExpense());
    						orderInfoGoodsBO.setIntegralExpenseChange(expnese2.getIntegralExpense());
    						orderInfoGoodsBO.setPlanExpenseChange(expnese2.getPlanExpense());    					
    					}    					
    					goodsExpenseInfoList.add(orderInfoGoodsBO);
    				}   		
    				
    			}else{
    				orderGoodsChangeList.add(orderGoods);
    			}
        	}
        	//计算改价后订单的应付总金额
        	BigDecimal orderActualAmount = new BigDecimal(0);	 
        	for (OrderGoods orderGoods : orderGoodsChangeList) {			       					
				orderActualAmount = orderActualAmount.add(orderGoods.getActualAmount());			   				
				
        	} 
        	orderSearch.setActualAmount(orderActualAmount);     
        	orderMap.put("orderInfo", orderSearch);//订单主表信息以及订单联系人信息
        	orderMap.put("orderGoodsList", orderGoodsChangeList);//订单商品改价信息list集合
        }else{//未改价
        	orderMap.put("orderInfo", orderSearch);//订单主表信息以及订单联系人信息
        	orderMap.put("orderGoodsList", orderGoodsList);//订单商品信息list集合
        }        
        orderMap.put("orderGoodsDetail", goodsExpenseInfoList);//订单商品佣金的详情    
       
        return orderMap;
        
    }
    
    /**
     * 根据订单id查询订单及订单商品信息(此方法是用于加载订单审核列表页面的数据)
     * @param orderId
     * @return orderMap
     */    
    public Map<String, Object> getOrderCheckInfoByOrderId(String orderId) {
        OrderSearch orderSearch = orderSearchMapperManual.getOrderInfoByOrderId(Integer.parseInt(orderId));
        List<OrderGoods> orderGoodsList = orderGoodsMapperManual.getOrderGoodsListByOrderId(Integer.parseInt(orderId));
        //创建一个list集合用于存储订单商品改价列表
        List<OrderGoods> orderGoodsChangeList = new ArrayList<OrderGoods>();
        //创建一个list集合用于存储订单商品佣金的改价列表
        List<OrderInfoGoodsBO> goodsExpenseInfoList = new ArrayList<OrderInfoGoodsBO>();
        //存放查询条件
        Map<String, Object> orderMap = new HashMap<String, Object>();  
        
        //如果有改价则按照以下数据进行显示        
        if(orderSearch.getOrderStatus()== Constants.OrderStatus.ChangePriceNotCheck.v().intValue()){        	
        	for (OrderGoods orderGoods : orderGoodsList) {
        	    //创建一个对象用于保存改价详情数据
                OrderInfoGoodsBO orderInfoGoodsBO = new OrderInfoGoodsBO();
    			if(orderGoods.getIsChange() == Constants.OrderGoodsIsChangePrice.YES.v()){//说明商品有改价
    				//从商品改价记录中取出改价后商品的价格
    				List<OrderGoodsChangeInfo> goodsChangeLogs = orderGoodsChangeInfoMapperManual.getOrderGoodsChangeLogsByOrderGoodsId(orderGoods.getId());
    				if(goodsChangeLogs!=null&&goodsChangeLogs.size()>0){
    					//按时间取出最后一次的改价
        				OrderGoodsChangeInfo goodsChangeInfo = goodsChangeLogs.get(goodsChangeLogs.size()-1);				
        				BigDecimal actualPrice = new BigDecimal(goodsChangeInfo.getAfterGoodsPrice().doubleValue());
        				orderGoods.setActualPrice(actualPrice);
        				BigDecimal count = new BigDecimal(orderGoods.getGoodsCount());
        				if(count !=null && count.intValue()>0){
        					BigDecimal goodsActualAmount =  count.multiply(actualPrice);
        					orderGoods.setActualAmount(goodsActualAmount);//改价后商品的总金额
        				}
        				orderGoodsChangeList.add(orderGoods);  
        				orderInfoGoodsBO.setChangeName(goodsChangeInfo.getChangeName());//改价人
        				orderInfoGoodsBO.setChangeTime(goodsChangeInfo.getChangeTime());//改价时间
        				if(goodsChangeInfo.getAuditName()!=null&&goodsChangeInfo.getAuditTime()!=null){
        					orderInfoGoodsBO.setAuditName(goodsChangeInfo.getAuditName());//审核人
        					orderInfoGoodsBO.setAuditTime(goodsChangeInfo.getAuditTime());//审核时间
        				}		
    				}    				
    				//根据订单商品id获取商品的佣金改价记录			
    				List<OrderGoodsExpenseInfo> list = orderGoodsExpenseInfoMapperManual.getHoldGoldByOrderGoodsId(orderGoods.getId());
    				if(list !=null && list.size()>0){    					
    					//改价前商品的佣金				
    					OrderGoodsExpenseInfo goodsExpenseInfo = list.get(0);
    					orderInfoGoodsBO.setVillageExpense(goodsExpenseInfo.getVillageExpense());
    					orderInfoGoodsBO.setPlatformExpense(goodsExpenseInfo.getPlatformExpense());
    					orderInfoGoodsBO.setLcExpense(goodsExpenseInfo.getLcExpense());
    					orderInfoGoodsBO.setIntegralExpense(goodsExpenseInfo.getIntegralExpense());
    					orderInfoGoodsBO.setPlanExpense(goodsExpenseInfo.getPlanExpense());
    					//改价后商品的佣金
    					OrderGoodsExpenseInfo expnese2 = list.get(list.size()-1);
    					if(expnese2 !=null ){
    						orderInfoGoodsBO.setVillageExpenseChange(expnese2.getVillageExpense());
    						orderInfoGoodsBO.setPlatformExpenseChange(expnese2.getPlatformExpense());
    						orderInfoGoodsBO.setLcExpenseChange(expnese2.getLcExpense());
    						orderInfoGoodsBO.setIntegralExpenseChange(expnese2.getIntegralExpense());
    						orderInfoGoodsBO.setPlanExpenseChange(expnese2.getPlanExpense());    					
    					}    					
    					goodsExpenseInfoList.add(orderInfoGoodsBO);
    				}   		
    				
    			}
        	}
        	//计算改价后订单的应付总金额
        	BigDecimal orderActualAmount = new BigDecimal(0);	 
        	for (OrderGoods orderGoods : orderGoodsChangeList) {			       					
				orderActualAmount = orderActualAmount.add(orderGoods.getActualAmount());			   				
				
        	} 
        	orderSearch.setActualAmount(orderActualAmount);     
        	orderMap.put("orderInfo", orderSearch);//订单主表信息以及订单联系人信息
        	orderMap.put("orderGoodsList", orderGoodsChangeList);//订单商品改价信息list集合
        }else{//未改价
        	orderMap.put("orderInfo", orderSearch);//订单主表信息以及订单联系人信息
        	orderMap.put("orderGoodsList", orderGoodsList);//订单商品信息list集合
        }        
        orderMap.put("orderGoodsDetail", goodsExpenseInfoList);//订单商品佣金的详情    
       
        return orderMap;
        
    }
    
 /**
  * 根据订单id查询订单及订单商品详情(包括改价，未改价，改价未审核，改价已审核)
  * (此方法是用于加载订单详情页的数据)
  * @param orderId
  * @return orderMap
  */   
    public Map<String, Object> getOrderDetailInfoByOrderId(String orderId) {
        OrderSearch orderSearch = orderSearchMapperManual.getOrderInfoByOrderId(Integer.parseInt(orderId));
        //查询订单中的所有商品
        List<OrderGoods> orderGoodsList = orderGoodsMapperManual.getOrderGoodsListByOrderId(Integer.parseInt(orderId));
        //创建一个list集合用于存储订单中未改价的商品
        List<OrderGoods> goodsNotChangeList = new ArrayList<OrderGoods>();
        
        //创建一个list集合用于存储订单商品改价列表
        List<OrderGoods> orderGoodsChangeList = new ArrayList<OrderGoods>();
        //创建一个list集合用于存储订单商品佣金的改价列表
        List<OrderInfoGoodsBO> goodsExpenseInfoList = new ArrayList<OrderInfoGoodsBO>();
        //创建一个map集合存放查询条件
        Map<String, Object> orderMap = new HashMap<String, Object>();  
        Integer orderGoodsCount = 0;//订单中商品总个数
     	BigDecimal orderActualAmount = new BigDecimal(0);//订单总金额
     	BigDecimal goodsNotChangeAmount = new BigDecimal(0);//未改价的商品总金额
     	BigDecimal goodsChangeAmount = new BigDecimal(0);//改价的商品总金额
     	BigDecimal couponAmount = new BigDecimal(0);//改价的商品总金额
     	Boolean flag = false;
        //如果有改价则按照以下数据进行显示           	
    	if(orderGoodsList!=null&&orderGoodsList.size()>0){
    		for (OrderGoods orderGoods : orderGoodsList) {
    			orderGoodsCount = orderGoodsCount+orderGoods.getGoodsCount();
        	    //创建一个对象用于保存改价详情数据
                OrderInfoGoodsBO orderInfoGoodsBO = new OrderInfoGoodsBO();
    			if(orderGoods.getIsChange() == Constants.OrderGoodsIsChangePrice.YES.v()){//说明商品有改价
    				flag = true;
    				//从商品改价记录中取出改价后商品的价格
    				List<OrderGoodsChangeInfo> goodsChangeLogs = orderGoodsChangeInfoMapperManual.getOrderGoodsChangeLogsByOrderGoodsId(orderGoods.getId());
    				if(goodsChangeLogs!=null&&goodsChangeLogs.size()>0){
    					//按时间取出最后一次的改价
        				OrderGoodsChangeInfo goodsChangeInfo = goodsChangeLogs.get(goodsChangeLogs.size()-1);				
        				BigDecimal actualPrice = new BigDecimal(goodsChangeInfo.getAfterGoodsPrice().doubleValue());
        				orderGoods.setActualPrice(actualPrice);
        				BigDecimal count = new BigDecimal(orderGoods.getGoodsCount());
        				if(count !=null && count.intValue()>0){
        					BigDecimal goodsActualAmount =  count.multiply(actualPrice);
        					orderGoods.setActualAmount(goodsActualAmount);//改价后商品的总金额
        				}
        				orderGoodsChangeList.add(orderGoods); //每遍历完一个商品就把它放到这个集合里
        				orderInfoGoodsBO.setChangeName(goodsChangeInfo.getChangeName());//改价人
        				orderInfoGoodsBO.setChangeTime(goodsChangeInfo.getChangeTime());//改价时间
        				if(goodsChangeInfo.getAuditName()!=null&&goodsChangeInfo.getAuditTime()!=null){
        					orderInfoGoodsBO.setAuditName(goodsChangeInfo.getAuditName());//审核人
        					orderInfoGoodsBO.setAuditTime(goodsChangeInfo.getAuditTime());//审核时间
        				}		
    				}    				
    				//根据订单商品id获取商品的佣金改价记录			
    				List<OrderGoodsExpenseInfo> list = orderGoodsExpenseInfoMapperManual.getHoldGoldByOrderGoodsId(orderGoods.getId());
    				if(list !=null && list.size()>0){    					
    					//改价前商品的佣金				
    					OrderGoodsExpenseInfo goodsExpenseInfo = list.get(0);
    					orderInfoGoodsBO.setVillageExpense(goodsExpenseInfo.getVillageExpense());
    					orderInfoGoodsBO.setPlatformExpense(goodsExpenseInfo.getPlatformExpense());
    					orderInfoGoodsBO.setLcExpense(goodsExpenseInfo.getLcExpense());
    					orderInfoGoodsBO.setIntegralExpense(goodsExpenseInfo.getIntegralExpense());
    					orderInfoGoodsBO.setPlanExpense(goodsExpenseInfo.getPlanExpense());
    					//改价后商品的佣金
    					OrderGoodsExpenseInfo expnese2 = list.get(list.size()-1);
    					if(expnese2 !=null ){
    						orderInfoGoodsBO.setVillageExpenseChange(expnese2.getVillageExpense());
    						orderInfoGoodsBO.setPlatformExpenseChange(expnese2.getPlatformExpense());
    						orderInfoGoodsBO.setLcExpenseChange(expnese2.getLcExpense());
    						orderInfoGoodsBO.setIntegralExpenseChange(expnese2.getIntegralExpense());
    						orderInfoGoodsBO.setPlanExpenseChange(expnese2.getPlanExpense());    					
    					}
    					CloneUtils.cloneObject(orderGoods, orderInfoGoodsBO);
    					goodsExpenseInfoList.add(orderInfoGoodsBO);
    				}  				
    			}else{
    				goodsNotChangeList.add(orderGoods);
    				if(goodsNotChangeList!=null&&goodsNotChangeList.size()>0){
    					
    					for (OrderGoods orderGoods1 : goodsNotChangeList) {
    						//计算未改价商品的总金额(订单中单类商品)
    						goodsNotChangeAmount = goodsNotChangeAmount.add(orderGoods1.getActualAmount()); 
						}
    				}
    			}
        	}
    		 if(orderGoodsChangeList!=null&&orderGoodsChangeList.size()>0){
    			 
    			 for (OrderGoods orderGoods2 : orderGoodsChangeList) {
             		//计算改价后商品的总金额(订单中单类商品)
    				 goodsChangeAmount = goodsChangeAmount.add(orderGoods2.getActualAmount()); 
     					
    	         }     			
    		 }
			 if(!flag){//说明没有被改价的商品
				//改价前商品中的总数量及订单总金额
	    		for (OrderGoods orderGoods3 : orderGoodsList) {    			
	 				//orderActualAmount = orderActualAmount.add(orderGoods3.getActualAmount());	
	    			orderActualAmount = orderSearch.getActualAmount();
				}	    		
	    		
			 }else{//说明订单中有改价的商品
				 //orderActualAmount = goodsNotChangeAmount.add(goodsChangeAmount);
				 orderActualAmount = orderSearch.getActualAmount();
			 }    		
    	} 
    	couponAmount = orderSearch.getCouponAmount();
		/*if(orderActualAmount!=null&&orderActualAmount.compareTo(couponAmount)==1){//如果订单金额大于红包金额
			//计算订单中使用红包之后的应付总金额
    		//orderActualAmount = orderActualAmount.subtract(couponAmount);	
		}else{//订单金额小于使用的红包金额
			orderActualAmount=new BigDecimal(0);
		}*/
    	
    	orderSearch.setOrderGoodsCount(orderGoodsCount);
     	orderSearch.setActualAmount(orderActualAmount); 
    	orderMap.put("orderInfo", orderSearch);//订单主表信息以及订单联系人信息
    	orderMap.put("orderGoodsList", orderGoodsList);//订单商品信息list集合
    	orderMap.put("orderGoodsDetail", goodsExpenseInfoList);//订单商品佣金的详情    	
        
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("orderId", Integer.parseInt(orderId));       
        List<OrderOptLog> orderOptLogList = orderOptLogMapperManual.getOrderLogListByIdType(map1);
        List<OrderOptLogBO> listBO = new ArrayList<OrderOptLogBO>();
        OrderShippingInfo shippingInfoDB = orderShippingInfoMapperManual.selectByOrderId(Integer.parseInt(orderId));
        for (OrderOptLog orderOptLog : orderOptLogList) {
        	OrderOptLogBO orderOptLogBO = new OrderOptLogBO();
        	CloneUtils.cloneObject(orderOptLog, orderOptLogBO);
        	if(shippingInfoDB!=null){
        		orderOptLogBO.setPreparedNo(shippingInfoDB.getPreparedNo());
            	orderOptLogBO.setStockNo(shippingInfoDB.getStockNo());
            	orderOptLogBO.setDelivelyNo(shippingInfoDB.getDelivelyNo());
        	}        	
        	listBO.add(orderOptLogBO);
		}       
        
        orderMap.put("orderOptLogList", listBO);       
        return orderMap;
        
    }
    /**
     * 根据用户的编号获取us或vs或lc的交易订单
     * @param userNum
     * @return
     */
    public List<OrderInfoBO> getOrderListByUserNum(String userNum){
    	List<OrderInfoBO> list = new ArrayList<OrderInfoBO>();
    	if(StringUtils.isNotBlank(userNum)){
    		list = orderInfoMapperManual.getOrderListByUserNum(userNum);    		 
    	}
    	return list;
    }
    
    /**
     * 获取供应商交易订单列表
     * @param orderId
     * @param userNum
     * @return
     */
    public List<OrderInfoGoodsBO> getSupplierOrderList(String storeId){
    	List<OrderInfoBO> orderList = orderInfoMapperManual.getSupplierOrderListByStoreId(Integer.parseInt(storeId));
    	List<OrderInfoGoodsBO> orderInfoGoodsBOList = new ArrayList<OrderInfoGoodsBO>();
    	if(orderList !=null && orderList.size()>0 ){
    		for (OrderInfoBO orderInfoBO : orderList) {
        		List<OrderGoods> orderGoodsList = orderGoodsMapperManual.getOrderGoodsListByOrderId(orderInfoBO.getId());
        		if(orderGoodsList !=null && orderGoodsList.size()>0 ){
        			for (OrderGoods orderGoods : orderGoodsList) {
            			OrderInfoGoodsBO  orderInfoGoodsBO = new OrderInfoGoodsBO();
            			orderInfoGoodsBO.setOrderNo(orderInfoBO.getOrderNo());
            			orderInfoGoodsBO.setCreateTime(orderInfoBO.getCreateTime());
            			orderInfoGoodsBO.setOrderStatus(orderInfoBO.getOrderStatus());
            			CloneUtils.cloneObject(orderGoods, orderInfoGoodsBO);
            			orderInfoGoodsBOList.add(orderInfoGoodsBO);
        			}
        		}
        		
    		}   
    	}    	
    	return orderInfoGoodsBOList;
    }   
    @Transactional
    //取消订单
    public void cancleOrder(String orderId,String userNum, Map<String, String> param) {          
  
        orderInfoMapperManual.cancleOrder(Integer.parseInt(orderId));
        
    }  
    /**
	 * 出库管理各种订单通用的biz方法
	 * @param search
	 * @return
	 */    
	//通过订单的状态查询订单列表
	public List<OrderSearch> searchOrderByOrderStatus(OrderSearch search) {			
		return orderSearchMapperManual.searchOrderByOrderStatus(search);
	}
	//通过订单的状态查询订单记录数
	public int searchCountOrderByOrderStatus(OrderSearch search) {		
		return orderSearchMapperManual.searchCountOrderByOrderStatus(search);
	}
    //批量生成配货单或出库单或发货单
    @Autowired
    private OrderNoGenBiz orderNoGenBiz;
    @Transactional    
    public void updateOrderStatusByOrderId(List<Integer> orderIdList,List<Integer> orderTypeList,OrderOptLog orderOptLog,Integer orderStatus) {		
		if(orderIdList!=null && orderIdList.size()>0 ){
			for (int i=0;i<orderIdList.size();i++) {
				//比如orderId:76,78,79
				//比如orderType:0,1,1	
			if(orderTypeList!=null && orderTypeList.size()>0 ){
				if(orderTypeList.get(i)==Constants.OrderType.MAIN.v()){//主订单
					OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderIdList.get(i));
	    			
					Map <String,Object> map = new HashMap<String,Object>();
	                map.put("orderId", orderIdList.get(i));
	                //主订单之前的状态
	                map.put("orderInfoBefStatus", orderInfo.getOrderStatus());
	                map.put("toStatus", orderStatus);		                
	                    
	                orderInfoMapperManual.updateOrderStatusByOrderId(map);
	                //生成主订单的订单操作记录 		
					orderOptLog.setOrderId(orderInfo.getId());
					orderOptLog.setOrderNo(orderInfo.getOrderNo());
					orderOptLog.setOrderType(Constants.OrderType.MAIN.v().intValue());
					orderOptLog.setOrderStatus(orderStatus);
					orderOptLog.setDelStatus(Constants.IsDelete.NO.v());				
					orderOptLog.setOptTime(orderOptLog.getOptTime());
					orderOptLogMapper.insertSelective(orderOptLog);
					
					//生成主订单订单配货信息或出库信息或发货信息
					OrderShippingInfo orderShippingInfo = new OrderShippingInfo();
					orderShippingInfo.setOrderId(orderIdList.get(i));
					orderShippingInfo.setOrderNo(orderInfo.getOrderNo());
					orderShippingInfo.setOrderType(Constants.OrderType.MAIN.v().intValue());
					if(orderStatus==Constants.OrderStatus.PreparedGoods.v().intValue()){
						orderShippingInfo.setPreparedTime(orderOptLog.getOptTime());					
						orderShippingInfo.setPreparedNo(getOrderNumberByStatus(orderStatus));
						orderShippingInfoMapper.insertSelective(orderShippingInfo);
					}
					if(orderStatus==Constants.OrderStatus.StockOut.v().intValue()){
						OrderShippingInfo stockOrder = orderShippingInfoMapperManual.selectByOrderId(orderIdList.get(i));
						stockOrder.setStockTime(orderOptLog.getOptTime());
						stockOrder.setStockNo(getOrderNumberByStatus(orderStatus));
						orderShippingInfoMapper.updateByPrimaryKeySelective(stockOrder);
					}
					if(orderStatus==Constants.OrderStatus.Delivery.v().intValue()){
						OrderShippingInfo deliveryOrder = orderShippingInfoMapperManual.selectByOrderId(orderIdList.get(i));
						deliveryOrder.setDelivelyTime(orderOptLog.getOptTime());
						deliveryOrder.setDelivelyNo(getOrderNumberByStatus(orderStatus));
						orderShippingInfoMapper.updateByPrimaryKeySelective(deliveryOrder);
					} 			
				}
				if(orderTypeList.get(i)==Constants.OrderType.SUB.v()){//子订单
					OrderSubInfo orderSubInfo = orderSubInfoMapper.selectByPrimaryKey(orderIdList.get(i));
					
					Map <String,Object> map = new HashMap<String,Object>();
	                map.put("orderId", orderIdList.get(i));
	                //子订单之前的状态
                    map.put("subInfoBefStatus", orderSubInfo.getOrderStatus());	
	                map.put("toStatus", orderStatus);
	                orderSubInfoMapperManual.updateSubOrderStatusByOrderId(map); 
                    
                    //生成子订单的订单操作记录            
    				OrderOptLog subOrderOptLog = new OrderOptLog();
    				subOrderOptLog.setOrderId(orderIdList.get(i));
    				subOrderOptLog.setOrderNo(orderSubInfo.getOrderNo());
    				subOrderOptLog.setOrderType(Constants.OrderType.SUB.v().intValue());
    				subOrderOptLog.setOrderStatus(orderStatus);
    				subOrderOptLog.setDelStatus(Constants.IsDelete.NO.v());	
    				//操作人的编号和姓名				
    				subOrderOptLog.setOptNum(orderOptLog.getOptNum());
    				subOrderOptLog.setOptName(orderOptLog.getOptName());
    				subOrderOptLog.setOptTime(orderOptLog.getOptTime());
    				orderOptLogMapper.insertSelective(subOrderOptLog);
    				
    				//生成子订单订单配货信息或出库信息或发货信息
    				OrderShippingInfo orderSubShippingInfo = new OrderShippingInfo();
    				orderSubShippingInfo.setOrderId(orderIdList.get(i));
    				orderSubShippingInfo.setOrderNo(orderSubInfo.getOrderNo());
    				orderSubShippingInfo.setOrderType(Constants.OrderType.SUB.v().intValue());
    				if(orderStatus==Constants.OrderStatus.PreparedGoods.v().intValue()){
    					orderSubShippingInfo.setPreparedTime(orderOptLog.getOptTime());					
    					orderSubShippingInfo.setPreparedNo(getOrderNumberByStatus(orderStatus));
    					orderShippingInfoMapper.insertSelective(orderSubShippingInfo);
    				}
    				if(orderStatus==Constants.OrderStatus.StockOut.v().intValue()){
    					OrderShippingInfo stockOrder = orderShippingInfoMapperManual.selectByOrderId(orderIdList.get(i));
    					stockOrder.setStockTime(orderOptLog.getOptTime());
    					stockOrder.setStockNo(getOrderNumberByStatus(orderStatus));
    					orderShippingInfoMapper.updateByPrimaryKeySelective(stockOrder);
    				}
    				if(orderStatus==Constants.OrderStatus.Delivery.v().intValue()){
    					OrderShippingInfo deliveryOrder = orderShippingInfoMapperManual.selectByOrderId(orderIdList.get(i));
    					deliveryOrder.setDelivelyTime(orderOptLog.getOptTime());
    					deliveryOrder.setDelivelyNo(getOrderNumberByStatus(orderStatus));
    					orderShippingInfoMapper.updateByPrimaryKeySelective(deliveryOrder);
    				}	                    
				}
				
			}
			}
		}
    }
    
    //根据订单状态获取配货单号，出库单号，发货单号
    private String getOrderNumberByStatus(Integer orderStatus){
        if(orderStatus==Constants.OrderStatus.PreparedGoods.v().intValue()){                   
           return orderNoGenBiz.getPreparedNo("01");//配货单号        	
        }else if(orderStatus==Constants.OrderStatus.StockOut.v().intValue()){
            return orderNoGenBiz.getStockNo("01");//出库单号
        }else if(orderStatus==Constants.OrderStatus.Delivery.v().intValue()){                    
            return orderNoGenBiz.getDeliveryNo("01");//发货单号
        }else{
        	 return null;
        }                
       
    }
    
    /**
     * 
     * @Title: selectByPrimaryKey
     * @Description: TODO    ：    根据orderId 返回订单信息
     * @author: sunyf    
     * @date: 2015年10月16日 下午3:03:18       
     * @version: 
     *
     * @param orderId
     * @return
     *
     */
    public OrderInfo selectByPrimaryKey(Integer orderId){
        return orderInfoMapper.selectByPrimaryKey(orderId);
    }
    //查询入驻商订单
    public List<OrderSearch> searchPopOrder(OrderSearch search) {
        
        return orderSearchMapperManual.searchPopOrder(search);
    }
    //查询入驻商订单记录数
    public long searchPopOrderCount(OrderSearch search) {
        
        return orderSearchMapperManual.searchPopOrderCount(search);
    }
    
    @Transactional
    public int createOrder(OrderInfo orderInfo, List<OrderGoodsBO> orderGoodsList, List<OrderStoreBO> orderStoreBOList,
            List<OrderPromotionBO> orderPromotionList, OrderContact orderContact,OrderEngineer orderEngineer,OrderUser orderUser,OrderAddress orderAddress) {

        // 订单
        orderInfo.setVersion(1);
        orderInfoMapper.insertSelective(orderInfo);
        Integer orderId = orderInfo.getId();
        
        // 订单商品
        for (OrderGoodsBO orderGoodsBO : orderGoodsList) {
            // 订单商品表
            OrderGoods orderGoods = new OrderGoods();
            CloneUtils.cloneObject(orderGoodsBO, orderGoods);
            orderGoods.setVersion(1);
            orderGoods.setOrderId(orderId);
            orderGoodsMapper.insertSelective(orderGoods);
            Integer orderGoodsId = orderGoods.getId();
            // 订单商品佣金表
            OrderGoodsExpense orderGoodsExpense = new OrderGoodsExpense();
            CloneUtils.cloneObject(orderGoodsBO, orderGoodsExpense);
            orderGoodsExpense.setId(orderGoodsId);
            orderGoodsExpense.setVersion(1);
            orderGoodsExpenseMapper.insertSelective(orderGoodsExpense);
            // 订单佣金记录表
            OrderGoodsExpenseInfo orderGoodsExpenseInfo = new OrderGoodsExpenseInfo();
            CloneUtils.cloneObject(orderGoodsBO, orderGoodsExpenseInfo);
            orderGoodsExpenseInfo.setOrderGoodsId(orderGoodsId);
            orderGoodsExpenseInfoMapper.insertSelective(orderGoodsExpenseInfo);
        }
        
        // 订单供货商
        for (OrderStoreBO orderStoreBO : orderStoreBOList) {
            OrderStore orderStore = orderStoreBO.getOrderStore();
            orderStore.setOrderId(orderId);
            orderStoreMapper.insertSelective(orderStore);
            List<OrderCoupon> orderCouponList = orderStoreBO.getOrderCouponList();
            if(orderCouponList.size() > 0){
               for(OrderCoupon orderCoupon : orderCouponList){
                   if(StringUtils.isNotBlank(orderStore.getOrderSubNo())){
                       orderCoupon.setOrderNo(orderStore.getOrderSubNo());
                       orderCoupon.setOrderType(Constants.OrderType.SUB.v());
                   }else{
                       orderCoupon.setOrderNo(orderStore.getOrderNo());
                       orderCoupon.setOrderType(Constants.OrderType.MAIN.v());
                   }
                   UserCoupon userCoupon = userCouponMapper.selectByPrimaryKey(orderCoupon.getUserCouponId());
                   userCoupon.setIsLock(Constants.Whether.Yes.v());
                   userCoupon.setStatus(Constants.Whether.Yes.v());
                   userCouponMapper.updateByPrimaryKey(userCoupon);
                   orderCouponMapper.insertSelective(orderCoupon);
                   
               } 
            }
        }
        
        // 订单活动
        if (orderPromotionList != null && orderPromotionList.size() > 0) {
            for (OrderPromotionBO orderPromotionBO : orderPromotionList) {
                // 订单活动表
                OrderPromotion orderPromotion = new OrderPromotion();
                CloneUtils.cloneObject(orderPromotionBO, orderPromotion);
                orderPromotion.setOrderId(orderId);
                orderPromotionMapper.insert(orderPromotion);
                Integer orderPromotionId = orderPromotion.getId();
                List <OrderGoodsBO> promGoodsBOList = orderPromotionBO.getPromGoodsBOList();
                for (OrderGoodsBO orderGoodsBO : promGoodsBOList) {
                    // 订单商品表
                    OrderGoods orderGoods = new OrderGoods();
                    CloneUtils.cloneObject(orderGoodsBO, orderGoods);
                    orderGoods.setOrderId(orderId);
                    orderGoodsMapper.insertSelective(orderGoods);
                    // 订单佣金表
                    Integer orderGoodsId = orderGoods.getId();
                    OrderGoodsExpense orderGoodsExpense = new OrderGoodsExpense();
                    CloneUtils.cloneObject(orderGoodsBO, orderGoodsExpense);
                    orderGoodsExpense.setId(orderGoodsId);
                    orderGoodsExpenseMapper.insertSelective(orderGoodsExpense);
                    
                    // 订单佣金记录表
                    OrderGoodsExpenseInfo orderGoodsExpenseInfo = new OrderGoodsExpenseInfo();
                    CloneUtils.cloneObject(orderGoodsBO, orderGoodsExpenseInfo);
                    orderGoodsExpenseInfo.setOrderGoodsId(orderGoodsId);
                    orderGoodsExpenseInfoMapper.insertSelective(orderGoodsExpenseInfo);
                    
                    // 订单活动商品表
                    OrderPromotionGoods orderPromotionGoods = new OrderPromotionGoods();
                    orderPromotionGoods.setOrderGoodsId(orderGoodsId);
                    orderPromotionGoods.setOrderId(orderId);
                    orderPromotionGoods.setOrderPromotionId(orderPromotionId);
                    orderPromotionGoods.setStoreId(orderGoods.getStoreId());
                    orderPromotionGoods.setCreateTime(orderGoods.getCreateTime());
                    orderPromotionGoodsMapper.insertSelective(orderPromotionGoods);
                }
            }
        }
        // 订单联系人
        orderContact.setId(orderId);
        orderContactMapper.insertSelective(orderContact);
        
        // 订单工程师
        if(orderEngineer != null){
            orderEngineer.setId(orderId);
            orderEngineerMapper.insertSelective(orderEngineer);
        }
        
        // 下单人
        orderUser.setId(orderId);
        orderUserMapper.insertSelective(orderUser);
        
        // 下单地址
        orderAddress.setId(orderId);
        orderAddressMapper.insertSelective(orderAddress);
        return orderId;
    }
    /**
     * 
     * @Title: updateByPrimaryKeySelective
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年10月29日 下午1:12:09       
     * @version: 
     *
     * @param orderInfo
     * @return
     *
     */
    public Integer updateByPrimaryKeySelective (OrderInfo orderInfo) {
       return orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
    }
    /**
     * 查询需要取消的订单
     * @Title: selectOrderNeedCancel
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年11月2日 下午2:55:11       
     * @version: 
     *
     * @param orderNo
     * @return
     *
     */
    public List<OrderInfo> selectOrderNeedCancel() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, -12);
        Date currentDate = calendar.getTime();
        Map<String,Object>map = new HashMap<String,Object> ();
        
        map.put("currentDate", currentDate);
        map.put("status", Constants.OrderStatus.NotPay.v().intValue());
        return orderInfoMapperManual.selectOrderNeedCancel(map);
    }
    /**
     * 后台查询退货标记的详情页
     * @param orderId
     * @return
     */
	public Map<String, Object> getReturnOrderDetailInfoByOrderId(String orderId) {
		OrderSearch orderSearch = orderSearchMapperManual.getOrderInfoByOrderId(Integer.parseInt(orderId));
        List<OrderGoods> orderGoodsList = orderGoodsMapperManual.getOrderGoodsListByOrderId(Integer.parseInt(orderId));
        //创建一个list集合用于存储订单商品改价列表
        List<OrderGoods> orderGoodsChangeList = new ArrayList<OrderGoods>();
        //创建一个list集合用于存储订单商品佣金的列表
        List<OrderInfoGoodsBO> goodsExpenseInfoList = new ArrayList<OrderInfoGoodsBO>();
        //创建一个list集合只用于存储订单商品及佣金改价详情的列表
        List<OrderInfoGoodsBO> goodsExpenseChangeInfoList = new ArrayList<OrderInfoGoodsBO>();   
        //存放查询条件
        Map<String, Object> orderMap = new HashMap<String, Object>();  
        boolean flag = false;
        //如果有改价则按照以下数据进行显示                	
    	for (OrderGoods orderGoods : orderGoodsList) {
    	    //创建一个对象用于保存改价详情数据
            OrderInfoGoodsBO orderInfoGoodsBO = new OrderInfoGoodsBO();
			if(orderGoods.getIsChange() == Constants.OrderGoodsIsChangePrice.YES.v()){//说明商品有改价
				flag = true;
				//从商品改价记录中取出改价后商品的价格
				List<OrderGoodsChangeInfo> goodsChangeLogs = orderGoodsChangeInfoMapperManual.getOrderGoodsChangeLogsByOrderGoodsId(orderGoods.getId());
				if(goodsChangeLogs!=null&&goodsChangeLogs.size()>0){
					//按时间取出最后一次的改价
    				OrderGoodsChangeInfo goodsChangeInfo = goodsChangeLogs.get(goodsChangeLogs.size()-1);				
    				BigDecimal actualPrice = new BigDecimal(goodsChangeInfo.getAfterGoodsPrice().doubleValue());
    				orderGoods.setActualPrice(actualPrice);
    				BigDecimal count = new BigDecimal(orderGoods.getGoodsCount());
    				if(count !=null && count.intValue()>0){
    					BigDecimal goodsActualAmount =  count.multiply(actualPrice);
    					orderGoods.setActualAmount(goodsActualAmount);//改价后商品的总金额
    				}
    				orderGoodsChangeList.add(orderGoods);  
    				orderInfoGoodsBO.setChangeName(goodsChangeInfo.getChangeName());//改价人
    				orderInfoGoodsBO.setChangeTime(goodsChangeInfo.getChangeTime());//改价时间
    				if(goodsChangeInfo.getAuditName()!=null&&goodsChangeInfo.getAuditTime()!=null){
    					orderInfoGoodsBO.setAuditName(goodsChangeInfo.getAuditName());//审核人
    					orderInfoGoodsBO.setAuditTime(goodsChangeInfo.getAuditTime());//审核时间
    				}		
				}    				
				//根据订单商品id获取商品的佣金改价记录			
				List<OrderGoodsExpenseInfo> list = orderGoodsExpenseInfoMapperManual.getHoldGoldByOrderGoodsId(orderGoods.getId());
				if(list !=null && list.size()>0){    					
					//改价前商品的佣金				
					OrderGoodsExpenseInfo goodsExpenseInfo = list.get(0);
					orderInfoGoodsBO.setVillageExpense(goodsExpenseInfo.getVillageExpense());
					//orderInfoGoodsBO.setPlatformExpense(goodsExpenseInfo.getPlatformExpense());
					orderInfoGoodsBO.setLcExpense(goodsExpenseInfo.getLcExpense());
					//orderInfoGoodsBO.setIntegralExpense(goodsExpenseInfo.getIntegralExpense());
					orderInfoGoodsBO.setPlanExpense(goodsExpenseInfo.getPlanExpense());
					//改价后商品的佣金
					OrderGoodsExpenseInfo expnese2 = list.get(list.size()-1);
					if(expnese2 !=null ){
						orderInfoGoodsBO.setVillageExpenseChange(expnese2.getVillageExpense());
						//orderInfoGoodsBO.setPlatformExpenseChange(expnese2.getPlatformExpense());
						orderInfoGoodsBO.setLcExpenseChange(expnese2.getLcExpense());
						//orderInfoGoodsBO.setIntegralExpenseChange(expnese2.getIntegralExpense());
						orderInfoGoodsBO.setPlanExpenseChange(expnese2.getPlanExpense());    					
					}   
					CloneUtils.cloneObject(orderGoods, orderInfoGoodsBO);
					goodsExpenseInfoList.add(orderInfoGoodsBO);
					goodsExpenseChangeInfoList.add(orderInfoGoodsBO);
				}   		
				
			}else{//未改价
				//改价前商品的佣金			
	        	OrderGoodsExpense orderGoodsExpense = orderGoodsExpenseMapper.selectByPrimaryKey(orderGoods.getId());			
				OrderInfoGoodsBO orderInfoGoodsBO2 = new OrderInfoGoodsBO();			
				orderInfoGoodsBO2.setVillageExpense(orderGoodsExpense.getVillageExpense());
				//orderInfoGoodsBO2.setPlatformExpense(orderGoodsExpense.getPlatformExpense());
				orderInfoGoodsBO2.setLcExpense(orderGoodsExpense.getLcExpense());
				//orderInfoGoodsBO.setIntegralExpense(orderGoodsExpense.getIntegralExpense());
				orderInfoGoodsBO2.setPlanExpense(orderGoodsExpense.getPlanExpense());
				CloneUtils.cloneObject(orderGoods, orderInfoGoodsBO2);
				goodsExpenseInfoList.add(orderInfoGoodsBO2);
			}
    	}
        if(flag){
        	//计算改价后订单的应付总金额
        	BigDecimal orderActualAmount = new BigDecimal(0);	 
        	for (OrderGoods orderGoods : orderGoodsList) {			       					
				orderActualAmount = orderActualAmount.add(orderGoods.getActualAmount());			   				
				
        	} 
        	orderSearch.setActualAmount(orderActualAmount);     
        	orderMap.put("orderInfo", orderSearch);//订单主表信息以及订单联系人信息
        	orderMap.put("goodsExpenseInfoList", goodsExpenseInfoList);//订单商品佣金的详情 
        	orderMap.put("goodsExpenseChangeInfoList", goodsExpenseChangeInfoList);//订单商品及佣金改价的详情    
        	orderMap.put("isChangePrice", 1) ;//放一个是否改价的标记，1：表示改价
        }else{//未改价           	
			orderMap.put("orderInfo", orderSearch);//订单主表信息以及订单联系人信息
        	orderMap.put("goodsExpenseInfoList", goodsExpenseInfoList);//订单商品佣金的详情    
        	orderMap.put("isChangePrice", 0) ;//放一个是否改价的标记，0：表示未改价
		}      
        return orderMap;  

	}
}
