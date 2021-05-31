package Server.Command;

import Server.Room.Room;
import Server.Room.RoomManager;
import Server.User.Member;
import Server.User.MemberManager;

public class InRoom implements Command
{
    private String nickname;
    private String status;
    private String roomName;
    private Boolean sw = false;

    public InRoom(String[] args)
    {
        if ((args.length == CommandInfo.InRoomArgs))
        {
            this.nickname = args[3];
            this.status = args[4];
            this.roomName = args[5];
            sw = true;
        }
    }

    @Override
    public void execute() {
        if (sw)
        {
            Member member = MemberManager.getMember(nickname);
            if (member != null)
            {
                RoomManager.JoinRoom(this.roomName, member);
                member.setRoomName(this.roomName);

                Room room = RoomManager.getRoom(this.roomName);
                if (room != null)
                {
                    member.setRoom(room);
                    String format =
                            CommandInfo.CommandStart + CommandInfo.splitCommand
                            + CommandInfo.InRoomSendCommand + CommandInfo.splitCommand
                            + this.nickname + CommandInfo.splitCommand
                            + this.status + CommandInfo.splitCommand
                            + CommandInfo.CommandEnd;
                    room.sendAllData(format);
                }
            }
        }
    }
}
