package Model.Cards;

/**
 *
 * @author csd3926
 */
public abstract class PointsCard implements Card{
	String image;
	int points;

	/**
	 * Initializes the card's attributes.
	 * @param img	File path of the card's image.
	 * @param point Points the card is worth.
	 */
	public PointsCard(String img, int point){
		image = img;
		points = point;
	}

	/**
	 * Returns the number of points the card is worth.
	 * @return points int variable.
	 */
	public int getPoints() {
		return this.points;
	}
	
	/**
	 * @return The location of the card's image.
	 */
	public String getImage(){
		return this.image;
	}
}
