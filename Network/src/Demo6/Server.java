package Demo6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static void main(String[] args) throws IOException {
         
       
        try (
            ServerSocket serverSocket =
                new ServerSocket(1112);
            Socket clientSocket = serverSocket.accept();    
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);                  
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        		 BufferedReader stdIn =
        	                new BufferedReader(
        	                    new InputStreamReader(System.in))
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
            	System.out.println("Client: " + inputLine);
                out.println(stdIn.readLine());
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + "1112" + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}