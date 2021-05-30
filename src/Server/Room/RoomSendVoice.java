package Server.Room;

import java.util.LinkedList;
import java.util.Queue;

public class RoomSendVoice extends Thread
{
    private Queue<byte[]> dataq = new LinkedList<>();
    private Room room;

    public RoomSendVoice(Room room)
    {
        this.room = room;
    }

    public void addVoiceDeate(byte[] bytes)
    {
        dataq.add(bytes);
    }

    @Override
    public void run()
    {
        if (!dataq.isEmpty())
        {
            byte[] data = dataq.poll();
            this.room.sendVoice(data, data.length);
        }
    }
}
