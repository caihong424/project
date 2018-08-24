package org.eastwill.service;

import java.net.URLDecoder;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eastwill.message.ErrorMsg;
import org.eastwill.model.DocPlanReqModel;
import org.eastwill.model.HosparamOut;
import org.eastwill.model.NetTestRequstModel;
import org.eastwill.utils.AesUtils;
import org.eastwill.utils.Config;
import org.eastwill.utils.HttpEastwill;
import org.eastwill.utils.Sign;
import org.eastwill.utils.SignEastwill;
import org.eastwill.utils.XmlUtils;

import com.alibaba.fastjson.JSON;

public class GetRegInfoService {
	private static final Log log=LogFactory.getLog(GetDeptInfoService.class);
	
	public static String getRegInfo(String strXml, String res_xml) throws Exception {
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
		//排班信息查询
		DocPlanReqModel objreq = XmlUtils.toBean(AesUtils.decrypt(Config.getProper("key"),obj.getReqEncrypted()),DocPlanReqModel.class);
		String turl = Config.getProper("url")+"/GetRegInfo";
		//入参:科室ID|医生ID(为-1时查询科室ID下所有医生排班)|排班开始日期，格式：YYYY-MM-DD|排班结束日期，格式：YYYY-MM-DD
		String tdepid = objreq.getDeptId();
		if(tdepid==null)tdepid = "";
		String tdocid = objreq.getDoctorId();
		if(tdocid==null)tdocid = "";
		String tstart_date = objreq.getStartDate();
		if(tstart_date==null)tstart_date = "";
		String tend_date = objreq.getEndDate();
		if(tend_date==null)tend_date = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");		
		try {
			dateFormat.parse(tstart_date);
			dateFormat.parse(tend_date);
		}catch(Exception e) {
			log.error("日期格式输入错误"+e);
			return MessageFormat.format(res_xml, ErrorMsg.DATE_NOT_RIGHT.getResCode(), ErrorMsg.DATE_NOT_RIGHT.getResMsg(), "MD5", res_sign, res_encrypted);
		}
		String tinstr = Config.getProper("hosId")+"|"+tdepid+"|"+tdocid+"|"+tstart_date+"|"+tend_date;
		log.info("传入排班信息"+tinstr);
		String toutstr = "" ;
		try {
			toutstr = HttpEastwill.post_hisurl(turl, tinstr);			
			HosparamOut thout = new HosparamOut();
			thout = JSON.parseObject(toutstr, HosparamOut.class);
			String tresultCode = thout.getResultCode();
			String toutdata = thout.getOutdata();
			//log.info("1:"+toutdata);
			toutdata = URLDecoder.decode(toutdata, "utf-8");
			//log.info("排班信息2:"+toutdata);
			toutstr = toutdata;
			String terrMsg = thout.getErrorMsg();
			if(tresultCode.equals("0")) {
				//log.info("获取his排班信息成功"+toutdata);
			}else {
				log.info("获取his排班信息失败"+terrMsg);
				return MessageFormat.format(res_xml, "-3", "获取his排班信息失败"+terrMsg, "MD5", res_sign, res_encrypted);
			}
			
			
		}catch(Exception e) {
			log.error("1:获取his科室信息失败"+e);
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
