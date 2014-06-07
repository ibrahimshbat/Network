package DatagramSocket1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Scanner;


public class Sender {

	public static void main(String[] args) throws IOException {
		Scanner read = new Scanner(System.in);
		String input;
		DatagramSocket sender = new DatagramSocket();
		
		byte b[] = new byte[36];
        ByteBuffer responseBuffer = ByteBuffer.wrap(b);
        responseBuffer.clear();
        responseBuffer.getInt(); // Skip the xid
        responseBuffer.putLong(1111);
        

		
		//byte [] buffer = "0123456789".getBytes();
        
		InetAddress address =InetAddress.getLocalHost();
		
		while(true){
			DatagramPacket packet = new DatagramPacket(b, b.length, address, 1234);
            
			//DatagramPacket packet = new DatagramPacket();
			//packet.setData(b);
			packet.setAddress(address);
			//packet.setLength(36);
			sender.send(packet);
			System.out.println("Enter data :");
			input = read.next();
			b = input.getBytes();
		}
		

	}

}
