package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.HashMap;

import br.ufpb.dce.aps.coffeemachine.Messages;
import br.ufpb.dce.aps.coffeemachine.Recipe;

public class SweetCream implements IDrink {
	
	public static HashMap<String, Double> itens = new HashMap<String, Double>();

	private int cup;

	public SweetCream() {
		cup = 1;
		itens.put(Recipe.WATER, 100.0);
		itens.put(Recipe.SUGAR, 15.0);
		itens.put(Recipe.CREAMER, 25.0);

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

		if (!GerenteDrink.getFactory().getSugarDispenser().contains(itens.get(Recipe.SUGAR))) {
			MyDisplay.setWarnMessage(Messages.OUT_OF_SUGAR);
			return false;
		}
		
		if (!GerenteDrink.getFactory().getCreamerDispenser().contains(itens.get(Recipe.CREAMER))) {
			MyDisplay.setWarnMessage(Messages.OUT_OF_CREAMER);
			return false;
		}
		return true;

	}

	public void Mix() {
		GerenteDrink.getFactory().getWaterDispenser().release((itens.get(Recipe.WATER)));
		GerenteDrink.getFactory().getCreamerDispenser().release((itens.get(Recipe.CREAMER)));
		GerenteDrink.getFactory().getSugarDispenser().release((itens.get(Recipe.SUGAR)));
	}

	public void drinkRelease() {
		GerenteDrink.getFactory().getCupDispenser().release(cup);
		GerenteDrink.getFactory().getDrinkDispenser().release();
	}

	public void configureDrink(Recipe recipe) {
		itens.put(Recipe.WATER, recipe.getIngredientQuantity(Recipe.WATER));
		itens.put(Recipe.CREAMER, recipe.getIngredientQuantity(Recipe.CREAMER));
		itens.put(Recipe.SUGAR, recipe.getIngredientQuantity(Recipe.SUGAR));
		
		
		
	}

}
