package com.spayker.account.service;

import com.spayker.account.domain.Account;
import com.spayker.account.domain.User;

import java.util.Date;
import java.util.List;

public interface AccountService {

	Account findAccountById(String accountId);

	List<Account> findAccountByName(String accountName);

	Account findAccountByEmail(String email);

	List<Account> findAccountByCreatedDate(Date createdDate);

	List<Account> findAccountByModifiedDate(Date modifiedDate);

	List<Account> findAccountByAge(int age);

	List<Account> findAccountByGender(byte gender);

	List<Account> findAccountByWeight(int weight);

	List<Account> findAccountByHeight(int height);

	Account create(User user);

	void saveChanges(String name, Account update);
}
