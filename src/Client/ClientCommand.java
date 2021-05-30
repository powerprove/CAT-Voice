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
            //int idx = Integer.parseInt(command[4]);
            //myUser.roomUserName[idx] = command[2]; // nickName
            //myUser.roomUserMSG[idx] = command[3]; // state message
        }
        else if("SETMYSTATUS".equals(command[1])) { // change statemessage
            int idx = Integer.parseInt(command[2]);
            myUser.roomUserMSG[idx] = command[3];
        }
        else if("GETROOMLIST".equals(command[1])){ // Room List SAVE
            //COMMANDSTART:GETROOMLIST:SERVER:방개수:방이름:방인원수:방이름:방인원수:END//
           int roomCount = Integer.parseInt(command[3]);
            for(int i=0; i<=roomCount; i+=2){
                myUser.roomInfo[i].roomName = command[4+i];
                System.out.println("command : " + command[4+i]);
                myUser.roomInfo[i].headCount = Integer.parseInt(command[4+i+1]);
            }
            myUser.roomcnt = roomCount;
       }
        else if("GETROOMMEMBERLIST".equals(command[1])) { // COMMANDSTART:GETROOMMEMBERLIST:SERVER:방제목:사람수:사람닉네임:사람상태메시지:사람닉네임:사람상태메시지:END
            int i;
            for(i=0; i<myUser.roomcnt; i++){
                if(myUser.roomInfo[i].roomName == command[3]) { //방 이름이 같다면 그방안에 값 저장
                    int totalPeople = Integer.parseInt(command[4]);
                    for(int j=0; j<totalPeople; j++) {
                        myUser.roomInfo[i].otherName[j]=command[5+ j*2]; //5 7  9
                        myUser.roomInfo[i].otherStausMSG[j] =command[6+ j*2];  //6 8 10
                    }
                }
            }
       }
       else if("GETROOMMEMBERLIST".equals(command[1])) { // COMMANDSTART:GETROOMMEMBERLIST:SERVER:방제목:사람수:사람닉네임:사람상태메시지:사람닉네임:사람상태메시지:END
            int i;
            for(i=0; i<myUser.roomcnt; i++){
                if(myUser.roomInfo[i].roomName == command[3]) { //방 이름이 같다면 그방안에 값 저장
                    int totalPeople = Integer.parseInt(command[4]);
                    for(int j=0; j<totalPeople; j++) {
                        myUser.roomInfo[i].otherName[j]=command[5+ j*2]; //5 7  9
                        myUser.roomInfo[i].otherStausMSG[j] =command[6+ j*2];  //6 8 10
                    }
                }
            }
       }
    }

    public synchronized void Command(String data,User myUser)
    {
        System.out.println("data : "+data);
        if (data.contains(":")){
        String[] command = data.split(":");
        CommandArgv2(command,myUser);
        }

    }
}
