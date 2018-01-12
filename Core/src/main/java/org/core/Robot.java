package org.core;

import java.awt.Color;

public class Robot {

    private String nom;
    private int vie = 100;
    private int energie = 100;
    private int posX;
    private int posY;
    private boolean EstEnVie;
    private Color couleur;

    protected double champDeVue, vitesseCourante, directionCourante, width, height, prochaineVitesse,
            prochaineDirection;

    public Robot(String nom, int posX, int posY) {
        this.nom = nom;
        this.posX = posX;
        this.posY = posY;
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

    public int getVie() {
        return this.vie;
    }

    public void setVie(int newVie) {
        this.vie = newVie;
    }

    public int getEnergie() {
        return this.energie;
    }

    public void setEnergie(int newEnergie) {
        this.energie = newEnergie;
    }

    public int getPosX() {
        return this.posX;
    }

    public void setPosX(int newPosX, int bordX) {
        if(newPosX > bordX) {
            this.posX = bordX;
        }
        else if(newPosX < 0) {
            this.posX = 0;
        }
        else {
            this.posX = newPosX;
        }
    }

    public void setPosY(int newPosY, int bordY) {
        if(newPosY > bordY) {
            this.posY = bordY;
        }
        else if(newPosY < 0) {
            this.posY = 0;
        }
        else {
            this.posY = newPosY;
        }
    }
    
    public int getPosY() {
        return this.posY;
    }

    public boolean getEstEnVie() {
        return this.EstEnVie;
    }

    public void setEstEnVie(boolean newEstEnVie) {
        this.EstEnVie = newEstEnVie;
    }

    // Les degats
    public void degatsPris(int degats) {
        this.vie -= degats;
        if (getVie() <= 0) {
            this.setEstEnVie(false);
        }
    }

    // Utilisation d'une action par le robot
    public void action() {

    }

    /**calcul la distance entre deux robots*/
    public double distance(Robot rob){
        double xdiff=this.posX-rob.posX;
        double ydiff=this.posY-rob.posY;
        double xdiffCarre=Math.pow(xdiff,2);
        double ydiffCarre=Math.pow(ydiff,2);
        return Math.sqrt(xdiffCarre+ydiffCarre);
    }
}
