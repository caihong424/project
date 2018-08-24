package org.eastwill.factory;
/**
 * 工厂类
 * 根据输入的 class类型 获得该类型的 对象
 * @since 2015-12-23 15:45:37
 * @author wangjin
 *
 */
public class DaoFactory {
	
	/**
	 * T为泛型
	 * 根据 class对象获取相应的 对象
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getDaoImpl(Class<T>c){
		try {
			T newInstance = (T) Class.forName(c.getName()).newInstance();
			return newInstance;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
