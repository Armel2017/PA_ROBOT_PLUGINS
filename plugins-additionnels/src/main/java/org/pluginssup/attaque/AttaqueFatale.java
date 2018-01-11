package org.pluginssup.attaque;

import org.plugins.*;
import java.util.*;


@Plugin(name="AttaqueFatale", type=PluginType.ATTAQUE)
public class AttaqueFatale {

	public static final int DEGAT_PRIS=100;
	public static final int ENERGIE_PRIS=80;

	private Random rand = new Random();

	/**Tue un robot au hasard*/
	@Attaque(name="attaque")
	public int[] attaque(){
		return new int[]{DEGAT_PRIS, ENERGIE_PRIS};
	}

	@Portee(name="portee")
	public int portee() {
		return 10;
	}



	
}