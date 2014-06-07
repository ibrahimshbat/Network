package DatagramSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receiver {

	public static void main(String[] args) throws IOException {
		
		DatagramSocket receiver = new DatagramSocket(1234);
		
		byte [] data = new byte[36];
		
		
		DatagramPacket packet = new DatagramPacket(data, data.length);
		while(true){
			receiver.receive(packet);
			String s = new String(packet.getData(),0,packet.getLength()-3);
			System.out.println("Receiverd :" + s);

			//receiver.receive(packet);
			//System.out.println("Receiverd " + packet.toString());
		}
	}

}
