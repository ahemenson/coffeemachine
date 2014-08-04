package br.ufpb.dce.aps.coffeemachine.impl;

import static org.mockito.Matchers.anyDouble;

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
	private int troco;
	private boolean retornaTroco = false;
	private final int COFFEEPRICE = 35;
	private int valorTroco;

	public MyCoffeeMachine(ComponentsFactory factory) {
		centavos = 0;
		dolares = 0;
		this.factory = factory;
		display = factory.getDisplay();
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
			this.troco = (centavos + dolares) - 35;
			if (troco > 0) {
				retornaTroco = true;
			}

		} else {
			throw new CoffeeMachineException("");
		}

	}

	public void cancel() {

		if ((centavos == 0) && (dolares == 0)) {
			throw new CoffeeMachineException("");
		}
		factory.getDisplay().warn(Messages.CANCEL);
		if (moedas.size() > 0) {
			returnCoins();
		}

	}

	void returnCoins() {

		Coin[] inverso = Coin.reverse();

		for (Coin r : inverso) {
			for (Coin aux : moedas) {
				if (aux == r) {
					cashBox.release(aux);
				}
			}
		}
		newSession();
	}

	void planCoins(int troco) {

		Coin[] inverso = Coin.reverse();
		for (Coin r : inverso) {
			if (r.getValue() <= troco) {
				cashBox.count(r);
				troco -= r.getValue();
			}
		}

	}

	void releaseCoins(int troco) {

		Coin[] inverso = Coin.reverse();
		for (Coin r : inverso) {
			if (r.getValue() <= troco) {
				cashBox.release(r);
				troco -= r.getValue();
			}
		}

	}

	public int calculaTroco() {

		int somatorioMoedas = 0;


		for (Coin aux : moedas) {
			somatorioMoedas += aux.getValue();
		}

		return  somatorioMoedas - this.COFFEEPRICE;

		
	}

	void clearCoins() {
		// Limpando o total de moedas inseridas
		moedas.clear();
	}

	void newSession() {
		clearCoins();
		display.info("Insert coins and select a drink!");
	}

	public void select(Drink drink) {
		// método verifyBlackPlan(inOrder);

		if (!factory.getCupDispenser().contains(1)) { // inOrder.verify(cupDispenser).contains(1);
			factory.getDisplay().warn(Messages.OUT_OF_CUP); // verifyOutOfIngredient(inOrder,
															// Messages.OUT_OF_CUP,
															// Coin.quarter,
															// Coin.dime);
			returnCoins();
			return;
		}

		if (!factory.getWaterDispenser().contains(0.5)) { // inOrder.verify(waterDispenser).contains(anyDouble());
			factory.getDisplay().warn(Messages.OUT_OF_WATER); // verifyOutOfIngredient(inOrder,
																// Messages.OUT_OF_WATER,
																// Coin.quarter,
																// Coin.dime);
			returnCoins();
			return;
		}

		if (!factory.getCoffeePowderDispenser().contains(0.8)) { // inOrder.verify(coffeePowderDispenser).contains(anyDouble());
			factory.getDisplay().warn(Messages.OUT_OF_COFFEE_POWDER); // verifyOutOfIngredient(inOrder,
																		// Messages.OUT_OF_COFFEE_POWDER,
																		// Coin.quarter,
																		// Coin.dime);
			returnCoins();
			return;
		}

		if (drink == this.drink.WHITE) {
			factory.getCreamerDispenser().contains(2.0); // inOrder.verify(creamerDispenser).contains(anyDouble());

		}

		if (drink == this.drink.WHITE_SUGAR) {
			factory.getCreamerDispenser().contains(2.0); // inOrder.verify(creamerDispenser).contains(anyDouble());
			factory.getSugarDispenser().contains(5.0);

		}

		if (drink == this.drink.BLACK_SUGAR) {
			if (!factory.getSugarDispenser().contains(5.0)) { // inOrder.verify(sugarDispenser).contains(anyDouble());
				factory.getDisplay().warn(Messages.OUT_OF_SUGAR); // inOrder.verify(display).warn(message);
				returnCoins(); // verifyReleaseCoins(inOrder, coins);
				return; // verifyNewSession(inOrder);

			}

		}

		
		planCoins(calculaTroco());
		

		// método verifyBlackSugarMix(InOrder inOrder) {

		display.info("Mixing ingredients."); // inOrder.verify(display).info(Messages.MIXING);

		factory.getCoffeePowderDispenser().release(0.6); // inOrder.verify(coffeePowderDispenser).release(anyDouble());

		factory.getWaterDispenser().release(0.9); // inOrder.verify(waterDispenser).release(anyDouble());

		if (drink == this.drink.WHITE) {
			factory.getCreamerDispenser().release(2.0); // inOrder.verify(creamerDispenser).release(anyDouble());
		}

		if (drink == this.drink.WHITE_SUGAR) {
			factory.getCreamerDispenser().release(2.0); // inOrder.verify(creamerDispenser).contains(anyDouble());
			factory.getSugarDispenser().release(5.0);
		}

		if (drink == this.drink.BLACK_SUGAR) {

			factory.getSugarDispenser().release(5.0); // inOrder.verify(sugarDispenser).release(anyDouble());

		}

		// verifyDrinkRelease(InOrder inOrder)

		display.info("Releasing drink."); // inOrder.verify(display).info(Messages.RELEASING);

		factory.getCupDispenser().release(1); // inOrder.verify(cupDispenser).release(1);

		factory.getDrinkDispenser().release(0.3); // inOrder.verify(drinkDispenser).release(anyDouble());

		display.info("Please, take your drink."); // inOrder.verify(display).info(Messages.TAKE_DRINK);

	
		releaseCoins(calculaTroco());
		
		newSession(); // nova sessão

	}

}
