package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread
{
    //static ArrayList<Room> room = new ArrayList<>();
    static Room room;
    public ServerSocket serverSocket;
    public ServerSocket vServerSocket;

    public Server()
    {
        OpenSocket();
    }

    // Socket open
    public void OpenSocket()
    {
        try
        {
            serverSocket = new ServerSocket(8282);
            vServerSocket = new ServerSocket(8383);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // Waiting Client and accept
    public Socket AcceptSocket(ServerSocket server)
    {
        System.out.println("[SERVER] WAITING CLIENT");
        Socket member = null;
        try
        {
            member = server.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return member;
    }

    public void addMember(Socket member, Socket vmember) throws IOException
    {
        System.out.println("[SERVER] INPUT => " + member.getInetAddress());
        Member client = new Member(member, vmember);
        room.addMember(client);
    }

    @Override
    public void run()
    {
        Socket member = AcceptSocket(serverSocket);
        Socket vmember = AcceptSocket(vServerSocket);

        try {
            addMember(member, vmember);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
