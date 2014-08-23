package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.PayrollSystem;


public class MyPayrollSystem {
	
	private PayrollSystem payrollSystem;
	private static boolean isUseBadge;
	
	public static boolean isUseBadge() {
		return isUseBadge;
	}

	public static void setUseBadge(boolean isUseBadge) {
		MyPayrollSystem.isUseBadge = isUseBadge;
	}

	public MyPayrollSystem(ComponentsFactory factory){
		payrollSystem =   factory.getPayrollSystem();
		isUseBadge = false;
			
	}
	
	public boolean debitar(int cents, int badgeCode){
		return payrollSystem.debit(cents, badgeCode);
	}

}
