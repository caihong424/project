package org.eastwill.service;

import java.net.URLDecoder;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eastwill.message.ErrorMsg;
import org.eastwill.model.HosparamOut;
import org.eastwill.model.LineUpListReqModel;
import org.eastwill.model.NetTestRequstModel;
import org.eastwill.utils.AesUtils;
import org.eastwill.utils.Config;
import org.eastwill.utils.HttpEastwill;
import org.eastwill.utils.Sign;
import org.eastwill.utils.SignEastwill;
import org.eastwill.utils.XmlUtils;

import com.alibaba.fastjson.JSON;

public class GetUserLineUpListService {
	private static final Log log=LogFactory.getLog(GetUserLineUpListService.class);
	
	public static String getUserLineUpList(String strXml, String res_xml) throws Exception {
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
		//排队列表查询接口
		LineUpListReqModel objreq = XmlUtils.toBean(AesUtils.decrypt(Config.getProper("key"),obj.getReqEncrypted()),LineUpListReqModel.class);
		String turl = Config.getProper("url")+"/GetUserLineUpList";
		String thosId = objreq.getHosId();
		String thospPatientId = objreq.getHospPatientId();
		String tpatientIdcardType = objreq.getPatientIdcardType();
		String tpatientIdcardNo = objreq.getPatientCardNo();
		String tpatientCardType = objreq.getPatientCardType();
		String tpatientCardNo = objreq.getPatientCardNo();
		String tpatientName = objreq.getPatientName();
		String tpatientSex = objreq.getPatientSex();
		String tpatientAge = objreq.getPatientAge();
		String tinstr = thosId+"|"+thospPatientId+"|"+tpatientIdcardType+"|"+tpatientIdcardNo+"|"+tpatientCardType+"|"+tpatientCardNo+"|"+tpatientName+"|"+tpatientSex+"|"+ tpatientAge;
		log.info("传入排队列表查询接口"+tinstr);
		
		try {
			toutstr = HttpEastwill.post_hisurl(turl, tinstr);			
			HosparamOut thout = new HosparamOut();
			thout = JSON.parseObject(toutstr, HosparamOut.class);
			String tresultCode = thout.getResultCode();
			String toutdata = thout.getOutdata();
			//log.info("1:"+toutdata);
			toutdata = URLDecoder.decode(toutdata, "utf-8");
			log.info("排队列表查询接口2:"+toutdata);
			String terrMsg = thout.getErrorMsg();
			if(tresultCode.equals("0")) {
				log.info("获取his排队列表查询接口成功"+toutdata);
			}else {
				log.info("获取his排队列表查询接口失败"+terrMsg);
				return MessageFormat.format(res_xml, "-3", "获取his排队列表查询接口失败"+terrMsg, "MD5", res_sign, res_encrypted);
			}
		
			toutstr = toutdata;
		}catch(Exception e) {
			log.error("3:获取his排队列表查询接口失败"+e);
			return MessageFormat.format(res_xml, "-3", "获取his排队列表查询接口失败"+e.getMessage(), "MD5", res_sign, res_encrypted);
		}
		log.info("返回排队列表查询接口信息:"+toutstr);

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
