package org.eastwill.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件读取类
 * 
 * @author 王畅
 * @version 创建时间：2015-4-11 下午08:43:14
 */
public class Config {

	//创建日志对象
	private static final Log log=LogFactory.getLog(Config.class);
	private static Properties prop = new Properties();

	/**
	 * 加载配置文件
	 * 
	 * @author 王畅
	 * @throws Exception
	 */
	public static void loadProper() throws Exception {
		String system_name = System.getProperty("os.name");
		log.info("system_name="+system_name);
		
		InputStream is = null;
		if ((system_name != null) && (system_name.toLowerCase().indexOf("linux") != -1)) {
			String ctxPath = System.getProperty("user.dir");
			String sep = System.getProperty("file.separator");
			String certPath = ctxPath+sep+sep+"src"+sep+sep+"config.properties";
			//is = new FileInputStream("/gytl/gytl-conf/config.properties");
			log.info("certPath="+certPath);
			is = new FileInputStream(certPath);
			prop.load(is);
			is.close();
		} else {
			is = Config.class.getClassLoader().getResourceAsStream("config.properties");
			prop.load(is);
		}
		if (is != null) {
			is.close();
		}

	}

	public static String getProper(String key) {
		return prop.getProperty(key);
	}
}
