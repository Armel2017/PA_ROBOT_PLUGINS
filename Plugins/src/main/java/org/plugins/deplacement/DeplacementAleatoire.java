package org.plugins.deplacement;

import java.util.List;
import org.core.Robot;
import org.plugins.Deplacement;
import org.plugins.Plugin;
import org.plugins.PluginType;

@Plugin(name = "DeplacementAleatoire", type = PluginType.DEPLACEMENT)
public class DeplacementAleatoire {
	
	protected final static int DEFAUT_RAPIDITE = 5;
	protected int rapidite = DEFAUT_RAPIDITE;
	
	@Deplacement(name="deplacer")
	public void deplacementRandom(DeplacementAleatoire rand, List<Robot> l)
	{
		int distanceMin=0;
		int distanceMax= 50;
		double deplacementAleatoire = (Math.random()* distanceMax)+distanceMin;
		for (Robot r:l){
		
		if (r.getDirectionCourante()!=0)
		{
			r.setProchaineVitesse(rapidite);
			r.setProchaineDirection(deplacementAleatoire);
		
		}
		
		}
	
	}

}