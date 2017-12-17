import java.util.ArrayList;
import java.util.HashMap;


//CONTROLLER - contains all logic and interacts with model and view 
public class JourneyController {

	// static member variables defined to ensure lists stay consistent for all objects of class
	private static ArrayList<String> linesFromFile;
	private static ArrayList<String> destinations;
	private static ArrayList<Double> prices;
	private static ArrayList<Integer> seats;
	private static ArrayList<String> startTimes;
	private static ArrayList<String> endTimes;
	private int selectedDestinationIndex; // not static to allow index to change for each object


	// entry point of Controller - calls setLists and interacts with the View
	public void runController(){		
		JourneyView view = new JourneyView();

		setLists();
		view.userInterface();	
		
	}
	
	// interacts with the Model to initialise all lists
	public void setLists(){
		JourneyModel jModel = new JourneyModel();
		
		linesFromFile = jModel.readCSV();
		destinations = jModel.setDestinations(linesFromFile);
		prices = jModel.setPrices(linesFromFile);
		seats = jModel.setSeats(linesFromFile);
		startTimes = jModel.setStartTimes(linesFromFile);
		endTimes = jModel.setEndTimes(linesFromFile);
	}

	// returns true if user's input does exist within 'destinations' list. Also sets index of CSV file row for later use
	public boolean checkDestination(String userDestination) {

		for (int i = 0; i < destinations.size(); i++) {
			if (userDestination.equalsIgnoreCase(destinations.get(i))) {
				// defines row of CSV file that is being used by this object (based on the destination they're going to)
				selectedDestinationIndex = i; 
				return true;
			}
		}

		return false;
	}
	
	// returns true if user input is at least the value required for the journey
	public boolean checkPrices(double userMoney) {

		if (userMoney >= prices.get(selectedDestinationIndex)) {
			return true;
		}
		return false;
	}

	// returns true if seats are available for journey. Updates list 'seats'
	public boolean checkSeats() {

		if (seats.get(selectedDestinationIndex) > 0) {
			// replaces the seat availability for the destination with a number 1 less than before
			seats.set(selectedDestinationIndex, seats.get(selectedDestinationIndex)-1);
			return true;
		}
		return false;
	}
	
	// returns price for the particular journey
	public double getPrice() {
		return prices.get(selectedDestinationIndex);
	}

	// returns start time for the particular journey
	public String getStartTime() {
		return startTimes.get((selectedDestinationIndex));
	}
	
	// returns end time for the particular journey
	public String getEndTime() {
		return endTimes.get((selectedDestinationIndex));
	}

	public HashMap<String, Integer> changeReturned(float change) {
		// HashMap: Key = coin type, Value = coin frequency
		HashMap<String, Integer> coinFreq = new HashMap<String, Integer>();
		// float and whole numbers used to implement accurate calculations
		int coins = (int) ((change * 100) / 50);
		change = (float) (change * 100 % 50);
		coinFreq.put("50p", coins);

		coins = (int) ((change) / 20);
		change = (float) (change % 20);
		coinFreq.put("20p", coins);

		coins = (int) ((change) / 10);
		change = (float) (change % 10);
		coinFreq.put("10p", coins);

		coins = (int) ((change) / 5);
		change = (float) (change % 5);
		coinFreq.put("5p", coins);

		return coinFreq;

	}

	// returns true if String input is numeric
	public boolean checkIfNumeric(String moneyPaid) {
	    try
	    {
	        Double.parseDouble(moneyPaid);
	        return true;
	    } catch (NumberFormatException ex)
	    {
	        return false;
	    }

	}
	
	// currently both methods below are only used to print seat availability in View
	public static ArrayList<Integer> getSeats() {
		return seats;
	}
	
	// returns index of particular destination chosen by user
	public int getSelectedDestinationIndex() {
		return selectedDestinationIndex;
	}	
	

}
