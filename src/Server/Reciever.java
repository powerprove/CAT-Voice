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

    Reciever(DataInputStream in, Member recv)
    {
        this.in = in;
        running = true;
        member = recv;
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
                String Data = in.readUTF();
                member.recvData(Data);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }


    }
}

