package Server.User;

import Server.Command.CommandHandler;
import Server.Room.Room;
import Server.Room.RoomManager;
import Server.Server;
import Server.User.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Member extends User
{
    private Room room;
    private Socket member;
    private Socket vmember;
    private DataInputStream dataIn;
    private DataInputStream voiceIn;
    private DataOutputStream dataOut;
    private DataOutputStream voiceOut;
    private Reciever reciever;
    private VoiceReciever vreciever;
    private CommandHandler commandhandler = new CommandHandler();
    
    private boolean Manager = false;
    private boolean isNotice = false;

    private int roomId;
    private String roomName;

    public Member(Socket member, Socket vmember) throws IOException
    {
        this.member = member;
        this.vmember = vmember;
        room = null;
        SetStream();
        Execute();
    }

    public boolean isManager() { return this.Manager; }

    public void setManageer() { this.Manager = true;}

    public void setisNotice() {
        if (isManager()){
            if (this.isNotice == true)
                this.isNotice = false;
            else if (this.isNotice = false)
                this.isNotice = true;
        }
    }

    public void setRoomName(String roomName)
    {
        System.out.println("[ROOM] => ROOMName " + roomName);
        this.roomName = roomName;
    }

    public String getRoomName()
    {
        return this.roomName;
    }

    public void setRoomId(int id)
    {
        System.out.println("[ROOM] => ROOMID " + id);
        roomId = id;
    }

    public int getRoomId()
    {
        return roomId;
    }

    public void setRoom(Room room)
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
        if (dataIn == null)
            System.out.println("DataIn is null");
        if (voiceIn == null)
            System.out.println("DataIn is null");

        reciever = new Reciever(dataIn, this);
        vreciever = new VoiceReciever(voiceIn, this);
    }

    public void recvData(String data)
    {
        System.out.println("recvData");
        commandhandler.CommandExecute(data);
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
        if (isNotice)
            RoomManager.sendNotice(data, count);
        else
            room.sendVoice(data, roomId, count);
    }

    public void sendVoice(byte[] data, int count)
    {
        try {
            voiceOut.write(data,0,count);
            //voiceOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString()
    {
        return getNickName();
    }

    @Override
    public boolean equals(Object target)
    {
        if (target.getClass() != getClass())
            return false;
        Member tmpRoom = (Member)target;
        return (tmpRoom.getNickName().equals(this.getNickName()));
    }

    @Override
    public int hashCode()
    {
        return this.getNickName().hashCode();
    }

}
