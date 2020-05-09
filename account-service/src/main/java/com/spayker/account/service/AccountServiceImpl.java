package com.spayker.account.service;

import com.spayker.account.client.AuthServiceClient;
import com.spayker.account.domain.Account;
import com.spayker.account.domain.User;
import com.spayker.account.exception.AccountException;
import com.spayker.account.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private AuthServiceClient authClient;

	@Autowired
	private AccountRepository repository;

	@Override
	public List<Account> findAccountByName(String accountName) {
		if(accountName.length() == 0){
			throw new IllegalArgumentException("provided accountName has 0 String length");
		}
		return repository.findByName(accountName);
	}

	@Override
	public Account findAccountById(String accountId) {
		if(accountId.length() == 0){
			throw new IllegalArgumentException("provided accountId has 0 String length");
		}
		Optional<Account> foundAccount = repository.findById(Long.valueOf(accountId));
		return foundAccount.orElse(null);
	}

	@Override
	public Account findAccountByEmail(String email) {
		if(email.length() == 0){
			throw new IllegalArgumentException("provided email has 0 String length");
		}
		return repository.findByEmail(email);
	}

	@Override
	public List<Account> findAccountByCreatedDate(Date createdDate) {
		return repository.findByCreatedDate(createdDate);
	}

	@Override
	public List<Account> findAccountByModifiedDate(Date modifiedDate) {
		return repository.findByModifiedDate(modifiedDate);
	}

	@Override
	public List<Account> findAccountByAge(int age) {
		return repository.findByAge(age);
	}

	@Override
	public List<Account> findAccountByGender(byte gender) {
		return repository.findByGender(gender);
	}

	@Override
	public List<Account> findAccountByWeight(int weight) {
		return repository.findByWeight(weight);
	}

	@Override
	public List<Account> findAccountByHeight(int height) {
		return repository.findByHeight(height);
	}

	@Override
	public Account create(User user) {
		List<Account> existing = repository.findByName(user.getUsername());
		if(existing == null){
			authClient.createUser(user);
			Account account = Account.builder()
					.name(user.getUsername())
					.createdDate(new Date())
					.build();

			repository.save(account);
			log.info("new account has been created: " + account.getName());
			return account;
		} else {
			throw new AccountException("account already exists: " + user.getUsername());
		}
	}

	@Override
	public void saveChanges(String name, Account update) {
		List<Account> accounts = repository.findByName(name);
		if(accounts == null){
			throw new AccountException("can't find account with name " + name);
		} else {
			// accounts.setModifiedDate(new Date());
			// repository.save(accounts);
			log.debug("account {} changes has been saved", name);
		}
	}
}
