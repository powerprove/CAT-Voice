package Client;
class _roomInfo{
    String roomName;
    int headCount;
    String[] otherName = new String[100];
    String[] otherStausMSG = new String[100];
}


public class User
{
    _roomInfo[] roomInfo = new _roomInfo[100];
    String[] roomUserName = new String[101];
    String[] roomUserMSG = new String[101];

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
    public void ArraySetting(){
        for(int i=0; i<100; i++){
            roomInfo[i] = new _roomInfo();
        }
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
