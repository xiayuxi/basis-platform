package com.ync365.seed.admin.vo.promotion;

import java.math.BigDecimal;
import java.util.Date;

public class PromotionReturnDetailVo {
    private Integer id;

    private BigDecimal costAmount;

    private BigDecimal reduceAmount;

    private Integer couponId;

    private BigDecimal couponAmount;//优惠券金额

    private Date validDate;
    
    private Date validStart;//红包有效期开始(此字段暂时不用)
    private Date validEnd;//红包有效期结束(此字段暂时不用)
    private Integer promotionId;
    
    private String moneyCheckbox;//减额的复选框
    private String couponCheckbox;//优惠券的复选框
    
    
    public Date getValidStart() {
		return validStart;
	}

	public void setValidStart(Date validStart) {
		this.validStart = validStart;
	}

	public Date getValidEnd() {
		return validEnd;
	}

	public void setValidEnd(Date validEnd) {
		this.validEnd = validEnd;
	}

	public String getMoneyCheckbox() {
		return moneyCheckbox;
	}

	public void setMoneyCheckbox(String moneyCheckbox) {
		this.moneyCheckbox = moneyCheckbox;
	}

	public String getCouponCheckbox() {
		return couponCheckbox;
	}

	public void setCouponCheckbox(String couponCheckbox) {
		this.couponCheckbox = couponCheckbox;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(BigDecimal costAmount) {
        this.costAmount = costAmount;
    }

    public BigDecimal getReduceAmount() {
        return reduceAmount;
    }

    public void setReduceAmount(BigDecimal reduceAmount) {
        this.reduceAmount = reduceAmount;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }  
    

	public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}

	public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }
    
}