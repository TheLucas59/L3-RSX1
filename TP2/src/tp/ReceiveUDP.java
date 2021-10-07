package tp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ReceiveUDP {
	
	public static void main(String[] args) {
		DatagramSocket s = null;
		try {
			s = new DatagramSocket(1024);
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
				"port :" + p.getPort() +
				"taille : " + p.getLength());
		
		System.out.println("mesage : " + new String(p.getData()));
		s.close();
	}
	
}
