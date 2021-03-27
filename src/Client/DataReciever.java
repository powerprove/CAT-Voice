package Client;

import java.io.DataInputStream;
import java.io.IOException;

public class DataReciever extends Thread
{
    private DataInputStream in;
    private boolean running;

    DataReciever(DataInputStream in)
    {
        this.in = in;
        running = true;
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
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }


    }
}
