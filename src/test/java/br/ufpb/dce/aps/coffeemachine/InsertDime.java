package br.ufpb.dce.aps.coffeemachine;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

public class InsertDime extends CoffeeMachineTest {
	
	private InOrder inOrder;

	@Before
	public void given() {
		inOrder = prepareScenarioWithCoins(Coin.dime);
	}

	@Test
	public void selectBlackWithoutMoney2() {
		// Simulating returns
		doContainBlackIngredients();

		// Operation under test
		facade.select(Button.BUTTON_1);

		// Verification
		inOrder.verify(display).warn(Messages.NO_ENOUGHT_MONEY);
		verifyCloseSession(inOrder, Coin.dime);
	}

	@Test
	public void insertCoinReadBadge() {
		// Operation under test
		facade.readBadge(123456);

		// Verification
		verifyCannotReadBadgeMessage(inOrder);
	}

}
