package org.eastwill.webservice.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eastwill.message.ErrorMsg;
import org.eastwill.message.ResponseMsg;
import org.eastwill.service.CancelRegService;
import org.eastwill.service.GetCheckOutReportListService;
import org.eastwill.service.GetCheckReportInfoService;
import org.eastwill.service.GetDeptInfoService;
import org.eastwill.service.GetDoctorInfoService;
import org.eastwill.service.GetDurgReportInfoService;
import org.eastwill.service.GetNormalReportInfoService;
import org.eastwill.service.GetOutpatientDoctorDataService;
import org.eastwill.service.GetPayDetailService;
import org.eastwill.service.GetPayListService;
import org.eastwill.service.GetRegInfoService;
import org.eastwill.service.GetRegNumService;
import org.eastwill.service.GetTimeRegInfoService;
import org.eastwill.service.GetUserLineUpListService;
import org.eastwill.service.LockRegService;
import org.eastwill.service.NetWorkService;
import org.eastwill.service.OrderRegService;
import org.eastwill.service.PayOrderService;
import org.eastwill.service.PayRegService;
import org.eastwill.service.QueryPayRecordService;
import org.eastwill.service.QueryRegRecordService;
import org.eastwill.service.RefundService;
import org.eastwill.service.UnLockRegService;
import org.eastwill.utils.AesUtils;
import org.eastwill.utils.Config;
import org.eastwill.utils.Sign;
import org.eastwill.webservice.RegInterFace;


@WebService (endpointInterface = "org.eastwill.webservice.RegInterFace") 
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class RegInterFaceImpl implements RegInterFace, Serializable {
	private static final long serialVersionUID = 1L;
	//创建日志对象
    Log log=LogFactory.getLog(this.getClass());
    
	public RegInterFaceImpl() {
		try {
			Config.loadProper();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String NetTest(String xml) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		xml = xml.trim();
		log.info("网络通讯测试入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("网络通讯测试接收报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
			log.info("NetTest接收报文格式:" + xml);
			return NetWorkService.netWorkTest(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("NetTest"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}

	@Override
	public String GetDeptInfo(String xml) {
		xml = xml.trim();
		log.info("科室查询接口入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("科室查询接口接收报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
		    //入参正常,返回结果
			return GetDeptInfoService.getDeptInfo(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("科室查询接口"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = "";
			try {
				res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			} catch (Exception e) {
				log.info("科室查询接口加密失败"+ e);
			}
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}

	@Override
	public String GetDoctorInfo(String xml) {
		xml = xml.trim();
		log.info("医生查询接口入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("医生查询接口接收报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
		    //入参正常,返回结果
			return GetDoctorInfoService.getDoctorInfo(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("医生查询接口"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = "";
			try {
				res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			} catch (Exception e) {
				log.info("医生查询接口加密失败"+ e);
			}
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}

	@Override
	public String GetRegInfo(String xml) {
		xml = xml.trim();
		log.info("排班信息查询接口入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("排班信息查询接口报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
		    //入参正常,返回结果
			return GetRegInfoService.getRegInfo(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("排班信息查询接口"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = "";
			try {
				res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			} catch (Exception e) {
				log.info("排班信息查询接口加密失败"+ e);
			}
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}

	@Override
	public String GetTimeRegInfo(String xml) {
		xml = xml.trim();
		log.info("排班分时查询接口入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("排班分时查询接口报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
		    //入参正常,返回结果
			return GetTimeRegInfoService.getTimeRegInfo(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("排班分时查询接口"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = "";
			try {
				res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}

	@Override
	public String LockReg(String xml) {
		xml = xml.trim();
		log.info("号源锁定接口入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("号源锁定接口报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
		    //入参正常,返回结果
			return LockRegService.lockReg(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("号源锁定接口接口"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = "";
			try {
				res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}

	@Override
	public String UnLockReg(String xml) {
		xml = xml.trim();
		log.info("解除号源锁定接口入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("解除号源锁定接口报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
		    //入参正常,返回结果
			return UnLockRegService.unLockReg(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("解除号源锁定接口接口"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = "";
			try {
				res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}

	@Override
	public String OrderReg(String xml) {
		xml = xml.trim();
		log.info("预挂号接口入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("预挂号接口报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
		    //入参正常,返回结果
			return OrderRegService.orderReg(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("预挂号接口"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = "";
			try {
				res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}

	@Override
	public String PayReg(String xml) {
		xml = xml.trim();
		log.info("挂号支付接口入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("挂号支付接口报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
		    //入参正常,返回结果
			return PayRegService.payReg(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("挂号支付接口"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = "";
			try {
				res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}

	@Override
	public String CancelReg(String xml) {
		xml = xml.trim();
		log.info("取消挂号接口入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("取消挂号接口报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
		    //入参正常,返回结果
			return CancelRegService.cancelReg(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("取消挂号接口"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = "";
			try {
				res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}

	@Override
	public String Refund(String xml) {
		xml = xml.trim();
		log.info("退款挂号接口入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("退款挂号接口报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
		    //入参正常,返回结果
			return RefundService.refund(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("退款挂号接口"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = "";
			try {
				res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}

	@Override
	public String GetRegNum(String xml) {
		xml = xml.trim();
		log.info("取号接口入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("取号接口报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
		    //入参正常,返回结果
			return GetRegNumService.getRegNum(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("取号接口接口"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = "";
			try {
				res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}

	@Override
	public String QueryRegRecord(String xml) {
		xml = xml.trim();
		log.info("挂号记录查询接口入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("挂号记录查询接口报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
		    //入参正常,返回结果
			return QueryRegRecordService.queryRegRecord(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("挂号记录查询接口接口"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = "";
			try {
				res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}

	@Override
	public String GetOutpatientDoctorData(String xml) {
		xml = xml.trim();
		log.info("医生门诊数据查询入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("医生门诊数据查询报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
		    //入参正常,返回结果
			return GetOutpatientDoctorDataService.getOutpatientDoctorData(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("医生门诊数据查询"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = "";
			try {
				res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}

	@Override
	public String GetPayList(String xml) {
		xml = xml.trim();
		log.info("2.1.3.1 缴费记录查询接口入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("2.1.3.1 缴费记录查询接口报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
		    //入参正常,返回结果
			return GetPayListService.getPayList(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("2.1.3.1 缴费记录查询接口"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = "";
			try {
				res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}

	@Override
	public String GetPayDetail(String xml) {
		xml = xml.trim();
		log.info("缴费明细查询接口入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("缴费明细查询接口报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
		    //入参正常,返回结果
			return GetPayDetailService.getPayDetail(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("排班信息查询接口"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = "";
			try {
				res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}

	@Override
	public String PayOrder(String xml) {
		xml = xml.trim();
		log.info("待缴费记录支付接口入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("待缴费记录支付接口报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
		    //入参正常,返回结果
			return PayOrderService.payOrder(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("待缴费记录支付接口"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = "";
			try {
				res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}

	@Override
	public String QueryPayRecord(String xml) {
		xml = xml.trim();
		log.info("缴费订单查询接口入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("缴费订单查询接口报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
		    //入参正常,返回结果
			return QueryPayRecordService.queryPayRecord(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("缴费订单查询接口"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = "";
			try {
				res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}

	@Override
	public String GetUserLineUpList(String xml) {
		xml = xml.trim();
		log.info("排队列表查询接口入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("排队列表查询接口报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
		    //入参正常,返回结果
			return GetUserLineUpListService.getUserLineUpList(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("排队列表查询接口"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = "";
			try {
				res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}

	@Override
	public String GetCheckOutReportList(String xml) {
		xml = xml.trim();
		log.info("检查/检验列表查询接口入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("检查/检验列表查询接口报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
		    //入参正常,返回结果
			return GetCheckOutReportListService.getCheckOutReportList(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("检查/检验列表查询接口"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = "";
			try {
				res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}

	@Override
	public String GetNormalReportInfo(String xml) {
		xml = xml.trim();
		log.info("检验报告查询（普通检验）接口入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("检验报告查询（普通检验）接口报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
		    //入参正常,返回结果
			return GetNormalReportInfoService.getNormalReportInfo(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("检验报告查询（普通检验）接口"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = "";
			try {
				res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}

	@Override
	public String GetDurgReportInfo(String xml) {
		xml = xml.trim();
		log.info("检验报告查询（药敏检验）接口入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("检验报告查询（药敏检验）接口报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
		    //入参正常,返回结果
			return GetDurgReportInfoService.getDurgReportInfo(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("检验报告查询（药敏检验）接口"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = "";
			try {
				res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}

	@Override
	public String GetCheckReportInfo(String xml) {
		xml = xml.trim();
		log.info("检查报告查询接口入参 xml="+xml);		
		try {
			if (!StringUtils.isNotEmpty(xml) || !StringUtils.isNotBlank(xml) || !StringUtils.endsWith(xml, "</ROOT>")) {
				log.info("检查报告查询接口报文格式:" + xml);
				String res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
				String res_sign = Sign.GetResponseSign(ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), res_encrypted, Config.getProper("key"));
				return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, ErrorMsg.CLIENT_INPUT_ERROR.getResCode(), ErrorMsg.CLIENT_INPUT_ERROR.getResMsg(), "MD5", res_sign, res_encrypted);
			}
		    //入参正常,返回结果
			return GetCheckReportInfoService.getCheckReportInfo(xml, ResponseMsg.NET_WORK_MESSAGE);
		} catch (Exception ex) {
			log.info("检查报告查询接口"+ ex);
			String errorMsg = MessageFormat.format("异常：{0}", ex.getMessage());
			String res_encrypted = "";
			try {
				res_encrypted = AesUtils.encrypt(Config.getProper("key"), "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String res_sign = Sign.GetResponseSign(-1, errorMsg, res_encrypted, Config.getProper("key"));
			return MessageFormat.format(ResponseMsg.NET_WORK_MESSAGE, -1, errorMsg, "MD5", res_sign, res_encrypted);
		}
	}


}
