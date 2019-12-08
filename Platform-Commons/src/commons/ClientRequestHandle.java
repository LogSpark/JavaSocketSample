package commons;

import util.XstreamUtil;

/**
 * 客户端发送消息处理
 * @author win10
 *
 */
public class ClientRequestHandle extends MessageHandle {

	/**
	 * @param sender 发送者
	 * @param message 消息
	 * @param receiver 接收者
	 * @param actionHandleClass 接收后动作处理类名
	 */
	public ClientRequestHandle(User sender, String message, User receiver, String actionHandleClass) {
		super(sender, message, receiver, actionHandleClass);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 转化成XML
	 * @return XML字符串
	 */
	public String toXML() {
//		使用XStream转化为XML字符串
		return XstreamUtil.toXML(this);
	}
}
