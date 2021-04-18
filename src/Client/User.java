package Client;

public class User
{
    private String nickName;
    private String statusMessage;
    UserInfo usrInfo = new UserInfo();
    User(String nickName,String statusMessage)
    {
        usrInfo.nickName= nickName;
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

    public void setNickname(String nickName)
    {
        this.nickName = nickName;
    }
    
    public void setStatusMessage(String statusMessage)
    {
        this.statusMessage = statusMessage;
    }
}
