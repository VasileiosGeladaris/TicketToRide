/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Control.Controller;
import Model.Gameplay.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author billy
 */
public class ShowWinnerDialog extends JFrame{
	
	public ShowWinnerDialog(Player player){
		JLayeredPane pane = new JLayeredPane();
		pane.setBounds(0, 0, this.getWidth(), this.getHeight());
		
		JLabel label = new JLabel((player.getID() == 0) ? "The Game is a Tie!" : (player.getID() == 1) ? "Player 1 is the Winner!" : "Player 2 is the Winner!");
		label.setBounds(120, 10, 200, 70);
		
		JButton button = new JButton("Close Game");
		button.setBounds(115, 90, 150, 30);
		button.addActionListener(new Controller.ExitGameListener());
		
		pane.add(label);
		pane.add(button);
		
		this.add(pane);
		this.setTitle("End Game Reults");
		this.setResizable(false);
		this.setSize(380, 200);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
}
