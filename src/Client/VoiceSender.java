package Client;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import Client.LoginFrame;

public class VoiceSender extends Thread
{
    static int i=0;

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
        ClientHandler clientHandler = new ClientHandler();
        while (running) //동기화
        {
            int count = mic.read(bytes, 0, bytes.length);
            //
            if(count>0)
            {
                try {
                    System.out.println(i++);
                    chk = true;
           //         System.out.print("MIC SAMpleRate: " + mic.getFormat().getSampleRate());
//                    System.out.print("MIC FrameRAte: " +  mic.getFormat().getFrameRate());
//                    System.out.print("MIC Framesize: " +  mic.getFormat().getFrameSize());
//                    System.out.print("MIC Samplesizeinbits: " +  mic.getFormat().getSampleSizeInBits());
                      
                       out.write(bytes,0,count);
                      if(bytes[0] + bytes[1] + bytes[2] < 3 && bytes[0] + bytes[1] + bytes[2] > -3 ){
                          continue;
                      }
                      else{
                          clientHandler.client.sendData("COMMANDSTART:SETNOTICE:"+LoginFrame.name.getText()+":END");
                          //out.write(bytes,0,count);
                      }
                    System.out.println(clientCommand.client.getMyUserName());
               /*     int VoiceSum = 0;
                    for(int i=0; i<bytes.length; i++)
                        VoiceSum +=bytes[i];
                    System.out.println("VOICE SUM : " + VoiceSum);
*/
                    chk = false; // add
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
