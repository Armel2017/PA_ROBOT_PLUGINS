package org.core;

public class Robot {
	
	private String nom;
	private int hp;
	private int energie;
	private double posX;
	private double posY;
	private boolean enVie;
	
	public Robot(String nom, double posX, double posY) {
		this.nom = nom;
		this.posX = posX;
		this.posY = posY;
		this.enVie = true;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int getHp() {
		return this.hp;
	}
	
	public void setHP(int newHp) {
		this.hp = newHp;
	}
	
	public int getEnergie() {
		return this.energie;
	}
	
	public void setEnergie(int newEnergie) {
		this.energie = newEnergie;
	}
	
	public double getPosX() {
		return this.posX;
	}
	
	public void setPosX(int newPosX) {
		this.posX = newPosX;
	}
	
	public double getPosY() {
		return this.posY;
	}
	
	public void setPosY(int newPosY) {
		this.posY = newPosY;
	}
	
	public boolean getEnVie() {
		return this.enVie;
	}
	
	public void setEnVie(boolean newEnVie) {
		this.enVie = newEnVie;
	}
	
	// Les dégâts que prennent le robot
	public void degatsPris(int degats) {
		this.hp -= degats;
		if(getHp() <= 0) {
			this.setEnVie(false);
		}
	}
	
	// Utilisation d'une action par le robot
	public void action() {
		
	}
}
