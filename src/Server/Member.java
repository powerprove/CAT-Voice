package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Member
{
    private Room room;
    private Socket member;
    private Socket vmember;
    private DataInputStream dataIn;
    private DataInputStream voiceIn;
    private DataOutputStream dataOut;
    private DataOutputStream voiceOut;
    private Reciever reciever;

    Member(Socket member, Socket vmember) throws IOException
    {
        this.member = member;
        this.vmember = vmember;
        room = null;
        SetStream();
        Execute();
    }

    void setRoom(Room room)
    {
        this.room = room;
    }

    void SetStream() throws IOException
    {
        dataIn = new DataInputStream(member.getInputStream());
        dataOut = new DataOutputStream(member.getOutputStream());
        voiceOut = new DataOutputStream(vmember.getOutputStream());
        voiceIn = new DataInputStream(vmember.getInputStream());
    }

    public DataInputStream getDataIn()
    {
        return dataIn;
    }
    public DataOutputStream getDataOut()
    {
        return dataOut;
    }
    public DataInputStream getVoiceIn()
    {
        return voiceIn;
    }

    public DataOutputStream getVoiceOut()
    {
        return voiceOut;
    }

    public void Execute()
    {
        reciever = new Reciever(dataIn, this);
    }

    public void recvData(String data)
    {
        room.sendData(data, this);
    }

    //Send Data to Client
    public void sendData(String data)
    {
        try
        {
            dataOut.writeUTF(data);
            dataOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recvVoice(byte[] data, int count)
    {
        room.sendVoice(data, this, count);
    }

    public void sendVoice(byte[] data, int count)
    {
        try {
            voiceOut.write(data,0,count);
            voiceOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
