package org.core;

import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.WindowConstants;


public class App {
  private static final int bordX = 600;
  private static final int bordY = 400;
  
  public static void main( String[] args ) {
    Moteur moteur = new Moteur();
    
    JFrame frame = new JFrame("Robot war");
    
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.setSize(bordX, bordY);
  
      JPanel panel = new JPanel(null);
      JMenuBar menuBar = new JMenuBar();
      //menuBar.add(new AdditionnelPluginMenu());
  
      frame.add(panel);
      frame.setJMenuBar(menuBar);
        frame.setVisible(true);

        LinkedList<Robot> robots = new LinkedList<Robot>();
    Robot r1 = new Robot("robot1", 10, 10);
    Robot r2 = new Robot("robot2", 30, 30);
    robots.add(r1);
    robots.add(r2);
    
    moteur.lancerPartie(panel, robots, bordX, bordY);
  }
}
