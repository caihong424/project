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
import org.eastwill.model.NetTestRequstModel;
import org.eastwill.model.PayListReqModel;
import org.eastwill.utils.AesUtils;
import org.eastwill.utils.Config;
import org.eastwill.utils.HttpEastwill;
import org.eastwill.utils.Sign;
import org.eastwill.utils.SignEastwill;
import org.eastwill.utils.XmlUtils;

import com.alibaba.fastjson.JSON;

public class GetPayListService {
	private static final Log log=LogFactory.getLog(GetPayListService.class);
	
	public static String getPayList(String strXml, String res_xml) throws Exception {
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
		//缴费记录查询接口
		PayListReqModel objreq = XmlUtils.toBean(AesUtils.decrypt(Config.getProper("key"),obj.getReqEncrypted()),PayListReqModel.class);
		String turl = Config.getProper("url")+"/GetPayList";
		//入参HOS_ID 医院ID
		//HOSP_PATIENT_ID 用户院内ID
		//IDCARD_TYPE 用户证件类型，详情见 “证件类型”
		//IDCARD_NO 用户证件号码
		//CARD_TYPE 用户卡类型，详见 “卡类型”
		//CARD_NO 用户卡号
		//PATIENT_NAME 用户姓名
		//PATIENT_SEX 用户性别，详见 “性别”
		//PATIENT_AGE 用户年龄
		//QUERY_TYPE 查询状态类型：0-所有 1-未缴费 2-已缴费 3-已退款
		//BEGIN_DATE 起始日期，格式：YYYY-MM-DD
		//END_DATE 结束日期，格式：YYYY-MM-DD
		String thosId = objreq.getHosId();
		String thospPatientId = objreq.getHospPatientId();
		String tidcardType = objreq.getIdcardType();
		String tidcardNo = objreq.getIdcardNo();
		String tcardType = objreq.getCardType();
		String tcardNo = objreq.getCardNo();
		String tpatientName = objreq.getPatientName();
		String tpatientSex = objreq.getPatientSex();
		String tpatientAge = objreq.getPatientAge();
		String tqueryType = objreq.getQueryType();
		String tbeginDate = objreq.getBeginDate();
		String tendDate = objreq.getEndDate();
		
		String tinstr = thosId+"|"+thospPatientId+"|"+tidcardType+"|"+tidcardNo+"|"+tcardType+"|"+tcardNo+"|"+tpatientName+"|"+tpatientSex+"|"+tpatientAge+"|"+tqueryType+"|"+tbeginDate+"|"+tendDate;
		log.info("缴费记录查询信息"+tinstr);
		
		try {
			toutstr = HttpEastwill.post_hisurl(turl, tinstr);			
			HosparamOut thout = new HosparamOut();
			thout = JSON.parseObject(toutstr, HosparamOut.class);
			String tresultCode = thout.getResultCode();
			String toutdata = thout.getOutdata();
			//log.info("1:"+toutdata);
			toutdata = URLDecoder.decode(toutdata, "utf-8");
			log.info("缴费记录查询信息2:"+toutdata);
			String terrMsg = thout.getErrorMsg();
			if(tresultCode.equals("0")) {
				log.info("获取his缴费记录查询信息成功"+toutdata);
			}else {
				log.info("获取his缴费记录查询失败"+terrMsg);
				return MessageFormat.format(res_xml, "-3", "获取his缴费记录查询信息失败"+terrMsg, "MD5", res_sign, res_encrypted);
			}
						
			toutstr = toutdata;
		}catch(Exception e) {
			log.error("3:获取his缴费记录查询信息失败"+e);
			return MessageFormat.format(res_xml, "-3", "获取his缴费记录查询信息失败"+e.getMessage(), "MD5", res_sign, res_encrypted);
		}
		log.info("返回缴费记录查询信息:"+toutstr);

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
