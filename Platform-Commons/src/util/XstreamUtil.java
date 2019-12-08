package util;

import com.thoughtworks.xstream.XStream;

/**
 * XStream对象
 * @author win10
 *
 */
public class XstreamUtil {
	private static XStream xstream = new XStream();
	
	/**
	 * 将XML转换成对象
	 * @param xml
	 * @return
	 */
	public static Object fromXML(String xml) {
		return xstream.fromXML(xml);
	}
	
	/**
	 * 将对象转换成XML字段串
	 * @param obj
	 * @return
	 */
	public static String toXML(Object obj) {
		String xml = xstream.toXML(obj);
		//去掉换行
		String a = xml.replaceAll("\n", "");
		String s = a.replaceAll("\r", "");
		return s;
	}
}
