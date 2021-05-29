package Server.User;

import Server.Room.Room;
import Server.Server;
import Server.User.Member;

import javax.sound.sampled.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VoiceReciever extends Thread
{
    DataInputStream in;
    private ArrayList<DataOutputStream> outs = new ArrayList<>();
    boolean running = true;
    Member member;

    VoiceReciever (DataInputStream in, Member member)
    {
        this.in=in;
        this.member = member;
        start();
    }

    public void setOut(DataOutputStream out)
    {
        this.outs.add(out);
    }

    public void setRunning (boolean running)
    {
        this.running=running;
    }

    @Override
    public void run()
    {
        AudioFormat format = new AudioFormat(8000.F,16,1,true,false);

        byte[] data = new byte[8000];

        while (running){
            try {
                if(in.available() <= 0)
                    continue;
            } catch (IOException e) {
                e.printStackTrace();
            }

            int readCount = 0;

            try {
                readCount = in.read(data,0,data.length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(readCount);

            if(readCount>0){
                for (DataOutputStream out : this.outs) {
                    try {
                        out.write(data, 0, readCount);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //member.recvVoice(data, readCount);
            }

        }


    }
}

