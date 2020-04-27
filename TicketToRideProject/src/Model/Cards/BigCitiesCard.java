package Model.Cards;

/**
 * 
 * @author Vasileios Geladaris / Gelonit
 * @version something.something
 */
public class BigCitiesCard extends PointsCard{
	private int points;
	private String city;
	private boolean isActive = true;
	
	/**
	 * @param city		City of the card.
	 * @param points	Number of points the card is worth.
	 */
	public BigCitiesCard(String city, int points){
		super(null, points);
		this.points = points;
		this.city = city;
	}
	
	/**
	 * Disables the card so no player can get it.
	 * Postcondition: No player can redeem this card.
	 */
	public void disable(){
		this.isActive = false;
	}
	
	/**
	 * Returns true if the card is active, false if not.
	 * @return isActive boolean variable
	 */
	public boolean isActive(){
		return this.isActive;
	}
	
	/**
	 * Returns the points this card is worth.
	 * @return points int variable.
	 */
	public int getPoints(){
		return this.points;
	}
	
	/**
	 * @return	The city which the card represents.
	 */
	public String getCity(){
		return this.city;
	}
}
