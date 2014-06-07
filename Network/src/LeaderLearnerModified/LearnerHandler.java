package LeaderLearnerModified;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class LearnerHandler extends Thread {
	PrintWriter out ;
	BufferedReader in ;
	BufferedReader stdIn ;
	 String inputLine;
		Socket sock ;
		
	LearnerHandler(Socket sock) throws IOException{
		this.sock = sock;
		  out =
	                new PrintWriter(sock.getOutputStream(), true);                  
	             in = new BufferedReader(
	                new InputStreamReader(sock.getInputStream()));
	        		  stdIn =
	        	                new BufferedReader(
	        	                    new InputStreamReader(System.in));
	}
	
	
	
	 @Override
     public void run() {
	       
	        
	        try {
				while ((inputLine = in.readLine()) != null) {
				System.out.println("Client: " + inputLine);
				out.println(stdIn.readLine());
				    }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	    }
}
	


