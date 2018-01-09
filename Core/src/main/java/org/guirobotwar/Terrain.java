package org.guirobotwar;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Terrain extends JPanel{
	
	public Terrain(){
		super(null);
		setSize(100,50);
	}

	/**  Pour dessiner le terrain */
	@Override
	public void paint(Graphics g){
		Color c=new Color(20,50,55,200);
		g.setColor(c);
		g.fillRect(0,0,getWidth(),getHeight());
	}



	

}