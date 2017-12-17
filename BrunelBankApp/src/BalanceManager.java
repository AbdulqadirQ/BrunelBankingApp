public class BalanceManager{

	private Account balance = new Account(); 
	SharedData mySharedData = new SharedData();
	
	public double add(double valueToAdd){		
		double tempB;
		tempB = balance.getBalance() + valueToAdd;
		balance.setBalance(tempB);
		/*try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		mySharedData.releaseLock();
		
		return tempB;
	}
	
	public double subtract(double valueToSubtract){
		double tempB;
		tempB = balance.getBalance() - valueToSubtract;
		balance.setBalance(tempB);
		mySharedData.releaseLock();
				
		return tempB;
	}

static class Account{
	private double balance;
	SharedData mySharedData = new SharedData();

	
	private double getBalance(){	
		int clientNo = findClient.whichThread()+1;
		try{
		      // Acquire the lock using the acquireLock() method
		      // The thread will pause here on wait() until it gets a lock
		      
		  	 	while(mySharedData.acquireLock()==false){			  	 		
		  	 		System.out.println("Client " + clientNo + " is being deadlocked!");
		  	 	}
		  	 	return balance;
	  	 	
		    }catch(InterruptedException e) {
		    	System.err.println("Failed to get lock when reading:"+e);
		    }
		
			return -999;
	}
	
	private void setBalance(double valueToSet){		
		balance = valueToSet;
	}

	
}

}
