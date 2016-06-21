package com.ync365.seed;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import com.ync365.seed.commons.rsa.Base64;

public class KeyGenerater {
	private byte[] priKey;
	private byte[] pubKey;

	public void generater() {
		try {
			java.security.KeyPairGenerator keygen = java.security.KeyPairGenerator
					.getInstance("RSA");
			SecureRandom secrand = new SecureRandom();
			secrand.setSeed("syj".getBytes()); // 初始化随机产生器
			keygen.initialize(1024, secrand);
			KeyPair keys = keygen.genKeyPair();

			PublicKey pubkey = keys.getPublic();
			PrivateKey prikey = keys.getPrivate();

			pubKey = Base64.encode(pubkey.getEncoded()).getBytes();
			priKey = Base64.encode(prikey.getEncoded()).getBytes();

			System.out.println("pubKey = " + new String(pubKey));
			System.out.println("priKey = " + new String(priKey));
		} catch (java.lang.Exception e) {
			System.out.println("生成密钥对失败");
			e.printStackTrace();
		}
	}

	public byte[] getPriKey() {
		return priKey;
	}

	public void setPriKey(byte[] priKey) {
		this.priKey = priKey;
	}

	public byte[] getPubKey() {
		return pubKey;
	}

	public void setPubKey(byte[] pubKey) {
		this.pubKey = pubKey;
	}

	public static void main(String[] args) {
		KeyGenerater key = new KeyGenerater();
		key.generater();
	}
}
