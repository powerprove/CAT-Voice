package Client;

import javax.sound.sampled.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class VoiceReciever extends Thread
{
    DataInputStream in;
    boolean running;
    AudioFormat format;
    DataLine.Info speakerInfo;
    SourceDataLine speaker;

    VoiceReciever (DataInputStream in)
    {
        this.in=in;
        start();

        SourceDataLine speaker = null;
    }

    public void setRunning (boolean running)
    {
        this.running=running;
    }

    public void initVoice()
    {
        format = new AudioFormat(8000.F,16,1,true,false);
        speakerInfo = new DataLine.Info(SourceDataLine.class, format);
        try {
            speaker = (SourceDataLine) AudioSystem.getLine(speakerInfo);
            speaker.open(format);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run()
    {
        initVoice();
        speaker.start();
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
                speaker.write(data,0,readCount);
            }

        }


    }
}
