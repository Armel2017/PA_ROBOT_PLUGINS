package org.plugins.attaque;

import java.util.List;
// import org.core.*;
import org.plugins.*;

@Plugin(name="AttaqueFacile", type=PluginType.ATTAQUE)
public class AttaqueDeBase {

	private final static int PORTEE = 30;
	private final static int DEGATS = 20;
	private final static int ENERGIE = 10;
	
	/** une attaque qui diminue les degats par 2, 
	de tous les enemies dans une portee de 5.
	Il consomme 10 energies par enemie attaque  */
	@Attaque(name="attaque")
	public int[] attaque(){
		int[] caracteristiques = new int[2];
		caracteristiques[0] = DEGATS;
		caracteristiques[1] = ENERGIE;
		
		return caracteristiques;
	}
	
	@Portee(name="portee")
	public int portee() {
		return PORTEE;
	}
}
