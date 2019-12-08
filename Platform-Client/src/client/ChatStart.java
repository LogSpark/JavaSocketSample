package client;

import commons.Game;
import commons.User;

/**
 * 聊天室开始接口的实现类
 * @author lndx
 *
 */
public class ChatStart implements Game {

	/* (non-Javadoc)
	 * @see commons.Game#start(commons.User)
	 */
	@Override
	public void start(User user) {
		// TODO Auto-generated method stub

		LoginFrame.allUsers.add(user);
		new ChatPanel(user, LoginFrame.allUsers);
	}
//	toString方法重写，游戏名字
	public String toString()
	{
		return "Chat";
	}
}
