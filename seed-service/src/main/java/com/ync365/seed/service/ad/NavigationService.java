package com.ync365.seed.service.ad;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.ync365.seed.dto.base.ResponseDTO;

/**
 * 导航接口
 *     
 * @Title：NavigationService  
 * @Description: TODO   
 * @author: robo        
 * @date: 2015年10月27日 下午6:30:18      
 * @version     
 *
 */
@Path("nav")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface NavigationService {

	/**
	 * 
	 * @Title: getNavigationList
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月27日 下午6:30:56       
	 * @version: 
	 *
	 * @return
	 *
	 */
	@GET
	@POST
	@Path("getNavigationList")
	public ResponseDTO getNavigationList();
}
