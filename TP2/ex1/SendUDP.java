import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.InetAddress;

public class SendUDP {

    public static void main(String[] args) {
		DatagramSocket s = null;
		try {
			s = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
			System.exit(1);
		}

        String message = args[2];
        byte[] buffer = message.getBytes();

        InetAddress dst = null;
        try {
            dst = InetAddress.getByName(args[0]);
        }
        catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        int dstPort = Integer.parseInt(args[1]);
		
		DatagramPacket p = new DatagramPacket(buffer, buffer.length, dst, dstPort);

        try {
            s.send(p);
        }
        catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        s.close();
    }
}
	