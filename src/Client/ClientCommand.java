package Client;

public class ClientCommand
{
    private Client client;

    ClientCommand(Client client)
    {
        this.client = client;
    }

    public void CommandArgv2(String[] command)
    {
        if (command[0].equals("SETNAME"))
        {
            client.setAnotherUserName(command[1]);
        }
        else if (command[0].equals("SETSTATUS"))
        {
            client.setAnotherUserStatus(command[1]);
        }
    }

    public synchronized void Command(String data)
    {
        String[] command = data.split(":");
        //System.out.println(command);

        if (command.length == 3)
        {
            CommandArgv2(command);
        }

    }
}
