package chat;

import client.LoginFrame;

import commons.Game;
import commons.User;

/**
 * 聊天接口的实现
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

	public String toString()
	{
		return "Chat";
	}
}
