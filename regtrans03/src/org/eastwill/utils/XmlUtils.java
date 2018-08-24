package org.eastwill.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * XML与JavaBean相互转换工具类
 * 
 * @author 王畅
 * @version 创建时间：2015-4-10 上午11:35:50
 */
public class XmlUtils {

	//创建日志对象
	private static final Log log=LogFactory.getLog(XmlUtils.class);
	private static XStream xstream = null;
	static {
		xstream = new XStream(new DomDriver());

	}

	/**
	 * java转换成XML
	 * 
	 * @param obj
	 * @return
	 * @author Administrator
	 * @since 2015-04-11
	 */
	public static String toXml(Object obj) throws Exception{
		xstream.processAnnotations(obj.getClass()); // 通过注解方式的，一定要有这句话
		return xstream.toXML(obj);
	}

	/**
	 * 将传入XML文本转换成Java对象
	 * 
	 * @param xmlStr
	 * @param cla
	 * @return
	 * @author Administrator
	 * @since 2015-04-11
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toBean(String xmlStr, Class<T> cla) throws Exception {
		xstream.processAnnotations(cla);
		return (T) xstream.fromXML(xmlStr);
	}

	/**
	 * 写到XML文件中去
	 * 
	 * @param obj
	 *            java对象
	 * @param absPath
	 *            绝对路径
	 * @param fileName
	 *            文件名
	 * @return
	 * @since 2015-04-11
	 */
	public static boolean toXMLFile(Object obj, String absPath, String fileName) throws Exception{
		String strXml = toXml(obj);
		String filePath = absPath + fileName;
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				log.error("创建{" + filePath + "}文件失败!!!" + e.getMessage());
				return false;
			}
		}
		OutputStream ous = null;
		try {
			ous = new FileOutputStream(file);
			ous.write(strXml.getBytes());
			ous.flush();
		} catch (Exception e1) {
			log.error("写{" + filePath + "}文件失败!!!" + e1.getMessage());
			return false;
		} finally {
			if (ous != null)
				try {
					ous.close();
				} catch (IOException e) {
					log.error("写{" + filePath + "}文件关闭输出流异常!!!" + e.getMessage());
				}
		}
		return true;
	}

	/**
	 * 从XML文件读取报文
	 * 
	 * @param absPath
	 *            绝对路径
	 * @param fileName
	 * @param cla
	 * @return
	 * @throws Exception
	 * @author Administrator
	 * @since 2015-04-11
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toBeanFromFile(String absPath, String fileName, Class<T> cla) throws Exception {
		String filePath = absPath + fileName;
		InputStream ins = null;
		try {
			ins = new FileInputStream(new File(filePath));
		} catch (Exception e) {
			throw new Exception("读{" + filePath + "}文件失败！", e);
		}
		xstream.processAnnotations(cla);
		T obj = null;
		try {
			obj = (T) xstream.fromXML(ins);
		} catch (Exception e) {
			throw new Exception("解析{" + filePath + "}文件失败！", e);
		}
		if (ins != null)
			ins.close();
		return obj;
	}

}