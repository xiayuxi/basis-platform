package com.ync365.seed.service.user;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
import com.ync365.seed.dto.user.LargeCustomerInfoDTO;
import com.ync365.seed.dto.user.SysFarmInfoDTO;
import com.ync365.seed.dto.user.SysUserBrowseDTO;
import com.ync365.seed.dto.user.SysUserYnbDTO;
import com.ync365.seed.dto.user.UserAttentionDTO;
import com.ync365.seed.dto.user.UserBankInfoDTO;
import com.ync365.seed.dto.user.UserLoginDTO;
import com.ync365.seed.dto.user.UserRegisterDTO;

@Path("users")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface IUserService {
    /**
     * 测试Hello World
     * 
     * @param str
     * @return
     */
    @GET
    @Path("hello/{name}")
    ResponseDTO sayHello(@PathParam("name") String str);

    /**
     * US/VS/LC注册
     * 
     * @param vo
     * @return
     */
    @POST
    @Path("register")
    ResponseDTO register(UserRegisterDTO vo);

    // TODO US/VS/LC资产相关信息（余额 红包 云农宝 丰收汇补贴 贷款信息）
    /**
     * 获取认证材料信息
     * @Title: getUsLargeCustomerProperty
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月10日 下午5:18:55       
     * @version: 
     *
     * @return
     *
     */
    @GET
    @Path("getUsLargeCustomerProperty")
    ResponseDTO getUsLargeCustomerProperty();
    
    /**
     * 获取审核原因接口
     * @param usernum
     * @param count
     * @return
     */
    @GET
    @Path("getAuthenticationFailed/{usernum}/{count}")
    ResponseDTO getAuthenticationFailed(@PathParam("usernum") String usernum,@Min(0)@PathParam("count") Integer count);

    /**US大客户认证材料添加
     * @author xieang
     * 2015年9月28日
     * @return
     */
    @POST
    @Path("perfectUsLargeCustomerInfo")
    ResponseDTO perfectUsLargeCustomerInfo(LargeCustomerInfoDTO largeCustomerInfoDTO);

    /**
     * 修改大客户认证材料
     * @Title: updateUsLargeCustomerInfo
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月10日 下午5:12:00       
     * @version: 
     *
     * @param largeCustomerInfoDTO
     * @return
     *
     */
    @PUT
    @Path("updateUsLargeCustomerInfo")
    ResponseDTO updateUsLargeCustomerInfo(LargeCustomerInfoDTO largeCustomerInfoDTO);

    /**US获取大客户认证信息
     * @author xieang
     * 2015年9月28日
     * @return
     */
    @GET
    @Path("getUsLargeCustomerInfo/{userNum}")
    ResponseDTO getUsLargeCustomerInfo(@PathParam("userNum") String userNum);

    /**
     * US获取关注的商品
     * @author xieang
     * 2015年9月23日
     * @param userNum
     * @return
     */
    @GET
    @Path("getUserAttentionGoods/{userNum}")
    ResponseDTO getUserAttentionGoods(@PathParam("userNum") String userNum);

    /**
     * 判断用户是否已经关注商品
     * @Title: checkUserAttentionGoods
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月20日 下午4:50:20       
     * @version: 
     *
     * @param userNum
     * @param goodsId
     * @return
     *
     */
    @GET
    @Path("checkUserAttentionGoods/{userNum}/{goodsId}")
    ResponseDTO checkUserAttentionGoods(@PathParam("userNum") String userNum, @PathParam("goodsId") Integer goodsId);

    /**US删除关注的商品
     * @author xieang
     * 2015年9月25日
     * @param userNum
     * @return
     */
    @DELETE
    @Path("deleteUserAttentionGoods")
    ResponseDTO deleteUserAttentionGoods(UserAttentionDTO userAttentionDTO);

    /**
     * 添加关注商品
     * @Title: addUserAttentionGoods
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月15日 下午2:23:43       
     * @version: 
     *
     * @param userAttentionDTO
     * @return
     *
     */
    @POST
    @Path("addUserAttentionGoods")
    ResponseDTO addUserAttentionGoods(UserAttentionDTO userAttentionDTO);

    /**
     * US获取关注的店铺
     * @author xieang
     * 2015年9月25日
     * @param userNum
     * @return
     */
    @GET
    @Path("getUserAttentionPopStore/{userNum}")
    ResponseDTO getUserAttentionPopStore(@PathParam("userNum") String userNum);

    /**
     * US删除关注的店铺
     * @author xieang
     * 2015年9月26日
     * @param userNum
     * @return
     */
    @DELETE
    @Path("deleteUserAttentionPopStore")
    ResponseDTO deleteUserAttentionPopStore(UserAttentionDTO userAttentionDTO);

    /**
     * 添加关注的店铺
     * @Title: addUserAttentionPopStore
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月15日 下午2:24:18       
     * @version: 
     *
     * @param userAttentionDTO
     * @return
     *
     */
    @POST
    @Path("addUserAttentionPopStore")
    ResponseDTO addUserAttentionPopStore(UserAttentionDTO userAttentionDTO);

    /**
     * 判断用户是否已经关注此店铺
     * @Title: checkUserAttentionPopStore
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月20日 下午4:51:43       
     * @version: 
     *
     * @param userNum
     * @param popStoreId
     * @return
     *
     */
    @GET
    @Path("checkUserAttentionPopStore/{userNum}/{popStoreNum}")
    ResponseDTO checkUserAttentionPopStore(@PathParam("userNum") String userNum,
            @PathParam("popStoreNum") String popStoreNum);

    /**
     * US/VS/LC解绑手机号
     * @author xieang
     * 2015年9月23日
     * @param userNum
     * @return
     */
    @DELETE
    @Path("unbundlingMobile/{userNum}")
    ResponseDTO unbundlingMobile(@PathParam("userNum") String userNum);

    /**
     * US/VS/LC修改手机号
     * @author xieang
     * 2015年9月23日
     * @param userNum
     * @return
     */
    @PUT
    @Path("modifyMobile")
    ResponseDTO modifyMobile(UserLoginDTO userNum);

    /**
     * SE解绑手机号
     * @author xieang
     * 2015年9月23日
     * @param userNum
     * @return
     */
    @DELETE
    @Path("unbundlingMobileAdmin/{userNum}")
    ResponseDTO unbundlingMobileAdmin(@PathParam("userNum") String userNum);

    /**
     * SE修改手机号
     * @author xieang
     * 2015年9月23日
     * @param userNum
     * @return
     */
    @PUT
    @Path("modifyMobileAdmin")
    ResponseDTO modifyMobileAdmin(UserLoginDTO userNum);

    /**
     * 用户名获取用户信息
     * @Title: getUserByUserName
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月9日 下午8:16:32       
     * @version: 
     *
     * @param username
     * @return
     *
     */
    @GET
    @Path("getUserByUserName/{username}")
    ResponseDTO getUserByUserName(@PathParam("username") String username);

    /**
     * se用户名获取SE信息
     * @Title: getSEByUserName
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月9日 下午8:17:15       
     * @version: 
     *
     * @param username
     * @return
     *
     */
    @GET
    @Path("getSEByUserName/{username}")
    ResponseDTO getSEByUserName(@PathParam("username") String username);

    /**
     * 添加用户银行卡信息
     * @Title: addUserBankInfo
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月10日 上午11:34:07       
     * @version: 
     *
     * @param dto
     * @return
     *
     */
    @POST
    @Path("addUserBankInfo")
    ResponseDTO addUserBankInfo(UserBankInfoDTO dto);

    /**
     * 修改用户银行卡信息
     * @Title: updateUserBankInfo
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月10日 上午11:34:19       
     * @version: 
     *
     * @param dto
     * @return
     *
     */
    @PUT
    @Path("updateUserBankInfo")
    ResponseDTO updateUserBankInfo(UserBankInfoDTO dto);

    /**
     * 获取用户银行卡信息
     * @Title: getUserBankInfo
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月10日 上午11:34:30       
     * @version: 
     *
     * @param usernum
     * @return
     *
     */
    @GET
    @Path("getUserBankInfo/{usernum}")
    ResponseDTO getUserBankInfo(@PathParam("usernum") String usernum);

    /**
     * 通过手机号或编号获取SS信息
     * @Title: getSsInfoByMobileOrNum
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月12日 下午4:05:58       
     * @version: 
     *
     * @param mobileOrNum
     * @return
     *
     */
    @GET
    @Path("getSsInfoByMobileOrNum/{mobileOrNum}")
    ResponseDTO getSsInfoByMobileOrNum(@PathParam("mobileOrNum") String mobileOrNum);

    /**
     * 用户编号获取农场信息
     * @Title: getFarmInfoByUserNum
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月13日 上午11:28:42       
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    @GET
    @Path("getFarmInfoByUserNum/{userNum}")
    ResponseDTO getFarmInfoByUserNum(
            @PathParam("userNum") @Pattern(regexp = "\\d+", message = "userNum 只能是数字") String userNum);

    /**
     * 添加农场信息
     * @Title: addFarmInfoByUserNum
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月13日 上午11:31:26       
     * @version: 
     *
     * @param sysFarmInfoDTO
     * @return
     *
     */
    @POST
    @Path("addFarmInfo")
    ResponseDTO addFarmInfo(SysFarmInfoDTO sysFarmInfoDTO);

    @interface AddFarmInfo {
    }

    /**
     * 修改农场信息
     * @Title: updateFarmInfoByUserNum
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月13日 上午11:31:36       
     * @version: 
     *
     * @param sysFarmInfoDTO
     * @return
     *
     */
    @PUT
    @Path("updateFarmInfo")
    ResponseDTO updateFarmInfo(SysFarmInfoDTO sysFarmInfoDTO);

    @interface UpdateFarmInfo {
    }

    /**
     * 删除农场信息
     * @Title: deleteFarmInfoById
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月23日 下午2:42:23       
     * @version: 
     *
     * @param id
     * @return
     *
     */
    @DELETE
    @Path("deleteFarmInfoById/{id}")
    ResponseDTO deleteFarmInfoById(@PathParam("id") @NotNull Integer id);

    /**
     * 添加用户浏览记录
     * @Title: addUserBrowse
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月15日 下午3:19:08       
     * @version: 
     *
     * @param sysUserBrowseDTO
     * @return
     *
     */
    @POST
    @Path("addUserBrowse")
    ResponseDTO addUserBrowse(SysUserBrowseDTO sysUserBrowseDTO);

    /**
     * 批量添加用户浏览记录
     * @Title: addUserBrowse
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月15日 下午3:19:08       
     * @version: 
     *
     * @param sysUserBrowseDTO
     * @return
     *
     */
    @POST
    @Path("addUserBrowses")
    ResponseDTO addUserBrowses(List<SysUserBrowseDTO> sysUserBrowseDTOs);

    /**
     * 获取用户浏览记录
     * @Title: getUserBrowses
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月15日 下午3:21:45       
     * @version: 
     *
     * @param userNum
     * @param count
     * @return
     *
     */
    @GET
    @Path("getUserBrowses/{userNum}/{count}")
    ResponseDTO getUserBrowses(@PathParam("userNum") String userNum,
            @PathParam("count") @NotNull @Min(0) Integer count);

    /**
     * 删除所有浏览记录
     * @Title: deleteAllBrowses
     * @Description: 
     * @author: duan.zhao.qian
     * @date: 2015年10月30日 下午5:33:45
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    @DELETE
    @Path("deleteAllBrowses/{userNum}")
    ResponseDTO deleteAllBrowses(@PathParam("userNum") String userNum);

    /**
     * 添加云农宝绑定
     * @Title: addUserYnb
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月20日 下午2:29:35       
     * @version: 
     *
     * @param sysUserYnbDTO
     * @return
     *
     */
    @POST
    @Path("addUserYnb")
    ResponseDTO addUserYnb(SysUserYnbDTO sysUserYnbDTO);

    /**
     * 获取云农宝绑定
     * @Title: getUserYnbByUserNum
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月20日 下午2:29:48       
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    @GET
    @Path("getUserYnbByUserNum/{userNum}")
    ResponseDTO getUserYnbByUserNum(@PathParam("userNum") String userNum);

    /**
     * 删除云农宝绑定
     * @Title: deleteUserYnbById
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月20日 下午6:15:03       
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    @DELETE
    @Path("deleteUserYnbById/{id}")
    ResponseDTO deleteUserYnbById(@PathParam("id") Integer id);

    /**
     * 更新云农宝绑定
     * @Title: updateUserYnbById
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月20日 下午6:19:10       
     * @version: 
     *
     * @param sysUserYnbDTO
     * @return
     *
     */
    @PUT
    @Path("updateUserYnbById")
    ResponseDTO updateUserYnbById(SysUserYnbDTO sysUserYnbDTO);

}
