package commons;

/**
 * 消息处理父类
 * @author win10
 *
 */
public class MessageHandle {
	/**
	 * 发送者
	 */
	public User sender;
	/**
	 * 消息
	 */
	public String message;
	/**
	 * 接收者
	 */
	public User receiver;
	/**
	 * 接收到后消息处理类
	 */
	public String actionHandleClass;

	/**
	 * @param sender 发送者
	 * @param message 消息
	 * @param receiver 接收者
	 * @param actionHandleClass 接收到后消息处理类
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
