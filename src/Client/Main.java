package Client;

import java.io.IOException;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) throws IOException {
        User user1 = new User("powerprove", "very hard...");
        Client clientUser1 = new Client(user1, "203.229.206.26");
        clientUser1.setSocketCommand();
    }
}
