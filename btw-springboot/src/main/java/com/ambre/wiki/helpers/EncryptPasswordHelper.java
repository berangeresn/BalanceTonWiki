package com.ambre.wiki.helpers;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptPasswordHelper {

	/**
	 * Encrypt the password
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String encryptedPassword(String password) {
		String encryptedPassword = null;
		if ((password!=null) && (!password.isEmpty())) {
			try {
			    MessageDigest digest = MessageDigest.getInstance("SHA-256");
			    byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			    StringBuffer hexString = new StringBuffer();
			    String hex = null;
			    for (int i = 0; i < encodedHash.length; i++) {
			        hex = Integer.toHexString(0xff & encodedHash[i]);
			        if (hex.length() == 1) {
			            hexString.append('0');
			        }
			        hexString.append(hex);
			    }
			    encryptedPassword = hexString.toString();
			} catch (Exception e) {
			}			
		} 
	    return encryptedPassword;
	}
	
}
