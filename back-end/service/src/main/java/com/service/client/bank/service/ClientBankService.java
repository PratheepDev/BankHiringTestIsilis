package com.service.client.bank.service;

import com.domain.model.Account;

public interface ClientBankService {

	public Account getAccountBalance(String name, String accountCode);
	
	public Account depositeClientAccount(String name, String accountCode, double deposite);
	
	public Account withDrawClientAccount (String name, String accountCode, double withdraw);
	
}
