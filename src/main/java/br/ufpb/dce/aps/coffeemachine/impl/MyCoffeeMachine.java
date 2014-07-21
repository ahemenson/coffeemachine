package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.CashBox;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Display;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class MyCoffeeMachine implements CoffeeMachine {

	private ComponentsFactory factory;
	private int centavos, dolares; 
	private Display display;
	private CashBox cashBox;
	
	public MyCoffeeMachine(ComponentsFactory factory) {
		centavos = 0;
		dolares = 0;
		this.factory = factory;
		display= factory.getDisplay();
		display.info("Insert coins and select a drink!");
		cashBox = factory.getCashBox();
	}

	public void insertCoin(Coin dime) {

		if (dime != null) {
			centavos += dime.getValue() % 100;
			dolares += dime.getValue() / 100;
			factory.getDisplay().info(
					"Total: US$ " + dolares + "." + centavos + ""); //
		} else {
			throw new CoffeeMachineException("");
		}

	}

	public void cancel() {
		
		if ((centavos==0) && (dolares==0)){
			throw new CoffeeMachineException("");
		}
		else{
			factory.getDisplay().warn(Messages.CANCEL_MESSAGE);
			cashBox.release(Coin.halfDollar);
			factory.getDisplay().info(Messages.INSERT_COINS_MESSAGE);
		}
		
	

		
	}
}
