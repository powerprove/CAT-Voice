package Client;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import java.io.DataOutputStream;
import java.io.IOException;

public class VoiceSender extends Thread
{
    DataOutputStream out;
    boolean running;
    AudioFormat format;
    TargetDataLine mic;

    public void setRunning(boolean running)
    {
        this.running=running;
    }

    VoiceSender(DataOutputStream out)
    {
        this.out=out;
        running = true;
        mic = null;
        start();
    }

    public void initVoice()
    {
        try {
            format = new AudioFormat(8000.F, 16, 1, true, false);
            mic = AudioSystem.getTargetDataLine(format);
            mic.open(format);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        initVoice();
        System.out.println("Start recording");
        mic.start();

        byte[] bytes = new byte[(int) (mic.getFormat().getSampleRate()*0.4)];
        //System.out.println(bytes);

        while (running)
        {
            int count = mic.read(bytes, 0, bytes.length);

            if (count>0)
            {
                try {
                    out.write(bytes,0,count);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
