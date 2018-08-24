package org.eastwill.service;

import java.net.URLDecoder;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eastwill.message.ErrorMsg;
import org.eastwill.model.DeptInfo;
import org.eastwill.model.DeptInfoReqModel;
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

public class GetDeptInfoService {
	private static final Log log=LogFactory.getLog(GetDeptInfoService.class);
	
	public static String getDeptInfo(String strXml, String res_xml) throws Exception {
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
		//获取科室信息
		DeptInfoReqModel objreq = XmlUtils.toBean(AesUtils.decrypt(Config.getProper("key"),obj.getReqEncrypted()),DeptInfoReqModel.class);
		String turl = Config.getProper("url")+"/GetDeptInfo";
		String tinstr = objreq.getDeptId();
		log.info("传入科室信息"+tinstr);
		String toutstr = "" ;
		try {
			toutstr = HttpEastwill.post_hisurl(turl, tinstr);			
			HosparamOut thout = new HosparamOut();
			thout = JSON.parseObject(toutstr, HosparamOut.class);
			String tresultCode = thout.getResultCode();
			String toutdata = thout.getOutdata();
			//log.info("1:"+toutdata);
			toutdata = URLDecoder.decode(toutdata, "utf-8");
			log.info("科室信息2:"+toutdata);
			String terrMsg = thout.getErrorMsg();
			if(tresultCode.equals("0")) {
				log.info("获取his科室信息成功"+toutdata);
			}else {
				log.info("获取his科室信息失败"+terrMsg);
				return MessageFormat.format(res_xml, "-3", "获取his科室信息失败"+terrMsg, "MD5", res_sign, res_encrypted);
			}
			
			List<DeptInfo> tl_dept = JSONArray.parseArray(toutdata, DeptInfo.class);
			toutstr = "<RES><HOS_ID>"+Config.getProper("hosId")+"</HOS_ID>";
			String tdepts = "";
			for(DeptInfo td:tl_dept) {
				String tdeptId = td.getDeptId();
				if(tdeptId==null)tdeptId ="";
				String tdeptName = td.getDeptName();
				if(tdeptName==null)tdeptName ="";
				String tparentId = td.getParentId();
				if(tparentId==null)tparentId ="";
				String tdeptDesc = td.getDeptDesc();
				if(tdeptDesc==null)tdeptDesc ="";
				String texpertise = td.getExpertise();
				if(texpertise==null)texpertise ="";
				Integer tdeptLevel = td.getDeptLevel();
				if(tdeptLevel==null)tdeptLevel =0;
				String taddress = td.getAddress();
				if(taddress==null)taddress ="";
				Integer tstatus = td.getStatus();
				if(tstatus==null)tstatus =0;
				tdepts = tdepts+"<DEPT_LIST><DEPT_ID>"+tdeptId+"</DEPT_ID>"+
				  "<DEPT_NAME>"+tdeptName+"</DEPT_NAME>"+
				  "<PARENT_ID>"+tparentId+"</PARENT_ID>"+
				  "<DESC>"+tdeptDesc+"</DESC>"+
				  "<LEVEL>"+tdeptLevel+"</LEVEL>"+
				  "<ADDRESS>"+taddress+"</ADDRESS>"+
				  "<EXPERTISE>"+texpertise+"</EXPERTISE>"+
				  "<STATUS>"+tstatus+"</STATUS></DEPT_LIST>";
			}
			toutstr = toutstr + tdepts + "</RES>";
		}catch(Exception e) {
			log.error("3:获取his科室信息失败"+e);
			return MessageFormat.format(res_xml, "-3", "获取his科室信息失败"+e.getMessage(), "MD5", res_sign, res_encrypted);
		}
		log.info("返回科室信息:"+toutstr);
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
