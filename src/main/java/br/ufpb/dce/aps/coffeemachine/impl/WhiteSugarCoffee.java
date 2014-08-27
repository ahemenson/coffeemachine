package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;

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
				.contains((int) water)) {
			MyDisplay.setWarnMessage(Messages.OUT_OF_WATER);
			return false;
		}

		if (!GerenteDrink.getFactory().getCoffeePowderDispenser()
				.contains((int) coffeePonder)) {
			MyDisplay.setWarnMessage(Messages.OUT_OF_COFFEE_POWDER);
			return false;
		}
		if (!GerenteDrink.getFactory().getCreamerDispenser()
				.contains((int) creame)) {
			MyDisplay.setWarnMessage(Messages.OUT_OF_CREAMER);
			return false;
		}
		GerenteDrink.getFactory().getSugarDispenser().contains((int) sugar);
		return true;

	}

	public void Mix() {
		GerenteDrink.getFactory().getCoffeePowderDispenser().release((int) coffeePonder);
		GerenteDrink.getFactory().getWaterDispenser().release((int) water);
		GerenteDrink.getFactory().getCreamerDispenser().release((int) creame);
		GerenteDrink.getFactory().getSugarDispenser().release((int) sugar);

	}

	public void drinkRelease() {
		GerenteDrink.getFactory().getCupDispenser().release(1);
		GerenteDrink.getFactory().getDrinkDispenser().release(100);

	}

}
