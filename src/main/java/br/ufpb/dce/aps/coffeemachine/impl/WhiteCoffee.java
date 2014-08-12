package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;

	public class WhiteCoffee extends BlackCoffee {
	
			
	public WhiteCoffee(ComponentsFactory factory) {
			super(factory);
			
	}

	public boolean whitePlan(){
		if (!blackPlan()) { //verifyBlackPlan(getCupDispenser(), getWaterDispenser(), getCoffeePowderDispenser()
			return false;
		}
		if(!getFactory().getCreamerDispenser().contains(2.0)){//inOrder.verify(creamerDispenser).contains(anyDouble());
			WarnMessage.setWarnMessage(Messages.OUT_OF_CREAMER);
			return false;
		}
		
		
		return true;
	}
	
	public void whiteMix(){
		blackMix();//blackMix(coffeePowderDispenser.release,waterDispenser).release )
		getFactory().getCreamerDispenser().release(2.0); //inOrder.verify(creamerDispenser).release
	} 
	
	

}
