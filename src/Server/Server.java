package Server;

import Server.Room.RoomManager;
import Server.User.Member;
import Server.User.MemberManager;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashSet;

public class Server extends Thread
{
    private static MemberManager memberManager = new MemberManager();
    private static RoomManager roomManager = new RoomManager();
    public ServerSocket serverSocket;
    public ServerSocket vserverSocket;

    public Server()
    {
        OpenSocket();
    }

    public static RoomManager getRoomManager() {
        return roomManager;
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
        memberManager.addMember(client);
    }

    public synchronized void addListenSocket(Socket clientData, Socket clientVoice) throws IOException
    {
        byte[] nickName = new byte[100];
        clientData.getInputStream().read(nickName, 0, 90);
        Member member = MemberManager.getMember(Arrays.toString(nickName));
        if (member != null)
        {
            member.addVoiceList(clientVoice);
        }

    }

    @Override
    public synchronized void run()
    {
        while (true)
        {
            Socket member = AcceptSocket(serverSocket);
            byte[] line = new byte[20];
            System.out.println("[SERVER] INPUT => " + member.getInetAddress());

            try {
                member.getInputStream().read(line, 0, 20);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Socket vmember = AcceptSocket(vserverSocket);
            System.out.println("[SERVER] INPUT => " + vmember.getInetAddress());
            try {
                if (Arrays.toString(line).equals("LOGIN"))
                    addMember(member, vmember);
                else if(Arrays.toString(line).equals("ADDLISTENSOCKET"))
                    addListenSocket(member, vmember);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

        }
    }

}
