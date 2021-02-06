/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Cards.DestinationCard;
import Control.Controller;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 *
 * @author billy
 */
public class SelectDeckCardsDialog extends JFrame{
	public ArrayList<JCheckBox> boxes = new ArrayList<>();
	
	public SelectDeckCardsDialog(ArrayList<DestinationCard> cards, String player){
		for(int i = 0; i < cards.size(); i++)
			boxes.add(new JCheckBox("Keep"));
		this.setResizable(false);
        this.setTitle("Select Cards " + player);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setSize(435, 250);
		this.initComponents(cards);		
	}
	
	private void initComponents(ArrayList<DestinationCard> cards){
		JLayeredPane pane = new JLayeredPane();
		pane.setBounds(0, 0, 635, 250);
		
		ArrayList<JLabel> labels = new ArrayList<>();
		for(int i = 0; i < cards.size(); i++){
			JLabel temp = new JLabel(new ImageIcon(new ImageIcon(cards.get(i).getImage()).getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH)));
			temp.setBounds(5 + (i * 107), 5, 100, 140);
			boxes.get(i).setBounds(5 + (i * 107), 150, 80, 15);
			labels.add(temp);
		}
		
		JButton finish = new JButton("Done");
		finish.setBounds(180, 185, 80, 25);
		
		for(int i = 0; i < cards.size(); i++){
			pane.add(labels.get(i));
			pane.add(boxes.get(i));
		}
		
		finish.addActionListener(new Controller.DeckFinishListen());
		pane.add(finish);
		
		this.add(pane);
	}
}
