package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Recipe;

public interface IDrink {

	public boolean Plan();

	public void Mix();

	public void drinkRelease();
	
	public void configureDrink(Recipe recipe);

}
