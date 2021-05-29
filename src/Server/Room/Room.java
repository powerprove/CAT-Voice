package Server.Room;

import Server.User.Member;

import java.util.ArrayList;

public class Room
{
    private ArrayList<Member> room = new ArrayList<>();
    private String name;

    public Room(String name)
    {
        this.name = name;
    }

    public String getRoomName()
    {
        return this.name;
    }

    public void addMember(Member member)
    {

        member.setRoom(this);
        member.setRoomId(room.size());
        for (Member anotherMember : this.room) {
            anotherMember.addVoiceOut(member.getVoiceOutStream());
            member.addVoiceOut(anotherMember.getVoiceOutStream());
        }
        room.add(member);
    }

    public ArrayList<Member> getMembers()
    {
        return this.room;
    }
    public Member getMember(int id)
    {
        return room.get(id);
    }
    public int getMemberNum()
    {
        return room.size();
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
            member.sendData(Data);
        }
    }

    public void sendAllData(String Data)
    {
        System.out.println("[ROOM] sendData => " + Data);
        for (Member member : room)
        {
            member.sendData(Data);
        }
    }

    public void sendVoice(byte[] Data, int roomnum, int count)
    {
        for (Member member : room)
        {
            if (member.getRoomId() != roomnum)
                member.sendVoice(Data, count);
        }
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
