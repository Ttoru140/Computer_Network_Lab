import java.io.*;
import java.net.Socket;

public class client {
    public static void main(String[] args) throws Exception {
        Socket s2 = new Socket("localhost", 7777);
        DataOutputStream ds2 = new DataOutputStream(s2.getOutputStream());
        DataInputStream dis = new DataInputStream(s2.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("You: ");
            String userMessage = br.readLine(); 
            ds2.writeUTF(userMessage);
            ds2.flush();

            if (userMessage.equalsIgnoreCase("exit")) break;

            for (int i = 0; i < (userMessage.equalsIgnoreCase("Ok") ? 9 : 1); i++) {
                System.out.println("Server: " + dis.readUTF());
            }
        }

        ds2.close();
        dis.close();
        s2.close();
        br.close();
    }
}
