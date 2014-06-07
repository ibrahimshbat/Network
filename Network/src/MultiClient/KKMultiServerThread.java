package MultiClient;
import java.net.*;
import java.io.*;
 
public class KKMultiServerThread extends Thread {
    private Socket socket = null;
 
    public KKMultiServerThread(Socket socket) {
        super("KKMultiServerThread");
        this.socket = socket;
    }
     
    public void run() {
 
        try (
        		 PrintWriter out =
                 new PrintWriter(socket.getOutputStream(), true);                  
             BufferedReader in = new BufferedReader(
                 new InputStreamReader(socket.getInputStream()));
         		 BufferedReader stdIn =
         	                new BufferedReader(
         	                    new InputStreamReader(System.in))
         ) {
        	String inputLine, outputLine;
 
            while ((inputLine = in.readLine()) != null) {
            	System.out.println("Client: " + inputLine);
            	outputLine = (stdIn.readLine());
                out.println(outputLine);
                if (outputLine.equals("Bye"))
                    break;
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}