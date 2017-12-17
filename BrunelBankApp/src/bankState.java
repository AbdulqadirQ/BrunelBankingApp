public class bankState {
	// Possible states a thread can be in
    private static final int welcome = 0;
    private static final int addOrSubtract = 1;
    private static final int addTransaction = 2;
    private static final int subtractTransaction = 3;
    private static final int transferToAnotherAccount = 4;
    private static final int howMuchToTransfer = 5;
    private static final int anotherTransaction = 6;
    
    private int state = welcome;    
    
    	// called from bankThread()
    	public String processInput(String theInput) {
    		
    		String theOutput = null;
    		findClient findC = new findClient();
    		double val = 0;
    		double balance = 0;
    		
    		
    		switch(state){
    		case welcome:
    			
    			theOutput = "Would you like to add, subtract or transfer money to another account?";
    			state = addOrSubtract;
    			break;
    		
    			// user chooses between adding/subracting to their account, or transferring to another account	
    		case addOrSubtract:
    			if(theInput.equalsIgnoreCase("add")){
    				theOutput = "How much would you like to add?";
    				state = addTransaction;
    				break;
    			}
    			else if(theInput.equalsIgnoreCase("subtract")){
    				theOutput = "How much would you like to subtract?";
    				state = subtractTransaction;
    				break;
    			}else if(theInput.equalsIgnoreCase("transfer")){
    				theOutput = "Which client would you like to transfer to? Choose from client: " + bankThread.tArray; // ArrayList tArray displays all clients currently connected
    				state = transferToAnotherAccount;
    			}else{
    				theOutput = "That was not recognised. Please type in 'add' or 'subtract'"; // error handling			
    			}   			
    			break;	
    			
    		case addTransaction:

    			if(isDouble(theInput)==false){
    				theOutput = "That was not recognised. Please enter a value"; // error handling
    				break;    				
    			}
    			
    			val = Double.parseDouble(theInput); //converting user input into Double format
    			balance = findC.addtoAccount(val); //calls bankAccount class to add the amount
    			
    			theOutput = "Your balance is now £" + balance + ". Would you like to make another transaction? (y/n)";
    			
    			state = anotherTransaction;	
    			break;
    			
    		case subtractTransaction:	
    			
    			if(isDouble(theInput)==false){
    				theOutput = "That was not recognised. Please enter a value"; // error handling
    				break;    				
    			}
    			
    			val = Double.parseDouble(theInput); //converting user input into Double format
    			balance = findC.subtractFromAccount(val); //calls bankAccount class to add the amount
    			
    			theOutput = "Your balance is now £" + balance + ". Would you like to make another transaction? (y/n)";
    			
    			state = anotherTransaction;	
    			break;
    			
    		case transferToAnotherAccount:

    			if(theInput.equals("1") || (theInput.equals("2") || theInput.equals("3") || theInput.equals("4"))){ // only allows up to 4 clients
    				findC.transferTo(Integer.parseInt(theInput)); //calls bankAccount class to save which client needs transferring to
    				theOutput = "Client " + theInput + " has been selected. How much will you transfer?";
    				state = howMuchToTransfer;
    				break;
    			}else{
    				theOutput = "That was not recognised. Please enter 1,2,3 or 4."; // error handling
    				break;
    			}	  
    			
    		case howMuchToTransfer:
    			
    			if(isDouble(theInput)==false){
    				theOutput = "That was not recognised. Please enter a value"; // error handling
    				break;    				
    			}
    			
    			val = Double.parseDouble(theInput);
    			balance = findC.transferToClient(val); // calls bankAccount to process the transaction
    			theOutput = "Client " + findC.whichAccountToTransfer + "'s balance is now £" + balance + ". Would you like to make another transaction? (y/n)";
    			state = anotherTransaction;
    			break;
    		
    			//final state to either process another transaction, or to close off the connection
    		case anotherTransaction:
    			if(theInput.equalsIgnoreCase("y")){
    				theOutput = "Returning to start. Press enter";
    				state = welcome;
    				break;
    			}else if(theInput.equalsIgnoreCase("n")){
    				theOutput = "Bye";

    			}else{
    				theOutput = "That was not recognised. Please type in 'y' or 'n'"; // error handling
    				break;
    			}
    		}   		
    		
			return theOutput;
    		
    	}	
    	private boolean isDouble(String theInput){
    		try{
	    	  Double.parseDouble(theInput);
	    	  return true;
	    	}
	    	catch(NumberFormatException e)
	    	{
	    	  return false;
	    	}
    	}

	}
