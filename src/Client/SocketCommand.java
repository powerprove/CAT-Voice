package Client;

import java.net.ServerSocket;

public abstract class SocketCommand
{
    ServerSocket serverSocket;
    ServerSocket voiceServerSocket;

    public void execute()
    {
        System.out.println("Socket start..!");
    }
}
