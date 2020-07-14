package my.home.library.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {
	
	public Encryptor() {
	}
	
	public static String encryptMD5(String data) {
		MessageDigest md5;
		StringBuilder result = new StringBuilder();
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(data.getBytes());
			for(byte b : bytes) {
				result.append(String.format("%02X", b));
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result.toString();
	}
	
	
}
