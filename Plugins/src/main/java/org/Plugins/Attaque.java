package org.Plugins;

import java.lang.annotation.*;

public class Attaque {
	
	public static void main(String [] args)
	{
		@Override
		public String toString() {
			return "Attaque normale";
		}
		
		@Configurable(nom="Dommages infligés")
		private int DAMMAGE_PER_ITER = 5;
		
		@Configurable(nom="Energie consommée")
		private int REQUIERED_ENERGY = 30;
		
		private final double NB_ITERATION_BY_ATTACK = 5;
		
		private int nbIterationAttacking = 0;
		
	}
	
	/**
	 * Methode permettant de selectionner
	 * les robots a attaquer
	 */
	@Override
	public void computeNextAttackToDo(Robot b) {
		if(b.isAttacking()) {
			return;
		}
		
		List<? extends Positionnable> betesVues = b.getChosesVues();
		if(betesVues.size() > 0) {
			b.setAttacking(true);
			b.setEnergy(b.getEnergy() - REQUIERED_ENERGY);
		}
	}
	
	/**
	 * Methode permettant de realiser
	 * l'attaque
	 */
	@Override
	public void doTheNextAttack(Robot b) {
		if(b.isAttacking()) {
			
			// LES GARS DEVANT PRENNENT CHER
			List<? extends Positionnable> betesVues = b.getChosesVues();
			if(b.canAttack(REQUIERED_ENERGY) && betesVues.size() > 0) {
				for(Positionnable p : betesVues) {
					if(p instanceof Robot) {
						Robot be = (Robot)p;
						be.damage(DAMMAGE_PER_ITER);
					}
				}
			}
			
			nbIterationAttacking ++;
			
			if(nbIterationAttacking >= NB_ITERATION_BY_ATTACK) {
				b.setAttacking(false);
				nbIterationAttacking = 0;
			}
		}
	}
	
	

}
