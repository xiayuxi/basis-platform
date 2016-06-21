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
import com.ync365.seed.dto.user.PopInfoDTO;
import com.ync365.seed.dto.user.PopLoginDTO;
import com.ync365.seed.dto.user.PopRegisterDTO;
import com.ync365.seed.dto.user.PopStoreDecorateDTO;
import com.ync365.seed.dto.user.PopStoreInfoDTO;
import com.ync365.seed.dto.user.PopStoreSearchDTO;
import com.ync365.seed.dto.user.PopStoreTextWithBLOBsDTO;

@Path("users")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface IPOPService {
    @POST
    @Path("popLogin")
    ResponseDTO popLogin(PopLoginDTO popLoginDTO);

    @PUT
    @Path("updatePopRegister/{popNum}")
    ResponseDTO updatePopRegister(PopRegisterDTO popRegisterDTO, @PathParam("popNum") String popNum);

    @GET
    @Path("getPopStoreInfo/{popStoreNum}")
    ResponseDTO getPopStoreInfo(@PathParam("popStoreNum") String popStoreNum);

    @PUT
    @Path("updatePopStoreInfo/{popStoreNum}")
    ResponseDTO updatePopStoreInfo(PopStoreInfoDTO popStoreInfoDTO, @PathParam("popStoreNum") String popStoreNum);

    @GET
    @Path("getPopInfo/{popNum}")
    ResponseDTO getPopInfo(@PathParam("popNum") String popNum);

    @GET
    @Path("getPopInfoByLoginName/{popLoginName}")
    ResponseDTO getPopInfoByLoginName(@PathParam("popLoginName")String popLoginName);
    @GET
    @Path("getPopInfoByMobile/{mobile}")
    ResponseDTO getPopInfoByMobile(@PathParam("mobile")String mobile);

    @PUT
    @Path("updatePopInfo/{popNum}")
    ResponseDTO updatePopInfo(PopInfoDTO popInfoDTO, @PathParam("popNum") String popNum);

    @GET
    @Path("getPopStoreDecorateInfo/{popStoreNum}")
    ResponseDTO getPopStoreDecorateInfo(@PathParam("popStoreNum") String popStoreNum);

    @PUT
    @Path("updatePopStoreDecorateInfo/{popStoreNum}")
    ResponseDTO updatePopStoreDecorateInfo(PopStoreDecorateDTO popStoreDecorateDTO);

    @POST
    @Path("insertPopStoreDecorateInfo/{popStoreNum}")
    ResponseDTO insertSelective(PopStoreDecorateDTO popStoreDecorateDTO);

    @POST
    @Path("getPopStoreBySearch")
    ResponseDTO getPopStoreBySearch(PopStoreSearchDTO popStoreSearchDTO);

    /**
     * 功能描述：更新品牌优势和售后服务
     * @author liukai
     * @param popStoreTextWithBLOBsDTO
     * @return
     */
    @PUT
    @Path("updateSysPopStoreTextWithBLOBs/{id}")
    ResponseDTO updateSysPopStoreTextWithBLOBs(PopStoreTextWithBLOBsDTO popStoreTextWithBLOBsDTO,
            @PathParam("id") Integer id);

    /**
     * 功能描述：获取品牌优势和售后服务
     * @author liukai
     * @param popStoreTextWithBLOBsDTO
     * @return
     */
    @GET
    @Path("getPopStoreTextWithBLOBs/{popStoreNum}")
    ResponseDTO getPopStoreTextWithBLOBs(@PathParam("popStoreNum") String popStoreNum);

    /**
     * 功能描述：添加品牌优势和售后服务
     * @author liukai
     * @param popStoreDecorateDTO
     * @return
     */
    @POST
    @Path("insertPopStoreTextWithBLOBs")
    ResponseDTO insertPopStoreTextWithBLOBs(PopStoreTextWithBLOBsDTO popStoreTextWithBLOBsDTO);

    /**
     * 功能描述：删除品牌优势和售后服务
     * @author liukai
     * @param popStoreDecorateDTO
     * @return
     */
    @DELETE
    @Path("deletePopStoreTextWithBLOBs/{id}")
    ResponseDTO deletePopStoreTextWithBLOBs(@PathParam("id") Integer id);

}
