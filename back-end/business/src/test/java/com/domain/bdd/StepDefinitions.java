package com.domain.bdd;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Arrays;

import org.assertj.core.api.Assertions;

import com.domain.impl.ClientBankDomainServiceImpl;
import com.domain.model.Account;
import com.domain.model.Client;

public class StepDefinitions {
	
	Client client = new Client("client");
	ClientBankDomainServiceImpl clientBankDomainServiceImpl = new ClientBankDomainServiceImpl();
	Account result;
	
	@Given("I am a client with '<double>' on my account '<String>'")
	public void client_who_wants_to_check_his_account(double balance, String account) {
		   Account account1 = new Account(account);
		   account1.setBalance(balance);
		   client.getAccounts().addAll(Arrays.asList(account1));

	}

	@When("I check my account '<String>'")
	public void he_checks_his_account(String account) {
		result = clientBankDomainServiceImpl.getBalanceClientAccount(client, account);	   
	}

	@Then("My balance should be '<double>'")
	public void his_balance_should_be(double balance) {
		Assertions.assertThat(result.getBalance()).isEqualTo(balance);
		client.getAccounts().clear();
	}
	
	
	@Given("I am a client with '<double>' on my account '<String>' who wants to deposit")
	public void client_who_wants_to_make_a_deposit_his_account(double balance, String account) {
		   Account account1 = new Account(account);
		   account1.setBalance(balance);
		   client.getAccounts().addAll(Arrays.asList(account1));
	}

	@When("I deposit '<double>' on my account '<String>")
	public void makes_a_deposit_his_account(double deposite, String account) {
		result = clientBankDomainServiceImpl.putDepositClientAccount(client, account, deposite);
	}

	@Then("My balance should be '<double>' after deposit")
	public void his_balance_after_deposit(double balance) {
		Assertions.assertThat(result.getBalance()).isEqualTo(balance);
		client.getAccounts().clear();
	}
	
	@Given("I am a client with '<double>' on my account '<String>' who wants to withdraw")
	public void client_who_wants_to_make_a_withdraw_his_account(double balance, String account) {
		   Account account1 = new Account(account);
		   account1.setBalance(balance);
		   client.getAccounts().addAll(Arrays.asList(account1));
	}

	@When("I withdraw '<double>' on my account '<String>'")
	public void makes_a_withdraw_his_account(double withdraw, String account) {
		result = clientBankDomainServiceImpl.withdrawInClientAccount(client, account, withdraw);
	}

	@Then("My balance should be '<double>' after withdraw")
	public void his_balance_after_withdraw(double balance) {
		Assertions.assertThat(result.getBalance()).isEqualTo(balance);
		client.getAccounts().clear();
	}
	
}