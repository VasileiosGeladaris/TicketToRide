/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Cards.DestinationCard;
import Model.Gameplay.Player;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author billy
 */
public class MyDestCardsDialog extends JFrame{
	public MyDestCardsDialog(ArrayList<DestinationCard> cards, Player player){
		this.setSize(800, 550);
		this.setResizable(false);
		this.setLayout(new GridLayout(5, 10));
		this.setTitle("Player " + player.getID() + " Destination Cards");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		for(int i = 0; i < cards.size(); i++){
			JLabel temp = new JLabel(new ImageIcon(new ImageIcon(cards.get(i).getImage()).getImage().getScaledInstance(80, 110, Image.SCALE_SMOOTH)));
			temp.setSize(80, 110);
			this.add(temp);
		}
	}
}
