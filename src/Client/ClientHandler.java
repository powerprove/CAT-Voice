package Client;

;
import Client.Client;
import Client.LoginFrame;
import java.io.IOException;


public class ClientHandler{
    public static User user;
    public static Client client;
    public static int RoomTotalpeople = 0;
    
    public void createHandler() throws IOException{
       this.user = new User(LoginFrame.name.getText(),LoginFrame.profile.getText());
       this.client = new Client(user,LoginFrame.ipaddr.getText());
       client.startClient();
       user.ArraySetting();
    }
}
