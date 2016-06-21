package com.ync365.seed.utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ConfigUtils {

	private static PropertiesConfiguration config = new PropertiesConfiguration();
	static {
		try {
			config.load("application.properties");
		} catch (ConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getStringByKey(String key) {
		return config.getString(key);
	}

	/**
	 * 上传图片的基础路径
	 */
	public static String getUploadImgBasePath() {
		return config.getString("sys.upload.basepath");
	}
	
	/**
	 * 上传图片的访问路径
	 */
	public static String getUploadImgAccessUrl() {
		return config.getString("sys.upload.accessurl");
	}
}
