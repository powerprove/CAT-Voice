package Client;

import java.io.IOException;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) throws IOException {
        User user1 = new User("powerprove", "very hard...");
        Client clientUser1 = new Client(user1, "182.219.127.218");
        System.out.println("CALL");
        clientUser1.startCall();
        //clientUser1.sendData("COMMAND:ARGV:ARGV:ARGV:END");
    }
}
