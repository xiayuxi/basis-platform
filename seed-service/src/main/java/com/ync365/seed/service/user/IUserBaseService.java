package com.ync365.seed.service.user;

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
import com.ync365.seed.dto.user.UserMobileCodeDTO;
@Path("users")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface IUserBaseService {
    /**
     * 获取手机验证码
     * @Title: getMobileValidateCode
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年9月28日 下午5:03:24       
     * @version: 
     *
     * @param mobile
     * @return
     *
     */
    @GET
    @Path("code/{mobile}")
    ResponseDTO getMobileValidateCode(@PathParam("mobile") String mobile);

    /**
     * 发送手机验证码
     * @Title: sendValidateCode
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年9月28日 下午5:03:13       
     * @version: 
     *
     * @return
     *
     */
    @POST
    @Path("code/sendValidateCode")
    ResponseDTO sendValidateCode(UserMobileCodeDTO dto);

    /**
     * 验证手机验证码
     * @Title: validateMobileAndCode
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年9月28日 下午5:05:39       
     * @version: 
     *
     * @param dto
     * @return
     *
     */
    @POST
    @Path("code/validateMobileAndCode")
    ResponseDTO validateMobileAndCode(UserMobileCodeDTO dto);

    /**
     * 获取种植种类
     * @Title: getGrowKind
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年9月28日 下午5:07:58       
     * @version: 
     *
     * @return
     *
     */
    @GET
    @Path("getGrowKind")
    ResponseDTO getGrowKind();

    /**
     * 获取可选从事工作
     * @Title: getDoWork
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年9月28日 下午5:08:30       
     * @version: 
     *
     * @return
     *
     */
    @GET
    @Path("getDoWork")
    ResponseDTO getDoWork();

    /**
     * 获取表单Token
     * @Title: getToken
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年9月28日 下午5:08:45       
     * @version: 
     *
     * @return
     *
     */
    @GET
    @Path("token")
    ResponseDTO getToken();
}
