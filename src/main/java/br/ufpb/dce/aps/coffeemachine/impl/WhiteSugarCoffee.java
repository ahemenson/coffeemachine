package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;
import br.ufpb.dce.aps.coffeemachine.Recipe;

public class WhiteSugarCoffee implements IDrink {

	private int cup;
	private double water, coffeePonder, creame, sugar;

	public WhiteSugarCoffee() {
		cup = 1;
		water = 80;
		coffeePonder = 15;
		creame = 20;
		sugar = 5;
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

		if (!GerenteDrink.getFactory().getCoffeePowderDispenser()
				.contains(coffeePonder)) {
			MyDisplay.setWarnMessage(Messages.OUT_OF_COFFEE_POWDER);
			return false;
		}
		if (!GerenteDrink.getFactory().getCreamerDispenser()
				.contains(creame)) {
			MyDisplay.setWarnMessage(Messages.OUT_OF_CREAMER);
			return false;
		}
		GerenteDrink.getFactory().getSugarDispenser().contains(sugar);
		return true;

	}

	public void Mix() {
		GerenteDrink.getFactory().getCoffeePowderDispenser().release(coffeePonder);
		GerenteDrink.getFactory().getWaterDispenser().release( water);
		GerenteDrink.getFactory().getCreamerDispenser().release( creame);
		GerenteDrink.getFactory().getSugarDispenser().release(sugar);

	}

	public void drinkRelease() {
		GerenteDrink.getFactory().getCupDispenser().release(1);
		GerenteDrink.getFactory().getDrinkDispenser().release(100);

	}

	public void configureDrink(Recipe recipe) {
				
	}

}
