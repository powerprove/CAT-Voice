package Server;

import java.util.ArrayList;

public class Room
{
    static ArrayList<Member> room = new ArrayList<>();

    public void addMember(Member member)
    {
        room.add(member);
        member.setRoom(this);
    }

    public void sendData(String Data, Member my)
    {
        System.out.println("[ROOM] sendData => " + Data);
        for (Member member : room)
        {
            if (member != my)
            {
                member.sendData(Data);
            }
        }
    }

    public void sendVoice(byte[] Data, Member my, int count)
    {
        System.out.println("[ROOM] sendVoice");
        for (Member member : room)
        {
            if (member != my)
            {
                member.sendVoice(Data, count);
            }
        }
    }


}
