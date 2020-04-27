package Model.Gameplay;

import Model.Cards.TrainCard;
import Model.Cards.Color;
import java.util.ArrayList;

/**
 *
 * @author csd3926
 */
public class OnTheTrack {
	private int counters[] = new int[9];
	
	/**
	 * Initializes the counters array.
	 */
	public OnTheTrack(){
		for(int i = 0; i < 9; i++)
			counters[i] = 0;
	}
	
	/**
	 * Returns the counters array.
	 * @return array of ints.
	 */
	public int[] getCounters(){
		return this.counters;
	}
	
	/**
	 * Gets cards from the rail yard and increases the right counters.
	 * @param cards	The cards that came from the railyard.
	 */
	public void collectFromRailYard(ArrayList<TrainCard> cards){
		for(TrainCard c : cards){
			if(c.getColor().toString().equals("Red")){
				counters[0]++;
			}
			else if(c.getColor().toString().equals("Black")){
				counters[1]++;
			}
			else if(c.getColor().toString().equals("Blue")){
				counters[2]++;
			}
			else if(c.getColor().toString().equals("Green")){
				counters[3]++;
			}
			else if(c.getColor().toString().equals("Purple")){
				counters[4]++;
			}
			else if(c.getColor().toString().equals("White")){
				counters[5]++;
			}
			else if(c.getColor().toString().equals("Yellow")){
				counters[6]++;
			}
			else if(c.getColor().toString().equals("Orange")){
				counters[7]++;
			}
			else if(c.getColor().toString().equals("Locomotive")){
				counters[8]++;
			}
		}
	}
	
	/**
	 * Removes trains of certain colors off the tracks.
	 * @param colors Colors of the trains to be removed.
	 */
	public void removeFromTrack(ArrayList<Color> colors){
		//boolean usedloco = false;
		for(Color c : colors){
			if(c.toString().equals("Red")){
				if(counters[0] > 0)
					counters[0]--;
				else
					counters[8]--;
			}
			else if(c.toString().equals("Black")){
				if(counters[1] > 0)
					counters[1]--;
				else
					counters[8]--;
			}
			else if(c.toString().equals("Blue")){
				if(counters[2] > 0)
					counters[2]--;
				else
					counters[8]--;
			}
			else if(c.toString().equals("Green")){
				if(counters[3] > 0)
					counters[3]--;
				else
					counters[8]--;
			}
			else if(c.toString().equals("Purple")){
				if(counters[4] > 0)
					counters[4]--;
				else
					counters[8]--;
			}
			else if(c.toString().equals("White")){
				if(counters[5] > 0)
					counters[5]--;
				else
					counters[8]--;
			}
			else if(c.toString().equals("Yellow")){
				if(counters[6] > 0)
					counters[6]--;
				else
					counters[8]--;
			}
			else if(c.toString().equals("Orange")){
				if(counters[7] > 0)
					counters[7]--;
				else
					counters[8]--;
			}
		}
	}
}
