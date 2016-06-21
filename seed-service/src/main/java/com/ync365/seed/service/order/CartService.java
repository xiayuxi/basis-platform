package com.ync365.seed.service.order;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.ync365.seed.dto.base.ResponseDTO;
import com.ync365.seed.dto.order.input.CartOrderInputDTO;
import com.ync365.seed.dto.order.input.CartSettleInputDTO;

/**    
 *     
 * @Title：CartService  
 * @Description: C网购物车服务
 * @author: Ken        
 * @date: 2015年9月21日 上午11:01:35      
 * @version 1.0  
 *     
 */
@Path("cart")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface CartService {

    /**
     * 
     * @Title: queryCart
     * @Description: 查看购物车中的商品 
     * @author: Ken    
     * @date: 2015年10月8日 下午12:55:23       
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    @GET
    @Path("queryCart/{userNum}/{cityId}")
    public ResponseDTO queryCart(@PathParam("userNum") String userNum,@PathParam("cityId") Integer cityId);

    /**
     * 
     * @Title: addCart
     * @Description: 向购物车添加商品  
     * @author: Ken    
     * @date: 2015年10月13日 下午6:29:56       
     * @version: 
     *
     * @param userNum
     * @param param
     * @return
     *
     */
    @POST
    @Path("addCart/{userNum}/{cityId}")
    public ResponseDTO addCart(@PathParam("userNum") String userNum,@PathParam("cityId") Integer cityId, Map <String,Integer> param);

    /**
     * 
     * @Title: delCart
     * @Description: 删除、批量删除购物车中的商品    
     * @author: Ken    
     * @date: 2015年10月13日 下午6:31:18       
     * @version: 
     *
     * @param userNum
     * @param param
     * @return
     *
     */
    @DELETE
    @Path("delCart/{userNum}/{cityId}")
    public ResponseDTO delCart(@PathParam("userNum") String userNum,@PathParam("cityId") Integer cityId, Map<Integer, List<Integer>> param);

    /**
     * 
     * @Title: selCartAll
     * @Description: 全选、全部取消    
     * @author: Ken    
     * @date: 2015年10月13日 下午6:33:55       
     * @version: 
     *
     * @param userNum
     * @param param
     * @return
     *
     */
    @PUT
    @Path("selCartAll/{userNum}/{cityId}")
    public ResponseDTO selCartAll(@PathParam("userNum") String userNum, @PathParam("cityId") Integer cityId, Map <String,Integer> param);
    
    /**
     * 
     * @Title: selCartStore
     * @Description: 选择、取消选择店铺  
     * @author: Ken    
     * @date: 2015年10月13日 下午7:33:20       
     * @version: 
     *
     * @param userNum
     * @param param
     * @return
     *
     */
    @PUT
    @Path("selCartStore/{userNum}/{cityId}")
    public ResponseDTO selCartStore(@PathParam("userNum") String userNum,@PathParam("cityId") Integer cityId, Map<String,Integer> param);
    
    /**
     * 
     * @Title: selCartSku
     * @Description: 选择、取消选择商品   
     * @author: Ken    
     * @date: 2015年10月13日 下午7:56:18       
     * @version: 
     *
     * @param userNum
     * @param param
     * @return
     *
     */
    @PUT
    @Path("selCartSku/{userNum}/{cityId}")
    public ResponseDTO selCartSku(@PathParam("userNum") String userNum,@PathParam("cityId") Integer cityId,Map<String,Integer> param);
    

    /**
     * 
     * @Title: numCart
     * @Description: 修改购物车中商品数量 
     * @author: Ken    
     * @date: 2015年10月13日 下午7:37:07       
     * @version: 
     *
     * @param userNum
     * @param param
     * @return
     *
     */
    @PUT
    @Path("numCart/{userNum}/{cityId}")
    public ResponseDTO numCart(@PathParam("userNum") String userNum,@PathParam("cityId") Integer cityId, Map<String,Integer> param);
    
    /**
     * 
     * @Title: countCart
     * @Description: 根据购物车商品数量判断是否可以添加商品    
     * @author: Ken    
     * @date: 2015年10月9日 下午6:39:03       
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    @GET
    @Path("countCart/{userNum}/{cityId}")
    public ResponseDTO countCart(@PathParam("userNum") String userNum,@PathParam("cityId") Integer cityId);
    
    /**
     * 
     * @Title: settleCart
     * @Description: 结算按钮   
     * @author: Ken    
     * @date: 2015年10月17日 下午6:06:07       
     * @version: 
     *
     * @param userNum
     * @param cityId
     * @param param
     * @return
     *
     */
    @POST
    @Path("settleCart/{userNum}/{cityId}")
    public ResponseDTO settleCart(@PathParam("userNum") String userNum,@PathParam("cityId") Integer cityId,CartSettleInputDTO cartSettleInfo);

    /**
     * 
     * @Title: queryOrderValidCoupons
     * @Description: 查询可用红包
     * @author: Ken    
     * @date: 2015年10月30日 上午11:42:19       
     * @version: 
     *
     * @param userNum
     * @param skuIds
     * @return
     *
     */
    @POST
    @Path("queryOrderValidCoupons/{userNum}")
    public ResponseDTO queryOrderValidCoupons(@PathParam("userNum") String userNum,List<Integer> skuIds);
    
    /**
     * 
     * @Title: orderCart
     * @Description: 提交订单    
     * @author: Ken    
     * @date: 2015年10月19日 上午10:21:38       
     * @version: 
     *
     * @param cartOrderInputDTO
     * @return
     *
     */
    @POST
    @Path("orderCart")
    public ResponseDTO orderCart(CartOrderInputDTO cartOrderInputDTO);
    
    /**
     * 
     * @Title: orderCart
     * @Description: 提交订单    
     * @author: Ken    
     * @date: 2015年10月19日 上午10:21:38       
     * @version: 
     *
     * @param cartOrderInputDTO
     * @return
     *
     */
    @POST
    @Path("couponSettle")
    public ResponseDTO couponSettle(CartOrderInputDTO input);
    
}
