package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public class MyCoffeeMachine implements CoffeeMachine{

	private ComponentsFactory factory;
	private int centavos , dolares;

	public MyCoffeeMachine(ComponentsFactory factory) {
		centavos = 0;
		dolares = 0;
		this.factory = factory;
		factory.getDisplay().info("Insert coins and select a drink!");
	}

	public void insertCoin(Coin dime) {
		centavos += dime.getValue() % 100;
		dolares += dime.getValue() / 100;
		factory.getDisplay().info("Total: US$ "+dolares+"."+centavos+"");  //
			
	}


}
