package Model.Gameplay;

import Model.Cards.DestinationCard;
import Model.Cards.TrainCard;
import Model.Cards.Color;
import java.util.ArrayList;

/**
 *
 * @author Vasileios Geladaris / Gelonit
 */
public class Player {
	private int score;
	private int id;
	private int visits[] = new int[6];
	private ArrayList<TrainCard> trainsInHand = new ArrayList<TrainCard>();
	private ArrayList<DestinationCard> cardsInHand = new ArrayList<DestinationCard>();
	private ArrayList<DestinationCard> redeemedCards = new ArrayList<DestinationCard>();
	private RailYard railyard = new RailYard();
	private OnTheTrack onthetrack = new OnTheTrack();
	private int numofbigcities;
	
	/**
	 * Initializes all the player's attributes.
	 * @param id ID of the player.
	 */
	public Player(int id){
		this.score = 0;
		this.id = id;
		this.numofbigcities = 0;
	}
	
	/**
	 * Returns the array of the player's big cities visits.
	 * @return Array of ints.
	 */
	public int[] getVisits(){
		return this.visits;
	}
	
	/**
	 * Increases the player's visits to a big city.
	 * Precondition: index parameter must be in the [0, 5] range.
	 * @param index Which city counter to be increased.
	 */
	public void visitCity(int index){
		this.visits[index]++;
	}
	
	/**
	 * Returns the ID of the player.
	 * @return id int variable.
	 */
	public int getID(){
		return this.id;
	}
	
	/**
	 * Returns the score of the player.
	 * @return score int variable.
	 */
	public int getScore(){
		return this.score;
	}
	
	/**
	 * Returns The player's rail yard.
	 * @return railyard RailYard variable.
	 */
	public RailYard getRailYard(){
		return this.railyard;
	}
	
	/**
	 * Returns the player's on-the-track area
	 * @return onthetrack OnTheTrack variable.
	 */
	public OnTheTrack getTrack(){
		return this.onthetrack;
	}
	
	/**
	 * Returns the player's trains in hand.
	 * @return trainsInHand ArrayList variable.
	 */
	public ArrayList<TrainCard> getTrains(){
		return this.trainsInHand;
	}
	
	/**
	 * Adds a train card to the player's hand.
	 * @param card Train card to be added.
	 */
	public void addTrainCard(TrainCard card){
		this.trainsInHand.add(card);
	}
	
	/**
	 * Removes a train card of the player's hand.
	 * Precondition: Position i is in the [0, arraylist size) range. 
	 * @param i	Position of the card to be removed.
	 */
	public void removeTrain(int i){
		this.trainsInHand.remove(i);
	}
	
	/**
	 * Adds a destination card to the player's hand.
	 * @param card Destination card to be added.
	 */
	public void addDestinationCard(DestinationCard card){
		if(card != null){
			this.score -= card.getPoints();
			this.cardsInHand.add(card);
		}
	}
	
	/**
	 * Redeems a card from the player's hand.
	 * <p>Redeems the card, checks for the big cities involved and removes colors from the player's OOT area.</p>
	 * Precondition: Position index must be in the [0, arraylist size) range.
	 * @param index Position of the card to be redeemed.
	 */
	public void redeemCard(int index){
		DestinationCard c = this.cardsInHand.remove(index);
		this.redeemedCards.add(c);
		this.score += (2 * c.getPoints());
		
		if(c.getDeparture().equals("Chicago") || c.getDestination().equals("Chicago")){
			if(visits[0] < 3)
				visits[0]++;
		}
		if(c.getDeparture().equals("Dallas") || c.getDestination().equals("Dallas")){
			if(visits[1] < 3)
				visits[1]++;
		}
		if(c.getDeparture().equals("Los Angeles") || c.getDestination().equals("Los Angeles")){
			if(visits[2] < 3)
				visits[2]++;
		}
		if(c.getDeparture().equals("Miami") || c.getDestination().equals("Miami")){
			if(visits[3] < 3)
				visits[3]++;
		}
		if(c.getDeparture().equals("New York") || c.getDestination().equals("New York")){
			if(visits[4] < 3)
				visits[4]++;
		}
		if(c.getDeparture().equals("Seattle") || c.getDestination().equals("Seattle")){
			if(visits[5] < 3)
				visits[5]++;
		}
		
		this.onthetrack.removeFromTrack(c.getColors());
	}
	
	/**
	 * Checks if the player can redeem a destination card.
	 * Precondition: Position index must be in the [0, arraylist size) range.
	 * @param index	Position of the card to be checked.
	 * @return 
	 */
	public boolean canRedeem(int index){
		int colorsneeded[] = new int[8];
		DestinationCard c = this.cardsInHand.get(index);
		
		for(Color color : c.getColors()){
			if(color == Color.Red)
				colorsneeded[0]++;
			else if(color == Color.Black)
				colorsneeded[1]++;
			else if(color == Color.Blue)
				colorsneeded[2]++;
			else if(color == Color.Green)
				colorsneeded[3]++;
			else if(color == Color.Purple)
				colorsneeded[4]++;
			else if(color == Color.White)
				colorsneeded[5]++;
			else if(color == Color.Yellow)
				colorsneeded[6]++;
			else if(color == Color.Orange)
				colorsneeded[7]++;
		}
		
		boolean usedloco = false;
		
		for(int i = 0; i < 8; i++){
			if(onthetrack.getCounters()[i] < colorsneeded[i]){
				if(!usedloco){
					if(onthetrack.getCounters()[8] == 0)
						return false;
					else
						usedloco = true;
				}
				else
					return false;
			}
		}
		return true;
	}
	
	/**
	 * Redeems a big city bonus card and adds points.
	 * @param points Points the city is worth.
	 */
	public void redeemBigCity(int points){
		this.score += points;
		this.numofbigcities++;
	}
	
	/**
	 * Returns the number of big city cards the player has redeemed.
	 * @return numofbigcities int variable.
	 */
	public int getNumOfBigCities(){
		return this.numofbigcities;
	}
	
	/**
	 * Returns the redeemed destination cards of the player.
	 * @return redeemedCards ArrayList variable.
	 */
	public ArrayList<DestinationCard> getRedeemedCards(){
		return this.redeemedCards;
	}
	
	/**
	 * Returns the number of destination cards the player has redeemed.
	 * @return size int variable.
	 */
	public int getNumberOfRedeemedCards(){
		return this.redeemedCards.size();
	}
}
