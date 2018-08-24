package org.eastwill.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * AES加密类
 * 
 * @author Administrator
 * @since 2015-03-24
 * 
 */
public class AesUtils {

	/**
	 * 加密
	 * 
	 * @param secKey
	 * @param encryptedString
	 * @return
	 * @author Administrator
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @since 2015-03-24
	 */
	public static String encrypt(String secKey, String encryptedString) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
	
		if (secKey == null) {
			System.out.print("encryptKey为空null");
		   return null;
		}
		// 判断Key是否为16位
		if (secKey.length() != 16) {
		   System.out.print("Key长度不是16位");
		   return null;
		}
		byte[] raw = secKey.getBytes("utf-8");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(encryptedString.getBytes("utf-8"));
		return new Base64().encodeToString(encrypted); 

	}

	/**
	 * 解密
	 * 
	 * @param secKey
	 * @param encryptedString
	 * @return
	 * @author Administrator
	 * @since 2015-03-24
	 */
	public static String decrypt(String secKey, String encryptedString) {
		try {
		  // 判断Key是否正确
		  if (secKey == null) {
		        System.out.print("Key为空null");
		        return null;
		  }
		  // 判断Key是否为16位
		  if (secKey.length() != 16) {
		        System.out.print("Key长度不是16位");
		        return null;
		  }
		  byte[] raw = secKey.getBytes("utf-8");
		  SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		  Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		  cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		  byte[] encrypted1 = new Base64().decode(encryptedString);//先用base64解密
		  
		  byte[] original = cipher.doFinal(encrypted1);
		  String originalString = new String(original,"utf-8");
		  return originalString;
		           
		  } catch (Exception ex) {
		      System.out.println(ex.toString());
		      return null;
		  }

	}

}
