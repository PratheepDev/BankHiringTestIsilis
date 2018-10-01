package com.domain.tdd;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import com.domain.impl.ClientBankDomainServiceImpl;
import com.domain.model.Account;
import com.domain.model.Client;

public class ClientBanckDomainServiceTest {
	
private ClientBankDomainServiceImpl clientBankDomainService;

private  Client client;



   @Before
   public void init() {
	   clientBankDomainService = new ClientBankDomainServiceImpl();
	   client = new Client("clientTest");
	   Account account1 = new Account("1234");
	   account1.setBalance(100);
	   
	   Account account2 = new Account("12345");
	   account2.setBalance(200);
	   
	   Account account3 = new Account("123456");
	   account3.setBalance(300);
	   
	   client.getAccounts().addAll(Arrays.asList(account1, account2, account3));
   };
   
	@Test
    public void testGetBalanceClientAccount() {
		Account result = clientBankDomainService.getBalanceClientAccount(client, "1234");
        Assertions.assertThat(result.getBalance()).isEqualTo(100);
        
    	Account resultNull = clientBankDomainService.getBalanceClientAccount(client, "Null");
        Assertions.assertThat(resultNull).isNull();
    };
	
	@Test
    public void testPutDepositClientAccount() {
		Account result = clientBankDomainService.putDepositClientAccount(client, "12345", 6.6);
        Assertions.assertThat(result.getBalance()).isEqualTo(206.6);
        
    	Account resultNull = clientBankDomainService.putDepositClientAccount(client, "Null", 6.6);
        Assertions.assertThat(resultNull).isNull();
    };
	
	@Test
    public void testWithdrawInClientAccount() {
		Account result = clientBankDomainService.withdrawInClientAccount(client, "1234", 5.6);
        Assertions.assertThat(result.getBalance()).isEqualTo(94.4);
        
    	Account resultNull = clientBankDomainService.withdrawInClientAccount(client, "null", 5.6);
        Assertions.assertThat(resultNull).isNull();
    }
	
	@Test
    public void testManageAllCaseClientAccount() {
		
		Account result = clientBankDomainService.manageAllCases(client, "123456", 0);
        Assertions.assertThat(result.getBalance()).isEqualTo(300);
		
		Account result2 = clientBankDomainService.manageAllCases(client, "123456", 6.6);
        Assertions.assertThat(result2.getBalance()).isEqualTo(306.6);
        
		Account result3 = clientBankDomainService.manageAllCases(client, "123456", -5.6);
        Assertions.assertThat(result3.getBalance()).isEqualTo(301);
        
    	Account resultNull = clientBankDomainService.manageAllCases(client, "null", 5.6);
        Assertions.assertThat(resultNull).isNull();
    }	
}
