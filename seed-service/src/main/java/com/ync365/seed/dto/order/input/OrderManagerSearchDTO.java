/**    
 * 文件名：OrderManagerSearchDTO.java    
 *    
 * 版本信息：    
 * 日期：2015年11月3日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.dto.order.input;

/**    
 *     
 * @Title：OrderManagerSearchDTO  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年11月3日 下午1:24:13      
 * @version     
 *     
 */
public class OrderManagerSearchDTO {
    private Integer status;

    private Integer startIndex;

    private Integer pageSize;

    private Long beginTime;//订单开始时间

    private Long endTime;//订单结束时间
    
    private String userTel;//用户电话
    
    private String orderNo;//订单编号
    
    private String userName;//用户姓名
    
    private  Integer payWay;//支付方式
    
    private Integer orderSource;//订单来源

    private String vsName;//vs姓名
    
    private String vsTel;//vsTel 
    
    private Integer userType;//用户类型
    
    private Integer status2;//订单状态
    
    public Integer getStatus2() {
        return status2;
    }

    public void setStatus2(Integer status2) {
        this.status2 = status2;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getVsName() {
        return vsName;
    }

    public void setVsName(String vsName) {
        this.vsName = vsName;
    }

    public String getVsTel() {
        return vsTel;
    }

    public void setVsTel(String vsTel) {
        this.vsTel = vsTel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

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

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    @Override
    public String toString() {
        return "OrderManagerSearchDTO [status=" + status + ", startIndex=" + startIndex + ", pageSize=" + pageSize
                + ", beginTime=" + beginTime + ", endTime=" + endTime + ", userTel=" + userTel + ", orderNo=" + orderNo
                + ", userName=" + userName + ", payWay=" + payWay + ", orderSource=" + orderSource + ", vsName="
                + vsName + ", vsTel=" + vsTel + "]";
    }

}
