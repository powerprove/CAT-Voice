package Server.Command;

// COMMANDSTART:COMMAND:USERNAME:ARGV:END
public class CommandHandler
{
    public void CommandExecute(String data)
    {
        System.out.println(data);
        if (!data.contains(":"))
            return;
        String[] commandString = data.split(CommandInfo.splitCommand);
        Command command = findCommand(commandString);
        if (command != null)
            command.execute();
    }

    public Command findCommand(String[] commandString)
    {
        Command command = null;
        if (commandString[1].equals(CommandInfo.CreateRoom))
        {
            command = new CreateRoom(commandString);
        }
        else if (commandString[1].equals(CommandInfo.DeleteRoom))
        {
            command = new DeleteRoom(commandString);
        }
        else if (commandString[1].equals(CommandInfo.InRoom))
        {
            command = new InRoom(commandString);
        }
        else if (commandString[1].equals(CommandInfo.SetMyStatus))
        {
            command = new SetMyStatus(commandString);
        }
        else if (commandString[1].equals(CommandInfo.SETNOTICE))
        {
            command = new SetNotice(commandString);
        }
        else if (commandString[1].equals(CommandInfo.GetRoomList))
        {
            command = new GetRoomList(commandString);
        }
        else if (commandString[1].equals(CommandInfo.GetRoomMemberList))
        {
            command = new GetRoomMemberList(commandString);
        }
        return command;
    }
}
