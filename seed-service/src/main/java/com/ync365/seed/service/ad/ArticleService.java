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
 * 文章接口
 *     
 * @Title：ArticleService  
 * @Description: TODO   
 * @author: robo        
 * @date: 2015年9月29日 下午1:57:04      
 * @version     
 *
 */
@Path("article")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface ArticleService {

	/**
	 * 得到对应栏目的文章内容 
	 * @Title: getArticleList
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年9月29日 下午1:59:00       
	 * @version: 
	 *
	 * @param pageSize      显示记录条数
	 * @param categoryId    栏目Id
	 * @return
	 *
	 */
	@POST
	@GET
	@Path("/getArticleList/{pageSize}/{categoryId}")
	public ResponseDTO  getArticleList(@PathParam("pageSize")int pageSize,@PathParam("categoryId")Integer categoryId);
	
	@POST
	@GET
	@Path("/getArticleById/{articleId}")
	public ResponseDTO getArticleById(@PathParam("articleId") Integer articleId);
}
