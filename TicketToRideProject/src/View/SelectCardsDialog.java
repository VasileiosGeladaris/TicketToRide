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
public class SelectCardsDialog extends JFrame{
	public JCheckBox box1 = new JCheckBox("Keep");
	public JCheckBox box2 = new JCheckBox("Keep");
	public JCheckBox box3 = new JCheckBox("Keep");
	public JCheckBox box4 = new JCheckBox("Keep");
	public JCheckBox box5 = new JCheckBox("Keep");
	public JCheckBox box6 = new JCheckBox("Keep");
	//private ArrayList<DestinationCard> cards = new ArrayList<>();
	
	public SelectCardsDialog(ArrayList<DestinationCard> cards, String player){
		this.setResizable(false);
        this.setTitle("Select Cards " + player);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setSize(635, 250);
		this.initComponents(cards);		
	}
	
	private void initComponents(ArrayList<DestinationCard> cards){
		JLayeredPane pane = new JLayeredPane();
		JLabel card1 = new JLabel(new ImageIcon(new ImageIcon(cards.get(0).getImage()).getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH)));
		JLabel card2 = new JLabel(new ImageIcon(new ImageIcon(cards.get(1).getImage()).getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH)));
		JLabel card3 = new JLabel(new ImageIcon(new ImageIcon(cards.get(2).getImage()).getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH)));
		JLabel card4 = new JLabel(new ImageIcon(new ImageIcon(cards.get(3).getImage()).getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH)));
		JLabel card5 = new JLabel(new ImageIcon(new ImageIcon(cards.get(4).getImage()).getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH)));
		JLabel card6 = new JLabel(new ImageIcon(new ImageIcon(cards.get(5).getImage()).getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH)));
		
		pane.setBounds(0, 0, 635, 250);
		card1.setBounds(5, 5, 100, 140);
		card2.setBounds(110, 5, 100, 140);
		card3.setBounds(215, 5, 100, 140);
		card4.setBounds(320, 5, 100, 140);
		card5.setBounds(425, 5, 100, 140);
		card6.setBounds(530, 5, 100, 140);
		
		box1.setBounds(5, 150, 80, 15);
		box2.setBounds(110, 150, 80, 15);
		box3.setBounds(215, 150, 80, 15);
		box4.setBounds(320, 150, 80, 15);
		box5.setBounds(425, 150, 80, 15);
		box6.setBounds(530, 150, 80, 15);
		
		JButton finish = new JButton("Done");
		finish.setBounds(280, 185, 80, 25);
		
		pane.add(card1);
		pane.add(card2);
		pane.add(card3);
		pane.add(card4);
		pane.add(card5);
		pane.add(card6);
		
		pane.add(box1);
		pane.add(box2);
		pane.add(box3);
		pane.add(box4);
		pane.add(box5);
		pane.add(box6);
		
		finish.addActionListener(new Controller.FinishListen());
		
		pane.add(finish);
		
		this.add(pane);
	}
}
