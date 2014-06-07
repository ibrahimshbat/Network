package Demo4;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class Leader {
    
    

    public static void main(String [] arg) throws IOException{
    ServerSocket ss;
    BinaryInputArchive ia;
    BinaryOutputArchive oa;
    BufferedOutputStream bufferedOutput;

    ss = new ServerSocket(6799);
 
    Socket s = ss.accept();
	   ia = BinaryInputArchive.getArchive(new BufferedInputStream(s
	            .getInputStream()));
	    bufferedOutput = new BufferedOutputStream(s.getOutputStream());
	    oa = BinaryOutputArchive.getArchive(bufferedOutput);

	   
   
    		System.out.println("Leader is waiting");
              while (true){
            	   
            	  
            	  	QuorumPacket qp = new QuorumPacket();
            	  	
            	    ia.readRecord(qp, "packet");
            	   System.out.println("Packet From Client");

            	    System.out.println(qp.getType()); 
            	    System.out.println(qp.getZxid());
            	    System.out.println(qp.getData());
            	    
              }

           
    }

    
}
