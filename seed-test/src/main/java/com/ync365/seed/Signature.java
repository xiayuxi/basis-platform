package com.ync365.seed;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

import com.ync365.seed.commons.rsa.Base64;

public class Signature {
	 public static byte[] sign(byte[] priKeyText, String plainText) {
	  try {
		  System.out.println(priKeyText.toString());
	   System.out.println(Base64.decode(priKeyText.toString()));
	   PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec( Base64.decode(priKeyText.toString()));
	   KeyFactory keyf = KeyFactory.getInstance("RSA");
	   PrivateKey prikey = keyf.generatePrivate(priPKCS8);

	   // 用私钥对信息生成数字签名
	   java.security.Signature signet = java.security.Signature
	     .getInstance("MD5withRSA");
	   signet.initSign(prikey);
	   signet.update(plainText.getBytes());
	   byte[] signed = Base64.encode(signet.sign()).getBytes(); 
	   return signed;
	  } catch (java.lang.Exception e) {
	   System.out.println("签名失败");
	   e.printStackTrace();
	  }
	  return null;
	 }
	 public static void main(String[] args) {
		Signature s=new Signature();
		String str= "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIA1lWKd9UdVJEbZHNyGDPZe9cSW62rGJ+dV4k3n5XFBN6hvlNw4hHDRtCrvOMzlO6rY4eXguDuiC75rslv6Al628nA+uwWtgmwFMgjCQGNvqrzEFLKjkXx8Onn9mbZImAVqbcVt8tgHe+qHPWRtyptuBZnV1u8JbFSt5zM/RY8RAgMBAAECgYBqsKx8oWgAkVib4IbE+ISG7STmEJUdiIKiXvTw0b48jgIMF7avwBRucgPVCregwk3x8YOisWt+rG3La4HESnt3MfusAAGNUioO5I5MFUEyRDoMq3hh+zySvnzw/h3aONNAgYV6d/0qyWCmMWST7NyPfsODB3inJY71oHpuZmuBcQJBAOXVU9oLDHvqg2vLpaCwAYV8noNvAlOEgEq/GDV9X6+TU/H4k9c+BsijC6rSeYeRtSKiRZ84qA9ZAPZaM/vrmS0CQQCOzlg64lpthn+AQfaICx2a1IvtRDDigyAWpvWY7nFelZ26dzshKMtptOIZhqhxhChlDOcjNbbSPb8So4URaDP1AkEAsIVjLKG1yeq5W26C3GKyGHM5T9tP3xNycXZJwrNzbWdrXvo7mmKSVUEc8etL2froMxyM+phKQ9dpLMzlBkTSVQJAeLDz6HA9NKujPmaBUOD0GFLiH5iuKAHiMMLSdnmu3t1b/KegFZpAsAnrvN8NpUKoUR7iieNvq6f7wGjqoZeLsQJBANprDC/OPq9YgFgthOzg0B3ujT4xV14T4S8WOGpqIwHuIm3x0a0/zsb2rB8o+QnFurAtQWZmQ15SaJIwUJUjXI8=";
		byte[] priKeyText=str.getBytes();
		byte[] signature =s.sign(priKeyText, "guanfl");
		System.out.println(signature);
	}
}
