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
 * �����ҽ��湹��
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
	 * ����������ʾ����
	 */
	private static JTextArea textArea = new JTextArea(30, 10);
	/**
	 * �û��б�
	 */
	private static JList userList;
	/**
	 * �����ı���
	 */
	private JTextField field = new JTextField(20);
    
	private JButton sendButton = new JButton("����");

	//������ʾ����ͱ�����ɫ
	Font fnt = new Font("����",Font.BOLD,15);
	Font fnt1 = new Font("�����п�",Font.BOLD,15);
	Color col = new Color(138,199,251);
	Color col1 = new Color(240,248,255);
	
	/**
	 * ��Ҫ�����������
	 * @param user
	 * @param all
	 */
	public ChatPanel(User user,List<User> all)
	{
		this.user = user;
		
		this.all = all;
		//���Ͱ�ť����
		sendLisenter();
		//�ı����򲻿ɱ༭
		textArea.setEditable(false);
		
        //���Ĭ��JFrame�߿�		
		this.setUndecorated(true);
		
		// ����ָ���Ĵ���װ�η��
//		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		//����JScrollPane����װ�ı���
		JScrollPane contentPane = new JScrollPane(textArea);
		JScrollPane userListPane = new JScrollPane(userList);
		contentPane.setMinimumSize(new Dimension(200, 300));
		userListPane.setMinimumSize(new Dimension(100, 75));
		
		//Ϊ�û���ʾ�������ñ߿�
		userListPane.setBorder(BorderFactory.createTitledBorder("����"));
		
//		userListPane.add(userList);
		
		//��ʽ����
		Box sendBox = Box.createHorizontalBox();
		sendBox.add(this.field);
		sendBox.add(this.sendButton);
		
		//�����ı�������Ϊ����
		field.setFont(fnt);
		textArea.setFont(fnt);
		userListPane.setFont(fnt1);
		
		Box contentBox = Box.createVerticalBox();
		contentBox.add(contentPane);
		contentBox.add(sendBox);
		contentBox.setBorder(BorderFactory.createTitledBorder("������"));
		
//		Box userListBox = Box.createVerticalBox();
//		field.setOpaque(false);
		//�����ı���͸��
		textArea.setOpaque(false);
		
		//��������ָ�
		JSplitPane mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, userListPane,contentBox);
		
		//��ӹرմ��ڰ�ť
		JButton closeButton = new JButton();
		this.add(closeButton,BorderLayout.NORTH);
		closeButton.setBorder(null);
		closeButton.setIcon(new ImageIcon("image/11.jpg"));
//		closeButton.setToolTipText("�ر�");
//		closeButton.setBackground(col1);
//		closeButton.setHorizontalAlignment(JButton.RIGHT); 
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//���ô�����ɫ
		mainPanel.setDividerSize(1);
		mainPanel.isOpaque();
		userListPane.getViewport().setBackground(col);
		contentPane.getViewport().setBackground(col1);
		
		this.add(mainPanel);
		this.setTitle("������");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//����Ӧ
		this.pack();
		//���ɸ��Ĵ�С
		this.setResizable(false);
		this.setSize(550, 550);
		this.setLocation(600, 200);
		this.setVisible(true);
	}
	
	
	/**
	 * ���Ͱ�ť���¼�����
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
	 * ���촰����ʾ����
	 * @param content
	 */
	public static void appendContent(String content) {
		if (textArea.getText().equals("")) 
			
			textArea.append(content);
		else 
			textArea.append("\n" + content);
	}
	
	/**
	 * �����û��б�
	 */
	public static void setUserList() {
			
		userList = new JList();
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