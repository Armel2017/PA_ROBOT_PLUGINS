package org.plugins.graphisme;

import java.awt.*;
import java.util.*;
import java.util.List;
import javafx.util.Pair;

import javax.swing.*;

import org.plugins.*;

/**
 * Classe qui gere le plugin de graphisme basique, chaque robot est represente
 * par un carre de couleur aleatoire sans barre de vie et d'energie
 */

@Plugin(name="XXGraphismeBarreEtat", type=PluginType.GRAPHISME)
public class XXGraphismeBarreEtat {

	private final static Random rand = new Random();

	private final int[] colrgb = { 155 + rand.nextInt(100), 155 + rand.nextInt(100), 0 };

	private Color couleurFond = (new Color(colrgb[rand.nextInt(3)], colrgb[rand.nextInt(3)], colrgb[rand.nextInt(3)]));

	@Graphisme(name="write")
	public void draw(int posX, int posY, Map<String, Pair<Integer, Integer>> infos, JPanel panel) {
		
		
		
		panel.revalidate();
		panel.repaint();
		
	}

	public String toString() {
		return "Barre d'Etat";
	}
}
