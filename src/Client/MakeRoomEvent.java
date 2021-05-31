package Client;

import java.io.IOException;
import Client.LoginFrame;
import static Client.LoginFrame.name;

public class MakeRoomEvent {
    private String roomName;
    private Client client;
    private User usr;
    public MakeRoomEvent(String text) {
        this.roomName = text;
    }

    public void sendMakeRoomData() throws IOException { // 서버로 방생성 정보 전달.
      ClientHandler clientHandler = new ClientHandler();
      clientHandler.client.sendData("COMMANDSTART:CREATEROOM:"+clientHandler.user.getNickName()+":"+roomName+":END");
    }
}
