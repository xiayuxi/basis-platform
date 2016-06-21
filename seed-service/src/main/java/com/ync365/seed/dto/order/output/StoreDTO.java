package com.ync365.seed.dto.order.output;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StoreDTO {
    private Integer storeId;
    private String storeName;
    private Boolean isStoreSel = false;
    private List<PromSkuDTO> promSkus = new ArrayList<PromSkuDTO>();
    private List<SkuDTO> skus = new ArrayList<SkuDTO>();
    private Boolean hasProm = false;
    private BigDecimal saveStoreAmount;
    private BigDecimal totalStoreAmount;
    private BigDecimal actualStoreAmount;
    
    public BigDecimal getActualStoreAmount() {
        return actualStoreAmount;
    }
    public void setActualStoreAmount(BigDecimal actualStoreAmount) {
        this.actualStoreAmount = actualStoreAmount;
    }
    public Integer getStoreId() {
        return storeId;
    }
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
    public String getStoreName() {
        return storeName;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    public Boolean getIsStoreSel() {
        return isStoreSel;
    }
    public void setIsStoreSel(Boolean isStoreSel) {
        this.isStoreSel = isStoreSel;
    }
    public List<PromSkuDTO> getPromSkus() {
        return promSkus;
    }
    public void setPromSkus(List<PromSkuDTO> promSkus) {
        this.promSkus = promSkus;
    }
    public List<SkuDTO> getSkus() {
        return skus;
    }
    public void setSkus(List<SkuDTO> skus) {
        this.skus = skus;
    }
    public Boolean getHasProm() {
        return hasProm;
    }
    public void setHasProm(Boolean hasProm) {
        this.hasProm = hasProm;
    }
    public BigDecimal getSaveStoreAmount() {
        return saveStoreAmount;
    }
    public void setSaveStoreAmount(BigDecimal saveStoreAmount) {
        this.saveStoreAmount = saveStoreAmount;
    }
    public BigDecimal getTotalStoreAmount() {
        return totalStoreAmount;
    }
    public void setTotalStoreAmount(BigDecimal totalStoreAmount) {
        this.totalStoreAmount = totalStoreAmount;
    }
}
