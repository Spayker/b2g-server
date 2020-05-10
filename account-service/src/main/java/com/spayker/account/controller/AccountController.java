package com.spayker.account.controller;

import com.spayker.account.domain.Account;
import com.spayker.account.domain.User;
import com.spayker.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PreAuthorize("#oauth2.hasScope('server')")
	@RequestMapping(path = "/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Account>> getAccountByName(@PathVariable String name) {
		return new ResponseEntity<>(accountService.findAccountByName(name), HttpStatus.OK);
	}


	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ResponseEntity<Account> createNewAccount(@Valid @RequestBody Account account) {
		return new ResponseEntity<>(accountService.create(account), HttpStatus.CREATED);
	}



}
