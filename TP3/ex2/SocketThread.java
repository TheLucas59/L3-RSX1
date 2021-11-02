import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.File;

public class SocketThread extends Thread {
    private Socket socket;

    public SocketThread(Socket s) {
        super();
        this.socket = s;
    }

    public void run() {
        OutputStream out = null;
        InputStream in = null;
        byte[] buffer = new byte[512];

        try {
            out = socket.getOutputStream();
            in = socket.getInputStream();
        }
        catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            int read = in.read(buffer);
            while(read != -1) {
                for(Socket s : TCPServer.activeSockets) {
                    try {
                        if(!s.equals(this.socket)) {
                            s.getOutputStream().write(buffer);
                        }
                    }
                    catch(IOException e) {
                        e.printStackTrace();
                        System.exit(1);
                    }
                }
                buffer = new byte[512];
                read = in.read(buffer);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            TCPServer.activeSockets.remove(socket);
            in.close();
            out.close();
            socket.close();
        }
        catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
