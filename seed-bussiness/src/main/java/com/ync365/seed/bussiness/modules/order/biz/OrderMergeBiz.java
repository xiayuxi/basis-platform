/**    
 * 文件名：OrderMergeBiz.java    
 *    
 * 版本信息：    
 * 日期：2015年10月21日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.bussiness.modules.order.biz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.order.dao.OrderMergeDetailMapperManual;
import com.ync365.seed.bussiness.modules.order.dao.OrderMergeInfoMapper;
import com.ync365.seed.bussiness.modules.order.entity.OrderListInfoManual;
import com.ync365.seed.bussiness.modules.order.entity.OrderMergeDetail;
import com.ync365.seed.bussiness.modules.order.entity.OrderMergeInfo;
import com.ync365.seed.bussiness.modules.user.bo.UserInfoBO;
import com.ync365.seed.utils.Constants;

/**    
 *     
 * @Title：OrderMergeBiz  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年10月21日 下午8:07:33      
 * @version     
 *     
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class OrderMergeBiz {

    private static final Logger logger = LoggerFactory.getLogger(OrderBiz.class);

    @Autowired
    private OrderMergeInfoMapper orderMergeInfoMapper;

    @Autowired
    private OrderMergeDetailMapperManual orderMergeDetailMapperManual;


    /***
     * 
     * @Title: insertMergeInfo
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年10月24日 下午3:45:14       
     * @version: 
     *
     * @param orderList
     * @param userInfoBO
     * @param mergeType
     * @return
     *
     */
    @Transactional(readOnly = false)
    public OrderMergeInfo createMergeOrder(List<OrderListInfoManual> orderList, UserInfoBO userInfoBO, Integer mergeType,String mergeNo) {
        OrderMergeInfo orderMergeInfo = new OrderMergeInfo();
        Date curDate = new Date();
        BigDecimal totalPrice = new BigDecimal("0");
        Long temp = 0l;
        String orderNoChannel;
        List<OrderMergeDetail> orderMergeDetailList = new ArrayList<OrderMergeDetail>();
        for (OrderListInfoManual orderInfo : orderList) {
            totalPrice = totalPrice.add(orderInfo.getPayFee());
            temp = temp + Long.parseLong(orderInfo.getOrderNo().substring(1, 3));
        }
        if (temp == orderList.size()) {
            orderNoChannel = Constants.OrderNoChannelType.Self.v();
        } else {
            orderNoChannel = Constants.OrderNoChannelType.Mix.v();
        }
        orderMergeInfo.setCreateTime(curDate);
        orderMergeInfo.setUserNum(userInfoBO.getUserNum());
        orderMergeInfo.setUsername(userInfoBO.getSysUserRealName());
        orderMergeInfo.setOrderStatus(Constants.OrderStatus.NotPay.v().intValue());
        orderMergeInfo.setGoodsAmount(totalPrice);
        orderMergeInfo.setOrderMergeNo(mergeNo);

        orderMergeInfoMapper.insertSelective(orderMergeInfo);
        //        orderMergeInfo.set
        for (OrderListInfoManual orderInfo : orderList) {
            OrderMergeDetail orderMergeDetail = new OrderMergeDetail();
            orderMergeDetail.setCreateTime(curDate);
            orderMergeDetail.setOrderInfoId(orderInfo.getOrderId());
            orderMergeDetail.setOrderNo(orderInfo.getOrderNo());
            orderMergeDetail.setOrderMergeNo(mergeNo);
            orderMergeDetail.setOrderMergeInfoId(orderMergeInfo.getId());
            orderMergeDetailList.add(orderMergeDetail);
        }
        orderMergeDetailMapperManual.insertOrderMergeDetailList(orderMergeDetailList);
        return orderMergeInfo;
    }

    /**
     * 
     * @Title: getOrderListByMergeInfoId
     * @Description: TODO    ：    根据合并订单ID查询合并订单关联表信息
     * @author: ivan    
     * @date: 2015年10月24日 下午3:45:07       
     * @version: 
     *
     * @param orderMergeInfoId
     * @return
     *
     */
    public List<OrderMergeDetail> getOrderListByMergeInfoId(Integer orderMergeInfoId) {
        return orderMergeDetailMapperManual.getMergeDListByMergeIId(orderMergeInfoId);
    }
}
