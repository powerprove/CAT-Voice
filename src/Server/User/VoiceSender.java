package Server.User;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class VoiceSender extends Thread
{
    private Queue<byte[]> dataq = new LinkedList<>();
    private Queue<Integer> countq = new LinkedList<>();
    DataOutputStream out;

    public VoiceSender(DataOutputStream out)
    {
        this.out = out;
        start();
    }

    public void add(byte[] bytes, int count)
    {
        this.dataq.offer(bytes);
        this.countq.offer(count);
        //System.out.println(dataq.size());
    }

    @Override
    public void run()
    {
        while (true)
        {
            if (dataq.isEmpty()) {
                try {
                    sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (countq.isEmpty())
                continue;

            byte[] data = dataq.poll();
            int count = countq.poll();

            try {
                this.out.write(data, 0, count);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (dataq.size() > 20 && countq.size() > 20)
            {
                dataq.clear();
                countq.clear();
            }

        }
    }
}
