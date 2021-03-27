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

    public Client(User myUser, String ip) throws UnknownHostException, IOException
    {
        this.myUser = myUser;
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
        sendData("USERNICKNAME:" + myUser.getNickName() + ":END");
        sendData("USERSETSTATUS:" + myUser.getStatusMessage() + ":END");
        startVoice();
    }

    public void startVoice()
    {
        voiceSender = new VoiceSender(voiceOut);
        voiceReciever = new VoiceReciever(voiceIn);
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

}