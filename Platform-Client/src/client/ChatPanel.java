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
 * 构建聊天室
 * @author win10
 *
 */
public class ChatPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户
	 */
	private User user;
	
	/**
	 * 接收消息者,默认为所有人
	 */
	private User receiver = new User("all","all");
	
//	static List<String> users;
	/**
	 * 所有用户
	 */
	private static List all;
	
	/**
	 * 聊天内容显示区域
	 */
	private static JTextArea textArea = new JTextArea(30, 10);
	
	/**
	 * 用户列表
	 */
	private static JList userList;

	/**
	 * 输入框
	 */
	private JTextField field = new JTextField(20);

	private JButton sendButton = new JButton("发送");

	/**
	 * 构建聊天界面
	 * @param user
	 * @param all
	 */
	public ChatPanel(User user,List all)
	{
		this.user = user;
		
		ChatPanel.all = all;
		
		sendLisenter();
//		显示区域，不可编辑
		textArea.setEditable(false);
		
		JScrollPane contentPane = new JScrollPane(textArea);
		JScrollPane userListPane = new JScrollPane(userList);
		contentPane.setMinimumSize(new Dimension(200, 300));
		userListPane.setMinimumSize(new Dimension(100, 75));
		
//		userListPane.add(userList);
//		输入框及发送键
		Box sendBox = Box.createHorizontalBox();
		sendBox.add(this.field);
		sendBox.add(this.sendButton);
//		整合
		Box contentBox = Box.createVerticalBox();
		contentBox.add(contentPane);
		contentBox.add(sendBox);
		
//		Box userListBox = Box.createVerticalBox();
//		拼接
		JSplitPane mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, userListPane,contentBox);
		
		this.add(mainPanel);
		this.setTitle("聊天室");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setResizable(false);
		this.setSize(550, 550);
		this.setLocation(600, 200);
		this.setVisible(true);
	}
	
	/**
	 * 发送按钮监听
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
	 * 显示在消息显示区域
	 * @param content 发送的消息
	 */
	public static void appendContent(String content) {
		if (textArea.getText().equals("")) 
			
			textArea.append(content);
		else 
			textArea.append("\n" + content);
	}
	
	/**
	 * 显示用户列表
	 */
	public static void setUserList() {
			
		userList = new JList<User>();
//		String[] strings = { "列表1", "列表2", "列表3", "列表4" };
//		userList.setListData(strings);
		userList.setFixedCellHeight(20);
		userList.setListData(all.toArray());
//		userList.setFixedCellHeight(20);
	}
	
	/**
	 * 发送消息
	 */
	public void send() 
	{
//		User selectUser = (User)this.userList.getSelectedValue();
//		if (selectUser == null) {
//			selectUser = this.receiver;
//		}
		
		if(this.field.getText()=="")
		{
			JOptionPane.showConfirmDialog(null, "不能发送空消息！", "提示", 
					JOptionPane.OK_CANCEL_OPTION);
			return;
		}
		
//		appendContent("我："+this.field.getText());
//		System.out.println(this.user);
		User newUser = new User(this.user.id,this.user.name);
		ClientRequestHandle cr = new ClientRequestHandle(newUser,this.field.getText(),this.receiver,"client.ChatReceiverAction");
		
		this.user.getPrintStream().println(XstreamUtil.toXML(cr));
		this.field.setText("");
	}
}
