package LeaderLearnerModified;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;

public class LearnersHandler extends Thread {
	PrintWriter out ;
	BufferedReader in ;
	BufferedReader stdIn ;
	 String inputLine;
	Socket sock ;
	boolean listening = true;
	Learner learner;
		
		final LinkedBlockingQueue<String> queuePackets = 
				new LinkedBlockingQueue<String>();
		
		BufferedOutputStream bufferOutput;
		private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();	
		private static HashSet<BufferedReader> readers = new HashSet<BufferedReader>();	

		
	public LearnersHandler(Socket sock, Learner learner) throws IOException {
		this.sock = sock;
		bufferOutput = new BufferedOutputStream(sock.getOutputStream());
		  out =
	                new PrintWriter(bufferOutput, true);                  
	      in = new BufferedReader(
	                new InputStreamReader(sock.getInputStream()));
	      
	      
	      this.learner = learner;
	             
	             
	       stdIn =
	        	    new BufferedReader(
	        	           new InputStreamReader(System.in));
	       
	       writers.add(out);
	       readers.add(in);
	        		 
	}

	public Socket getScoket(){
		return sock;
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

                for (PrintWriter pw : writers){
                pw.println(s);
                }
               
        }
	}
	
//            } catch (IOException e) {
//                if (!sock.isClosed()) {
//                    try {
//                        // this will cause everything to shutdown on
//                        // this learner handler and will help notify
//                        // the learner/observer instantaneously
//                        sock.close();
//                    }
//                }
//            }
        

	

//	public void readPacket() throws IOException, InterruptedException{
//        while (true) {
//            //try {
//                String s;
//                s = queuePackets.poll();
//                if (s == null) {
//                	bufferOutput.flush();
//                    s = queuePackets.take();
//                }
//
//                for (PrintWriter pw : writers){
//                pw.println(s);
//                }
//               
//        }
//	}
	
	public Socket getSocket(){
		return sock;
	}
           
	
	 @Override
     public void run() {
		 
			 new Thread() {
				 public void run(){
		 
					 Thread.currentThread().setName("Sender= " + sock.getRemoteSocketAddress());
					
						 //System.out.println(getSocket().getRemoteSocketAddress().toString());
						try {
							sendPacket();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				 }
				
			 }.start();
	
	       
	        
	        try {
	        	while (true){
				//while ((inputLine = in.readLine()) != null) {
	        		//for (BufferedReader r: readers){
					//System.out.println("Client: " + r.readLine());
	        		//System.out.println("Enter Message for Clients");
	        		//queuePackets.put(stdIn.readLine());
	        		//while ((inputLine = in.readLine()) != null) 
	        			for (BufferedReader r: readers){
	        			System.out.println("Received From: " + r.readLine());
	        		}
				//out.println(stdIn.readLine());
				}
				   
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	        
	        
	    }

	


