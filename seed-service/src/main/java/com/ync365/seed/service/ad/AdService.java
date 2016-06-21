package com.ync365.seed.service.ad;

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
 * 
 * 广告接口    
 * @Title：AdService  
 * @Description: TODO   
 * @author: robo        
 * @date: 2015年9月28日 下午6:06:00      
 * @version     
 *
 */
@Path("ad")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface AdService {

	/**
	 * 得到广告 图片列表
	 * @Title: getAdList
	 * @Description: TODO
	 * @param pageSize      显示条数
	 * @param adPosition    广告位
	 * @return ResponseDTO    
	 * @throws
	 */
	@POST
	@GET
	@Path("getAdList/{pageSize}/{adPosition}")
	public ResponseDTO  getAdList(@PathParam("pageSize") int pageSize,@PathParam("adPosition") int adPosition);
}
