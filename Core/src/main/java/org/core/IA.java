package org.core;

public interface IA {
	
	
	public static final int RANDOM = 0;
	public static final int MOYEN = 1;
	public static final int MAX = 2;
	public static final int ENERGIE_RANDOM = 0;
	public static final int ENERGIE_MOYEN = 50;
	public static final int ENERGIE_MAX = 100;

	
	public int getNiveau();

	public void jouer(Robot robot);

	public boolean seDeplacer(Robot robot);

	public boolean deplacementRandom(Robot robot);
	
	public boolean attaquer(Robot robot);

	public boolean approachOthers(Robot robot);
	
	public boolean attaquerMax(Robot robot);
	
	public String getLibelle();

	

}
