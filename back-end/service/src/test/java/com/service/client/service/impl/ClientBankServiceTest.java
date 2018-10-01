package com.service.client.service.impl;

import java.util.Arrays;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.domain.model.Account;
import com.service.client.bank.service.impl.ClientBankServiceImpl;
import com.service.dao.model.AccountEntity;
import com.service.dao.model.ClientEntity;
import com.service.dao.repository.ClientRepository;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class ClientBankServiceTest {

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    ClientBankServiceImpl clientBankServiceImpl;
    
    @Before
    public void init() {
    	clientBankServiceImpl.init();
    	
    	ClientEntity client = new ClientEntity();
    	client.setId(1);
    	client.setName("test");
    	AccountEntity account1 = new AccountEntity();
    	AccountEntity account2 = new AccountEntity();
    	account1.setId(1);
    	account1.setBalance(100);
    	account1.setCode("accountCode");
    	
    	account2.setId(2);
    	account2.setBalance(200);
    	account2.setCode("accountCode2");
    	
    	client.getAccounts().addAll(Arrays.asList(account1, account2));
    	
    	Mockito.when(clientRepository.findByName("test")).thenReturn(Optional.of(client));
    	
    	Mockito.when(clientRepository.findByName("testNull")).thenReturn(Optional.ofNullable(null));
    }
    
    @Test
    public void testGetBalance() {

    	Account result = clientBankServiceImpl.getAccountBalance("test", "accountCode");
    	
    	Assertions.assertThat(result.getBalance()).isEqualTo(100);
    	Assertions.assertThat(result.getCode()).isEqualTo("accountCode");
    	
    	Account resultNullClient = clientBankServiceImpl.getAccountBalance("testNull", "accountCode");
    	Assertions.assertThat(resultNullClient).isNull();
    	
    	Account resultNullAcccount = clientBankServiceImpl.getAccountBalance("test", "accountCodeNull");
    	Assertions.assertThat(resultNullAcccount).isNull();
    
    }
    
    @Test
    public void testDeposit() {
    	
    	Account result = clientBankServiceImpl.withDrawClientAccount("test", "accountCode", 5);
    	
    	Assertions.assertThat(result.getBalance()).isEqualTo(95);
    	Assertions.assertThat(result.getCode()).isEqualTo("accountCode");
    	
    	Account resultNullClient = clientBankServiceImpl.withDrawClientAccount("testNull", "accountCode", 5);
    	Assertions.assertThat(resultNullClient).isNull();
    	
    	Account resultNullAcccount = clientBankServiceImpl.withDrawClientAccount("test", "accountCodeNull", 5);
    	Assertions.assertThat(resultNullAcccount).isNull();
    }
    
    @Test
    public void testWithdraw() {

    	Account result = clientBankServiceImpl.depositeClientAccount("test", "accountCode2", 5);
    	
    	Assertions.assertThat(result.getBalance()).isEqualTo(205);
    	Assertions.assertThat(result.getCode()).isEqualTo("accountCode2");
    	
    	Account resultNullClient = clientBankServiceImpl.depositeClientAccount("testNull", "accountCode", 5);
    	Assertions.assertThat(resultNullClient).isNull();
    	
    	Account resultNullAcccount = clientBankServiceImpl.depositeClientAccount("test", "accountCodeNull", 5);
    	Assertions.assertThat(resultNullAcccount).isNull();
    
    }
    
}
