package com.domain;

import com.domain.model.Account;
import com.domain.model.Client;

public interface ClientBankDomainServices {

	public Account getBalanceClientAccount (Client client , String code);
	
	public Account putDepositClientAccount (Client client , String code , double deposite);
	
	public Account withdrawInClientAccount (Client client , String code , double withdraw);
	
	public Account manageAllCases (Client client , String code , double transaction);
}
