package com.ync365.seed.admin.controller.base;

import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ync365.seed.admin.utils.UpYunFileManager;
import com.ync365.seed.bussiness.modules.goods.biz.PicInfoBiz;
import com.ync365.seed.bussiness.modules.goods.entity.PicInfo;
import com.ync365.seed.commons.mapper.JsonMapper;
import com.ync365.seed.utils.UUIDGenerator;



/**
 * 异步上传文件
 * @author 王佳田
 *
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    
    @Autowired
    private UpYunFileManager upYunFileManager;
    
    @Autowired
    PicInfoBiz picInfoBiz;
    
    @ResponseBody
    @RequestMapping(value="/uploadFile")  
    public Object uploadFile(HttpServletResponse response,HttpServletRequest request,@RequestParam(value="uploaderImage", required=false) MultipartFile file,Boolean secretKey,String elementId) throws Exception{  
        String[] strs = new String[2];
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        String rootC=basePath+"merchantAuth";
        String msg = "false";  
            msg = uploadPic(file,elementId,secretKey);
        strs[0]=msg;
        strs[1]=rootC+msg; 
        return strs;
    }  
    
    private String uploadPic(MultipartFile file,String elementId,Boolean secretKey) throws Exception {
         MultipartFile fileUpload = file;
         //后缀名
         String extensionName = null;
         //文件名
         String fileNameLong = null;
         // 文件名
         String fileName = null;
         //文件路径
         String filePath = null;
         // 判断是否有文件
         if (!fileUpload.isEmpty()) {
             // 获取文件上传路径名称
             fileNameLong = fileUpload.getOriginalFilename();
             // 获取文件扩展名
             extensionName = fileNameLong.substring(fileNameLong.lastIndexOf(".") + 1);
             Image src = javax.imageio.ImageIO.read(fileUpload.getInputStream());
             if (src == null) {
                 return "请上传正常图片";
             }
         } else {
             return "上传文件不能为空";
         }
         InputStream image = null;
             image = fileUpload.getInputStream();
             Calendar cal=Calendar.getInstance();//使用日历类
             int year=cal.get(Calendar.YEAR);//得到年
             int month=cal.get(Calendar.MONTH)+1;//得到月
             fileName =elementId+"_"+System.currentTimeMillis()+"."+extensionName ;
             filePath = "/m/auth/"+year+month+"/"+fileName;
             try {
                 //上传
                 if(secretKey){
                     
                     upYunFileManager.writeFile(filePath, toByteArray(image),"aa123456");
                 }
                 
         } catch (Exception e) {
             return "上传图片出错";
         } finally {
             if (image != null) {
                 image.close();
             }
         }
         return filePath;
     }
     /**
      * 字节流转换
      * @param in
      * @return
      * @throws IOException
      * @author 徐大伟
      * @date 2015年5月20日
      */
     private static byte[] toByteArray(InputStream in) throws IOException {
         ByteArrayOutputStream out=new ByteArrayOutputStream();
         byte[] buffer=new byte[1024*4];
         int n=0;
         while ( (n=in.read(buffer)) !=-1) {
             out.write(buffer,0,n);
         }
         return out.toByteArray();
     }
     
     @RequestMapping(value="upImgPic")
  	public void upImgPic(HttpServletResponse response,HttpServletRequest request,@RequestParam(value="uploaderImage", required=false) MultipartFile file) throws IOException{
  		
  		//图片不能为空
  		 if (!file.isEmpty()) {
 	    	
  			byte[] bytes = file.getBytes();
  	 		Calendar cal = Calendar.getInstance();// 使用日历类
  	 		int year = cal.get(Calendar.YEAR);// 得到年
  	 		int month = cal.get(Calendar.MONTH) + 1;// 得到月
  	 		
  	 		long currentTime = System.currentTimeMillis() ;
  	 		
  	 		//文件原名称 
 	    	String originalFilename = file.getOriginalFilename(); 
 	    	
 	    	String suffix = originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length() );
 	    	
  	 		String fileName = "auth_" + currentTime + suffix;
 		
 	 		String fileNameKerl = "auth_" + currentTime +"_s"+ suffix;
 	 		
 	 		String filePath = "/images/p/" + year + month + "/" + fileName;
 	 		
 	 		String filePathKerl= "/images/p/" + year + month + "/" + fileNameKerl;
 	 		// 上传
 	 		boolean isUpload = upYunFileManager.writeFile(filePath, bytes);
 	 		if(isUpload){
 	 			//创建缩略图
 	 			isUpload = upYunFileManager.writeThumbPic(filePathKerl, bytes, 200, 200) ;
 	 		}
 	 		
 	 		Map<String, Object> map = new HashMap<String, Object>();
 	 		map.put("url", filePath);
 	 		map.put("originalFilename", originalFilename);
 	 		response.setContentType("text/html");
 	 		
 	 		JsonMapper mapper = new JsonMapper() ;
 	 		response.getWriter().write(mapper.toJson(map));
 	 		response.flushBuffer();
 	    }
  	}
     
     
    @RequestMapping(value="upImg")
 	public void up(HttpServletResponse response,HttpServletRequest request,@RequestParam(value="uploaderImage", required=false) MultipartFile file,String id,String type,Integer isMain,Integer picNum,Integer has,Integer picId) throws IOException{
 		
 		//图片不能为空
 		 if (!file.isEmpty()) {
	    	
 			byte[] bytes = file.getBytes();
 	 		Calendar cal = Calendar.getInstance();// 使用日历类
 	 		int year = cal.get(Calendar.YEAR);// 得到年
 	 		int month = cal.get(Calendar.MONTH) + 1;// 得到月
 	 		
 	 		long currentTime = System.currentTimeMillis() ;
 	 		
 	 		//文件原名称 
	    	String originalFilename = file.getOriginalFilename(); 
	    	
	    	String suffix = originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length() );
	    	
 	 		String fileName = "auth_" + currentTime + suffix;
		
	 		String fileNameKerl = "auth_" + currentTime +"_s"+ suffix;
	 		
	 		String filePath = "/images/p/" + year + month + "/" + fileName;
	 		
	 		String filePathKerl= "/images/p/" + year + month + "/" + fileNameKerl;
	 		// 上传
	 		boolean isUpload = upYunFileManager.writeFile(filePath, bytes);
	 		if(isUpload){
	 			//创建缩略图
	 			isUpload = upYunFileManager.writeThumbPic(filePathKerl, bytes, 200, 200) ;
	 		}
	 		
	 		Map<String, Object> map = new HashMap<String, Object>();
	 		map.put("url", filePath);
	 		map.put("originalFilename", originalFilename);
	 		response.setContentType("text/html");
	 		
	 		JsonMapper mapper = new JsonMapper() ;
	 		response.getWriter().write(mapper.toJson(map));
	 		response.flushBuffer();
	 		
	 		
	 		if(id!=null){	 		    
	 		    //写数据库
	 		    PicInfo picInfo = new PicInfo();
	 		    picInfo.setPicName(filePath);
	 		    picInfo.setPicType(type);
	 		    picInfo.setUuid(id);
	 		    
	 		    if(picNum!=null&&picNum==1){
	 		       picInfo.setIsMain(1);
	 		    }else{
	 		       picInfo.setIsMain(isMain);
	 		    }	    
	 		    if(has!=null&&has==1){
	 		        picInfo.setId(picId);
	 		        picInfoBiz.updateByPrimaryKey(picInfo);
	 		    }else{
	 		        picInfoBiz.insert(picInfo);
	 		    }
	 		}
	    }
 	}
     
    
    /**
     * 功能描述：上传供应商店铺logo
     * @author liukai
     * @param response
     * @param request
     * @param file
     * @throws IOException
     */
    @RequestMapping(value="upPopStoreLogo")
 	public void upPopStoreLogo(HttpServletResponse response,HttpServletRequest request,@RequestParam(value="uploaderImage", required=false) MultipartFile file) throws IOException{
 		//图片不能为空
 		 if (!file.isEmpty()) {
	    	
 			byte[] bytes = file.getBytes();
 	 		Calendar cal = Calendar.getInstance();// 使用日历类
 	 		int year = cal.get(Calendar.YEAR);// 得到年
 	 		int month = cal.get(Calendar.MONTH) + 1;// 得到月
 	 		
 	 		String uuid = UUIDGenerator.getUUID();
 	 		String formatSuffix = ".170_100.jpg";
 	 		
 	 		long currentTime = System.currentTimeMillis() ;
 	 		
 	 		//文件原名称 
	    	String originalFilename = file.getOriginalFilename(); 
	    	
	    	String suffix = originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length() );
	    	
 	 		String fileName =  uuid + suffix;	//大图文件名
 	 	
	 		String fileNameKerl =  uuid + suffix + formatSuffix; 	//小图文件名
	 		
	 		String filePath = "/images/users/" + year + month + "/" + fileName;	//路径 + 大图文件名
	 		
	 		String filePathKerl= "/images/users/" + year + month + "/" + fileNameKerl;	//路径 + 小图文件名
	 		// 上传
	 		boolean isUpload = upYunFileManager.writeFile(filePath, bytes);		//上传大图
	 		if(isUpload){
	 			//创建缩略图
	 			isUpload = upYunFileManager.writeThumbPic(filePathKerl, bytes, 170, 100) ;	//上传小图
	 		}
	 		
	 		Map<String, Object> map = new HashMap<String, Object>();
	 		map.put("url", filePath);
	 		map.put("originalFilename", originalFilename);
	 		response.setContentType("text/html");
	 		
	 		JsonMapper mapper = new JsonMapper() ;
	 		response.getWriter().write(mapper.toJson(map));
	 		response.flushBuffer();
	    }
 	}
}
