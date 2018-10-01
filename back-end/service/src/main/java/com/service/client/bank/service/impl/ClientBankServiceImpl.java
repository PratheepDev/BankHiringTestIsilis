package com.service.client.bank.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.impl.ClientBankDomainServiceImpl;
import com.domain.model.Account;
import com.domain.model.Client;
import com.service.client.bank.service.ClientBankService;
import com.service.dao.model.AccountEntity;
import com.service.dao.model.ClientEntity;
import com.service.dao.repository.ClientRepository;

@Service
public class ClientBankServiceImpl implements ClientBankService {

	private ClientBankDomainServiceImpl innerService;

	@Autowired
	private ClientRepository clientRepository;

	@PostConstruct
	public void init(){
		this.innerService = new ClientBankDomainServiceImpl();
	}

	/**
	 * Methode permettant de consulter le solde d'un compte
	 * @param name
	 * @param accountCode
	 * @return Account
	 */
	@Transactional(readOnly = true)
	@Override
	public Account getAccountBalance(String name, String accountCode) {
		Account account;
		Optional<ClientEntity> clientDao = clientRepository.findByName(name);

		if (!clientDao.isPresent()) {
			//Gestion de cas d'erreur à envisager pour le client
			return null;
		}

		account = innerService.getBalanceClientAccount(convertClientDaoToBO(clientDao.get()), accountCode);

		return account;
	}

	/**
	 * Methode permettant de deposer de l'argent
	 * @param name
	 * @param accountCode
	 * @param deposite
	 * @return Account
	 */
	@Transactional(readOnly = false)
	@Override
	public Account depositeClientAccount(String name, String accountCode, double deposite) {
		Account account;

		Optional<ClientEntity> clientDao = clientRepository.findByName(name);

		if (!clientDao.isPresent()) {
			return null;
		}

		account = innerService.putDepositClientAccount(convertClientDaoToBO(clientDao.get()), accountCode, deposite);

		if (account == null) {
			return null;
		}

		updateDbAccount(account, clientDao);

		return account;
	}
    
	/**
	 * Methode permettant de retirer de l'argent
	 * @param name
	 * @param accountCode
	 * @param withdraw
	 * @return Account
	 */
	@Transactional(readOnly = false)
	@Override
	public Account withDrawClientAccount(String name, String accountCode, double withdraw) {
		Account account;

		Optional<ClientEntity> clientDao = clientRepository.findByName(name);

		if (!clientDao.isPresent()) {
			return null;
		}

		account = innerService.withdrawInClientAccount(convertClientDaoToBO(clientDao.get()), accountCode, withdraw);

		if (account == null) {
			return null;
		}

		updateDbAccount(account, clientDao);

		return account;
	}

	/**
	 * Methode de convertion entre la base de donnee et le modele pour l'entité client
	 * @param clientEntity
	 * @return
	 */
	private Client convertClientDaoToBO(ClientEntity clientEntity) {

		Client client = new Client(clientEntity.getName());
		client.getAccounts().addAll(clientEntity.getAccounts().stream().map(account -> convertAccountDaoToBO(account))
				.collect(Collectors.toList()));

		return client;
	};

	/**
	 * Methode de convertion entre la base de donnee et le modele pour l'entité client
	 * @param accountEntity
	 * @return
	 */
	private Account convertAccountDaoToBO(AccountEntity accountEntity) {
		Account account = new Account(accountEntity.getCode());
		account.setBalance(accountEntity.getBalance());
		return account;

	}

	/**
	 * Methode permettant d'enregistrer dans la base
	 * @param account
	 * @param clientDao
	 */
	private void updateDbAccount(Account account, Optional<ClientEntity> clientDao) {
		clientDao.get().getAccounts().stream()
				.filter(accountFilter -> account.getCode().equals(accountFilter.getCode())).findAny().get()
				.setBalance(account.getBalance());
	}

}
