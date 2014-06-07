package DatagramSocket1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Receiver {

	public static void main(String[] args) throws IOException {
		InetSocketAddress [] addresses = new InetSocketAddress[2];
        //addresses[0] = new InetSocketAddress("Mill047.ncl.ac.uk", 1234);
        //addresses[0] = new InetSocketAddress("Mill032.ncl.ac.uk", 1234);
        final DatagramSocket  senderReceiver = new DatagramSocket(1234);

        
        new Thread (){
        	public void run(){
        		byte [] da = new byte[36];
        		Scanner read = new Scanner(System.in);
        		InetSocketAddress [] addresses = new InetSocketAddress[2];
                addresses[0] = new InetSocketAddress("Mill047.ncl.ac.uk", 1234);
                addresses[1] = new InetSocketAddress("Mill032.ncl.ac.uk", 1234);
                
        		String info = null;
        		while(true){
        			System.out.println("Enter message:");
        			info = read.next();
        			try{
                    for (int i = 0 ; i<addresses.length;i++){ 
                    	info+=" " + InetAddress.getLocalHost();
                    	da=info.getBytes();
            			DatagramPacket packet = new DatagramPacket(da, da.length);
                    	packet.setSocketAddress(addresses[i]);
                    
        			//packet.setLength(36);
        			
						senderReceiver.send(packet);
					
                    }
        			}catch (IOException e){
        				
        			}
        			
        		}
        		
        		
        	}
        }.start();
		
		
		
		//DatagramSocket receiver = new DatagramSocket(1234);
		
		byte [] data = new byte[36];
		
		DatagramPacket packet = new DatagramPacket(data, data.length);
		while(true){
			senderReceiver.receive(packet);
			String s = new String(packet.getData(),0,packet.getLength());
			System.out.println("Receiverd :" + s);

			//receiver.receive(packet);
			//System.out.println("Receiverd " + packet.toString());
		}
	}
}
	


