import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ReceiveUDP {
	
	public static void main(String[] args) {
		DatagramSocket s = null;
		try {
			s = new DatagramSocket(Integer.parseInt(args[0]));
		} catch (SocketException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		DatagramPacket p = new DatagramPacket(new byte[512], 512);
		
		try {
			s.receive(p);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		System.out.println("paquet reçu de :" + p.getAddress() +
				"\nport :" + p.getPort() +
				"\ntaille : " + p.getLength());
		
		System.out.println("message : " + new String(p.getData()));
		s.close();
	}
	
}
