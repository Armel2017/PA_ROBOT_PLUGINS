package org.plugins.deplacement;

import java.util.List;
import java.util.Random;


import org.plugins.Deplacement;
import org.plugins.Plugin;
import org.plugins.PluginType;

@Plugin(name = "DeplacementAleatoire", type = PluginType.DEPLACEMENT)
public class DeplacementAleatoire {
	
	protected final static int DEFAUT_RAPIDITE = 5;
	protected int rapidite = DEFAUT_RAPIDITE;
	
	@Deplacement(name="deplacer")
	public int[] deplacementRandom(int posX, int posY)
	{
		int[] nouvellesPos = new int[2];
		int distanceMin= -10;
		int distanceMax= 10;
		Random rand = new Random();
		
		nouvellesPos[0] = posX + ((int)((rand.nextInt(10) * distanceMin)+distanceMax * rand.nextInt(10)));
		nouvellesPos[1] = posY + ((int)((rand.nextInt(10) * distanceMin)+distanceMax * rand.nextInt(10)));
	
		return nouvellesPos;
	}

}