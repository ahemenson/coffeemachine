package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.HashMap;

import br.ufpb.dce.aps.coffeemachine.Button;
import br.ufpb.dce.aps.coffeemachine.ButtonDisplay;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Display;
import br.ufpb.dce.aps.coffeemachine.Recipe;

public class MyDisplay implements Display {
	
	
	public static HashMap<Button, Recipe> drink = new HashMap<Button, Recipe>();

	private Display myDisplay;
	private ButtonDisplay buttonDisplay;
	private Recipe recipe;
	private Button button;

	private static String warnMessage;

	public MyDisplay(ComponentsFactory factory) {
		myDisplay = factory.getDisplay();
		buttonDisplay = factory.getButtonDisplay();

	}

	public void info(String msg) {
		myDisplay.info(msg);

	}

	public void warn(String msg) {
		myDisplay.warn(msg);

	}

	public static void setWarnMessage(String msg) {
		warnMessage = msg;
	}

	public static String getWarnMessage() {
		return warnMessage;
	}
	
	void setRecipe(Button button, Recipe recipe){
		this.button = button;
		this.recipe = recipe;
	}

	public void showInfoButtons() {
		
		if(this.button == Button.BUTTON_6){
			buttonDisplay.show(
					"Black: $" + "0." + DrinkPrice.drinkPrice.get(Button.BUTTON_1)
							+ "",
					"White: $0." + DrinkPrice.drinkPrice.get(Button.BUTTON_2),
					"Black with sugar: $0."
							+ DrinkPrice.drinkPrice.get(Button.BUTTON_3),
					"White with sugar: $0."
							+ DrinkPrice.drinkPrice.get(Button.BUTTON_4),
					"Bouillon: $0." + DrinkPrice.drinkPrice.get(Button.BUTTON_5),
					
					"Sweet cream: $0." + DrinkPrice.drinkPrice.get(Button.BUTTON_6),
					null);
			return;
		}
		
		buttonDisplay.show(
				"Black: $" + "0." + DrinkPrice.drinkPrice.get(Button.BUTTON_1)
						+ "",
				"White: $0." + DrinkPrice.drinkPrice.get(Button.BUTTON_2),
				"Black with sugar: $0."
						+ DrinkPrice.drinkPrice.get(Button.BUTTON_3),
				"White with sugar: $0."
						+ DrinkPrice.drinkPrice.get(Button.BUTTON_4),
				"Bouillon: $0." + DrinkPrice.drinkPrice.get(Button.BUTTON_5),
				
				null,
				null);
		

		
		
	}
		
				
	
	public void showInfoButton2s(Button button,Recipe recipe) {
		
		
		
		
		//recipe.getName() + ": "+"$0."+ recipe.getPriceCents()
		
		//buttonDisplay.show(recipe.getName(), recipe.getPriceCents();
	}
	
	

}
