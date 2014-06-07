package LearnerLeaderACK;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import LeaderLearnerModified.LearnersHandler;


public class Leader {
	
		   static Scanner read = new Scanner(System.in);
		   static int messageType;
		   String message;
	        boolean listening = false;
	        boolean running = false;
	        final HashSet<LearnerHandler> followers =
			        new HashSet<LearnerHandler>();
		
	         
	        public void initialize(){
	       
	        try (ServerSocket serverSocket = new ServerSocket(1112)) {
	            while (listening) {
	                new LearnerHandler(serverSocket.accept(), Leader.this).start();
	            }
	        } catch (IOException e) {
	            System.err.println("Could not listen on port " + "1112");
	            System.exit(-1);
	        }
	    }
	        
	        public void registerWithLeader(LearnerHandler follower){
	        	followers.add(follower);
	        	
	        }
	        
	        public void startReceiveMessage(){
	         	listening = true;
	         	running = true;
	        	
	        	new Thread(){
	        		
	        		public void run(){
	        			initialize();
	        		}
	        	}.start();
	        	
	        	while (running){
	        		
	        		System.out.println("Enter message type");
	        		System.out.println("1 ---> PROPOSAL");
	        		System.out.println("2 ---> COMMIT");
	        		System.out.println("4 ---> Finish");
	        		messageType = read.nextInt();
	        		switch(messageType){
	        		
	        		case 1:
	        			System.out.println("Enter PROPOSAL");
	        			message = null;
	        			message = "1 ";
	        			message += read.next();
	        			for (LearnerHandler f:followers){
	        				f.queuedMessage(message);
	        			}
//	        			Iterator<LearnerHandler> it;
//	        			it = followers.iterator();
	        			//it.next().queuedMessage(message);
	        			break;
	        		case 2:
	        			System.out.println("Enter COMMIT");
	        			message = null;
	        			message = "3 ";
	        			message += read.next();
	        			for (LearnerHandler f:followers){
	        				f.queuedMessage(message);
	        			}
	        			break;
	        		case 3:
	 	        			System.out.println("Enter Finished Connection");
	 	        			message = null;
		        			message = "4 ";
		        			message += read.next();
		        			
	 	        			for (LearnerHandler f:followers){
	 	        				f.queuedMessage(message);
	 	        			}
	        			break;
	        			
	        			
	        		
	        		
	        		}
	        		
	        		
	        	}
	        	}
	        	
	        	
	        }
	        
	 
	
	

