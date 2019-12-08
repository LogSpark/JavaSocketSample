package commons;

/**
 * ��Ϣ������
 * @author win10
 *
 */
public class MessageHandle {
	/**
	 * ������
	 */
	public User sender;
	/**
	 * ��Ϣ
	 */
	public String message;
	/**
	 * ������
	 */
	public User receiver;
	/**
	 * ���յ�����Ϣ������
	 */
	public String actionHandleClass;

	/**
	 * @param sender ������
	 * @param message ��Ϣ
	 * @param receiver ������
	 * @param actionHandleClass ���յ�����Ϣ������
	 */
	public MessageHandle(User sender, String message, User receiver,String actionHandleClass) {
		this.sender = sender;
		this.message = message;
		this.receiver = receiver;
		this.actionHandleClass = actionHandleClass;
	}

	/**
	 * @return
	 */
	public User getSender() {
		return sender;
	}

	/**
	 * @param sender
	 */
	public void setSender(User sender) {
		this.sender = sender;
	}

	/**
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return
	 */
	public User getReceiver() {
		return receiver;
	}

	/**
	 * @param receiver
	 */
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	/**
	 * @return
	 */
	public String getActionHandleClass() {
		return actionHandleClass;
	}

	/**
	 * @param actionHandleClass
	 */
	public void setActionHandleClass(String actionHandleClass) {
		this.actionHandleClass = actionHandleClass;
	}

}
