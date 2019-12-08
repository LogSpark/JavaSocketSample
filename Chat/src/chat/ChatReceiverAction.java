package chat;

import client.LoginFrame;
import commons.ClientAction;
import commons.ClientReceiveHandle;
import commons.User;

public class ChatReceiverAction implements ClientAction {

	@Override
	public void execute(ClientReceiveHandle crh) {
		// TODO Auto-generated method stub

//		String user = line.split("&:&")[0];
//		String msg = line.split("&:&")[1];
		ChatPanel.appendContent(crh.getSender().getName()+":"+"\n"+"    "+crh.getMessage());
		ChatPanel.setUserList();
		for(User u:LoginFrame.allUsers)
			System.out.println(u.getId()+u.getName());
//		HandleRequestContent hrc;
	}

}
