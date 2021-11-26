/**
 * Class representing a list of polls, 
 * all of which collect data for the same election. 
 * 
 * @author Fedor Prokopchuk. 
 */
public class PollList {
	
	private Poll[] polls;
	private int numOfSeats;
	public static final int MAX_STARS_FOR_VISUALIZATION = 18;

	/**
	 * Constructor for PollList class that sets the instance variables.
	 * @param numOfPolls = number of polls that the list should contain. 
	 * @param seats = number of seats available in election that is 
	 * covered by polls in the list. 
	 */
	public PollList(int numOfPolls, int seats) {
		if (numOfPolls > 0) {
			polls = new Poll[numOfPolls];
		} 
		else {
			polls = new Poll[5];
			System.out.println("Number of polls should be at least 1. "
					+ "Number of polls set to 5.");
		}
		
		if (seats > 0) {
			numOfSeats = seats; 
		} 
		else {
			numOfSeats = 10;
			System.out.println("Number of seats should be at least 1. "
					+ "Number of seats set to 10.");
		}
	}
	
	/**
	 * Getter methods that return a list of polls and the number of seats
	 * available in the election covered by the polls.
	 */
	public Poll[] toArray() {
		return polls;
	}

	public int getNumOfSeats() {
		return numOfSeats;
	}
	
	/**
	 * Method to add a poll to the poll list. Does not returning anything. 
	 * @param pollToAdd = Poll to add to list of polls.
	 */
	public void addPoll(Poll pollToAdd) {
		// check if polls array is full
		boolean full = true;
		int index = 0;
		int i = 0;
		while (i < polls.length && full) {
			if (polls[i] == null) {
				full = false;
				index = i;
			}
			i++;
		}
		
		// add poll to array if there is space
		if (pollToAdd == null) {
			System.out.println("Error: Provided argument is of type 'null'.");
		} 
		else if (full) {
			System.out.println("Error: List is full, no further polls can be added.");
		} 
		else {
			polls[index] = pollToAdd;
		}
		
		return;
	}
	
	/**
	 * Method to calculate expected number of seats and percentage of votes for a party.
	 * @param name = name of party to calculate expected results for.
	 * @return = instance of Party with expected seats and percentage. 
	 */
	public Party getAveragePartyData(String name) {
		// new party object and variables
		Party newParty = new Party(name);
		Party currentParty;
		float totalSeats = 0f;
		float totalPercentages = 0f;
		int numOfPolls = 0;
		
		// getting total seats, percentages, and polls that contain party
		for (Poll currentPoll : polls) {
			currentParty = currentPoll.getParty(name);
			if (currentParty != null) {
				totalSeats += currentParty.getProjectedNumberOfSeats();
				totalPercentages += currentParty.getProjectedPercentageOfVotes();
				numOfPolls++;
			}
		}
		
		// average seats & percentages calculations
		if (numOfPolls != 0) {
			newParty.setProjectedNumberOfSeats(totalSeats / numOfPolls);
			newParty.setProjectedPercentageOfVotes(totalPercentages / numOfPolls);
		} 
		else {
			newParty.setProjectedNumberOfSeats(0f);
			newParty.setProjectedPercentageOfVotes(0f);
		}
		
		return newParty;
	}
	
	/**
	 * Method to obtain a poll that represents the aggregate of all polls in the list.
	 * @param names = names of parties to include in the aggregate poll
	 * @return = aggregate poll with desired parties. 
	 */
	public Poll getAggregatePoll(String[] names) {
		Poll aggregatePoll = new Poll("Aggregate", names.length); 
		for (String party : names) {
			aggregatePoll.addParty(getAveragePartyData(party));
		}
		return aggregatePoll;
	}
	
	/**
	 * Method to obtain a text-based visualization, based on seats, 
	 * for each poll in the list. 
	 * @return = text visualization of polls. 
	 */
	public String textVisualizationBySeats() {
		String stringVisualization = "";
		
		// seats per star rounding 
		int seats = seatsPerStar();
		
		// string concatenation
		for (int index = 0; index < polls.length; index++) {
			if (polls[index] != null) {
				stringVisualization += polls[index].textVisualizationBySeats(
						MAX_STARS_FOR_VISUALIZATION, seats) + "\n";
			} 
			else {
				stringVisualization += "";
			}
		}
		
		return stringVisualization;
	}
	
	/**
	 * Method to obtain a number of seats per star to be used 
	 * for text visualization by seats.  
	 * @return = number of seats per star. 
	 */
	public int seatsPerStar() {
		int seatsPerStar = numOfSeats / MAX_STARS_FOR_VISUALIZATION;
		float fseatsPerStar = (float)numOfSeats / MAX_STARS_FOR_VISUALIZATION;
		// round up if seats don't exactly divide into stars 
		if ((float)seatsPerStar != fseatsPerStar) {
			seatsPerStar += 1;
		}
		
		return seatsPerStar;
	}
	
	/**
	 * Method to obtain a text-based visualization, based on votes,
	 * for each poll in the list.
	 * @return = text visualization of polls. 
	 */
	public String textVisualizationByVotes() {
		String stringVisualization = "";
		
		// string concatenation
		for (int index = 0; index < polls.length; index++) {
			if (polls[index] != null) {
				stringVisualization += polls[index].textVisualizationByVotes(
						MAX_STARS_FOR_VISUALIZATION, 
						100 / MAX_STARS_FOR_VISUALIZATION + 1) + "\n";
			} 
			else {
				stringVisualization += "";
			}
		}
		
		return stringVisualization;
	}
	
	/**
	 * Method to obtain the number of seats and a text-based visualization
	 * for each poll in the list. 
	 * @return = text-based summary of Class instance. 
	 */
	public String toString() {
		String stringVisualization = "Number of seats: " + numOfSeats + "\n" 
				+ textVisualizationBySeats();
		
		return stringVisualization;
	}
	
}
