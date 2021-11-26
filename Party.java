import java.awt.Color; 
import java.lang.Math; 

/**
 * The Party class is used to document and visualize 
 * the data pertaining to a political party.
 * 
 * @author Ray Zegrer 
 */
public class Party {
	
	// INSTANCE VARIABLES
	 private String name;
	 private float projectedNumberOfSeats;
	 private float projectedPercentageOfVotes;
	 private Color partyColour;

	 
	 // GETTER METHODS
	 /**
	  * This method gets the party name
	  * @return string party name
	  */
	 public String getName() {
		 return name;
	 }
	 
	 /**
	  * This method gets the projected number of seats
	  * @return projected number of seats as a float
	  */
	 public float getProjectedNumberOfSeats() {
		 return projectedNumberOfSeats;
	 }
	 
	 
	 /**
	  * This method gets the percentage of votes
	  * @return projected percentage of votes as a float
	  */
	 public float getProjectedPercentageOfVotes() {
		 return projectedPercentageOfVotes;
	 }
	 
	 /**
	  * This method gets the party colour
	  * @return party colour
	  */
	 public Color getPartyColour() {
	 	return partyColour;
	 }
	 
	 // SETTER METHODS
	 /**
	  * This method sets the party name
	  * @param n Party name as a string
	  * @return nothing
	  */
	 public void setName(String n) {
	  this.name = n;
	 }
	 
	 /**
	  * This method sets the expected number of seats
	  * @param seat Expected seats as a float
	  * @return nothing
	  */
	 public void setProjectedNumberOfSeats(float seat) {
		 if(seat<0) {
			 System.out.println("Error: cannot enter a negative seat value");
		 }
		 else
			 this.projectedNumberOfSeats = seat;
	 }
	 
	 /**
	  * This method sets the expected percentage of votes
	  * @param percentage Expected percentage as a float
	  * @return nothing
	  */
	 public void setProjectedPercentageOfVotes(float percentage) {
		 if (percentage>1 || percentage<0) {// if the value entered for percentage is over 100% or negative
			 System.out.println("Error: cannot enter a percentage value over 100% (1.0) or under 0% (0.0)");
			 percentage = 0;
		 }
		 else 
			 this.projectedPercentageOfVotes = percentage;
	 }
	 
	 /**
	  * This method sets the party colour
	  * @param colourValue Party colour as a Color value
	  * @return nothing
	  */
	 public void setColour(Color colourValue) {
		 int rgb = colourValue.getRGB();
		 this.partyColour = new Color(rgb);
	 }
	 
	 // CONSTRUCTORS
	 /**
	  * Constructor
	  * takes one string argument and sets the party name
	  * @param partyName 
	  */
	 public Party(String partyName){
		 setName(partyName);
	 }
	 
	 /**
	  * Constructor
	  * takes three arguments and sets all of the party instance variables, excluding colour
	  * @param partyName Name of the party as a string
	  * @param seats Number of projected seats as a float
	  * @param percentage Percentage of projected votes as a float
	  */
	 public Party(String partyName, float seats, float percentage){	 
		 setName(partyName);
		 setProjectedNumberOfSeats(seats);
		 setProjectedPercentageOfVotes(percentage);
	 }
	 
	 // PARTY CLASS METHODS
	 /**
	  * This method is used to produce a string stating the data of the party
	  * @param none
	  * @returns String The name, colour (if available), percentage, and seats
	  */
	 public String toString(){
		 String percent = ((int)(projectedPercentageOfVotes * 100)) + "%";
		 String printedText, colour;
		 if (partyColour != null) {
			 int r = partyColour.getRed();
			 int g = partyColour.getGreen();
			 int b = partyColour.getBlue();
			 colour = r+","+g+","+b;
			 printedText = name+" (["+colour+"], "+percent+" of votes, "+projectedNumberOfSeats+" seats)";
		 }
		 else printedText= name+" ("+percent+" of votes, "+projectedNumberOfSeats+" seats)";
		
		 return printedText;
	 }
	 
	 /**
	  * This method calculates what percentage of seats a party is expected to win
	  * @param val The total number of seats
	  * @returns double The percentage as a decimal between 0 and 1
	  */
	 public double projectedPercentOfSeats(int val) {
		 double percent;
		 if (val>0) {
			percent = projectedNumberOfSeats/val;
		 }
		 else {
			 System.out.println("Error: number of seats must be at least 1");
			 percent = 0.0;
		 }
		 return percent;
	 }
	 
	 /**
	  * This method prints a visualization of the proportion of 
	  * seats a party is expected to win and prints a bar at the
	  * point where a majority has been achieved, the remaining space
	  * is left blank
	  * 
	  * @param maxStars the total number of stars that can be used to 
	  * represent the total number of seats
	  * @param perStar the number of seats that each star is worth
	  * @returns String the visual representation of the expected proportion
	  * of seats to be won by the party
	  */
	 public String textVisualizationBySeats(int maxStars, double perStar) {
		 if(maxStars<0 || perStar<0){
			 System.out.println("Error: cannot enter a negative value");
			 return "";
		 } else
			 return visualization("seats", maxStars, perStar);
	 }
	 
	 /**
	  * This method performs the same work as textVisualizationBySeats but
	  * represents vote percentage
	  * 
	  * @param maxStars the total number of stars that can be used to 
	  * represent the total vote percentage
	  * @param perStar the percentage that each star is worth
	  * @returns String the visual representation of the expected proportion
	  * of the vote to be won by the party
	  */
	 public String textVisualizationByVotes(int maxStars, double perStar) {
		 if(maxStars<0 || perStar<0){
			 System.out.println("Error: cannot enter a negative value");
			 return "";
		 } else
			 return visualization("votes", maxStars, perStar);
	 }
	 
	 //PRIVATE METHODS
	 private String visualization(String type, int maxStars, double numOfItemsPerStar) {
		 //determines which value to use to calculate the number of stars to 
		 //be printed based on if the visualization is for seats or votes
		 int printedStars;
		 if (type == "seats") {
			 printedStars = (int)Math.floor(projectedNumberOfSeats/numOfItemsPerStar);
		 } 
		 else {
			 int percentage = (int)(projectedPercentageOfVotes * 100);
			 printedStars = (int)Math.floor(percentage/numOfItemsPerStar);
		 }
		 
		 /*
		  * determines where the bar that represents the majority needs to 
		  * be printed and how many blank spaces need to be represented
		  */
		 int blankSpace = maxStars - printedStars;
		 int majority = (int) Math.ceil(maxStars/2.0);
		 String visualToPrint;
		 
		 //calls on smaller methods based on whether the number of stars
		 //is equal to, exceeds, or is smaller than the majority
		 if(printedStars>majority) {
			 visualToPrint = visForMajority(majority, printedStars, blankSpace);
		 }
		 else if(printedStars == majority) {
			 visualToPrint = visForEqual(majority, printedStars, blankSpace);
		 }
		 else {
			 visualToPrint = visForMinority(majority, printedStars, blankSpace);
		 }
		 
		 //adds the remaining information to the star visual
		 visualToPrint = visualToPrint + " " + toString(); 
		 return visualToPrint;
	 }
	 
	 private String visForMajority(int majority, int printedStars, int blankSpace){
		 int starCounter = 0; int blankCounter = 0;
		 String visual = "";
		 while(starCounter<majority) { //prints the stars before the bar
			 visual = visual  +"*"; 
			 starCounter++; 
		 }
		 visual = visual + "|";
		 starCounter = 0;
		 while(starCounter<(printedStars-majority)) { //prints the stars after the bar
			 visual = visual +"*";
			 starCounter++;
		 }
		 while(blankCounter<blankSpace) { //prints the remaining blank spaces
			 visual = visual + " ";
			 blankCounter++;
		 }
		 return visual;
	 }
	 
	 private String visForEqual(int majority, int printedStars, int blankSpace){
		 int starCounter = 0; int blankCounter = 0;
		 String visual = "";
		 while(starCounter<majority) { //prints the stars
			 visual = visual  +"*"; 
			 starCounter++; 
		 }
		 visual = visual + "|";
		 while(blankCounter<blankSpace) { //prints the blank spaces
			 visual = visual + " ";
			 blankCounter++;
		 }
		 return visual;
	 }
	 
	 private String visForMinority(int majority, int printedStars, int blankSpace){
		 int starCounter = 0; int blankCounter = 0;
		 String visual = "";
		 while(starCounter<printedStars) { //prints the stars
			 visual = visual  +"*"; 
			 starCounter++;
		 }
		 while(blankCounter<(blankSpace-majority)) { //prints the blank spaces before the bar
			 visual = visual + " ";
			 blankCounter++;
		 }
		 visual = visual + "|";
		 blankCounter = 0;
		 while(blankCounter<majority) { //prints the blank spaces after the bar
			 visual = visual + " "; 
			 blankCounter++;
		 }
		 return visual;
	 }
}