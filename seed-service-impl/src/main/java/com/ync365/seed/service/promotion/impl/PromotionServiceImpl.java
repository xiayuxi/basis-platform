package com.ync365.seed.service.promotion.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ync365.seed.bussiness.modules.goods.biz.SkuBiz;
import com.ync365.seed.bussiness.modules.goods.bo.SkuBO;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionBiz;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionOnLinePO;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGoodsSku;
import com.ync365.seed.bussiness.modules.promotion.po.PromotionSkuPO;
import com.ync365.seed.dto.base.ResponseCode;
import com.ync365.seed.dto.base.ResponseDTO;
import com.ync365.seed.service.promotion.PromotionService;
import com.ync365.seed.utils.Constants;

public class PromotionServiceImpl implements PromotionService{
    
    @Autowired
    private PromotionBiz promotionBiz;
    
    @Autowired
    private SkuBiz skuBiz;
    
    /**
     * @Title: queryPromotionSkus
     * @Description: 查看活动商品
     * @author: Ken    
     * @date: 2015年10月14日 下午1:56:07       
     * @version: 
     *
     * @param cityId
     * @return
     *
     */
    @Override
    public ResponseDTO queryPromotionSkus(Integer regionId,Integer pageNum) {
        
        ResponseDTO response = new ResponseDTO();
        try {
            Map <Integer,List<PromotionOnLinePO>> map = promotionBiz.getOnlinePromotion();
            if(map == null || map.size() == 0){
                response.setCode(ResponseCode.PromotionGoodsCode.PROMOTION_GOODS_NOT_EXISTS.v());
                response.setMsg(ResponseCode.PromotionGoodsCode.PROMOTION_GOODS_NOT_EXISTS.c());
                return response;
            }
            List<SkuBO> skuBOList = new ArrayList<SkuBO>();
            for(List <PromotionOnLinePO> promotionOnLineList : map.values()){
                for(PromotionOnLinePO promotionOnLinePO : promotionOnLineList){
                    for(PromotionGoodsSku goodsSku: promotionOnLinePO.getSkuMap().values()){
                        Integer promotionType = promotionOnLinePO.getPromotionPO().getPromotionType();
                        SkuBO skuBO = new SkuBO();
                        skuBO.setSkuId(goodsSku.getSkuId());
                        skuBO.setActivityType(promotionType);
                        skuBOList.add(skuBO);
                    }
                }
            }
            if(skuBOList.size() == 0){
                response.setCode(ResponseCode.PromotionGoodsCode.PROMOTION_GOODS_NOT_EXISTS.v());
                response.setMsg(ResponseCode.PromotionGoodsCode.PROMOTION_GOODS_NOT_EXISTS.c());
                return response;
            }
            if(skuBOList.size() > pageNum){
                skuBOList = skuBOList.subList(0, pageNum);
            }
            List<SkuBO> returnSkuBOList = skuBiz.getSkuBySkuIdsAndRegion(skuBOList, regionId);
            if(returnSkuBOList == null || returnSkuBOList.size() == 0){
                response.setCode(ResponseCode.PromotionGoodsCode.PROMOTION_GOODS_NOT_EXISTS.v());
                response.setMsg(ResponseCode.PromotionGoodsCode.PROMOTION_GOODS_NOT_EXISTS.c());
                return response;
            }
            response.setCode(ResponseCode.CommonCode.OK.v());
            response.setMsg(ResponseCode.CommonCode.OK.c());
            response.setData(returnSkuBOList);
        } catch (Exception e) {
            response.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
            response.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
        }
        return response;
        
    }

    @Override
    public ResponseDTO queryPromotionBySkuId(Integer skuId, Integer cityId) {
        ResponseDTO response = new ResponseDTO();
        try {
            PromotionSkuPO promotionSkuPO = promotionBiz.findPromotionBySkuId(skuId);
            if(promotionSkuPO == null){
                response.setCode(ResponseCode.PromotionGoodsCode.NO_PROMOTION.v());
                response.setMsg(ResponseCode.PromotionGoodsCode.NO_PROMOTION.c());
                return response;
            }
            if(promotionSkuPO.getPromotionType() == Constants.PromotionType.Down.v()){
                List <Integer> skuIds = new ArrayList<Integer>();
                skuIds.add(skuId);
                Map<Integer,SkuBO> map = skuBiz.getSkuByIdAndRegionId(skuIds, cityId);
                SkuBO skuBO = map.get(skuId);
                BigDecimal promotionPrice = skuBO.getCostPrice().multiply(promotionSkuPO.getDiscount());
                promotionSkuPO.setPromotionPrice(promotionPrice);
            }
            
            response.setCode(ResponseCode.CommonCode.OK.v());
            response.setMsg(ResponseCode.CommonCode.OK.c());
            response.setData(promotionSkuPO);
        } catch (Exception e) {
            response.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
            response.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
            e.printStackTrace();
        }
        return response;
    }

}
