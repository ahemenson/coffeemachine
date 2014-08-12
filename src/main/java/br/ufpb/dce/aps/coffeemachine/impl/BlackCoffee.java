package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class BlackCoffee {
	
	private ComponentsFactory factory;
		
	public BlackCoffee(ComponentsFactory factory){
		this.factory = factory;
		
	}
		
	public boolean blackPlan(){  
						
		if (!factory.getCupDispenser().contains(1)) { 
			WarnMessage.setWarnMessage(Messages.OUT_OF_CUP);
			return false;
		}

		if (!factory.getWaterDispenser().contains(100)) { 
			WarnMessage.setWarnMessage(Messages.OUT_OF_WATER);
			return false;
		}

		if (!factory.getCoffeePowderDispenser().contains(15)) {
			WarnMessage.setWarnMessage(Messages.OUT_OF_COFFEE_POWDER); 
			return false;
		}
		
		return true;
	}
	
	public void blackMix(){
		factory.getCoffeePowderDispenser().release(15); 
		factory.getWaterDispenser().release(100);
	}
	
	public ComponentsFactory getFactory(){
		return factory;
	}
	
	public void drinkRelease(){
		factory.getCupDispenser().release(1);
		factory.getDrinkDispenser().release(100); 
	}
	
}
