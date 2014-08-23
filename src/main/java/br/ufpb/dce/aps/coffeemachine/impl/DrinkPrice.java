package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.ArrayList;

import br.ufpb.dce.aps.coffeemachine.Button;
import br.ufpb.dce.aps.coffeemachine.ButtonDisplay;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;
import br.ufpb.dce.aps.coffeemachine.PayrollSystem;

public class DrinkPrice {
	
	private int BLACK,WHITE,BLACKSUGAR,WHITESUGAR,BOUILLON;
	
	private ButtonDisplay buttonDisplay;
	
	DrinkPrice(ComponentsFactory factory){
		buttonDisplay = factory.getButtonDisplay();
		BLACK = 35;
		WHITE = 35;
		BLACKSUGAR = 35;
		WHITESUGAR = 35;
		BOUILLON = 25;
	}
		
	public  void setPriceDrink(Button drink, int priceCents) {
		
			switch (drink) {

			case BUTTON_1:			
				BLACK =  priceCents	;
				break;
			case BUTTON_2:
				WHITE = priceCents	;
				break;		
			case BUTTON_3:
				BLACKSUGAR = priceCents	;
				break;			
			case BUTTON_4:
				WHITESUGAR = priceCents	;
				break;
			case BUTTON_5:
				BOUILLON = priceCents	;
				break;
		
		}
	}
	
	void show(){
		buttonDisplay.show("Black: $"+"0."+BLACK+"", "White: $0.35", "Black with sugar: $0.35", "White with sugar: $0.35", "Bouillon: $0.25", null, null);
	}

	

	

}
