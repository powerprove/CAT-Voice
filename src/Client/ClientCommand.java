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
        else if("INROOMUSER".equals(command[1])){ // 어떤 방에 있는데, 다른 유저가 들어올때
           // COMMANDSTART:INROOM:아이디(nickname):닉네임:상태메시지:ROOM이름:END
           // COMMANDSTART:INROOMUSER:닉네임:상태메시지:END

            //int idx = Integer.parseInt(command[4]);
           myUser.roomUserName.add(command[2]); // --추가
           myUser.roomUserMSG.add(command[3]); // --추가
           //myUser.roomUserName[myUser.roomUserIdx] = command[2]; // nickName
           //myUser.roomUserMSG[myUser.roomUserIdx] = command[3]; // state message
           //myUser.roomUserIdx +=1;

           // 나가기 하는순간 초기화 해야함.

        }
        else if("SETMYSTATUS".equals(command[1])) { // change statemessage
            // COMMANDSTART:SETMYSTATUS:바뀔유저이름:바뀐상메:END
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
        else if("GETROOMMEMBERLIST".equals(command[1])) { // 내가 어떤 방에 들어갈 때
            // COMMANDSTART:GETROOMMEMBERLIST:SERVER:방제목:사람수:사람닉네임:사람상태메시지:사람닉네임:사람상태메시지:END
            int i;
            for(i=0; i<myUser.roomcnt; i++){
                if(myUser.roomInfo[i].roomName == command[3]) { //방 이름이 같다면 그방안에 값 저장
                    int totalPeople = Integer.parseInt(command[4]);
                    for(int j=0; j<totalPeople; j++) {
                        //myUser.roomInfo[i].otherName[j]=command[5+ j*2]; //5 7  9
                        // myUser.roomInfo[i].otherStausMSG[j] =command[6+ j*2];  //6 8 10
                        myUser.roomUserName.add(command[5+ j*2]); // --추가
                        myUser.roomUserMSG.add(command[6+ j*2]); // -- 추가
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
