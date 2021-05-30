package Server.Room;

import Server.Command.CommandInfo;
import Server.User.Member;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

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

    public static void JoinRoom(String Roomname, Member member)
    {
        if (RoomManager.rooms.containsKey(Roomname))
        {
            Room room = RoomManager.rooms.get(Roomname);
            if (room != null) {
                room.addMember(member);
                System.out.format(RoomInfo.PrintFormatTitleContent,
                        RoomInfo.SearchRoom, member);
            }
        }
    }

    public static Room getRoom(String roomName)
    {
        Room room = null;
        if (RoomManager.rooms.containsKey(roomName))
            room = RoomManager.rooms.get(roomName);

        return room;
    }

    public static String getRoomList()
    {
        String roomList = RoomManager.rooms.size() + CommandInfo.splitCommand;
        for ( Entry<String, Room> entry : RoomManager.rooms.entrySet() )
        {
            roomList = roomList.concat(entry.getKey() + CommandInfo.splitCommand);
            roomList = roomList.concat(entry.getValue().getMemberNum() + CommandInfo.splitCommand);
        }
        return roomList;
    }

    public static void sendNotice(byte[] Data, int count)
    {
        for (Entry<String, Room> entry : RoomManager.rooms.entrySet()) {
            Room room = entry.getValue();
            for (Member member : room.getMembers()) {
                    member.sendVoice(Data, count);
            }
        }
    }



}
