package com.spayker.account.repository;

import com.spayker.account.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

	List<Account> findByName(String name);

	Account findByEmail(String email);

	List<Account> findByCreatedDate(Date createDate);

	List<Account> findByModifiedDate(Date modifiedDate);

	List<Account> findByAge(int age);

	List<Account> findByGender(byte gender);

	List<Account> findByWeight(int weight);

	List<Account> findByHeight(int height);


}
