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
import org.eastwill.model.PayOrderReqModel;
import org.eastwill.utils.AesUtils;
import org.eastwill.utils.Config;
import org.eastwill.utils.HttpEastwill;
import org.eastwill.utils.Sign;
import org.eastwill.utils.SignEastwill;
import org.eastwill.utils.XmlUtils;

import com.alibaba.fastjson.JSON;

public class PayOrderService {
	private static final Log log=LogFactory.getLog(PayOrderService.class);
	
	public static String payOrder(String strXml, String res_xml) throws Exception {
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
		//待缴费记录支付接口
		PayOrderReqModel objreq = XmlUtils.toBean(AesUtils.decrypt(Config.getProper("key"),obj.getReqEncrypted()),PayOrderReqModel.class);
		String turl = Config.getProper("url")+"/PayOrder";
		String thosId = objreq.getHosId();
		String torderId = objreq.getOrderId();
		String thospSequence = objreq.getHospSequence();
		String tserialNum = objreq.getSerialNum();
		String tpayDate = objreq.getPayDate();
		String tpayTime = objreq.getPayTime();
		String tpayChannelId = objreq.getPayChannelId();
		String tpayTotalFee = objreq.getPayTotalFee();
		String tpayBehooveFee = objreq.getPayBehooveFee();
		String tpayActualFee = objreq.getPayActualFee();
		String tpayMiFee = objreq.getPayMiFee();
		String tpayResCode = objreq.getPayResCode();
		String tpayResDesc = objreq.getPayResDesc();
		String tmerchantId = objreq.getMerchantId();
		String tterminalId = objreq.getTerminalId();
		String tbankNo = objreq.getBankNo();
		String tpayAccount = objreq.getPayAccount();
		String toperatorId = objreq.getOperatorId();
		String treceiptId = objreq.getReceiptId();
		
		String tinstr = thosId+"|"+torderId+"|"+thospSequence+"|"+tserialNum+"|"+tpayDate+"|"+tpayTime+"|"+tpayChannelId+"|"+tpayTotalFee+"|"+tpayBehooveFee+"|"+tpayActualFee+"|"+tpayMiFee+"|"+tpayResCode+"|"+tpayResDesc+"|"+tmerchantId+"|"+tterminalId+"|"+tbankNo+"|"+tpayAccount+"|"+toperatorId+"|"+treceiptId;
		log.info("传入待缴费记录支付信息"+tinstr);
		
		try {
			toutstr = HttpEastwill.post_hisurl(turl, tinstr);			
			HosparamOut thout = new HosparamOut();
			thout = JSON.parseObject(toutstr, HosparamOut.class);
			String tresultCode = thout.getResultCode();
			String toutdata = thout.getOutdata();
			//log.info("1:"+toutdata);
			toutdata = URLDecoder.decode(toutdata, "utf-8");
			log.info("待缴费记录支付信息2:"+toutdata);
			String terrMsg = thout.getErrorMsg();
			if(tresultCode.equals("0")) {
				log.info("获取his待缴费记录支付信息成功"+toutdata);
			}else {
				log.info("获取his待缴费记录支付信息失败"+terrMsg);
				return MessageFormat.format(res_xml, "-3", "获取his待缴费记录支付信息失败"+terrMsg, "MD5", res_sign, res_encrypted);
			}
			
			toutstr = toutdata;
		}catch(Exception e) {
			log.error("3:获取his待缴费记录支付信息失败"+e);
			return MessageFormat.format(res_xml, "-3", "获取his待缴费记录支付信息失败"+e.getMessage(), "MD5", res_sign, res_encrypted);
		}
		log.info("返回待缴费记录支付信息:"+toutstr);

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
