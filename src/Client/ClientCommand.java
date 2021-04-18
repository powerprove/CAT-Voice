package Client;

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

    }

    public synchronized void Command(String data,User myUser)
    {
        String[] command = data.split(":");
        //System.out.println(command);

        if (command.length == 3)
        {
            CommandArgv2(command,myUser);
        }

    }
}
