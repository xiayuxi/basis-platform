package com.ync365.seed.dto.order.output;

public class OrderTrackDTO {
    
    private Long trackTime;//记录时间
    
    private Integer status;//订单状态

    public Long getTrackTime() {
        return trackTime;
    }

    public void setTrackTime(Long trackTime) {
        this.trackTime = trackTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

   
}
