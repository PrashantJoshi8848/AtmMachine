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
    	// Your code here
    	if(isvalidaccount(accountNumber)) {
    		throw new AccountNotFoundException("Account Not found"+accountNumber);
    	}
    	
    	int compareResultofAccountAndamount=checkBalance(accountNumber).compareTo(amount);
    	int comapreResultofAtmAndAmount=sumOfMoneyInAtm().compareTo(amount);   	   	
    	
    	
    	if(compareResultofAccountAndamount<0) 
    	{	
    		throw new InsufficientFundsException(accountNumber);
    		
    	}else {    		
    		if(comapreResultofAtmAndAmount<0){
    			throw new NotEnoughMoneyInATMException(accountNumber);
    		}
    	}
    	
    	return null;
    }
	@Override
	public BigDecimal checkBalance(String accountNumber) {
		return getAccountBalance(accountNumber);
	}
}
