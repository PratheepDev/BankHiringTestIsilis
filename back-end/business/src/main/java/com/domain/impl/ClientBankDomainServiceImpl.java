package com.domain.impl;

import com.domain.ClientBankDomainServices;
import com.domain.model.Account;
import com.domain.model.Client;

/*
 * THIS IS NOT EXPECTED TO BECOME A SPRING-BOOT SERVICE
 */
public class ClientBankDomainServiceImpl implements ClientBankDomainServices {

	/**
	 * Consultation du solde d'un compte pour un client donnée 
	 * @param client
	 * @param code
	 * @return
	 */
	@Override
	public Account getBalanceClientAccount (Client client , String code) {
		
		Account account =  client.getAccounts()
		  .stream()
		  .filter(accountFilter -> code.equals(accountFilter.getCode()))
		  .findAny()
		  .orElse(null);
		
		return account;	
	}
	
	/**
	 * Deposer une sommme dans un compte pour un client donnée
	 * @param client
	 * @param code
	 * @param deposite
	 * @return
	 */
	@Override
	public Account putDepositClientAccount (Client client , String code , double deposite) {
		
		Account account =  client.getAccounts()
		  .stream()
		  .filter(accountFilter -> code.equals(accountFilter.getCode()))
		  .findAny()
		  .orElse(null);
		
		if (account != null)  {
			account.setBalance(account.getBalance() + deposite);	
			return account;	
		}
		
		return null;
		
	}
	
	/**
	 * Retirer une sommme dans un compte pour un client donnée
	 * @param client
	 * @param code
	 * @param withdraw
	 * @return
	 */
	@Override
	public Account withdrawInClientAccount (Client client , String code , double withdraw) {
		
		Account account =  client.getAccounts()
		  .stream()
		  .filter(accountFilter -> code.equals(accountFilter.getCode()))
		  .findAny()
		  .orElse(null);
		
		if (account != null)  {
			account.setBalance(account.getBalance() - withdraw);	
			return account;	
		}
		
		return null;
		
	}
	
	/**
	 * Methode pouvant gérer le retrait, le dépot et la consultation d'un compte pour un client donnée
	 * @param client
	 * @param code
	 * @param transaction
	 * @return
	 */
	@Override
	public Account manageAllCases (Client client , String code , double transaction) {
		
		Account account =  client.getAccounts()
		  .stream()
		  .filter(accountFilter -> code.equals(accountFilter.getCode()))
		  .findAny()
		  .orElse(null);
		
		if (account != null)  {
			account.setBalance(account.getBalance() + transaction);	
			return account;	
		}
		
		return null;
		
	}
	
}
