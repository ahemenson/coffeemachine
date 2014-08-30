package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;
import br.ufpb.dce.aps.coffeemachine.Recipe;

public class Bouillon implements IDrink {

	private int cup;
	private double water, bouilon;

	public Bouillon() {
		cup = 1;
		water = 100;
		bouilon = 10;
	}

	public boolean Plan() {
		if (!GerenteDrink.getFactory().getCupDispenser().contains(cup)) {
			MyDisplay.setWarnMessage(Messages.OUT_OF_CUP);
			return false;
		}

		if (!GerenteDrink.getFactory().getWaterDispenser()
				.contains(water)) {
			MyDisplay.setWarnMessage(Messages.OUT_OF_WATER);
			return false;
		}
		if (!GerenteDrink.getFactory().getBouillonDispenser().contains(bouilon)) {
			MyDisplay.setWarnMessage(Messages.OUT_OF_BOUILLON_POWDER);
			return false;
		}
		return true;
	}

	public void Mix() {
		GerenteDrink.getFactory().getBouillonDispenser().release( bouilon);
		GerenteDrink.getFactory().getWaterDispenser().release(water);

	}

	public void drinkRelease() {
		GerenteDrink.getFactory().getCupDispenser().release(cup);
		GerenteDrink.getFactory().getDrinkDispenser().release(100);

	}

	public void configureDrink(Recipe recipe) {
		// TODO Auto-generated method stub
		
	}

}
