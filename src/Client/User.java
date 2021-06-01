package Client;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

class _roomInfo{
    String roomName;
    int headCount;
    String[] otherName = new String[100];
    String[] otherStausMSG = new String[100];
}


public class User
{
    ClientHandler clienthandler = new ClientHandler();
    _roomInfo[] roomInfo = new _roomInfo[100];
    static Vector<String> roomUserName = new Vector<String>();
    static Vector<String> roomUserMSG = new Vector<String>();
    static Queue<String> SpeakerMark = new LinkedList<>();
    int roomUserIdx = clienthandler.RoomTotalpeople;
    static Queue<String> SpeakerMark = new LinkedList<>(); ///////////

    int roomid;
    static int roomcnt=0;
    private String nickName;
    private String statusMessage;
    
    public User(){
    }
    public User(String nickName,String statusMessage)
    {
        this.nickName = nickName;
        setStatusMessage(statusMessage);
    }

    public String getNickName()
    {
        return nickName;
    }

    public String getStatusMessage()
    {
        return statusMessage;
    }

    public int getRoomid(){return roomid;}

    public void setNickname(String nickName)
    {
        this.nickName = nickName;
    }
    
    public void setStatusMessage(String statusMessage)
    {
        this.statusMessage = statusMessage;
    }
    
    public void ArraySetting(){
        for(int i=0; i<100; i++){
            roomInfo[i] = new _roomInfo();
        }
    }
    
    public void getRoomInfo(String roomName) throws IOException {
        ClientHandler clientHandler = new ClientHandler();
        clientHandler.client.sendData("COMMANDSTART:GETROOMMEMBERLIST:"+clientHandler.user.getNickName()+":"+roomName+":END");
        System.out.println("COMMANDSTART:GETROOMMEMBERLIST:"+clientHandler.user.getNickName()+":"+roomName+":END");
    }

}
