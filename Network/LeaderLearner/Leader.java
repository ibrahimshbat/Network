package LeaderLearner;

import java.io.IOException;
import java.net.ServerSocket;


public class Leader {
	
	 public static void main(String[] args) throws IOException {
		 
		   
	        boolean listening = true;
	         
	        try (ServerSocket serverSocket = new ServerSocket(1112)) {
	            while (listening) {
	                new LearnerHandler(serverSocket.accept()).start();
	            }
	        } catch (IOException e) {
	            System.err.println("Could not listen on port " + "1112");
	            System.exit(-1);
	        }
	    }
	}

