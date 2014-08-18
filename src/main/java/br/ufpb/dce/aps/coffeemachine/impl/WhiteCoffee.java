package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;

	public class WhiteCoffee extends BlackCoffee {
	
			
	public WhiteCoffee(ComponentsFactory factory) {
			super(factory);
			
	}
	
	public boolean blackPlan(){  
		
		if (!getFactory().getCupDispenser().contains(1)) { 
			WarnMessage.setWarnMessage(Messages.OUT_OF_CUP);
			return false;
		}

		if (!getFactory().getWaterDispenser().contains(80)) { 
			WarnMessage.setWarnMessage(Messages.OUT_OF_WATER);
			return false;
		}

		if (!getFactory().getCoffeePowderDispenser().contains(15)) {
			WarnMessage.setWarnMessage(Messages.OUT_OF_COFFEE_POWDER); 
			return false;
		}
		
		return true;
	}

	public boolean whitePlan(){
		if (!blackPlan()) { //verifyBlackPlan(getCupDispenser(), getWaterDispenser(), getCoffeePowderDispenser()
			return false;
		}
		if(!getFactory().getCreamerDispenser().contains(20)){//inOrder.verify(creamerDispenser).contains(anyDouble());
			WarnMessage.setWarnMessage(Messages.OUT_OF_CREAMER);
			return false;
		}
		
		
		return true;
	}
	
	public void blackMix(){
		getFactory().getCoffeePowderDispenser().release(15); 
		getFactory().getWaterDispenser().release(80);
	}
	
	public void whiteMix(){
		blackMix();//blackMix(coffeePowderDispenser.release,waterDispenser).release )
		getFactory().getCreamerDispenser().release(20); //inOrder.verify(creamerDispenser).release
	} 
	
	

}
