package org.eastwill.webtest;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;

import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


import org.apache.commons.codec.digest.DigestUtils;
import org.eastwill.utils.AesUtils;
import org.eastwill.utils.SignEastwill;

public class Test1001 {

	public static void main(String[] args) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		// TODO Auto-generated method stub
		NetTest();
	}

	private static String NetTest() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String toutstr = "";
		String tkey = "2098D32C4D1399EC";
		String treq = "<REQ><HOS_ID>21030008</HOS_ID><IP>192.168.100.2</IP></REQ>";
		//String treq_encrypted = AesUtils.encrypt(tkey, treq);
		String treq_encrypted = "7rirzekAo8RJxeMwTuqY9QXBubyQ/bZk9yL4mqu3/2e7uGOtlRSC8Ix8Ileuqgr5f3IayaGZmc79QAi65TOjy2NHrTlPtaZj2TlwgYVYZI0=";
		String theader ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		String tfun_code = "1001";
		String tuser_id = "LN12320";
		String tsign_type = "MD5";
		
		if (tkey.length() != 16) {
			System.out.print("Key长度不是16位");
			return null;
		}
		Map<String, String> tdata = new HashMap<String, String>();
		tdata.put("FUN_CODE", tfun_code);
		tdata.put("USER_ID", tuser_id);
		tdata.put("REQ_ENCRYPTED", treq_encrypted);
		String treq_sign="";
		try {
			treq_sign = SignEastwill.generateSignature(tdata, tkey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = "FUN_CODE=1001&REQ_ENCRYPTED=7rirzekAo8RJxeMwTuqY9QXBubyQ/bZk9yL4mqu3/2e7uGOtlRSC8Ix8Ileuqgr5f3IayaGZmc79QAi65TOjy2NHrTlPtaZj2TlwgYVYZI0=&USER_ID=LN12320&KEY=2098D32C4D1399EC";
		treq_sign = DigestUtils.md5Hex(str);
		System.out.println("treq_sign="+treq_sign);
       /* toutstr = "<![CDATA["+theader+"<ROOT><FUN_CODE><![CDATA["+tfun_code+"]]></FUN_CODE>"
        		+"<USER_ID><![CDATA["+tuser_id+"]]></USER_ID>"
        		+"<SIGN_TYPE><![CDATA["+tsign_type+"]]></SIGN_TYPE>"
        		+"<SIGN><![CDATA["+treq_sign+"]]></SIGN>"
        		+"<REQ_ENCRYPTED><![CDATA["+treq_sign+"]]></REQ_ENCRYPTED>"
        		+"</ROOT>]]>"; */
        toutstr = "<![CDATA["+theader+"<ROOT><FUN_CODE>"+tfun_code+"</FUN_CODE>"
        		+"<USER_ID>"+tuser_id+"</USER_ID>"
        		+"<SIGN_TYPE>"+tsign_type+"</SIGN_TYPE>"
        		+"<SIGN>"+treq_sign+"</SIGN>"
        		+"<REQ_ENCRYPTED>"+treq_encrypted+"</REQ_ENCRYPTED>"
        		+"</ROOT>]]>";
       System.out.println("toutstr="+toutstr);
       // System.out.println("treg="+AesUtils.decrypt(tkey, treq_encrypted));
        System.out.println("treg="+AesUtils.decrypt(tkey,"W883Xdd2wHHFau65htsjGACiSAH7/bf3oyumSzlaSL5A0usNBcseBaFzPmJYocgXYw9ZCsw3CtC0Q6j6X9C+CQ=="));
		return toutstr;
	}
}
