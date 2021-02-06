package View;

import Control.Controller;
import Model.Cards.DestinationCard;
import Model.Cards.TrainCard;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author csd3926
 */
public class UserInterface extends JFrame{
	public JLayeredPane playerarea1 = new JLayeredPane();
	public JLayeredPane playerarea2 = new JLayeredPane();
	public JLayeredPane deckarea = new JLayeredPane();
	public JLayeredPane deck = new JLayeredPane();
	public JLayeredPane railyard1 = new JLayeredPane();
	public JLayeredPane railyard2 = new JLayeredPane();
	public JLayeredPane onthetrack1 = new JLayeredPane();
	public JLayeredPane onthetrack2 = new JLayeredPane();
	public JLayeredPane trainsinhand1 = new JLayeredPane();
	public JLayeredPane trainsinhand2 = new JLayeredPane();
	public JLayeredPane destinhand1 = new JLayeredPane();
	public JLayeredPane destinhand2 = new JLayeredPane();
	public JLayeredPane infobox1 = new JLayeredPane();
	public JLayeredPane infobox2 = new JLayeredPane();
	public JButton playcards1 = new JButton("Play Cards");
	public JButton playcards2 = new JButton("Play Cards");
	public JButton movecards1 = new JButton("Move Cards On-The-Track");
	public JButton movecards2 = new JButton("Move Cards On-The-Track");
	public JButton mydest1 = new JButton("Owned Cards");
	public JButton mydest2 = new JButton("Owned Cards");
	public JButton mybigcities1 = new JButton("My Big Cities");
	public JButton mybigcities2 = new JButton("My Big Cities");
	public JLabel info1 = new JLabel("<html>Player 1<br>Player Turn: <br>Score: 0");
	public JLabel info2 = new JLabel("<html>Player 2<br>Player Turn: <br>Score: 0");
	public JButton hiddentrains = new JButton();
	public JLabel hiddentrainscounter = new JLabel("Train Deck: 0");
	public JButton dest = new JButton();
	public JLabel destcounter = new JLabel("Destination Deck: 0");
	public JButton opentrains[] = new JButton[5];
	public TrainCard opentraincards[] = new TrainCard[5];
	public ArrayList<JButton> trains1 = new ArrayList<>();
	public ArrayList<TrainCard> traincards1 = new ArrayList<>();
	public ArrayList<JButton> trains2 = new ArrayList<>();
	public ArrayList<TrainCard> traincards2 = new ArrayList<>();
	public ArrayList<JButton> dest1 = new ArrayList<>();
	public ArrayList<JButton> dest2 = new ArrayList<>();
	public ArrayList<DestinationCard> destcards1 = new ArrayList<>();
	public ArrayList<DestinationCard> destcards2 = new ArrayList<>();
	public ArrayList<JLabel> stacks1[] = new ArrayList[8];
	public ArrayList<JLabel> stacks2[] = new ArrayList[8];
	public int counters1[] = new int[9];
	public int counters2[] = new int[9];
	public ArrayList<JLabel> labelcounters1 = new ArrayList<>();
	public ArrayList<JLabel> labelcounters2 = new ArrayList<>();
	public JLabel city1 = null;
	public JLabel city2 = null;
	public JLabel city3 = null;
	public JLabel city4 = null;
	public JLabel city5 = null;
	public JLabel city6 = null;
	
	public UserInterface(){
		for(int i = 0; i < 8; i++){
			stacks1[i] = new ArrayList<>();
			stacks2[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < 9; i++){
			labelcounters1.add(new JLabel());
			labelcounters2.add(new JLabel());
			counters1[i] = 0;
			counters2[i] = 0;
		}
		
		this.initComponents();
        this.setResizable(false);
        this.setTitle("Ticket To Ride");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void updateTrainCounter(int count){
		this.hiddentrainscounter.setText("Train Deck: " + count);
	}
	
	public void updateDestCounter(int count){
		this.destcounter.setText("Destination Deck: " + count);
	}
	
	public void addTrainP1(TrainCard card){
		if(card != null){
			//System.out.println("ADDED " + card.getColor().toString());
			ImageIcon trainicon = new ImageIcon(new ImageIcon(card.getImage()).getImage().getScaledInstance(50, 90, Image.SCALE_SMOOTH));
			JButton train = new JButton();
			train.setIcon(trainicon);
			train.setBounds(trains1.size() * 35 + 10, 15, 50, 90);
			train.addActionListener(new Controller.PickTrainsListener1());
			trains1.add(train);
			traincards1.add(card);
			trainsinhand1.add(train);
		}
	}
	
	public void addTrainP2(TrainCard card){
		if(card != null){
			ImageIcon trainicon = new ImageIcon(new ImageIcon(card.getImage()).getImage().getScaledInstance(50, 90, Image.SCALE_SMOOTH));
			JButton train = new JButton();
			train.setIcon(trainicon);
			train.setBounds(trains2.size() * 35 + 10, 15, 50, 90);
			train.addActionListener(new Controller.PickTrainsListener2());
			trains2.add(train);
			traincards2.add(card);
			trainsinhand2.add(train);
		}
	}
	
	public void addDestP1(DestinationCard card){
		if(card != null){
			ImageIcon desticon = new ImageIcon(new ImageIcon(card.getImage()).getImage().getScaledInstance(80, 110, Image.SCALE_SMOOTH));
			JButton dest = new JButton();
			dest.setIcon(desticon);
			dest.setBounds(dest1.size() * 20 + 10, 15, 80, 110);
			dest.addActionListener(new Controller.PlayDestCardListener1());
			dest1.add(dest);
			destcards1.add(card);
			destinhand1.add(dest);
		}
	}
	
	public void addDestP2(DestinationCard card){
		if(card != null){
			ImageIcon desticon = new ImageIcon(new ImageIcon(card.getImage()).getImage().getScaledInstance(80, 110, Image.SCALE_SMOOTH));
			JButton dest = new JButton();
			dest.setIcon(desticon);
			dest.setBounds(dest2.size() * 20 + 10, 15, 80, 110);
			dest.addActionListener(new Controller.PlayDestCardListener2());
			dest2.add(dest);
			destcards2.add(card);
			destinhand2.add(dest);
		}
	}
	
	public void rearrangeRY1(){
		int i;
		for(ArrayList<JLabel> arr : stacks1){
			i = 0;
			for(JLabel l : arr){
				l.setLocation(l.getX(), 25 + (i * 5));
				i++;
			}
		}
	}
	
	public void rearrangeRY2(){
		int i;
		for(ArrayList<JLabel> arr : stacks2){
			i = 0;
			for(JLabel l : arr){
				l.setLocation(l.getX(), 25 + (i * 5));
				i++;
			}
		}
	}
	
	public void rearrangeTrains1(){
		int i = 0;
		for(JButton b : trains1){
			b.setLocation(10 + (i * 35), 15);
			i++;
		}
	}
	
	public void rearrangeTrains2(){
		int i = 0;
		for(JButton b : trains2){
			b.setLocation(10 + (i * 35), 15);
			i++;
		}
	}
	
	public void rearrangeDest1(){
		int i = 0;
		for(JButton b : dest1){
			b.setLocation(10 + (i * 20), 15);
			i++;
		}
	}
	
	public void rearrangeDest2(){
		int i = 0;
		for(JButton b : dest2){
			b.setLocation(10 + (i * 20), 15);
			i++;
		}
	}
	
	public void deleteDestP1(int i){
		DestinationCard c = destcards1.remove(i);
		dest1.get(i).setVisible(false);
		dest1.remove(i);
		
		for(Model.Cards.Color co : c.getColors()){
			if(co == Model.Cards.Color.Red){
				if(counters1[0] > 0)
					labelcounters1.get(0).setText("Red: " + (--counters1[0]));
				else
					labelcounters1.get(8).setText("Locomotives: " + (--counters1[8]));
			}
			else if(co == Model.Cards.Color.Black){
				if(counters1[1] > 0)
					labelcounters1.get(1).setText("Black: " + (--counters1[1]));
				else
					labelcounters1.get(8).setText("Locomotives: " + (--counters1[8]));
			}
			else if(co == Model.Cards.Color.Blue){
				if(counters1[2] > 0)
					labelcounters1.get(2).setText("Blue: " + (--counters1[2]));
				else
					labelcounters1.get(8).setText("Locomotives: " + (--counters1[8]));
			}
			else if(co == Model.Cards.Color.Green){
				if(counters1[3] > 0)
					labelcounters1.get(3).setText("Green: " + (--counters1[3]));
				else
					labelcounters1.get(8).setText("Locomotives: " + (--counters1[8]));
			}
			else if(co == Model.Cards.Color.Purple){
				if(counters1[4] > 0)
					labelcounters1.get(4).setText("Purple: " + (--counters1[4]));
				else
					labelcounters1.get(8).setText("Locomotives: " + (--counters1[8]));
			}
			else if(co == Model.Cards.Color.White){
				if(counters1[5] > 0)
					labelcounters1.get(5).setText("White: " + (--counters1[5]));
				else
					labelcounters1.get(8).setText("Locomotives: " + (--counters1[8]));
			}
			else if(co == Model.Cards.Color.Yellow){
				if(counters1[6] > 0)
					labelcounters1.get(6).setText("Yellow: " + (--counters1[6]));
				else
					labelcounters1.get(8).setText("Locomotives: " + (--counters1[8]));
			}
			else if(co == Model.Cards.Color.Orange){
				if(counters1[7] > 0)
					labelcounters1.get(7).setText("Orange: " + (--counters1[7]));
				else
					labelcounters1.get(8).setText("Locomotives: " + (--counters1[8]));
			}
		}
		rearrangeDest1();
	}
	
	public void deleteDestP2(int i){
		DestinationCard c = destcards2.remove(i);
		dest2.get(i).setVisible(false);
		dest2.remove(i);
		
		for(Model.Cards.Color co : c.getColors()){
			if(co == Model.Cards.Color.Red){
				if(counters2[0] > 0)
					labelcounters2.get(0).setText("Red: " + (--counters2[0]));
				else
					labelcounters2.get(8).setText("Locomotives: " + (--counters2[8]));
			}
			else if(co == Model.Cards.Color.Black){
				if(counters2[1] > 0)
					labelcounters2.get(1).setText("Black: " + (--counters2[1]));
				else
					labelcounters2.get(8).setText("Locomotives: " + (--counters2[8]));
			}
			else if(co == Model.Cards.Color.Blue){
				if(counters2[2] > 0)
					labelcounters2.get(2).setText("Blue: " + (--counters2[2]));
				else
					labelcounters2.get(8).setText("Locomotives: " + (--counters2[8]));
			}
			else if(co == Model.Cards.Color.Green){
				if(counters2[3] > 0)
					labelcounters2.get(3).setText("Green: " + (--counters2[3]));
				else
					labelcounters2.get(8).setText("Locomotives: " + (--counters2[8]));
			}
			else if(co == Model.Cards.Color.Purple){
				if(counters2[4] > 0)
					labelcounters2.get(4).setText("Purple: " + (--counters2[4]));
				else
					labelcounters2.get(8).setText("Locomotives: " + (--counters2[8]));
			}
			else if(co == Model.Cards.Color.White){
				if(counters2[5] > 0)
					labelcounters2.get(5).setText("White: " + (--counters2[5]));
				else
					labelcounters2.get(8).setText("Locomotives: " + (--counters2[8]));
			}
			else if(co == Model.Cards.Color.Yellow){
				if(counters2[6] > 0)
					labelcounters2.get(6).setText("Yellow: " + (--counters2[6]));
				else
					labelcounters2.get(8).setText("Locomotives: " + (--counters2[8]));
			}
			else if(co == Model.Cards.Color.Orange){
				if(counters2[7] > 0)
					labelcounters2.get(7).setText("Orange: " + (--counters2[7]));
				else
					labelcounters2.get(8).setText("Locomotives: " + (--counters2[8]));
			}
		}
		rearrangeDest2();
	}
	
	public void moveToRY1(ArrayList<Integer> pos){		
		int j = traincards1.get(pos.get(pos.size()-1)).getColor().getPos();
		int stack = 0;
		
		if(j != 0){
			if(traincards1.get(pos.get(pos.size()-1)).getColor() == Model.Cards.Color.Red)
				stack = 0;
			else if(traincards1.get(pos.get(pos.size()-1)).getColor() == Model.Cards.Color.Black)
				stack = 1;
			else if(traincards1.get(pos.get(pos.size()-1)).getColor() == Model.Cards.Color.Blue)
				stack = 2;
			else if(traincards1.get(pos.get(pos.size()-1)).getColor() == Model.Cards.Color.Green)
				stack = 3;
			else if(traincards1.get(pos.get(pos.size()-1)).getColor() == Model.Cards.Color.Purple)
				stack = 4;
			else if(traincards1.get(pos.get(pos.size()-1)).getColor() == Model.Cards.Color.White)
				stack = 5;
			else if(traincards1.get(pos.get(pos.size()-1)).getColor() == Model.Cards.Color.Yellow)
				stack = 6;
			else if(traincards1.get(pos.get(pos.size()-1)).getColor() == Model.Cards.Color.Orange)
				stack = 7;
		}
		
		for(int i = 0; i < pos.size(); i++){
			JLabel train = new JLabel(trains1.get(pos.get(i)).getIcon());
			if(traincards1.get(pos.get(i)).getColor().toString().equals("Red")){
			//	System.out.println(traincards1.get(pos.get(i)).getColor().toString());
				train.setBounds(traincards1.get(pos.get(i)).getColor().getPos(), 25 + stacks1[0].size() * 5, train.getIcon().getIconWidth(), train.getIcon().getIconHeight());
				stacks1[0].add(train);
			}
			else if(traincards1.get(pos.get(i)).getColor().toString().equals("Black")){
			//	System.out.println(traincards1.get(pos.get(i)).getColor().toString());
				train.setBounds(traincards1.get(pos.get(i)).getColor().getPos(), 25 + stacks1[1].size() * 5, train.getIcon().getIconWidth(), train.getIcon().getIconHeight());
				stacks1[1].add(train);
			}
			else if(traincards1.get(pos.get(i)).getColor().toString().equals("Blue")){
			//	System.out.println(traincards1.get(pos.get(i)).getColor().toString());
				train.setBounds(traincards1.get(pos.get(i)).getColor().getPos(), 25 + stacks1[2].size() * 5, train.getIcon().getIconWidth(), train.getIcon().getIconHeight());
				stacks1[2].add(train);
			}
			else if(traincards1.get(pos.get(i)).getColor().toString().equals("Green")){
			//	System.out.println(traincards1.get(pos.get(i)).getColor().toString());
				train.setBounds(traincards1.get(pos.get(i)).getColor().getPos(), 25 + stacks1[3].size() * 5, train.getIcon().getIconWidth(), train.getIcon().getIconHeight());
				stacks1[3].add(train);
			}
			else if(traincards1.get(pos.get(i)).getColor().toString().equals("Purple")){
			//	System.out.println(traincards1.get(pos.get(i)).getColor().toString());
				train.setBounds(traincards1.get(pos.get(i)).getColor().getPos(), 25 + stacks1[4].size() * 5, train.getIcon().getIconWidth(), train.getIcon().getIconHeight());
				stacks1[4].add(train);
			}
			else if(traincards1.get(pos.get(i)).getColor().toString().equals("White")){
			//	System.out.println(traincards1.get(pos.get(i)).getColor().toString());
				train.setBounds(traincards1.get(pos.get(i)).getColor().getPos(), 25 + stacks1[5].size() * 5, train.getIcon().getIconWidth(), train.getIcon().getIconHeight());
				stacks1[5].add(train);
			}
			else if(traincards1.get(pos.get(i)).getColor().toString().equals("Yellow")){
			//	System.out.println(traincards1.get(pos.get(i)).getColor().toString());
				train.setBounds(traincards1.get(pos.get(i)).getColor().getPos(), 25 + stacks1[6].size() * 5, train.getIcon().getIconWidth(), train.getIcon().getIconHeight());
				stacks1[6].add(train);
			}
			else if(traincards1.get(pos.get(i)).getColor().toString().equals("Orange")){
			//	System.out.println(traincards1.get(pos.get(i)).getColor().toString());
				train.setBounds(traincards1.get(pos.get(i)).getColor().getPos(), 25 + stacks1[7].size() * 5, train.getIcon().getIconWidth(), train.getIcon().getIconHeight());
				stacks1[7].add(train);
			}
			else{
				train.setBounds(j, 25 + stacks1[stack].size() * 5, train.getIcon().getIconWidth(), train.getIcon().getIconHeight());
				stacks1[stack].add(train);
			}
			railyard1.add(train);
		}
		for(int i = pos.size() - 1; i >= 0; i--){
			trains1.get(pos.get(i)).setVisible(false);
			trainsinhand1.remove(trains1.remove((int)pos.get(i)));
			traincards1.remove((int)pos.get(i));
		}
		
		rearrangeTrains1();
	}
	
	public void moveToRY2(ArrayList<Integer> pos){		
		int j = traincards2.get(pos.get(pos.size()-1)).getColor().getPos();
		int stack = 0;
		
		if(j != 0){
			if(traincards2.get(pos.get(pos.size()-1)).getColor() == Model.Cards.Color.Red)
				stack = 0;
			else if(traincards2.get(pos.get(pos.size()-1)).getColor() == Model.Cards.Color.Black)
				stack = 1;
			else if(traincards2.get(pos.get(pos.size()-1)).getColor() == Model.Cards.Color.Blue)
				stack = 2;
			else if(traincards2.get(pos.get(pos.size()-1)).getColor() == Model.Cards.Color.Green)
				stack = 3;
			else if(traincards2.get(pos.get(pos.size()-1)).getColor() == Model.Cards.Color.Purple)
				stack = 4;
			else if(traincards2.get(pos.get(pos.size()-1)).getColor() == Model.Cards.Color.White)
				stack = 5;
			else if(traincards2.get(pos.get(pos.size()-1)).getColor() == Model.Cards.Color.Yellow)
				stack = 6;
			else if(traincards2.get(pos.get(pos.size()-1)).getColor() == Model.Cards.Color.Orange)
				stack = 7;
		}
		
		for(int i = 0; i < pos.size(); i++){
			JLabel train = new JLabel(trains2.get(pos.get(i)).getIcon());
			if(traincards2.get(pos.get(i)).getColor().toString().equals("Red")){
			//	System.out.println(traincards1.get(pos.get(i)).getColor().toString());
				train.setBounds(traincards2.get(pos.get(i)).getColor().getPos(), 25 + stacks2[0].size() * 5, train.getIcon().getIconWidth(), train.getIcon().getIconHeight());
				stacks2[0].add(train);
			}
			else if(traincards2.get(pos.get(i)).getColor().toString().equals("Black")){
			//	System.out.println(traincards1.get(pos.get(i)).getColor().toString());
				train.setBounds(traincards2.get(pos.get(i)).getColor().getPos(), 25 + stacks2[1].size() * 5, train.getIcon().getIconWidth(), train.getIcon().getIconHeight());
				stacks2[1].add(train);
			}
			else if(traincards2.get(pos.get(i)).getColor().toString().equals("Blue")){
			//	System.out.println(traincards1.get(pos.get(i)).getColor().toString());
				train.setBounds(traincards2.get(pos.get(i)).getColor().getPos(), 25 + stacks2[2].size() * 5, train.getIcon().getIconWidth(), train.getIcon().getIconHeight());
				stacks2[2].add(train);
			}
			else if(traincards2.get(pos.get(i)).getColor().toString().equals("Green")){
			//	System.out.println(traincards1.get(pos.get(i)).getColor().toString());
				train.setBounds(traincards2.get(pos.get(i)).getColor().getPos(), 25 + stacks2[3].size() * 5, train.getIcon().getIconWidth(), train.getIcon().getIconHeight());
				stacks2[3].add(train);
			}
			else if(traincards2.get(pos.get(i)).getColor().toString().equals("Purple")){
			//	System.out.println(traincards1.get(pos.get(i)).getColor().toString());
				train.setBounds(traincards2.get(pos.get(i)).getColor().getPos(), 25 + stacks2[4].size() * 5, train.getIcon().getIconWidth(), train.getIcon().getIconHeight());
				stacks2[4].add(train);
			}
			else if(traincards2.get(pos.get(i)).getColor().toString().equals("White")){
			//	System.out.println(traincards1.get(pos.get(i)).getColor().toString());
				train.setBounds(traincards2.get(pos.get(i)).getColor().getPos(), 25 + stacks2[5].size() * 5, train.getIcon().getIconWidth(), train.getIcon().getIconHeight());
				stacks2[5].add(train);
			}
			else if(traincards2.get(pos.get(i)).getColor().toString().equals("Yellow")){
			//	System.out.println(traincards1.get(pos.get(i)).getColor().toString());
				train.setBounds(traincards2.get(pos.get(i)).getColor().getPos(), 25 + stacks2[6].size() * 5, train.getIcon().getIconWidth(), train.getIcon().getIconHeight());
				stacks2[6].add(train);
			}
			else if(traincards2.get(pos.get(i)).getColor().toString().equals("Orange")){
			//	System.out.println(traincards1.get(pos.get(i)).getColor().toString());
				train.setBounds(traincards2.get(pos.get(i)).getColor().getPos(), 25 + stacks2[7].size() * 5, train.getIcon().getIconWidth(), train.getIcon().getIconHeight());
				stacks2[7].add(train);
			}
			else{
				train.setBounds(j, 25 + stacks2[stack].size() * 5, train.getIcon().getIconWidth(), train.getIcon().getIconHeight());
				stacks2[stack].add(train);
			}
			railyard2.add(train);
		}
		for(int i = pos.size() - 1; i >= 0; i--){
			trains2.get(pos.get(i)).setVisible(false);
			trainsinhand2.remove(trains2.remove((int)pos.get(i)));
			traincards2.remove((int)pos.get(i));
		}
		
		rearrangeTrains2();
	}
	
	public void moveToOOT1(ArrayList<TrainCard> cards){
		for(TrainCard c : cards){
			//System.out.println(c.getColor().toString());
			if(c.getColor().toString().equals("Red")){
				counters1[0]++;
				labelcounters1.get(0).setText("Red: " + counters1[0]);
			}
			else if(c.getColor().toString().equals("Black")){
				counters1[1]++;
				labelcounters1.get(1).setText("Black: " + counters1[1]);
			}
			else if(c.getColor().toString().equals("Blue")){
				counters1[2]++;
				labelcounters1.get(2).setText("Blue: " + counters1[2]);
			}
			else if(c.getColor().toString().equals("Green")){
				counters1[3]++;
				labelcounters1.get(3).setText("Green: " + counters1[3]);
			}
			else if(c.getColor().toString().equals("Purple")){
				counters1[4]++;
				labelcounters1.get(4).setText("Purple: " + counters1[4]);
			}
			else if(c.getColor().toString().equals("White")){
				counters1[5]++;
				labelcounters1.get(5).setText("White: " + counters1[5]);
			}
			else if(c.getColor().toString().equals("Yellow")){
				counters1[6]++;
				labelcounters1.get(6).setText("Yellow: " + counters1[6]);
			}
			else if(c.getColor().toString().equals("Orange")){
				counters1[7]++;
				labelcounters1.get(7).setText("Orange: " + counters1[7]);
			}
			else if(c.getColor().toString().equals("Locomotive")){
				counters1[8]++;
				labelcounters1.get(8).setText("Locomotives: " + counters1[8]);
			}
		}
		
		for(int i = 0; i < 8; i++){
			if(stacks1[i].size() > 0){
				stacks1[i].get(0).setVisible(false);
				stacks1[i].remove(0);
			}
		}
		
		rearrangeRY1();
	}
	
	public void moveToOOT2(ArrayList<TrainCard> cards){
		for(TrainCard c : cards){
			//System.out.println(c.getColor().toString());
			if(c.getColor().toString().equals("Red")){
				counters2[0]++;
				labelcounters2.get(0).setText("Red: " + counters2[0]);
			}
			else if(c.getColor().toString().equals("Black")){
				counters2[1]++;
				labelcounters2.get(1).setText("Black: " + counters2[1]);
			}
			else if(c.getColor().toString().equals("Blue")){
				counters2[2]++;
				labelcounters2.get(2).setText("Blue: " + counters2[2]);
			}
			else if(c.getColor().toString().equals("Green")){
				counters2[3]++;
				labelcounters2.get(3).setText("Green: " + counters2[3]);
			}
			else if(c.getColor().toString().equals("Purple")){
				counters2[4]++;
				labelcounters2.get(4).setText("Purple: " + counters2[4]);
			}
			else if(c.getColor().toString().equals("White")){
				counters2[5]++;
				labelcounters2.get(5).setText("White: " + counters2[5]);
			}
			else if(c.getColor().toString().equals("Yellow")){
				counters2[6]++;
				labelcounters2.get(6).setText("Yellow: " + counters2[6]);
			}
			else if(c.getColor().toString().equals("Orange")){
				counters2[7]++;
				labelcounters2.get(7).setText("Orange: " + counters2[7]);
			}
			else if(c.getColor().toString().equals("Locomotive")){
				counters2[8]++;
				labelcounters2.get(8).setText("Locomotives: " + counters2[8]);
			}
		}
		
		for(int i = 0; i < 8; i++){
			if(stacks2[i].size() > 0){
				stacks2[i].get(0).setVisible(false);
				stacks2[i].remove(0);
			}
		}
		
		rearrangeRY2();
	}
	
	public void removeStackP1(Model.Cards.Color c){
		if(c.toString().equals("Red")){
			for(int i = stacks1[0].size() - 1; i >= 0; i--){
				stacks1[0].get(i).setVisible(false);
				stacks1[0].remove(i);
			}
		}
		else if(c.toString().equals("Black")){
			for(int i = stacks1[1].size() - 1; i >= 0; i--){
				stacks1[1].get(i).setVisible(false);
				stacks1[1].remove(i);
			}
		}
		else if(c.toString().equals("Blue")){
			for(int i = stacks1[2].size() - 1; i >= 0; i--){
				stacks1[2].get(i).setVisible(false);
				stacks1[2].remove(i);
			}
		}
		else if(c.toString().equals("Green")){
			for(int i = stacks1[3].size() - 1; i >= 0; i--){
				stacks1[3].get(i).setVisible(false);
				stacks1[3].remove(i);
			}
		}
		else if(c.toString().equals("Purple")){
			for(int i = stacks1[4].size() - 1; i >= 0; i--){
				stacks1[4].get(i).setVisible(false);
				stacks1[4].remove(i);
			}
		}
		else if(c.toString().equals("White")){
			for(int i = stacks1[5].size() - 1; i >= 0; i--){
				stacks1[5].get(i).setVisible(false);
				stacks1[5].remove(i);
			}
		}
		else if(c.toString().equals("Yellow")){
			for(int i = stacks1[6].size() - 1; i >= 0; i--){
				stacks1[6].get(i).setVisible(false);
				stacks1[6].remove(i);
			}
		}
		else if(c.toString().equals("Orange")){
			for(int i = stacks1[7].size() - 1; i >= 0; i--){
				stacks1[7].get(i).setVisible(false);
				stacks1[7].remove(i);
			}
		}
	}
	
	public void removeStackP2(Model.Cards.Color c){
		if(c.toString().equals("Red")){
			for(int i = stacks2[0].size() - 1; i >= 0; i--){
				stacks2[0].get(i).setVisible(false);
				stacks2[0].remove(i);
			}
		}
		else if(c.toString().equals("Black")){
			for(int i = stacks2[1].size() - 1; i >= 0; i--){
				stacks2[1].get(i).setVisible(false);
				stacks2[1].remove(i);
			}
		}
		else if(c.toString().equals("Blue")){
			for(int i = stacks2[2].size() - 1; i >= 0; i--){
				stacks2[2].get(i).setVisible(false);
				stacks2[2].remove(i);
			}
		}
		else if(c.toString().equals("Green")){
			for(int i = stacks2[3].size() - 1; i >= 0; i--){
				stacks2[3].get(i).setVisible(false);
				stacks2[3].remove(i);
			}
		}
		else if(c.toString().equals("Purple")){
			for(int i = stacks2[4].size() - 1; i >= 0; i--){
				stacks2[4].get(i).setVisible(false);
				stacks2[4].remove(i);
			}
		}
		else if(c.toString().equals("White")){
			for(int i = stacks2[5].size() - 1; i >= 0; i--){
				stacks2[5].get(i).setVisible(false);
				stacks2[5].remove(i);
			}
		}
		else if(c.toString().equals("Yellow")){
			for(int i = stacks2[6].size() - 1; i >= 0; i--){
				stacks2[6].get(i).setVisible(false);
				stacks2[6].remove(i);
			}
		}
		else if(c.toString().equals("Orange")){
			for(int i = stacks2[7].size() - 1; i >= 0; i--){
				stacks2[7].get(i).setVisible(false);
				stacks2[7].remove(i);
			}
		}
	}
	
	public void disableCity(int i){
		if(i == 0)
			city1.setVisible(false);
		else if(i == 1)
			city2.setVisible(false);
		else if(i == 2)
			city3.setVisible(false);
		else if(i == 3)
			city4.setVisible(false);
		else if(i == 4)
			city5.setVisible(false);
		else if(i == 5)
			city6.setVisible(false);
	}
	
	public void addDeckTrain(TrainCard c, int i){
		if(c != null){
			opentraincards[i] = c;
			opentrains[i].setIcon(new ImageIcon(new ImageIcon(c.getImage()).getImage().getScaledInstance(65, 105, Image.SCALE_SMOOTH)));
		}
		else{
			opentraincards[i] = null;
			opentrains[i].setIcon(new ImageIcon(new ImageIcon("resources/images/trainCards/trainBackCard.jpg").getImage().getScaledInstance(65, 105, Image.SCALE_SMOOTH)));
			opentrains[i].setEnabled(false);
		}
	}
	
	/**
	 * Initializes all the other components in the package.
	 */
	private void initComponents(){
		try{
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("resources/images/background.jpg")))));
        }
		catch (IOException e){
            e.printStackTrace();
        }
		
		/* -------------------------------------------------------- RAILYARD -------------------------------------------------------- */
		
		railyard1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Railyard"));
		railyard2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Railyard"));
		railyard1.setBounds(5, 150, 500, 155);
		railyard2.setBounds(5, 5, 500, 155);
		
		movecards1.setBounds(280, 130, 215, 20);
		movecards1.addActionListener(new Controller.MoveToOTTListener1());
		movecards2.setBounds(280, 130, 215, 20);
		movecards2.addActionListener(new Controller.MoveToOTTListener2());
		
		railyard1.add(movecards1, new Integer(150));
		railyard2.add(movecards2, new Integer(150));
		
		JLabel red1 = new JLabel("Red");
		red1.setBounds(10, 12, 61, 15);
		JLabel black1 = new JLabel("Black");
		black1.setBounds(71, 12, 61, 15);
		JLabel blue1 = new JLabel("Blue");
		blue1.setBounds(132, 12, 61, 15);
		JLabel green1 = new JLabel("Green");
		green1.setBounds(193, 12, 61, 15);
		JLabel purple1 = new JLabel("Purple");
		purple1.setBounds(254, 12, 61, 15);
		JLabel white1 = new JLabel("White");
		white1.setBounds(315, 12, 61, 15);
		JLabel yellow1 = new JLabel("Yellow");
		yellow1.setBounds(376, 12, 61, 15);
		JLabel orange1 = new JLabel("Orange");
		orange1.setBounds(437, 12, 61, 15);
		
		JLabel red2 = new JLabel("Red");
		red2.setBounds(10, 12, 61, 15);
		JLabel black2 = new JLabel("Black");
		black2.setBounds(71, 12, 61, 15);
		JLabel blue2 = new JLabel("Blue");
		blue2.setBounds(132, 12, 61, 15);
		JLabel green2 = new JLabel("Green");
		green2.setBounds(193, 12, 61, 15);
		JLabel purple2 = new JLabel("Purple");
		purple2.setBounds(254, 12, 61, 15);
		JLabel white2 = new JLabel("White");
		white2.setBounds(315, 12, 61, 15);
		JLabel yellow2 = new JLabel("Yellow");
		yellow2.setBounds(376, 12, 61, 15);
		JLabel orange2 = new JLabel("Orange");
		orange2.setBounds(437, 12, 61, 15);
		
		railyard1.add(red1, new Integer(150));
		railyard1.add(black1, new Integer(150));
		railyard1.add(blue1, new Integer(150));
		railyard1.add(green1, new Integer(150));
		railyard1.add(purple1, new Integer(150));
		railyard1.add(white1, new Integer(150));
		railyard1.add(yellow1, new Integer(150));
		railyard1.add(orange1, new Integer(150));
		
		railyard2.add(red2, new Integer(150));
		railyard2.add(black2, new Integer(150));
		railyard2.add(blue2, new Integer(150));
		railyard2.add(green2, new Integer(150));
		railyard2.add(purple2, new Integer(150));
		railyard2.add(white2, new Integer(150));
		railyard2.add(yellow2, new Integer(150));
		railyard2.add(orange2, new Integer(150));
		/* -------------------------------------------------------------------------------------------------------------------------- */
		
		/* ------------------------------------------------------ ON THE TRACK ------------------------------------------------------ */
		
		onthetrack1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "On-The-Track"));
		onthetrack2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "On-The-Track"));
		onthetrack1.setBounds(510, 150, 350, 155);
		onthetrack2.setBounds(510, 5, 350, 155);
		
		labelcounters1.get(0).setText("Red: " + counters1[0]);
		labelcounters1.get(1).setText("Black: " + counters1[1]);
		labelcounters1.get(2).setText("Blue: " + counters1[2]);
		labelcounters1.get(3).setText("Green: " + counters1[3]);
		labelcounters1.get(4).setText("Purple: " + counters1[4]);
		labelcounters1.get(5).setText("White: " + counters1[5]);
		labelcounters1.get(6).setText("Yellow: " + counters1[6]);
		labelcounters1.get(7).setText("Orange: " + counters1[7]);
		labelcounters1.get(8).setText("Locomotives: " + counters1[8]);
		
		labelcounters1.get(0).setBounds(10, 10, 83, 15);
		labelcounters1.get(1).setBounds(98, 10, 83, 15);
		labelcounters1.get(2).setBounds(186, 10, 83, 15);
		labelcounters1.get(3).setBounds(274, 10, 83, 15);
		labelcounters1.get(4).setBounds(10, 57, 83, 15);
		labelcounters1.get(5).setBounds(98, 57, 83, 15);
		labelcounters1.get(6).setBounds(186, 57, 83, 15);
		labelcounters1.get(7).setBounds(274, 57, 83, 15);
		labelcounters1.get(8).setBounds(10, 118, 115, 15);		
		
		JLabel redicon1 = new JLabel(new ImageIcon(new ImageIcon("resources/images/trainCards/rotated/red.jpg").getImage().getScaledInstance(70, 35, Image.SCALE_SMOOTH)));
		JLabel blackicon1 = new JLabel(new ImageIcon(new ImageIcon("resources/images/trainCards/rotated/black.jpg").getImage().getScaledInstance(70, 35, Image.SCALE_SMOOTH)));
		JLabel blueicon1 = new JLabel(new ImageIcon(new ImageIcon("resources/images/trainCards/rotated/blue.jpg").getImage().getScaledInstance(70, 35, Image.SCALE_SMOOTH)));
		JLabel greenicon1 = new JLabel(new ImageIcon(new ImageIcon("resources/images/trainCards/rotated/green.jpg").getImage().getScaledInstance(70, 35, Image.SCALE_SMOOTH)));
		JLabel purpleicon1 = new JLabel(new ImageIcon(new ImageIcon("resources/images/trainCards/rotated/purple.jpg").getImage().getScaledInstance(70, 35, Image.SCALE_SMOOTH)));
		JLabel whiteicon1 = new JLabel(new ImageIcon(new ImageIcon("resources/images/trainCards/rotated/white.jpg").getImage().getScaledInstance(70, 35, Image.SCALE_SMOOTH)));
		JLabel yellowicon1 = new JLabel(new ImageIcon(new ImageIcon("resources/images/trainCards/rotated/yellow.jpg").getImage().getScaledInstance(70, 35, Image.SCALE_SMOOTH)));
		JLabel orangeicon1 = new JLabel(new ImageIcon(new ImageIcon("resources/images/trainCards/rotated/orange.jpg").getImage().getScaledInstance(70, 35, Image.SCALE_SMOOTH)));
		JLabel locoicon1 = new JLabel(new ImageIcon(new ImageIcon("resources/images/trainCards/rotated/locomotive.jpg").getImage().getScaledInstance(70, 35, Image.SCALE_SMOOTH)));
		
		redicon1.setBounds(6, 23, redicon1.getIcon().getIconWidth(), redicon1.getIcon().getIconHeight());
		blackicon1.setBounds(94, 23, blackicon1.getIcon().getIconWidth(), blackicon1.getIcon().getIconHeight());
		blueicon1.setBounds(182, 23, blueicon1.getIcon().getIconWidth(), blueicon1.getIcon().getIconHeight());
		greenicon1.setBounds(270, 23, greenicon1.getIcon().getIconWidth(), greenicon1.getIcon().getIconHeight());
		purpleicon1.setBounds(6, 70, purpleicon1.getIcon().getIconWidth(), purpleicon1.getIcon().getIconHeight());
		whiteicon1.setBounds(94, 70, whiteicon1.getIcon().getIconWidth(), whiteicon1.getIcon().getIconHeight());
		yellowicon1.setBounds(182, 70, yellowicon1.getIcon().getIconWidth(), yellowicon1.getIcon().getIconHeight());
		orangeicon1.setBounds(270, 70, orangeicon1.getIcon().getIconWidth(), orangeicon1.getIcon().getIconHeight());
		locoicon1.setBounds(135, 110, locoicon1.getIcon().getIconWidth(), locoicon1.getIcon().getIconHeight());
		
		onthetrack1.add(labelcounters1.get(0), new Integer(150));
		onthetrack1.add(redicon1, new Integer(150));
		onthetrack1.add(labelcounters1.get(1), new Integer(150));
		onthetrack1.add(blackicon1, new Integer(150));
		onthetrack1.add(labelcounters1.get(2), new Integer(150));
		onthetrack1.add(blueicon1, new Integer(150));
		onthetrack1.add(labelcounters1.get(3), new Integer(150));
		onthetrack1.add(greenicon1, new Integer(150));
		onthetrack1.add(labelcounters1.get(4), new Integer(150));
		onthetrack1.add(purpleicon1, new Integer(150));
		onthetrack1.add(labelcounters1.get(5), new Integer(150));
		onthetrack1.add(whiteicon1, new Integer(150));
		onthetrack1.add(labelcounters1.get(6), new Integer(150));
		onthetrack1.add(yellowicon1, new Integer(150));
		onthetrack1.add(labelcounters1.get(7), new Integer(150));
		onthetrack1.add(orangeicon1, new Integer(150));
		onthetrack1.add(labelcounters1.get(8), new Integer(150));
		onthetrack1.add(locoicon1, new Integer(150));
		
		labelcounters2.get(0).setText("Red: " + counters2[0]);
		labelcounters2.get(1).setText("Black: " + counters2[1]);
		labelcounters2.get(2).setText("Blue: " + counters2[2]);
		labelcounters2.get(3).setText("Green: " + counters2[3]);
		labelcounters2.get(4).setText("Purple: " + counters2[4]);
		labelcounters2.get(5).setText("White: " + counters2[5]);
		labelcounters2.get(6).setText("Yellow: " + counters2[6]);
		labelcounters2.get(7).setText("Orange: " + counters2[7]);
		labelcounters2.get(8).setText("Locomotives: " + counters2[8]);
		
		labelcounters2.get(0).setBounds(10, 10, 83, 15);
		labelcounters2.get(1).setBounds(98, 10, 83, 15);
		labelcounters2.get(2).setBounds(186, 10, 83, 15);
		labelcounters2.get(3).setBounds(274, 10, 83, 15);
		labelcounters2.get(4).setBounds(10, 57, 83, 15);
		labelcounters2.get(5).setBounds(98, 57, 83, 15);
		labelcounters2.get(6).setBounds(186, 57, 83, 15);
		labelcounters2.get(7).setBounds(274, 57, 83, 15);
		labelcounters2.get(8).setBounds(10, 118, 105, 15);		
		
		JLabel redicon2 = new JLabel(new ImageIcon(new ImageIcon("resources/images/trainCards/rotated/red.jpg").getImage().getScaledInstance(70, 35, Image.SCALE_SMOOTH)));
		JLabel blackicon2 = new JLabel(new ImageIcon(new ImageIcon("resources/images/trainCards/rotated/black.jpg").getImage().getScaledInstance(70, 35, Image.SCALE_SMOOTH)));
		JLabel blueicon2 = new JLabel(new ImageIcon(new ImageIcon("resources/images/trainCards/rotated/blue.jpg").getImage().getScaledInstance(70, 35, Image.SCALE_SMOOTH)));
		JLabel greenicon2 = new JLabel(new ImageIcon(new ImageIcon("resources/images/trainCards/rotated/green.jpg").getImage().getScaledInstance(70, 35, Image.SCALE_SMOOTH)));
		JLabel purpleicon2 = new JLabel(new ImageIcon(new ImageIcon("resources/images/trainCards/rotated/purple.jpg").getImage().getScaledInstance(70, 35, Image.SCALE_SMOOTH)));
		JLabel whiteicon2 = new JLabel(new ImageIcon(new ImageIcon("resources/images/trainCards/rotated/white.jpg").getImage().getScaledInstance(70, 35, Image.SCALE_SMOOTH)));
		JLabel yellowicon2 = new JLabel(new ImageIcon(new ImageIcon("resources/images/trainCards/rotated/yellow.jpg").getImage().getScaledInstance(70, 35, Image.SCALE_SMOOTH)));
		JLabel orangeicon2 = new JLabel(new ImageIcon(new ImageIcon("resources/images/trainCards/rotated/orange.jpg").getImage().getScaledInstance(70, 35, Image.SCALE_SMOOTH)));
		JLabel locoicon2 = new JLabel(new ImageIcon(new ImageIcon("resources/images/trainCards/rotated/locomotive.jpg").getImage().getScaledInstance(70, 35, Image.SCALE_SMOOTH)));
		
		redicon2.setBounds(6, 23, redicon1.getIcon().getIconWidth(), redicon1.getIcon().getIconHeight());
		blackicon2.setBounds(94, 23, blackicon1.getIcon().getIconWidth(), blackicon1.getIcon().getIconHeight());
		blueicon2.setBounds(182, 23, blueicon1.getIcon().getIconWidth(), blueicon1.getIcon().getIconHeight());
		greenicon2.setBounds(270, 23, greenicon1.getIcon().getIconWidth(), greenicon1.getIcon().getIconHeight());
		purpleicon2.setBounds(6, 70, purpleicon1.getIcon().getIconWidth(), purpleicon1.getIcon().getIconHeight());
		whiteicon2.setBounds(94, 70, whiteicon1.getIcon().getIconWidth(), whiteicon1.getIcon().getIconHeight());
		yellowicon2.setBounds(182, 70, yellowicon1.getIcon().getIconWidth(), yellowicon1.getIcon().getIconHeight());
		orangeicon2.setBounds(270, 70, orangeicon1.getIcon().getIconWidth(), orangeicon1.getIcon().getIconHeight());
		locoicon2.setBounds(135, 110, locoicon1.getIcon().getIconWidth(), locoicon1.getIcon().getIconHeight());
		
		onthetrack2.add(labelcounters2.get(0), new Integer(150));
		onthetrack2.add(redicon2, new Integer(150));
		onthetrack2.add(labelcounters2.get(1), new Integer(150));
		onthetrack2.add(blackicon2, new Integer(150));
		onthetrack2.add(labelcounters2.get(2), new Integer(150));
		onthetrack2.add(blueicon2, new Integer(150));
		onthetrack2.add(labelcounters2.get(3), new Integer(150));
		onthetrack2.add(greenicon2, new Integer(150));
		onthetrack2.add(labelcounters2.get(4), new Integer(150));
		onthetrack2.add(purpleicon2, new Integer(150));
		onthetrack2.add(labelcounters2.get(5), new Integer(150));
		onthetrack2.add(whiteicon2, new Integer(150));
		onthetrack2.add(labelcounters2.get(6), new Integer(150));
		onthetrack2.add(yellowicon2, new Integer(150));
		onthetrack2.add(labelcounters2.get(7), new Integer(150));
		onthetrack2.add(orangeicon2, new Integer(150));
		onthetrack2.add(labelcounters2.get(8), new Integer(150));
		onthetrack2.add(locoicon2, new Integer(150));
		/* -------------------------------------------------------------------------------------------------------------------------- */
		
		/* ----------------------------------------------------- TRAINS IN HAND ----------------------------------------------------- */
		trainsinhand1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Train Cards on Hand"));
		trainsinhand2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Train Cards on Hand"));
		trainsinhand1.setBounds(5, 5, 500, 140);
		trainsinhand2.setBounds(5, 165, 500, 140);
		
		playcards1.setBounds(375, 115, 120, 20);
		playcards1.addActionListener(new Controller.PlayCardsListener1());
		playcards2.setBounds(375, 115, 120, 20);
		playcards2.addActionListener(new Controller.PlayCardsListener2());
		
		trainsinhand1.add(playcards1, new Integer(150));
		trainsinhand2.add(playcards2, new Integer(150));
		/* -------------------------------------------------------------------------------------------------------------------------- */
		
		/* ------------------------------------------------------ DEST IN HAND ------------------------------------------------------ */
		
		destinhand1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Destination Tickets on Hand"));
		destinhand2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Destination Tickets on Hand"));
		destinhand1.setBounds(510, 5, 350, 140);
		destinhand2.setBounds(510, 165, 350, 140);
		/* -------------------------------------------------------------------------------------------------------------------------- */
		
		/* ------------------------------------------------------ INFORMATION ------------------------------------------------------- */
		
		infobox1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Information"));
		infobox2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Information"));
		infobox1.setBounds(865, 5, 150, 300);
		infobox2.setBounds(865, 5, 150, 300);
		
		mydest1.setBounds(10, 120, 130, 50);
		mydest1.addActionListener(new Controller.MyDestCardsListener1());
		mydest2.setBounds(10, 120, 130, 50);
		mydest2.addActionListener(new Controller.MyDestCardsListener2());
		
		mybigcities1.setBounds(10, 180, 130, 50);
		mybigcities1.addActionListener(new Controller.MyBigCitiesListener1());
		mybigcities2.setBounds(10, 180, 130, 50);
		mybigcities2.addActionListener(new Controller.MyBigCitiesListener2());
		
		info1.setBounds(10, 20, 140, 60);
		info2.setBounds(10, 20, 140, 60);
		
		infobox1.add(mydest1, new Integer(150));
		infobox2.add(mydest2, new Integer(150));
		infobox1.add(mybigcities1, new Integer(150));
		infobox2.add(mybigcities2, new Integer(150));
		infobox1.add(info1, new Integer(150));
		infobox2.add(info2, new Integer(150));
		/* -------------------------------------------------------------------------------------------------------------------------- */
		
		/* ---------------------------------------------------- DECK ---------------------------------------------------------------- */
		
		ImageIcon hiddentrainsicon = new ImageIcon(new ImageIcon("resources/images/trainCards/trainBackCard.jpg").getImage().getScaledInstance(70, 110, Image.SCALE_SMOOTH));
		hiddentrains.setIcon(hiddentrainsicon);
		hiddentrains.setBounds(20, 5, 70, 110);
		hiddentrains.addActionListener(new Controller.PickTrainCardListener());
		
		ImageIcon desticon = new ImageIcon(new ImageIcon("resources/images/destination_Tickets/desBackCard.jpg").getImage().getScaledInstance(70, 110, Image.SCALE_SMOOTH));
		dest.setIcon(desticon);
		dest.setBounds(160, 5, 70, 110);
		dest.addActionListener(new Controller.PickDestinationCardListener());
		
		hiddentrainscounter.setBounds(15, 123, 100, 15);
		destcounter.setBounds(130, 123, 150, 15);
		
		//ImageIcon trainicon1 = new ImageIcon(new ImageIcon("resources/images/trainCards/trainBackCard.jpg").getImage().getScaledInstance(65, 105, Image.SCALE_SMOOTH));
		opentrains[0] = new JButton();
		//opentrains[0].setIcon(trainicon1);
		opentrains[0].setBounds(290, 20, 65, 105);
		opentrains[0].addActionListener(new Controller.PickOpenTrainListener());
		
		//ImageIcon trainicon2 = new ImageIcon(new ImageIcon("resources/images/trainCards/trainBackCard.jpg").getImage().getScaledInstance(65, 105, Image.SCALE_SMOOTH));
		opentrains[1] = new JButton();
		//opentrains[1].setIcon(trainicon2);
		opentrains[1].setBounds(360, 20, 65, 105);
		opentrains[1].addActionListener(new Controller.PickOpenTrainListener());
		
		//ImageIcon trainicon3 = new ImageIcon(new ImageIcon("resources/images/trainCards/trainBackCard.jpg").getImage().getScaledInstance(65, 105, Image.SCALE_SMOOTH));
		opentrains[2] = new JButton();
		//opentrains[2].setIcon(trainicon3);
		opentrains[2].setBounds(430, 20, 65, 105);
		opentrains[2].addActionListener(new Controller.PickOpenTrainListener());
		
		//ImageIcon trainicon4 = new ImageIcon(new ImageIcon("resources/images/trainCards/trainBackCard.jpg").getImage().getScaledInstance(65, 105, Image.SCALE_SMOOTH));
		opentrains[3] = new JButton();
		//opentrains[3].setIcon(trainicon4);
		opentrains[3].setBounds(500, 20, 65, 105);
		opentrains[3].addActionListener(new Controller.PickOpenTrainListener());
		
		//ImageIcon trainicon5 = new ImageIcon(new ImageIcon("resources/images/trainCards/trainBackCard.jpg").getImage().getScaledInstance(65, 105, Image.SCALE_SMOOTH));
		opentrains[4] = new JButton();
		//opentrains[4].setIcon(trainicon5);
		opentrains[4].setBounds(570, 20, 65, 105);
		opentrains[4].addActionListener(new Controller.PickOpenTrainListener());
		
		city1 = new JLabel(new ImageIcon(new ImageIcon("resources/images/bigCitiesCards/Chicago.jpg").getImage().getScaledInstance(55, 90, Image.SCALE_SMOOTH)));
		city2 = new JLabel(new ImageIcon(new ImageIcon("resources/images/bigCitiesCards/Dallas.jpg").getImage().getScaledInstance(55, 90, Image.SCALE_SMOOTH)));
		city3 = new JLabel(new ImageIcon(new ImageIcon("resources/images/bigCitiesCards/LosAngeles.jpg").getImage().getScaledInstance(55, 90, Image.SCALE_SMOOTH)));
		city4 = new JLabel(new ImageIcon(new ImageIcon("resources/images/bigCitiesCards/Miami.jpg").getImage().getScaledInstance(55, 90, Image.SCALE_SMOOTH)));
		city5 = new JLabel(new ImageIcon(new ImageIcon("resources/images/bigCitiesCards/NewYork.jpg").getImage().getScaledInstance(55, 90, Image.SCALE_SMOOTH)));
		city6 = new JLabel(new ImageIcon(new ImageIcon("resources/images/bigCitiesCards/Seattle.jpg").getImage().getScaledInstance(55, 90, Image.SCALE_SMOOTH)));
		JLabel bigcities = new JLabel("Available Big Cities Bonus Cards");
		
		city1.setBounds(655, 20, 55, 90);
		city2.setBounds(715, 20, 55, 90);
		city3.setBounds(775, 20, 55, 90);
		city4.setBounds(835, 20, 55, 90);
		city5.setBounds(895, 20, 55, 90);
		city6.setBounds(955, 20, 55, 90);
		bigcities.setBounds(718, 120, 400, 15);
		
		deckarea.add(hiddentrains, new Integer(100));
		deckarea.add(hiddentrainscounter, new Integer(100));
		deckarea.add(dest, new Integer(100));
		deckarea.add(destcounter, new Integer(100));
		deckarea.add(opentrains[0], new Integer(100));
		deckarea.add(opentrains[1], new Integer(100));
		deckarea.add(opentrains[2], new Integer(100));
		deckarea.add(opentrains[3], new Integer(100));
		deckarea.add(opentrains[4], new Integer(100));
		deckarea.add(city1, new Integer(100));
		deckarea.add(city2, new Integer(100));
		deckarea.add(city3, new Integer(100));
		deckarea.add(city4, new Integer(100));
		deckarea.add(city5, new Integer(100));
		deckarea.add(city6, new Integer(100));
		deckarea.add(bigcities, new Integer(100));
		/* -------------------------------------------------------------------------------------------------------------------------- */
		
		playerarea1.setPreferredSize(new Dimension(300, 310));
		playerarea2.setPreferredSize(new Dimension(300, 310));
		playerarea1.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 0), 2));
		playerarea2.setBorder(BorderFactory.createLineBorder(new Color(120, 0, 0), 2));
		playerarea1.setOpaque(true);
		playerarea2.setOpaque(true);
		
		playerarea1.add(railyard1, new Integer(100));
		playerarea2.add(railyard2, new Integer(100));
		playerarea1.add(onthetrack1, new Integer(100));
		playerarea2.add(onthetrack2, new Integer(100));
		playerarea1.add(trainsinhand1, new Integer(100));
		playerarea2.add(trainsinhand2, new Integer(100));
		playerarea1.add(destinhand1, new Integer(100));
		playerarea2.add(destinhand2, new Integer(100));
		playerarea1.add(infobox1, new Integer(100));
		playerarea2.add(infobox2, new Integer(100));
		
		
		Container content = getContentPane();
		content.setLayout(new BorderLayout());
		content.add("South", playerarea1);
		content.add("North", playerarea2);
		content.add(deckarea);
		
		this.pack();
    }
}
