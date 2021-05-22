package Server.Command;

// COMMANDSTART:COMMAND:USERNAME:ARGV:END
public class CommandHandler
{
    public void CommandExecute(String data)
    {
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
        else if (commandString[1].equals(CommandInfo.GetRoomList))
        {
            command = new GetRoomList(commandString);
        }
        return command;
    }
}
