import java.io.*;
import java.net.*;

public class Server {
    ServerSocket serverSocket;

    public void go() {
        try {
            serverSocket = new ServerSocket(5000);
            while(true) {
                Socket s = serverSocket.accept();

                InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                
                String result = bufferedReader.readLine();
                System.out.println("server receive");
                System.out.println(result);
                bufferedReader.close();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.go();
    }
}