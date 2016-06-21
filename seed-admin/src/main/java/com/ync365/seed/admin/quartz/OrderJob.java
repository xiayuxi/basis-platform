/**    
 * 文件名：TestJob.java    
 *    
 * 版本信息：    
 * 日期：2015年11月2日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.admin.quartz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.ync365.seed.bussiness.modules.goods.biz.StocksBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderGoodsBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderInfoBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderOptLogBiz;
import com.ync365.seed.bussiness.modules.order.bo.OrderBO;
import com.ync365.seed.bussiness.modules.order.entity.OrderGoods;
import com.ync365.seed.bussiness.modules.order.entity.OrderInfo;
import com.ync365.seed.bussiness.modules.user.bo.UserInfoBO;
import com.ync365.seed.utils.Constants;

/**    
 *     
 * @Title：TestJob  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年11月2日 上午11:44:41      
 * @version     
 *     
 */
@Controller
public class OrderJob {

    private static final Logger logger = LoggerFactory.getLogger(OrderJob.class);

    @Autowired
    private OrderInfoBiz orderInfoBiz;

    @Autowired
    private OrderGoodsBiz orderGoodsBiz;

    @Autowired
    private OrderBiz orderBiz;

    @Autowired
    private StocksBiz stocksBiz;

    @Autowired
    private OrderOptLogBiz orderOptLogBiz;

    @Scheduled(cron = "0 0/30 *  * * ? ")
    public void cancelOrderJob() {
        logger.info("定时任务，取消订单定时任务，参数 时间，{}", new Date());
        try {
            List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
            orderInfoList = orderInfoBiz.selectOrderNeedCancel();
            for (OrderInfo orderInfo : orderInfoList) {
                OrderBO orderBO = new OrderBO();
                List<OrderBO> orderBOList = new ArrayList<OrderBO>();
                List<Map<String, Object>> orderList = new ArrayList<Map<String, Object>>();
                List<OrderGoods> goodsList = new ArrayList<OrderGoods>();
                UserInfoBO userInfoBO = new UserInfoBO();
                userInfoBO.setSysUserRealName("cancelOrderJob");
                orderBO.setOrderId(orderInfo.getId());
                orderBO.setOrderNo(orderInfo.getOrderNo());
                orderBO.setOrderType(Constants.OrderType.MAIN.v());
                orderBOList.add(orderBO);
                goodsList = orderGoodsBiz.getOrderGoodsListByOrderId(orderBO.getOrderId());
                for (OrderGoods orderGood : goodsList) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("skuId", orderGood.getSkuId());
                    map.put("orderId", orderBO.getOrderId());
                    map.put("num", orderGood.getGoodsCount());
                    orderList.add(map);
                }
                orderBiz.updateOrderStatusByOrderId(orderBO.getOrderId(), orderBO.getOrderType(),
                        orderInfo.getOrderStatus(), Constants.OrderStatus.Closed.v().intValue());
                orderOptLogBiz.insertOrderOptLog(orderBOList, "cancelOrderJob", userInfoBO);
                stocksBiz.lockQtyReduction(orderList);
            }
        } catch (Exception e) {
            logger.equals(e);
        }
    }
}
