package Server.Room;


import Server.Room.Room;
import Server.Server;

public class RoomCommand
{
    private Room room;

    public RoomCommand(Room room)
    {
        this.room = room;
    }

    public synchronized void CommandParse(String data, int roomid)
    {
        String[] command = data.split(":");

        if (command.length == 1)
        {
            room.sendData(data, roomid);
        }
        else
        {
            if (command[1].equals("INROOM")) // 처음 유저가 입장햇을 때
                InRoom(command, roomid);
            else if (command[1].equals("SETMYSTATUS"))
                SetMyStatus(command, roomid);
        }

    }

    private synchronized void InRoom(String[] command, int roomid)
    {
        room.getMember(roomid).setNickname(command[2]);
        room.getMember(roomid).setStatusMessage(command[3]);
        room.sendDataMember("COMMANDSTART:" + "USERROOMID:" + roomid + ":END", roomid);
        sendInRoomUser(roomid);
    }

    private synchronized void SetMyStatus(String[] command, int roomid)
    {
        if (roomid == room.getMember(roomid).getRoomId())
            room.getMember(roomid).setStatusMessage(command[3]);
        sendUserChangeStatus(command, roomid);
    }

    // 여기서부터 서버에서 클라로 보내는거
    private void sendInRoomUser(int roomid)
    {
        String command = "COMMANDSTART:INROOMUSER:" + room.getMember(roomid).getNickName() + ":"
                + room.getMember(roomid).getStatusMessage() + ":" + roomid + ":END";
        room.sendData(command, roomid);
    }

    private void sendUserChangeStatus(String[] command, int roomid)
    {
        String sendcommand = "COMMANDSTART:SETMYSTATUS:" + room.getMember(roomid).getRoomId() + ":"
                + room.getMember(roomid).getStatusMessage() + ":END";
        room.sendData(sendcommand, roomid);
    }

}
