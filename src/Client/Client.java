package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class Client {
    private SocketCommand socketCommand;
    private User myUser, anotherUser;
    private String ip;
    private DataOutputStream voiceOut, dataOut;
    private DataInputStream voiceIn, dataIn;
    public VoiceSender voiceSender;
    private VoiceReciever voiceReciever;
    private DataReciever dataReciever;
    // gui
    private CallFrame callFrame;
    public Client(){

    }
    public Client(User myUser, String ip) throws IOException
    {
        this.myUser = myUser;
        this.anotherUser = new User("", "");
        this.ip = ip;
        this.callFrame = null;
        setSocketCommand();
        setDataStream();
        System.out.println("Create Client");
    }

    public void setCallFrame(CallFrame callFrame)
    {
        this.callFrame = callFrame;
    }

    public boolean isCallFrame()
    {
        if (callFrame == null)
            return false;
        return true;
    }

    public void setSocketCommand() throws IOException
    {
        socketCommand = new ConnectCommand();
        //System.out.println(ip);
        socketCommand.execute(ip);
    }

    public void setDataStream() throws IOException
    {
        dataIn = new DataInputStream(socketCommand.clientSocket.getInputStream());
        dataOut = new DataOutputStream(socketCommand.clientSocket.getOutputStream());
        voiceOut = new DataOutputStream(socketCommand.clientVoiceSocket.getOutputStream());
        voiceIn = new DataInputStream(socketCommand.clientVoiceSocket.getInputStream());
    }
    
   public void startClient() throws IOException
    {
        startData();
    }

    public void startCall() throws IOException
    {
        startData();
        startVoice();
        //sendData("COMMANDSTART:GETROOMLIST:"+myUser.getNickName()+":END");
    }

    public void startVoice()
    {
        voiceSender = new VoiceSender(voiceOut,this );
        voiceReciever = new VoiceReciever(voiceIn);
    }

    public int callCheck()
    {
       while (getAnotherUserName().equals(""))
       {
       }
       System.out.println(getAnotherUserName());
       System.out.println(getAnotherUserStatus());
       return 1;
    }
    
    public void startData()
    {
        dataReciever = new DataReciever(dataIn, this,myUser);
    }

    public void sendData(String data) throws IOException
    {
        dataOut.writeUTF(data);
        dataOut.flush();
    }


    public String getMyUserName()
    {
        return myUser.getNickName();
    }
    public int getMyUserRoomid(){ return myUser.getRoomid(); }
    public String getMyUserStatus()
    {
        return myUser.getStatusMessage();
    }

    public void setMyUserStatus(String Status)
    {
        myUser.setStatusMessage(Status);
        try {
            //COMMANDSTART:SETMYSTATUS:아이디(nickname):룸제목:상메:END
            sendData("COMMANDSTART:SETMYSTATUS:"+myUser.getNickName()+":null:"+Status+":END"); /////////////// --- 추ㅏㄱ-------------
            //sendData("COMMANDSTART:" +"SETMYSTATUS:"+myUser.getNickName()+":"+ Status+ ":END");
            //sendData("COMMANDSTART:" +"SETMYSTATUS:"+ myUser.getRoomid() + ":END");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getRoomInfo(String roomName) throws IOException {
        sendData("COMMANDSTART:GETROOMMEMBERLIST:"+myUser.getNickName()+":"+roomName+":END");
    }

    public String getAnotherUserName()
    {
        return anotherUser.getNickName();
    }

    public String getAnotherUserStatus()
    {
        return anotherUser.getStatusMessage();
    }

    public void setAnotherUserName(String name)
    {
        anotherUser.setNickname(name);
    }

    public void setAnotherUserStatus(String status)
    {
        anotherUser.setStatusMessage(status);
        if (isCallFrame()) {
            callFrame.setStatusLabel(status);
        }
    }
    
    public String getIP(){
        return ip;
    }


}