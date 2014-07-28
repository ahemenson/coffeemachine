package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.ArrayList;

import br.ufpb.dce.aps.coffeemachine.CashBox;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Display;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class MyCoffeeMachine implements CoffeeMachine {

	private ComponentsFactory factory;
	private int centavos, dolares; 
	private Display display;
	private CashBox cashBox;
	private Dispenser dispenser;
	private ArrayList<Coin> moedas;
	private Drink drink;
	
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
			factory.getDisplay().warn("Cancelling drink. Please, get your coins.");
			for(Coin r : inverso){
				for(Coin aux : moedas){
					if(aux == r){
						cashBox.release(aux);
					}
				}
			}
			factory.getDisplay().info("Insert coins and select a drink!");
		}
		
	

		
	}

	public void select(Drink drink) {
		//verifyBlackPlan(inOrder);

		factory.getCupDispenser().contains(1);  //inOrder.verify(cupDispenser).contains(1);

		factory.getWaterDispenser().contains(0.5);  // inOrder.verify(waterDispenser).contains(anyDouble());

		factory.getCoffeePowderDispenser().contains (0.8);  // inOrder.verify(coffeePowderDispenser).contains(anyDouble());

 
		if(drink == this.drink.BLACK_SUGAR){

		//inOrder.verify(sugarDispenser).contains(anyDouble());

		factory.getSugarDispenser().contains(5.0);

		}

		 		 

		//verifyBlackSugarMix(InOrder inOrder) {

		display.info("Mixing ingredients."); //inOrder.verify(display).info(Messages.MIXING);

		factory.getCoffeePowderDispenser().release (0.6); // inOrder.verify(coffeePowderDispenser).release(anyDouble());

		factory.getWaterDispenser().release (0.9); // inOrder.verify(waterDispenser).release(anyDouble());

		 
		if(drink == this.drink.BLACK_SUGAR){

		//inOrder.verify(sugarDispenser).release(anyDouble());

		factory.getSugarDispenser().release(5.0);

		}

		 
		//verifyDrinkRelease(InOrder inOrder)

		display.info("Releasing drink."); //inOrder.verify(display).info(Messages.RELEASING);

		factory.getCupDispenser().release (1);  // inOrder.verify(cupDispenser).release(1);

		factory.getDrinkDispenser().release (0.3); //inOrder.verify(drinkDispenser).release(anyDouble());

		display.info("Please, take your drink.");  // inOrder.verify(display).info(Messages.TAKE_DRINK);

		display.info("Insert coins and select a drink!");
		
				
	}
}
