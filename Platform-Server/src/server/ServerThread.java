package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import commons.ClientRequestHandle;
import commons.ServerHandle;
import commons.ServerIdUser;

/**
 * Server线程
 * @author win10
 *
 */
public class ServerThread extends Thread {
	
	/**
	 * 连接的客户端socket
	 */
	private Socket s;
	
	/**
	 * 数据读入
	 */
	private BufferedReader br;
	
	/**
	 * @param s 连接的客户端socket
	 * @throws IOException
	 */
	public ServerThread(Socket s)
	throws IOException
	{
		this.s = s;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run()
	{
		try {
//			收到的消息
			String line = null;
			while((line = readFromLine())!=null)
			{
//				for(Socket s:Server.socketList)
//				{
//					PrintStream ps = new PrintStream(s.getOutputStream());
//					ps.println(line);
//				}
				System.out.println(line);
//				将消息转化为服务器消息处理对象
				ServerHandle hrc = getContent(line);
//				System.out.println(hrc.getReceiver());
//				if(hrc.getReceiver()!="all"||hrc.getReceiver()!="")
//					ServerResponse.idUser.put(s, hrc.getReceiver());
				
//				hrc.socketSender.put(s, hrc.getSender());
//				for(Socket keySocket:hrc.socketSender.keySet())
//				{
//					System.out.println(s+"\n"+hrc.getSender());
//				}
				
				ServerIdUser siu = new ServerIdUser(hrc.getSender().id, s);
				System.out.println(hrc.getSender().id+" "+s);
				Server.idSocket.add(siu);
//				System.out.println("???");
				sendMessage(hrc,line);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 读入字符串处理如果连接异常,移除该socket
	 * @return
	 */
	private String readFromLine()
	{
		try {
			return br.readLine();
		} catch (IOException e) {
			// TODO: handle exception
//			从所有列表里移除
			Server.socketList.remove(s);
		}
		return null;
	}
	
	/**
	 * 将客户端发来的xml字符串转化为消息处理对象
	 * @param line
	 * @return
	 */
	public ServerHandle getContent(String line)
	{
//	XStream.alias("commons.ClientRequest", HandleRequestContent.class);
		XStream xStream = new XStream(new DomDriver());
		XStream.setupDefaultSecurity(xStream);
		xStream.allowTypes(new Class[]{ClientRequestHandle.class, ServerHandle.class}); // 要关联的类
		xStream.alias("commons.ClientRequestHandle", commons.ServerHandle.class);
		ServerHandle h = (ServerHandle) xStream.fromXML(line);
//	return (HandleRequestContent) XstreamUtil.fromXML(line);
		return h;
//		try {
////			XStream.alias("commons.ClientRequest", HandleRequestContent.class);
//			HandleRequestContent h = (HandleRequestContent) new XStream(new DomDriver()).fromXML(line);
////			return (HandleRequestContent) XstreamUtil.fromXML(line);
//			return h;
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return null;
//		}
	}
//	public void sendMessage(HandleRequestContent hrc) 
//		throws IOException
//	{
////		群聊/私聊
//		Map<String, Socket> receiverSocket = new HashMap<String, Socket>();
//		receiverSocket.put(hrc.getReceiver().getId(),s);
//		if(hrc.getReceiver().getId()=="all")
////			sendMessage(hrc);
//		{
//			for(Socket s:Server.socketList)
//			{
//				PrintStream ps = new PrintStream(s.getOutputStream());
//				ps.println(hrc.sender.getName()+"&:&"+hrc.getMessage());
//			}
//		}
//		else
//		{
////			socketReceiver.put(s, hrc.getReceiver());
//			Socket value = new Socket("127.0.0.1",8888);
//			for(String key : receiverSocket.keySet()){
//				   value = receiverSocket.get(key);
//				   System.out.println(key+"  "+value);
//				  }
//			PrintStream ps = new PrintStream(value.getOutputStream());
////			ps.println(hrc.getMessage());
//			ps.println(hrc.sender.getName()+"&:&"+hrc.getMessage());
//		}
//	}
	
	/**
	 * 将消息发送给客户端
	 * @param hrc Server消息处理对象
	 * @param line xml字符串
	 * @throws IOException
	 */
	public void sendMessage(ServerHandle hrc,String line) 
		throws IOException
		{
		System.out.println(hrc.getReceiver().getId()+"+"+hrc.getReceiver().id);
//		String receiverId = hrc.getReceiver().getId();
//		if(hrc.getReceiver().getId()=="all")
//		接收者为all时,发给所有socket列表
		if(hrc.getReceiver().getId().equalsIgnoreCase("all"))
		{
//			System.out.println("what");
			for(Socket socketTemp:Server.socketList)
			{
//				打开socket输出流
				PrintStream ps = new PrintStream(socketTemp.getOutputStream());
//				ps.println(hrc.sender.getName()+"&:&"+hrc.getMessage());
				ps.println(line);
			}
		}
		else
		{
//			System.out.println("...");
//			指定接收者发送消息
			for(ServerIdUser keyTemp:Server.idSocket)
			{
//				if(hrc.getReceiver().getId()==keyTemp.getId())
				if(hrc.getReceiver().getId().equalsIgnoreCase("all"))
				{
					PrintStream ps = new PrintStream(keyTemp.getSocket().getOutputStream());
//					ps.println(hrc.sender.getName()+"&:&"+hrc.getMessage());
//					System.out.println("2");
					ps.println(line);
				}
			}
		}
		}

}
