package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.HashMap;

import br.ufpb.dce.aps.coffeemachine.Button;
import br.ufpb.dce.aps.coffeemachine.ButtonDisplay;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Recipe;

public class DrinkPrice {

	public static HashMap<Button, Integer> drinkPrice = new HashMap<Button, Integer>();
	

	private ButtonDisplay buttonDisplay;

	DrinkPrice(ComponentsFactory factory) {
		buttonDisplay = factory.getButtonDisplay();
	}
	
	public void setPriceDrink(Button button, Recipe recipe) {
		
		if(recipe != null){
			
				drinkPrice.put(button, recipe.getPriceCents());
				
		}
		else{
				throw new CoffeeMachineException("");
			}
			
				
		

	}

	public void setPriceDrink(Button drink, int priceCents) {
		drinkPrice.put(drink, priceCents);

	}

	public void setDefautPriceDrink() {
		drinkPrice.put(Button.BUTTON_1, 35);
		drinkPrice.put(Button.BUTTON_2, 35);
		drinkPrice.put(Button.BUTTON_3, 35);
		drinkPrice.put(Button.BUTTON_4, 35);
		drinkPrice.put(Button.BUTTON_5, 25);
		drinkPrice.put(null, 0);
		drinkPrice.put(null, 0);
	}

	public int getPriceDrink(Button drink) {
		return drinkPrice.get(drink);
	}

	public ButtonDisplay getButtonDisplay() {
		return buttonDisplay;
	}

	public void setButtonDisplay(ButtonDisplay buttonDisplay) {
		this.buttonDisplay = buttonDisplay;
	}
	
	

}