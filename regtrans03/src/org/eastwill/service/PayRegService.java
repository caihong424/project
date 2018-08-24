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
import org.eastwill.model.RegPayReqModel;
import org.eastwill.utils.AesUtils;
import org.eastwill.utils.Config;
import org.eastwill.utils.HttpEastwill;
import org.eastwill.utils.Sign;
import org.eastwill.utils.SignEastwill;
import org.eastwill.utils.XmlUtils;

import com.alibaba.fastjson.JSON;
//挂号支付接口
public class PayRegService {
	private static final Log log=LogFactory.getLog(PayRegService.class);
	
	public static String payReg(String strXml, String res_xml) throws Exception {
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
		//挂号支付接口
		RegPayReqModel objreq = XmlUtils.toBean(AesUtils.decrypt(Config.getProper("key"),obj.getReqEncrypted()),RegPayReqModel.class);
		String turl = Config.getProper("url")+"/PayReg";		
		String thosId = objreq.getHosId(); //HOS_ID	REQ	医院ID
		if(thosId==null)thosId="";
		String torderId = objreq.getOrderId(); //ORDER_ID	平台订单号
		if(torderId==null)torderId="";
		String thospOrderId = objreq.getHospOrderId(); //HOSP_ORDER_ID	医院订单号
		if(thospOrderId==null)thospOrderId="";
		String tserialNum = objreq.getSerialNum(); //SERIAL_NUM	流水号（银行流水号、第三方支付流水号等）
		if(tserialNum==null)tserialNum="";
		String tpayDate = objreq.getPayDate(); //PAY_DATE	交易日期（银行、第三方支付等），格式：YYYY-MM-DD
		if(tpayDate==null)tpayDate="";
		String tpayTime = objreq.getPayTime(); //PAY_TIME	交易时间（银行、第三方支付等），格式：HH24:MI:SS
		if(tpayTime==null)tpayTime="";
		String tpayChannelId = objreq.getPayChannelId(); //PAY_CHANNEL_ID	支付渠道ID，详见 “支付渠道”
		if(tpayChannelId==null)tpayChannelId="";
		String tpayTotalFee = objreq.getPayTotalFee(); //PAY_TOTAL_FEE	总金额，单位：分
		if(tpayTotalFee==null)tpayTotalFee="";
		String tpayCopeFee = objreq.getPayCopeFee(); //PAY_COPE_FEE	应付金额，单位：分
		if(tpayCopeFee==null)tpayCopeFee="";
		String tpayFee = objreq.getPayFee(); //PAY_FEE 实付金额，单位：分
		if(tpayFee==null)tpayFee="";
		String tpayResCode = objreq.getPayResCode(); //PAY_RES_CODE	交易响应代码(银行、第三方支付等返回的结果码)
		if(tpayResCode==null)tpayResCode="";
		String tpayResDesc = objreq.getPayResDesc(); //PAY_RES_DESC	交易响应描述
		if(tpayResDesc==null)tpayResDesc="";
		String tmerchantId = objreq.getMerchantId(); //MERCHANT_ID	商户号，对应支付渠道的商户号
		if(tmerchantId==null)tmerchantId="";
		String tterminalId = objreq.getTerminalId(); //TERMINAL_ID	终端号，对应支付渠道的终端号
		if(tterminalId==null)tterminalId="";
		String tbankNo = objreq.getBankNo(); //BANK_NO	银行卡号
		if(tbankNo==null)tbankNo="";
		String tpayAccount = objreq.getPayAccount(); //PAY_ACCOUNT	第三方支付时，用户的支付帐号
		if(tpayAccount==null)tpayAccount="";
		String tinstr = thosId+"|"+ torderId+"|"+thospOrderId+"|"+tserialNum+"|"+tpayDate+"|"+tpayTime+"|"+
			tpayChannelId+"|"+tpayTotalFee+"|"+tpayCopeFee+"|"+tpayFee+"|"+tpayResCode+"|"+tpayResDesc+"|"+
			tmerchantId+"|"+tterminalId+"|"+tbankNo+"|"+tpayAccount;
		log.info("传入挂号支付接口信息"+tinstr);
		
		try {
			toutstr = HttpEastwill.post_hisurl(turl, tinstr);			
			HosparamOut thout = new HosparamOut();
			thout = JSON.parseObject(toutstr, HosparamOut.class);
			String tresultCode = thout.getResultCode();
			String toutdata = thout.getOutdata();
			//log.info("1:"+toutdata);
			toutdata = URLDecoder.decode(toutdata, "utf-8");
			log.info("挂号支付接口2:"+toutdata);
			String terrMsg = thout.getErrorMsg();
			if(tresultCode.equals("0")) {
				log.info("获取his挂号支付接口成功"+toutdata);
			}else {
				log.info("获取his挂号支付接口失败"+terrMsg);
				return MessageFormat.format(res_xml, "-3", "挂号支付接口失败"+terrMsg, "MD5", res_sign, res_encrypted);
			}
			
		
			toutstr = toutdata;
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
