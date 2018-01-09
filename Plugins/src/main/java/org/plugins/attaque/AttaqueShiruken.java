package org.plugins.attaque;

import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.*;
import java.util.*;
import org.core.*;
import org.plugins.*;



public class AttaqueShiruken{

	@Plugin(name="AttaqueShiruken", type=PluginType.ATTAQUE)
	public void attaque(Robot attaquant, List<Robot> list){
		List<Robot> robotsTrie=new ArrayList<Robot>(list);
		RobotDistanceComparator rdc=new RobotDistanceComparator(attaquant);
		Collections.sort(robotsTrie,rdc);
		Robot victime=robotsTrie.get(0);
	}

	private class RobotDistanceComparator implements Comparator<Robot>{

		private Robot cible;

		public RobotDistanceComparator(Robot r){
			this.cible=r;
		}

		/** si r1 est plus proche de la cible, on renvoie un resultat negatif si r1==r2, on renvoie
		0 sinon un resultat positif*/
		@Override
		public int compare(Robot r1, Robot r2){
			double d1=r1.distance(cible);
			double d2=r2.distance(cible);
			double res= d1-d2;
			return (int)res;
		}


	}
}