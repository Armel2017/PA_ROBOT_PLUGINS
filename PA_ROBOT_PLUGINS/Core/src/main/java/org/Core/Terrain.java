package org.Core;

import java.util.ArrayList;

public class Terrain {
	private ArrayList<Robot> robots;

    public Terrain(){
        this.robots = new ArrayList<Robot>();
    }

    public boolean ajouterRobot(Robot robot){
        return robots.add(robot);
    }
}