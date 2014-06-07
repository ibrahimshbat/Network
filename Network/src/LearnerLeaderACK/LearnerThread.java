package LearnerLeaderACK;

import java.io.IOException;
import java.net.ServerSocket;

public class LearnerThread {
	private boolean listening = true;

	public LearnerThread(Learner learner) {
		 try (ServerSocket serverSocket = new ServerSocket(1333)) {
	            while (listening) {
	                new LearnHandler(serverSocket.accept(), learner).start();
	            }
	        } catch (IOException e) {
	            System.err.println("Could not listen on port " + "1333");
	            System.exit(-1);
	        }
	    }
		
	}
	
	
