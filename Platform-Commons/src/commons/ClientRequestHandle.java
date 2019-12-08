package commons;

import util.XstreamUtil;

/**
 * �ͻ��˷�����Ϣ����
 * @author win10
 *
 */
public class ClientRequestHandle extends MessageHandle {

	/**
	 * @param sender ������
	 * @param message ��Ϣ
	 * @param receiver ������
	 * @param actionHandleClass ���պ�����������
	 */
	public ClientRequestHandle(User sender, String message, User receiver, String actionHandleClass) {
		super(sender, message, receiver, actionHandleClass);
		// TODO Auto-generated constructor stub
	}
	/**
	 * ת����XML
	 * @return XML�ַ���
	 */
	public String toXML() {
//		ʹ��XStreamת��ΪXML�ַ���
		return XstreamUtil.toXML(this);
	}
}
