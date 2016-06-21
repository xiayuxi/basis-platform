package com.ync365.seed.dto.order.input;

public class OrderSearchDTO {

    private Integer status;//订单状态
    
    private Integer startIndex;//开始记录数
    
    private Integer pageSize;//每页显示数
    
    private Long beginTime;//订单开始时间
    
    private Long endTime;//订单结束时间
    
    private String searchInfo;//查询信息
    
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

    @Override
    public String toString() {
        return "OrderSearchDTO [status=" + status + ", startIndex=" + startIndex + ", pageSize=" + pageSize
                + ", beginTime=" + beginTime + ", endTime=" + endTime + ", searchInfo=" + searchInfo + "]";
    }
}
