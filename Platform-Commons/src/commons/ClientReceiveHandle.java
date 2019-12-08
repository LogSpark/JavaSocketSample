package commons;

/**
 * 客户端接收消息的信息处理
 * @author win10
 *
 */
public class ClientReceiveHandle extends MessageHandle {

	/**
	 * @param sender
	 * @param message
	 * @param receiver
	 * @param actionHandleClass
	 */
	public ClientReceiveHandle(User sender, String message, User receiver, String actionHandleClass) {
		super(sender, message, receiver, actionHandleClass);
		// TODO Auto-generated constructor stub
	}

}
