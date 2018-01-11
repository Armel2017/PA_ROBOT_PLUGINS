package org.plugins.graphisme;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import org.plugins.*;

@Plugin(name="GraphismeBasique", type=PluginType.GRAPHISME)
public class RobotImage {

		@Graphisme(name="draw")
		public void draw(int posX, int posY, String name, JPanel panel) {
			
			
			JLabel label = new JLabel(new ImageIcon("resources/images/destrier.png"));
			label.setBounds(posX, posY, 40, 52);
			label.setOpaque(true);

			panel.add(label);
			
			panel.revalidate();
			panel.repaint();
		}
}
