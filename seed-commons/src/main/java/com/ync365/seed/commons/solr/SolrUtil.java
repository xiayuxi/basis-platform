/**
 * Project Name:tht-common-util
 * File Name:SolrUtil.java
 * Package Name:com.tht.util.solr
 * Date:2014年8月7日上午9:20:06
 * Copyright (c) 2014, chenzhou1025@126.com All Rights Reserved.
 *
 */

package com.ync365.seed.commons.solr;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;

import com.ync365.seed.utils.StringUtils;
import com.ync365.seed.web.vo.Grid;


/**
 * 
 * Solr公共类
 * @author robo
 *
 */
public class SolrUtil {

	public static HttpSolrServer server = null;

	private final static String url = "http://192.168.98.76:8080/solr/sku/";// url为solr服务的地址
	// private final static String contentType =
	// "text/plain;charset=utf-8";//富文本头部策略
	private final static String CHARSET = "charset=utf-8";
	private final static String CONTENTTYPE_WORD = "word/plain;" + CHARSET;// WORD富文本头部策略
	private final static String CONTENTTYPE_PDF = "pdf/plain;" + CHARSET;// pdf富文本头部策略
	private final static String CONTENTTYPE_TXT = "txt/plain;" + CHARSET;// txt富文本头部策略
	private final static String CONTENTTYPE_XLS = "xls/plain;" + CHARSET;// xls富文本头部策略
	private final static String CONTENTTYPE_PPT = "ppt/plain;" + CHARSET;// xls富文本头部策略
	private static final String CONTENTTYPE_XML = "application/xml" + CHARSET;
	private final static String CONTENTTYPE_WORDS = "application/octet-stream";
	private final static String CONTENTTYPE_OTHER = "application/octet-stream"
			+ CHARSET;// WORD富文本头部策略
	public String shards = "";

	static {
		initSolr();
	}

	public SolrUtil(String url, String shards) {
		// this.url = url;
		this.shards = shards;
		initSolr();
	}

	// 初始化Server
	private static void initSolr() {

		try {

			server = new HttpSolrServer(url);
			server.setSoTimeout(60 * 1000);
			server.setConnectionTimeout(60 * 1000);
			server.setDefaultMaxConnectionsPerHost(1000);
			server.setMaxTotalConnections(1000);
			server.setFollowRedirects(false);
			server.setAllowCompression(true);

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * addDocList(批量添加)
	 */
	public static void addDocList(Collection<SolrInputDocument> docs) {
		try {
			server.add(docs);
			server.commit();
			docs.clear();// 释放
		} catch (Exception e) {
			Logger.getLogger(SolrUtil.class).error("addDocList error.", e);
		}
	}

	/**
	 * addDoc(添加单个文件)
	 */
	public static void addDoc(SolrInputDocument docs) {

		try {
			server.add(docs);
			server.commit();
			docs.clear();// 释放
		} catch (Exception e) {
			Logger.getLogger(SolrUtil.class).error("addDocList error.", e);
		}
	}
	
	/**
	 * 
	 * @param obj
	 */
	public static void addObject(Object obj) {
		
		try{ 
			server.addBean(obj);
			server.commit(false, false);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addObject(Map<String,Object> map) {
		
		try{ 
			SolrInputDocument doc = new SolrInputDocument();
	        doc.addField("id", map.get("id"));
	        doc.addField("name", map.get("name"));
	        doc.addField("featureName", map.get("featureName"));
	        doc.addField("brandName", map.get("brandName"));
	        doc.addField("code", map.get("code"));
	        doc.addField("featureValue", map.get("featureValue"));
	        doc.addField("categoryName", map.get("categoryName"));
	        
			server.add(doc);
			server.commit(false, false);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static <T> void addObjectList(List<T> objectList) {
		try {
			 
			server.addBeans(objectList);
			server.commit(false, false);
			//logger.info("成功提交 " + objectList.size() + " 个元素到索引库中");
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * deleteDocByQuery(删除索引)
	 * 
	 */
	public static void deleteDocByQuery(String query) throws Exception {
		try {
			server.deleteByQuery(query);
			server.commit();
		} catch (Exception e) {
			Logger.getLogger(SolrUtil.class)
					.error("deleteDocByQuery error.", e);
			throw e;
		}
	}

	/**
	 * query(查询返回list结果)
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List query( SolrParams params, Class c)
			throws SolrServerException {

		QueryResponse response = null;
		try {
			response = server.query(params);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.getBeans(c);
	}

	/**
	 * query(查询返回SolrDocumentList集合,每个solrdocument都有getFieldValue("xxx")功能)
	 */
	public static SolrDocumentList query(SolrParams params)
			throws SolrServerException {

		QueryResponse response = null;
		try {
			response = server.query(params);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.getResults();
	}

	/**
	 * queryEasy(查询返回queryresponse)
	 */
	public static QueryResponse queryEasy(SolrParams params)
			throws SolrServerException {
		QueryResponse response = null;
		try {
			response =  server.query(params);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response ;
	}

	/**
	 * addResumeDoc
	 * 
	 */
	public static void addDoc(File file, Map<String, String> params,String fileName)
			throws IOException, SolrServerException {

		ContentStreamUpdateRequest cs = new ContentStreamUpdateRequest(
				"/update/extract");
		String contentType = getFileContentType(fileName);
		cs.addFile(file, contentType + CHARSET);

		String key = null;
		String value = null;

		for (Map.Entry<String, String> entry : params.entrySet()) {

			key = entry.getKey();
			value = entry.getValue();

			cs.setParam(key, value);
		}
		cs.setParam("uprefix", "attr_");
		cs.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);

		server.request(cs);
	}

	/**
	 * 根据文件名获取文件的ContentType类型
	 * 
	 * @param filename
	 * @return
	 */
	public static String getFileContentType(String filename) {
		
		String contentType = "";
		String prefix = filename.substring(filename.lastIndexOf(".") + 1);
		
		if (prefix.equals("xlsx")) {
			
			contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
			
		} else if (prefix.equals("pdf")) {
			
			contentType = "application/pdf";
			
		} else if (prefix.equals("doc")) {
			
			contentType = "application/msword";
			
		} else if (prefix.equals("txt")) {
			
			contentType = "text/plain";
			
		} else if (prefix.equals("xls")) {
			
			contentType = "application/vnd.ms-excel";
			
		} else if (prefix.equals("docx")) {
			
			contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
			
		} else if (prefix.equals("ppt")) {
			
			contentType = "application/vnd.ms-powerpoint";
			
		} else if (prefix.equals("pptx")) {
			
			contentType = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
			
		}else {
			contentType = "othertype";
		}

		return contentType;
	}
	
	public static Grid searchList(GoodsQueryBean bean, int pageSize,int startIndex) throws IOException,
            ParseException {
        
          	long totalSize = 0;
         	SolrQuery params = new SolrQuery();
	        // 查询关键词，*:*代表所有属性、所有值，即所有index
	        // params.set("q", "*:*");
         	if(StringUtils.isNotBlank(bean.getName())){
         		params.set("q", "name:"+bean.getName()); 
         	}
         	if(StringUtils.isNotBlank(bean.getFeatureName())){
         		params.set("q", "featureName:"+bean.getFeatureName());
         	}
         	
         	
         	if(StringUtils.isNotBlank(bean.getFeatureValue())){
         		params.set("q", "featureValue:"+bean.getFeatureValue()+"*");
         	}
         
	        // 分页，start=0就是从0开始，，rows=5当前返回5条记录，第二页就是变化start这个值为5就可以了。
        	params.setStart(startIndex);
    		params.setRows(pageSize);
	 
	        // 按nickname排序，asc升序 desc降序
    		params.setSort("id", ORDER.asc);
	        
	        params.setFacet(true).setFacetMinCount(1).addFacetField("name");
	        SolrDocumentList solrDocumentList  = null;
	        
	        List<GoodsResultBean> list = new ArrayList<GoodsResultBean>();
	        
	        Grid grid = new Grid() ;
	        try {
	            QueryResponse rsp = server.query(params);
	            solrDocumentList = rsp.getResults();
	            
	            totalSize = solrDocumentList.getNumFound();
	            for (SolrDocument doc : solrDocumentList) {
	            	 
	            	GoodsResultBean gb = new GoodsResultBean() ;
	  
	                gb.setId( ((Long)doc.getFieldValue("id")).intValue());
	                gb.setName((String)doc.getFieldValue("name"));
	                gb.setBrandName((String)doc.getFieldValue("brandName"));
	                gb.setCategoryName((String)doc.getFieldValue("categoryName"));
	                gb.setFeatureName((String)doc.getFieldValue("featureName"));
	                gb.setFeatureValue((String)doc.getFieldValue("featureValue"));
	                gb.setCode((String)doc.getFieldValue("code"));
	                
	                list.add(gb) ;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }  
	        
	        grid.setRows(list);
	        grid.setTotal(totalSize);
        return grid;
       
    }
	
	
	public static Grid searchList(String content, int pageSize,
			int startIndex) throws IOException, ParseException {

		long totalSize = 0;
		SolrQuery params = new SolrQuery();
		// 查询关键词，*:*代表所有属性、所有值，即所有index
		// params.set("q", "*:*");
		 
		if (StringUtils.isNotBlank(content)) {
			params.set("q", "content:" + content + "*");
		}

		// 分页，start=0就是从0开始，，rows=5当前返回5条记录，第二页就是变化start这个值为5就可以了。
	 
		params.setStart(startIndex);
		params.setRows(pageSize);

		// 按nickname排序，asc升序 desc降序
		params.setSort("id", ORDER.asc);

		params.setFacet(true).setFacetMinCount(1).addFacetField("name");
		SolrDocumentList solrDocumentList = null;

		List<GoodsResultBean> list = new ArrayList<GoodsResultBean>();

		Grid grid = new Grid();
		try {
			QueryResponse rsp = server.query(params);
			solrDocumentList = rsp.getResults();

			totalSize = solrDocumentList.getNumFound();
			for (SolrDocument doc : solrDocumentList) {
				 
				GoodsResultBean gb = new GoodsResultBean();

				gb.setId(((Long) doc.getFieldValue("id")).intValue());
				gb.setName((String) doc.getFieldValue("name"));
				gb.setBrandName((String) doc.getFieldValue("brandName"));
				gb.setCategoryName((String) doc.getFieldValue("categoryName"));
				gb.setFeatureName((String) doc.getFieldValue("featureName"));
				gb.setFeatureValue((String) doc.getFieldValue("featureValue"));
				gb.setCode((String)doc.getFieldValue("code"));

				list.add(gb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		grid.setRows(list);
		grid.setTotal(totalSize);
		return grid;

	}
	

	public static void main(String[] args) throws IOException, Exception {
		/**
		GoodsQueryBean bean = new GoodsQueryBean() ;
		//bean.setName("化肥一号");
		bean.setFeatureValue("25");
		Grid grid = searchList( bean,  10, 0);
		System.out.println(grid);*/
		 
		
		 	SolrQuery params = new SolrQuery();
	        // 查询关键词，*:*代表所有属性、所有值，即所有index
	        // params.set("q", "*:*");
	       // params.set("q", "name:化肥一号 ");// 查询nickname是已chm开头的数据
	        //params.set("q", "feature:颜色");
	 
	        params.setQuery("name:12  AND code:10004");
	        // 分页，start=0就是从0开始，，rows=5当前返回5条记录，第二页就是变化start这个值为5就可以了。
 
	        // 按nickname排序，asc升序 desc降序
	        
	        params.setStart(0);
	        params.setRows(5);
	        params.setSort("id", ORDER.asc);
	        
	        params.setFacet(true)
            .setFacetMinCount(1)
            .addFacetField("name");
	        
	      
	 
	        try {
	            QueryResponse rsp = server.query(params);
	            SolrDocumentList docs = rsp.getResults();
	            System.out.println("文档个数：" + docs.getNumFound());
	            System.out.println("查询时间：" + rsp.getQTime());
	            for (SolrDocument doc : docs) {
	                String name = (String) doc.getFieldValue("name");
	                System.out.println(doc);
	                System.out.println(name);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	        
	      //add();
	}
	
	
	public static  void add(){
		
		Map map = new HashMap() ;
		
		  map.put("id",5);
          map.put("name","中农化复合肥1");
          map.put("featureName","尺码");
          map.put("brandName","中国制造");
          map.put("code","10005");
          map.put("featureValue","25袋，50袋，100袋");
          map.put("categoryName","化肥");
          
          addObject(map);
	}
}
