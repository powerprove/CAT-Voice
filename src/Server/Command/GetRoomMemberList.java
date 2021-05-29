package Server.Command;

import Server.Room.Room;
import Server.Room.RoomManager;
import Server.User.Member;
import Server.User.MemberManager;

import java.util.ArrayList;

public class GetRoomMemberList implements Command
{
    private String Name;
    private String RoomName;
    private boolean sw = false;

    public GetRoomMemberList(String[] args)
    {
        if ((args.length == CommandInfo.GetRoomMemberListArgs))
        {
            this.Name = args[2];
            this.RoomName = args[3];
            sw = true;
        }
    }

    @Override
    public void execute()
    {
        if (sw)
        {
            Room room = RoomManager.getRoom(this.RoomName);

            if (room == null)
                return;

            ArrayList<Member> members =  room.getMembers();

            if (members == null)
                return;

            String format = CommandInfo.CommandStart + CommandInfo.splitCommand
                    + CommandInfo.GetRoomMemberList + CommandInfo.splitCommand
                    + CommandInfo.Server + CommandInfo.splitCommand
                    + this.RoomName + CommandInfo.splitCommand
                    + members.size() + CommandInfo.splitCommand;

            for (Member member: members)
            {
                format = format.concat(member.getNickName() + CommandInfo.splitCommand
                        + member.getStatusMessage() + CommandInfo.splitCommand);
            }

            format = format.concat(CommandInfo.CommandEnd);

            Member member = MemberManager.getMember(this.Name);
            if (member != null)
                member.sendData(format);
                System.out.println("getROomList Format : " + format);
        }
    }
}
