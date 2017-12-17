import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// VIEW
public class JourneyView {
	
	public void userInterface() {

		JourneyController jController;
		Scanner scanner = new Scanner(System.in);	
		boolean endApplication = false;
		
		// while loop included here to allow use of 'continue' statements
		while(!endApplication) {

			// object's use will be the highest object within the stack - no other object is created on top of this
			jController = new JourneyController(); // new object created for each user of the application
			
			System.out.println("Welcome. Please enter the destination you want to travel to:");
			String destination = scanner.nextLine();
			
			if(!jController.checkDestination(destination)){
				System.out.println("Invalid destination entered. Please try again later.");
				continue;
			}
			
			System.out.println("Seats available: " + jController.getSeats().get(jController.getSelectedDestinationIndex()));
			
			System.out.println("Please pay now:");
			
			// By not using 'Scanner.nextDouble()', avoid issue with not reading immediate next user input into Scanner
			String userInput = scanner.nextLine();
			
			if(!jController.checkIfNumeric(userInput)) {
				System.out.println("You have entered an invalid input. Please try again later");
				continue;
			}
			
			double moneyPaid = Double.parseDouble(userInput); // safe to now convert userInput to Double
			
			if(!jController.checkPrices(moneyPaid)){
				System.out.println("Insufficient funds entered. Please try again later.");
				continue;
			}
			
			if(!jController.checkSeats()){
				System.out.println("Sorry, there are no available seats. Please select a different destination.");
				continue;
			}
			
			System.out.println("Purchase successful! Please board the train carefully.");	
			float change = (float) (moneyPaid - jController.getPrice());
			
			HashMap<String, Integer> coinFreq = jController.changeReturned(change);
			
			System.out.print("Your change is: ");
			// Entry class used to print HashMap Keys and Values
			for(Map.Entry<String, Integer> entry : coinFreq.entrySet()) {
				
				System.out.print(entry.getValue() + " " + entry.getKey()+ "'s, ");
				
			}
			System.out.print("for a total of ");
			System.out.printf("£%#.2f",change); // format to currency 
			System.out.println("\nJourney begins at: " + jController.getStartTime() + "\nJourney ends at: " + jController.getEndTime());
			System.out.println();
			System.out.println("Shutdown machine? [y/n]");
			userInput = scanner.nextLine();

			if(userInput.equalsIgnoreCase("y")){
				endApplication=true;
				System.out.println("Exiting... ");
			}
			
		}

	}

}
