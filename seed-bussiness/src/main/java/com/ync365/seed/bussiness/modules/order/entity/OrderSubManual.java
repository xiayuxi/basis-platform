/**    
 * 文件名：OrderSubManual.java    
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
import java.util.List;

/**    
 *     
 * @Title：OrderSubManual  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年10月24日 下午5:44:48      
 * @version     
 *     
 */
public class OrderSubManual {
    private Integer id;

    private String orderNo;// 订单编号

    private BigDecimal payFee;// 订单金额

    private Integer status;// 订单状态

    private String orderSubNo;//子订单编号

    private List<OrderGoods> goodsList = new ArrayList<OrderGoods>();// 订单商品表

    public String getOrderSubNo() {
        return orderSubNo;
    }

    public void setOrderSubNo(String orderSubNo) {
        this.orderSubNo = orderSubNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<OrderGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderGoods> goodsList) {
        this.goodsList = goodsList;
    }

}
