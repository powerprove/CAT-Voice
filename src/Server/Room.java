package Server;

import java.util.ArrayList;

public class Room
{
    static ArrayList<Member> room = new ArrayList<>();

    public void addMember(Member member)
    {
        member.setRoom(this);
        member.setRoomId(room.size());
        room.add(member);
    }

    public void sendData(String Data, int roomnum)
    {
        System.out.println("[ROOM] sendData => " + Data);
        for (Member member : room)
        {
            if (member.getRoomId() != roomnum)
                member.sendData(Data);
        }
    }

    public void sendVoice(byte[] Data, int roomnum, int count)
    {
        //System.out.println("[ROOM] sendVoice");
        for (Member member : room)
        {
            //System.out.println(count);
            if (member.getRoomId() != roomnum)
                member.sendVoice(Data, count);
        }
    }


}
