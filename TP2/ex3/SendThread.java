import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.InetAddress;
import java.util.Scanner;

public class SendThread extends Thread {
    private String name;

    public SendThread(String name) {
        super();
        this.name = name;
    }

    public void run() {
        DatagramSocket s = null;
        try {
            s = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Scanner sc = new Scanner(System.in);

        InetAddress dst = null;
        try {
            dst = InetAddress.getByName("224.0.0.1");
        }
        catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        int dstPort = 7654;

        try {
            while(true) {
                String entree = sc.nextLine();
                String message = this.name + ": " + entree;
                byte[] buffer = message.getBytes();
                DatagramPacket p = new DatagramPacket(buffer, buffer.length, dst, dstPort);
                s.send(p);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        finally {
            s.close();
            sc.close();
        }
    }
}