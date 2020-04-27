package Control;

import View.MyBigCitiesDialog;
import View.ShowWinnerDialog;
import View.SelectDeckCardsDialog;
import View.UserInterface;
import View.RedeemCardDialog;
import View.MyDestCardsDialog;
import View.SelectCardsDialog;
import Model.Gameplay.Deck;
import Model.Gameplay.Turn;
import Model.Gameplay.Player;
import Model.Cards.DestinationCard;
import Model.Cards.TrainCard;
import Model.Cards.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 * 
 * @author csd3926
 * @version I don't even know anymore
 */
public class Controller {
	private static UserInterface view;
	private static Player player1;
	private static Player player2;
	private static Deck deck;
	private static Turn turn;
	private static ArrayList<DestinationCard> alldestcards;
	private static ArrayList<TrainCard> alltraincards;
	private static ArrayList<DestinationCard> selected1 = new ArrayList<>();
	private static ArrayList<DestinationCard> selected2 = new ArrayList<>();
	private static ArrayList<DestinationCard> notselected1 = new ArrayList<>();
	private static ArrayList<DestinationCard> notselected2 = new ArrayList<>();
	private static ArrayList<DestinationCard> options1 = new ArrayList<>();
	private static ArrayList<DestinationCard> options2 = new ArrayList<>();
	private static boolean pick1 = true;
	private static boolean pick2 = false;
	private static int index;
	private static int pos;
	private static int counter = 0;
	
	private static SelectCardsDialog dialog1;
	private static SelectCardsDialog dialog2;
	private static SelectDeckCardsDialog deckdialog1;
	private static SelectDeckCardsDialog deckdialog2;
	private static RedeemCardDialog redeemdialog;
	private static ShowWinnerDialog winnerdialog;
	private static MyDestCardsDialog mydestdialog1;
	private static MyDestCardsDialog mydestdialog2;
	
	/**
	 * <p>Reads the destination card information from a csv file and adds them to the deck along with all the train cards.</p>
	 * Precondition: The filePath is valid.
	 * Postcondition: Creates all necessary cards for the deck.
	 * @param filePath	Path of the csv file containing the destination card information.
	 * @throws FileNotFoundException
	 * @throws IOException 
	 */
	public void readCards(String filePath) throws FileNotFoundException, IOException{
		/* DESTINATION CARDS */
        BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
        String sCurrentLine = "";
        int i = -1;
        while ((sCurrentLine = br.readLine()) != null) {
            if (i == -1) {
                i = 0;
                continue;
            }
            String[] splitLine = sCurrentLine.split(",");
            String id = splitLine[0];
            String from = splitLine[1];
            String to = splitLine[2];
            int score = Integer.parseInt(splitLine[3]);
            String colorsList = splitLine[4];
            String[] splitColors = colorsList.split("-");
            ArrayList<String> colors = new ArrayList<String>();
            colors.addAll(Arrays.asList(splitColors));
            String imagePath = splitLine[5];
			
			DestinationCard card = new DestinationCard(Integer.parseInt(id), colors, to, from, score, "resources/images/destination_Tickets/" + imagePath);
			alldestcards.add(card);
        }
		/* ----------------- */
		
		/* TRAINCARDS */
		TrainCard red = new TrainCard();
		red.setColor(Color.Red);
		red.setImage("resources/images/trainCards/red.jpg");
			
		TrainCard black = new TrainCard();
		black.setColor(Color.Black);
		black.setImage("resources/images/trainCards/black.jpg");
			
		TrainCard blue = new TrainCard();
		blue.setColor(Color.Blue);
		blue.setImage("resources/images/trainCards/blue.jpg");
			
		TrainCard green = new TrainCard();
		green.setColor(Color.Green);
		green.setImage("resources/images/trainCards/green.jpg");
			
		TrainCard purple = new TrainCard();
		purple.setColor(Color.Purple);
		purple.setImage("resources/images/trainCards/purple.jpg");
			
		TrainCard white = new TrainCard();
		white.setColor(Color.White);
		white.setImage("resources/images/trainCards/white.jpg");
			
		TrainCard yellow = new TrainCard();
		yellow.setColor(Color.Yellow);
		yellow.setImage("resources/images/trainCards/yellow.jpg");
		
		TrainCard orange = new TrainCard();
		orange.setColor(Color.Orange);
		orange.setImage("resources/images/trainCards/orange.jpg");
		
		TrainCard loco = new TrainCard();
		loco.setColor(Color.Locomotive);
		loco.setImage("resources/images/trainCards/locomotive.jpg");
		
		for(i = 0; i < 10; i++){			
			alltraincards.add(red);
			alltraincards.add(black);
			alltraincards.add(blue);
			alltraincards.add(green);
			alltraincards.add(purple);
			alltraincards.add(white);
			alltraincards.add(yellow);
			alltraincards.add(orange);
		}
		
		for(i = 0; i < 16; i++)
			alltraincards.add(loco);
		/* ---------- */
    }
	
	/**
	 * <p>Initializes the game components, gives out the cards and shuffles them.</p>
	 * Postcondition: The game is initialized and the players can start playing.
	 */
	public void initialize(){
		view = new UserInterface();
		player1 = new Player(1);
		player2 = new Player(2);
		deck = new Deck();
		turn = new Turn();
		alldestcards = new ArrayList<>();
		alltraincards = new ArrayList<>();
                view.setVisible(true);
		
		try {
			readCards("resources/files/destinationCards.csv");
		} catch (IOException ex) {
			Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		/* ----------------------------------------------------------------------------------------------- */
		TrainCard c1 = alltraincards.remove(alltraincards.size()-1);
		player1.addTrainCard(c1);
		view.addTrainP1(c1);
		
		TrainCard c2 = alltraincards.remove(alltraincards.size()-1);
		player2.addTrainCard(c2);
		view.addTrainP2(c2);
		
		Collections.shuffle(alltraincards);
		Collections.shuffle(alldestcards);
		
		for(int i = 0; i < 7; i++){
			TrainCard temp = alltraincards.remove(0);
			player1.addTrainCard(temp);
			view.addTrainP1(temp);
			TrainCard temp2 = alltraincards.remove(0);
			player2.addTrainCard(temp2);
			view.addTrainP2(temp2);
		}
		
		ArrayList<TrainCard> opentrains = new ArrayList<>();
		for(int i = 0; i < 5; i++){
			TrainCard c = alltraincards.remove(0);
			opentrains.add(c);
			view.addDeckTrain(c, i);
		}
		
		deck.initializeOpenTrainCards(opentrains);
		deck.initializeHiddenTrainCards(alltraincards);
		deck.initializeDestCards(alldestcards);
		
		turn.setPlayer((Math.random() <= 0.5) ? player1 : player2);
		
		updateInfo(player1);
		updateInfo(player2);
		
		for(int i = 0; i < 6; i++){
			options1.add(alldestcards.remove(0));
			options2.add(alldestcards.remove(0));
		}
		
		dialog1 = new SelectCardsDialog(options1, "Player 1");
		dialog1.setVisible(true);
		dialog2 = new SelectCardsDialog(options2, "Player 2");
		
		view.updateTrainCounter(deck.getHiddenTrainCounter());
		view.updateDestCounter(deck.getDestCounter());
	}
	
	/**
	 * <p>Updates a player's information box.</p>
	 * Postcondition: The player's information box is updated.
	 * @param player Player object.
	 */
	public static void updateInfo(Player player){
		if(player == player1)
			if(turn.getPlayer().getID() == 1)
				view.info1.setText("<html>Player " + player.getID() + "<br>Player Turn: Yes<br>Score: " + player.getScore());
			else
				view.info1.setText("<html>Player " + player.getID() + "<br>Player Turn: No<br>Score: " + player.getScore());
		else if(player == player2)
			if(turn.getPlayer().getID() == 2)
				view.info2.setText("<html>Player " + player.getID() + "<br>Player Turn: Yes<br>Score: " + player.getScore());
			else
				view.info2.setText("<html>Player " + player.getID() + "<br>Player Turn: No<br>Score: " + player.getScore());	
	}
	
	/**
	 * <p>Checks whether or not the game is finished.</p>
	 * @return True if the game is finished, false if not.
	 */
	public static boolean isGameOver(){
		int trainsavailable = deck.getHiddenTrainCounter() + deck.getOpenTrainCounter();
		
		return (player1.getScore() >= 100 || player2.getScore() >= 100 || trainsavailable == 0);
	}
	
	/**
	 * <p>Returns the winner of the two players, or a new Player object with ID = 0 in case of a tie.</p>
	 * @return The winner Player object.
	 */
	public static Player getWinner(){
		
		if(player1.getScore() >= 100)
			return player1;
		else if(player2.getScore() >= 100)
			return player2;
		else if(deck.getHiddenTrainCounter() + deck.getOpenTrainCounter() <= 0){
			if(player1.getScore() > player2.getScore())
				return player1;
			else if(player1.getScore() < player2.getScore())
				return player2;
			else{
				if(player1.getNumberOfRedeemedCards() > player2.getNumberOfRedeemedCards())
					return player1;
				else if(player1.getNumberOfRedeemedCards() < player2.getNumberOfRedeemedCards())
					return player2;
				else{
					if(player1.getNumOfBigCities() > player2.getNumOfBigCities())
						return player1;
					else if(player1.getNumOfBigCities() < player2.getNumOfBigCities())
						return player2;
				}
			}
		}
		return new Player(0);
	}
	
	/**
	 * <p>Checks if a player has redeemed a big city bonus card.</p>
	 */
	public static void checkBigCities(){
		if(deck.getBigCities()[0].isActive() && player1.getVisits()[0] == 3){
			player1.redeemBigCity(deck.getBigCities()[0].getPoints());
			deck.getBigCities()[0].disable();
			view.disableCity(0);
		}
		if(deck.getBigCities()[1].isActive() && player1.getVisits()[1] == 3){
			player1.redeemBigCity(deck.getBigCities()[1].getPoints());
			deck.getBigCities()[1].disable();
			view.disableCity(1);
		}
		if(deck.getBigCities()[2].isActive() && player1.getVisits()[2] == 3){
			player1.redeemBigCity(deck.getBigCities()[2].getPoints());
			deck.getBigCities()[2].disable();
			view.disableCity(2);
		}
		if(deck.getBigCities()[3].isActive() && player1.getVisits()[3] == 3){
			player1.redeemBigCity(deck.getBigCities()[3].getPoints());
			deck.getBigCities()[3].disable();
			view.disableCity(3);
		}
		if(deck.getBigCities()[4].isActive() && player1.getVisits()[4] == 3){
			player1.redeemBigCity(deck.getBigCities()[4].getPoints());
			deck.getBigCities()[4].disable();
			view.disableCity(4);
		}
		if(deck.getBigCities()[5].isActive() && player1.getVisits()[5] == 3){
			player1.redeemBigCity(deck.getBigCities()[5].getPoints());
			deck.getBigCities()[5].disable();
			view.disableCity(5);
		}
		
		if(deck.getBigCities()[0].isActive() && player2.getVisits()[0] == 3){
			player2.redeemBigCity(deck.getBigCities()[0].getPoints());
			deck.getBigCities()[0].disable();
			view.disableCity(0);
		}
		if(deck.getBigCities()[1].isActive() && player2.getVisits()[1] == 3){
			player2.redeemBigCity(deck.getBigCities()[1].getPoints());
			deck.getBigCities()[1].disable();
			view.disableCity(1);
		}
		if(deck.getBigCities()[2].isActive() && player2.getVisits()[2] == 3){
			player2.redeemBigCity(deck.getBigCities()[2].getPoints());
			deck.getBigCities()[2].disable();
			view.disableCity(2);
		}
		if(deck.getBigCities()[3].isActive() && player2.getVisits()[3] == 3){
			player2.redeemBigCity(deck.getBigCities()[3].getPoints());
			deck.getBigCities()[3].disable();
			view.disableCity(3);
		}
		if(deck.getBigCities()[4].isActive() && player2.getVisits()[4] == 3){
			player2.redeemBigCity(deck.getBigCities()[4].getPoints());
			deck.getBigCities()[4].disable();
			view.disableCity(4);
		}
		if(deck.getBigCities()[5].isActive() && player2.getVisits()[5] == 3){
			player2.redeemBigCity(deck.getBigCities()[5].getPoints());
			deck.getBigCities()[5].disable();
			view.disableCity(5);
		}
	}
	
	/**
	 * <p>Main method of the project.</p>
	 * @param args Command line arguments.
	 */
	public static void main(String[] args){
		Controller controller = new Controller();
		controller.initialize();
	}
	
	public static class PickTrainCardListener implements ActionListener{ // Pick from Deck.

		/**
		 * <p>Adds a train card to a player's hand and removes it from the hidden stack on the deck.</p>
		 * Postcondition: Player get a new card and the deck stack gets smaller.
		 * @param e An ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!isGameOver()){
				if(turn.getPlayer() == player1){
					if(turn.hasMovedToOTT() && !turn.hasFinished() && counter <= 2 && (turn.getChoice() == 0 || turn.getChoice() == 1)){
						TrainCard c = deck.getHiddenTrainCard();
						player1.addTrainCard(c);
						view.addTrainP1(c);
						view.updateTrainCounter(deck.getHiddenTrainCounter());
						counter++;
						turn.setChoice(1);
						if(counter == 2){
							counter = 0;
							turn.justFinished();
							turn.setPlayer((turn.getPlayer() == player1) ? player2 : player1);
							updateInfo(player1);
							updateInfo(player2);
						}
					}
				}
				else if(turn.getPlayer() == player2){
					if(turn.hasMovedToOTT() && !turn.hasFinished() && counter <= 2 && (turn.getChoice() == 0 || turn.getChoice() == 1)){
						TrainCard c = deck.getHiddenTrainCard();
						player2.addTrainCard(c);
						view.addTrainP2(c);
						view.updateTrainCounter(deck.getHiddenTrainCounter());
						counter++;
						turn.setChoice(1);
						if(counter == 2){
							counter = 0;
							turn.justFinished();
							turn.setPlayer((turn.getPlayer() == player1) ? player2 : player1);
							updateInfo(player1);
							updateInfo(player2);
						}
					}
				}
				if(isGameOver() && winnerdialog == null){
					winnerdialog = new ShowWinnerDialog(getWinner());
					winnerdialog.setVisible(true);
				}
			}
		}
	}
	
	public static class PickDestinationCardListener implements ActionListener{ // Pick from Deck.

		/**
		 * <p>Adds up to four destination cards to the player's hand and removes them from the hidden stack on the deck.</p>
		 * Postcondition: Player get new destination cards and the deck stack gets smaller.
		 * @param e An ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!isGameOver()){
				if(turn.getPlayer() == player1){
					if(turn.hasMovedToOTT() && !turn.hasFinished() && turn.getChoice() == 0){
						if(options1.isEmpty()){
							options1 = new ArrayList<>();
							if(deck.getDestCounter() > 0){
								if(deck.getDestCounter() >= 4)
									for(int i = 0; i < 4; i++)
										options1.add(deck.getDestinationCard());
								else{
									int size = deck.getDestCounter();
									for(int i = 0; i < size; i++)
										options1.add(deck.getDestinationCard());
								}
								deckdialog1 = new SelectDeckCardsDialog(options1, "Player 1");
								deckdialog1.setVisible(true);
							}
						}
					}
				}
				else if(turn.getPlayer() == player2){
					if(turn.hasMovedToOTT() && !turn.hasFinished() && turn.getChoice() == 0){
						if(options2.isEmpty()){
							options2 = new ArrayList<>();
							if(deck.getDestCounter() > 0){
								if(deck.getDestCounter() >= 4)
									for(int i = 0; i < 4; i++)
										options2.add(deck.getDestinationCard());
								else{
									int size = deck.getDestCounter();
									for(int i = 0; i < size; i++)
										options2.add(deck.getDestinationCard());
								}
								deckdialog2 = new SelectDeckCardsDialog(options2, "Player 2");
								deckdialog2.setVisible(true);
							}
						}
					}
				}
				if(isGameOver() && winnerdialog == null){
					winnerdialog = new ShowWinnerDialog(getWinner());
					winnerdialog.setVisible(true);
				}
			}
		}
	}
	
	public static class PickOpenTrainListener implements ActionListener{ // DONE

		/**
		 * <p>Adds a train card to a player's hand and removes it from the open cards on the deck, then it replaces it with one from the hidden stack.</p>
		 * Postcondition: Player get a new card and the deck stack gets smaller.
		 * @param e An ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!isGameOver()){
				if(turn.getPlayer() == player1){
					if(turn.hasMovedToOTT() && !turn.hasFinished() && counter <= 2 && (turn.getChoice() == 0 || turn.getChoice() == 1)){
						JButton src = (JButton)e.getSource();
						if(src.getX() == 290){
							TrainCard c = deck.getOpenTrainCard(0);
							player1.addTrainCard(c);
							view.addTrainP1(c);
							view.addDeckTrain(deck.visibleTrains[0], 0);
							view.updateTrainCounter(deck.getHiddenTrainCounter());
						}
						else if(src.getX() == 360){
							TrainCard c = deck.getOpenTrainCard(1);
							player1.addTrainCard(c);
							view.addTrainP1(c);
							view.addDeckTrain(deck.visibleTrains[1], 1);
							view.updateTrainCounter(deck.getHiddenTrainCounter());
						}
						else if(src.getX() == 430){
							TrainCard c = deck.getOpenTrainCard(2);
							player1.addTrainCard(c);
							view.addTrainP1(c);
							view.addDeckTrain(deck.visibleTrains[2], 2);
							view.updateTrainCounter(deck.getHiddenTrainCounter());
						}
						else if(src.getX() == 500){
							TrainCard c = deck.getOpenTrainCard(3);
							player1.addTrainCard(c);
							view.addTrainP1(c);
							view.addDeckTrain(deck.visibleTrains[3], 3);
							view.updateTrainCounter(deck.getHiddenTrainCounter());
						}
						else if(src.getX() == 570){
							TrainCard c = deck.getOpenTrainCard(4);
							player1.addTrainCard(c);
							view.addTrainP1(c);
							view.addDeckTrain(deck.visibleTrains[4], 4);
							view.updateTrainCounter(deck.getHiddenTrainCounter());
						}
						counter++;
						turn.setChoice(1);
						if(counter == 2){
							counter = 0;
							turn.justFinished();
							turn.setPlayer((turn.getPlayer() == player1) ? player2 : player1);
							updateInfo(player1);
							updateInfo(player2);
						}
					}
				}
				else if(turn.getPlayer() == player2){
					if(turn.hasMovedToOTT() && !turn.hasFinished() && counter <= 2 && (turn.getChoice() == 0 || turn.getChoice() == 1)){
						JButton src = (JButton)e.getSource();
						if(src.getX() == 290){
							TrainCard c = deck.getOpenTrainCard(0);
							player2.addTrainCard(c);
							view.addTrainP2(c);
							view.addDeckTrain(deck.visibleTrains[0], 0);
							view.updateTrainCounter(deck.getHiddenTrainCounter());
						}
						else if(src.getX() == 360){
							TrainCard c = deck.getOpenTrainCard(1);
							player2.addTrainCard(c);
							view.addTrainP2(c);
								view.addDeckTrain(deck.visibleTrains[1], 1);
							view.updateTrainCounter(deck.getHiddenTrainCounter());
						}
						else if(src.getX() == 430){
							TrainCard c = deck.getOpenTrainCard(2);
							player2.addTrainCard(c);
							view.addTrainP2(c);
							view.addDeckTrain(deck.visibleTrains[2], 2);
							view.updateTrainCounter(deck.getHiddenTrainCounter());
						}
						else if(src.getX() == 500){
							TrainCard c = deck.getOpenTrainCard(3);
							player2.addTrainCard(c);
							view.addTrainP2(c);
							view.addDeckTrain(deck.visibleTrains[3], 3);
							view.updateTrainCounter(deck.getHiddenTrainCounter());
						}
						else if(src.getX() == 570){
							TrainCard c = deck.getOpenTrainCard(4);
							player2.addTrainCard(c);
							view.addTrainP2(c);
							view.addDeckTrain(deck.visibleTrains[4], 4);
							view.updateTrainCounter(deck.getHiddenTrainCounter());
						}
						counter++;
						turn.setChoice(1);
						if(counter == 2){
							counter = 0;
							turn.justFinished();
							turn.setPlayer((turn.getPlayer() == player1) ? player2 : player1);
							updateInfo(player1);
							updateInfo(player2);
						}
					}
				}
				if(isGameOver() && winnerdialog == null){
					winnerdialog = new ShowWinnerDialog(getWinner());
					winnerdialog.setVisible(true);
				}
			}
		}	
	}
	
	public static class PickTrainsListener1 implements ActionListener{ // Pick from Hand.

		/**
		 * <p>Changes the selected card's Y position.</p>
		 * @param e An ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!isGameOver()){
				JButton j = (JButton) e.getSource();
				if(j.getY() > 20)
					j.setLocation(j.getX(), j.getY() - 10);
				else
					j.setLocation(j.getX(), j.getY() + 10);
			}
		}
	}
	
	public static class PickTrainsListener2 implements ActionListener{

		/**
		 * <p>Changes the selected card's Y position.</p>
		 * @param e An ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!isGameOver()){
				JButton j = (JButton) e.getSource();
				if(j.getY() > 10)
					j.setLocation(j.getX(), j.getY() - 10);
				else
					j.setLocation(j.getX(), j.getY() + 10);
			}
		}
	}
	
	public static class PlayCardsListener1 implements ActionListener{ // Move to RailYard

		/**
		 * Moves train cards from the player's hand to his rail yard.
		 * <p>Checks which of the player's train cards are selected, then it checks if those cards can be moved to the player's rail yard while also checking if the enemy player's trains should get robbed.</p>
		 * Postcondition: If the cards can be moved, then it adds them to the player's rail yard.
		 * @param e An ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!isGameOver()){
				if(turn.getPlayer() == player1 && turn.hasMovedToOTT() && !turn.hasFinished() && turn.getChoice() == 0){
					ArrayList<Integer> pos = new ArrayList<>();
					int i = 0;
					for(JButton b : view.trains1){
						if(b.getY() > 20)
							pos.add(i);
						i++;
					}

					ArrayList<TrainCard> cards = new ArrayList<>();
					for(int j : pos)
						cards.add(player1.getTrains().get(j));

					for(i = 0; i < cards.size(); i++){
						if(cards.get(i).getColor() == Color.Locomotive)
							cards.add(0, cards.remove(i));
					}

					for(i = 0; i < cards.size(); i++){
						if(cards.get(i).getColor() != Color.Locomotive)
							break;
					}
					if(i == cards.size())
						return;

					boolean canmove;
					boolean samecolor = true;
					boolean diffcolor = true;
					boolean robtrains = false;

					// ------------------------ SAME COLOR -----------------------------
					Color col = cards.get(cards.size() - 1).getColor();
					for(i = 0; i < cards.size(); i++)
						if(cards.get(i).getColor() != col)
							if(cards.get(i).getColor() != Color.Locomotive){
								samecolor = false;
								break;
							}

					if(samecolor)
						if(cards.size() < 2)
							samecolor = false;
					// -----------------------------------------------------------------

					// --------------------- DIFFERENT COLOR ---------------------------
					if(cards.size() == 3){
						if((cards.get(0).getColor() == cards.get(1).getColor()) || (cards.get(1).getColor() == cards.get(2).getColor()) || cards.get(0).getColor() == cards.get(2).getColor())
							diffcolor = false;
					}
					else
						diffcolor = false;

					for(i = 0; i < cards.size(); i++)
						if(cards.get(i).getColor() == Color.Locomotive){
							diffcolor = false;
							break;
						}
					//------------------------------------------------------------------

					canmove = diffcolor || samecolor;

					if(canmove){
						if(!diffcolor && samecolor){
							if(cards.get(cards.size() - 1).getColor() == Color.Red){
								if(view.stacks2[0].size() < cards.size())
									robtrains = true;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Black){
								if(view.stacks2[1].size() < cards.size())
									robtrains = true;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Blue){
								if(view.stacks2[2].size() < cards.size())
									robtrains = true;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Green){
								if(view.stacks2[3].size() < cards.size())
									robtrains = true;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Purple){
								if(view.stacks2[4].size() < cards.size())
									robtrains = true;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.White){
								if(view.stacks2[5].size() < cards.size())
									robtrains = true;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Yellow){
								if(view.stacks2[6].size() < cards.size())
									robtrains = true;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Orange){
								if(view.stacks2[7].size() < cards.size())
									robtrains = true;
							}
						}

						if(samecolor){
							if(cards.get(cards.size()-1).getColor() == Color.Red){
								if(view.stacks1[0].size() > 0  || view.stacks2[0].size() >= cards.size())
									canmove = false;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Black){
								if(view.stacks1[1].size() > 0  || view.stacks2[1].size() >= cards.size())
									canmove = false;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Blue){
								if(view.stacks1[2].size() > 0  || view.stacks2[2].size() >= cards.size())
									canmove = false;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Green){
								if(view.stacks1[3].size() > 0  || view.stacks2[3].size() >= cards.size())
									canmove = false;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Purple){
								if(view.stacks1[4].size() > 0  || view.stacks2[4].size() >= cards.size())
									canmove = false;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.White){
								if(view.stacks1[5].size() > 0  || view.stacks2[5].size() >= cards.size())
									canmove = false;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Yellow){
								if(view.stacks1[6].size() > 0  || view.stacks2[6].size() >= cards.size())
									canmove = false;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Orange){
								if(view.stacks1[7].size() > 0  || view.stacks2[7].size() >= cards.size())
									canmove = false;
							}
						}
						else if(diffcolor){
							for(i = 0; i < cards.size(); i++){
								if(cards.get(i).getColor() == Color.Red){
									if(view.stacks1[0].size() > 0 || view.stacks2[0].size() > 0){
										canmove = false;
										break;
									}
								}
								else if(cards.get(i).getColor() == Color.Black){
									if(view.stacks1[1].size() > 0 || view.stacks2[1].size() > 0){
										canmove = false;
										break;
									}
								}
								else if(cards.get(i).getColor() == Color.Blue){
									if(view.stacks1[2].size() > 0 || view.stacks2[2].size() > 0){
										canmove = false;
										break;
									}
								}
								else if(cards.get(i).getColor() == Color.Green){
									if(view.stacks1[3].size() > 0 || view.stacks2[3].size() > 0){
										canmove = false;
										break;
									}
								}
								else if(cards.get(i).getColor() == Color.Purple){
									if(view.stacks1[4].size() > 0 || view.stacks2[4].size() > 0){
										canmove = false;
										break;
									}
								}
								else if(cards.get(i).getColor() == Color.White){
									if(view.stacks1[5].size() > 0 || view.stacks2[5].size() > 0){
										canmove = false;
										break;
									}
								}
								else if(cards.get(i).getColor() == Color.Yellow){
									if(view.stacks1[6].size() > 0 || view.stacks2[6].size() > 0){
										canmove = false;
										break;
									}
								}
								else if(cards.get(i).getColor() == Color.Orange){
									if(view.stacks1[7].size() > 0 || view.stacks2[7].size() > 0){
										canmove = false;
										break;
									}
								}
							}
						}
					}

					if(canmove){
						for(int j = pos.size() - 1; j >= 1; j--){
							for(i = 1; i <= j; i++){
								if(player1.getTrains().get(pos.get(i)).getColor() == Color.Locomotive && player1.getTrains().get(pos.get(i - 1)).getColor() != Color.Locomotive){
									Collections.swap(player1.getTrains(), pos.get(i), pos.get(i - 1));
									Collections.swap(view.trains1, pos.get(i), pos.get(i - 1));
									Collections.swap(view.traincards1, pos.get(i), pos.get(i - 1));
								}
							}
						}

						player1.getRailYard().playCards(cards);

						for(i = pos.size() - 1; i >= 0; i--)
							player1.removeTrain(pos.get(i));
						view.moveToRY1(pos);

						if(robtrains){
							player2.getRailYard().removeStack(cards.get(cards.size()-1).getColor());
							view.removeStackP2(cards.get(cards.size()-1).getColor());
						}

						turn.justFinished();
						turn.setPlayer((turn.getPlayer() == player1) ? player2 : player1);
						updateInfo(player1);
						updateInfo(player2);
					}
				}
			}
		}
	}
	
	public static class PlayCardsListener2 implements ActionListener{ // DONE

		/**
		 * Moves train cards from the player's hand to his rail yard.
		 * <p>Checks which of the player's train cards are selected, then it checks if those cards can be moved to the player's rail yard while also checking if the enemy player's trains should get robbed.</p>
		 * Postcondition: If the cards can be moved, then it adds them to the player's rail yard.
		 * @param e An ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!isGameOver()){
				if(turn.getPlayer() == player2 && turn.hasMovedToOTT() && !turn.hasFinished() && turn.getChoice() == 0){
					ArrayList<Integer> pos = new ArrayList<>();
					int i = 0;
					for(JButton b : view.trains2){
						if(b.getY() < 10)
							pos.add(i);
						i++;
					}

					ArrayList<TrainCard> cards = new ArrayList<>();
					for(int j : pos)
						cards.add(player2.getTrains().get(j));

					for(i = 0; i < cards.size(); i++){
						if(cards.get(i).getColor() == Color.Locomotive)
							cards.add(0, cards.remove(i));
					}

					for(i = 0; i < cards.size(); i++){
						if(cards.get(i).getColor() != Color.Locomotive)
							break;
					}
					if(i == cards.size())
						return;

					boolean canmove;
					boolean samecolor = true;
					boolean diffcolor = true;
					boolean robtrains = false;

					// ------------------------ SAME COLOR -----------------------------
					Color col = cards.get(cards.size() - 1).getColor();
					for(i = 0; i < cards.size(); i++)
						if(cards.get(i).getColor() != col)
							if(cards.get(i).getColor() != Color.Locomotive){
								samecolor = false;
								break;
							}

					if(samecolor)
						if(cards.size() < 2)
							samecolor = false;
					// -----------------------------------------------------------------

					// --------------------- DIFFERENT COLOR ---------------------------
					if(cards.size() == 3){
						if((cards.get(0).getColor() == cards.get(1).getColor()) || (cards.get(1).getColor() == cards.get(2).getColor()) || cards.get(0).getColor() == cards.get(2).getColor())
							diffcolor = false;
					}
					else
						diffcolor = false;

					for(i = 0; i < cards.size(); i++)
						if(cards.get(i).getColor() == Color.Locomotive){
							diffcolor = false;
							break;
						}
					//------------------------------------------------------------------

					canmove = diffcolor || samecolor;

					if(canmove){
						if(!diffcolor && samecolor){
							if(cards.get(cards.size() - 1).getColor() == Color.Red){
								if(view.stacks1[0].size() < cards.size())
									robtrains = true;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Black){
								if(view.stacks1[1].size() < cards.size())
									robtrains = true;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Blue){
								if(view.stacks1[2].size() < cards.size())
									robtrains = true;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Green){
								if(view.stacks1[3].size() < cards.size())
									robtrains = true;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Purple){
								if(view.stacks1[4].size() < cards.size())
									robtrains = true;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.White){
								if(view.stacks1[5].size() < cards.size())
									robtrains = true;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Yellow){
								if(view.stacks1[6].size() < cards.size())
									robtrains = true;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Orange){
								if(view.stacks1[7].size() < cards.size())
									robtrains = true;
							}
						}

						if(samecolor){
							if(cards.get(cards.size()-1).getColor() == Color.Red){
								if(view.stacks2[0].size() > 0  || view.stacks1[0].size() >= cards.size())
									canmove = false;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Black){
								if(view.stacks2[1].size() > 0  || view.stacks1[1].size() >= cards.size())
									canmove = false;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Blue){
								if(view.stacks2[2].size() > 0  || view.stacks1[2].size() >= cards.size())
									canmove = false;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Green){
								if(view.stacks2[3].size() > 0  || view.stacks1[3].size() >= cards.size())
									canmove = false;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Purple){
								if(view.stacks2[4].size() > 0  || view.stacks1[4].size() >= cards.size())
									canmove = false;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.White){
								if(view.stacks2[5].size() > 0  || view.stacks1[5].size() >= cards.size())
									canmove = false;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Yellow){
								if(view.stacks2[6].size() > 0  || view.stacks1[6].size() >= cards.size())
									canmove = false;
							}
							else if(cards.get(cards.size()-1).getColor() == Color.Orange){
								if(view.stacks2[7].size() > 0  || view.stacks1[7].size() >= cards.size())
									canmove = false;
							}
						}
						else if(diffcolor){
							for(i = 0; i < cards.size(); i++){
								if(cards.get(i).getColor() == Color.Red){
									if(view.stacks2[0].size() > 0 || view.stacks1[0].size() > 0){
										canmove = false;
										break;
									}
								}
								else if(cards.get(i).getColor() == Color.Black){
									if(view.stacks2[1].size() > 0 || view.stacks1[1].size() > 0){
										canmove = false;
										break;
									}
								}
								else if(cards.get(i).getColor() == Color.Blue){
									if(view.stacks2[2].size() > 0 || view.stacks1[2].size() > 0){
										canmove = false;
										break;
									}
								}
								else if(cards.get(i).getColor() == Color.Green){
									if(view.stacks2[3].size() > 0 || view.stacks1[3].size() > 0){
										canmove = false;
										break;
									}
								}
								else if(cards.get(i).getColor() == Color.Purple){
									if(view.stacks2[4].size() > 0 || view.stacks1[4].size() > 0){
										canmove = false;
										break;
									}
								}
								else if(cards.get(i).getColor() == Color.White){
									if(view.stacks2[5].size() > 0 || view.stacks1[5].size() > 0){
										canmove = false;
										break;
									}
								}
								else if(cards.get(i).getColor() == Color.Yellow){
									if(view.stacks2[6].size() > 0 || view.stacks1[6].size() > 0){
										canmove = false;
										break;
									}
								}
								else if(cards.get(i).getColor() == Color.Orange){
									if(view.stacks2[7].size() > 0 || view.stacks1[7].size() > 0){
										canmove = false;
										break;
									}
								}
							}
						}
					}

					if(canmove){
						for(int j = pos.size() - 1; j >= 1; j--){
							for(i = 1; i <= j; i++){
								if(player2.getTrains().get(pos.get(i)).getColor() == Color.Locomotive && player2.getTrains().get(pos.get(i - 1)).getColor() != Color.Locomotive){
									Collections.swap(player2.getTrains(), pos.get(i), pos.get(i - 1));
									Collections.swap(view.trains2, pos.get(i), pos.get(i - 1));
									Collections.swap(view.traincards2, pos.get(i), pos.get(i - 1));
								}
							}
						}

						player2.getRailYard().playCards(cards);

						for(i = pos.size() - 1; i >= 0; i--)
							player2.removeTrain(pos.get(i));
						view.moveToRY2(pos);

						if(robtrains){
							player1.getRailYard().removeStack(cards.get(cards.size()-1).getColor());
							view.removeStackP1(cards.get(cards.size()-1).getColor());
						}

						turn.justFinished();
						turn.setPlayer((turn.getPlayer() == player1) ? player2 : player1);
						updateInfo(player1);
						updateInfo(player2);
					}
				}
			}
		}
	}
	
	public static class MoveToOTTListener1 implements ActionListener{

		/**
		 * Moves train cards from the player's rail yard to his On-The-Track area.
		 * <p>Gets the first card off every stack of the player's rail yard and moves it to the player's On-The-Track area.</p>
		 * Precondition: The player has not already moved the cards.
		 * Postcondition: All the stacks of the player's rail yard will get shorter and the counters of every card's color in the OOT area will increase.
		 * @param e An ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!isGameOver()){
				if(turn.shouldMove()){
					if(turn.getPlayer() == player1 && !turn.hasMovedToOTT()){
						ArrayList<TrainCard> fromry = player1.getRailYard().removeTrains();

						player1.getTrack().collectFromRailYard(fromry);
						view.moveToOOT1(fromry);
						turn.justMovedToOTT();
					}
				}
			}
		}
	}
	
	public static class MoveToOTTListener2 implements ActionListener{ // DONE

		/**
		 * Moves train cards from the player's rail yard to his On-The-Track area.
		 * <p>Gets the first card off every stack of the player's rail yard and moves it to the player's On-The-Track area.</p>
		 * Precondition: The player has not already moved the cards.
		 * Postcondition: All the stacks of the player's rail yard will get shorter and the counters of every card's color in the OOT area will increase.
		 * @param e An ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!isGameOver()){
				if(turn.shouldMove()){
					if(turn.getPlayer() == player2 && !turn.hasMovedToOTT()){
						ArrayList<TrainCard> fromry = player2.getRailYard().removeTrains();

						player2.getTrack().collectFromRailYard(fromry);
						view.moveToOOT2(fromry);
						turn.justMovedToOTT();
					}
				}
			}
			else{
				if(winnerdialog == null){
					winnerdialog = new ShowWinnerDialog(getWinner());
					winnerdialog.setVisible(true);
				}
			}
		}
	}
	
	public static class RedeemCardListener implements ActionListener{

		/**
		 * Redeems a destination card for the player.
		 * Precondition: The 'Redeem Card' window must be open in order for the button to appear and for the listener to work.
		 * Postcondition: The destination card gets removed from the game, its points are added to the score of the player and its colors needed are removed from the player's OOT area.
		 * @param e An ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(turn.getPlayer() == player1){
				player1.redeemCard(pos);
				checkBigCities();
				updateInfo(player1);
				view.deleteDestP1(pos);
				turn.justFinished();
				turn.setPlayer((turn.getPlayer() == player1) ? player2 : player1);
				updateInfo(player1);
				updateInfo(player2);
			}
			else if(turn.getPlayer() == player2){
				player2.redeemCard(pos);
				checkBigCities();
				updateInfo(player2);
				view.deleteDestP2(pos);
				turn.justFinished();
				turn.setPlayer((turn.getPlayer() == player1) ? player2 : player1);
				updateInfo(player1);
				updateInfo(player2);
			}
			redeemdialog.dispose();
			redeemdialog = null;
			if(isGameOver() && winnerdialog == null){
				winnerdialog = new ShowWinnerDialog(getWinner());
				winnerdialog.setVisible(true);
			}
		}
	}
	
	public static class DontRedeemCardListener implements ActionListener{

		/**
		 * Closes the 'Redeem Card' window.
		 * Precondition: The window must be open in order for the button to appear and for the listener to work.
		 * Postcondition: The window closes without the player redeeming the card.
		 * @param e An ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			redeemdialog.dispose();
			redeemdialog = null;
		}
	}
	
	public static class PlayDestCardListener1 implements ActionListener{

		/**
		 * Opens the 'Redeem Card' window for a player.
		 * <p>Checks if the player can redeem the selected destination card, if so, a 'Redeem Card' window opens which gives the player the choice to redeem it or not.</p>
		 * Postcondition: If the player can redeem a card, a window opens letting the player decide if he wants to do so.
		 * @param e An ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!isGameOver()){
				if(turn.getPlayer() == player1 && turn.hasMovedToOTT() && !turn.hasFinished() && turn.getChoice() == 0){
					if(redeemdialog == null){
						JButton src = (JButton) e.getSource();
						index = (src.getX() - 10) / 20;

						if(player1.canRedeem(index)){
							pos = index;
							redeemdialog = new RedeemCardDialog(view.destcards1.get(pos), "Player 1");
							redeemdialog.setVisible(true);
						}
					}
				}
			}
		}
	}
	
	public static class PlayDestCardListener2 implements ActionListener{

		/**
		 * Opens the 'Redeem Card' window for a player.
		 * <p>Checks if the player can redeem the selected destination card, if so, a 'Redeem Card' window opens which gives the player the choice to redeem it or not.</p>
		 * Postcondition: If the player can redeem a card, a window opens letting the player decide if he wants to do so.
		 * @param e An ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!isGameOver()){
				if(turn.getPlayer() == player2 && turn.hasMovedToOTT() && !turn.hasFinished() && turn.getChoice() == 0){
					if(redeemdialog == null){
						JButton src = (JButton) e.getSource();
						index = (src.getX() - 10) / 20;

						if(player2.canRedeem(index)){
							pos = index;
							redeemdialog = new RedeemCardDialog(view.destcards2.get(pos), "Player 2");
							redeemdialog.setVisible(true);
						}
					}
				}
			}
		}
	}
	
	public static class MyDestCardsListener1 implements ActionListener{

		/**
		 * Opens a window with all the player's redeemed cards.
		 * @param e An ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			mydestdialog1 = new MyDestCardsDialog(player1.getRedeemedCards(), player1);
			mydestdialog1.setVisible(true);
		}
	}
	
	public static class MyDestCardsListener2 implements ActionListener{

		/**
		 * Opens a window with all the player's redeemed cards.
		 * @param e An ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			mydestdialog2 = new MyDestCardsDialog(player2.getRedeemedCards(), player2);
			mydestdialog2.setVisible(true);
		}
	}
	
	public static class MyBigCitiesListener1 implements ActionListener{

		/**
		 * Opens a window with all the player's big city bonus card information.
		 * @param e An ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			MyBigCitiesDialog dialog = new MyBigCitiesDialog("Player 1", player1.getVisits());
			dialog.setSize(800, 300);
			dialog.setVisible(true);
		}
	}
	
	public static class MyBigCitiesListener2 implements ActionListener{

		/**
		 * Opens a window with all the player's big city bonus card information.
		 * @param e An ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			MyBigCitiesDialog dialog = new MyBigCitiesDialog("Player 2", player2.getVisits());
			dialog.setSize(800, 300);
			dialog.setVisible(true);
		}
	}
	
	public static class DeckFinishListen implements ActionListener{ // DONE

		/**
		 * <p>Closes the 'Pick Destination Card' window and adds the selected destination cards to the player's hand while also removing them from the hidden stack on the deck.</p>
		 * Precondition: The window must be open in order for the button and the listener to work.
		 * Postcondition: The player get new destination cards and the deck's hidden stack gets smaller.
		 * @param e An ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e){
			if(turn.getPlayer() == player1){
				for(int i = 0; i < options1.size(); i++){
					if(deckdialog1.boxes.get(i).isSelected()){
						player1.addDestinationCard(options1.get(i));
						view.addDestP1(options1.get(i));
					}
					else
						deck.addDestCard(options1.get(i));
				}
			
				options1.clear();
				updateInfo(player1);
				view.updateDestCounter(deck.getDestCounter());
				deckdialog1.dispose();
				turn.justFinished();
				turn.setPlayer((turn.getPlayer() == player1) ? player2 : player1);
				updateInfo(player1);
				updateInfo(player2);
			}
			else if(turn.getPlayer() == player2){
				for(int i = 0;  i < options2.size(); i++){
					if(deckdialog2.boxes.get(i).isSelected()){
						player2.addDestinationCard(options2.get(i));
						view.addDestP2(options2.get(i));
					}
					else
						deck.addDestCard(options2.get(i));
				}
				
				options2.clear();
				updateInfo(player2);
				view.updateDestCounter(deck.getDestCounter());
				deckdialog2.dispose();
				turn.justFinished();
				turn.setPlayer((turn.getPlayer() == player1) ? player2 : player1);
				updateInfo(player1);
				updateInfo(player2);
			}
		}
	}
	
	public static class FinishListen implements ActionListener{ // DONE

		/**
		 * <p>Closes the 'Pick Destination Card' window and adds the selected destination cards to the player's hand while also removing them from the hidden stack on the deck.</p>
		 * Precondition: The window must be open in order for the button and the listener to work.
		 * Postcondition: The player get new destination cards and the deck's hidden stack gets smaller.
		 * @param e An ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e){
			if(pick1){
				if(dialog1.box1.isSelected()){
					player1.addDestinationCard(options1.get(0));
					view.addDestP1(options1.get(0));
				}
				else
					deck.addDestCard(options1.get(0));
			
				if(dialog1.box2.isSelected()){
					player1.addDestinationCard(options1.get(1));
					view.addDestP1(options1.get(1));
				}
				else
					deck.addDestCard(options1.get(1));
				
				if(dialog1.box3.isSelected()){
					player1.addDestinationCard(options1.get(2));
					view.addDestP1(options1.get(2));
				}
				else
					deck.addDestCard(options1.get(2));
			
				if(dialog1.box4.isSelected()){
					player1.addDestinationCard(options1.get(3));
					view.addDestP1(options1.get(3));
				}
				else
					deck.addDestCard(options1.get(3));
			
				if(dialog1.box5.isSelected()){
					player1.addDestinationCard(options1.get(4));
					view.addDestP1(options1.get(4));
				}
				else
					deck.addDestCard(options1.get(4));
			
				if(dialog1.box6.isSelected()){
					player1.addDestinationCard(options1.get(5));
					view.addDestP1(options1.get(5));
				}
				else
					deck.addDestCard(options1.get(5));
			
				options1.clear();
				updateInfo(player1);
				view.updateDestCounter(deck.getDestCounter());
				dialog1.dispose();
				pick1 = false;
				pick2 = true;
				dialog2.setVisible(true);
			}
			else if(pick2){
				if(dialog2.box1.isSelected()){
					player2.addDestinationCard(options2.get(0));
					view.addDestP2(options2.get(0));
				}
				else
					deck.addDestCard(options2.get(0));
			
				if(dialog2.box2.isSelected()){
					player2.addDestinationCard(options2.get(1));
					view.addDestP2(options2.get(1));
				}
				else
					deck.addDestCard(options2.get(1));
				
				if(dialog2.box3.isSelected()){
					player2.addDestinationCard(options2.get(2));
					view.addDestP2(options2.get(2));
				}
				else
					deck.addDestCard(options2.get(2));
			
				if(dialog2.box4.isSelected()){
					player2.addDestinationCard(options2.get(3));
					view.addDestP2(options2.get(3));
				}
				else
					deck.addDestCard(options2.get(3));
			
				if(dialog2.box5.isSelected()){
					player2.addDestinationCard(options2.get(4));
					view.addDestP2(options2.get(4));
				}
				else
					deck.addDestCard(options2.get(4));
			
				if(dialog2.box6.isSelected()){
					player2.addDestinationCard(options2.get(5));
					view.addDestP2(options2.get(5));
				}
				else
					deck.addDestCard(options2.get(5));
			
				options2.clear();
				updateInfo(player2);
				view.updateDestCounter(deck.getDestCounter());
				dialog2.dispose();
				pick1 = true;
				pick2 = false;
			}
		}
	}
	
	public static class ExitGameListener implements ActionListener{

		/**
		 * Exits the game.
		 * Precondition: The 'Winner' window must be open for the button and the listener to work.
		 * Postcondition: The program gets terminated.
		 * @param e An ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}


