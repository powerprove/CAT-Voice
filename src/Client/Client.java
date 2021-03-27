package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
    private Socket ServerSocket, VoiceSocket;
    private SocketCommand socketCommand;
    private User myUser;
    private String ip;
    private DataOutputStream voiceOut, out;
    private DataInputStream voiceIn, in;

    public Client(User myUser, String ip) throws UnknownHostException, IOException {
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
        in = new DataInputStream(socketCommand.clientSocket.getInputStream());
        out = new DataOutputStream(socketCommand.clientSocket.getOutputStream());
        voiceOut = new DataOutputStream(socketCommand.clientVoiceSocket.getOutputStream());
        voiceIn = new DataInputStream(socketCommand.clientVoiceSocket.getInputStream());
    }

}