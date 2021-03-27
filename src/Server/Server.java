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
            e.printStackTrace();
        }
    }

    public Socket SocketAccept(ServerSocket serverSocket)
    {
        Socket client = null;
        try
        {
            client = serverSocket.accept();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return client;
    }


    public void run()
    {
        while (true)
        {
            System.out.println("상대를 기다리는 중입니다.");
            Socket client = null;
            Socket voice = null;

            client = SocketAccept(serverSocket);

            System.out.println("Client accepted");
            System.out.println("wait for voice");

            voice = SocketAccept(voiceServerSocket);

            System.out.println("voice accepted");

        }
    }
}
