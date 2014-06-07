package Demo3;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


class TCPServer
{
   public static void main(String argv[]) throws Exception
      {
         String clientSentence;
         String capitalizedSentence;
         String toClient;
         ServerSocket welcomeSocket = new ServerSocket(6788);

         while(true)
         {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inToClient =
                    new BufferedReader(new InputStreamReader(System.in));
            BufferedReader inFromClient =
               new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            System.out.println("Received from client: " + clientSentence + '\n');
            toClient = inToClient.readLine();
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(toClient + '\n');
            outToClient.flush();
         }
      }
} 