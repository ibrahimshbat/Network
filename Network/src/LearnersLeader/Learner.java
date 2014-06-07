package LearnersLeader;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;

public class Learner {
	final int PROPOSAL = 1;
	final int COMMIT = 3;
	Socket sockWithLeader;
	InetSocketAddress addr = new InetSocketAddress("localhost", 1112);
	DatagramSocket sockgram;
	
	
	PrintWriter leaderOs;
	BufferedReader leaderIs;
	BufferedReader stdIn;
	String messageReceive;
	
	boolean running = false;
	//InetSocketAddress addr1 = new InetSocketAddress("192.168.0.5", 1222);
	InetSocketAddress addr2 = new InetSocketAddress("192.168.0.6", 1222);

   private  InetSocketAddress[] addresses = new InetSocketAddress[1];
	
	
	Learner() throws IOException{
		sockgram = new DatagramSocket(1222);
		addresses[0] = addr2;
		//addresses[1] = addr2;
		this.sockWithLeader = new Socket();
		this.sockWithLeader.connect(addr);
		
		leaderOs =
	                new PrintWriter(sockWithLeader.getOutputStream(), true);                  
		leaderIs = new BufferedReader(
	                new InputStreamReader(sockWithLeader.getInputStream()));

		stdIn=
	        	    new BufferedReader(
	        	                    new InputStreamReader(System.in));
		running = true;
	}


	public void Initale() throws IOException{
		new Thread(){
			public void run(){
	                while (true) {
	                    byte[] buffer = new byte[20];
	                    
	                    DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
	                    try {
	                    	
						sockgram.receive(dp);
						
						byte[] data=dp.getData();
						int offset=dp.getOffset();
						int length=dp.getLength();
						System.out.println("Test " + new String(data, offset, length)) ;
						
	                    ByteArrayInputStream bais = new ByteArrayInputStream(dp.getData(),
	                            dp.getOffset(), dp.getLength());
	                    DataInputStream dis = new DataInputStream(bais);

	                    int senderIP;
						
						senderIP = dis.readInt();
						
	                    byte[] datas = new byte[dp.getLength()];
	                 
						dis.read(datas);
						
						
		                System.out.println("From " + senderIP + " " + datas);

						
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                    
	                    
	                }
	                    
	        }
	    }.start();

		
		leaderOs.println("Client1: Hi Leader I am ready");
		readPacket();
	}

	
	public void readPacket() throws IOException{
		
		while(running){
			messageReceive = leaderIs.readLine();
			//System.out.println(messageReceive);
			sendPacket(messageReceive);			
		}
		
	}
	
	
	
	public void sendPacket(String packet){
		
		String [] parts = packet.split(" ");
		switch(Integer.parseInt(parts[0])){
		
		case PROPOSAL:
			 System.out.println("From Leader: " + packet);
			 leaderOs.println("Client 1: Thank you I received: Proposal I ACK " + packet);
			 
			 byte[] data = new byte[packet.length() + 4];
		        ByteBuffer.wrap(data).putInt(1).put(packet.getBytes());
		        DatagramPacket dp = new DatagramPacket(data, data.length);
		        
		        for (int i = 0; i < addresses.length ; i++) {
		            dp.setSocketAddress(addresses[i]);
		            try {
		                sockgram.send(dp);
		            } catch (IOException e) {
		                throw new RuntimeException(e);
		            }
		        }
		    

	   break;
			
			
		case COMMIT:
			System.out.println("From Leader " + packet);
			 leaderOs.println("Client 1: Thank you I received: " + packet);
			break;
			
		 default:
			 
			 System.out.println("Unkonw Message");
       
		}
		
		}
		
	}
