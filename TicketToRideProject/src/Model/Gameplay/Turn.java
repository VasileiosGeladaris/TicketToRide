package Model.Gameplay;

import Model.Cards.TrainCard;
import java.util.ArrayList;

/**
 *
 * @author csd3926
 */
public class Turn {
	private Player currplayer;
	private boolean hasmoved;
	private int choice;
	private boolean isFinished;
	private boolean shouldmove;
	
	/**
	 * Sets the player who is about to play and checks if the player should move trains to his OOT area, depending if he has any trains in his rail yard or not..
	 * @param pl Player object about to play.
	 */
	public void setPlayer(Player pl){
		this.currplayer = pl;
		this.hasmoved = false;
		this.isFinished = false;
		this.shouldmove = false;
		this.choice = 0;
			
		for(ArrayList<TrainCard> arr : pl.getRailYard().getColorStacks())
			if(arr.size() > 0){
				shouldmove = true;
				break;
			}
		
		if(shouldmove)
			hasmoved = false;
		else
			hasmoved = true;
	}
	
	/**
	 * Returns if the player should move to his OOT area.
	 * @return shouldmove boolean variable.
	 */
	public boolean shouldMove(){
		return this.shouldmove;
	}
	
	/**
	 * Sets the choice of the player.
	 * @param i Number of the choice the player selected.
	 */
	public void setChoice(int i){
		this.choice = i;
	}
	
	/**
	 * Returns the choice of the player.
	 * @return choice int variable.
	 */
	public int getChoice(){
		return this.choice;
	}
	
	/**
	 * Returns the player who's turn it is.
	 * @return currplayer Player variable.
	 */
	public Player getPlayer(){
		return this.currplayer;
	}
	
	/**
	 * Indicates that the player just moved his trains to his OOT area.
	 */
	public void justMovedToOTT(){
		this.hasmoved = true;
	}
	
	/**
	 * Returns whether or not the player has moved his trains to his OOT area.
	 * @return hasmoved boolean variable.
	 */
	public boolean hasMovedToOTT(){
		return this.hasmoved;
	}
	
	/**
	 * Indicates that the player just finished his turn.
	 */
	public void justFinished(){
		this.isFinished = true;
	}
	
	/**
	 * Returns whether or not the player has finished his turn.
	 * @return isFinished boolean variable.
	 */
	public boolean hasFinished(){
		return this.isFinished;
	}
}
