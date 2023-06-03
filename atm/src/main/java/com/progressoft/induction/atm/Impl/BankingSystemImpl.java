package com.progressoft.induction.atm.Impl;

import com.progressoft.induction.atm.BankingSystem;
import com.progressoft.induction.atm.Banknote;
import com.progressoft.induction.atm.exceptions.InsufficientFundsException;
import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import com.progressoft.induction.atm.exceptions.AccountNotFoundException;

public class BankingSystemImpl implements BankingSystem {
   Map<String, BigDecimal> accountBalanceMap = new HashMap<String, BigDecimal>();
   EnumMap<Banknote,Integer> atmCashMap = new EnumMap<>(Banknote.class);

    public BankingSystemImpl() {
        atmCashMap.put(Banknote.FIFTY_JOD,10);
        atmCashMap.put(Banknote.TWENTY_JOD,20);
        atmCashMap.put(Banknote.TEN_JOD,100);
        atmCashMap.put(Banknote.FIVE_JOD,100);

        accountBalanceMap.put("123456789", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("111111111", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("222222222", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("333333333", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("444444444", BigDecimal.valueOf(1000.0));
    }

	public BigDecimal sumOfMoneyInAtm(){
        // Your code
		 BigDecimal totalMoneyInAtm = BigDecimal.ZERO;
	        
	        for (Map.Entry<Banknote, Integer> entry : atmCashMap.entrySet()) {
	            BigDecimal bankValue = entry.getKey().getValue();
	            int banknoteCount = entry.getValue();
	            totalMoneyInAtm = totalMoneyInAtm.add(bankValue.multiply(BigDecimal.valueOf(banknoteCount)));
	        }
        return totalMoneyInAtm;
    }


    @Override
    public BigDecimal getAccountBalance(String accountNumber){
        //your code  
    	if(isvalidaccount(accountNumber)) {
    		throw new AccountNotFoundException("Account Not found"+accountNumber);
    	}
    	
        return accountBalanceMap.get(accountNumber);
    }

    @Override
    public void debitAccount(String accountNumber, BigDecimal amount) {
        //your code
    	if(isvalidaccount(accountNumber)) {
    		throw new AccountNotFoundException("Account Not found"+accountNumber);
    	}
      	BigDecimal TotalAmountinAccount=getAccountBalance(accountNumber);
    	if(getAccountBalance(accountNumber).signum()==0) {
    		throw new InsufficientFundsException(accountNumber);   
    		};
    	
    }
//    Check valid account or not
    public Boolean isvalidaccount(String accountNumber) {
    	if(!accountBalanceMap.containsKey(accountNumber)) {
    		return true;
    	}
    	return false;
    }
}
