package Client;

public class User
{
    String[] roomUserName = new String[101];
    String[] roomUserMSG = new String[101];
    int roomid;
    private String nickName;
    private String statusMessage;
    User(String nickName,String statusMessage)
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
