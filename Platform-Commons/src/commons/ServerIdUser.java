package commons;

import java.net.Socket;

/**
 * �������˹����û�ID����socket
 * @author win10
 *
 */
public class ServerIdUser {

	/**
	 * �û�ID
	 */
	public String Id;
	
	/**
	 * �û�socket
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
