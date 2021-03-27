package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class SocketCommand
{
    public ServerSocket serverSocket;
    public ServerSocket voiceServerSocket;
    public Socket clientSocket;
    public Socket clientVoiceSocket;

    public void execute(String ip) throws IOException
    {
        System.out.println("Socket start..!");
    }

}
