package Client;

public class ClientCommand
{
    private static Client client;

    ClientCommand(Client client)
    {
        this.client = client;
    }

    public synchronized void Command(String data)
    {
        String[] command = data.split(":");
        System.out.println(command);

    }
}
