package Client;

public class User
{
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

    public void setNickname(String nickName)
    {
        this.nickName = nickName;
    }
    
    public void setStatusMessage(String statusMessage)
    {
        this.statusMessage = statusMessage;
    }
}
