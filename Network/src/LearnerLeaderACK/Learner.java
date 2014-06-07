package LearnerLeaderACK;

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
import java.util.HashSet;

public class Learner {
	final int PROPOSAL = 1;
	final int COMMIT = 3;
	Socket sockWithLeader;
	Socket sockWitLearner1;
	Socket sockWitLearner2;
	InetSocketAddress addr = new InetSocketAddress("192.168.0.3", 1112);
	InetSocketAddress addr1 = new InetSocketAddress("192.168.0.5", 1333);
	InetSocketAddress addr2 = new InetSocketAddress("192.168.0.4", 1333);


	final HashSet<LearnHandler> followers =
	        new HashSet<LearnHandler>();

	
	
	PrintWriter learnerOs1;
	BufferedReader learnerIs1;
	PrintWriter learnerOs2;
	BufferedReader learnerIs2;
	PrintWriter leaderOs;
	BufferedReader leaderIs;
	BufferedReader stdIn;
	String messageReceive;
	
	boolean running = false;

	
	Learner() throws IOException{
		
		new Thread(){
			public void run(){
				new LearnerThread(Learner.this);
			}
		}.start();
		
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

	
	public Socket getSocket1(){
		
		return sockWitLearner1;
	}
	
	public Socket getSocket2(){
		
		return sockWitLearner2;
	}
	
	public void connect() throws IOException{
		
		if (sockWitLearner1 != null && sockWitLearner1.isConnected()  ||
		            sockWitLearner2 != null && sockWitLearner2.isConnected()){
			
				close();
       
		
				this.sockWitLearner1 = new Socket();
				this.sockWitLearner2 = new Socket();
				this.sockWitLearner1.connect(addr1);
				this.sockWitLearner2.connect(addr2);
				
				learnerOs1 =
		                new PrintWriter(sockWitLearner1.getOutputStream(), true);                  
				learnerIs1 = new BufferedReader(
		                new InputStreamReader(sockWitLearner1.getInputStream()));
				
				new Thread(){
					public void run(){
						try {
							recieivedACK1();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}.start();
				
				learnerOs2 =
		                new PrintWriter(sockWitLearner2.getOutputStream(), true);                  
				learnerIs2 = new BufferedReader(
		                new InputStreamReader(sockWitLearner2.getInputStream()));
				
				new Thread(){
					public void run(){
						try {
							recieivedACK2();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}.start();
				
				
		}else{
			
			this.sockWitLearner1 = new Socket();
			this.sockWitLearner2 = new Socket();
			this.sockWitLearner1.connect(addr1);
			this.sockWitLearner2.connect(addr2);
			
			learnerOs1 =
	                new PrintWriter(sockWitLearner1.getOutputStream(), true);                  
			learnerIs1 = new BufferedReader(
	                new InputStreamReader(sockWitLearner1.getInputStream()));
			
			new Thread(){
				public void run(){
					try {
						recieivedACK1();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
			
			learnerOs2 =
	                new PrintWriter(sockWitLearner2.getOutputStream(), true);    
			learnerIs2 = new BufferedReader(
	                new InputStreamReader(sockWitLearner2.getInputStream()));
			
			new Thread(){
				public void run(){
					try {
						recieivedACK2();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
				
			
		}

		
		
		
	}

	public void Initale() throws IOException{
		//connect();
		 
		
			
		leaderOs.println("Client1: Hi Leader I am ready");
		readPacket();
	}

	 public void registerWithLeader(LearnHandler follower){
     	followers.add(follower);
     	
     }
	 
	 public void recieivedACK1() throws IOException{
		 
		while(true){
			 System.out.println(learnerIs1.readLine());
		 }
	 }
	 
	 public void recieivedACK2() throws IOException{
		 
			while(true){
				 System.out.println(learnerIs2.readLine());
			 }
		 }
	
	public void readPacket() throws IOException{
		
		connect();
		
		while(running){
			messageReceive = leaderIs.readLine();
			//System.out.println(messageReceive);
			sendPacket(messageReceive);			
		}
		
	}
	
	
	
	public void sendPacket(String packet) throws IOException{
		
		String [] parts = packet.split(" ");
		switch(Integer.parseInt(parts[0])){
		
		case PROPOSAL:
			 System.out.println("From Leader: " + packet);
			 //connect();
			 
			 leaderOs.println("Client 1: Thank you I received: Proposal I ACK " + packet);
			 for (LearnHandler f : followers){
				 f.queuedMessage("ACK From 1" + " " + packet);
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
		
	public void close() throws IOException{
		
		 sockWitLearner1.shutdownOutput();
         sockWitLearner1.close();
         sockWitLearner1 = null;
         
         sockWitLearner2.shutdownOutput();
         sockWitLearner2.close();
         sockWitLearner2 = null;
	}
	}
