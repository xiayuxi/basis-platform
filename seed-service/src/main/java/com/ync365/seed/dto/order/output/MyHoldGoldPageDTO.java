package com.ync365.seed.dto.order.output;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MyHoldGoldPageDTO {

    private BigDecimal myHoldGold;//我的佣金

    private BigDecimal takenHoldGold;//已提现佣金

    private List<MyHoldGoldInfoDTO> holdGoldList = new ArrayList<MyHoldGoldInfoDTO>();

    private Long totalNum;//总页数

    public BigDecimal getMyHoldGold() {
        return myHoldGold;
    }

    public void setMyHoldGold(BigDecimal myHoldGold) {
        this.myHoldGold = myHoldGold;
    }

    public BigDecimal getTakenHoldGold() {
        return takenHoldGold;
    }

    public void setTakenHoldGold(BigDecimal takenHoldGold) {
        this.takenHoldGold = takenHoldGold;
    }

    public List<MyHoldGoldInfoDTO> getHoldGoldList() {
        return holdGoldList;
    }

    public void setHoldGoldList(List<MyHoldGoldInfoDTO> holdGoldList) {
        this.holdGoldList = holdGoldList;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }
}
