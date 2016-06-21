/**    
 * 文件名：OrderManagerSearchBO.java    
 *    
 * 版本信息：    
 * 日期：2015年11月3日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.bussiness.modules.order.bo;

import java.util.List;

import com.ync365.seed.bussiness.modules.user.bo.UserInfoBO;

/**    
 *     
 * @Title：OrderManagerSearchBO  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年11月3日 下午1:50:45      
 * @version     
 *     
 */
public class OrderManagerSearchBO {
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
    
    private Integer storeId;//店铺ID
    
    List<UserInfoBO> userList;//用户列表
    
    private Integer status2;

    public Integer getStatus2() {
        return status2;
    }

    public void setStatus2(Integer status2) {
        this.status2 = status2;
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

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public List<UserInfoBO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserInfoBO> userList) {
        this.userList = userList;
    }
}
