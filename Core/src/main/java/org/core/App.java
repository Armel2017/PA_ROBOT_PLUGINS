package org.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

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

		// Classes correspondantes aux plugins
		Object pluginAttaque = moteur.getClassesPlugins("attaque");
		Object pluginDeplacement = moteur.getClassesPlugins("deplacement");
		Object pluginGraphisme = moteur.getClassesPlugins("graphisme");
		Object pluginGraphisme2 = moteur.getClassesPlugins("graphisme");

		System.out.println(pluginAttaque.getClass());
   
		// Methodes correspondantes aux plugins
		Method[] methodesAttaque = moteur.getMethodesAttque(pluginAttaque.getClass().getDeclaredMethods());
		Method[] methodesDeplacement = moteur.getMethodesDeplacement(pluginDeplacement.getClass().getDeclaredMethods());
		Method[] methodesGraphisme = moteur.getMethodesGraphisme(pluginGraphisme.getClass().getDeclaredMethods());
		Method[] methodesGraphisme2 = moteur.getMethodesGraphisme(pluginGraphisme2.getClass().getDeclaredMethods());

		System.out.println(methodesAttaque[0].getName());
		System.out.println(methodesAttaque[1].getName());
		System.out.println(methodesDeplacement[0].getName());
		System.out.println(methodesGraphisme[0].getName());
		
		LinkedList<Robot> robots = new LinkedList<Robot>();
		Robot r1 = new Robot("robot1", 10, 10);
	    Robot r2 = new Robot("robot2", 300, 300);
	    Robot r3 = new Robot("robot3", 45, 90);
	    Robot r4 = new Robot("robot3", 450, 100);
	    robots.add(r1);
	    robots.add(r2);
	    //robots.add(r3);
	    //robots.add(r4);
		
		Object[] graphArgs = new Object[4];
		graphArgs[3] = panel;
		
		Object[] graphArgs2 = new Object[4];
		graphArgs2[3] = panel;
		
		Object[] deplArgs = new Object[3];
		Object nouvellesPos;
		
		Object caracsAttaque;
		
		while(true) {
			// Appel d'une méthode de plugin
			try {
				panel.removeAll();
				graphArgs[0] = r1.getPosX();
				graphArgs[1] = r1.getPosY();
				graphArgs[2] = r1.getNom();
				methodesGraphisme[0].invoke(pluginGraphisme, graphArgs);
				graphArgs2[0] = r2.getPosX();
				graphArgs2[1] = r2.getPosY();
				graphArgs2[2] = r2.getNom();
				methodesGraphisme2[0].invoke(pluginGraphisme2, graphArgs2);
				
				caracsAttaque = methodesAttaque[0].invoke(pluginAttaque, (Object[]) null);
				
				for(Robot robot : robots) {
					deplArgs[0] = robot.getPosX();
					deplArgs[1] = robot.getPosY();
					deplArgs[2] = moteur.getPositions(robots, robot);
					
					nouvellesPos = methodesDeplacement[0].invoke(pluginDeplacement, deplArgs);
					robot.setPosX(((int[]) nouvellesPos)[0], bordX);
					deplArgs[0] = robot.getPosX();
					robot.setPosY(((int[]) nouvellesPos)[1], bordY);
					deplArgs[1] = robot.getPosY();
					
					for(Robot r : robots) {
						if(!robot.equals(r) && robot.distance(r) <= (Integer)(methodesAttaque[1].invoke(pluginAttaque, (Object[]) null))){
							
							r.degatsPris(((int[])caracsAttaque)[0]);
							robot.setEnergie(robot.getEnergie() - (((int[])caracsAttaque)[1]));
							System.out.println(robot.getNom() + " attaque " + r.getNom() + ", vie restante : " + r.getVie());
							if(r.getVie() <= 0) {
								robots.remove(r);
							}
						}
					}
				}
				
				if(robots.size() == 1) {
					break;
				}
				
				TimeUnit.SECONDS.sleep(1);
				
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		JLabel winnerlabel = new JLabel(robots.get(0).getNom() + " est le vainqueur !");
		winnerlabel.setBounds((bordX/2)-200, (bordY/2)-200, 200, 200);
		winnerlabel.setOpaque(true);
		panel.add(winnerlabel);
		panel.revalidate();
		panel.repaint();
		System.out.println(robots.get(0).getNom() + " est le vainqueur !");

	}
}
