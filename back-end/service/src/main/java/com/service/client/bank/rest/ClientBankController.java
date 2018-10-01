package com.service.client.bank.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.domain.model.Account;
import com.service.client.bank.service.impl.ClientBankServiceImpl;

@RestController
@RequestMapping("/api/vi")
public class ClientBankController {

	@Autowired
	private ClientBankServiceImpl clientBankService;
	
	@GetMapping("/client/{client}/account/{account}")
	@ResponseBody
    public Account getAccountBalance(@PathVariable String client, @PathVariable String account) {
		return clientBankService.getAccountBalance(client, account);
	}
	
	@PutMapping("/client/{client}/account/{account}/deposite/{deposite}")
	@ResponseBody
    public Account putDepositeAccount(@PathVariable String client, @PathVariable String account, @PathVariable double deposite) {
		return clientBankService.depositeClientAccount(client, account, deposite);
	}
	
	@PutMapping("/client/{client}/account/{account}/withdraw/{withdraw}")
	@ResponseBody
    public Account withdrawAccount(@PathVariable String client, @PathVariable String account, @PathVariable double withdraw) {
		return clientBankService.withDrawClientAccount(client, account, withdraw);
	}
}
