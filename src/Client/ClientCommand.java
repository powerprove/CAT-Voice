package Client;

import static Client.WatingRoomFrame.User1;

public class ClientCommand
{
    public Client client;


    ClientCommand(Client client)
    {
        this.client = client;

    }

    public void CommandArgv2(String[] command,User myUser)
    {
       if("USERROOMID".equals(command[1])){
            myUser.roomid = Integer.parseInt(command[2]);
        }
        else if("INROOMUSER".equals(command[1])){ // another User info Save
            int idx = Integer.parseInt(command[4]);
            myUser.roomUserName[idx] = command[2]; // nickName
            myUser.roomUserMSG[idx] = command[3]; // state message
        }
        else if("SETMYSTATUS".equals(command[1])) { // change statemessage
            int idx = Integer.parseInt(command[2]);
            myUser.roomUserMSG[idx] = command[3];
        }
        else if("GETROOMLIST".equals(command[1])){ // Room List SAVE
            System.out.println("InTEST");
            //COMMANDSTART:GETROOMLIST:SERVER:방개수:방이름:방인원수:방이름:방인원수:END//
           int roomCount = Integer.parseInt(command[3]);
            for(int i=0; i<=roomCount; i+=2){
                myUser.roomInfo[i].roomName = command[4+i];
                myUser.roomInfo[i].headCount = Integer.parseInt(command[4+i+1]);
            }
            myUser.roomcnt = roomCount;

       }
    }

    public synchronized void Command(String data,User myUser)
    {
        String[] command = data.split(":");
        CommandArgv2(command,myUser);


    }
}
