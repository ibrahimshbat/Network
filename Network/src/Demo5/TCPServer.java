package Demo5;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;


class TCPServer
{
   public static void main(String argv[]) throws Exception
      {
         String clientSentence;
         String capitalizedSentence;
         String toClient;
         ServerSocket welcomeSocket = new ServerSocket(6759);

         while(true)
         {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inToClient =
                    new BufferedReader(new InputStreamReader(System.in));
            BufferedReader inFromClient =
               new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            
            clientSentence = inFromClient.readLine();

            byte learnerInfoData[] = clientSentence.getBytes();
            
            //	if (learnerInfoData.length == 2) {
            		ByteBuffer bbsid = ByteBuffer.wrap(learnerInfoData);
            		long num = bbsid.getLong();
                    System.out.println("Received from client: " + num + '\n');
            //	}
           // toClient = inToClient.readLine();
            //capitalizedSentence = clientSentence.toUpperCase() + '\n';
           // outToClient.writeBytes(toClient + '\n');
         }
      }
}
 