package com.ync365.seed.admin.utils;

import java.io.File;
import java.io.IOException;

/**
 * 文件管理器
 * 主要提供文件上传下载等功能
 * @author 
 *
 */
public interface FileManager {
	/**
	 * 上传文件
	 * @param filePath，路径必须包含文件名
	 * @param datas 
	 * @return
	 * @throws IOException
	 */
	public boolean writeFile(String filePath, byte[] datas)throws IOException ;
	/**
	 * 上传文件(带访问密钥)
	 * @param filePath，路径必须包含文件名
	 * @param datas
	 * @param secret 密钥
	 * @return
	 * @throws IOException
	 */
	public boolean writeFile(String filePath, byte[] datas,String secret)throws IOException ;
	
	/**
	 * 上传缩略图
	 * @param filePath，路径必须包含文件名
	 * @param datas 
	 * @param width  缩略图 宽
	 * @param height 缩略图 高
	 * @return
	 * @throws IOException
	 */
	public boolean writeThumbPic(String filePath,  byte[] datas,int width, int height) throws IOException ;
	
	public void redFile(String filePath, File file) ;
	
}
