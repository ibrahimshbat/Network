package LeaderLearnerModified;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;


public class Learner {
	final int PROPOSAL = 1;
	final int COMMIT = 3;
	Socket sockWithLeader;
	Socket sockWithFollower;
	InetSocketAddress addrServer = new InetSocketAddress("192.168.0.6", 1112);
	//InetSocketAddress addrFoloower = new InetSocketAddress("10.66.67.217", 1114);

	
	PrintWriter leaderOs;
	BufferedReader leaderIs;
	PrintWriter learnerOs;
	BufferedReader learnerIs;
	BufferedReader stdIn;
	String messageReceive;
	
	boolean running = false;
	 final HashSet<LearnersHandler> followers =
		        new HashSet<LearnersHandler>();
	
	
	Learner() throws IOException{
		
		
		
		this.sockWithLeader = new Socket();
		this.sockWithLeader.connect(addrServer);
		
//		new Thread (){
//			public void run(){
//
//				try {
//					 ServerSocket serverSocket =  new ServerSocket(1113);
//				
//				
//		            while (true) {
//		            	
//					   new LearnersHandler(serverSocket.accept(), Learner.this).start();
//		            } 
//		            
//		            
//					} catch (IOException e) {
//							
//					e.printStackTrace();
//					
//					}
//	          
//			}
//		}.start();
		
//		this.sockWithFollower = new Socket();
//		this.sockWithFollower.connect(addrFoloower);
		
		
		leaderOs =
	                new PrintWriter(sockWithLeader.getOutputStream(), true);                  
		leaderIs = new BufferedReader(
	                new InputStreamReader(sockWithLeader.getInputStream()));

		stdIn=
	        	    new BufferedReader(
	        	                    new InputStreamReader(System.in));
		
		
		running = true;
		
//		learnerOs =
//                new PrintWriter(sockWithFollower.getOutputStream(), true);                  
//		learnerIs = new BufferedReader(
//                new InputStreamReader(sockWithFollower.getInputStream()));

//		stdIn=
//        	    new BufferedReader(
//        	                    new InputStreamReader(System.in));
		
		
		
	}
		
	
	public void registerFollower(LearnersHandler follower){
		followers.add(follower);
		
	}


	public void Initale() throws IOException{
		leaderOs.println("Client 2: Hi Leader I am ready");
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
			 leaderOs.println("client 2: Thank you I received The Proposal this is my ACK for: " + packet);
			 for (LearnersHandler f : followers){
				 f.queuePackets.add("ACK From " + f.getScoket().getRemoteSocketAddress().toString());
			 }
	   break;
			
			
		case COMMIT:
			System.out.println("From Leader COMMIT" + packet);
			 leaderOs.println("Client 2: Thank you I received a COMMIT : " + packet);
			break;
			
		 default:
			 
			 System.out.println("Unkonw Message");
       
		}
		
		}
		
	}
