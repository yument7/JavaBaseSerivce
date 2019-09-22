package com.leach.foundation.encryption;

import java.io.IOException;

import sun.misc.BASE64Decoder;

/**
 * @explain 
 * @author lvao
 * @date  2018年4月13日
 */
public class BASE64 {
	
	//加密
	public static byte[] encrypt(String key) throws IOException{
		BASE64Decoder decoder =new BASE64Decoder();
		decoder.decodeBuffer(key);
		return null;
	}
	
	
	//解密
	public static String decrypt(byte[] key) {
		
		
		return null; 
	}
}
