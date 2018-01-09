package org.plugins.attaque;

import java.util.List;
import org.core.*;
import org.plugins.*;


public class AttaqueDeBase {

	/** une attaque qui diminue les degats par 2, 
	de tous les enemies dans une portee de 5.
	Il consomme 10 energies par enemie attaque  */
	@Plugin(name="AttaqueFacile", type=PluginType.ATTAQUE)
	public void attaque(Robot attaquant, List<Robot> lr){
		for(Robot r: lr){
			if(!r.equals(attaquant)){
				if(r.distance(attaquant)<=5){
					r.degatsPris(2);
					attaquant.setEnergie(attaquant.getEnergie()-10);
				}
			}
		}


	}
	
	
}
