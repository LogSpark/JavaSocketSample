package chat; 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.security.auth.Refreshable;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;

import util.XstreamUtil;

import commons.ClientRequestHandle;
import commons.User;

/**
 * 聊天室界面构建
 * @author win10
 *
 */
public class ChatPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	
	private User receiver = new User("all","all");
	
//	static List<String> users;
	private static List<User> all;
	/**
	 * 聊天内容显示区域
	 */
	private static JTextArea textArea = new JTextArea(30, 10);
	/**
	 * 用户列表
	 */
	private static JList userList;
	/**
	 * 输入文本框
	 */
	private JTextField field = new JTextField(20);
    
	private JButton sendButton = new JButton("发送");

	//设置显示字体和背景颜色
	Font fnt = new Font("楷体",Font.BOLD,15);
	Font fnt1 = new Font("华文行楷",Font.BOLD,15);
	Color col = new Color(138,199,251);
	Color col1 = new Color(240,248,255);
	
	/**
	 * 主要构建聊天界面
	 * @param user
	 * @param all
	 */
	public ChatPanel(User user,List<User> all)
	{
		this.user = user;
		
		this.all = all;
		//发送按钮监听
		sendLisenter();
		//文本区域不可编辑
		textArea.setEditable(false);
		
        //清除默认JFrame边框		
		this.setUndecorated(true);
		
		// 采用指定的窗口装饰风格
//		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		//建立JScrollPane面板封装文本框
		JScrollPane contentPane = new JScrollPane(textArea);
		JScrollPane userListPane = new JScrollPane(userList);
		contentPane.setMinimumSize(new Dimension(200, 300));
		userListPane.setMinimumSize(new Dimension(100, 75));
		
		//为用户显示区域设置边框
		userListPane.setBorder(BorderFactory.createTitledBorder("好友"));
		
//		userListPane.add(userList);
		
		//箱式布局
		Box sendBox = Box.createHorizontalBox();
		sendBox.add(this.field);
		sendBox.add(this.sendButton);
		
		//设置文本框字体为楷体
		field.setFont(fnt);
		textArea.setFont(fnt);
		userListPane.setFont(fnt1);
		
		Box contentBox = Box.createVerticalBox();
		contentBox.add(contentPane);
		contentBox.add(sendBox);
		contentBox.setBorder(BorderFactory.createTitledBorder("聊天室"));
		
//		Box userListBox = Box.createVerticalBox();
//		field.setOpaque(false);
		//设置文本区透明
		textArea.setOpaque(false);
		
		//设置组件分割
		JSplitPane mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, userListPane,contentBox);
		
		//添加关闭窗口按钮
		JButton closeButton = new JButton();
		this.add(closeButton,BorderLayout.NORTH);
		closeButton.setBorder(null);
		closeButton.setIcon(new ImageIcon("image/11.jpg"));
//		closeButton.setToolTipText("关闭");
//		closeButton.setBackground(col1);
//		closeButton.setHorizontalAlignment(JButton.RIGHT); 
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//设置窗体颜色
		mainPanel.setDividerSize(1);
		mainPanel.isOpaque();
		userListPane.getViewport().setBackground(col);
		contentPane.getViewport().setBackground(col1);
		
		this.add(mainPanel);
		this.setTitle("聊天室");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//自适应
		this.pack();
		//不可更改大小
		this.setResizable(false);
		this.setSize(550, 550);
		this.setLocation(600, 200);
		this.setVisible(true);
	}
	
	
	/**
	 * 发送按钮的事件监听
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
	 * 聊天窗口显示内容
	 * @param content
	 */
	public static void appendContent(String content) {
		if (textArea.getText().equals("")) 
			
			textArea.append(content);
		else 
			textArea.append("\n" + content);
	}
	
	/**
	 * 更新用户列表
	 */
	public static void setUserList() {
			
		userList = new JList();
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