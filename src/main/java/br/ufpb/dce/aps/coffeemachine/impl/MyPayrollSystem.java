package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;
import br.ufpb.dce.aps.coffeemachine.PayrollSystem;

public class MyPayrollSystem {

	private PayrollSystem payrollSystem;
	private int badgeCode;
	private static boolean IS_USE_BADGE = false;

	public MyPayrollSystem(ComponentsFactory factory) {
		payrollSystem = factory.getPayrollSystem();
		setUseBadge(false);
	}

	public void UtilizeBadge() throws UseCoinBeforeBadgeException {
		if (MyCashBox.isUseCoin()) {
			throw new UseCoinBeforeBadgeException("");
		} else {
			setUseBadge(true);
		}

	}

	public static boolean isUseBadge() {
		return IS_USE_BADGE;
	}

	public static void setUseBadge(boolean isUseBadge) {
		IS_USE_BADGE = isUseBadge;
	}

	public boolean debitar(int cents, int badgeCode) {
		if (!payrollSystem.debit(cents, badgeCode)) {
			MyDisplay.setWarnMessage((Messages.UNKNOWN_BADGE_CODE));
			return false;
		}
		return true;
	}

	public void readBadgeCode(int badgeCode) {
		this.badgeCode = badgeCode;

	}

	public int getBadgeCode() {
		return badgeCode;
	}

}
