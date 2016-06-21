package com.ync365.seed.service.user;

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
import com.ync365.seed.dto.user.LcInfoDTO;
import com.ync365.seed.dto.user.SysReceiveGoodsInfoDTO;
import com.ync365.seed.dto.user.UserInfoDTO;
import com.ync365.seed.dto.user.UserSearchDTO;
import com.ync365.seed.dto.user.VsInfoDTO;

@Path("users")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface IUserInfoService {
	
	// TODO US获取用户信息 （头像 昵称 性别 真实姓名 身份证号 出生日期 手机号 编号 农场面积 种植种类 收货地址）
	@GET
	@Path("getuserinfobyusernum/{usernum}")
	ResponseDTO getUserInfoByUserNum(@PathParam("usernum") String userNum);
	
	// TODO US修改用户信息 （头像 昵称 性别 真实姓名 身份证号 出生日期）
	@PUT
	@Path("updateuserinfobyusernum")
	ResponseDTO updateUserInfoByUserNum(UserInfoDTO userInfoDto);
	
	// TODO US获取VS服务站信息 （联系人 联系电话 地址 服务区域）
	@GET
	@Path("getvsinfobyusernum/{usernum}")
	ResponseDTO getVsInfoByUserNum(@PathParam("usernum") String userNum);
	
	// TODO US 获取收货地址列表
	@GET
	@Path("getsysreceivegoodsinfolist/{usernum}")
	ResponseDTO getSysReceiveGoodsInfoList(@PathParam("usernum") String userNum);
	
	// TODO US 添加收货地址
	@POST
	@Path("insertsysreceivegoodsinfo")
	ResponseDTO insertSysReceiveGoodsInfo(SysReceiveGoodsInfoDTO  sysReceiveGoodsInfoDTO);
	
	// TODO US 编辑收货地址
	@GET
	@Path("getsysreceivegoodsinfobyid/{id}")
	ResponseDTO getSysReceiveGoodsInfoById(@PathParam("id") Integer id);
	
	// TODO US 编辑保存收货地址
	@PUT
	@Path("updatesysreceivegoodsinfobyid")
	ResponseDTO updateSysReceiveGoodsInfoById(SysReceiveGoodsInfoDTO  sysReceiveGoodsInfoDTO);
	
	// TODO US删除收货地址
	@DELETE
	@Path("deletesysreceivegoodsinfonyid/{id}")
	ResponseDTO deleteSysReceiveGoodsInfo(@PathParam("id") Integer id);
	
	// TODO US 设置默认收货地址
	@PUT
	@Path("updatesysreceivegoodsisdefaultbyid/{id}/{usernum}")
	ResponseDTO updateSysReceiveGoodsIsDefaultById(@PathParam("id") Integer id,@PathParam("usernum") String userNum);
	
	// TODO US修改农场信息 （农场总面积 种植种类）
	// TODO US添加农场信息
	// TODO US获取农场列表

	// TODO VS获取绑定US数量
	@GET
	@Path("getcountsysusbyvsnum/{vsnum}")
	ResponseDTO getCountSysUsByVsNum(@PathParam("vsnum") String vsNum);
	
	// TODO VS获取绑定US订单数量（未付款 已发货 已完成）
	
	// TODO VS获取绑定US列表   // TODO VS条件查询绑定US列表（姓名 手机号 注册时间）
	@POST
	@Path("selectsysuslistbyvsnum")
	ResponseDTO selectSysUsListByVsNum(UserSearchDTO userSearchDTO);
	
	
	// TODO US获取订单列表
	// TODO VS获取佣金信息 （总额 已提现 佣金订单列表）
	// TODO VS佣金提现
	// TODO VS获取佣金订单列表
	// TODO VS条件获取佣金订单列表（订单号 商品名称 下单时间）

	// TODO VS获取帐号信息
	@GET
	@Path("getvsinfobyvsnum/{vsnum}")
	ResponseDTO getVsInfoByVsNum(@PathParam("vsnum") String vsNum);
	
	// TODO VS修改帐号信息
	@PUT
	@Path("updatevsinfobyusnum")
	ResponseDTO updateVsInfoByUsNum(VsInfoDTO vsInfoDTO);
	
	// TODO LC获取绑定US数量
	@GET
	@Path("getconutusbylcnum/{lcnum}")
	ResponseDTO getConutUsByLcNum(@PathParam("lcnum") String lcNum);
	
	// TODO LC获取绑定VS数量
	@GET
	@Path("getconutvsbylcnum/{lcnum}")
	ResponseDTO getConutVsByLcNum(@PathParam("lcnum") String lcNum);
	
	// TODO LC获取绑定US订单数量（未付款 已发货 已完成）
	
	
	// TODO LC获取绑定VS列表    LC条件查询绑定VS列表（姓名 手机号 注册时间 绑定US数量 所属LC）
	@POST
	@Path("getsysvsinfolistbylcnum")
	ResponseDTO getSysVsInfoListByLcNum(UserSearchDTO userSearchDTO);
	
	// TODO LC获取绑定US列表
	// TODO LC条件查询绑定US列表（姓名 手机号 注册时间 所属VS 所属LC）
	@POST
	@Path("getsysusinfolistbylcnum")
	ResponseDTO getSysUsInfoListByLcNum(UserSearchDTO userSearchDTO);
	
	// TODO LC获取帐号信息
	@GET
	@Path("getsyslcinfobylcnum/{lcnum}")
	ResponseDTO getSysLcInfoByLcNum(@PathParam("lcnum") String lcNum);
	
	// TODO LC修改帐号信息
	@PUT
	@Path("updatesyslcinfobylcnum")
	ResponseDTO updateSysLcInfoByLcNum(LcInfoDTO lcInfoDTO);
}
