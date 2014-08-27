package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;

public class BlackSugarCoffee implements IDrink {

	private int cup;
	private double water, coffeePonder, sugar;

	public BlackSugarCoffee() {
		cup = 1;
		water = 100;
		coffeePonder = 15;
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
		if (!GerenteDrink.getFactory().getSugarDispenser()
				.contains((int) sugar)) {
			MyDisplay.setWarnMessage(Messages.OUT_OF_SUGAR);
			return false;
		}
		return true;

	}

	public void Mix() {
		GerenteDrink.getFactory().getCoffeePowderDispenser().release((int) coffeePonder);
		GerenteDrink.getFactory().getWaterDispenser().release((int) water);
		GerenteDrink.getFactory().getSugarDispenser().release((int) sugar);

	}

	public void drinkRelease() {
		GerenteDrink.getFactory().getCupDispenser().release(cup);
		GerenteDrink.getFactory().getDrinkDispenser().release(100);

	}

}
