package org.eastwill.service;

import java.net.URLDecoder;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eastwill.message.ErrorMsg;
import org.eastwill.model.DocPlanReqModel;
import org.eastwill.model.DocPlanTime;
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

public class GetTimeRegInfoService {
	private static final Log log=LogFactory.getLog(GetTimeRegInfoService.class);
	
	public static String getTimeRegInfo(String strXml, String res_xml) throws Exception {
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
		String toutstr = "" ;
		//排班分时查询
		DocPlanReqModel objreq = XmlUtils.toBean(AesUtils.decrypt(Config.getProper("key"),obj.getReqEncrypted()),DocPlanReqModel.class);
		String turl = Config.getProper("url")+"/GetTimeRegInfo";
		String tdepid = objreq.getDeptId();
		if(tdepid==null)tdepid = "";
		String tdocid = objreq.getDoctorId();
		if(tdocid==null)tdocid = "";
		String tstart_date = objreq.getStartDate();
		if(tstart_date==null)tstart_date = "";
		String tend_date = objreq.getEndDate();
		if(tend_date==null)tend_date = "";
		String ttime_flag = objreq.getTimeFlag();
		if(ttime_flag==null)ttime_flag = "-1";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");		
		try {
			dateFormat.parse(tstart_date);
			dateFormat.parse(tend_date);
		}catch(Exception e) {
			log.error("日期格式输入错误"+e);
			return MessageFormat.format(res_xml, ErrorMsg.DATE_NOT_RIGHT.getResCode(), ErrorMsg.DATE_NOT_RIGHT.getResMsg(), "MD5", res_sign, res_encrypted);
		}
		String tinstr = Config.getProper("hosId")+"|"+tdepid+"|"+tdocid+"|"+tstart_date+"|"+tend_date+"|"+ttime_flag;
		log.info("传入排班分时查询"+tinstr);

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
				log.info("获取his排班分时失败"+terrMsg);
				return MessageFormat.format(res_xml, "-3", "获取his排班分时失败"+terrMsg, "MD5", res_sign, res_encrypted);
			}
			List<DocPlanTime> tl_DocPlanTime = JSONArray.parseArray(toutdata, DocPlanTime.class);
			toutstr = "<RES>";
			String tdepts = "";
			for(DocPlanTime td:tl_DocPlanTime) {
				Integer ttimeFlag = td.getTimeFlag();
				String tbeginTime = td.getBeginTime();
				if(tbeginTime==null)tbeginTime ="";
				String tendTime = td.getEndTime();
				if(tendTime==null)tendTime ="";
				Integer ttotal = td.getTotal();
				Integer toverCount = td.getOverCount();
				String tregId = td.getRegId();
				if(tregId==null)tregId ="";
				tdepts = tdepts+"<TIME_REG_LIST><TIME_FLAG>"+ttimeFlag+"</TIME_FLAG>"+
				    "<BEGIN_TIME>"+tbeginTime+"</BEGIN_TIME>"+
				    "<END_TIME>"+tendTime+"</END_TIME>"+
				    "<TOTAL>"+ttotal+"</TOTAL>"+
				    "<OVER_COUNT>"+toverCount+"</OVER_COUNT>"+
				    "<REG_ID>"+tregId+"</REG_ID></TIME_REG_LIST>";

			}
			toutstr = toutstr + tdepts + "</RES>";
		}catch(Exception e) {
			log.error("3:获取his排班分时信息失败"+e);
			return MessageFormat.format(res_xml, "-3", "获取his排班分时信息失败"+e.getMessage(), "MD5", res_sign, res_encrypted);
		}
		log.info("返回排班分时信息:"+toutstr);
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
