import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.InetAddress;
import java.util.Scanner;

public class Chat {
    public static void main(String[] args) {
        System.out.println("Entrez votre nom :");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        ReceiveThread threadReceive = new ReceiveThread();
        SendThread threadSend = new SendThread(name);

        threadReceive.start();
        threadSend.start();
    }
}