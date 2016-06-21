package com.ync365.seed.service.common;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.ync365.seed.dto.base.ResponseDTO;

/**
 * 区域接口
 *     
 * @Title：RegionService  
 * @Description: TODO   
 * @author: robo        
 * @date: 2015年9月30日 下午2:30:28      
 * @version     
 *
 */
@Path("region")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface RegionService {

    /**
     * 根据编码得到对应的区域
     * @Title: getRegion
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年9月30日 下午2:31:26       
     * @version: 
     *
     * @param code   区域编码
     * @return
     *
     */
    @POST
    @GET
    @Path("getRegion/{code}")
    public ResponseDTO getRegion(@PathParam("code") String code);

    /**
     * 得到省市两级列表数据
     * @Title: getRegionProvinceCityList
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年9月30日 下午2:34:40       
     * @version: 
     *
     * @return
     *
     */
    @POST
    @GET
    @Path("getRegionProvinceCityList")
    public ResponseDTO getRegionProvinceCityList();

    /**
     * 得到省市县数据列表
     * @Title: getRegionProvinceCityCountyList
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月2日 下午2:49:58       
     * @version: 
     *
     * @return
     *
     */
    @POST
    @GET
    @Path("getRegionProvinceCityCountyList")
    public ResponseDTO getRegionProvinceCityCountyList();

    /**
     * 通过PID获取子列表
     * @Title: getRegionByPId
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月9日 上午10:28:53       
     * @version: 
     *
     * @param pId
     * @return
     *
     */
    @POST
    @GET
    @Path("getRegionByPId/{PId}")
    public ResponseDTO getRegionByPId(@PathParam("PId") String pId);
    /**
     * 获取区域及其父区域
     * @Title: getRegionAndParentRegionById
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月20日 上午9:37:15       
     * @version: 
     *
     * @param id
     * @return
     *
     */
    @GET
    @Path("getRegionAndParentRegionById/{id}")
    public ResponseDTO getRegionAndParentRegionById(@PathParam("id") Integer id);
}
