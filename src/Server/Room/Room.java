package Server.Room;

import Server.User.Member;

import java.util.ArrayList;

public class Room
{
    static ArrayList<Member> room = new ArrayList<>();
    private String name;
    private RoomCommand roomcommand;

    public Room(String name)
    {
        this.name = name;
        roomcommand = new RoomCommand(this);
    }

    public String getRoomName()
    {
        return this.name;
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

    @Override
    public boolean equals(Object target)
    {
        if (target.getClass() != getClass())
            return false;
        Room tmpRoom = (Room)target;
        return (tmpRoom.getRoomName().equals(this.name));
    }

    @Override
    public int hashCode()
    {
        return this.name.hashCode();
    }

}
