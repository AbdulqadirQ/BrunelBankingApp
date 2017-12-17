import java.io.*;
import java.net.*;

public class bankClient {

	public static void main(String[] args) throws IOException {

		Socket bankSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
  
        try {
            bankSocket = new Socket("localhost", 4444);
            out = new PrintWriter(bankSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(bankSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: 4444.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

        System.out.println("Initialised client and IO connections");
        
        /* Print out what the server says 
         * Take the client's response and send it to the server */

        while ((fromServer = in.readLine()) != null) {
            System.out.println("Server: " + fromServer);
            if (fromServer.equalsIgnoreCase("Bye."))
                break;

            fromUser = stdIn.readLine();
            if (fromUser != null) {
                System.out.println("Client: " + fromUser);
                out.println(fromUser);
            }
        }
        out.close();
        in.close();
        stdIn.close();
        bankSocket.close();
    }
}