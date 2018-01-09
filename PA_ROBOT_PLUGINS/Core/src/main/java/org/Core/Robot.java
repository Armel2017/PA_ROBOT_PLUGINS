package org.Core;

import java.awt.Color;

public class Robot {

	private String nom;
	private int nbVie;
	static public int nbVieInit;
	private int energie;
	static public int energieInit;
	private double posX;
	private double posY;
	private boolean EstEnVie;
	private Color couleur;

	protected double champDeVue, vitesseCourante, directionCourante, width, height, prochaineVitesse,
			prochaineDirection;

	public Robot(String nom, int energie, double posX, double posY, boolean estEnVie, Color couleur,
			double champDeVue, double vitesseCourante, double directionCourante, double width, double height,
			double prochaineVitesse, double prochaineDirection) {
		this.nom = nom;
		nbVieInit = 100;
		this.energie = energie;
		this.posX = posX;
		this.posY = posY;
		EstEnVie = estEnVie;
		this.couleur = couleur;
		this.champDeVue = champDeVue;
		this.vitesseCourante = vitesseCourante;
		this.directionCourante = directionCourante;
		this.width = width;
		this.height = height;
		this.prochaineVitesse = prochaineVitesse;
		this.prochaineDirection = prochaineDirection;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public String getNom() {
		return this.nom;
	}

	public double getVitesseCourante() {
		return vitesseCourante;
	}

	public void setVitesseCourante(double vitesseCourante) {
		this.vitesseCourante = vitesseCourante;
	}

	public double getDirectionCourante() {
		return directionCourante;
	}

	public void setDirectionCourante(double directionCourante) {
		this.directionCourante = directionCourante;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getProchaineVitesse() {
		return prochaineVitesse;
	}

	public void setProchaineVitesse(double prochaineVitesse) {
		this.prochaineVitesse = prochaineVitesse;
	}

	public double getProchaineDirection() {
		return prochaineDirection;
	}

	public void setProchaineDirection(double prochaineDirection) {
		this.prochaineDirection = prochaineDirection;
	}

	public int getNbVie() {
		return this.nbVie;
	}

	public void setNbVie(int newNbVie) {
		this.nbVie = newNbVie;
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

	public boolean getEstEnVie() {
		return this.EstEnVie;
	}

	public void setEstEnVie(boolean newEstEnVie) {
		this.EstEnVie = newEstEnVie;
	}

	public int getNbVieInit() {
		return nbVieInit;
	}

	public void setNbVieInit(int nbVieInit) {
		this.nbVieInit = nbVieInit;
	}

	// Les degats
	public void degatsPris(int degats) {
		this.nbVie -= degats;
		if (getNbVie() <= 0) {
			this.setEstEnVie(false);
		}
	}

	// Utilisation d'une action par le robot
	public void action() {

	}
}
