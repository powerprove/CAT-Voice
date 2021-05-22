package Client;

import java.io.IOException;

public class MakeRoomEvent extends User{
    private String roomName;
    private Client client;
    private User usr;
    public MakeRoomEvent(String text) {
        this.roomName = text;
    }

    public void sendMakeRoomData() throws IOException { // 서버로 방생성 정보 전달.

        client.sendData("COMMANDSTART:CREATEROOM:"+getNickName()+":"+roomName+":END");
    }

}
