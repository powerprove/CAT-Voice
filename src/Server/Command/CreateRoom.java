package Server.Command;

public class CreateRoom implements Command
{
    private String RoomName;
    private Boolean sw = false;

    public CreateRoom(String[] args)
    {
        if ((args.length == CommandInfo.CreateRoomArgs))
        {
            this.RoomName = args[3];
            sw = true;
        }

    }

    @Override
    public void execute()
    {
        if (sw)
            Server.Server.getRoomManager().CreateRoom(this.RoomName);
    }
}
