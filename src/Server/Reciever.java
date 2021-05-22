package Server;

import Client.Client;
import Client.ClientCommand;

import java.io.DataInputStream;
import java.io.IOException;

public class Reciever extends Thread
{
    private DataInputStream in;
    private boolean running;
    private Member member;

    Reciever(DataInputStream in, Member member)
    {
        this.in = in;
        running = true;
        this.member = member;
        start();
    }

    public void setRunning(boolean running){
        this.running=running;
    }

    @Override
    public void run()
    {
        while (running)
        {
            try
            {
                //System.out.println("RECV");
                String Data = in.readUTF();
                //System.out.print("Data");
                member.recvData(Data);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }


    }
}

