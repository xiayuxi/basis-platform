package com.ync365.seed.bussiness.modules.order.biz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.order.redis.RedisOrderService;

/**
 * 
 *     
 * @Title：OrderNoGenBiz  
 * @Description: 订单号生成策略Biz 
 * @author: Ken        
 * @date: 2015年10月11日 下午1:33:04      
 * @version     
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class OrderNoGenBiz {
    
    private static final Logger logger = LoggerFactory.getLogger(OrderNoGenBiz.class);
    
    @Autowired
    private RedisOrderService redisOrderService;
    
    /**
     * 
     * @Title: genOrderNo
     * @Description: 根据规则生成订单号   
     * @author: Ken    
     * @date: 2015年10月19日 上午10:42:35       
     * @version: 
     *
     * @param orderType
     * @param orderChannel
     * @param businessCode
     * @param mixNum
     * @return
     *
     */
    public String genOrderNo(String orderType, String orderNoChannel,String businessCode,String mixNum){
        String orderNo = "";
        StringBuffer sb = new StringBuffer();
        sb.append(orderType);
        sb.append(orderNoChannel);
        businessCode = businessCode == null ? String.valueOf(new java.util.Random().nextInt(900)+100):businessCode;
        sb.append(businessCode = businessCode == null ? "000" : businessCode);
        sb.append(DateFormatUtils.format(new Date(), "yyMMdd"));
        sb.append(mixNum = mixNum == null ? "": mixNum);
        // 订单个数
        Long count = redisOrderService.getOrderCount(DateFormatUtils.format(new Date(), "yyMMdd"));
        sb.append(count);
        orderNo = sb.toString();
        logger.info("购物车，提交订单，生成订单编号，编号，orderNo，{}",orderNo);
        return orderNo;
    }

    public String getCoupnoNo(Integer promotionId) {
        StringBuffer buf = new StringBuffer();
        buf.append("HB").append(DateFormatUtils.format(new Date(), "yyMMdd")).append(promotionId)
        .append(String.valueOf(redisOrderService.getCouponCount(String.valueOf(promotionId))));
        return buf.toString();
    }
    
    //生成配货单单号   
    public String getPreparedNo(String orderType){    	
        String preparedNo = "";
        StringBuffer sb = new StringBuffer();
        sb.append("2");
        sb.append(orderType);
        sb.append("000");
        sb.append(DateFormatUtils.format(new Date(), "yyMMdd"));
        Long count = redisOrderService.getPreparedCount(DateFormatUtils.format(new Date(), "yyMMdd"));
        sb.append(count);
        preparedNo = sb.toString();
        return preparedNo;
    }
    
    //生成出库单单号   
    public String getStockNo(String orderType){    	
        String stockNo = "";
        StringBuffer sb = new StringBuffer();
        sb.append("3");
        sb.append(orderType);
        sb.append("000");
        sb.append(DateFormatUtils.format(new Date(), "yyMMdd"));
        Long count = redisOrderService.getStockCount(DateFormatUtils.format(new Date(), "yyMMdd"));
        sb.append(count);
        stockNo = sb.toString();
        return stockNo;
    }
    
    
    //生成发货单单号   
    public String getDeliveryNo(String orderType){    	
        String deliveryNo = "";
        StringBuffer sb = new StringBuffer();
        sb.append("4");
        sb.append(orderType);
        sb.append("000");
        sb.append(DateFormatUtils.format(new Date(), "yyMMdd"));
        Long count = redisOrderService.getDeliveryCount(DateFormatUtils.format(new Date(), "yyMMdd"));
        sb.append(count);
        deliveryNo = sb.toString();
        return deliveryNo;
    }
    
    
}
