package com.ync365.seed.service.order;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.ync365.seed.dto.base.ResponseDTO;
import com.ync365.seed.dto.order.input.MyHoldGoldSearchDTO;

/**
 * 
 *     
 * @Title：MyAssetService  
 * @Description: TODO   我的资产
 * @author: sunyf
 * @date: 2015年10月13日 下午3:01:42      
 * @version     
 *
 */
@Path("myAsset")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface MyAssetService {
    /**
     * 
     * @Title: getMyHoldGold
     * @Description: TODO    ：    我的佣金
     * @author: sunyf    
     * @date: 2015年10月13日 下午3:07:13       
     * @version: 
     *
     * @param userNum
     * @param userType
     * @return
     *
     */
    @POST
    @Path("getMyHoldGold/{userNum}")
    public ResponseDTO getMyHoldGold(@PathParam("userNum")String userNum,MyHoldGoldSearchDTO myHoldGoldSearchDTO);
    /**
     * 
     * @Title: getMyReturn
     * @Description: TODO    ：    我的红包
     * @author: sunyf    
     * @date: 2015年10月13日 下午3:10:25       
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    @POST
    @Path("getMyCoupon/{userNum}")
    public ResponseDTO getMyCoupon(@PathParam("userNum")String userNum,Map<String,String> condition);
    /**
     * 
     * @Title: getMyBalance
     * @Description: TODO    ：    我的余额
     * @author: sunyf    
     * @date: 2015年10月14日 上午10:51:22       
     * @version: 
     *
     * @param userNum
     * @param condition
     * @return
     *
     */
    @POST
    @Path("getMyBalance/{userNum}")
    public ResponseDTO getMyBalance(@PathParam("userNum")String userNum,Map<String,String> condition);
}
