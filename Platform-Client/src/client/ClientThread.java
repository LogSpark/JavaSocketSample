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
 * �ͻ����̣߳����ܷ�������Ϣ
 * @author win10
 *
 */
public class ClientThread extends Thread {
	
	User user;
//	�յ�����Ϣ
	String line;
	
	/**
	 * @param user �ͻ����û�
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
//				����������Ϣ������
				ClientReceiveHandle crh = getContent(line);
//				�ҵ��ÿͻ��˵Ķ�������ӿ�ʵ����
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
	 * �����յ�����Ϣ(XML)ת��Ϊ��Ϣ�������
	 * @param line ���յ�����Ϣ
	 * @return
	 */
	public ClientReceiveHandle getContent(String line)
	{
		XStream xStream = new XStream(new DomDriver());
		XStream.setupDefaultSecurity(xStream);
		xStream.allowTypes(new Class[]{ClientRequestHandle.class, ClientReceiveHandle.class}); // Ҫ��������
		xStream.alias("commons.ClientRequestHandle", commons.ClientReceiveHandle.class);
		ClientReceiveHandle h = (ClientReceiveHandle) xStream.fromXML(line);
		return h;
	}
}
