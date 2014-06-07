
package Demo6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client2 {
    public static void main(String[] args) throws IOException {
         
       
 
        try (
        		ServerSocket serverSocket1 = new ServerSocket(1223);
        		Socket serverSocket11 = serverSocket1.accept();

             final Socket toClientSocket1 = new Socket("10.66.64.87", 1223);
            Socket echoSocket = new Socket("10.66.64.89", 1112);
            PrintWriter out =
                new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in));
            PrintWriter out1 =
            new PrintWriter(serverSocket11.getOutputStream(), true);
        BufferedReader in1 =
            new BufferedReader(
                new InputStreamReader(serverSocket11.getInputStream()));
        BufferedReader stdIn1 =
            new BufferedReader(
                new InputStreamReader(System.in));
        
        PrintWriter out12 =
        new PrintWriter(toClientSocket1.getOutputStream(), true);
    BufferedReader in12 =
        new BufferedReader(
            new InputStreamReader(toClientSocket1.getInputStream()));
    BufferedReader stdIn12 =
        new BufferedReader(
            new InputStreamReader(System.in))
    
        ) {
        	
            String userInput;
           while ((userInput = stdIn.readLine()) != null) {
            //userInput = stdIn.readLine();
                out.println(userInput);
                System.out.println("Server: " + in.readLine());    
//                System.out.print("Enter Message to Client: ");
//                userInput = stdIn1.readLine();
//                out12.println(userInput);
//                System.out.println("Client 1: " + in1.readLine());

          }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + "localhost");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
            		"localhost");
            System.exit(1);
        }
    }
}