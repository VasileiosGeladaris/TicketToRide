/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author billy
 */
public class MyBigCitiesDialog extends JFrame{
	
	public MyBigCitiesDialog(String player, int visits[]){
		this.setResizable(false);
        this.setTitle("Big Cities Cards For " + player);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.initComponents(visits);	
	}
	
	public void initComponents(int visits[]){
		
		JLayeredPane pane = new JLayeredPane();
		pane.setBounds(0, 0, 800, 300);
		
		JLabel city1 = new JLabel(new ImageIcon(new ImageIcon("resources/images/bigCitiesCards/Chicago.jpg").getImage().getScaledInstance(110, 180, Image.SCALE_SMOOTH)));
		JLabel city2 = new JLabel(new ImageIcon(new ImageIcon("resources/images/bigCitiesCards/Dallas.jpg").getImage().getScaledInstance(110, 180, Image.SCALE_SMOOTH)));
		JLabel city3 = new JLabel(new ImageIcon(new ImageIcon("resources/images/bigCitiesCards/LosAngeles.jpg").getImage().getScaledInstance(110, 180, Image.SCALE_SMOOTH)));
		JLabel city4 = new JLabel(new ImageIcon(new ImageIcon("resources/images/bigCitiesCards/Miami.jpg").getImage().getScaledInstance(110, 180, Image.SCALE_SMOOTH)));
		JLabel city5 = new JLabel(new ImageIcon(new ImageIcon("resources/images/bigCitiesCards/NewYork.jpg").getImage().getScaledInstance(110, 180, Image.SCALE_SMOOTH)));
		JLabel city6 = new JLabel(new ImageIcon(new ImageIcon("resources/images/bigCitiesCards/Seattle.jpg").getImage().getScaledInstance(110, 180, Image.SCALE_SMOOTH)));
		
		JLabel counter1 = new JLabel("<html>Times Visited: " + visits[0] + "<br>&nbsp;&nbsp;&nbsp;&nbsp;Owned: " + ((visits[0] == 3) ? "Yes" : "No"));
		JLabel counter2 = new JLabel("<html>Times Visited: " + visits[1] + "<br>&nbsp;&nbsp;&nbsp;&nbsp;Owned: " + ((visits[1] == 3) ? "Yes" : "No"));
		JLabel counter3 = new JLabel("<html>Times Visited: " + visits[2] + "<br>&nbsp;&nbsp;&nbsp;&nbsp;Owned: " + ((visits[2] == 3) ? "Yes" : "No"));
		JLabel counter4 = new JLabel("<html>Times Visited: " + visits[3] + "<br>&nbsp;&nbsp;&nbsp;&nbsp;Owned: " + ((visits[3] == 3) ? "Yes" : "No"));
		JLabel counter5 = new JLabel("<html>Times Visited: " + visits[4] + "<br>&nbsp;&nbsp;&nbsp;&nbsp;Owned: " + ((visits[4] == 3) ? "Yes" : "No"));
		JLabel counter6 = new JLabel("<html>Times Visited: " + visits[5] + "<br>&nbsp;&nbsp;&nbsp;&nbsp;Owned: " + ((visits[5] == 3) ? "Yes" : "No"));
		
		city1.setBounds(12, 20, 110, 180);
		city2.setBounds(145, 20, 110, 180);
		city3.setBounds(278, 20, 110, 180);
		city4.setBounds(411, 20, 110, 180);
		city5.setBounds(544, 20, 110, 180);
		city6.setBounds(677, 20, 110, 180);
		
		counter1.setBounds(12, 210, 130, 30);
		counter2.setBounds(145, 210, 130, 30);
		counter3.setBounds(278, 210, 130, 30);
		counter4.setBounds(411, 210, 130, 30);
		counter5.setBounds(544, 210, 130, 30);
		counter6.setBounds(677, 210, 130, 30);
		
		pane.add(city1);
		pane.add(city2);
		pane.add(city3);
		pane.add(city4);
		pane.add(city5);
		pane.add(city6);
		pane.add(counter1);
		pane.add(counter2);
		pane.add(counter3);
		pane.add(counter4);
		pane.add(counter5);
		pane.add(counter6);
		
		this.add(pane);
	}
}
