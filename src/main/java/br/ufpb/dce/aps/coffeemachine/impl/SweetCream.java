package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.HashMap;

import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Messages;
import br.ufpb.dce.aps.coffeemachine.Recipe;

public class SweetCream implements IDrink {
	
	public static HashMap<String, Double> itens = new HashMap<String, Double>();

	private int cup;
	
//	inOrder.verify(cupDispenser).contains(1);
//	inOrder.verify(milkDispenser).contains(120.0);
//	inOrder.verify(chocolateDispenser).contains(20.0);
//	inOrder.verify(sugarDispenser).contains(5.0);
//	inOrder.verify(display).info(Messages.MIXING);
//	inOrder.verify(milkDispenser).release(120.0);
//	inOrder.verify(chocolateDispenser).release(20.0);
//	inOrder.verify(sugarDispenser).release(5.0);

	public SweetCream() {
		cup = 1;
		itens.put(Recipe.MILK, 120.0);
		itens.put(Recipe.CHOCOLATE, 20.0);
		itens.put(Recipe.SUGAR, 5.0);
		
		

	}

	public boolean Plan() {

		if (!GerenteDrink.getFactory().getCupDispenser().contains(cup)) {
			MyDisplay.setWarnMessage(Messages.OUT_OF_CUP);
			return false;
		}

		if (!GerenteDrink.getFactory().getWaterDispenser().contains(itens.get(Recipe.WATER))) {
			MyDisplay.setWarnMessage(Messages.OUT_OF_WATER);
			return false;
		}

		if (!GerenteDrink.getFactory().getSugarDispenser().contains(itens.get(Recipe.SUGAR))) {
			MyDisplay.setWarnMessage(Messages.OUT_OF_SUGAR);
			return false;
		}
		
		if (!GerenteDrink.getFactory().getCreamerDispenser().contains(itens.get(Recipe.CREAMER))) {
			MyDisplay.setWarnMessage(Messages.OUT_OF_CREAMER);
			return false;
		}
		//GerenteDrink.getFactory().
		return true;

	}

	public void Mix() {
		GerenteDrink.getFactory().getWaterDispenser().release((itens.get(Recipe.WATER)));
		GerenteDrink.getFactory().getCreamerDispenser().release((itens.get(Recipe.CREAMER)));
		GerenteDrink.getFactory().getSugarDispenser().release((itens.get(Recipe.SUGAR)));
	}

	public void drinkRelease() {
		GerenteDrink.getFactory().getCupDispenser().release(cup);
		GerenteDrink.getFactory().getDrinkDispenser().release();
	}

	public void configureDrink(Recipe recipe) {
		itens.put(Recipe.WATER, recipe.getIngredientQuantity(Recipe.WATER));
		itens.put(Recipe.CREAMER, recipe.getIngredientQuantity(Recipe.CREAMER));
		itens.put(Recipe.SUGAR, recipe.getIngredientQuantity(Recipe.SUGAR));
		itens.put(Recipe.MILK, recipe.getIngredientQuantity(Recipe.MILK));
		itens.put(Recipe.CHOCOLATE, recipe.getIngredientQuantity(Recipe.CHOCOLATE));
		
			
		
	}

	public void configureNewDispenserDrink(HashMap<String, Dispenser> listDispenser){
		
//		
//		for (String chave : listDispenser.keySet())  
//        {  
//           
//                System.out.println(listDispenser.get(chave));  
//         
//				
//        }
	}

}
