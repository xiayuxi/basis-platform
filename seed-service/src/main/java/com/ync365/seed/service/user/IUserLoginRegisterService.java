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
import com.ync365.seed.dto.user.LcInfoDTO;
import com.ync365.seed.dto.user.AdminRegisterDTO;
import com.ync365.seed.dto.user.UserAdminInfoDTO;
import com.ync365.seed.dto.user.UserInfoDTO;
import com.ync365.seed.dto.user.UserLoginDTO;
import com.ync365.seed.dto.user.UserRegisterDTO;
import com.ync365.seed.dto.user.VsInfoDTO;

@Path("users")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface IUserLoginRegisterService {

    /**
     * US/VS/LC登录
     * @author xieang
     * 2015年9月21日
     * @param userLoginDTO
     * @return
     */
    @POST
    @Path("login")
    ResponseDTO login(UserLoginDTO userLoginDTO);

    /**
     * US添加完善资料
     * @author xieang
     * 2015年9月21日
     * @param userInfoDTO
     * @return
     */
    @POST
    @Path("perfectUsInfo")
    ResponseDTO perfectUsInfo(UserInfoDTO userInfoDTO);

    /**
     * US/VS/LC修改资料
     * @author xieang
     * 2015年9月21日
     * @param userInfoDTO
     * @return
     */
    @PUT
    @Path("updateUserRegister/{userNum}")
    ResponseDTO updateUserRegister(UserRegisterDTO userInfoDTO, @PathParam("userNum") String userNum);

    /**
     * VS添加完善资料
     * @author xieang
     * 2015年9月21日
     * @param vsInfoDTO
     * @return
     */
    @POST
    @Path("perfectVsInfo")
    ResponseDTO perfectVsInfo(VsInfoDTO vsInfoDTO);

    /**
     * LC添加完善资料
     * @author xieang
     * 2015年9月21日
     * @param lcInfoDTO
     * @return
     */
    @POST
    @Path("perfectLcInfo")
    ResponseDTO perfectLcInfo(LcInfoDTO lcInfoDTO);

    /**
     * 根据地址编号查询村站
     * @author xieang
     * 2015年9月22日
     * @param addressCode
     * @return
     */
    @GET
    @Path("selectVsByAddress/{addressCode}")
    ResponseDTO selectVsByAddress(@PathParam("addressCode") String addressCode);


    /**
     * 编号或手机号获取VS
     * @Title: selectVsByNumOrMobile
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月12日 下午4:55:13       
     * @version: 
     *
     * @param userNumOrMobile
     * @return
     *
     */
    @GET
    @Path("selectVsByNumOrMobile/{userNumOrMobile}")
    ResponseDTO selectVsByNumOrMobile(@PathParam("userNumOrMobile") String userNumOrMobile);

    /**
     * 根据编号查询LC
     * @author xieang
     * 2015年9月23日
     * @param userNum
     * @return
     */
    @GET
    @Path("selectLcByNum/{userNum}")
    ResponseDTO selectLcByNum(@PathParam("userNum") String userNum);

    /**
     * 编号或手机号获取LC
     * @Title: selectLcByNumOrMobile
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月12日 下午4:56:13       
     * @version: 
     *
     * @param userNumOrMobile
     * @return
     *
     */
    @GET
    @Path("selectLcByNumOrMobile/{userNumOrMobile}")
    ResponseDTO selectLcByNumOrMobile(@PathParam("userNumOrMobile") String userNumOrMobile);

    /**
     * 根据编号查询SE
     * @author xieang
     * 2015年9月23日
     * @param userNum
     * @return
     */
    @GET
    @Path("selectSeByNum/{userNum}")
    ResponseDTO selectSeByNum(@PathParam("userNum") String userNum);

    /**
     * 编号或手机号获取se
     * @Title: selectSeByNumOrMobile
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月12日 下午4:57:09       
     * @version: 
     *
     * @param userNumOrMobile
     * @return
     *
     */
    @GET
    @Path("selectSeByNumOrMobile/{userNumOrMobile}")
    ResponseDTO selectSeByNumOrMobile(@PathParam("userNumOrMobile") String userNumOrMobile);

    /**重新设置US/VS/LC密码
     * @author xieang
     * 2015年9月23日
     * @param userLoginDTO
     * @return
     */
    @POST
    @Path("resetPasswordByUser")
    ResponseDTO resetPassword(UserLoginDTO userLoginDTO);

    /**SE登录
     * @author xieang
     * 2015年9月23日
     * @param userLoginDTO
     * @return
     */
    @POST
    @Path("loginAdmin")
    ResponseDTO loginAdmin(UserLoginDTO userLoginDTO);

    /**SE注册
     * @author xieang
     * 2015年9月23日
     * @param userLoginDTO
     * @return
     */
    @POST
    @Path("registerAdmin")
    ResponseDTO registerAdmin(AdminRegisterDTO userAdminDTO);
    /**
     * 修改SE注册信息
     * @Title: updateAdminRegister
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月14日 下午3:16:52       
     * @version: 
     *
     * @param userAdminDTO
     * @param adminNum
     * @return
     *
     */
    @PUT
    @Path("updateAdminRegister/{adminNum}")
    ResponseDTO updateAdminRegister(AdminRegisterDTO userAdminDTO,@PathParam("adminNum")String adminNum);

    /**SE添加完善资料
     * @author xieang
     * 2015年9月23日
     * @param userLoginDTO
     * @return
     */
    @POST
    @Path("perfectSeInfo")
    ResponseDTO perfectSeInfo(UserAdminInfoDTO userAdminInfoDTO);

    /**
     * 编号选择A+
     * @author xieang
     * 2015年9月24日
     * @param userNum
     * @return
     */
    @GET
    @Path("selectAPlusByNum/{userNum}")
    ResponseDTO selectAPlusByNum(@PathParam("userNum") String userNum);

    /**
     * 编号或手机号获取A+
     * @Title: selectAPlusByNumOrMobile
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月12日 下午4:58:13       
     * @version: 
     *
     * @param userNumOrMobile
     * @return
     *
     */
    @GET
    @Path("selectAPlusByNumOrMobile/{userNumOrMobile}")
    ResponseDTO selectAPlusByNumOrMobile(@PathParam("userNumOrMobile") String userNumOrMobile);

    /**重新设置SE/A+密码
     * @author xieang
     * 2015年9月24日
     * @param userLoginDTO
     * @return
     */
    @POST
    @Path("resetPasswordByAdmin")
    ResponseDTO resetPasswordAdmin(UserLoginDTO userLoginDTO);

    /**
     * 用户名是否重复
     * @author xieang
     * 2015年9月29日
     * @param loginName
     * @return
     */
    @GET
    @Path("validateLoginName/{loginName}")
    ResponseDTO validateLoginName(@PathParam("loginName") String loginName);

    /**
     * 手机号是否重复
     * @author xieang
     * 2015年9月29日
     * @param userMobile
     * @return
     */
    @GET
    @Path("validateUserMobile/{userMobile}")
    ResponseDTO validateUserMobile(@PathParam("userMobile") String userMobile);
    
    /**
     * SE用户名是否重复
     * @author xieang
     * 2015年9月29日
     * @param loginName
     * @return
     */
    @GET
    @Path("validateSeLoginName/{loginName}")
    ResponseDTO validateSeLoginName(@PathParam("loginName") String loginName);
    
    /**
     * SE手机号是否重复
     * @author xieang
     * 2015年9月29日
     * @param userMobile
     * @return
     */
    @GET
    @Path("validateSeUserMobile/{userMobile}")
    ResponseDTO validateSeUserMobile(@PathParam("userMobile") String userMobile);
}
