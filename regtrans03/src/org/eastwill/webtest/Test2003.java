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

public class Test2003 {

	public static void main(String[] args) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		// TODO Auto-generated method stub
		//GetRegInfoTest();
		
		testreturn();
	}

	private static String GetRegInfoTest() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String toutstr = "";
		String tkey = "2098D32C4D1399EC";
		String treq = "<REQ><HOS_ID>21030008</HOS_ID><DEPT_ID>0007</DEPT_ID><DOCTOR_ID>-1</DOCTOR_ID>"+
		    "<START_DATE>2018-07-23</START_DATE>" + 
				"<END_DATE >2018-10-31</END_DATE>" + 
				"</REQ>";
		String treq_encrypted = AesUtils.encrypt(tkey, treq);
		//String treq_encrypted = "7rirzekAo8RJxeMwTuqY9QXBubyQ/bZk9yL4mqu3/2e7uGOtlRSC8Ix8Ileuqgr5f3IayaGZmc79QAi65TOjy2NHrTlPtaZj2TlwgYVYZI0=";
		String theader ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		String tfun_code = "2003";
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
		String tres = "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";
		//String tres = "PaUcRnjtPMnxeJwHHJ6fk3DKUhPcAZOAqZHRlQMj4uQn1fsk4nXOX/yFOxW1zgiH5uJOTRQBJ2jUufn/Hafaq6TnhRlyNre3/W1GInkoT9icfv+K1AsPeCk+nER9RIafTx+cpqxiRGlyz69Qp+OoRGVaCb2fHd59bdJKmWH0SKW+VkMTOvCTYdKaDO2kTHx+1jADiHFgKgd5C5HsVpVuhk9Dfq2C1Mg/gH8dYiGg5XmWSMdcjo9rJA8W5AY37h5NxxR6m7G6MxW6LxJeRDFd0GKiGeRv4QdSsvXeuB1KIvn8B4Q3DQdhi6DmVuba6/gEp3FGkcrKg714F9KlbcWdwBz0KhtyEj/C1m4FFTWDPukkY3vX8pxJ4JT5GKcfYmJ5hszrtd/vd7fVOjEf5iAmVNp805NPHHo8OmeR8Ed6tPHyrN/wLwIhry+p61HjSlIrhJDkGClayfX+oxb1NA/eeJeslrHz0Rbuzz8ZIgG/dIu8ZmNOGaHNv7vAO0oQZX7gd9XMcVAMUvXs1SfVTosCwSlMqIAybK7Eg5l7PeqUk15kqRHBNz4szdxy5z2ytB3l6v8uahLNCemdRwSLEPsw79SUPMOxBW2cctHMBQOpmX2PDBf1SZM4dya95yqpskhe1KEwlIl1oYsZJD2+z3CFfoXZC34+8V+Bk2mnBtVfEl+AzA0t9CfI8Nsqq/ypr2AC0m3dYdVuqw4qn2mc4C+evZ1YTpOQnqKfN56EqSHbUZQTvm/V17jfIYQgUaNgC4cu12MCjQhDzGbol4e4b6IYXQa4QJohuhjF0sKquGt2DX1DoUDIQCwjfUbC/8/0Mpsq/PKK3NRJMJW0Xag6AkUOcn1d5kTyLSTySZPXDBchewbILeFhd5MdseFa7/FkBB8p0Wc8mt4BFMXkz21J25NvQHj4OuLBg9mo28h5KaA4PSdY9oT0h9mFZqj3dTse7zpCS+7DcYO50yAb0z1e+ngd7zFg/szjRCfAfxSRgi9fN3N98lBD/L7zDxOXiV8IVdk1C2xKiZ8X14VuqXc/e7e+qs27X1ye9WZtR4aHAe3jzItWfoWVphGPpVLGJsZfBQ3HyW7bWHJMCZPQpUDjZOiwpOuuj7hJ+dXahgMxocQQ9QyOOkFEXjQL1aHEPgQToizGpMMl2bidxLGWolhB9O8SZWfN7rsTRs5mwk/1xRMeQDhLpmDhhMr2UiWm7qsTjnSDoBuE/8444dfYo9nPNwNG22UX2GDhjX7867hcqSkM+0EKu+SzPcowy/dX/8eVNNhMyKnD9JjQ9cUjseWL8Do883UJolNVA+k1FvY7jIoi1i/vXwaQzRLnZhQeYCb89vYnQ6ljU1BasGmzsCY82pBJwT0t/zIli7vdOqL22rAPUUs9n2EFwSsuLQ77SMzlAH60LWsqSQe3hhRKu1+t+IcWMDdP4gp+He9cLLpCyVhMKI3APYdWXoYHqEZB3aTGPxy4WbIKV9bR4WdxMcNGz8Uh8HMUftqlfyEYSPPvOJIES+m76cnIUQptEBTv5dD23HUenk47lQsbwwVxyU1+mRbp8m0C//wwkUJWz58tk+PjZ4N9WDTeFIEp1rUEmhqUrcS8LvfmC8JuP2PXfGSUTrS9z+jiB7rq8WQaYMSGHCi6VY+l76OxdEIP8tEq8n0k5y+kCJVD9euwT5tDYLQtrxo8Bvi4LwYrG5iAv/YaXkVnYo0=" ; 

		String toutstr = AesUtils.decrypt(tkey, tres);
		System.out.println("toutstr="+toutstr);
		return toutstr;

	}
}
