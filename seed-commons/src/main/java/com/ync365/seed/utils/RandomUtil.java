/**   
 * @Title: RandomUtil.java 
 * @Package com.ync365.uic.util 
 * @Description: TODO() 
 * @author s - > suilei 
 * @date 2015年1月30日 下午4:04:06 
 * @version V1.0   
 */
package com.ync365.seed.utils;

/**
 * @author s 随机数生成工具类
 */
public class RandomUtil {
	
	 public static final String SMS_AUTH_CODE_NUMBER           = "1234567890";
	 public static final String SMS_AUTH_CODE_NUMBER_CHARACTER = "1234567890abcdefghijkmnpqrstuvwxyz";
	    
	/**
	 * 
	 * @Title: createRandom
	 * @author: s -> suilei
	 * @date 2015年1月30日 下午4:04:58
	 * @param @param numberFlag
	 * @param @param length
	 * @param @return
	 * @Description: TODO()
	 * @return String 返回类型
	 * @throws
	 */
	public static String createRandom(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? SMS_AUTH_CODE_NUMBER
				: SMS_AUTH_CODE_NUMBER_CHARACTER;
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}
}
