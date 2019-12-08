package util;

import com.thoughtworks.xstream.XStream;

/**
 * XStream����
 * @author win10
 *
 */
public class XstreamUtil {
	private static XStream xstream = new XStream();
	
	/**
	 * ��XMLת���ɶ���
	 * @param xml
	 * @return
	 */
	public static Object fromXML(String xml) {
		return xstream.fromXML(xml);
	}
	
	/**
	 * ������ת����XML�ֶδ�
	 * @param obj
	 * @return
	 */
	public static String toXML(Object obj) {
		String xml = xstream.toXML(obj);
		//ȥ������
		String a = xml.replaceAll("\n", "");
		String s = a.replaceAll("\r", "");
		return s;
	}
}
