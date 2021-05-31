package Server.Command;

import Server.User.Member;
import Server.User.MemberManager;

public class SetNotice implements Command
{
    private String Name;
    private boolean sw;

    public SetNotice(String[] args)
    {
        if ((args.length == CommandInfo.DeleteRoomArgs))
        {
            this.Name = args[2];
            sw = true;
        }
    }

    @Override
    public void execute()
    {
        if (sw)
        {
            Member member = MemberManager.getMember(this.Name);
            if (member != null)
            {
                member.setisNotice();
            }
        }
    }
}
