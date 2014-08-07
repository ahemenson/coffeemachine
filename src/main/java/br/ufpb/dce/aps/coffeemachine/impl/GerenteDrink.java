package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.HashMap;

import br.ufpb.dce.aps.coffeemachine.Button;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Recipe;

public class GerenteDrink {

	private static ComponentsFactory factory;
	private IDrink drink;
	private boolean isValid;
	private Recipe recipe;
	private Button button;
	private Dispenser dispenser;

	public GerenteDrink(ComponentsFactory factory) {
		this.factory = factory;
	}

	public void planDrink() {
		validateDrink(drink.Plan());

	}
	
	public void selectDrink(IDrink drink){
		this.drink = drink;
		changeDrinkRecipe(this.button);
		
	}

	public void mixDrink() {
		drink.Mix();
	}

	public void drinkRelease() {
		drink.drinkRelease();

	}

	public static ComponentsFactory getFactory() {
		return factory;
	}

	public void validateDrink(boolean planDrinkResult) {
		this.isValid = planDrinkResult;
	}

	public boolean getResultValidateDrink() {
		return this.isValid;
	}
	
	void changeDrinkRecipe(Button b){
		if(b != null){
					drink.configureDrink(recipe);
					
		}
			
	}

	public void setconfigureDrinkRecipe(Button b, Recipe recipe) {
		this.button = b;
		this.recipe = recipe;
		
		
	}

	public void addNewDispenser(HashMap<String, Dispenser> listDispenser) {
		drink.configureNewDispenserDrink(listDispenser);
		
	}

}
