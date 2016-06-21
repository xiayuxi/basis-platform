package com.ync365.seed.admin.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.com.UpYun;
import main.java.com.UpYun.PARAMS;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 基于UpYun的文件管理功能
 *
 */
public class UpYunFileManager implements FileManager {
	private static final Logger logger = LoggerFactory
			.getLogger(UpYunFileManager.class);
	
	private static final String PARAM_SECRET="secret";
	
	/**
	 * 空间名称
	 */
	private String bucketName = "";
	/**
	 * 操作员名称
	 */
	private String operatorName = "";
	/**
	 * 操作员密码
	 */
	private String operatorPwd = "";
	
	private int timeout=60;
	
	private String fileServerUrl="http://yncnslm.b0.upaiyun.com";
	
	



	public boolean writeFile(String filePath, byte[] datas)throws IOException {
		return write(filePath, datas, null);
	}
	
	public boolean writeFile(String filePath, byte[] datas,String secret)throws IOException {
		Map<String, String> params =null;
		if(StringUtils.isNotBlank(secret)){
			params= new HashMap<String, String>();
			params.put(PARAM_SECRET, secret);
		}
		return write(filePath, datas, params);
	}
	public boolean writeThumbPic(String filePath,  byte[] datas,int width, int height) throws IOException {
		// 设置缩略图的参数
		Map<String, String> params = new HashMap<String, String>();

		// 设置缩略图类型，必须搭配缩略图参数值（KEY_VALUE）使用，否则无效
		params.put(PARAMS.KEY_X_GMKERL_TYPE.getValue(),
				PARAMS.VALUE_FIX_BOTH.getValue());

		// 设置缩略图参数值，必须搭配缩略图类型（KEY_TYPE）使用，否则无效
		params.put(PARAMS.KEY_X_GMKERL_VALUE.getValue(), width + "x" + height);

		// 设置缩略图的质量，默认 95
		params.put(PARAMS.KEY_X_GMKERL_QUALITY.getValue(), "95");

		// 设置缩略图的锐化，默认锐化（true）
		params.put(PARAMS.KEY_X_GMKERL_UNSHARP.getValue(), "true");

		// 若在 upyun 后台配置过缩略图版本号，则可以设置缩略图的版本名称
		// 注意：只有存在缩略图版本名称，才会按照配置参数制作缩略图，否则无效
		params.put(PARAMS.KEY_X_GMKERL_THUMBNAIL.getValue(), "small");
		
		return write(filePath, datas, params);
	}
	
	
	private  boolean write(String filePath, byte[] bytes,Map<String, String> params) throws IOException {
		UpYun upyun = new UpYun(bucketName, operatorName, operatorPwd);
		// 切换 API 接口的域名接入点，默认为自动识别接入点
		 upyun.setApiDomain(UpYun.ED_AUTO);
		// 设置连接超时时间，默认为30秒
		 upyun.setTimeout(timeout);
		// 设置是否开启debug模式，默认不开启
		upyun.setDebug(true);
		if(filePath==null || filePath.trim()==""){
			logger.error("文件路径不能为空:{}",filePath);
			return false;
		}
		boolean auto =false;
		//判断目录是否存在
		String rPath= StringUtils.removePattern(filePath, "[\\w[.-]]+\\.[\\w]+");
		if(StringUtils.isNotBlank(rPath)){//如果存在设置自动创建目录
			Map<String,String> filePathInfo =upyun.getFileInfo(rPath);
			if(filePathInfo==null){
				auto=true;
			}
		}
		
		//设置密钥
		if(params!=null){
			String secret =params.get(PARAM_SECRET);
			if(secret!=null && secret.trim()!=""){
				upyun.setFileSecret(secret);
				params.remove(PARAM_SECRET);
			}
		}
		return  upyun.writeFile(filePath, bytes, auto, null);
	}


	/**
	 * 读取文件
	 * 
	 * @param filePath
	 *            文件路径（包含文件名）
	 * @param file
	 *            临时文件
	 * @return true or false
	 */
	public void redFile(String filePath, File file) {
		UpYun upyun = new UpYun(bucketName, operatorName, operatorPwd);
		// 切换 API 接口的域名接入点，默认为自动识别接入点
		 upyun.setApiDomain(UpYun.ED_AUTO);
		// 设置连接超时时间，默认为30秒
		 upyun.setTimeout(timeout);
		// 设置是否开启debug模式，默认不开启
		upyun.setDebug(true);
		upyun.readFile(filePath, file);
	}
	/**
	 * 根据文件路径获取url访问路径
	 * @param filePath
	 * @param secret 密钥   
	 * @return url访问路径
	 */
	public String getFileURL(String filePath,String secret){
		//	http://yncnslm.b0.upaiyun.com/crop.jpg
		StringBuffer buff = new StringBuffer(getFileServerUrl());
		buff.append(filePath);
		if(StringUtils.isNoneBlank(secret)){
			buff.append("!").append(secret);
		}
		return buff.toString();
	}

	public List<Map<String,Object>> readDir(String filePath){
		UpYun upyun = new UpYun(bucketName, operatorName, operatorPwd);
	    List<UpYun.FolderItem> items = upyun.readDir(filePath);
	    Map<String,Object> map = null;
	    List<Map<String,Object>>  list = new ArrayList<Map<String,Object>>();
	    for(UpYun.FolderItem ft : items){
	    	map =new HashMap<String,Object>();
	    	map.put("name", ft.name);
	    	map.put("type", ft.type);
	    	map.put("date", ft.date);
	    	map.put("size", ft.size);
	    	list.add(map);
	    }
		return list;
	}
	

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorPwd() {
		return operatorPwd;
	}

	public void setOperatorPwd(String operatorPwd) {
		this.operatorPwd = operatorPwd;
	}
	public void setFileServerUrl(String fileServerUrl) {
		this.fileServerUrl = fileServerUrl;
	}
	public String getFileServerUrl() {
		return fileServerUrl;
	}
}
