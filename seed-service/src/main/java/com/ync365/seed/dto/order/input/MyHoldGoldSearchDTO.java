/**    
 * 文件名：MyHoldGoldSearchDTO.java    
 *    
 * 版本信息：    
 * 日期：2015年11月5日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.dto.order.input;

/**    
 *     
 * @Title：MyHoldGoldSearchDTO  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年11月5日 上午10:40:21      
 * @version     
 *     
 */
public class MyHoldGoldSearchDTO {

    private Long beginTime;//订单开始时间

    private Long endTime;//订单结束时间

    private Integer startIndex;//开始记录数

    private Integer pageSize;//每页显示数

    private Integer status;//状态

    private String goodsName;

    private Integer userType;//用户类型

    private String orderNo;//订单编号

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "MyHoldGoldSearchDTO [beginTime=" + beginTime + ", endTime=" + endTime + ", startIndex=" + startIndex
                + ", pageSize=" + pageSize + ", status=" + status + ", goodsName=" + goodsName + ", userType="
                + userType + ", orderNo=" + orderNo + "]";
    }

}
