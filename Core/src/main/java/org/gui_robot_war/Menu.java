package org.gui_robot_war;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe qui permet d'afficher
 * l'interface d'accueil
 */

public class Menu extends JFrame {
	int x=0;
	int y=0;
	
	Timer timer;
	Menu menu;

	public Menu() {
		
		
		// operation de fermeture
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
		// extraire les dimensions
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
	    
	    // definir l'icone
		setTitle("RobotWar");

	    // ajouter le panneau au Menu
		add(new Panneau());

		// ajouter un timer
		timer = new Timer(100,new BougeAction());
		timer.start();

		// ajouter le bouton stop
		Bouton bouton = new Bouton();
		add(bouton,BorderLayout.NORTH);

		setVisible(true);
	    }

	    class Panneau extends JPanel
	    {
			public Panneau()
			{
			
			    setBackground(Color.white);
			}
		
			public void paintComponent(Graphics g){
			    super.paintComponent(g);
	
			    Graphics2D gr=(Graphics2D) g;
	
			    Rectangle2D rec = new Rectangle2D.Double(x,y,20,20);
			    gr.fill(rec);
			   
			}
	    }
		
	    public class BougeAction implements ActionListener {
			public void actionPerformed(ActionEvent e) {
			    x += 10;
			    x = x % getWidth();
			    y += 10;
			    y = y %getHeight();
			    repaint();
			}
	    }

	    public class Bouton extends JButton {
			public Bouton() {
			    super("Stop");
			    addActionListener(new Stop());
			}
	    }
	    
	    public class Stop implements ActionListener {
			public void actionPerformed(ActionEvent e) {
			    Bouton bouton = new Bouton();
				if (timer.isRunning()) {
				timer.stop();
				bouton.setText("Start");
			    }
			    else {
				timer.start();
				bouton.setText("Stop");
			    }
			}
	    }
}
