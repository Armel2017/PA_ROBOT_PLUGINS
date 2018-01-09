package org.plugins.graphisme;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import org.core.Robot;
import org.plugins.Plugin;
import org.plugins.PluginType;

/**
 * Classe qui gere le plugin de graphisme basique, chaque robot est represente
 * par un carre de couleur aleatoire sans barre de vie et d'energie
 */

public class GrapgismeBasique {

	private final static Random rand = new Random();

	private final int[] colrgb = { 155 + rand.nextInt(100), 155 + rand.nextInt(100), 0 };

	private Color couleurFond = (new Color(colrgb[rand.nextInt(3)], colrgb[rand.nextInt(3)], colrgb[rand.nextInt(3)]));

	@Plugin(name="GraphismeBasique", type=PluginType.GRAPHISME)
	public void draw(Robot robot, Graphics g) {
		int r = (int) (robot.getHeight() / 2) + 15;
		int rX = (int) robot.getPosX();
		int rY = (int) robot.getPosY();

		// DESSIN DU ROBOT
		g.setColor(robot.getCouleur());
		g.drawRoundRect(rX - r, rY - r, r * 2, r * 2, r, r);

		// g.setColor(new Color(153, 0, 0));
		g.setColor(couleurFond);
		g.fillRoundRect(rX - r, rY - r, r * 2, r * 2, r, r);

		double direction = robot.getDirectionCourante();

		// SABRE
		int jx = (int) (rX + (r + 3) * Math.cos(direction + Math.PI / 2));
		int jy = (int) (rY + (r + 3) * Math.sin(direction + Math.PI / 2));
		int kx = (int) (rX + (r + 10) * Math.cos(direction + Math.PI / 2));
		int ky = (int) (rY + (r + 10) * Math.sin(direction + Math.PI / 2));
		int lx = (int) (rX + (r + 50) * Math.cos(direction));
		int ly = (int) (rY + (r + 50) * Math.sin(direction));

		g.setColor(Color.yellow);
		g.fillPolygon(new int[] { jx, kx, lx }, new int[] { jy, ky, ly }, 3);
		g.setColor(couleurFond);
		g.drawPolygon(new int[] { jx, kx, lx }, new int[] { jy, ky, ly }, 3);

	}

	public String toString() {
		return "Carré";
	}
}
