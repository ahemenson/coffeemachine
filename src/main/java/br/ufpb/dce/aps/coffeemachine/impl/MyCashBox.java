package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.ArrayList;

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
	private final int COFFEEPRICE = 35;
	
	public MyCashBox(ComponentsFactory factory){
		cashBox =  factory.getCashBox();
		myDisplay = factory.getDisplay();
		moedas = new ArrayList<Coin>();
	}
	
	
	public void insertCoin(Coin coin) {
		
		if (coin != null) {
			moedas.add(coin);
			centavos += coin.getValue() % 100;
			dolares += coin.getValue() / 100;
			myDisplay.info("Total: US$ " + dolares + "." + centavos + "");
	
		} else {
			throw new CoffeeMachineException("");
		}
	}

	public boolean cancel(){
		
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

		return  somatorioMoedas - this.COFFEEPRICE;
	}
	
	public void clearCoins(){
		moedas.clear();
	}
	
	public void release(Coin coin){
		cashBox.release(coin);
	}


	public int count(Coin coin) {
		return cashBox.count(coin);
	}
	
		

}
