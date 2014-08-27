package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Button;
import br.ufpb.dce.aps.coffeemachine.ButtonDisplay;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Display;

public class MyDisplay implements Display {

	private Display myDisplay;
	private ButtonDisplay buttonDisplay;

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

	public void showInfoButtons() {
		buttonDisplay.show(
				"Black: $" + "0." + DrinkPrice.drinkPrice.get(Button.BUTTON_1)
						+ "",
				"White: $0." + DrinkPrice.drinkPrice.get(Button.BUTTON_2),
				"Black with sugar: $0."
						+ DrinkPrice.drinkPrice.get(Button.BUTTON_3),
				"White with sugar: $0."
						+ DrinkPrice.drinkPrice.get(Button.BUTTON_4),
				"Bouillon: $0." + DrinkPrice.drinkPrice.get(Button.BUTTON_5),
				null, null);
	}

}
