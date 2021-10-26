import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class TCPServer {

    public static void main(String[] args) {
        ServerSocket socketServer = null;
        Socket socket = null;
        try {
            socketServer = new ServerSocket(2021);
        }
        catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while(true) {
            try {
                socket = socketServer.accept();
            }
            catch(IOException e) {
                e.printStackTrace();
                System.exit(1);
            }

            InputStream in = null;
            OutputStream out = null;
            try {
                in = socket.getInputStream();
                out = socket.getOutputStream();
            }
            catch(IOException e) {
                e.printStackTrace();
                System.exit(1);
            }

            try {
                out.write("Bienvenue sur mon serveur et au revoir.".getBytes());
                File log = new File("./log.txt");
                if(log.exists()) {
                    
                }
            }
            catch(IOException e) {
                e.printStackTrace();
                System.exit(1);
            }

            try {
                socket.close();
            }
            catch(IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

}