package Client;

import java.io.DataInputStream;
import java.io.IOException;

public class DataReciever extends Thread
{
    private DataInputStream in;
    private boolean running;
    private ClientCommand clientCommand;
    public User myUser;

    DataReciever(DataInputStream in, Client client, User myUser)
    {
        this.in = in;
        this.myUser = myUser;
        running = true;
        clientCommand = new ClientCommand(client);

        start();
    }

    public void setRunning(boolean running){
        this.running=running;
    }

    @Override
    public void run()
    {
        while (running)
        {
            try
            {
                String Data = in.readUTF();
                System.out.println(Data);
                clientCommand.Command(Data,myUser);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }


    }
}
