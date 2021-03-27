package Client;
	
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;


public class Client {
	private Socket ServerSocket,VoiceSocket;
	public String user;
    private DataOutputStream sendVoice, out;
    private DataInputStream recvVoice, in;
	public Client(String user, String ip) throws UnknownHostException, IOException {
		this.user = user;
		System.out.println("Wait!!!");
		ServerSocket = new Socket(ip,8282); // 클라이언트 서버로 연결
		VoiceSocket = new Socket(ip,8283); // 보이스 소켓 포트 8283
		
		try {
			
			out = new DataOutputStream(ServerSocket.getOutputStream());
	        in = new DataInputStream(ServerSocket.getInputStream());
			sendVoice = new DataOutputStream(VoiceSocket.getOutputStream());
			recvVoice = new DataInputStream(VoiceSocket.getInputStream());
	        
			
			System.out.println("Client connected IP addres = " + VoiceSocket.getRemoteSocketAddress().toString());
			
			while(true) { // 메인페이지에서 next 눌럿을때
				String buf = in.readUTF();
				System.out.println("[*] : " + buf);
				if( buf.equals("END")){
					break;
				}
			}
			
			
		}catch (Throwable e) {
			 e.printStackTrace();
		}
	}
	
	public void call(String user) throws IOException { // 전화 걸기 버튼 눌럿을때
		
	 
		Reciever reciever = new Reciever(recvVoice);
        Sender sender = new Sender(sendVoice);
	
	}
}
