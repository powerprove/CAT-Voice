package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
    private SocketCommand socketCommand;
    private User myUser, anotherUser;
    private String ip;
    private DataOutputStream voiceOut, dataOut;
    private DataInputStream voiceIn, dataIn;
    private VoiceSender voiceSender;
    private VoiceReciever voiceReciever;
    private DataReciever dataReciever;

    public Client(User myUser, String ip) throws IOException
    {
        this.myUser = myUser;
        this.anotherUser = new User("", "");
        this.ip = ip;
        setSocketCommand();
        setDataStream();
    }

    public void setSocketCommand() throws IOException
    {

        if ( ip.equals("0.0.0.0") || ip.equals("localhost"))
        {
            socketCommand = new ListenCommand();
        }
        else
        {
            socketCommand = new ConnectCommand();
        }

        socketCommand.execute(ip);
    }

    public void setDataStream() throws IOException
    {
        dataIn = new DataInputStream(socketCommand.clientSocket.getInputStream());
        dataOut = new DataOutputStream(socketCommand.clientSocket.getOutputStream());
        voiceOut = new DataOutputStream(socketCommand.clientVoiceSocket.getOutputStream());
        voiceIn = new DataInputStream(socketCommand.clientVoiceSocket.getInputStream());
    }

    public void startCall() throws IOException
    {
        startData();
        sendData("SETNAME:" + myUser.getNickName() + ":END");
        sendData("SETSTATUS:" + myUser.getStatusMessage() + ":END");
        startVoice();
    }

    public void startVoice()
    {
        voiceSender = new VoiceSender(voiceOut);
        voiceReciever = new VoiceReciever(voiceIn);
    }

    public int CallCheck()
    {
       while (getAnotherUserName().equals(""))
       {
       }
       return 1;
    }

    public void startData()
    {
        dataReciever = new DataReciever(dataIn, this);
    }

    public void sendData(String data) throws IOException
    {
        dataOut.writeUTF(data);
        dataOut.flush();
    }

    public String getMyUserName()
    {
        return myUser.getNickName();
    }

    public String getMyUserStatus()
    {
        return myUser.getStatusMessage();
    }

    public void setMyUserStatus(String Status)
    {
        myUser.setStatusMessage(Status);
        try {
            sendData("SETSTATUS:" + myUser.getStatusMessage() + ":END");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAnotherUserName()
    {
        return anotherUser.getNickName();
    }

    public String getAnotherUserStatus()
    {
        return anotherUser.getStatusMessage();
    }

    public void setAnotherUserName(String name)
    {
        anotherUser.setNickName(name);
    }

    public void setAnotherUserStatus(String status)
    {
        anotherUser.setStatusMessage(status);
    }


}