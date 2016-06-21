package com.ync365.seed;

import java.math.BigDecimal;

public class ConstantsDemo {
	public static void main(String[] args) {
		BigDecimal a=new BigDecimal(1.0);
		BigDecimal b=new BigDecimal(1.00);
		System.out.println(a==b);
		System.out.println(a.equals(b));
		System.out.println(a.compareTo(b)==0);
	}
}
