package org.plugins.deplacement;


import java.util.*;
import java.awt.Point;
import org.plugins.*;

/** Va vers le robot le plus proche */
@Plugin(name = "DeplacementVersRobot", type = PluginType.DEPLACEMENT)
public class XXDeplacementVersRobot {
	
	@Deplacement(name="deplacer")
	public int[] deplacementRandom(int posX, int posY, List<Point> coordonneesRobots)
	{
		List<Point> coordonneesTousSaufMoi = new ArrayList<Point>(coordonneesRobots);
		coordonneesTousSaufMoi.remove(new Point(posX, posY));
		Collections.sort(coordonneesTousSaufMoi, new PointComparator(new Point(posX, posY)));

		int[] nouvellesPos = new int[2];
		if(coordonneesTousSaufMoi.size() > 0) {
			Point cible = coordonneesTousSaufMoi.get(0);
			nouvellesPos[0] += (cible.x - posX)/4;
			nouvellesPos[1] += (cible.y - posY)/4;
		} else {
			int distanceMin= -10;
			int distanceMax= 10;
			Random rand = new Random();
			
			nouvellesPos[0] = posX + ((int)((rand.nextInt(10) * distanceMin)+distanceMax * rand.nextInt(10)));
			nouvellesPos[1] = posY + ((int)((rand.nextInt(10) * distanceMin)+distanceMax * rand.nextInt(10)));

		}
	
		return nouvellesPos;
	}

	private class PointComparator implements Comparator<Point> {

		Point origine;

		private PointComparator(Point p) {
			origine = p;
		}

		public int compare(Point p1, Point p2) {
			return (int)(distance(origine,p1) - distance(origine, p2));
		}

		private double distance(Point p1, Point p2) {
			return Math.hypot(p1.x - p2.x, p1.y  - p2.y);
		}
	}


}