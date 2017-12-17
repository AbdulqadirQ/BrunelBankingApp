

public class findClient {
	
	static BalanceManager b1 = new BalanceManager();
	static BalanceManager b2 = new BalanceManager();
	static BalanceManager b3 = new BalanceManager();
	static BalanceManager b4 = new BalanceManager();

	public static int whichAccountToTransfer = 0; // changed if a transfer is wanting to be made 
	
	// when user requests to add money to their own account
	public double addtoAccount(double valueToAdd){

		int findThread = whichThread(); // returns index value of current thread
		findThread+=1; // adding 1 to represent client number, as opposed to index number of thread
		
		System.out.println("Adding " + valueToAdd + " to client: " + findThread); // printed to server
		
		if(findThread == 1){
			return b1.add(valueToAdd);
		}else if(findThread == 2){
			return b2.add(valueToAdd);
		}else if(findThread == 3){
			return b3.add(valueToAdd);
		}else if(findThread == 4){
			return b4.add(valueToAdd);
		}
		
		return -999; // error balance returned
	}
	
	// when needing to subtract money from a user account
	public static double subtractFromAccount(double valueToSubtract){
		
		int findThread = whichThread(); // returns index value of current thread		
		findThread+=1; // adding 1 to represent client number, as opposed to index number of thread
		
		System.out.println("Subtracting " + valueToSubtract + " from client: " + findThread); // printed to server
		
		if(findThread == 1){
			return b1.subtract(valueToSubtract);
		}else if(findThread == 2){
			return b2.subtract(valueToSubtract);
		}else if(findThread == 3){
			return b3.subtract(valueToSubtract);
		}else if(findThread == 4){
			return b4.subtract(valueToSubtract);
		}		
		
		return -999; // error balance returned	
	}
	
	// saves which account needs money to be transferred
	public void transferTo(int whichAccount){		
		whichAccountToTransfer = whichAccount;
	}
	
	public static double transferToClient(double howMuchToTransfer){
		
		System.out.println("Transferrng " + howMuchToTransfer + " to client: " + whichAccountToTransfer);
		
		subtractFromAccount(howMuchToTransfer); // money is first subtracted from requesting user's account
		
		if(whichAccountToTransfer == 1){
			return b1.add(howMuchToTransfer);
		}else if(whichAccountToTransfer == 2){
			return b2.add(howMuchToTransfer);
		}else if(whichAccountToTransfer == 3){
			return b3.add(howMuchToTransfer);
		}else if(whichAccountToTransfer == 4){
			return b4.add(howMuchToTransfer);
		}
		
		//accMoney[whichAccountToTransfer] = accMoney[whichAccountToTransfer] + howMuchToTransfer; // money is added to requested client's account
		
		return -999; // error balance returned
	}
	
	// returns thread index of current thread
	public static int whichThread(){
		
		int thisThread = Thread.currentThread().hashCode();
		
		// cycles through all threads and matches with current thread
		for(int i=0; i<bankThread.tHash.size(); i++){
			
			if(thisThread == bankThread.tHash.get(i)){ // if current thread's hash code is the same as tHash(i) hash code then..
				int threadIndex = bankThread.tHash.indexOf(bankThread.tHash.get(i)); // save index of hash code in tHash to 'threadIndex'
				return threadIndex; // return this index
			}
		}
		
		return -999; // return error
	}

}
