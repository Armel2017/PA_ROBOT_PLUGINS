package org.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import org.core.ClassLoader;
import java.util.ArrayList;
import java.util.List;

public class Repository<T> {
    File base;

    public Repository(File base) {
        this.base = base;
    }

    // Chargement d'une ArrayList de Class<?>
    public ArrayList<Class<?>> load() {
    		// Instanciation du ClassLoader avec un path défini
        ArrayList<File> paths = new ArrayList<File>();
        paths.add(base);
        paths.add(new File("Core/target/classes"));
        ClassLoader classLoader = new ClassLoader(paths);
        
        //Conversion en byte array du fichier de la classe Plugin du module Plugins
        byte[] b = writeToByteArray(new File("Plugins/target/classes/org/plugins/Plugin.class"));
        // Défine class org.plugins.Plugin
        Class<?> pluginClass = classLoader.define("org.plugins.Plugin", b, 0, b.length);
        
        // Chargement de tous les fichiers .class dans le path défini
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        DirReader dirReader = new DirReader();
        ArrayList<String> filelist = dirReader.internalFilteredDirContent(base);
        
        // Pour chaque fichier retourné
        for (String s : filelist) {
            s = s.replace(paths.get(0).toString() + File.separator, "");
            s = s.replace(".class", "");
            s = s.replace(File.separator, ".");
            try {
            		// Si la classe est annotée par @Plugin, on la retourne
            		Class<?> temp = classLoader.loadClass(s);
            		if(temp.isAnnotationPresent((Class<? extends Annotation>) pluginClass)) {
            			classes.add(temp);
            		}
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return classes;
    }
    
    // Conversion d'un File en byte array
    private byte[] writeToByteArray(File f) {
		byte[] b = null;
		InputStream is = null;
		try {
			is = new FileInputStream(f.getAbsolutePath());
			b = new byte[is.available()];
			int incr = 0;
			while (incr != -1) {
				incr = is.read(b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}

}
