package com.ync365.seed.dto.order.output;

import java.util.ArrayList;
import java.util.List;

public class MyCouponPageDTO {

    private Integer totalNum;//总页数
    
    private Integer totalValid;//未使用红包数
    
    private Integer totalInvalid;//无效红包数
    
    private Integer totalUsed;//已使用红包数
    
    private List<MyCouponInfoDTO> couponInfoList = new ArrayList<MyCouponInfoDTO>();

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getTotalValid() {
        return totalValid;
    }

    public void setTotalValid(Integer totalValid) {
        this.totalValid = totalValid;
    }

    public Integer getTotalInvalid() {
        return totalInvalid;
    }

    public void setTotalInvalid(Integer totalInvalid) {
        this.totalInvalid = totalInvalid;
    }

    public Integer getTotalUsed() {
        return totalUsed;
    }

    public void setTotalUsed(Integer totalUsed) {
        this.totalUsed = totalUsed;
    }

    public List<MyCouponInfoDTO> getCouponInfoList() {
        return couponInfoList;
    }

    public void setCouponInfoList(List<MyCouponInfoDTO> couponInfoList) {
        this.couponInfoList = couponInfoList;
    }
    
 
}
