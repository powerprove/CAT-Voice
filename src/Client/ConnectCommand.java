package Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectCommand extends SocketCommand
{
    @Override
    public void execute(String ip) throws IOException {
        System.out.println("Socket Command => Connect");
        clientSocket = new Socket(ip,8282); // 클라이언트 서버로 연결
        clientVoiceSocket = new Socket(ip,8283); // 보이스 소켓 포트 8283
    }
}
