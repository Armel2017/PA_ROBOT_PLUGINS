package org.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.SecureClassLoader;
import java.util.ArrayList;

public class ClassLoader extends SecureClassLoader{
	
	ArrayList<File> paths;
	public ClassLoader(ArrayList<File> paths) {
		this.paths = paths;
	}

	public void setPaths(ArrayList<File> paths) {
		this.paths = paths;
	}
	
	// loadClass de la Class spécifiée en cherchant dans le path
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] b = null;
		InputStream is = null;
		for (File path : paths) {
			if (path.isDirectory()) {
				b = loadClassData(name, path);
				if(b != null) break;
			}
		}
		if (b == null) {
			throw new java.lang.ClassNotFoundException("classe " + name + " pas trouve parmi " + paths);
		}
		else {
			return super.defineClass(name, b, 0, b.length);
		}
	}
	
	// Retourne un byte array de la classe chargée
	private byte[] loadClassData(String name, File path) throws ClassNotFoundException {
		byte[] b = null;
		name = name.replace(".", File.separator);
		File classPath = new File(path.toString() + File.separator + name + ".class");
		
		if (path.isDirectory() && classPath.isFile()) {
			try {
				b = new byte[(int) classPath.length()];
				b = Files.readAllBytes(classPath.toPath());

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return b;
	}
	
	public Class<?> define(String name, byte[] b, int off, int len) {
		return this.defineClass(name, b, off, len);
	}
	
	// Conversion d'un InputStream en byte array
	private byte[] writeToByteArray(InputStream is) {
		byte[] b = null;
		try {
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
