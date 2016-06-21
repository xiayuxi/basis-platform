package com.ync365.seed.service.promotion;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.ync365.seed.dto.base.ResponseDTO;

/**
 *     
 * @Title：PromotionService  
 * @Description: 活动Service
 * @author: Ken        
 * @date: 2015年10月14日 下午1:52:18      
 * @version     
 *
 */
@Path("promotion")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface PromotionService {

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
    @GET
    @Path("queryPromotionSkus/{regionId}/{pageNum}")
    public ResponseDTO queryPromotionSkus(@PathParam("regionId") Integer regionId,@PathParam("pageNum") Integer pageNum);
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
    @GET
    @Path("queryPromotionBySkuId/{skuId}/{cityId}")
    public ResponseDTO queryPromotionBySkuId(@PathParam("skuId") Integer skuId,@PathParam("cityId") Integer cityId);

}
