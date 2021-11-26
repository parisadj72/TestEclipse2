/**
 * Class that represents a poll of an election, consists of a name for the poll
 * an array of the parties in the poll, and the number of parties in the poll.
 * 
 * @author Mackenzie Breithaupt
 */
public class Poll {

	// Variables
	private String name;
	private Party[] parties = new Party[10]; //sets default size of 10
	private int numPartiesInPoll;
	
	// Constructors
	/**
	 * Constructor 
	 * @param name Set the name of the poll.
	 * @param maxParties Set the max number of parties in the poll, with minimum of 1 party.
	 */
	public Poll(String name, int maxParties) {
		// Sets name of the poll
		this.name = name; 
		// Sets default size of the poll
		if (1<=maxParties) { 
			parties = new Party[maxParties];
		}
		else System.out.println("Poll size must be greater than 1.");
	}
	
	// Methods
	/**
	 * This method gets the name of the poll
	 * @return The name of the poll
	 */
	public String getPollName() {
		return this.name;
	}
	
	/**
	 * This method gets the list of parties in the poll
	 * @return list of parties
	 */
	public Party[] getParties() {
		return this.parties;
	}
	
	/**
	 * This method gets the number of parties in the poll
	 * @return Number of parties
	 */
	public int getNumberOfParties() {
		return this.numPartiesInPoll;
	}
	
	/**
	 * This method returns a string representation of the Poll
	 * @return String String representation of the poll
	 */
	public String toString() {
		String partyRepresentation = "\n";
		for (int index = this.parties.length-1; index>=0; index--) {
			if (this.parties[index] != null) partyRepresentation += this.parties[index]+"\n";
		}
		// Name of the poll followed by each of the parties name
		return this.name + partyRepresentation;
	}
	
	/**
	 * This method adds a party to the poll
	 * @param partyToAdd Party to add
	 */
	public void addParty(Party partyToAdd) {
		for (int index = this.parties.length-1; index >= 0 && partyToAdd!=null; index--) {
			// Checks if location in parties list is empty and adds the party
			if (this.parties[index] == null) {
				this.parties[index] = partyToAdd;
				this.numPartiesInPoll++;
				partyToAdd=null;
			}
			// Checks if location in parties list the same as the party to add and replaces it
			else if (this.parties[index].getName().equalsIgnoreCase(partyToAdd.getName().toLowerCase())) {
				this.parties[index] = partyToAdd;
				partyToAdd=null;
			}
			else if (index==0) System.out.println("Poll Is Full, Cannot Add Another Party.");
		}
	  }
	
	/**
	 * This method gets a specific party in the poll
	 * @param nameOfParty Name of party to retrieve
	 * @return Party Party requested, or null if the party isn't in the poll
	 */
	public Party getParty(String nameOfParty) {
		for (int index = this.parties.length-1; index>=0; index--) {
				// Checks if there is a party with the same name in the party list
				if (this.parties[index].getName().equalsIgnoreCase(nameOfParty) && this.parties[index]!=null) {
					return this.parties[index];
				}
			}
		System.out.println("No Party With Name '"+nameOfParty+"' Is In This Poll");
		return null;
	}
	
	/**
	 * This method provides a String representation of the poll by seats,
	 * the first line being the poll name, and each next line a party in the poll
	 * @param maxStars Max stars a party can get
	 * @param numOfSeatsPerStar Number of seats per star
	 * @return String String representation of the poll by seats
	 */
	public String textVisualizationBySeats(int maxStars, double numOfSeatsPerStar) {
		String partyRepresentation = "";
		for (int index = this.parties.length-1; index>=0; index--) {
			if (this.parties[index] != null) {
				// Uses visualization method from Party class to build visual for the whole poll
				partyRepresentation += this.parties[index].textVisualizationBySeats(maxStars, numOfSeatsPerStar) + "\n";
			}
		}
		return this.name +"\n"+ partyRepresentation;
	}
	
	/**
	 * This method provides a String representation of the poll by votes,
	 * the first line being the poll name, and each next line a party in the poll
	 * @param maxStars Max stars a party can get
	 * @param percentOfVotesPerStar Number of seats per star
	 * @return String String representation of the poll by votes
	 */
	public String textVisualizationByVotes(int maxStars, double percentOfVotesPerStar) {
		String partyRepresentation = "";
		for (int index = this.parties.length-1; index>=0; index--) {
			if (this.parties[index] != null) {
				// Uses visualization method from Party class to build visual for the whole poll
				partyRepresentation += this.parties[index].textVisualizationByVotes(maxStars, percentOfVotesPerStar)+"\n";
			}
		}
		return this.name +"\n"+ partyRepresentation;
	}
}
	
