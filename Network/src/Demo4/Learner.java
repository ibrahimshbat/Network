package Demo4;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;




public class Learner{       
   
    public static void main(String [] args) throws IOException{
    InputArchive leaderIs;
    OutputArchive leaderOs;  
    QuorumPacket qp = new QuorumPacket();
    Socket sock;
    sock = new Socket();
    BufferedOutputStream bufferedOutput;
    
    InetSocketAddress address = new InetSocketAddress("localhost",6799);
    sock.connect(address,200000);
    leaderIs = BinaryInputArchive.getArchive(new BufferedInputStream(
            sock.getInputStream()));
    bufferedOutput = new BufferedOutputStream(sock.getOutputStream());
    leaderOs = BinaryOutputArchive.getArchive(bufferedOutput);
    
    while (true){

       qp = makePacket();
       leaderOs.writeRecord(qp, "packet");
       sock.close();
       //leaderIs.readRecord(qp, "packet");
    }
           
        }
    
    static QuorumPacket makePacket(){
    	QuorumPacket qp = new QuorumPacket();
    	Scanner read = new Scanner(System.in);
    	System.out.println("Enter Type");
    	qp.setType(read.nextInt());
    	System.out.println("Enter Nymber");
    	qp.setZxid(read.nextLong());
    	System.out.println("Enter Message");
    	qp.setData(read.next().getBytes());
    	return qp;
    	
    }
    }

    
    

