package org.eastwill.service;

import java.net.URLDecoder;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eastwill.message.ErrorMsg;
import org.eastwill.model.DocInfo;
import org.eastwill.model.DocInfoReqModel;
import org.eastwill.model.HosparamOut;
import org.eastwill.model.NetTestRequstModel;
import org.eastwill.utils.AesUtils;
import org.eastwill.utils.Config;
import org.eastwill.utils.HttpEastwill;
import org.eastwill.utils.Sign;
import org.eastwill.utils.SignEastwill;
import org.eastwill.utils.XmlUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class GetDoctorInfoService {
	private static final Log log=LogFactory.getLog(GetDoctorInfoService.class);
	
	public static String getDoctorInfo(String strXml, String res_xml) throws Exception {
		String res_encrypted = "";
		String res_sign = "";
		NetTestRequstModel obj = XmlUtils.toBean(strXml, NetTestRequstModel.class);
		if (!StringUtils.equalsIgnoreCase(obj.getUserId(), Config.getProper("userId"))) {
			res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			res_sign = Sign.GetResponseSign(ErrorMsg.USER_NOT_EXIST.getResCode(), ErrorMsg.USER_NOT_EXIST.getResMsg(), res_encrypted, Config.getProper("key"));
			log.info("1="+ErrorMsg.CLIENT_INPUT_ERROR.getResCode());
			return MessageFormat.format(res_xml, ErrorMsg.USER_NOT_EXIST.getResCode(), ErrorMsg.USER_NOT_EXIST.getResMsg(), "MD5", res_sign, res_encrypted);
		}
	
		Map<String, String> tdata = new HashMap<String, String>();
		tdata.put("FUN_CODE", obj.getFunCode());
		tdata.put("USER_ID", obj.getUserId());
		tdata.put("REQ_ENCRYPTED", obj.getReqEncrypted());
		String sign = SignEastwill.generateSignature(tdata, Config.getProper("key"));
			
		if (!StringUtils.equalsIgnoreCase(obj.getSignType(), "MD5") || !StringUtils.equalsIgnoreCase(obj.getSign(), sign)) {
			res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			res_sign = Sign.GetResponseSign(ErrorMsg.SIGN_NOT_RIGHT.getResCode(), ErrorMsg.SIGN_NOT_RIGHT.getResMsg(), res_encrypted, Config.getProper("key"));
			log.info("2="+ErrorMsg.CLIENT_INPUT_ERROR.getResCode());
			return MessageFormat.format(res_xml, ErrorMsg.SIGN_NOT_RIGHT.getResCode(), ErrorMsg.SIGN_NOT_RIGHT.getResMsg(), "MD5", res_sign, res_encrypted);
		}
		//获取医生信息 入参:科室编码|医生编码
		DocInfoReqModel objreq = XmlUtils.toBean(AesUtils.decrypt(Config.getProper("key"),obj.getReqEncrypted()),DocInfoReqModel.class);
		String turl = Config.getProper("url")+"/GetDoctorInfo";
		String tinstr = objreq.getDeptId()+"|"+objreq.getDoctorId();
		log.info("传入医生信息"+tinstr);
		String toutstr = "" ;
		try {
			toutstr = HttpEastwill.post_hisurl(turl, tinstr);			
			HosparamOut thout = new HosparamOut();
			thout = JSON.parseObject(toutstr, HosparamOut.class);
			String tresultCode = thout.getResultCode();
			String toutdata = thout.getOutdata();
			//log.info("医生信息1:"+toutdata);
			toutdata = URLDecoder.decode(toutdata, "utf-8");
			log.info("医生信息2:"+toutdata);
			String terrMsg = thout.getErrorMsg();
			if(tresultCode.equals("0")) {
				log.info("获取his科室信息成功"+toutdata);
			}else {
				log.info("获取his科室信息失败"+terrMsg);
				return MessageFormat.format(res_xml, "-3", "获取his科室信息失败"+terrMsg, "MD5", res_sign, res_encrypted);
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			if(terrMsg.equals("0")) {
				//返回的是list
				List<DocInfo> tl_doc = JSONArray.parseArray(toutdata, DocInfo.class);
				toutstr = "<RES><HOS_ID>"+Config.getProper("hosId")+"</HOS_ID>";
				String tdocs = "";
				for(DocInfo td:tl_doc) {
					String tdoctorId = td.getDoctorId();
					if(tdoctorId==null)tdoctorId ="";
					String tdeptId = td.getDeptId();
					if(tdeptId==null)tdeptId ="";
					String tdocName = td.getDocName();
					if(tdocName==null)tdocName ="";
					String tidcard = td.getIdcard(); 
					if(tidcard==null)tidcard ="";
					String tdocDesc = td.getDocDesc();
					if(tdocDesc==null)tdocDesc ="";
					String tspecial = td.getSpecial();
					if(tspecial==null)tspecial ="";
					String tjobTitle = td.getJobTitle();
					if(tjobTitle==null)tjobTitle ="";
					Long tregFee = td.getRegFee();
					if(tregFee==null)tregFee =0L;
					Integer tstatus = td.getStatus();
					if(tstatus==null)tstatus =1;
					Integer tsex = td.getSex();
					if(tsex==null)tsex =1;
					Date tbirthday = td.getBirthday();
					String tbirthdays = "";
					try {
						tbirthdays = dateFormat.format(tbirthday);
					}catch(Exception e) {
						tbirthdays = "";
					}
					String tmobile = td.getMobile();
					if(tmobile==null)tmobile ="";
					String ttel = td.getTel();
					if(ttel==null)ttel ="";
					tdocs = tdocs+"<DOCTOR_LIST><DEPT_ID>"+tdeptId+"</DEPT_ID>"+
					  "<DOCTOR_ID>"+tdoctorId+"</DOCTOR_ID>" + 
					  "<NAME>"+tdocName+"</NAME>" + 
					  "<IDCARD>"+tidcard+"</IDCARD>" + 
					  "<DESC>"+tdocDesc+"</DESC>" + 
					  "<SPECIAL>"+tspecial+"</SPECIAL>" + 
					  "<JOB_TITLE>"+tjobTitle+"</JOB_TITLE>" + 
					  "<REG_FEE>"+tregFee+"</REG_FEE>" + 
					  "<STATUS>"+tstatus+"</STATUS>" + 
					  "<BIRTHDAY>"+tbirthdays+"</BIRTHDAY>" + 
					  "<MOBILE>"+tmobile+"</MOBILE>" + 
					  "<TEL>"+ttel+"</TEL></DOCTOR_LIST>";
				}
				toutstr = toutstr + tdocs + "</RES>";
			}else if(terrMsg.equals("1")) {
				//返回的是单个医生信息
				DocInfo td = JSONObject.parseObject(toutdata, DocInfo.class);
				toutstr = "<RES><HOS_ID>"+Config.getProper("hosId")+"</HOS_ID>";
				String tdocs = "";
				String tdoctorId = td.getDoctorId();
				if(tdoctorId==null)tdoctorId ="";
				String tdeptId = td.getDeptId();
				if(tdeptId==null)tdeptId ="";
				String tdocName = td.getDocName();
				if(tdocName==null)tdocName ="";
				String tidcard = td.getIdcard(); 
				if(tidcard==null)tidcard ="";
				String tdocDesc = td.getDocDesc();
				if(tdocDesc==null)tdocDesc ="";
				String tspecial = td.getSpecial();
				if(tspecial==null)tspecial ="";
				String tjobTitle = td.getJobTitle();
				if(tjobTitle==null)tjobTitle ="";
				Long tregFee = td.getRegFee();
				if(tregFee==null)tregFee =0L;
				Integer tstatus = td.getStatus();
				if(tstatus==null)tstatus =1;
				Integer tsex = td.getSex();
				if(tsex==null)tsex =1;
				Date tbirthday = td.getBirthday();
				String tbirthdays = "";
				try {
					tbirthdays = dateFormat.format(tbirthday);
				}catch(Exception e) {
					tbirthdays = "";
				}
				String tmobile = td.getMobile();
				if(tmobile==null)tmobile ="";
				String ttel = td.getTel();
				if(ttel==null)ttel ="";
				tdocs = tdocs+"<DOCTOR_LIST><DEPT_ID>"+tdeptId+"</DEPT_ID>"+
				  "<DOCTOR_ID>"+tdoctorId+"</DOCTOR_ID>" + 
				  "<NAME>"+tdocName+"</NAME>" + 
				  "<IDCARD>"+tidcard+"</IDCARD>" + 
				  "<DESC>"+tdocDesc+"</DESC>" + 
				  "<SPECIAL>"+tspecial+"</SPECIAL>" + 
				  "<JOB_TITLE>"+tjobTitle+"</JOB_TITLE>" + 
				  "<REG_FEE>"+tregFee+"</REG_FEE>" + 
				  "<STATUS>"+tstatus+"</STATUS>" + 
				  "<BIRTHDAY>"+tbirthdays+"</BIRTHDAY>" + 
				  "<MOBILE>"+tmobile+"</MOBILE>" + 
				  "<TEL>"+ttel+"</TEL></DOCTOR_LIST>";
				toutstr = toutstr + tdocs + "</RES>";
			}
			
		}catch(Exception e) {
			log.error("3:获取his医生信息失败"+e);
			return MessageFormat.format(res_xml, "-3", "获取his医生信息失败"+e.getMessage(), "MD5", res_sign, res_encrypted);
		}
		log.info("返回医生信息:"+toutstr);
		// 密文
		res_encrypted = AesUtils.encrypt(Config.getProper("key"), toutstr);
		//返回签名
		tdata = new HashMap<String, String>();
		tdata.put("RETURN_CODE", String.valueOf(ErrorMsg.DEAL_SUCCESS.getResCode()));
		tdata.put("RETURN_MSG",ErrorMsg.DEAL_SUCCESS.getResMsg());
		tdata.put("RES_ENCRYPTED",res_encrypted);
		res_sign = SignEastwill.generateSignature(tdata, Config.getProper("key"));
		//log.info("bf3 reg_sign="+res_sign);
		return MessageFormat.format(res_xml, ErrorMsg.DEAL_SUCCESS.getResCode(), ErrorMsg.DEAL_SUCCESS.getResMsg(), "MD5", res_sign, res_encrypted);
	}
}