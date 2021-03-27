package Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class SocketCommand
{
    ServerSocket serverSocket;
    ServerSocket voiceServerSocket;
    Socket clientSocket;
    Socket clientVoiceSocket;

    public void execute(String ip) throws IOException
    {
        System.out.println("Socket start..!");
    }
}
