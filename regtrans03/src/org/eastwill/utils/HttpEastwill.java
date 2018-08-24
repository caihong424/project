package org.eastwill.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.eastwill.model.HosparamIn;

import com.alibaba.fastjson.JSON;

public class HttpEastwill {
	public static String post_hisurl(String thisurl,String tin) throws ClientProtocolException, IOException {		
		//String thosurl = env.getProperty("hosurl");
		//String turl = thosurl+"/"+thisurl;
		HosparamIn thosparamIn = new HosparamIn();
		thosparamIn.setIndata(tin);
				
		String tsendstr = JSON.toJSONString(thosparamIn);
		String tout = "";
		//log.info("post_hisurl tsendstr="+tsendstr +" turl="+turl);		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {	        		        	
			HttpPost httppost = new HttpPost(thisurl);
			httppost.setHeader("Accept","aplication/json");  	             
			httppost.addHeader("Content-Type","application/json;charset=UTF-8");  
			StringEntity s = new StringEntity(tsendstr,"UTF-8");
			s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));   
			s.setContentType("application/json");//发送json数据需要设置contentType
			httppost.setEntity(s);
			InputStream inputStream = null;
			        
			CloseableHttpResponse response0 = httpclient.execute(httppost);
			try {
				HttpEntity resEntity = response0.getEntity();
			 	if (resEntity != null) {
			 		inputStream = resEntity.getContent();
			    	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			      	String line = "";
			       	while ((line = bufferedReader.readLine()) != null) {
			       		tout = tout + line;
			        }
			       	//log.info("post_hisurl  tout = "+tout);
			    }	          
			} finally {
				response0.close();
			}
		} finally {
			httpclient.close();
		}	
		return tout;
	}	
	
	public static String getIpAddr(HttpServletRequest request) {  
        String ip = request.getHeader("x-forwarded-for");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        String ips[] = ip.split(",");
        if (ips.length>0) {
        	ip = ips[0];
        }
        return ip;  
    }			
}
