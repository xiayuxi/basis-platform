package com.ync365.seed.bussiness.modules.user.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：集合工具类
 * @author liukai
 * @date 2015年10月16日 下午2:49:39 
 * @version 1.0
 */
public class ListUtil {
	
	public static List<Integer> getUniqueIds(List<Integer> collection1, List<Integer> collection2) {
		List<Integer> a = new ArrayList<Integer>(collection1);
		List<Integer> b = new ArrayList<Integer>(collection2);
		a.removeAll(collection2);
		b.removeAll(collection1);

		List<Integer> result = new ArrayList<Integer>();
		result.addAll(a);
		result.addAll(b);
		return result;
	}
	
	public static List<String> getStringUniqueIds(List<String> collection1, List<String> collection2) {
		List<String> a = new ArrayList<String>(collection1);
		List<String> b = new ArrayList<String>(collection2);
		a.removeAll(collection2);
		b.removeAll(collection1);

		List<String> result = new ArrayList<String>();
		result.addAll(a);
		result.addAll(b);
		System.out.println(result.toString());
		return result;
	}
	
	public static void main(String[] args) {
		List<String> a = new ArrayList<String>();
		List<String> b = new ArrayList<String>();
		a.add("30");
		a.add("40");
		
		b.add("40");
		b.add("60");
		getStringUniqueIds(a,b);
		
	}
}
