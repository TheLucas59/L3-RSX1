import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.InetAddress;

public class ReceiveThread implements Runnable {

    public void run() {
        MulticastSocket s = null;
		try {
			s = new MulticastSocket(7654);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

        InetAddress multicast = null;
        try {
            multicast = InetAddress.getByName("224.0.0.1");
        }
        catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            s.joinGroup(multicast);
        }
        catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
		DatagramPacket p = null;
		
		try {
            while(true) {
                p = new DatagramPacket(new byte[512], 512);
                s.receive(p);
                System.out.println("mesage : " + new String(p.getData()));
            }
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
    }
}