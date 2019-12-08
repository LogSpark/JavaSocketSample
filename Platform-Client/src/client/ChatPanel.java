package client;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import commons.ClientRequestHandle;
import commons.User;
import util.XstreamUtil;

/**
 * ����������
 * @author win10
 *
 */
public class ChatPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * �û�
	 */
	private User user;
	
	/**
	 * ������Ϣ��,Ĭ��Ϊ������
	 */
	private User receiver = new User("all","all");
	
//	static List<String> users;
	/**
	 * �����û�
	 */
	private static List all;
	
	/**
	 * ����������ʾ����
	 */
	private static JTextArea textArea = new JTextArea(30, 10);
	
	/**
	 * �û��б�
	 */
	private static JList userList;

	/**
	 * �����
	 */
	private JTextField field = new JTextField(20);

	private JButton sendButton = new JButton("����");

	/**
	 * �����������
	 * @param user
	 * @param all
	 */
	public ChatPanel(User user,List all)
	{
		this.user = user;
		
		ChatPanel.all = all;
		
		sendLisenter();
//		��ʾ���򣬲��ɱ༭
		textArea.setEditable(false);
		
		JScrollPane contentPane = new JScrollPane(textArea);
		JScrollPane userListPane = new JScrollPane(userList);
		contentPane.setMinimumSize(new Dimension(200, 300));
		userListPane.setMinimumSize(new Dimension(100, 75));
		
//		userListPane.add(userList);
//		����򼰷��ͼ�
		Box sendBox = Box.createHorizontalBox();
		sendBox.add(this.field);
		sendBox.add(this.sendButton);
//		����
		Box contentBox = Box.createVerticalBox();
		contentBox.add(contentPane);
		contentBox.add(sendBox);
		
//		Box userListBox = Box.createVerticalBox();
//		ƴ��
		JSplitPane mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, userListPane,contentBox);
		
		this.add(mainPanel);
		this.setTitle("������");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setResizable(false);
		this.setSize(550, 550);
		this.setLocation(600, 200);
		this.setVisible(true);
	}
	
	/**
	 * ���Ͱ�ť����
	 */
	private	void sendLisenter()
	{
		this.sendButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				send();
			}
		});
	}
	
	/**
	 * ��ʾ����Ϣ��ʾ����
	 * @param content ���͵���Ϣ
	 */
	public static void appendContent(String content) {
		if (textArea.getText().equals("")) 
			
			textArea.append(content);
		else 
			textArea.append("\n" + content);
	}
	
	/**
	 * ��ʾ�û��б�
	 */
	public static void setUserList() {
			
		userList = new JList<User>();
//		String[] strings = { "�б�1", "�б�2", "�б�3", "�б�4" };
//		userList.setListData(strings);
		userList.setFixedCellHeight(20);
		userList.setListData(all.toArray());
//		userList.setFixedCellHeight(20);
	}
	
	/**
	 * ������Ϣ
	 */
	public void send() 
	{
//		User selectUser = (User)this.userList.getSelectedValue();
//		if (selectUser == null) {
//			selectUser = this.receiver;
//		}
		
		if(this.field.getText()=="")
		{
			JOptionPane.showConfirmDialog(null, "���ܷ��Ϳ���Ϣ��", "��ʾ", 
					JOptionPane.OK_CANCEL_OPTION);
			return;
		}
		
//		appendContent("�ң�"+this.field.getText());
//		System.out.println(this.user);
		User newUser = new User(this.user.id,this.user.name);
		ClientRequestHandle cr = new ClientRequestHandle(newUser,this.field.getText(),this.receiver,"client.ChatReceiverAction");
		
		this.user.getPrintStream().println(XstreamUtil.toXML(cr));
		this.field.setText("");
	}
}
