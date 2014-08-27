package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.ArrayList;

import br.ufpb.dce.aps.coffeemachine.Button;
import br.ufpb.dce.aps.coffeemachine.CashBox;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Display;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class MyCashBox {

	private CashBox cashBox;
	private Display myDisplay;
	private ArrayList<Coin> moedas;
	private int centavos, dolares;
	private int COFFEEPRICE;
	private static boolean IS_USE_COIN = false;

	public MyCashBox(ComponentsFactory factory) {
		cashBox = factory.getCashBox();
		myDisplay = factory.getDisplay();
		moedas = new ArrayList<Coin>();
		setCoffeePrice(35);
		MyCashBox.setUseCoin(false);
	}

	public void insertCoin(Coin coin) throws UseBagdeBeforeCoinException {

		if (MyPayrollSystem.isUseBadge()) {
			throw new UseBagdeBeforeCoinException("");

		}
		if (coin != null) {
			moedas.add(coin);
			centavos += coin.getValue() % 100;
			dolares += coin.getValue() / 100;
			myDisplay.info("Total: US$ " + dolares + "." + centavos + "");
			IS_USE_COIN = true;

		} else {
			throw new CoffeeMachineException("");
		}
	}

	public boolean cancel() {

		if ((centavos == 0) && (dolares == 0)) {
			throw new CoffeeMachineException("");
		}
		myDisplay.warn(Messages.CANCEL);
		if (moedas.size() > 0) {
			returnCoins();
			return true;
		}
		return false;
	}

	public int[] planCoins() {

		int troco = calculaTroco();
		int[] changePlan = new int[6];
		int i = 0;
		for (Coin r : Coin.reverse()) {
			if (r.getValue() <= troco && count(r) > 0) {
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

	public void releaseCoins(int[] changePlan) {

		for (int i = 0; i < changePlan.length; i++) {
			int count = changePlan[i];
			Coin coin = Coin.reverse()[i];

			for (int j = 1; j <= count; j++) {
				release(coin);
			}
		}
	}

	public void returnCoins() {

		for (Coin r : Coin.reverse()) {
			for (Coin aux : moedas) {
				if (aux == r) {
					cashBox.release(aux);
				}
			}
		}
	}

	public int calculaTroco() {

		int somatorioMoedas = 0;

		for (Coin aux : moedas) {
			somatorioMoedas += aux.getValue();
		}

		return somatorioMoedas - this.COFFEEPRICE;
	}

	public void clearCoins() {
		moedas.clear();
	}

	public void release(Coin coin) {
		cashBox.release(coin);
	}

	public int count(Coin coin) {
		return cashBox.count(coin);
	}

	public void setCoffeePrice(int coffeePrice) {
		this.COFFEEPRICE = coffeePrice;
	}

	public int getCoffeePrice() {
		return this.COFFEEPRICE;
	}

	public static boolean isUseCoin() {
		return IS_USE_COIN;
	}

	public static void setUseCoin(boolean isUseCoins) {
		IS_USE_COIN = isUseCoins;
	}

}
