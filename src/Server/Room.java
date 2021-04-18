package Server;

import java.util.ArrayList;

public class Room
{
    static ArrayList<Member> room = new ArrayList<>();
    private RoomCommand roomcommand;

    public Room()
    {
        roomcommand = new RoomCommand(this);
    }

    public void addMember(Member member)
    {
        member.setRoom(this);
        member.setRoomId(room.size());
        room.add(member);
    }

    public Member getMember(int id)
    {
        return room.get(id);
    }

    public void sendDataMember(String Data, int id)
    {
        Member member = room.get(id);
        member.sendData(Data);
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

    public void CommandData(String data, int roomid)
    {
        roomcommand.CommandParse(data, roomid);
    }


}
