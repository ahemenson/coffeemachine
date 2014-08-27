package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Button;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class MyCoffeeMachine implements CoffeeMachine {

	private MyDisplay myDisplay;
	private MyCashBox myCashBox;
	private MyPayrollSystem myPayrollSystem;
	private DrinkPrice myDrinkPrice;
	private GerenteDrink gerenteDrink;

	public void insertCoin(Coin coin) {

		try {
			myCashBox.insertCoin(coin);
		} catch (UseBagdeBeforeCoinException e) {
			myDisplay.warn(Messages.CAN_NOT_INSERT_COINS);
			myCashBox.release(coin);
		}
	}

	public void cancel() {

		if (myCashBox.cancel()) {
			newSession();
		}
	}

	private void newSession() {
		myCashBox.clearCoins();
		myDisplay.info(Messages.INSERT_COINS);
	}

	public void select(Button drink) {

		myCashBox.setCoffeePrice(myDrinkPrice.getPriceDrink(drink));

		if (!MyPayrollSystem.isUseBadge()) {
			if (myCashBox.calculaTroco() < 0) {
				abortSession(Messages.NO_ENOUGHT_MONEY);
				return;
			}
		}

		switch (drink) {

		case BUTTON_1:
			gerenteDrink.PlanDrink(new BlackCoffee());
			break;
		case BUTTON_2:
			gerenteDrink.PlanDrink(new WhiteCoffee());
			break;
		case BUTTON_3:
			gerenteDrink.PlanDrink(new BlackSugarCoffee());
			break;
		case BUTTON_4:
			gerenteDrink.PlanDrink(new WhiteSugarCoffee());
			break;
		case BUTTON_5:
			gerenteDrink.PlanDrink(new Bouillon());
			break;

		}

		if (!gerenteDrink.getResultValidateDrink()) {
			abortSession(MyDisplay.getWarnMessage());
			return;
		}

		int[] changePlan = null;

		if (MyCashBox.isUseCoin()) {
			try {
				changePlan = myCashBox.planCoins();
			} catch (Exception e) {
				abortSession(Messages.NO_ENOUGHT_CHANGE);
				return;
			}
		}
		if (MyPayrollSystem.isUseBadge()) {
			if (!myPayrollSystem.debitar(myCashBox.getCoffeePrice(),
					myPayrollSystem.getBadgeCode())) {// inOrder.verify(payrollSystem).debit(drinkPrice,
														// badgeCode)
				abortSession(MyDisplay.getWarnMessage());// inOrder.verify(display).warn(Messages.UNKNOWN_BADGE_CODE);
				return;
			}
		}

		myDisplay.info(Messages.MIXING);

		gerenteDrink.MixDrink();

		myDisplay.info(Messages.RELEASING);

		gerenteDrink.drinkRelease();

		myDisplay.info(Messages.TAKE_DRINK);

		if (MyCashBox.isUseCoin()) {
			myCashBox.releaseCoins(changePlan);
		}

		newSession();

	}

	private void abortSession(String msg) {
		myDisplay.warn(msg);
		myCashBox.returnCoins();
		newSession();
	}

	public void setFactory(ComponentsFactory factory) {
		myDisplay = new MyDisplay(factory);
		myDisplay.info(Messages.INSERT_COINS);
		myCashBox = new MyCashBox(factory);
		myPayrollSystem = new MyPayrollSystem(factory);
		gerenteDrink = new GerenteDrink(factory);
		myDrinkPrice = new DrinkPrice(factory);
		myDrinkPrice.setDefautPriceDrink();
		showInfoButtons();
	}

	public void readBadge(int badgeCode) {

		myPayrollSystem.readBadgeCode(badgeCode);

		try {
			myPayrollSystem.UtilizeBadge();
			myDisplay.info(Messages.BADGE_READ);

		} catch (UseCoinBeforeBadgeException e) {
			myDisplay.warn(Messages.CAN_NOT_READ_BADGE);
		}
	}

	public void setPrice(Button drink, int priceCents) {
		myDrinkPrice.setPriceDrink(drink, priceCents);
		showInfoButtons();
	}

	private void showInfoButtons() {
		myDisplay.showInfoButtons();
	}

}
