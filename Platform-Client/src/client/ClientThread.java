package client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import commons.ClientAction;
import commons.ClientReceiveHandle;
import commons.ClientRequestHandle;
import commons.User;

/**
 * 客户端线程，接受服务器消息
 * @author win10
 *
 */
public class ClientThread extends Thread {
	
	User user;
//	收到的消息
	String line;
	
	/**
	 * @param user 客户端用户
	 */
	public ClientThread(User user)
	{
		this.user = user;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run()
	{
		try {
			
			InputStream is = this.user.getSocket().getInputStream();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			while((this.line = br.readLine())!=null)
			{
//				System.out.println("Come From:"+line);
//				this.line = br.readLine();
//				System.out.println(this.line);
//				String user = line.split("&:&")[0];
//				String msg = line.split("&:&")[1];
//				传给接收消息处理类
				ClientReceiveHandle crh = getContent(line);
//				找到该客户端的动作处理接口实现类
				ClientAction action = (ClientAction)Class.forName(crh.getActionHandleClass()).newInstance();
				action.execute(crh);
//				ChatPanel.appendContent(this.line);
				
//				br.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将接收到的消息(XML)转化为消息处理对象
	 * @param line 接收到的消息
	 * @return
	 */
	public ClientReceiveHandle getContent(String line)
	{
		XStream xStream = new XStream(new DomDriver());
		XStream.setupDefaultSecurity(xStream);
		xStream.allowTypes(new Class[]{ClientRequestHandle.class, ClientReceiveHandle.class}); // 要关联的类
		xStream.alias("commons.ClientRequestHandle", commons.ClientReceiveHandle.class);
		ClientReceiveHandle h = (ClientReceiveHandle) xStream.fromXML(line);
		return h;
	}
}
