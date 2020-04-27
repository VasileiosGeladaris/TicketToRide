package Model.Cards;

/**
 *
 * @author Vasileios Geladaris / Gelonit
 */
public class TrainCard implements Card{
	private String image;
	private Color color;
	
	/**
	 * Sets the color of the card.
	 * @param color Color of the card.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Returns the color of the card.
	 * @return color Color variable.
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * Sets the image path for the card.
	 * @param img String containing location of the card's image.
	 */
	public void setImage(String img){
		this.image = img;
	}
	
	/**
	 * Returns the location of the card's image.
	 * @return image String variable.
	 */
	public String getImage(){
		return this.image;
	}
}
