package commons;

import java.io.PrintStream;
import java.net.Socket;

/**
 * �û�����
 * @author win10
 *
 */
public class User {
//	user ID
	public String id;
//	�û���
	public String name;
////	�Ա�  �У�0��Ů��1
//	private int sex;
//	user��Ӧ��socket
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
	 * ��ͻ��������Ϣ�����ڹ���
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
