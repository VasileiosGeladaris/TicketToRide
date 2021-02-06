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
public class RedeemCardDialog extends JFrame{
	
	public RedeemCardDialog(DestinationCard card, String player){
		this.setResizable(false);
        this.setTitle(player);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setSize(270, 300);
		this.initComponents(card);		
	}
	
	void initComponents(DestinationCard card){
		JLayeredPane pane = new JLayeredPane();
		pane.setBounds(0, 0, this.getWidth(), this.getHeight());
		
		JLabel icon = new JLabel(new ImageIcon(new ImageIcon(card.getImage()).getImage().getScaledInstance(120, 175, Image.SCALE_SMOOTH)));
		icon.setBounds(75,15, 120, 175);
		
		JLabel text = new JLabel("Do you want to buy off this card?");
		text.setBounds(20, 200, 250, 15);
		
		JButton yes = new JButton("Yes");
		yes.setBounds(55, 235, 70, 30);
		yes.addActionListener(new Controller.RedeemCardListener());
		
		JButton no = new JButton("No");
		no.setBounds(145, 235, 70, 30);
		no.addActionListener(new Controller.DontRedeemCardListener());
		
		pane.add(icon);
		pane.add(text);
		pane.add(yes);
		pane.add(no);
		
		this.add(pane);
	}
}
