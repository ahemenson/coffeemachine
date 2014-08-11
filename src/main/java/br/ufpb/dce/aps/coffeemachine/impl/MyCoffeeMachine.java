package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.ArrayList;

import org.mockito.internal.stubbing.answers.ThrowsException;

import br.ufpb.dce.aps.coffeemachine.CashBox;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Display;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.Messages;

/**
 * Refatoramento - Versão 2.0
 * 
 * @author Eminho
 *
 */
public class MyCoffeeMachine implements CoffeeMachine {

	private ComponentsFactory factory;

	private BlackCoffee coffee;
	private MyDisplay myDisplay;
	private MyCashBox myCashBox;
	
	public MyCoffeeMachine(ComponentsFactory factory) {
		
		this.factory = factory;
		myDisplay = new MyDisplay(factory);
		myDisplay.info(Messages.INSERT_COINS);
		myCashBox = new MyCashBox(factory);
		
	}

	public void insertCoin(Coin coin) {
			
		myCashBox.insertCoin(coin);
		
	}

	public void cancel() {
		
		if(myCashBox.cancel()){
			newSession();
		}
	}
	
		

	private boolean planCoins() {
		
		int troco = myCashBox.calculaTroco();
		for (Coin r : Coin.reverse()) {
			if (r.getValue() <= troco && myCashBox.count(r) > 0) {
				troco -= r.getValue();
			}
		}

		return troco == 0;
	}
	
	private void releaseCoins() {
		
		int troco = myCashBox.calculaTroco();
		for (Coin r : Coin.reverse()) {
			if (r.getValue() <= troco) {
				myCashBox.release(r);
				troco -= r.getValue();
			}
		}
	}
	
	
	
	private void newSession() {
		myCashBox.clearCoins();
		myDisplay.info(Messages.INSERT_COINS);
	}
	
	
	public void select(Drink drink) {

		boolean isvalido = true;

		if(myCashBox.calculaTroco()< 0){
			abortSession(Messages.NO_ENOUGHT_MONEY);//inOrder.verify(display).warn(Messages.NO_ENOUGHT_MONEY);
			return;
		}
		
		// Chamada de métodos Plan (InOrder inOrder) 
		
		switch (drink) {
		
			case BLACK:
				coffee = new BlackCoffee(factory);
				isvalido = coffee.blackPlan();
				break;					
			case WHITE:
				coffee = new WhiteCoffee(factory);
				isvalido = ((WhiteCoffee) coffee).whitePlan();
				break;
			case WHITE_SUGAR:
				coffee = new WhiteSugarCoffee(factory);
				isvalido = ((WhiteSugarCoffee) coffee).whiteSugarPlan();
				break;
			case BLACK_SUGAR:
				coffee = new BlackSugarCoffee(factory);
				isvalido = ((BlackSugarCoffee) coffee).blackSugarPlan();
				break;
			
		}
		
		if(!isvalido){
			abortSession(WarnMessage.getWarnMessage());
			return;
		}
		
		if(!planCoins()){
			abortSession(Messages.NO_ENOUGHT_CHANGE); 
			return;
		}
		
				
		myDisplay.info(Messages.MIXING);
		
		// Chamada de métodos Mix (InOrder inOrder) 
		
		switch (drink) {
		
			case BLACK:
				coffee.blackMix();
				break;
	
			case WHITE:
				((WhiteCoffee) coffee).whiteMix();
				break;
				
			case WHITE_SUGAR:
				((WhiteSugarCoffee) coffee).whiteSugarMix();
				break;
				
			case BLACK_SUGAR:
				((BlackSugarCoffee) coffee).blackSugarMix();					
				break;
		}
		
	
		// Chamadas de DrinkRelease(InOrder inOrder)

		myDisplay.info(Messages.RELEASING);
		
		coffee.drinkRelease();
				
		myDisplay.info(Messages.TAKE_DRINK);

		releaseCoins(); // entrega troco

		newSession(); // nova sessão

	}
	
	private void abortSession(String msg){
		myDisplay.warn(msg); 
		myCashBox.returnCoins(); 
		newSession();
	}
	
}
