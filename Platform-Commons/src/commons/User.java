package commons;

import java.io.PrintStream;
import java.net.Socket;

/**
 * 用户对象
 * @author win10
 *
 */
public class User {
//	user ID
	public String id;
//	用户名
	public String name;
////	性别  男：0，女：1
//	private int sex;
//	user对应的socket
	private Socket socket;
	
	/**
	 * 
	 */
	public User()
	{
		
	}
	
	/**
	 * @param id
	 * @param name
	 */
	public User(String id,String name)
	{
		this.id = id;
		this.name = name;
	}
	
	/**
	 * @param id
	 */
	public void setId(String id)
	{
		this.id = id;
	}
	
	/**
	 * @return
	 */
	public String getId()
	{
		return id;
	}
	
	/**
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * @param socket
	 */
	public void setSocket(Socket socket)
	{
		this.socket = socket;
	}
	/**
	 * @return
	 */
	public Socket getSocket()
	{
		return socket;
	}
	/**
	 * 向客户端输出信息，便于管理
	 * @return
	 */
	public PrintStream getPrintStream() {
		try {
			PrintStream ps = new PrintStream(this.socket.getOutputStream());
			return ps;
		} catch (Exception e) {
			return null;
		}
	}
}
