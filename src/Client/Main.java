package Client;

import java.io.IOException;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) throws IOException {
        User user1 = new User("powerprove", "박진우 죽어라");
        Client clientUser1 = new Client(user1, "182.219.127.218");
        System.out.println("CALL");
        clientUser1.startCall();
    }
}
