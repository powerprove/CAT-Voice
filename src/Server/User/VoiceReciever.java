package Server.User;

import Server.Server;
import Server.User.Member;

import javax.sound.sampled.*;
import java.io.DataInputStream;
import java.io.IOException;

public class VoiceReciever extends Thread
{
    DataInputStream in;
    boolean running = true;
    Member member;

    VoiceReciever (DataInputStream in, Member member)
    {
        this.in=in;
        this.member = member;
        start();
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

            //System.out.println(readCount);

            if(readCount>0){
                member.recvVoice(data, readCount);
            }

        }


    }
}

