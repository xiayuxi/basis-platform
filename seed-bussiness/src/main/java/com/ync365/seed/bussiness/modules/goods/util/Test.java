package com.ync365.seed.bussiness.modules.goods.util;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String ss = "1,2,3";
		String s [] = ss.split(",");
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("2");
		list.add("4");
		
		
		for(int i = 0 ; i < s.length ; i++ ){
			String v = (String)s[i];
			for(int k = 0 ; k < list.size();k++){
				String t = list.get(k) ;
				
				if(v.equals(t)){
					list.remove(k);
					k--;
				}
			}
		}
		
		for(String str :list){
			System.out.println(str);
		}
		
		
	}

}
