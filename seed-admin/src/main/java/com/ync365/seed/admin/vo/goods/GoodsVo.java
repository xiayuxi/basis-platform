package com.ync365.seed.admin.vo.goods;

import java.util.List;

import com.ync365.seed.bussiness.modules.goods.entity.Blob;
import com.ync365.seed.bussiness.modules.goods.entity.Feature;
import com.ync365.seed.bussiness.modules.goods.entity.Goods;

/**
 * 商品Vo
 *
 */
public class GoodsVo {

	/**
	 * 商品
	 */
	private Goods goods;

	/**
	 * 属性
	 */
	private List<Feature> features;
	
	/**
	 * 商品详情
	 * */
	private Blob blob;
	
	/**
	 * 店铺名称
	 */
	private String storeName; 

	/**
	 * 品牌名称
	 */
    private String brandName;
	
    /**
     * 起始时间
     */
	private String beginDate;
	
	/**
	 * 结束时间 
	 */
	private String endDate;
	
	/**
	 * 审核人
	 */
	private String auditUser;
	
	/**
	 * 发布人
	 */
	private String releaseUser;
	
	private String goodsType;
	
	private String carouselPicture1;
	private String carouselPicture2;
	private String carouselPicture3;
	private String carouselPicture4;
	private String carouselPicture5;
	
	
	
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Blob getBlob() {
		return blob;
	}

	public void setBlob(Blob blob) {
		this.blob = blob;
	}
	
	   public String getBrandName() {
	        return brandName;
	    }

	    public void setBrandName(String brandName) {
	        this.brandName = brandName;
	    }

        public String getAuditUser() {
            return auditUser;
        }

        public void setAuditUser(String auditUser) {
            this.auditUser = auditUser;
        }

        public String getReleaseUser() {
            return releaseUser;
        }

        public void setReleaseUser(String releaseUser) {
            this.releaseUser = releaseUser;
        }

        public String getBeginDate() {
            return beginDate;
        }

        public void setBeginDate(String beginDate) {
            this.beginDate = beginDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(String goodsType) {
            this.goodsType = goodsType;
        }

		public String getCarouselPicture1() {
			return carouselPicture1;
		}

		public void setCarouselPicture1(String carouselPicture1) {
			this.carouselPicture1 = carouselPicture1;
		}

		public String getCarouselPicture2() {
			return carouselPicture2;
		}

		public void setCarouselPicture2(String carouselPicture2) {
			this.carouselPicture2 = carouselPicture2;
		}

		public String getCarouselPicture3() {
			return carouselPicture3;
		}

		public void setCarouselPicture3(String carouselPicture3) {
			this.carouselPicture3 = carouselPicture3;
		}

		public String getCarouselPicture4() {
			return carouselPicture4;
		}

		public void setCarouselPicture4(String carouselPicture4) {
			this.carouselPicture4 = carouselPicture4;
		}

		public String getCarouselPicture5() {
			return carouselPicture5;
		}

		public void setCarouselPicture5(String carouselPicture5) {
			this.carouselPicture5 = carouselPicture5;
		}
}
