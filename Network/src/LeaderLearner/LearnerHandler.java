package LeaderLearner;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;

public class LearnerHandler extends Thread {
	PrintWriter out ;
	BufferedOutputStream bufferOutput;
	BufferedReader in ;
	BufferedReader stdIn ;
	 String inputLine;
	Socket sock ;
	private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();	
	private static HashSet<BufferedReader> readers = new HashSet<BufferedReader>();
	 LinkedBlockingQueue<String> queuePackets = 
			new LinkedBlockingQueue<String>();
		
	Leader leader;
		
	LearnerHandler(Socket sock, Leader leader) throws IOException{
		this.sock = sock;
		bufferOutput = new BufferedOutputStream(sock.getOutputStream());
		  out =
	                new PrintWriter(bufferOutput, true);                  
	      in = new BufferedReader(
	                 new InputStreamReader(sock.getInputStream()));
	      stdIn =
	                 new BufferedReader(
	        	          new InputStreamReader(System.in));
	        		  
	      this.leader = leader;  	
	      writers.add(out);
	      readers.add(in);
	}
	
	public Socket getScoket(){
		return sock;
	}
	
	public void queuedMessage(String packet){
		queuePackets.add(packet);
		
	}
	
	public void sendPacket() throws IOException, InterruptedException{
        while (true) {
            //try {
                String s;
                s = queuePackets.poll();
                if (s == null) {
                	bufferOutput.flush();
                    s = queuePackets.take();
                }
                //for (PrintWriter pw : writers){
                out.println(s);
                //}
               
        }
	}
	
	public void receivedPacket() throws IOException{
	
	
	while (true){
    			//for (BufferedReader r: readers){
    			System.out.println("Received From: " + in.readLine() + "From " + getScoket().getInetAddress().toString());
    		//}
		}
	}
	
	
	 @Override
     public void run() {
	       
	        leader.registerWithLeader(this);
	        
	        new Thread(){
	        	public void run(){
	        		try {
						sendPacket();
					} catch (IOException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        }.start();
	        
	        new Thread(){
	        	public void run(){
	        		try {
						receivedPacket();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        }.start();
	        
//	        try {
//				while ((inputLine = in.readLine()) != null) {
//				System.out.println("Client: " + inputLine);
//				out.println(stdIn.readLine());
//				    }
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	        
	        
	    }
}
	


