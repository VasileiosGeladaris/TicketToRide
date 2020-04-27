package Model.Gameplay;

import Model.Cards.TrainCard;
import Model.Cards.Color;
import java.util.ArrayList;

/**
 * 
 * @author Vasileios Geladaris / Gelonit
 */
public class RailYard {
	private ArrayList<TrainCard> colorStacks[] = new ArrayList[8];
	
	/**
	 * Initializes the rail yard's color stacks.
	 */
	public RailYard(){
		for(int i = 0; i < 8; i++)
			colorStacks[i] = new ArrayList<>();
	}
	
	/**
	 * Checks each card individually and judging by each color, it places it in the right stack with locomotives always on top.
	 * @param cards Cards to be added to the player's rail yard.
	 */
	public void playCards(ArrayList<TrainCard> cards){
		
		if(cards.get(0).getColor() == Color.Locomotive){
			if(cards.get(cards.size() - 1).getColor().toString().equals("Red")){
				for(TrainCard c : cards)
					colorStacks[0].add(c);
			}
			else if(cards.get(cards.size() - 1).getColor().toString().equals("Black")){
				for(TrainCard c : cards)
					colorStacks[1].add(c);
			}
			else if(cards.get(cards.size() - 1).getColor().toString().equals("Blue")){
				for(TrainCard c : cards)
					colorStacks[2].add(c);
			}
			else if(cards.get(cards.size() - 1).getColor().toString().equals("Green")){
				for(TrainCard c : cards)
					colorStacks[3].add(c);
			}
			else if(cards.get(cards.size() - 1).getColor().toString().equals("Purple")){
				for(TrainCard c : cards)
					colorStacks[4].add(c);
			}
			else if(cards.get(cards.size() - 1).getColor().toString().equals("White")){
				for(TrainCard c : cards)
					colorStacks[5].add(c);
			}
			else if(cards.get(cards.size() - 1).getColor().toString().equals("Yellow")){
				for(TrainCard c : cards)
					colorStacks[6].add(c);
			}
			else if(cards.get(cards.size() - 1).getColor().toString().equals("Orange")){
				for(TrainCard c : cards)
					colorStacks[7].add(c);
			}
		}
		else{
			for(TrainCard c : cards){
				if(c.getColor().toString().equals("Red"))
					colorStacks[0].add(c);
				else if(c.getColor().toString().equals("Black"))
					colorStacks[1].add(c);
				else if(c.getColor().toString().equals("Blue"))
					colorStacks[2].add(c);
				else if(c.getColor().toString().equals("Green"))
					colorStacks[3].add(c);
				else if(c.getColor().toString().equals("Purple"))
					colorStacks[4].add(c);
				else if(c.getColor().toString().equals("White"))
					colorStacks[5].add(c);
				else if(c.getColor().toString().equals("Yellow"))
					colorStacks[6].add(c);
				else if(c.getColor().toString().equals("Orange"))
					colorStacks[7].add(c);
			}
		}
	}
	
	public void removeStack(Color c){
		if(c.toString().equals("Red")){
			colorStacks[0].clear();
		}
		else if(c.toString().equals("Black")){
			colorStacks[1].clear();
		}
		else if(c.toString().equals("Blue")){
			colorStacks[2].clear();
		}
		else if(c.toString().equals("Green")){
			colorStacks[3].clear();
		}
		else if(c.toString().equals("Purple")){
			colorStacks[4].clear();
		}
		else if(c.toString().equals("White")){
			colorStacks[5].clear();
		}
		else if(c.toString().equals("Yellow")){
			colorStacks[6].clear();
		}
		else if(c.toString().equals("Orange")){
			colorStacks[7].clear();
		}
	}
	
	/**
	 * Returns the color stacks of the rail yard.
	 * @return colorStacks array of ArrayLists.
	 */
	public ArrayList<TrainCard>[] getColorStacks(){
		return this.colorStacks;
	}
	
	/**
	 * Removes and returns the first train card of each color stack.
	 * @return ArrayList of train cards.
	 */
	public ArrayList<TrainCard> removeTrains(){
		ArrayList<TrainCard> trains = new ArrayList();
		for(int i = 0; i < 8; i++){
			if(colorStacks[i].size() > 0)
				trains.add(colorStacks[i].remove(0));
		}
		return trains;
	}
}
