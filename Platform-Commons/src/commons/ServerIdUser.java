package commons;

import java.net.Socket;

/**
 * 服务器端关联用户ID及其socket
 * @author win10
 *
 */
public class ServerIdUser {

	/**
	 * 用户ID
	 */
	public String Id;
	
	/**
	 * 用户socket
	 */
	public Socket socket;
	
	/**
	 * @param Id
	 * @param socket
	 */
	public ServerIdUser(String Id,Socket socket) {
		// TODO Auto-generated constructor stub
		this.Id = Id;
		this.socket = socket;
	}

	/**
	 * @return
	 */
	public String getId() {
		return Id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		Id = id;
	}

	/**
	 * @return
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * @param socket
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	
}
