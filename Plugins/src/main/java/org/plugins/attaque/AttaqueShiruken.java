package org.plugins.attaque;

import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.*;
import java.util.*;
import org.plugins.*;


@Plugin(name="AttaqueShiruken", type=PluginType.ATTAQUE)
public class AttaqueShiruken{

	public static final int DEGAT_PRIS=5;
	public static final int ENERGIE_PRIS=30;
	public static final int PORTEE = 200;

	/**Envoie un shiruken au 200 et on lui enleve 5 degats
	 et l' attaquand perd 30 points d'energie*/
	@Attaque(name="attaque")
	public int[] attaque(){
		return new int[]{DEGAT_PRIS,ENERGIE_PRIS};
	}

	@Portee(name="portee")
	public int portee() {
		return PORTEE;
	}



	
}