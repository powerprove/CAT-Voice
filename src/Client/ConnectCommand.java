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


public class ConnectCommand {
	private Socket ServerSocket,VoiceSocket;
	public String ip;
	public void execute(String ip) throws IOException {
		ServerSocket = new Socket(ip,8282); // Ŭ���̾�Ʈ ������ ����
		VoiceSocket = new Socket(ip,8283); // ���̽� ���� ��Ʈ 8283
		
	}	
}
