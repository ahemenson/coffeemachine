package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public class GerenteDrink {

	private static ComponentsFactory factory;
	private IDrink drink;
	private boolean isValid;

	public GerenteDrink(ComponentsFactory factory) {
		this.factory = factory;
	}

	public void PlanDrink(IDrink drink) {
		this.drink = drink;
		validateDrink(drink.Plan());

	}

	public void MixDrink() {
		drink.Mix();
	}

	public void drinkRelease() {
		drink.drinkRelease();

	}

	public static ComponentsFactory getFactory() {
		return factory;
	}

	public void validateDrink(boolean planDrinkResult) {
		this.isValid = true;
		this.isValid = planDrinkResult;
	}

	public boolean getResultValidateDrink() {
		return this.isValid;
	}

}
