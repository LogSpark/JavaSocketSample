package client;

import commons.ClientAction;
import commons.ClientReceiveHandle;
import commons.User;

/**
 * 客户端接收消息后的动作处理的实现类
 * @author win10
 *
 */
public class ChatReceiverAction implements ClientAction {

	@Override
	public void execute(ClientReceiveHandle crh) {
		// TODO Auto-generated method stub

//		String user = line.split("&:&")[0];
//		String msg = line.split("&:&")[1];
//		显示信息
		ChatPanel.appendContent(crh.getSender().getName()+":"+"\n"+"    "+crh.getMessage());
//		显示用户列表
		ChatPanel.setUserList();
		for(User u:LoginFrame.allUsers)
			System.out.println(u.getId()+u.getName());
//		HandleRequestContent hrc;
	}

}
