/**
 * 
 */
package commons;

/**
 * 服务器端消息处理
 * @author win10
 *
 */
public class ServerHandle extends MessageHandle {

	/**
	 * @param sender
	 * @param message
	 * @param receiver
	 * @param actionHandleClass
	 */
	public ServerHandle(User sender, String message, User receiver, String actionHandleClass) {
		super(sender, message, receiver, actionHandleClass);
		// TODO Auto-generated constructor stub
	}
}
