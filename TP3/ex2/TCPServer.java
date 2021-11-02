import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;

public class TCPServer {
    protected static List<Socket> activeSockets = new ArrayList<>();

    public static void writeLogs(File log, Socket socket) {
        try {
            FileWriter f = new FileWriter(log, true);
            String infos = "IP: " + socket.getInetAddress().toString() + ", Port: " + socket.getPort() + "\n";
            f.write(infos);
            f.close();
        }
        catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

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
                activeSockets.add(socket);
                Thread socketThread = new SocketThread(socket);
                socketThread.start();
            }
            catch(IOException e) {
                e.printStackTrace();
                System.exit(1);
            }

            OutputStream out = null;

            try {
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
                    writeLogs(log, socket);
                }
                else {
                    log.createNewFile();
                    writeLogs(log, socket);
                }
            }
            catch(IOException e) {
                e.printStackTrace();
                System.exit(1);
            }

        }
    }

}