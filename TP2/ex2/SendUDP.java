import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.InetAddress;
import java.util.Scanner;

public class SendUDP {

    public static void main(String[] args) {
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
                byte[] buffer = sc.nextLine().getBytes();
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
	