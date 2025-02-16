import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class server {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("Server is waiting...");

        Socket s1 = ss.accept();
        DataInputStream dis = new DataInputStream(s1.getInputStream());
        DataOutputStream dos = new DataOutputStream(s1.getOutputStream());

        while (true) {
            String clientMessage = dis.readUTF();
            System.out.println("Client: " + clientMessage);
            if (clientMessage.equalsIgnoreCase("exit")) break;

            String response = clientMessage.equalsIgnoreCase("Ok") ? "Okey"
                            : clientMessage.equalsIgnoreCase("Hi") ? "Hlw"
                            : clientMessage.equalsIgnoreCase("date") ? "Today's date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
                            : "Unknown Message";

            for (int i = 0; i < (clientMessage.equalsIgnoreCase("Ok") ? 9 : 1); i++) {
                dos.writeUTF(response);
            }
            dos.flush();
        }

        dis.close();
        dos.close();
        s1.close();
        ss.close();
    }
}
