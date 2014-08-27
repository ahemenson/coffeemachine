package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;

public class WhiteCoffee implements IDrink {

	private int cup;
	private double water, coffeePonder, creame;

	public WhiteCoffee() {
		cup = 1;
		water = 80;
		coffeePonder = 15;
		creame = 20;
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
		return true;

	}

	public void Mix() {
		GerenteDrink.getFactory().getCoffeePowderDispenser().release((int) coffeePonder);
		GerenteDrink.getFactory().getWaterDispenser().release((int) water);
		GerenteDrink.getFactory().getCreamerDispenser().release((int) creame); // inOrder.verify(creamerDispenser).release

	}

	public void drinkRelease() {
		GerenteDrink.getFactory().getCupDispenser().release(cup);
		GerenteDrink.getFactory().getDrinkDispenser().release(100);

	}

}