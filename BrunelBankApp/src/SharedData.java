public class SharedData {

	private static boolean accessing=false; // true a thread has a lock, false otherwise

	  // attempt to acquire a lock
	public synchronized boolean acquireLock() throws InterruptedException{
        int me = Thread.currentThread().hashCode(); // get a ref to the current thread - all threads here have the same name, so they are differentiated by their Hash codes
        System.out.println("Thread: " + me +" is attempting to acquire a lock!");
		
        while (accessing) {  // while someone else is accessing or threadsWaiting > 0
        	System.out.println("Thread: " + me +" waiting to get a lock as someone else is accessing...");
        	//wait for the lock to be released - see releaseLock() below
        	wait();
        	return(false);
	    }
	    // nobody has got a lock so get one
	    accessing = true;
	    System.out.println("Thread " + me +" got a lock!"); 
	    return(true);
	}

	  // Releases a lock to when a thread is finished
	  
	public synchronized void releaseLock() {
		//release the lock and tell everyone
		accessing = false;
		notifyAll();
		int me = Thread.currentThread().hashCode(); // get a ref to the current thread
		System.out.println("Thread " + me + " released a lock!");
	}
	    
}
