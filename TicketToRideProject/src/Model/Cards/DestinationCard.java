package Model.Cards;

import java.util.ArrayList;

/**
 *
 * @author Vasileios Geladaris / Gelonit
 */
public class DestinationCard extends PointsCard{
	private int id;
	private int points;
	private String destination;
	private String departure;
	private ArrayList<Color> colors = new ArrayList<>();
	private String image;
	
	/**
	 * Initializes the card's attributes.
	 * @param id
	 * @param colors	Colors the card requires to be redeemed.
	 * @param dest		Destination city of the card.
	 * @param dep		Departure city of the card.
	 * @param points	Number of points the card is worth.
	 * @param img		path of the card's image.
	 */
	public DestinationCard(int id, ArrayList<String> colors, String dest, String dep, int points, String img){
		super(img, points);
		this.id = id;
		
		for(String s : colors){
			if(Color.Red.toString().equals(s))
				this.colors.add(Color.Red);
			else if(Color.Black.toString().equals(s))
				this.colors.add(Color.Black);
			else if(Color.Blue.toString().equals(s))
				this.colors.add(Color.Blue);
			else if(Color.Green.toString().equals(s))
				this.colors.add(Color.Green);
			else if(Color.Purple.toString().equals(s))
				this.colors.add(Color.Purple);
			else if(Color.White.toString().equals(s))
				this.colors.add(Color.White);
			else if(Color.Yellow.toString().equals(s))
				this.colors.add(Color.Yellow);
			else if(Color.Orange.toString().equals(s))
				this.colors.add(Color.Orange);
		}
		this.destination = dest;
		this.departure = dep;
	}
	
	/**
	 * Returns the destination city of the card.
	 * @return destination String variable.
	 */
	public String getDestination(){
		return this.destination;
	}
	
	/**
	 * Returns the departure city of the card.
	 * @return departure String variable.
	 */
	public String getDeparture(){
		return this.departure;
	}
	
	/**
	 * Returns the colors required to redeem the card.
	 * @return colors ArrayList variable.
	 */
	public ArrayList<Color> getColors(){
		return this.colors;
	}
}
