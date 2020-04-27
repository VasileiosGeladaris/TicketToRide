package Model.Gameplay;

import Model.Cards.DestinationCard;
import Model.Cards.BigCitiesCard;
import Model.Cards.TrainCard;
import java.util.ArrayList;

/**
 *
 * @author Vasileios Geladaris / Gelonit
 */
public class Deck {
	private ArrayList<DestinationCard> hiddenDest = new ArrayList<>();
	private ArrayList<TrainCard> hiddenTrains = new ArrayList<>();
	public TrainCard visibleTrains[] = new TrainCard[5];
	private BigCitiesCard bigcities[] = new BigCitiesCard[6];
	
	/**
	 * Initializes the big cities cards of the deck.
	 */
	public Deck(){
		bigcities[0] = new BigCitiesCard("Chicago", 12);
		bigcities[1] = new BigCitiesCard("Dallas", 10);
		bigcities[2] = new BigCitiesCard("Los Angeles", 12);
		bigcities[3] = new BigCitiesCard("Miami", 8);
		bigcities[4] = new BigCitiesCard("New York", 15);
		bigcities[5] = new BigCitiesCard("Seattle", 8);
	}
	
	/**
	 * Initializes the open train cards on deck.
	 * @param cards	ArrayList containing the cards.
	 */
	public void initializeOpenTrainCards(ArrayList<TrainCard> cards){
		int i = 0;
		for(TrainCard c : cards){
			if(i == 5)
				break;
			visibleTrains[i] = c;
			i++;
		}
	}
	
	/**
	 * Initializes the hidden train cards on deck.
	 * @param cards ArrayList containing the cards.
	 */
	public void initializeHiddenTrainCards(ArrayList<TrainCard> cards){
		this.hiddenTrains = cards;
	}
	
	/**
	 * Initializes the hidden destination cards on deck.
	 * @param cards ArrayList containing the cards.
	 */
	public void initializeDestCards(ArrayList<DestinationCard> cards){
		this.hiddenDest = cards;
	}
	
	/**
	 * Returns the big cities bonus cards.
	 * @return bigcities ArrayList variable.
	 */
	public BigCitiesCard[] getBigCities(){
		return this.bigcities;
	}
	
	/**
	 * Replaces an open train card with another.
	 * @param i		Position of the card to be replaced.
	 * @param card	Replacement card.
	 */
	public void addOpenTrainCard(int i, TrainCard card){
		visibleTrains[i] = card;
	}
	
	/**
	 * Returns and removes the last destination card on the deck.
	 * @return DestinationCard object.
	 */
	public DestinationCard getDestinationCard(){
		if(hiddenDest.size() > 0)
			return hiddenDest.remove(hiddenDest.size()-1);
		else
			return null;
	}
	
	/**
	 * Returns and removes the last hidden train card on the deck.
	 * @return TrainCard object.
	 */
	public TrainCard getHiddenTrainCard(){
		if(hiddenTrains.size() > 0)
			return hiddenTrains.remove(hiddenTrains.size()-1);
		else
			return null;
	}
	
	/**
	 * Returns an open train card.
	 * @param index Position of the train card.
	 * @return TrainCard object.
	 */
	public TrainCard getOpenTrainCard(int index){
		TrainCard card = visibleTrains[index];
		addOpenTrainCard(index, (hiddenTrains.size() > 0) ? hiddenTrains.remove(hiddenTrains.size()-1) : null);
		return card;
	}
	
	/**
	 * Adds a destination card to the deck.
	 * @param card The card to be added.
	 */
	public void addDestCard(DestinationCard card){
		this.hiddenDest.add(card);
	}
	
	/**
	 * Returns the size of the hidden train deck.
	 * @return size int variable.
	 */
	public int getHiddenTrainCounter(){
		return this.hiddenTrains.size();
	}
	
	/**
	 * Returns the size of the open train deck.
	 * @return size int variable.
	 */
	public int getOpenTrainCounter(){
		int counter = 0;
		for(int i = 0; i < 5; i++){
			if(visibleTrains[i] != null)
				counter++;
		}
		return counter;
	}
	
	/**
	 * 
	 * Returns the size of the destination card deck.
	 * @return size int variable.
	 */
	public int getDestCounter(){
		return this.hiddenDest.size();
	}
}
