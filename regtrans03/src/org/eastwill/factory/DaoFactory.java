package org.eastwill.factory;
/**
 * ������
 * ��������� class���� ��ø����͵� ����
 * @since 2015-12-23 15:45:37
 * @author wangjin
 *
 */
public class DaoFactory {
	
	/**
	 * TΪ����
	 * ���� class�����ȡ��Ӧ�� ����
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
