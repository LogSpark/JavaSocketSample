package server;

//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import commons.ServerIdUser;

/**
 * Server������
 * @author win10
 *
 */
public class Server {
	
	/**
	 * �������˵ȴ�socket����
	 */
	ServerSocket serverSocket;
	
	/**
	 * �������ӵ�socket
	 */
	public static List<Socket> socketList = Collections.synchronizedList(new ArrayList<Socket>());
	
	/**
	 * �ͻ��˱�����û�ID��socket���������б�
	 */
	public static List<ServerIdUser> idSocket = new ArrayList<ServerIdUser>();
	
	/**
	 * Server������
	 * @throws IOException
	 */
	public Server() 
	throws IOException
	{
		this.serverSocket = new ServerSocket(2888);
//		�ȴ��ͻ�������
		while(true)
		{
			System.out.println("wait for connection.");
//			�����ͻ�������
			Socket s = this.serverSocket.accept();
//			�����������ӵ�socket�б�
			socketList.add(s);
//				PrintStream ps = new PrintStream(s.getOutputStream());
			
//				ps.println("server");
			
//				InputStream is = s.getInputStream();
//				BufferedReader br = new BufferedReader(new InputStreamReader(is));
//			Ϊsocket�����߳�
			new ServerThread(s).start();
//				ps.close();
//				s.close();
		}
	}
}
