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
        return command;
    }
}
