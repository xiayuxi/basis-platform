package com.ync365.seed.service.order;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.ync365.seed.dto.base.ResponseDTO;
import com.ync365.seed.dto.order.input.OrderManagerSearchDTO;
import com.ync365.seed.dto.order.input.OrderSearchDTO;
import com.ync365.seed.dto.order.input.PreparedPayInputDTO;
import com.ync365.seed.dto.order.output.OrderDTO;


/**    
 *     
 * @Title：IOrderService  
 * @Description: TODO   
 * @author: Ken        
 * @date: 2015年9月19日 下午4:53:16      
 * @version     
 *     
 */
@Path("order")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface OrderService {
    
    /**
     * 
     * @Title: getOrderList
     * @Description: 分页查询订单  
     * @author: Ken    
     * @date: 2015年10月8日 下午2:51:27       
     * @version: 
     *
     * @param userNum
     * @param pageSize
     * @param pageNo
     * @param condition
     * @return
     *
     */
    @POST
    @Path("getOrderList/{userNum}")
    public ResponseDTO getOrderList(@PathParam("userNum") String userNum, OrderSearchDTO orderSearchDTO);
    
    
    
    /**
     * 
     * @Title: getOrderDetail
     * @Description: 订单详情
     * @author: Ken    
     * @date: 2015年10月8日 下午2:17:04       
     * @version: 
     *
     * @param userNum
     * @param orderNo
     * @return
     *
     */
    @GET
    @Path("getOrderDetail/{userNum}/{orderId}/{orderType}")
    public ResponseDTO getOrderDetail(@PathParam("userNum") String userNum,@PathParam("orderId") String orderId,@PathParam("orderType") Integer orderType);
    /**
     * 
     * @Title: cancelOrder
     * @Description: TODO    ：    取消订单
     * @author: sunyf    
     * @date: 2015年10月8日 下午6:30:31       
     * @version: 
     *
     * @param userNum
     * @param isSub
     * @param orderNo
     * @return
     *
     */
    @PUT
    @Path("cancelOrder/{userNum}")
    public ResponseDTO cancelOrder(@PathParam("userNum") String userNum,OrderDTO orderDTO);
    /**
     * 
     * @Title: getSubOrderDetail
     * @Description: TODO    ：    查看拆单详情
     * @author: sunyf    
     * @date: 2015年10月8日 下午6:33:58       
     * @version: 
     *
     * @param userNum
     * @param orderNo
     * @return
     *
     */
    @GET
    @Path("getSplitOrderDetail/{userNum}/{orderNo}")
    public ResponseDTO getSplitOrderDetail(@PathParam("userNum") String userNum,@PathParam("orderNo") String orderNo);
    /**
     * 
     * @Title: changeOrder
     * @Description: TODO    ：    修改订单
     * @author: sunyf    
     * @date: 2015年10月8日 下午7:03:11       
     * @version: 
     *
     * @param userNum
     * @param orderNo
     * @return
     *
     */
    @PUT
    @Path("changeOrder/{userNum}")
    public ResponseDTO changeOrder(@PathParam("userNum") String userNum,Map<String,String>condition);
    /**
     * 
     * @Title: getManageOrderList
     * @Description: TODO    ：    获取服务用户订单列表
     * @author: sunyf    
     * @date: 2015年10月9日 下午7:19:35       
     * @version: 
     *
     * @param userNum
     * @param pageNo
     * @param condition
     * @return
     *
     */
    @POST
    @Path("getManageOrderList/{userNum}")
    public ResponseDTO getManageOrderList(@PathParam("userNum") String userNum, OrderManagerSearchDTO orderManagerSearchDTO);
    /**
     * 
     * @Title: mergePay
     * @Description: TODO    ：    合并支付
     * @author: sunyf    
     * @date: 2015年10月9日 下午7:23:55       
     * @version: 
     *
     * @return
     *
     */
    @POST
    @Path("mergePay/{userNum}/{mergeType}")
    public ResponseDTO mergePay(@PathParam("userNum") String userNum,@PathParam("mergeType") Integer mergeType,List<String> orderList);
    /**
     * 
     * @Title: buyOrderAgain
     * @Description: TODO    ：    再次购买
     * @author: sunyf    
     * @date: 2015年10月13日 上午11:33:45       
     * @version: 
     *
     * @param userNum
     * @param orderNo
     * @param orderType
     * @return
     *
     */
    @POST
    @Path("buyOrderAgain/{userNum}")
    public ResponseDTO buyOrderAgain(@PathParam("userNum") String userNum,Map<String,String>condition);
    /**
     * 
     * @Title: getRecycleList
     * @Description: TODO    ：    获取用户回收站
     * @author: sunyf    
     * @date: 2015年10月13日 下午1:25:23       
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    @POST
    @Path("getRecycleList/{userNum}")
    public ResponseDTO getRecycleList(@PathParam("userNum") String userNum, OrderSearchDTO orderSearchDTO);
    /**
     * 
     * @Title: affirmOrder
     * @Description: TODO    ：    订单确认
     * @author: sunyf    
     * @date: 2015年10月13日 下午1:39:58       
     * @version: 
     *
     * @param userNum
     * @param orderNo
     * @param orderType
     * @return
     *
     */
    @PUT
    @Path("affirmOrder/{userNum}")
    public ResponseDTO affirmOrder(@PathParam("userNum") String userNum,OrderDTO orderDTO);
    /**
     * 
     * @Title: delRecycleList
     * @Description: TODO    ：    订单回收站删除
     * @author: sunyf    
     * @date: 2015年10月13日 下午1:39:58       
     * @version: 
     *
     * @param userNum
     * @param orderList
     * @return
     *
     */
    @PUT
    @Path("delRecycleList/{userNum}")
    public ResponseDTO delRecycleList(@PathParam("userNum")String userNum,List<OrderDTO> orderList);
    /**
     * 
     * @Title: restoreRecycleList
     * @Description: TODO    ：    订单回收站还原
     * @author: sunyf    
     * @date: 2015年10月13日 下午1:39:58       
     * @version: 
     *
     * @param userNum
     * @param orderList
     * @return
     *
     */
    @PUT
    @Path("restoreRecycleList/{userNum}")
    public ResponseDTO restoreRecycleList(@PathParam("userNum")String userNum,List<OrderDTO> orderList);
    /**
     * 
     * @Title: affirmDelivery
     * @Description: TODO    ：    
     * @author: sunyf    
     * @date: 2015年10月15日 上午10:28:09       
     * @version: 
     *
     * @param userNum
     * @param condition
     * @return
     *
     */
    @PUT
    @Path("affirmDelivery/{userNum}")
    public ResponseDTO affirmDelivery(@PathParam("userNum")String userNum,OrderDTO orderDTO);
    /**
     * 
     * @Title: getSupplierOrderList
     * @Description: TODO    ：    获取供应商列表
     * @author: sunyf    
     * @date: 2015年10月15日 上午11:00:38       
     * @version: 
     *
     * @param userNum
     * @param condition
     * @return
     *
     */
    @POST
    @Path("getSupplierOrderList/{userNum}")
    public ResponseDTO getSupplierOrderList(@PathParam("userNum") String userNum,OrderManagerSearchDTO orderManagerSearchDTO);
    /**
     * 
     * @Title: delOrder
     * @Description: TODO    ：   删除订单
     * @author: sunyf    
     * @date: 2015年10月15日 上午11:00:38       
     * @version: 
     *
     * @param orderDTO
     * @return
     *
     */
    @PUT
    @Path("delOrder/{userNum}")
    public ResponseDTO delOrder(@PathParam("userNum")String userNum,OrderDTO orderDTO);
    
    /**
     * 
     * @Title: preparedPay
     * @Description: 订单支付前获取订单信息    
     * @author: Ken    
     * @date: 2015年10月22日 下午6:36:35       
     * @version: 
     *
     * @param param
     * @return
     *
     */
    @POST
    @Path("preparedPay")
    public ResponseDTO preparedPay(PreparedPayInputDTO param);
    /**
     * 
     * @Title: orderTypeNum
     * @Description: TODO    ：获取不同状态订单数量    
     * @author: ivan    
     * @date: 2015年10月27日 下午1:51:53       
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    @POST
    @Path("orderTypeNum/{userNum}")
    public ResponseDTO orderTypeNum(@PathParam("userNum")String userNum,Map <String ,String> map);
}
