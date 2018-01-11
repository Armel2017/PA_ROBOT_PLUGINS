package org.core;

import java.awt.List;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;


public class Moteur {
	private static final String pluginsAdd = "plugins-additionnels/target/classes";
	private static final String plugins = "Plugins/target/classes";

	// Chargement des classes dans le package du type de plugin specifié
	// TODO : Chercher d'abord dans plugins-additionnels puis compléter avec Plugins
	public Object getClassesPlugins(String typePlugin) {
		// Chargement des classes dans le module Plugins
		Repository repository = new Repository(new File(plugins));
		ArrayList<Class<?>> classes = repository.load();

		// Pour chaque classe chargée, verfier si le package correspond à celui specifié
		for (Class<?> c : classes) {
			try {	
				if(c.getName().matches("org.plugins."+ typePlugin + ".*")) {
					System.out.println(c.getName());
					// Retourne la première class appropriée trouvée
					return c.getConstructor().newInstance();
				}
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return null;
	}

	// Récupération de toutes les méthodes nécessaires en fonction du name des annotations
	public Method[] getMethodesAttque(Method[] methods) {
		Method[] methodesAttaque = new Method[2];
        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
            		System.out.println(annotation.toString());
            		if(annotation.toString().matches(".*.name=attaque.*")) {
            			methodesAttaque[0] = method;
            		}
            		else if(annotation.toString().matches(".*.name=portee.*")) {
            			methodesAttaque[1] = method;
            		}
            }
        }
		return methodesAttaque;
	}
	
	// Récupération de toutes les méthodes nécessaires en fonction du name des annotations
	public Method[] getMethodesDeplacement(Method[] methods) {
		Method[] methodesDeplacement = new Method[1];
        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
            		System.out.println(annotation.toString());
            		if(annotation.toString().matches(".*.name=deplacer.*")) {
            			methodesDeplacement[0] = method;
            		}
            }
        }
		return methodesDeplacement;
	}

	// Récupération de toutes les méthodes nécessaires en fonction du name des annotations
	public Method[] getMethodesGraphisme(Method[] methods) {
		Method[] methodesGraphisme = new Method[1];
        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
            		System.out.println(annotation.toString());
            		if(annotation.toString().matches(".*.name=draw.*")) {
            			methodesGraphisme[0] = method;
            		}
            }
        }
		return methodesGraphisme;
	}
}
