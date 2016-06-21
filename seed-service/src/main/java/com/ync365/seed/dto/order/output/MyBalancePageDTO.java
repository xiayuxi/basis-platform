package com.ync365.seed.dto.order.output;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MyBalancePageDTO {

    private Integer currentPage;//当前页

    private Integer totalPage;//总页数
    
    private BigDecimal availableBalance;//可用余额
    
    private List<MyBalanceInfoDTO> balanceList = new ArrayList<MyBalanceInfoDTO> ();

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<MyBalanceInfoDTO> getBalanceList() {
        return balanceList;
    }

    public void setBalanceList(List<MyBalanceInfoDTO> balanceList) {
        this.balanceList = balanceList;
    }
}
