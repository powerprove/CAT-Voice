package Client;

import javax.sound.sampled.AudioFormat;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler
{
    DataInputStream in;
    DataOutputStream out;
    DataOutputStream voiceOut;
    DataInputStream voiceIn;
    Socket client;
    Socket voice;

    ClientHandler(Socket client, Socket voice) throws IOException
    {
        in = new DataInputStream(client.getInputStream());
        out = new DataOutputStream(client.getOutputStream());
        voiceOut = new DataOutputStream(voice.getOutputStream());
        voiceIn = new DataInputStream(voice.getInputStream());
        this.client = client;
        this.voice = voice;
    }
    public DataInputStream getIn()
    {
        return in;
    }

    public DataOutputStream getOut()
    {
        return out;
    }
    public DataInputStream getVoiceIn()
    {
        return voiceIn;
    }

    public DataOutputStream getVoiceOut()
    {
        return voiceOut;
    }
}
