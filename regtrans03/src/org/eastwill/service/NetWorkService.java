package org.eastwill.service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eastwill.message.ErrorMsg;
import org.eastwill.model.NetTestRequstModel;
import org.eastwill.model.NetTestRequstParamModel;
import org.eastwill.utils.AesUtils;
import org.eastwill.utils.Sign;
import org.eastwill.utils.SignEastwill;
import org.eastwill.utils.DateUtils;
import org.eastwill.utils.XmlUtils;
import org.eastwill.utils.Config;

/**
 * 网络测试业务类
 * 
 * @author 王畅
 * @version 创建时间：2015-4-28 下午05:42:33
 */
public class NetWorkService {
	private static final Log log=LogFactory.getLog(NetWorkService.class);
	
	public static String netWorkTest(String strXml, String res_xml) throws Exception {
		String res_encrypted = "";
		String res_sign = "";
		NetTestRequstModel obj = XmlUtils.toBean(strXml, NetTestRequstModel.class);
		if (!StringUtils.equalsIgnoreCase(obj.getUserId(), Config.getProper("userId"))) {
			res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			res_sign = Sign.GetResponseSign(ErrorMsg.USER_NOT_EXIST.getResCode(), ErrorMsg.USER_NOT_EXIST.getResMsg(), res_encrypted, Config.getProper("key"));
			log.info("1="+ErrorMsg.CLIENT_INPUT_ERROR.getResCode());
			return MessageFormat.format(res_xml, ErrorMsg.USER_NOT_EXIST.getResCode(), ErrorMsg.USER_NOT_EXIST.getResMsg(), "MD5", res_sign, res_encrypted);
		}
		//log.info("bf2="+obj.getFunCode()+","+obj.getUserId()+",obj.getReqEncrypted()="+obj.getReqEncrypted()+","+Config.getProper("key"));
		

		NetTestRequstParamModel objreq = XmlUtils.toBean(AesUtils.decrypt(Config.getProper("key"),obj.getReqEncrypted()), NetTestRequstParamModel.class);
		log.info("hosid="+objreq.getHosId()+" ip="+objreq.getIp());
		Map<String, String> tdata = new HashMap<String, String>();
		tdata.put("FUN_CODE", obj.getFunCode());
		tdata.put("USER_ID", obj.getUserId());
		tdata.put("REQ_ENCRYPTED", obj.getReqEncrypted());
		String sign = SignEastwill.generateSignature(tdata, Config.getProper("key"));
		
		//log.info("bf2= sign="+sign+" obj.getSign()="+obj.getSign()+" obj.getSignType()="+obj.getSignType());
		//log.info("StringUtils.equalsIgnoreCase(obj.getSignType(), \"MD5\")="+StringUtils.equalsIgnoreCase(obj.getSignType(), "MD5"));
		//log.info("StringUtils.equalsIgnoreCase(obj.getSign(), sign)="+StringUtils.equalsIgnoreCase(obj.getSign(), sign));
		if (!StringUtils.equalsIgnoreCase(obj.getSignType(), "MD5") || !StringUtils.equalsIgnoreCase(obj.getSign(), sign)) {
			res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			res_sign = Sign.GetResponseSign(ErrorMsg.SIGN_NOT_RIGHT.getResCode(), ErrorMsg.SIGN_NOT_RIGHT.getResMsg(), res_encrypted, Config.getProper("key"));
			log.info("2="+ErrorMsg.CLIENT_INPUT_ERROR.getResCode());
			return MessageFormat.format(res_xml, ErrorMsg.SIGN_NOT_RIGHT.getResCode(), ErrorMsg.SIGN_NOT_RIGHT.getResMsg(), "MD5", res_sign, res_encrypted);
		}
		// 密文
		res_encrypted = AesUtils.encrypt(Config.getProper("key"), MessageFormat.format("<RES><SYSDATE>{0}</SYSDATE></RES>", DateUtils.getDateTime()));
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
