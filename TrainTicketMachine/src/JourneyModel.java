import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// MODEL
// reads from CSV file and contains methods to return specific columns from file
public class JourneyModel {
	
	// reads from CSV file and creates ArrayList with each row of CSV file as an element
	public ArrayList<String> readCSV() {

		ArrayList<String> linesFromFile = new ArrayList(); // will hold all lines from the file
		String line;

		FileReader fr;
		BufferedReader br;
		String csvFile = "src//20170216 Java Coding test dataset.csv";

		try {
			fr = new FileReader(csvFile);
			br = new BufferedReader(fr);

			try {
				while ((line = br.readLine()) != null) { // reads a single line and inputs into String line, then checks it it's not null
															// 
					linesFromFile.add(line); // adds the line to the ArrayList
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		linesFromFile.remove(0); // removes headers
		
		return linesFromFile;

	}
	// returns ArrayList of all possible destinations 
	public ArrayList<String> setDestinations(ArrayList<String> linesFromFile) {

		ArrayList<String> destinations = new ArrayList<String>();
		
		for (int i = 0; i < linesFromFile.size(); i++) {
			String row[] = linesFromFile.get(i).split(",");
			destinations.add(row[0]);
		}
		
		return destinations;
	}
	// returns ArrayList of all possible prices 
	public ArrayList<Double> setPrices(ArrayList<String> linesFromFile) {

		ArrayList<Double> prices = new ArrayList<Double>();
		
		for (int i = 0; i < linesFromFile.size(); i++) {
			String row[] = linesFromFile.get(i).split(",");

			prices.add(Double.parseDouble(row[1]));
		}
		
		return prices;
	}
	// returns ArrayList of all possible seat availabilities 
	public ArrayList<Integer> setSeats(ArrayList<String> linesFromFile) {

		ArrayList<Integer> seats = new ArrayList<Integer>();
		
		for (int i = 0; i < linesFromFile.size(); i++) {
			String row[] = linesFromFile.get(i).split(",");

			seats.add(Integer.parseInt(row[4]));
		}
		
		return seats;
	}
	// returns ArrayList of all possible journey start times 
	public ArrayList<String> setStartTimes(ArrayList<String> linesFromFile) {

		ArrayList<String> startTimes = new ArrayList<String>();
		
		for (int i = 0; i < linesFromFile.size(); i++) {
			String row[] = linesFromFile.get(i).split(",");

			startTimes.add(row[2]);
		}
		
		return startTimes;
	}
	// returns ArrayList of all possible journey end times 
	public ArrayList<String> setEndTimes(ArrayList<String> linesFromFile) {
		
		ArrayList<String> endTimes = new ArrayList<String>();

		for (int i = 0; i < linesFromFile.size(); i++) {
			String row[] = linesFromFile.get(i).split(",");

			endTimes.add(row[3]);
		}
		
		return endTimes;
	}

}
