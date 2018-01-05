package org.Core;



public class Robot {
	
	public Robot() 
	{
		
		
	}
	

	public boolean canAttack(int rEnerie) {
		return energy >= rEnerie;
	}

	public void setChampDeGuerre(ChampDeGuerre champ) {
		this.champ = champ;
	}

	public boolean isAWinner() {
		return isAWinner;
	}

	public void setAWinner(boolean isAWinner) {
		this.isAWinner = isAWinner;
	}

}
