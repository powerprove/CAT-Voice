package Client;
class _roomInfo{
    String roomName;
    int headCount;
}


public class User
{
    _roomInfo[] roomInfo = new _roomInfo[100];
    int roomCnt;

    String[] roomUserName = new String[101];
    String[] roomUserMSG = new String[101];

    int roomid;
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

}
