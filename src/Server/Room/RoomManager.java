package Server.Room;

import Server.User.Member;

import java.util.HashMap;
import java.util.HashSet;

public class RoomManager
{
    private static HashMap<String, Room> rooms = new HashMap<>();

    public void CreateRoom(String name)
    {
        Room room = new Room(name);
        if (RoomManager.rooms.containsKey(name))
            System.out.format(RoomInfo.ERRORformat, RoomInfo.ADDROOMFAIL,
                    RoomInfo.DetectedName + name);
        else {
            RoomManager.rooms.put(name, room);
            System.out.format(RoomInfo.PrintFormatTitleContent,
                    RoomInfo.ADDROOM, name);
        }
    }

    public void DeleteRoom(String name)
    {
        if (RoomManager.rooms.containsKey(name)) {
            RoomManager.rooms.remove(name);
            System.out.format(RoomInfo.PrintFormatTitleContent,
                    RoomInfo.DeleteRoom, name);
        }
    }

    public void JoinRoom(String Roomname, Member member)
    {
        if (RoomManager.rooms.containsKey(Roomname))
        {
            Room room = RoomManager.rooms.get(Roomname);
            if (Roomname != null) {
                room.addMember(member);
                System.out.format(RoomInfo.PrintFormatTitleContent,
                        RoomInfo.SearchRoom, member);
            }
        }
    }



}
