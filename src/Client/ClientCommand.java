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
           myUser.roomUserName.add(command[2]); // --추가
           myUser.roomUserMSG.add(command[3]); // --추가
           myUser.roomUserIdx +=1;
           ClientHandler clienthandler = new ClientHandler();
           clienthandler.RoomTotalpeople += 1;
           System.out.println("RoomTotalPeople:" + clienthandler.RoomTotalpeople);
           System.out.println("roomUSerIDX" + myUser.roomUserIdx);
           CallFrame callFrame = new CallFrame();
           callFrame.dispose();
           callFrame.setVisible(true);
        }
        else if("SETMYSTATUS".equals(command[1])) { // change statemessage
           int Vsize = myUser.roomUserMSG.size(); /////////////////// ----------- 추가
            for(int i=0; i< Vsize; i++) {
                   if(myUser.roomUserName.get(i).equals(command[2])) {
                       myUser.roomUserName.set(i,command[3]);
                   }
               }
        }
        else if("GETROOMLIST".equals(command[1])){ // Room List SAVE
            //COMMANDSTART:GETROOMLIST:SERVER:방개수:방이름:방인원수:방이름:방인원수:END//
           int roomCount = Integer.parseInt(command[3]);
            for(int i=0; i<roomCount; i++){
                myUser.roomInfo[i].roomName = command[4+i*2];
                System.out.println("command : " + command[4+i*2]);
                myUser.roomInfo[i].headCount = Integer.parseInt(command[5+i*2]);
            }
            myUser.roomcnt = roomCount;
       }
       else if("GETROOMMEMBERLIST".equals(command[1])) {
            ClientHandler clienthandler = new ClientHandler();
            int i;
            System.out.println("myUser.roomcnt : " + myUser.roomcnt);
            System.out.println("command3 : " + command[3]);
            System.out.println("roomName 0 : "+ myUser.roomInfo[0].roomName);
            for(i=0; i<myUser.roomcnt; i++){
                if(myUser.roomInfo[i].roomName.equals(command[3])) { //방 이름이 같다면 그방안에 값 저장
                    System.out.println("command4 : " + Integer.parseInt(command[4]));
                    int totalPeople = Integer.parseInt(command[4]);
                    clienthandler.RoomTotalpeople = Integer.parseInt(command[4]);
                    for(int j=0; j<totalPeople; j++) {
                       // myUser.roomInfo[i].otherName[j]=command[5+ j*2]; //5 7  9
                       // myUser.roomInfo[i].otherStausMSG[j] =command[6+ j*2];  //6 8 10
                        myUser.roomUserName.add(command[5+ j*2]); // --추가
                        myUser.roomUserMSG.add(command[6+ j*2]); // -- 추가
                        System.out.println("TotalPeople " + totalPeople);
                        System.out.println("Coomand OtherName " + command[5+ j*2]);
                        System.out.println("Coomand otherStatusMSG " + command[6+ j*2]);
                    }
                }
            }
       }
       else if("SETNOTICE".equals(command[1])){
        //COMMANDSTART:SETNOTICE:SERVER:방이름:아이디:END
           myUser.SpeakerMark.add(command[4]);
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
