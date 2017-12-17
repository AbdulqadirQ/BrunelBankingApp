
import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class bankThread extends Thread {

	private Socket socket = null; // Socket used for reading client input, and passing through output
	public static ArrayList<Integer> tArray = new ArrayList<Integer>(); // holds client number of each client that connects to the system
	public static ArrayList<Integer> tHash = new ArrayList<Integer>(); // holds thread hash number for each new thread created by the system
	
	public static int threadNum = 0;

	public bankThread(Socket socket) {
	    super("bankThread");
	    this.socket = socket;

	}

	public void run() {
		
		++threadNum; // increments to save number of clients accessing system
		
		tArray.add(threadNum); // adds the client number to tArray
		tHash.add(Thread.currentThread().hashCode()); // adds thread hash number to tHash
		
		System.out.println("Client: " + threadNum + " has accessed the system"); // prints the server the client number accessing the system

	    try{
		    System.out.println("Initialising thread IO connections and state object");
		    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);		    
		    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    String inputLine, outputLine;
		    out.print("Hello, you are client no. " + threadNum +  ". "); // outputs text to client
		    bankState bs = new bankState(); // bankState object created
		    outputLine = bs.processInput(null); // processInput() in bankState object is called initially with no user input
		    out.println(outputLine); // initial message displayed to user
		    
		    // loops while user still wants to make transactions
		    while ((inputLine = in.readLine()) != null) { // while user input is not null
		    	outputLine = bs.processInput(inputLine); // calls processInput()
		    	out.println(outputLine);		    	 // displays processInput() output to user
		    	if (outputLine.equalsIgnoreCase("n"))	// breaks loop when user responds 'n' to stop transactions
		    	break;
		    }

	   // tidying up
       out.close();
       in.close();
       socket.close();

	    }catch (IOException e) {
	      e.printStackTrace();
	    }
	  
	  
	}
}