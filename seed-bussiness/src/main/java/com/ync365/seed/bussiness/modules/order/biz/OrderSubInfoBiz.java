/**    
 * 文件名：OrderSubInfoBiz.java    
 *    
 * 版本信息：    
 * 日期：2015年10月25日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.bussiness.modules.order.biz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.fabric.xmlrpc.base.Array;
import com.ync365.seed.bussiness.modules.order.bo.OrderInfoGoodsBO;
import com.ync365.seed.bussiness.modules.order.bo.OrderOptLogBO;
import com.ync365.seed.bussiness.modules.order.bo.OrderSearch;
import com.ync365.seed.bussiness.modules.order.dao.OrderGoodsChangeInfoMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderGoodsExpenseInfoMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderGoodsExpenseMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderGoodsMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderGoodsMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderGoodsSubMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderOptLogMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderSearchMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderShippingInfoMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderSubInfoMapper;
import com.ync365.seed.bussiness.modules.order.dao.OrderSubInfoMapperManual;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoods;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsChangeInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsExpense;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsExpenseInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoodsSub;
import com.ync365.seed.bussiness.modules.order.entity.OrderOptLog;
import com.ync365.seed.bussiness.modules.order.entity.OrderShippingInfo;
import com.ync365.seed.bussiness.modules.order.entity.OrderSubInfo;
import com.ync365.seed.utils.CloneUtils;
import com.ync365.seed.utils.Constants;

/**    
 *     
 * @Title：OrderSubInfoBiz  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年10月25日 下午4:54:47      
 * @version     
 *     
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class OrderSubInfoBiz {

    @Autowired
    private OrderSubInfoMapper orderSubInfoMapper;
    
    @Autowired
    private OrderSearchMapperManual orderSearchMapperManual;
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;
    @Autowired
    private OrderGoodsMapperManual orderGoodsMapperManual;
    @Autowired
    private OrderGoodsExpenseMapper orderGoodsExpenseMapper;
    @Autowired
    private OrderGoodsSubMapperManual orderGoodsSubMapperManual;
    @Autowired
    private OrderGoodsChangeInfoMapperManual orderGoodsChangeInfoMapperManual;
    @Autowired
    private OrderGoodsExpenseInfoMapperManual orderGoodsExpenseInfoMapperManual;
    @Autowired
    private OrderSubInfoMapperManual orderSubInfoMapperManual;
    @Autowired
    private OrderOptLogMapperManual orderOptLogMapperManual;
    @Autowired
    private OrderShippingInfoMapperManual orderShippingInfoMapperManual;
    
    public OrderSubInfo selectByPrimaryKey (Integer subId) {
        return orderSubInfoMapper.selectByPrimaryKey(subId);
    }
    /**
     * 
     * @Title: updateByPrimaryKeySelective
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年10月29日 下午1:15:23       
     * @version: 
     *
     * @param orderSubInfo
     * @return
     *
     */
    public Integer updateByPrimaryKeySelective(OrderSubInfo orderSubInfo) {
        return orderSubInfoMapper.updateByPrimaryKeySelective(orderSubInfo);
    }
   /**
    * 
    * @Title: selectSubOrdersByOrderNo
    * @Description: TODO    ：    根据子订单编号查询子订单
    * @author: ivan    
    * @date: 2015年10月29日 下午3:21:29       
    * @version: 
    *
    * @param orderSubNo
    * @return
    *
    */
    public OrderSubInfo selectSubOrdersByOrderNo(String orderSubNo) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("orderSubNo", orderSubNo);
        return orderSubInfoMapperManual.selectSubOrdersByOrderNo(map);
    }
    
    /**
     * 根据子订单id查询订单及订单商品详情
     * @param orderId
     * @return orderMap
     */   
       public Map<String, Object> getOrderDetailInfoByOrderId(String orderSubId) {
           OrderSearch orderSearch = orderSearchMapperManual.getSubOrderInfoByOrderId(Integer.parseInt(orderSubId));
           //查询子订单中的所有商品          
           List<OrderGoodsSub> orderGoodsSubList  = orderGoodsSubMapperManual.getOrderGoodsSubListByOrderSubId(Integer.parseInt(orderSubId));
           List<OrderGoods> orderGoodsList = new ArrayList<OrderGoods>();           
           //创建一个list集合用于存储订单商品佣金的改价列表
           List<OrderInfoGoodsBO> goodsExpenseInfoList = new ArrayList<OrderInfoGoodsBO>();
           Map<String, Object> orderMap = new HashMap<String, Object>();            
           Integer orderGoodsCount = 0;//订单中商品总个数 
           
           if(orderGoodsSubList!=null&&orderGoodsSubList.size()>0){
        	   for (OrderGoodsSub orderGoodsSub : orderGoodsSubList) {
        		   OrderGoods orderGoods = orderGoodsMapper.selectByPrimaryKey(orderGoodsSub.getOrderGoodsId());
        		   orderGoodsList.add(orderGoods);
        	   }
        	  if(orderGoodsList!=null&&orderGoodsList.size()>0){
        		  for (OrderGoods orderGoods1 : orderGoodsList) {
        			  //计算订单中商品的总个数
        			  orderGoodsCount = orderGoodsCount+orderGoods1.getGoodsCount();
        			  //创建一个对象用于保存改价详情数据
                      OrderInfoGoodsBO orderInfoGoodsBO = new OrderInfoGoodsBO();
        			  if(orderGoods1.getIsChange() == Constants.OrderGoodsIsChangePrice.YES.v()){//说明商品有改价        				 		  
        				//根据订单商品id获取商品的佣金改价记录			
         				List<OrderGoodsExpenseInfo> list = orderGoodsExpenseInfoMapperManual.getHoldGoldByOrderGoodsId(orderGoods1.getId());
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
         					CloneUtils.cloneObject(orderGoods1, orderInfoGoodsBO);
         					goodsExpenseInfoList.add(orderInfoGoodsBO);
         				}      				  
	    		       	  				  
        			  }
        		  }        		 
        	  } 
        	  	orderSearch.setOrderNo(orderSearch.getOrderSubNo());
        		orderSearch.setOrderGoodsCount(orderGoodsCount);            	
	           	orderMap.put("orderInfo", orderSearch);
	           	orderMap.put("orderGoodsList", orderGoodsList);
	           	orderMap.put("orderGoodsDetail", goodsExpenseInfoList);
               
               Map<String,Object> map1 = new HashMap<String,Object>();
               map1.put("orderId", Integer.parseInt(orderSubId));       
               List<OrderOptLog> orderOptLogList = orderOptLogMapperManual.getOrderLogListByIdType(map1);
               List<OrderOptLogBO> listBO = new ArrayList<OrderOptLogBO>();
               OrderShippingInfo shippingInfoDB = orderShippingInfoMapperManual.selectByOrderId(Integer.parseInt(orderSubId));
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
           }                     
           return orderMap;           
       }
	/**
     * 后台查询退货标记的详情页
     * @param orderId
     * @return
     */
	public Map<String, Object> getReturnOrderDetailInfoByOrderId(String orderSubId) {
		 OrderSearch orderSearch = orderSearchMapperManual.getSubOrderInfoByOrderId(Integer.parseInt(orderSubId));
         //查询子订单中的所有商品          
         List<OrderGoodsSub> orderGoodsSubList  = orderGoodsSubMapperManual.getOrderGoodsSubListByOrderSubId(Integer.parseInt(orderSubId));
         List<OrderGoods> orderGoodsList = new ArrayList<OrderGoods>();           
         //创建一个list集合用于存储订单商品佣金的列表
         List<OrderInfoGoodsBO> goodsExpenseInfoList = new ArrayList<OrderInfoGoodsBO>();         
         //创建一个list集合只用于存储订单商品及佣金改价详情的列表
         List<OrderInfoGoodsBO> goodsExpenseChangeInfoList = new ArrayList<OrderInfoGoodsBO>();         
         Map<String, Object> orderMap = new HashMap<String, Object>();            
         Integer orderGoodsCount = 0;//订单中商品总个数          
         Boolean flag = false;
         if(orderGoodsSubList!=null&&orderGoodsSubList.size()>0){
      	   for (OrderGoodsSub orderGoodsSub : orderGoodsSubList) {
      		   OrderGoods orderGoods = orderGoodsMapper.selectByPrimaryKey(orderGoodsSub.getOrderGoodsId());
      		   orderGoodsList.add(orderGoods);      		  
      	   }
      	  if(orderGoodsList!=null&&orderGoodsList.size()>0){
      		  for (OrderGoods orderGoods1 : orderGoodsList) {
      			  //计算订单中商品的总个数
      			  orderGoodsCount = orderGoodsCount+orderGoods1.getGoodsCount();
      			  //创建一个对象用于保存改价详情数据
                    OrderInfoGoodsBO orderInfoGoodsBO = new OrderInfoGoodsBO();
      			  if(orderGoods1.getIsChange() == Constants.OrderGoodsIsChangePrice.YES.v()){//说明商品有改价        	
      				  flag = true;
      				//根据订单商品id获取商品的佣金改价记录			
       				List<OrderGoodsExpenseInfo> list = orderGoodsExpenseInfoMapperManual.getHoldGoldByOrderGoodsId(orderGoods1.getId());
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
       						orderInfoGoodsBO.setLcExpenseChange(expnese2.getLcExpense());       						
       						orderInfoGoodsBO.setPlanExpenseChange(expnese2.getPlanExpense());    					
       					}
       					CloneUtils.cloneObject(orderGoods1, orderInfoGoodsBO);
       					goodsExpenseInfoList.add(orderInfoGoodsBO);
       					goodsExpenseChangeInfoList.add(orderInfoGoodsBO);
       			    }    		       	  				  
      			  }else{//未改价
     				//改价前商品的佣金			
     	        	OrderGoodsExpense orderGoodsExpense = orderGoodsExpenseMapper.selectByPrimaryKey(orderGoods1.getId());			
     				OrderInfoGoodsBO orderInfoGoodsBO2 = new OrderInfoGoodsBO();			
     				orderInfoGoodsBO2.setVillageExpense(orderGoodsExpense.getVillageExpense());     				
     				orderInfoGoodsBO2.setLcExpense(orderGoodsExpense.getLcExpense());     				
     				orderInfoGoodsBO2.setPlanExpense(orderGoodsExpense.getPlanExpense());
     				CloneUtils.cloneObject(orderGoods1, orderInfoGoodsBO2);
     				goodsExpenseInfoList.add(orderInfoGoodsBO2);     				
     			}
      		 } 
      		orderSearch.setOrderNo(orderSearch.getOrderSubNo());
      		orderSearch.setOrderGoodsCount(orderGoodsCount);  
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
       	
     }                     
     return orderMap;   
    }
}
