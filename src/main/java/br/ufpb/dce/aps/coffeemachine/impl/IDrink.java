package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.HashMap;

import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Recipe;

public interface IDrink {

	public boolean Plan();

	public void Mix();

	public void drinkRelease();
	
	public void configureDrink(Recipe recipe);

	public void configureNewDispenserDrink(HashMap<String, Dispenser> listDispenser);

}
