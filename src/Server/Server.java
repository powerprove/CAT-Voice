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
    public ServerSocket vserverSocket;

    public Server()
    {
        OpenSocket();
        room = new Room();
    }

    // Socket open
    public void OpenSocket()
    {
        try
        {
            serverSocket = new ServerSocket(8282);
            vserverSocket = new ServerSocket(8383);
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

    public synchronized void addMember(Socket member, Socket vmember) throws IOException
    {
        System.out.println("[SERVER] INPUT => " + vmember.getInetAddress());
        Member client = new Member(member, vmember);
        room.addMember(client);
    }

    @Override
    public synchronized void run()
    {
        Socket member = AcceptSocket(serverSocket);
        System.out.println("[SERVER] INPUT => " + member.getInetAddress());
        Socket vmember = AcceptSocket(vserverSocket);
        System.out.println("[SERVER] INPUT => " + vmember.getInetAddress());

        try {
            addMember(member, vmember);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
