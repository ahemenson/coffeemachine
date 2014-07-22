package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.ArrayList;

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
	private ArrayList<Coin> moedas;
	
	public MyCoffeeMachine(ComponentsFactory factory) {
		centavos = 0;
		dolares = 0;
		this.factory = factory;
		display= factory.getDisplay();
		display.info("Insert coins and select a drink!");
		cashBox = factory.getCashBox();
		moedas = new ArrayList<Coin>();
	}

	public void insertCoin(Coin coin) {

		if (coin != null) {
			moedas.add(coin);
			centavos += coin.getValue() % 100;
			dolares += coin.getValue() / 100;
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
		
		if(moedas.size()>0){
			Coin[] inverso = Coin.reverse();
			factory.getDisplay().warn(Messages.CANCEL_MESSAGE);
			for(Coin r : inverso){
				for(Coin aux : moedas){
					if(aux == r){
						cashBox.release(aux);
					}
				}
			}
			factory.getDisplay().info(Messages.INSERT_COINS_MESSAGE);
		}
		
	

		
	}
}
