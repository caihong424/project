package org.eastwill.webservice;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;


@WebService
public interface RegInterFace {
	//网络通讯测试
	@WebMethod
	public @WebResult String NetTest(String xml)throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException; 
	
	//科室查询接口2001
	@WebMethod
	public @WebResult String GetDeptInfo(String xml); 
	
	//医生查询接口2002
	@WebMethod
	public @WebResult String GetDoctorInfo(String xml); 
	
	//排班信息查询接口2003
	@WebMethod
	public @WebResult String GetRegInfo(String xml); 
	
	//排班分时查询接口(GetTimeRegInfo)2004
	@WebMethod
	public @WebResult String GetTimeRegInfo(String xml); 
	
	//号源锁定接口(LockReg)2005
	@WebMethod
	public @WebResult String LockReg(String xml);
	
	//解除号源锁定接口(UnLockReg)2006
	@WebMethod
	public @WebResult String UnLockReg(String xml);
	
	//预挂号接口(OrderReg) 2007
	@WebMethod
	public @WebResult String OrderReg(String xml);
	
	//挂号支付接口(PayReg)2008
	@WebMethod
	public @WebResult String PayReg(String xml);
	
	//取消挂号接口(CancelReg)2009
	@WebMethod
	public @WebResult String CancelReg(String xml);
	
	//退款挂号接口(Refund)2010
	@WebMethod
	public @WebResult String Refund(String xml);
	
	//取号接口(GetRegNum)2011
	@WebMethod
	public @WebResult String GetRegNum(String xml);
	
	//挂号记录查询接口(QueryRegRecord)2012
	@WebMethod
	public @WebResult String QueryRegRecord(String xml);
	
	//医生门诊数据查询(GetOutpatientDoctorData)2020
	@WebMethod
	public @WebResult String GetOutpatientDoctorData(String xml);
	
	//----------------------------------------------------------------------
	//缴费记录查询接口(GetPayList)3001
	@WebMethod
	public @WebResult String GetPayList(String xml);
	
	//缴费明细查询接口(GetPayDetail)3002
	@WebMethod
	public @WebResult String GetPayDetail(String xml);
	
	//待缴费记录支付接口(PayOrder)3003
	@WebMethod
	public @WebResult String PayOrder(String xml);
	
	//缴费订单查询接口(QueryPayRecord)3004
	@WebMethod
	public @WebResult String QueryPayRecord(String xml);
	
	//-----------------------------------------------------------
	// 排队列表查询接口(GetUserLineUpList)4001
	@WebMethod
	public @WebResult String GetUserLineUpList(String xml);
	
	//--------------------------------------------------------------------------
	//检查/检验列表查询接口(GetCheckOutReportList)8001
	@WebMethod
	public @WebResult String GetCheckOutReportList(String xml);
	
	//检验报告查询（普通检验）接口(GetNormalReportInfo)8002
	@WebMethod
	public @WebResult String GetNormalReportInfo(String xml);
	
	//检验报告查询（药敏检验）接口(GetDurgReportInfo)8003
	@WebMethod
	public @WebResult String GetDurgReportInfo(String xml);
	
	//检查报告查询接口(GetCheckReportInfo)8004
	@WebMethod
	public @WebResult String GetCheckReportInfo(String xml);
}
