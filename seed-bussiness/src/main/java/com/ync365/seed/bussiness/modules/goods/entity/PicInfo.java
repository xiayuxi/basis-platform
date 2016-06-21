package com.ync365.seed.bussiness.modules.goods.entity;

public class PicInfo {
    private Integer id;

    private String uuid;

    /**
     * 1:goods
     * 2:brand
     * 3:ad
     */
    private String picType;

    private String picName;
    
    /**
     * 是否是主圖片 1是 0否
     * */
    private Integer isMain;
    
    private Integer seq;
    
    public Integer getIsMain() {
        return isMain;
    }

    public void setIsMain(Integer isMain) {
        this.isMain = isMain;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getPicType() {
        return picType;
    }

    public void setPicType(String picType) {
        this.picType = picType == null ? null : picType.trim();
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName == null ? null : picName.trim();
    }
}