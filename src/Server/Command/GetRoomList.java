package Server.Command;

import Server.Room.RoomManager;
import Server.User.Member;
import Server.User.MemberManager;

public class GetRoomList implements Command
{
    private String Name;
    private boolean sw = false;

    public GetRoomList(String[] args)
    {
        if ((args.length == CommandInfo.GetRoomListArgs))
        {
            this.Name = args[2];
            sw = true;
        }
    }

    @Override
    public void execute()
    {
        if (sw) {
            String format =
                    CommandInfo.CommandStart + CommandInfo.splitCommand
                            + CommandInfo.GetRoomList + CommandInfo.splitCommand
                            + CommandInfo.Server + CommandInfo.splitCommand;
            format += RoomManager.getRoomList() + CommandInfo.CommandEnd;

            System.out.print("TEST33");
            Member member = MemberManager.getMember(this.Name);
            System.out.print("format : " + format);
            member.sendData(format);
        }
    }
}
