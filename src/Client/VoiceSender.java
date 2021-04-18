package Client;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class VoiceSender extends Thread
{
    UserInfo usrInfo = new UserInfo();
    private static boolean chk=false;
    private DataOutputStream out = null;
    private boolean running =true;
    public ClientCommand clientCommand;

    public void setRunning(boolean running)
    {
        this.running=running;
    }


    VoiceSender(DataOutputStream out , Client client)
    {
        this.clientCommand = new ClientCommand(client);

        this.out=out;
        start();
    }


    @Override
    public void run()
    {

        AudioFormat format = new AudioFormat(8000.F, 16, 1, true, false);
        TargetDataLine mic = null;
        
        try {
            mic = AudioSystem.getTargetDataLine(format);
            mic.open(format);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        System.out.println("Start recording");
        mic.start();

        byte[] bytes = new byte[(int) (mic.getFormat().getSampleRate()*0.4)];

        while (running) //동기화
        {
            int count = mic.read(bytes, 0, bytes.length);
            if(count>0)
            {
                try {
                    chk = true;
                    out.write(bytes,0,count); //
                    out.write(usrInfo.getName().getBytes(StandardCharsets.UTF_8),0,usrInfo.getName().length());
                    chk = false; // add
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
