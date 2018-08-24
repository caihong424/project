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

public class Test2002 {

	public static void main(String[] args) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		// TODO Auto-generated method stub
		GetDocInfoTest();
		
		//testreturn();
	}

	private static String GetDocInfoTest() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String toutstr = "";
		String tkey = "2098D32C4D1399EC";
		String treq = "<REQ><HOS_ID>21030008</HOS_ID><DEPT_ID>-1</DEPT_ID><DOCTOR_ID>-1</DOCTOR_ID></REQ>";
		String treq_encrypted = AesUtils.encrypt(tkey, treq);
		//String treq_encrypted = "7rirzekAo8RJxeMwTuqY9QXBubyQ/bZk9yL4mqu3/2e7uGOtlRSC8Ix8Ileuqgr5f3IayaGZmc79QAi65TOjy2NHrTlPtaZj2TlwgYVYZI0=";
		String theader ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		String tfun_code = "2002";
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
	
	public static String testreturn() {
		String tkey = "2098D32C4D1399EC";
		String tres = "PaUcRnjtPMnxeJwHHJ6fk3DKUhPcAZOAqZHRlQMj4uQqhpqfK8ZEUwCE1Hdyqif5d0fCwnKUYy6gvhHRhCn5pj73Di6yVVxy7P8pblX7Oh1AFJIp6mBYZx8pW+eVYxklJnNppfuj67l7kKbPTIrRTqJTIU0C/t6xFj1xHgNtUm4VkDqhTZrDTB6JNQeN4lKjz8wBFEXmX5zY5ajVF3NPP+9UlkPCNsdpVwrxDz7Wu3jiCOIA1nuHueTQx1PxtXKI40ErRWAu3mU34adZcn/9zDJ1G8w41Wc0SYW8RQToqFyPq/qqMNb4mvBCNgiuGOAU+ZqQ7Mxss8ckglXNgs0pKxvRRyiS7aAtOga+UnXp86OG4bGbLZQF8pTVsRhmPe3FdmRlqqGFGUPOvhDjIFLUXfAKka3NsByumpM4V+Fk8MMMZbn2mpEPdHy6MgXatngz+LgvBisbmIC/9hpeRWdijQ==";
		String toutstr = AesUtils.decrypt(tkey, tres);
		System.out.println("toutstr="+toutstr);
		return toutstr;

	}
}
