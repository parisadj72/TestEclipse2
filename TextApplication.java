import java.util.Scanner; 

/** 
 * This class utilizes the Party, Poll, PollList,
 * and Factory classes to collect data related to 
 * parties and polls, then displays a visualization 
 * of the information; alternatively, it also 
 * generates a random set of data to visualize.
 * 
 * @author Mackenzie Breithaupt
 * @author Fedor Prokopchuk
 * @author Ray Zegrer
 * 
 */
public class TextApplication {
	
	//INSTANCE VARIABLES
	private PollList polls;
	private String visualizationType = ""; // By seats, or by votes
	private String visualizationOption = ""; // All results, or Aggregate of the results
	private String[] partyList;
	
	//PUBLIC METHODS
	/**
	 * This method takes a poll as a parameter and prints
	 * the visualization by seats method from the Party Class
	 * @param pollToPrint Poll to be visualized
	 */
	public void displayPollDataBySeat(Poll pollToPrint) {
		System.out.println(pollToPrint.textVisualizationBySeats(
				PollList.MAX_STARS_FOR_VISUALIZATION, polls.seatsPerStar()));
	}
	
	/**
	 * This method takes a list of polls and prints the 
	 * visualization by seats using the method from Party and 
	 * the aggregated data 
	 * @param pollInfo List of poll names
	 */
	public void displayPollsBySeat(String[] pollInfo) {
		System.out.println(polls.textVisualizationBySeats());
		System.out.println(displayAggregatePoll(pollInfo));
	}
	
	/**
	 * This method returns the aggregated visualization of
	 * the list of polls provided as the argument
	 * @param pollNames List of poll names
	 * @return Aggregated visualization of the polls provided 
	 * as a string
	 */
	public String displayAggregatePoll(String[] pollNames) {
		Poll aggPoll = polls.getAggregatePoll(pollNames);
		return aggPoll.textVisualizationBySeats(PollList.MAX_STARS_FOR_VISUALIZATION,
				polls.seatsPerStar());
	}
	
	/**
	 * This method takes a poll as a parameter and prints
	 * the visualization by votes using the method from Party
	 * @param pollToPrint Poll that will be visualized
	 */
	public void displayPollDataByVote(Poll pollToPrint) {
		System.out.println(pollToPrint.textVisualizationByVotes(PollList.MAX_STARS_FOR_VISUALIZATION, 
				100 / PollList.MAX_STARS_FOR_VISUALIZATION + 1));
	}
	
	/**
	 * This method takes a list of poll names as the parameter
	 * and visualizes the polls by votes as well as the aggregated
	 * visual
	 * @param pollInfo List of poll names
	 */
	public void displayPollsByVote(String[] pollInfo) {
		System.out.println(polls.textVisualizationByVotes());
		System.out.println(displayAggregatePoll(pollInfo));
	}
	
	/**
	 * This method runs the textApplication program, 
	 * it takes no parameters and doesn't return anything
	 */
	public void run() {
		setUpPollTracker(); // Initial setup for tracking
		while (!visualizationOption.equals("quit")) {
			getVisualizationType(); // set visualization type
			getVisualizationOption(); // set visualization option
			displayResult(); // output visualization
		}
	}
	
	//PRIVATE METHODS
	/**
	 * This method is used to prompt the user for the
	 * data used to set up the poll tracker
	 * Takes no arguments and returns nothing
	 */
	private void setUpPollTracker() {
		//VARIABLES
		Scanner keyInput = new Scanner(System.in); //Closing scanner throws "no line found" exception
		int numSeats, numOfPolls;
		String parties;
		
		//TEXT AND USER INPUT
		System.out.println("Welcome to the poll tracker \n"
				+ "How many seats are available in this election?");
		numSeats = keyInput.nextInt(); // get number of seats
		keyInput.nextLine();
		Factory randomFactory = new Factory(numSeats);
		
		System.out.println("What are the parties participating in this election? "
				+ "(provide names separated by commas)");
		parties = keyInput.nextLine(); // get string list of party names
		partyList = parties.split(",");
		randomFactory.setPartyNames(partyList);
		
		System.out.println("How many polls would you like to track?");
		numOfPolls = keyInput.nextInt(); // get number of polls to track
		keyInput.nextLine();
		
		System.out.println("Would you like me to generate a set of random polls? (yes/no)");
		if (keyInput.nextLine().equals("yes")) {
			polls = randomFactory.createRandomPollList(numOfPolls); // create random polls
		} 
		else {
			polls = promptForPollList(numOfPolls, numSeats, partyList); // get user defined poll information
		}
			 
	}
	
	/**
	 * This method is used to gather data from the user
	 * when they choose to not use random generated data.
	 * 
	 * @param numOfPolls Takes the number of polls to be 
	 * made as an integer
	 * @param numOfSeats Takes the number of seats in the 
	 * election as an integer
	 * @param parties Takes a list of party names as a list 
	 * of Strings
	 * @return a PollList of the polls entered by the user
	 */
	private PollList promptForPollList(int numOfPolls, int numOfSeats, String[] parties) {
		PollList listOfPolls = new PollList(numOfPolls, numOfSeats);
		Scanner keyInput = new Scanner(System.in); //Closing scanner throws "no line found" exception
		for (int i = 0; i < numOfPolls; i++) {
			// prompt user for info for each poll, starting with poll name 
			System.out.println("Name of poll: ");
			String pollName = keyInput.nextLine();
			// initialize poll
			Poll newPoll = new Poll(pollName, parties.length);
			
			// add parties to poll
			for (String party : parties) {
				// prompt for expected number of seats
				System.out.println("Expected number of seats for " + party + ": ");
				float expectedSeats = keyInput.nextFloat();
				keyInput.nextLine();
				// prompt for expected percentage of votes
				System.out.println("Expected percentage of the vote for " + party + ": (enter as a decimal)");
				float expectedVote = keyInput.nextFloat();
				keyInput.nextLine();
				// make new party and add to poll newPoll
				Party newParty = new Party(party, expectedSeats, expectedVote);
				newPoll.addParty(newParty);
			}
			listOfPolls.addPoll(newPoll);
		}
		 
		return listOfPolls;
	}
	
	/**
	 * This method prompts the user for the type of 
	 * visualization, either by seats or votes.
	 * Takes no arguments and doesn't return anything
	 */
	private void getVisualizationType() {
		Scanner keyInput = new Scanner(System.in); //Closing scanner throws "no line found" exception
		System.out.println("Choose visualization type: seats (visualization by seats), "
				+ "votes (visualization by vote percentage)");
		String choice = keyInput.nextLine(); // gets choice of either visualization by seats or votes
		visualizationType = choice;
		 
	}
	
	/**
	 * This method asks the user what they would like 
	 * to do after entering the poll data. Takes no 
	 * arguments and returns nothing
	 */
	private void  getVisualizationOption() {
			Scanner keyInput = new Scanner(System.in);
			System.out.println("What would you like to do next? "
					+ "(please choose one of the three provided options: \n"
					+ "all (shows the results of all polls), "
					+ "aggregate (shows aggregate result), quit (ends the application)");
			visualizationOption = keyInput.nextLine(); // gets display option choice
			 
	}
	
	/**
	 * This method prints the visualization for 
	 * seats/votes in the visualization format 
	 * the user selected. Takes no arguments and
	 * returns nothing.
	 */
	private void displayResult() {
		if (visualizationType.equals("seats")) { // Seat visualization
			if (visualizationOption.equals("all")) {
				System.out.println("\n");
				Poll [] pollForPrinting = polls.toArray();
				for (Poll pollToPrint : pollForPrinting) {
					displayPollDataBySeat(pollToPrint);
				}
			} 
			else if (visualizationOption.equals("aggregate")) {
				System.out.println("\n" + displayAggregatePoll(partyList));
			}
		}
		else if(visualizationType.equals("votes")) { // Vote visualization
			if (visualizationOption.equals("all")) {
				System.out.println("\n");
				Poll [] pollForPrinting = polls.toArray();
				for (Poll pollToPrint : pollForPrinting) {
					displayPollDataByVote(pollToPrint);
				}
			} 
			else if (visualizationOption.equals("aggregate")) {
				System.out.println("\n" + displayAggregatePoll(partyList));
			}
		}
	}
	
	//MAIN METHOD
	/**
	 * Creates an instance of TextApplication and
	 * runs the whole program
	 * @param args Array of all parameters used in 
	 * the program or values passed into the program 
	 */
	public static void main(String[] args) {
		TextApplication application = new TextApplication(); // Create application object
		application.run(); // Run application
	}

}
