package com.ync365.seed.utils;

/**
 * 
 * 功能描述: 环境变量配置
 * 
 */
public class Configuration {
  
	/**
	 * 公有密钥
	 */
	private String publickeyStore ;
	
	/**
	 * 私有密钥
	 */
	private String privatekeyStore;

	public String getPublickeyStore() {
		return publickeyStore;
	}

	public void setPublickeyStore(String publickeyStore) {
		this.publickeyStore = publickeyStore;
	}

	public String getPrivatekeyStore() {
		return privatekeyStore;
	}

	public void setPrivatekeyStore(String privatekeyStore) {
		this.privatekeyStore = privatekeyStore;
	}

    
}
