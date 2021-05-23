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
    static int i=0;
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
        System.out.println(bytes);
        while (running) //동기화
        {
            int count = mic.read(bytes, 0, bytes.length);
            //
            if(count>0)
            {
                try {
                    System.out.println(i++);
                    //************mic.getLevel();*************
                    // 라인의 현재의 볼륨 레벨을 취득합니다. 이 레벨은 신호의 현재의 진폭의 척도입니다.
                    //또, 이 레벨과 게인 컨트롤의 현재의 설정을 혼동 하지 말아 주세요.
                    // 범위는 0.0 (소리의 나오지 않는 상태)에서 1.0 (사운드 파형의 최대 진폭)까지입니다.
                    // 단위는 데시벨은 아니고 선형 진폭으로 측정됩니다.
                    out.write(bytes,0,count); //

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
