import java.net.*;
import java.io.*;


public class bankServer {
	
	public static void main(String[] args) throws IOException {

	    InetAddress computerAddr = null;
	    //Now store the local computer's name
	    try{
	    	computerAddr = InetAddress.getLocalHost();
	    }
	    catch(UnknownHostException e){
	    	System.out.println(e);
	    }
	
	    System.out.println("The address of this computer is... " + computerAddr.getHostName());
	
	    ServerSocket serverSocket = null;
	    boolean listening = true;
	
	    try{
	    	serverSocket = new ServerSocket(4444);
	    }catch (IOException e) {
	    	System.err.println("Could not listen on port: 4444.");
	    	System.exit(-1);
	    }
	    System.out.println("Server started");
	    
	    while (listening){
	      
	    	bankThread newThread = new bankThread((serverSocket.accept()));
	    	newThread.start();
	    	System.out.println("New server thread started");
	    }
	    serverSocket.close();
	}
}