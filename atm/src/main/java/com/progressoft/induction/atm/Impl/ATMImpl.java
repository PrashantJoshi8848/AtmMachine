package com.progressoft.induction.atm.Impl;

import com.progressoft.induction.atm.ATM;
import com.progressoft.induction.atm.BankingSystem;
import com.progressoft.induction.atm.Banknote;
import com.progressoft.induction.atm.exceptions.AccountNotFoundException;
import com.progressoft.induction.atm.exceptions.InsufficientFundsException;
import com.progressoft.induction.atm.exceptions.NotEnoughMoneyInATMException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ATMImpl extends BankingSystemImpl implements ATM {
    private final BankingSystemImpl bankingSystem=new BankingSystemImpl();
    @Override
    public List<Banknote> withdraw(String accountNumber, BigDecimal amount) {
    	if(isvalidaccount(accountNumber)) {
    		throw new AccountNotFoundException("Account Not found"+accountNumber);
    	}
        // Your code here
    	int compareResultofAmtAndAtm=getAccountBalance(accountNumber).compareTo(amount);
    	if(getAccountBalance(accountNumber).equals(BigDecimal.ZERO)) {
    		throw new InsufficientFundsException(accountNumber);
    	}
    	if(compareResultofAmtAndAtm==0) {
    		debitAccount(accountNumber, amount);
    		
    	}else if(compareResultofAmtAndAtm==1) {
    			debitAccount(accountNumber, amount);
    	}else {
    		throw new InsufficientFundsException(accountNumber);
    		
    	}
    	return null;
    }
	@Override
	public BigDecimal checkBalance(String accountNumber) {
		// TODO Auto-generated method stub
		return getAccountBalance(accountNumber);
	}
}
