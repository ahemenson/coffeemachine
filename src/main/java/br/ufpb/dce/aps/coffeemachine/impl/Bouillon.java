package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class Bouillon extends BlackCoffee{
	
	public Bouillon(ComponentsFactory factory){
		super(factory);
	}
	
	public boolean bouillonPlan(){
		if (!blackPlan()) { //verifyBlackPlan(getCupDispenser(), getWaterDispenser(), getCoffeePowderDispenser()
			return false;
		}		
		getFactory().getBouillonDispenser().contains(10);		
		return true;
	}	
	
	public void bouillonMix(){
		getFactory().getBouillonDispenser().release(10);
		blackMix(); 
	}
	
	public boolean blackPlan(){  
		
		if (!getFactory().getCupDispenser().contains(1)) { 
			WarnMessage.setWarnMessage(Messages.OUT_OF_CUP);
			return false;
		}

		if (!getFactory().getWaterDispenser().contains(100)) { 
			WarnMessage.setWarnMessage(Messages.OUT_OF_WATER);
			return false;
		}
		
		return true;
	}
	
	public void blackMix(){
		getFactory().getWaterDispenser().release(100);
	}

}
