package org.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class App {
	public static void main( String[] args ) {
		Moteur moteur = new Moteur();

		// Classes correspondantes aux plugins
		Object pluginAttaque = moteur.getClassesPlugins("attaque");
		Object pluginDeplacement = moteur.getClassesPlugins("deplacement");
		Object pluginGraphisme = moteur.getClassesPlugins("graphisme");

		System.out.println(pluginAttaque.getClass());
   
		// Methodes correspondantes aux plugins
		Method[] methodesAttaque = moteur.getMethodesAttque(pluginAttaque.getClass().getDeclaredMethods());
		Method[] methodesDeplacement = moteur.getMethodesDeplacement(pluginDeplacement.getClass().getDeclaredMethods());
		Method[] methodesGraphisme = moteur.getMethodesGraphisme(pluginGraphisme.getClass().getDeclaredMethods());

		System.out.println(methodesAttaque[0].getName());
		System.out.println(methodesDeplacement[0].getName());
		System.out.println(methodesGraphisme[0].getName());

		// Appel d'une méthode de plugin
		/*try {
			methodesAttaque[0].invoke(pluginAttaque, (Object[])null);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		/*Robot r1 = new Robot("robot1", 10, 10);
	    Robot r2 = new Robot("robot2", 100, 100);

	    ArrayList<Robot> robots = new ArrayList<Robot>();
	    robots.add(r1);
	    robots.add(r2);*/

	}
}
