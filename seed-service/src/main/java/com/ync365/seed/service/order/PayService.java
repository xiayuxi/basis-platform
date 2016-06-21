package com.ync365.seed.service.order;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.ync365.seed.dto.base.ResponseDTO;
import com.ync365.seed.dto.order.input.UserCouponDTO;

/**
 * 支付 接口
 *     
 * @Title：PayService  
 * @Description: TODO   
 * @author: robo        
 * @date: 2015年10月25日 下午1:44:15      
 * @version     
 *
 */
@Path("pay")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface PayService {

	/**
	 * 查询红包
	 * @Title: getRedPackets
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月25日 下午1:53:21       
	 * @version: 
	 *
	 * @return
	 *
	 */
	@POST
	@GET
	@Path("getRedPackets/{orderId}/{amount}")
	public ResponseDTO getRedPackets(@PathParam("orderId")String orderId,@PathParam("amount")Integer amount);
	
	/**
	 * 减红包
	 * @Title: reduceRedPackets
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月25日 下午2:01:27       
	 * @version: 
	 *
	 * @return
	 *
	 */
	@POST
	@GET
	@Path("reduceRedPackets")
	public ResponseDTO reduceRedPackets(List<UserCouponDTO> list);
	
	/**
	 * 取消
	 * @Title: cancelRedPackets
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月28日 下午3:46:59       
	 * @version: 
	 *
	 * @param map
	 * @return
	 *
	 */
	@POST
	@GET
	@Path("cancelRedPackets")
	public ResponseDTO cancelRedPackets(@PathParam("orderId")String orderId,@PathParam("amount")Integer amount) ;
	
	/**
	 * 异步回调支付订单
	 * @Title: asynPayOrder
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月25日 下午2:02:59       
	 * @version: 
	 *
	 * @param map
	 * @return
	 *
	 */
	@POST
	@GET
	@Path("asynNotifyPayOrder")
	public ResponseDTO asynNotifyPayOrder(Map<String,Object> map);
	
}
