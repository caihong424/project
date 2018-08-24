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
import org.eastwill.model.RegRefundReqModel;
import org.eastwill.utils.AesUtils;
import org.eastwill.utils.Config;
import org.eastwill.utils.HttpEastwill;
import org.eastwill.utils.Sign;
import org.eastwill.utils.SignEastwill;
import org.eastwill.utils.XmlUtils;

import com.alibaba.fastjson.JSON;

public class RefundService {
	private static final Log log=LogFactory.getLog(RefundService.class);
	
	public static String refund(String strXml, String res_xml) throws Exception {
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
		//退款挂号接口(Refund)
		RegRefundReqModel objreq = XmlUtils.toBean(AesUtils.decrypt(Config.getProper("key"),obj.getReqEncrypted()),RegRefundReqModel.class);
		String turl = Config.getProper("url")+"/Refund";
		String thosId = objreq.getHosId(); //HOS_ID 医院ID
		String torderId = objreq.getOrderId(); //ORDER_ID 平台订单号
		String thospOrderId = objreq.getHospOrderId(); //HOSP_ORDER_ID 医院订单号
		String trefundId = objreq.getRefundId(); //REFUND_ID 平台退款单号
		String trefundSerialNum = objreq.getRefundSerialNum(); //REFUND_SERIAL_NUM 退款流水号（银行流水号、第三方支付流水号等）
		String ttotalFee = objreq.getTotalFee(); //TOTAL_FEE 总金额，单位：分
		String trefundFee = objreq.getRefundFee(); //REFUND_FEE 退款金额，单位：分
		String trefundDate = objreq.getRefundDate(); //REFUND_DATE 退款日期(银行、第三方支付等返回的结果码)，格式：YYYY-MM-DD
		String trefundTime = objreq.getRefundTime(); //REFUND_TIME 退款时间(银行、第三方支付等返回的结果码)，格式：HH24:MI:SS
		String trefundResCode = objreq.getRefundResCode(); //REFUND_RES_CODE 交易响应代码(银行、第三方支付等返回的结果码)
		String trefundResDesc = objreq.getRefundResDesc(); //REFUND_RES_DESC 交易响应描述
		String trefundRemark = objreq.getRefundRemark(); //REFUND_REMARK 退款原因
		String tinstr = thosId+"|"+torderId+"|"+thospOrderId+"|"+trefundId+"|"+trefundSerialNum+"|"+
				ttotalFee+"|"+trefundFee+"|"+trefundDate+"|"+trefundTime+"|"+
				trefundResCode+"|"+trefundResDesc+"|"+trefundRemark;
		log.info("传入退款挂号接口"+tinstr);
		
		try {
			toutstr = HttpEastwill.post_hisurl(turl, tinstr);			
			HosparamOut thout = new HosparamOut();
			thout = JSON.parseObject(toutstr, HosparamOut.class);
			String tresultCode = thout.getResultCode();
			String toutdata = thout.getOutdata();
			//log.info("1:"+toutdata);
			toutdata = URLDecoder.decode(toutdata, "utf-8");
			log.info("退款挂号接口2:"+toutdata);
			String terrMsg = thout.getErrorMsg();
			if(tresultCode.equals("0")) {
				log.info("his退款挂号接口成功"+toutdata);
			}else {
				log.info("his退款挂号接口失败"+terrMsg);
				return MessageFormat.format(res_xml, "-3", "his退款挂号接口失败"+terrMsg, "MD5", res_sign, res_encrypted);
			}

			toutstr = toutdata;
		}catch(Exception e) {
			log.error("3:退款挂号接口失败"+e);
			return MessageFormat.format(res_xml, "-3", "退款挂号接口失败"+e.getMessage(), "MD5", res_sign, res_encrypted);
		}
		log.info("返回退款挂号接口信息:"+toutstr);

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
