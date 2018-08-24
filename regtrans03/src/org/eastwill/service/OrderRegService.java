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
import org.eastwill.model.RegOrderReqModel;
import org.eastwill.utils.AesUtils;
import org.eastwill.utils.Config;
import org.eastwill.utils.HttpEastwill;
import org.eastwill.utils.Sign;
import org.eastwill.utils.SignEastwill;
import org.eastwill.utils.XmlUtils;

import com.alibaba.fastjson.JSON;

public class OrderRegService {
	private static final Log log=LogFactory.getLog(OrderRegService.class);
	
	public static String orderReg(String strXml, String res_xml) throws Exception {
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
		//预挂号接口
		RegOrderReqModel objreq = XmlUtils.toBean(AesUtils.decrypt(Config.getProper("key"),obj.getReqEncrypted()),RegOrderReqModel.class);
		String turl = Config.getProper("url")+"/OrderReg";
		String torderId = objreq.getOrderId();
		if(torderId==null)torderId="";
		String thospPatientId = objreq.getHospPatientId();
		if(thospPatientId==null)thospPatientId="";
		String tchannelId = objreq.getChannelId();
		if(tchannelId==null)tchannelId="";
		String tisReg = objreq.getIsReg();
		if(tisReg==null)tisReg="";
		String tregId = objreq.getRegId();
		if(tregId==null)tregId="";
		String tregLevel = objreq.getRegLevel();
		if(tregLevel==null)tregLevel="";
		String thosId = objreq.getHosId();
		if(thosId==null)thosId="";
		String tdeptId = objreq.getDeptId();
		if(tdeptId==null)tdeptId="";
		String tdoctorId = objreq.getDoctorId();
		if(tdoctorId==null)tdoctorId="";
		String tregDate = objreq.getRegDate();
		if(tregDate==null)tregDate="";
		String ttimeFlag = objreq.getTimeFlag();
		if(ttimeFlag==null)ttimeFlag="";
		String tbeginTime = objreq.getBeginTime();
		if(tbeginTime==null)tbeginTime="";
		String tendTime = objreq.getEndTime();
		if(tendTime==null)tendTime="";
		String tregFee = objreq.getRegFee();
		if(tregFee==null)tregFee="";
		String ttreatFee = objreq.getTreatFee();
		if(ttreatFee==null)ttreatFee="";
		String tregType = objreq.getRegType();
		if(tregType==null)tregType="";
		String tidcardType = objreq.getIdcardType();
		if(tidcardType==null)tidcardType="";
		String tidcardNo = objreq.getIdcardNo();
		if(tidcardNo==null)tidcardNo="";
		String tcardType = objreq.getCardType();
		if(tcardType==null)tcardType="";
		String tcardNo = objreq.getCardNo();
		if(tcardNo==null)tcardNo="";
		String tname = objreq.getName();
		if(tname==null)tname="";
		String tsex = objreq.getSex();
		if(tsex==null)tsex="";
		String tbirthday = objreq.getBirthday();
		if(tbirthday==null)tbirthday="";
		String taddress = objreq.getAddress();
		if(taddress==null)taddress="";
		String tmobile = objreq.getMobile();
		if(tmobile==null)tmobile="";
		String toperIdcardType = objreq.getOperIdcardType();
		if(toperIdcardType==null)toperIdcardType="";
		String toperIdcardNo = objreq.getOperIdcardNo();
		if(toperIdcardNo==null)toperIdcardNo="";
		String toperName = objreq.getOperName();
		if(toperName==null)toperName="";
		String toperMobile = objreq.getOperMobile();
		if(toperMobile==null)toperMobile="";
		String tagentId = objreq.getAgentId();
		if(tagentId==null)tagentId="";
		String torderTime = objreq.getOrderTime();
		if(torderTime==null)torderTime="";
		
		String tinstr = torderId+"|"+thospPatientId+"|"+tchannelId+"|"+tisReg+"|"+tregId+"|"+tregLevel+"|"+thosId+"|"+
		    tdeptId+"|"+tdoctorId+"|"+tregDate+"|"+ttimeFlag+"|"+tbeginTime+"|"+tendTime+"|"+tregFee+"|"+ttreatFee+"|"+
		    tregType+"|"+tidcardType+"|"+tidcardNo+"|"+tcardType+"|"+tcardNo+"|"+tname+"|"+tsex+"|"+tbirthday+"|"+
			taddress+"|"+tmobile+"|"+toperIdcardType+"|"+toperIdcardNo+"|"+toperName+"|"+toperMobile+"|"+tagentId+"|"+torderTime;
		
		log.info("传入预挂号接口"+tinstr);
		
		try {
			toutstr = HttpEastwill.post_hisurl(turl, tinstr);			
			HosparamOut thout = new HosparamOut();
			thout = JSON.parseObject(toutstr, HosparamOut.class);
			String tresultCode = thout.getResultCode();
			String toutdata = thout.getOutdata();
			//log.info("1:"+toutdata);
			toutdata = URLDecoder.decode(toutdata, "utf-8");
			log.info("预挂号接口2:"+toutdata);
			String terrMsg = thout.getErrorMsg();
			if(tresultCode.equals("0")) {
				log.info("获取his预挂号接口成功"+toutdata);
			}else {
				log.info("获取his预挂号接口失败"+terrMsg);
				return MessageFormat.format(res_xml, "-3", "获取his预挂号接口失败"+terrMsg, "MD5", res_sign, res_encrypted);
			}
			toutstr = toutdata;
			
		}catch(Exception e) {
			log.error("3:获取his预挂号接口失败"+e);
			return MessageFormat.format(res_xml, "-3", "获取his预挂号接口失败"+e.getMessage(), "MD5", res_sign, res_encrypted);
		}
		log.info("返回预挂号接口信息:"+toutstr);
		
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
