package org.eastwill.webtest;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.lang.StringUtils;

public class TestMD5Sign {

	public static void main(String[] args) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String tinstr = "FUN_CODE=1001&REQ_ENCRYPTED=7rirzekAo8RJxeMwTuqY9QXBubyQ/bZk9yL4mqu3/2e7uGOtlRSC8Ix8Ileuqgr5f3IayaGZmc79QAi65TOjy2NHrTlPtaZj2TlwgYVYZI0=&USER_ID=LN12320&key=2098D32C4D1399EC";
        String toutstr = md5(tinstr);
        System.out.println(toutstr);
	}
	
	public static String md5(String content) {
	    if (StringUtils.isBlank(content)) {
	        return null;
	    }
	    try {
	        byte[] b = content.getBytes("UTF-8");
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.reset();
	        md.update(b);
	        byte[] hash = md.digest();
	        StringBuffer outStrBuf = new StringBuffer(32);
	        for (int i = 0; i < hash.length; i++) {
	            int v = hash[i] & 0xFF;
	            if (v < 16) {
	                outStrBuf.append('0');
	            }
	            outStrBuf.append(Integer.toString(v, 16).toLowerCase());
	        }
	        return outStrBuf.toString();
	    } catch (Exception e) {
	        System.out.println("sign_utils签名错误"+e);
	    }
	    return null;
	}
    
}
