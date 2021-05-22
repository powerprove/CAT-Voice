package Server.User;

import Server.Server;

public class User
{
    private String nickName;
    private String statusMessage;

    User()
    {
        nickName = null;
        statusMessage = null;
    }

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
