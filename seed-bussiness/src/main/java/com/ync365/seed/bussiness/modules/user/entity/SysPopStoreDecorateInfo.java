package com.ync365.seed.bussiness.modules.user.entity;

public class SysPopStoreDecorateInfo {
    private Integer id;
    
    /**
     * 店铺编号
     */
    private String popStoreNum;

    /**
     * 通用模板
     */
    private Integer popStoreDecorate;

    /**
     * 顶部banner图片
     */
    private String homePageTopBigPic;

    /**
     * 焦点图1
     */
    private String focusPic1;

    /**
     * 焦点图2
     */
    private String focusPic2;

    /**
     * 焦点图3
     */
    private String focusPic3;

    /**
     * 推荐广告位
     */
    private String recommendAdvertPic;

    /**
     * 推荐商品id
     */
    private Integer advertGoodsId;

    /**
     * 底部图片
     */
    private String bottomPic;

    /**
     * 首页推荐商品个数
     */
    private Integer homePageRecommendGoodsNum;

    
    private String recommendGoodsIds;

    /**
     * 店铺背景模板
     */
    private String backgroundTemplate;
    
    /**
     * 推荐图片 url 1
     */
    private String recommendGoodsPic1;
    
    /**
     * 推荐图片 url 1
     */
    private String recommendGoodsPic2;
    
    /**
     * 推荐图片 url 1
     */
    private String recommendGoodsPic3;
    
    /**
     * 推荐图片 url 1
     */
    private String recommendGoodsPic4;
    
    /**
     * 输入图片 地址 
     */
    private String goodsInputUrlOne;
    
    /**
     * 输入图片 地址 
     */
    private String goodsInputUrlTwo;
    
    /**
     * 输入图片 地址 
     */
    private String goodsInputUrlThree;
    
    /**
     * 输入图片 地址 
     */
    private String  goodsInputUrlFour;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPopStoreNum() {
        return popStoreNum;
    }

    public void setPopStoreNum(String popStoreNum) {
        this.popStoreNum = popStoreNum == null ? null : popStoreNum.trim();
    }

    public Integer getPopStoreDecorate() {
        return popStoreDecorate;
    }

    public void setPopStoreDecorate(Integer popStoreDecorate) {
        this.popStoreDecorate = popStoreDecorate;
    }

    public String getHomePageTopBigPic() {
        return homePageTopBigPic;
    }

    public void setHomePageTopBigPic(String homePageTopBigPic) {
        this.homePageTopBigPic = homePageTopBigPic == null ? null : homePageTopBigPic.trim();
    }

    public String getFocusPic1() {
        return focusPic1;
    }

    public void setFocusPic1(String focusPic1) {
        this.focusPic1 = focusPic1 == null ? null : focusPic1.trim();
    }

    public String getFocusPic2() {
        return focusPic2;
    }

    public void setFocusPic2(String focusPic2) {
        this.focusPic2 = focusPic2 == null ? null : focusPic2.trim();
    }

    public String getFocusPic3() {
        return focusPic3;
    }

    public void setFocusPic3(String focusPic3) {
        this.focusPic3 = focusPic3 == null ? null : focusPic3.trim();
    }

    public String getRecommendAdvertPic() {
        return recommendAdvertPic;
    }

    public void setRecommendAdvertPic(String recommendAdvertPic) {
        this.recommendAdvertPic = recommendAdvertPic == null ? null : recommendAdvertPic.trim();
    }

    public Integer getAdvertGoodsId() {
        return advertGoodsId;
    }

    public void setAdvertGoodsId(Integer advertGoodsId) {
        this.advertGoodsId = advertGoodsId;
    }

    public String getBottomPic() {
        return bottomPic;
    }

    public void setBottomPic(String bottomPic) {
        this.bottomPic = bottomPic == null ? null : bottomPic.trim();
    }

    public Integer getHomePageRecommendGoodsNum() {
        return homePageRecommendGoodsNum;
    }

    public void setHomePageRecommendGoodsNum(Integer homePageRecommendGoodsNum) {
        this.homePageRecommendGoodsNum = homePageRecommendGoodsNum;
    }


    public String getBackgroundTemplate() {
        return backgroundTemplate;
    }

    public void setBackgroundTemplate(String backgroundTemplate) {
        this.backgroundTemplate = backgroundTemplate == null ? null : backgroundTemplate.trim();
    }

    public String getRecommendGoodsPic1() {
        return recommendGoodsPic1;
    }

    public void setRecommendGoodsPic1(String recommendGoodsPic1) {
        this.recommendGoodsPic1 = recommendGoodsPic1 == null ? null : recommendGoodsPic1.trim();
    }

    public String getRecommendGoodsPic2() {
        return recommendGoodsPic2;
    }

    public void setRecommendGoodsPic2(String recommendGoodsPic2) {
        this.recommendGoodsPic2 = recommendGoodsPic2 == null ? null : recommendGoodsPic2.trim();
    }

    public String getRecommendGoodsPic3() {
        return recommendGoodsPic3;
    }

    public void setRecommendGoodsPic3(String recommendGoodsPic3) {
        this.recommendGoodsPic3 = recommendGoodsPic3 == null ? null : recommendGoodsPic3.trim();
    }

    public String getRecommendGoodsPic4() {
        return recommendGoodsPic4;
    }

    public void setRecommendGoodsPic4(String recommendGoodsPic4) {
        this.recommendGoodsPic4 = recommendGoodsPic4 == null ? null : recommendGoodsPic4.trim();
    }

    public String getGoodsInputUrlOne() {
        return goodsInputUrlOne;
    }

    public void setGoodsInputUrlOne(String goodsInputUrlOne) {
        this.goodsInputUrlOne = goodsInputUrlOne == null ? null : goodsInputUrlOne.trim();
    }

    public String getGoodsInputUrlTwo() {
        return goodsInputUrlTwo;
    }

    public void setGoodsInputUrlTwo(String goodsInputUrlTwo) {
        this.goodsInputUrlTwo = goodsInputUrlTwo == null ? null : goodsInputUrlTwo.trim();
    }

    public String getGoodsInputUrlThree() {
        return goodsInputUrlThree;
    }

    public void setGoodsInputUrlThree(String goodsInputUrlThree) {
        this.goodsInputUrlThree = goodsInputUrlThree == null ? null : goodsInputUrlThree.trim();
    }

    public String getGoodsInputUrlFour() {
        return goodsInputUrlFour;
    }

    public void setGoodsInputUrlFour(String goodsInputUrlFour) {
        this.goodsInputUrlFour = goodsInputUrlFour == null ? null : goodsInputUrlFour.trim();
    }

    public String getRecommendGoodsIds() {
        return recommendGoodsIds;
    }

    public void setRecommendGoodsIds(String recommendGoodsIds) {
        this.recommendGoodsIds = recommendGoodsIds == null ? null : recommendGoodsIds.trim();
    }
    
    
}