package Demo5;

import java.io.*;
import java.net.*;

class TCPClient
{
 public static void main(String argv[]) throws Exception
 {
	 long m = 65;
  String sentence;
  String modifiedSentence;
  BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
  Socket clientSocket = new Socket("localhost", 6788);
  DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
  sentence = inFromUser.readLine();

	
   ByteArrayOutputStream baos = new ByteArrayOutputStream();
   DataOutputStream dos = new DataOutputStream(baos);
   dos.writeLong(m);
    dos.close();
	byte[] longBytes = baos.toByteArray();
	
    outToServer.write(longBytes);

  modifiedSentence = inFromServer.readLine();
  //System.out.println("FROM SERVER: " + modifiedSentence);
  clientSocket.close();
 }
} 