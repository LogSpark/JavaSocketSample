package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import commons.Game;
import commons.User;
import exception.ClientException;

/**
 * 锟斤拷锟接达拷锟节ｏ拷一锟叫革拷源锟侥匡拷始
 * @author win10
 *
 */
public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;

	public static List<User> allUsers = new ArrayList<User>();
	// user name
	private JLabel nameTextLabel = new JLabel("锟矫伙拷锟斤拷锟斤拷");
	private JTextField nameField = new JTextField(20);

	// connection
	private JLabel connectionLabel = new JLabel("锟斤拷锟接碉拷址锟斤拷");
	private JTextField connectionField = new JTextField("127.0.0.1");
//	private JTextField connectionField = new JTextField("118.89.00.00");

	// select game
	private JLabel gameTextLabel = new JLabel("选锟斤拷锟斤拷戏锟斤拷");
	private JComboBox gameSelect = new JComboBox();
	// add game button
	private JButton addGame = new JButton("+");
	// 锟斤拷钮
	private JButton confirmButton = new JButton("确锟斤拷");
	private JButton cancelButton = new JButton("取锟斤拷");

	/**
	 * @throws InstantiationException 锟斤拷实锟斤拷锟斤拷锟届常
	 * @throws IllegalAccessException 锟斤拷锟斤拷权锟斤拷锟届常
	 * @throws ClassNotFoundException 锟洁不锟斤拷锟斤拷锟届常
	 */
	public LoginFrame() throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {

//		锟斤拷锟斤拷锟斤拷戏选锟斤拷锟斤拷锟斤拷锟斤拷
		buildGameSelect();
//		锟矫伙拷锟斤拷锟斤拷
		Box nameBox = Box.createHorizontalBox();
		nameBox.add(Box.createHorizontalStrut(30));
		nameBox.add(this.nameTextLabel);
		nameBox.add(Box.createHorizontalStrut(20));
		nameBox.add(this.nameField);
		nameBox.add(Box.createHorizontalStrut(30));
//		锟斤拷锟接碉拷址锟斤拷
		Box connectionBox = Box.createHorizontalBox();
		connectionBox.add(Box.createHorizontalStrut(30));
		connectionBox.add(this.connectionLabel);
		connectionBox.add(Box.createHorizontalStrut(7));
		connectionBox.add(this.connectionField);
		connectionBox.add(Box.createHorizontalStrut(30));
//		锟斤拷戏选锟斤拷锟斤拷
		Box gameBox = Box.createHorizontalBox();
		gameBox.add(Box.createHorizontalStrut(30));
		gameBox.add(this.gameTextLabel);
		gameBox.add(Box.createHorizontalStrut(6));
		gameBox.add(this.gameSelect);//锟斤拷戏选锟斤拷锟斤拷锟斤拷锟斤拷
		gameBox.add(Box.createHorizontalStrut(1));
		gameBox.add(this.addGame);//锟斤拷锟斤拷锟较�
		gameBox.add(Box.createHorizontalStrut(30));
//		锟斤拷钮
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(this.confirmButton);
		buttonBox.add(Box.createHorizontalStrut(30));
		buttonBox.add(this.cancelButton);
//		锟斤拷锟斤拷
		Box mainBox = Box.createVerticalBox();
		mainBox.add(Box.createVerticalStrut(20));
		mainBox.add(nameBox);
		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(connectionBox);
		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(gameBox);
		mainBox.add(Box.createVerticalStrut(20));
		mainBox.add(buttonBox);
		mainBox.add(Box.createVerticalStrut(20));

		this.add(mainBox);
		this.setLocation(300, 200);
		this.setTitle("锟斤拷锟斤拷");
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		initListeners();
		this.user = new User();
		// allUsers.add(this.user);
	}

	// 锟斤拷钮listener
	/**
	 * 锟斤拷钮锟铰硷拷锟斤拷锟斤拷
	 */
	private void initListeners() {
//		锟斤拷锟斤拷锟较凤拷锟斤拷锟�
		this.addGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					openDir();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
					JOptionPane.showConfirmDialog(null, "锟斤拷实锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷示",
							JOptionPane.OK_CANCEL_OPTION);
					return;
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
					JOptionPane.showConfirmDialog(null, "没锟叫凤拷锟斤拷权锟斤拷", "锟斤拷示",
							JOptionPane.OK_CANCEL_OPTION);
					return;
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showConfirmDialog(null, "没锟斤拷锟揭碉拷锟斤拷锟斤拷", "锟斤拷示",
							JOptionPane.OK_CANCEL_OPTION);
					return;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
//		确锟斤拷锟斤拷钮
		this.confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					login();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
//		取锟斤拷锟斤拷钮
		this.cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	/**
	 * 锟斤拷锟侥硷拷选锟斤拷锟斤拷
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException SQL锟斤拷锟斤拷锟届常
	 */
	private void openDir() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		int result = chooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION)
			;
//		锟斤拷取锟斤拷选锟侥硷拷路锟斤拷
		String path = chooser.getSelectedFile().getPath();
		System.out.println(path);
//		锟斤拷取锟斤拷选锟侥硷拷锟斤拷
		String name = chooser.getSelectedFile().getName();
		System.out.println(name);
//		锟斤拷锟斤拷要锟斤拷锟斤拷锟斤拷锟侥匡拷锟叫达拷锟絚lasspath(写锟斤拷锟斤拷锟�)
//		String willWrite = "<classpathentry combineaccessrules=\"false\" kind=\"src\" path=\"/wuziqi\"/>";
		
//		String subPath = path.substring(0,path.lastIndexOf("src"));
//		String dirNameTemp = subPath.substring(subPath.lastIndexOf("\\"));
//		String dirName = dirNameTemp.substring(dirNameTemp.lastIndexOf("\\"));
//		String classpath = subPath+".classpath";
//		File classpathFile = new File(classpath);
//		FileReader fr = new FileReader(classpathFile);
//		FileWriter fw = new FileWriter(classpathFile);
//		String reLine = "";
//		int lineNum = 0;
//		LineNumberReader r = new LineNumberReader(fr);
//		while(reLine!=null)
//		{
//			lineNum++;
//			reLine = r.readLine();
//			if(reLine=="<classpath>")
//			{
//				fw.write("\n<classpathentry combineaccessrules=\"false\" kind=\"src\" path=\"/"+dirName+"\"/>");
//				System.out.println("get!");
//				break;
//			}
//		}
//		r.close();
//		fr.close();
//		fw.close();
		
//		锟斤拷锟斤拷谩锟�.锟斤拷锟斤拷为锟街革拷锟侥伙拷,锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷写锟斤拷,String.split("\\."),锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷确锟侥分革拷锟斤拷,锟斤拷锟斤拷锟斤拷String.split(".");
//		锟斤拷.锟斤拷锟酵★拷|锟斤拷锟斤拷锟斤拷转锟斤拷锟街凤拷,锟斤拷锟斤拷眉锟�"\\";
//		String firstName = name.split("\\.")[0];
		String caselsh = name.substring(0,name.lastIndexOf("."));
		System.out.println(caselsh);
//		String endName = name.split("\\.")[1];
//		System.out.println(firstName);
		
//		锟侥硷拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟揭伙拷锟�
		File file = new File(path);
		FileReader fileReader = new FileReader(file);
		String firstLine = "";
		LineNumberReader reader = new LineNumberReader(fileReader);  
		firstLine = reader.readLine();
		System.out.println(firstLine);
		reader.close();
        fileReader.close();
//      锟斤拷锟侥硷拷锟斤拷一锟叫伙拷取锟斤拷锟斤拷/锟斤拷一锟叫憋拷锟斤拷为锟斤拷位锟斤拷//  锟脚伙拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷式锟叫讹拷
        String packageName = firstLine.split(" ")[1];
        packageName = packageName.substring(0, packageName.length()-1);
        System.out.println(packageName);
//      拼锟接帮拷锟斤拷锟斤拷锟斤拷锟斤拷
        String className = packageName+"."+caselsh;
        System.out.println(className);
		// Desktop.getDesktop().open(file);
//		Game game = (Game) Class.forName(name).newInstance();
//		this.gameSelect.addItem(game);
//		锟斤拷锟斤拷MySQL
		java.sql.Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/mydb", "root",
				"123456");
//		使锟斤拷MySQL锟斤拷锟�
		java.sql.Statement state = connection.createStatement();
//		state.executeUpdate("if not exists(select * from games_class where game_classname = '"+className+"')" +
//				"begin" +
//				"insert into games_class (game_classname) value ('"+className+"')" +
//				"end");
//		state.executeUpdate("INSERT INTO games_class (game_classname) SELECT ('"+className+"') WHERE NOT EXIST(select * from games_class where games_class.game_classname = '"+className+"')");
//		锟斤拷取锟斤拷选锟斤拷锟捷硷拷
		ResultSet resultSet = state.executeQuery("select game_classname from games_class");
//		while(resultSet.next())
//		{
//			if(resultSet.getString(1)==className)
//		锟斤拷锟斤拷锟斤拷锟捷ｏ拷锟斤拷始锟斤拷戏锟接匡拷实锟斤拷锟洁）
				state.executeUpdate("insert into games_class (game_classname) value ('"+className+"')");
//		}
		
		
//		锟斤拷映晒锟斤拷锟绞撅拷锟�
		JOptionPane.showConfirmDialog(null, "锟窖硷拷锟斤拷锟斤拷戏锟叫憋拷\n锟斤拷锟斤拷锟斤拷锟斤拷锟侥�", "锟斤拷示",
				JOptionPane.OK_CANCEL_OPTION);
//		锟斤拷锟斤拷锟斤拷戏锟斤拷锟斤拷锟斤拷
//		connectSQL();
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷锟斤拷
	 * @throws IOException
	 */
	private void login() throws IOException {
		if (this.nameField.getText().equals("")) {
			JOptionPane.showConfirmDialog(null, "锟斤拷锟斤拷锟斤拷锟矫伙拷锟斤拷", "锟斤拷示",
					JOptionPane.OK_CANCEL_OPTION);
			return;
		}
		// 锟斤拷锟斤拷User锟侥革拷锟斤拷锟斤拷锟斤拷
		setUser();
//		锟斤拷锟斤拷锟斤拷锟斤拷
		this.user.setSocket(createSocket(this.connectionField.getText(), 2888));
//鎴戠殑8888绔彛鍗犵敤鐫�锛屾墍浠ユ敼涓�涓鍙�
		// BufferedReader br = new BufferedReader(new
		// InputStreamReader(this.user.getSocket().getInputStream()));
		// String line = br.readLine();
		// System.out.println("come from:" + line);

		// br.close();
		// 锟斤拷取锟斤拷选锟斤拷戏
		Game game = (Game) this.gameSelect.getSelectedItem();
		game.start(this.user);
		// 锟斤拷锟斤拷锟竭筹拷
		// ClientThread thread = new ClientThread(this.user);
		// thread.start();
//		锟斤拷锟斤拷锟竭筹拷
		ClientThread ct = new ClientThread(this.user);
		ct.start();
//		锟斤拷锟斤拷锟斤拷锟�
		// new ChatPanel(user);

		this.setVisible(false);
	}

	/**
	 * 锟斤拷锟斤拷Socket
	 * @param address 锟斤拷锟斤拷IP
	 * @param port 锟剿口号ｏ拷默锟斤拷8888
	 * @return
	 */
	private Socket createSocket(String address, int port) {
		try {
			// 锟斤拷锟斤拷Socket
			return new Socket(address, port);
		} catch (Exception e) {
			throw new ClientException(e.getMessage());
		}
	}

	/**
	 * 锟斤拷始锟斤拷User
	 */
	public void setUser() {
		// int sex = 0;
//		锟斤拷锟斤拷唯一ID
		String id = UUID.randomUUID().toString();
//		锟斤拷取锟矫伙拷锟斤拷
		String name = this.nameField.getText();
		this.user.setId(id);
		this.user.setName(name);
		// this.user.setSex(sex);
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷戏锟斤拷锟斤拷锟斤拷
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	private void buildGameSelect() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
//		锟斤拷锟斤拷锟斤拷锟捷匡拷锟饺★拷锟较凤拷锟斤拷锟�
		connectSQL();
		// Game game = (Game) Class.forName("client.ChatStart").newInstance();
		// this.gameSelect.addItem(game);
	}

	private void connectSQL() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		try {
			// Class.forName("com.mysql.jdbc.Driver");
//			锟斤拷锟斤拷MySQL
			java.sql.Connection connection = DriverManager.getConnection(
					"jdbc:mysql://118.89.41.161:3306/mydb", "root",
					"Dream&&2018");
			java.sql.Statement state = connection.createStatement();
			// String s="insert into xs values('008','锟斤拷锟斤拷','锟斤拷锟斤拷')";
			// state.executeUpdate(s);
			ResultSet rs = state.executeQuery("select * from games_class");
			while (rs.next()) {
				// System.out.println(rs.getInt(1)+"???"+rs.getString(2));
				Game game = (Game) Class.forName(rs.getString(2)).newInstance();
				this.gameSelect.addItem(game);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
