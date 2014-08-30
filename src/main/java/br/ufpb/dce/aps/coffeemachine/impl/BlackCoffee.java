package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.HashMap;

import br.ufpb.dce.aps.coffeemachine.Button;
import br.ufpb.dce.aps.coffeemachine.Messages;
import br.ufpb.dce.aps.coffeemachine.Recipe;

public class BlackCoffee implements IDrink {
	
	public static HashMap<String, Double> itens = new HashMap<String, Double>();

	private int cup;
	//private double water, coffeePonder;

	public BlackCoffee() {
		cup = 1;
		itens.put(Recipe.WATER, 100.0);
		itens.put(Recipe.COFFEE_POWDER, 15.0);
//		water = 100;
//		coffeePonder = 15;
	}

	public boolean Plan() {

		if (!GerenteDrink.getFactory().getCupDispenser().contains(cup)) {
			MyDisplay.setWarnMessage(Messages.OUT_OF_CUP);
			return false;
		}

		if (!GerenteDrink.getFactory().getWaterDispenser().contains(itens.get(Recipe.WATER))) {
			MyDisplay.setWarnMessage(Messages.OUT_OF_WATER);
			return false;
		}

		if (!GerenteDrink.getFactory().getCoffeePowderDispenser().contains(itens.get(Recipe.COFFEE_POWDER))) {
			MyDisplay.setWarnMessage(Messages.OUT_OF_COFFEE_POWDER);
			return false;
		}
		return true;

	}

	public void Mix() {
		GerenteDrink.getFactory().getCoffeePowderDispenser().release(itens.get(Recipe.COFFEE_POWDER));
		GerenteDrink.getFactory().getWaterDispenser().release((itens.get(Recipe.WATER)));
	}

	public void drinkRelease() {
		GerenteDrink.getFactory().getCupDispenser().release(cup);
		GerenteDrink.getFactory().getDrinkDispenser().release(100);
	}

	public void configureDrink(Recipe recipe) {
		itens.put(Recipe.WATER, recipe.getIngredientQuantity(Recipe.WATER));
		
		
	}

}
