package Server.Command;

public class DeleteRoom implements Command
{
    private String RoomName;
    private Boolean sw = false;

    public DeleteRoom(String[] args)
    {
        if ((args.length == CommandInfo.DeleteRoomArgs))
        {
            this.RoomName = args[3];
            sw = true;
        }
    }

    @Override
    public void execute()
    {
        if (sw)
            Server.Server.getRoomManager().DeleteRoom(this.RoomName);
    }
}
