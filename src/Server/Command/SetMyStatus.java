package Server.Command;

import Server.Room.Room;
import Server.Room.RoomManager;
import Server.User.Member;
import Server.User.MemberManager;

public class SetMyStatus implements Command
{
    private String nickname;
    private String status;
    private Boolean sw = false;

    public SetMyStatus(String[] args)
    {
        if ((args.length == CommandInfo.SetMyStatusArgs))
        {
            this.nickname = args[2];
            this.status = args[4];
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
                member.setStatusMessage(this.status);

                Room room = RoomManager.getRoom(member.getRoomName());
                if (room != null)
                {
                    String format =
                            CommandInfo.CommandStart + CommandInfo.splitCommand
                                    + CommandInfo.SetMyStatus + CommandInfo.splitCommand
                                    + this.nickname + CommandInfo.splitCommand
                                    + this.status + CommandInfo.splitCommand
                                    + CommandInfo.CommandEnd;
                    room.sendAllData(format);
                }
            }
        }
    }
}
