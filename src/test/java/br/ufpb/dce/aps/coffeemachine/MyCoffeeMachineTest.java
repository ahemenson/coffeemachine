package br.ufpb.dce.aps.coffeemachine;

import br.ufpb.dce.aps.coffeemachine.impl.MycoffeeMachine;

public class MyCoffeeMachineTest extends CoffeeMachineTest {

	@Override
	protected CoffeeMachine createFacade(ComponentsFactory factory) {
		// TODO Auto-generated method stub
		return new MycoffeeMachine(factory);
	}
	
	
	

}
