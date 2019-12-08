package commons;


/**
 * 客户端动作接口
 * @author win10
 *
 */
public interface ClientAction {
	/**
	 * @param crh
	 */
	void execute(ClientReceiveHandle crh);
}
