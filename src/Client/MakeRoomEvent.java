package Client;

import java.io.IOException;
import Client.LoginFrame;

public class MakeRoomEvent {
    private String roomName;
    private Client client;
    private User usr;
    public MakeRoomEvent(String text) {
        this.roomName = text;
    }

    public void sendMakeRoomData() throws IOException { // 서버로 방생성 정보 전달.
        User usr = new User(LoginFrame.name.getText(), LoginFrame.profile.getText());
        Client client = new Client(usr, LoginFrame.ipaddr.getText());
        System.out.println("Test1 : "+roomName);
        System.out.println("Test2 : "+ usr.getNickName());
        client.sendData("COMMANDSTART:CREATEROOM:"+usr.getNickName()+":"+roomName+":END");
    }
}
