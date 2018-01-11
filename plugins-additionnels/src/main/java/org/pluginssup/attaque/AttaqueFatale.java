package org.pluginssup.attaque;

import org.core.*;
import org.plugins.*;
import java.util.*;


@Plugin(name="AttaqueFatale", type=PluginType.ATTAQUE)
public class AttaqueFatale {

	public static final int DEGAT_PRIS=100;
	public static final int ENERGIE_PRIS=80;

	private Random rand = new Random();

	/**Tue un robot au hasard*/
	@Attaque(name="attaque")
	public void attaque(Robot attaquant, List<Robot> list){
		Robot victime=list.get(rand.nextInt(list.size()));
		victime.degatsPris(DEGAT_PRIS);
		victime.setEnergie(victime.getEnergie()-ENERGIE_PRIS);
	}

	
}