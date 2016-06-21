package com.ync365.seed.dto.order.output;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class CartDTO {
    private Boolean isAllSel; // 是否全部选择
    private Integer totalNum = 0; // 商品总数
    private Integer selNum = 0; // 选择商品数量
    private BigDecimal saveAmount; // 节省金额
    private BigDecimal totalAmount; // 总价格
    private BigDecimal actualAmount; // 实际金额
    private List<StoreDTO> stores = new ArrayList<StoreDTO>(); // 店铺集合
    
    public Integer getSelNum() {
        return selNum;
    }
    public void setSelNum(Integer selNum) {
        this.selNum = selNum;
    }
    public Boolean getIsAllSel() {
        return isAllSel;
    }
    public void setIsAllSel(Boolean isAllSel) {
        this.isAllSel = isAllSel;
    }
    public Integer getTotalNum() {
        return totalNum;
    }
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
    public BigDecimal getSaveAmount() {
        return saveAmount;
    }
    public void setSaveAmount(BigDecimal saveAmount) {
        this.saveAmount = saveAmount;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    public List<StoreDTO> getStores() {
        return stores;
    }
    public void setStores(List<StoreDTO> stores) {
        this.stores = stores;
    }
    public BigDecimal getActualAmount() {
        return actualAmount;
    }
    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }
}
