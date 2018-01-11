package org.core;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class AdditionnelPluginMenu extends JMenu{

	private Map<Class<?>, Boolean> pluginsAdditionnelsActives = new HashMap<Class<?>,Boolean>();

	public AdditionnelPluginMenu() {
		super("Plugins Additionnels");
		Repository rep = new Repository(new File("plugins-additionnels/target/classes"));
		ArrayList<Class<?>> listeClasses = rep.load();
		for(Class<?> c : listeClasses) {
			pluginsAdditionnelsActives.put(c, false);
			JCheckBoxMenuItem jcbm = new JCheckBoxMenuItem(c.getSimpleName(), false);
			jcbm.setSelected(false);
			jcbm.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent ie) {

				}

			});
			this.add(jcbm);
		}

	}

	public static void main(String[] args) {
		new AdditionnelPluginMenu();
	}

}