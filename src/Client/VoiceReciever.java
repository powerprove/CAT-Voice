package Client;

import javax.sound.sampled.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class VoiceReciever extends Thread
{
    private DataInputStream in;
    private boolean running = true;

    UserInfo usrInfo = new UserInfo();
    VoiceState voiceState = new VoiceState();
    VoiceReciever (DataInputStream in)
    {
        this.in=in;
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
        DataLine.Info speakerInfo = new DataLine.Info(SourceDataLine.class, format);

        SourceDataLine speaker = null;
        try {
            speaker = (SourceDataLine) AudioSystem.getLine(speakerInfo);
            speaker.open(format);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

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

            if(readCount>0){
                if(usrInfo.nickName != null){
                    try {
                        String s1 = in.readUTF();
                        if(usrInfo.nickName != s1){ // 자기 닉네임 != 말한사람 이름
                            voiceState.name = s1;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


                speaker.write(data,0,readCount);

            }

        }


    }
}
