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

public class Test2004 {

	public static void main(String[] args) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		// TODO Auto-generated method stub
		String toutstr = GetTimeRegInfoTest();
		String tres = "eCXV6TmFwyz8AhfLFkBYo9bCiPdeBRNh84RI11PqIqI5akZYFV11nXatZzoIcVoNyl8xzTnZr0phLdScu3tJ0L2TgZFnOT3OHnJ/FSXJpkBZl9h0/VqWJlJM2i94322GQElMlI2hE/pAfTLOHuJb1IZw3VSKgTLefXe5PmNHAkB/x+1EHlJZ9NmOxbd9t9itViVf2zFOzGdWj2/vLUaMqv26+oDuJOYxnoyI9gCzA0XYhO4XzwAvqXGJJUIhTiUnC9naUPzpbBPydY577kCrCJ77NmO/3I2Ez67ercCB0fLn4R6sgkZzIHb4uhLzpMqN9nINds9bz2/d2+HFwsMEgXHobXWqhS2Xs/v6UCc4clZpfsRZ3JCVqVTUpefRRtQhIwzyQc6a89vswyEZqwgN77A329tDPlnsdWKQncKYNSSjun3xJjosAd81vwM04PsQhvscmv8NsMcW1Cco+pjKnckHQwbVv4aFoyXPznNAK0Ddnrod+Ak0hYAJT5AG/dqb";
		testreturn(tres);
	}

	private static String GetTimeRegInfoTest() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String toutstr = "";
		String tkey = "2098D32C4D1399EC";
		String treq = "<REQ><HOS_ID>21030008</HOS_ID><DEPT_ID>0007</DEPT_ID><DOCTOR_ID>169</DOCTOR_ID>"+
		        "<START_DATE>2018-07-23</START_DATE>" + 
				"<END_DATE >2018-10-31</END_DATE>" + 
				"<TIME_FLAG>-1</TIME_FLAG></REQ>";
		String treq_encrypted = AesUtils.encrypt(tkey, treq);
		//String treq_encrypted = "7rirzekAo8RJxeMwTuqY9QXBubyQ/bZk9yL4mqu3/2e7uGOtlRSC8Ix8Ileuqgr5f3IayaGZmc79QAi65TOjy2NHrTlPtaZj2TlwgYVYZI0=";
		String theader ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		String tfun_code = "2004";
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
		String str = "FUN_CODE="+tfun_code+"&REQ_ENCRYPTED="+treq_encrypted+"&USER_ID=LN12320&KEY=2098D32C4D1399EC";
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
       // System.out.println("treg="+AesUtils.decrypt(tkey,"W883Xdd2wHHFau65htsjGGDEc2UPhV6V2sSf9kVo6DCcNQakVHnJ8eExjmfTUjQhYw9ZCsw3CtC0Q6j6X9C+CQ=="));
		return toutstr;
	}
	
	public static String testreturn(String tres) {
		String tkey = "2098D32C4D1399EC";
		//String tres = "eCXV6TmFwyz8AhfLFkBYo9bCiPdeBRNh84RI11PqIqI5akZYFV11nXatZzoIcVoNyl8xzTnZr0phLdScu3tJ0L2TgZFnOT3OHnJ/FSXJpkBZl9h0/VqWJlJM2i94322GQElMlI2hE/pAfTLOHuJb1IZw3VSKgTLefXe5PmNHAkB/x+1EHlJZ9NmOxbd9t9itViVf2zFOzGdWj2/vLUaMqv26+oDuJOYxnoyI9gCzA0XYhO4XzwAvqXGJJUIhTiUnC9naUPzpbBPydY577kCrCJ77NmO/3I2Ez67ercCB0fLn4R6sgkZzIHb4uhLzpMqN9nINds9bz2/d2+HFwsMEgXHobXWqhS2Xs/v6UCc4clZpfsRZ3JCVqVTUpefRRtQhIwzyQc6a89vswyEZqwgN77A329tDPlnsdWKQncKYNSSjun3xJjosAd81vwM04PsQhvscmv8NsMcW1Cco+pjKnckHQwbVv4aFoyXPznNAK0Ddnrod+Ak0hYAJT5AG/dqb";
 

		String toutstr = AesUtils.decrypt(tkey, tres);
		System.out.println("toutstr="+toutstr);
		return toutstr;

	}
}
