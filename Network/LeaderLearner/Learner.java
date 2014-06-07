package LeaderLearner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Learner {
	Socket sockWithLeader;
	InetSocketAddress addr = new InetSocketAddress("localhost", 1112);
	
	PrintWriter leaderOs;
	BufferedReader leaderIs;
	BufferedReader stdIn;
	String messageReceive;
	
	boolean running = false;
	
	
	Learner() throws IOException{
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
		leaderOs.println("Hi Leader I am ready");
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
		
		switch(packet){
		
		case "PROPOSAL":
			 System.out.println("From Leader: " + packet);
			 leaderOs.println("Thank you I received: " + packet);
	   break;
			
			
		case "COMMIT":
			System.out.println("From Leader" + packet);
			 leaderOs.println("Thank you I received: " + packet);
			break;
			
		 default:
			 
			 System.out.println("Unkonw Message");
       
		}
		
		}
		
	}
