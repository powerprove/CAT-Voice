package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread
{
    ServerSocket serverSocket;
    ServerSocket voiceServerSocket;

    Server()
    {
        try
        {
            serverSocket = new ServerSocket(1004);
            voiceServerSocket = new ServerSocket(1111);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
}
