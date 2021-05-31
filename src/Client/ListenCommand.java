package Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ListenCommand extends SocketCommand
{
    // 무 쓸모인듯~!!!!!!!!!!!!!!!!!!!!!!!!
    ListenCommand()
    {
        try {
            serverSocket = new ServerSocket(8282);
            voiceServerSocket = new ServerSocket(8383);
        } catch (IOException e) {
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

    @Override
    public void execute(String ip) throws IOException
    {
        System.out.println("SocketCommand => Listen");
        System.out.println("상대를 기다리는 중입니다.");

        clientSocket = SocketAccept(serverSocket);

        System.out.println("Client accepted");
        System.out.println("wait for voice");

        clientVoiceSocket = SocketAccept(voiceServerSocket);
        System.out.println("voice accepted");

    }
}
