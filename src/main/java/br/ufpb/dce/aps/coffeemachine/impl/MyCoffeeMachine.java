package br.ufpb.dce.aps.coffeemachine.impl;

import static org.mockito.Mockito.verify;
import br.ufpb.dce.aps.coffeemachine.Button;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
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
	private MyPayrollSystem myPayrollSystem;

	private int badgeCode;

	public void insertCoin(Coin coin) {
				
		
		if(!myCashBox.isUseCard()){
			myCashBox.insertCoin(coin);
			myCashBox.setUseCoin(true);
			myPayrollSystem.setUseBadge(false);
		}
		else{
			myDisplay.warn(Messages.CAN_NOT_INSERT_COINS);
			myCashBox.release(coin);
		}

		
	}

	public void cancel() {

		if (myCashBox.cancel()) {
			newSession();
		}
	}

	int[] planCoins() {

		int troco = myCashBox.calculaTroco();
		int[] changePlan = new int[6];
		int i = 0;
		for (Coin r : Coin.reverse()) {
			if (r.getValue() <= troco && myCashBox.count(r) > 0) {
				while (r.getValue() <= troco) {
					troco -= r.getValue();
					changePlan[i]++;
				}
			}
			i++;
		}
		if (troco != 0) {
			throw new CoffeeMachineException("");
		}

		return changePlan;
	}

	private void releaseCoins(int[] changePlan) {

		for (int i = 0; i < changePlan.length; i++) {
			int count = changePlan[i];
			Coin coin = Coin.reverse()[i];

			for (int j = 1; j <= count; j++) {
				myCashBox.release(coin);
			}
		}
	}

	private void newSession() {
		
		//verify(buttonDisplay).show("Black: $0.35", "White: $0.35",
						//"Black with sugar: $0.35", "White with sugar: $0.35",
							//"Bouillon: $0.25", null, null);
		myCashBox.clearCoins();
		myDisplay.info(Messages.INSERT_COINS);
	}

	public void select(Button drink) {
		
		if(drink == drink.BUTTON_5){
			myCashBox.setCoffeePrice(25);
		}

		boolean isvalido = true;
			
		if(!MyPayrollSystem.isUseBadge()){
			if (myCashBox.calculaTroco() < 0) {
				abortSession(Messages.NO_ENOUGHT_MONEY);// inOrder.verify(display).warn(Messages.NO_ENOUGHT_MONEY);
				return;
			}
		}
		

		// Chamada de métodos Plan (InOrder inOrder)

		switch (drink) {

		case BUTTON_1:			
			isvalido = coffee.blackPlan();
			
			break;
		case BUTTON_2:
			coffee = new WhiteCoffee(factory);
			isvalido = ((WhiteCoffee) coffee).whitePlan();
			break;		
		case BUTTON_3:
			coffee = new BlackSugarCoffee(factory);
			isvalido = ((BlackSugarCoffee) coffee).blackSugarPlan();
			break;			
		case BUTTON_4:
			coffee = new WhiteSugarCoffee(factory);
			isvalido = ((WhiteSugarCoffee) coffee).whiteSugarPlan();
			break;
		case BUTTON_5:
			coffee = new  Bouillon(factory);
			//myCashBox.setCoffeePrice(25);
			isvalido = ((Bouillon) coffee).bouillonPlan();
			break;

		}

		if (!isvalido) {
			abortSession(WarnMessage.getWarnMessage());
			return;
		}
		int[] changePlan = null;
		
		if(MyPayrollSystem.isUseBadge()){
			if(!myPayrollSystem.debitar(myCashBox.getCoffeePrice(), badgeCode)){//inOrder.verify(payrollSystem).debit(drinkPrice, badgeCode)
				myDisplay.warn(Messages.UNKNOWN_BADGE_CODE);//inOrder.verify(display).warn(Messages.UNKNOWN_BADGE_CODE);
				newSession();
				return;
			}
		}
		
		else{
							
			try {
				changePlan = planCoins();
			} catch (Exception e) {
				abortSession(Messages.NO_ENOUGHT_CHANGE);
				return;
			}
			
		}
							
		myDisplay.info(Messages.MIXING);

		// Chamada de métodos Mix (InOrder inOrder)

		switch (drink) {

		case BUTTON_1:
			coffee.blackMix();
			break;

		case BUTTON_2:
			((WhiteCoffee) coffee).whiteMix();
			break;

		case BUTTON_3:
			((BlackSugarCoffee) coffee).blackSugarMix();
			break;
			
		case BUTTON_4:
			((WhiteSugarCoffee) coffee).whiteSugarMix();
			break;
			
		case BUTTON_5:
			((Bouillon) coffee).bouillonMix();
			break;
		}
		

		// Chamadas de DrinkRelease(InOrder inOrder)

		myDisplay.info(Messages.RELEASING);

		coffee.drinkRelease();

		myDisplay.info(Messages.TAKE_DRINK);
		
		if(!MyPayrollSystem.isUseBadge()){
			releaseCoins(changePlan); // entrega troco
		}
		
		newSession(); // nova sessão

	}

	private void abortSession(String msg) {
		myDisplay.warn(msg);
		myCashBox.returnCoins();
		newSession();
	}

	public void setFactory(ComponentsFactory factory) {
		this.factory = factory;
		myDisplay = new MyDisplay(factory);
		myDisplay.info(Messages.INSERT_COINS);
		myCashBox = new MyCashBox(factory);
		coffee = new BlackCoffee(factory);
		myCashBox.setCoffeePrice(35);
		myPayrollSystem = new MyPayrollSystem(factory);
		factory.getButtonDisplay().show("Black: $0.35", "White: $0.35", "Black with sugar: $0.35", "White with sugar: $0.35", "Bouillon: $0.25", null, null);	
		

	}

	public void readBadge(int badgeCode) {
		this.badgeCode = badgeCode;
		if(!myCashBox.isUseCoin()){
			myDisplay.info(Messages.BADGE_READ);
			myCashBox.setUseCard(true);
			myPayrollSystem.setUseBadge(true);
		}
		else{
			myDisplay.warn(Messages.CAN_NOT_READ_BADGE);
		}
	
			
	}

	

}
