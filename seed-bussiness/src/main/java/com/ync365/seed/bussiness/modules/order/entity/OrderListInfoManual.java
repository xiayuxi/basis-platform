/**    
 * 文件名：OrderListInfoManual.java    
 *    
 * 版本信息：    
 * 日期：2015年10月24日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.bussiness.modules.order.entity;

import java.math.BigDecimal;
import java.util.Date;

/**    
 *     
 * @Title：OrderListInfoManual  
 * @Description: TODO   用于展示拼装好的订单信息
 * @author: ivan        
 * @date: 2015年10月24日 下午6:56:51      
 * @version     
 *     
 */
public class OrderListInfoManual {
   private String orderNo;//订单号
    
    private String userName;//下单人
    
    private String userTel;//手机号
    
    private BigDecimal payFee;//订单金额
    
    private Date createTime;//下单时间
    
    private Integer status;//订单状态
    
    private Integer orderType;//订单类型
    
    private Integer source;//订单来源
    
    private BigDecimal dealFee;//成交金额
    
    private Date dealTime;//完成时间
    
    private Integer orderId;//订单id
    
    private String userNum;//用户ID

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public BigDecimal getPayFee() {
        return payFee;
    }

    public void setPayFee(BigDecimal payFee) {
        this.payFee = payFee;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public BigDecimal getDealFee() {
        return dealFee;
    }

    public void setDealFee(BigDecimal dealFee) {
        this.dealFee = dealFee;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

}
