/**    
 * 文件名：OrderSearchBO.java    
 *    
 * 版本信息：    
 * 日期：2015年11月3日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.bussiness.modules.order.bo;

import java.util.List;

/**    
 *     
 * @Title：OrderSearchBO  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年11月3日 上午11:58:41      
 * @version     
 *     
 */
public class OrderSearchBO {
    
   private Integer status;//订单状态
    
    private Integer startIndex;//开始记录数
    
    private Integer pageSize;//每页显示数
    
    private Long beginTime;//订单开始时间
    
    private Long endTime;//订单结束时间
    
    private String searchInfo;//查询信息
    
    private String user_num;//用户编号
    
    List<Integer> orderIdList;//订单ID list
    
    public List<Integer> getOrderIdList() {
        return orderIdList;
    }

    public void setOrderIdList(List<Integer> orderIdList) {
        this.orderIdList = orderIdList;
    }

    public String getUser_num() {
        return user_num;
    }

    public void setUser_num(String user_num) {
        this.user_num = user_num;
    }

    public String getSearchInfo() {
        return searchInfo;
    }

    public void setSearchInfo(String searchInfo) {
        this.searchInfo = searchInfo;
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
    

}
