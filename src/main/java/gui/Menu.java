package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Classe permettant de gere et afficher
 * l'interface d'accueil
 */
@SuppressWarnings("serial")
public class Menu extends JPanel {

	private Image img;
	
	/**
	 * Methode de construction de l'interface
	 * 
	 * @param width
	 * @param height
	 * @param ui
	 */
	public Menu(int width, int height) {
		
		
		setPreferredSize(new Dimension(width, height));
		setBorder(new EmptyBorder(300, 0, 0, 0));
		
	
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 0, 0, this);
		paintChildren(g);
	}
}