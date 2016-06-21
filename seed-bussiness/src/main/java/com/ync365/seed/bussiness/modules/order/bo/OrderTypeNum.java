/**    
 * 文件名：OrderTypeNum.java    
 *    
 * 版本信息：    
 * 日期：2015年10月27日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.bussiness.modules.order.bo;

/**    
 *     
 * @Title：OrderTypeNum  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年10月27日 下午1:55:20      
 * @version     
 *     
 */
public class OrderTypeNum {
    
    private Integer noPayNum;//未支付订单
    
    private Integer deliveryNum;//已发货订单
    
    private Integer completedNum;//已完成订单

    public Integer getNoPayNum() {
        return noPayNum;
    }

    public void setNoPayNum(Integer noPayNum) {
        this.noPayNum = noPayNum;
    }

    public Integer getDeliveryNum() {
        return deliveryNum;
    }

    public void setDeliveryNum(Integer deliveryNum) {
        this.deliveryNum = deliveryNum;
    }

    public Integer getCompletedNum() {
        return completedNum;
    }

    public void setCompletedNum(Integer completedNum) {
        this.completedNum = completedNum;
    }
}
