/**    
 * 文件名：OrderManual.java    
 *    
 * 版本信息：    
 * 日期：2015年10月24日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.bussiness.modules.order.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**    
 *     
 * @Title：OrderManual  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年10月24日 下午5:43:43      
 * @version     
 *     
 */
public class OrderManual {
    private Integer orderId;//订单id

    private String orderNo;//订单编号

    private BigDecimal payFee;//订单金额

    private Integer status;//订单状态

    private Integer hasSub;//是否包含子订单

    private String bookOrderUserName;//下单人

    private Date createTime;//下单时间

    private Integer payWay;//支付方式 农行支付 余额 红包 混合支付

    private Integer orderType;//订单类型

    private List<OrderSubManual> subList = new ArrayList<OrderSubManual>();//子订单列表

    private List<OrderGoods> goodsList = new ArrayList<OrderGoods>();//订单商品表

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getBookOrderUserName() {
        return bookOrderUserName;
    }

    public void setBookOrderUserName(String bookOrderUserName) {
        this.bookOrderUserName = bookOrderUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getPayFee() {
        return payFee;
    }

    public void setPayFee(BigDecimal payFee) {
        this.payFee = payFee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getHasSub() {
        return hasSub;
    }

    public void setHasSub(Integer hasSub) {
        this.hasSub = hasSub;
    }

    public List<OrderSubManual> getSubList() {
        return subList;
    }

    public void setSubList(List<OrderSubManual> subList) {
        this.subList = subList;
    }

    public List<OrderGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderGoods> goodsList) {
        this.goodsList = goodsList;
    }

}
