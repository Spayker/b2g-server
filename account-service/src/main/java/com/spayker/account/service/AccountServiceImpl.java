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

/**
 *  Service layer implementation to work with Account entities.
 **/
@Service
public class AccountServiceImpl implements AccountService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private AccountRepository repository;

	@Autowired
	private AuthServiceClient authClient;

	/**
	 *  Looks for stored account by its name.
	 *  @param name - string value for search
	 *  @return list of found accounts
	 **/
	@Override
	public List<Account> findAccountByName(String name) {
		if(name.isEmpty() || name.isBlank()){
			throw new IllegalArgumentException("provided name is empty or blank");
		}
		return repository.findByName(name);
	}

	/**
	 *  Looks for stored account by its id.
	 *  @param accountId - string value for search
	 *  @return found account
	 **/
	@Override
	public Account findAccountById(String accountId) {
		if(accountId.isEmpty() || accountId.isBlank()){
			throw new IllegalArgumentException("provided accountId has 0 String length");
		}
		Optional<Account> foundAccount = repository.findById(Long.valueOf(accountId));
		return foundAccount.orElse(null);
	}

	/**
	 *  Looks for stored account by its email
	 *  @param email - string value for search
	 *  @return found Account
	 **/
	@Override
	public Account findAccountByEmail(String email) {
		if(email.isEmpty() || email.isBlank()){
			throw new IllegalArgumentException("provided email is empty or blank");
		}
		return repository.findByEmail(email);
	}

	/**
	 *  Looks for stored account by its created date
	 *  @param createdDate - string value for search
	 *  @return list of found accounts
	 **/
	@Override
	public List<Account> findAccountByCreatedDate(Date createdDate) {
		return repository.findByCreatedDate(createdDate);
	}

	/**
	 *  Looks for stored account by its modified date
	 *  @param modifiedDate - string value for search
	 *  @return list of found accounts
	 **/
	@Override
	public List<Account> findAccountByModifiedDate(Date modifiedDate) { return repository.findByModifiedDate(modifiedDate); }

	/**
	 *  Looks for stored account by its modified age
	 *  @param age - string value for search
	 *  @return list of found accounts
	 **/
	@Override
	public List<Account> findAccountByAge(int age) {
		return repository.findByAge(age);
	}

	/**
	 *  Looks for stored account by its gender
	 *  @param gender - string value for search
	 *  @return list of found accounts
	 **/
	@Override
	public List<Account> findAccountByGender(int gender) {
		return repository.findByGender(gender);
	}

	/**
	 *  Looks for stored account by its weight
	 *  @param weight - string value for search
	 *  @return list of found accounts
	 **/
	@Override
	public List<Account> findAccountByWeight(int weight) {
		return repository.findByWeight(weight);
	}

	/**
	 *  Looks for stored account by its height
	 *  @param height - string value for search
	 *  @return list of found accounts
	 **/
	@Override
	public List<Account> findAccountByHeight(int height) {
		return repository.findByHeight(height);
	}

	/**
	 *  Creates new Account and returns it by provided User instance.
	 *  @param user - instance of User with email and password
	 *  @return created Account
	 **/
	@Override
	public Account create(Account account, User user) {
		Account existing = repository.findByEmail(account.getEmail());
		if(existing == null){
			authClient.createUser(user);
			Account savedAccount = repository.saveAndFlush(account);
			log.info("new account has been created: " + savedAccount.getEmail());
			return savedAccount;
		} else {
			throw new AccountException("account already exists: " + account.getEmail());
		}
	}

	/**
	 *  Updates a stored account and returns its updated variant.
	 *  @param update - an updated variation of Account that must be persisted
	 **/
	@Override
	public Account saveChanges(Account update) {
		Account account = repository.findByEmail(update.getEmail());
		if(account == null){
			throw new AccountException("can't find account with email " + update.getEmail());
		} else {
			update.setModifiedDate(new Date());
			log.debug("account {} changes have been saved", update.getEmail());
			return repository.saveAndFlush(update);
		}
	}
}
