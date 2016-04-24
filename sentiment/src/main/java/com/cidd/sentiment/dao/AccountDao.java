package com.cidd.sentiment.dao;

import java.util.Set;

import javax.inject.Inject;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.cidd.sentiment.model.Account;

@Repository
public class AccountDao extends AbstractDao<Account> {

//	@Inject
//	private PasswordEncoder passwordEncoder;

	public Account saveNewAccount(Account account) {
//		account.setPassword(passwordEncoder.encode(account.getPassword()));
//		save(account);
		return account;
	}

	public Account saveAccount(Account account) {
//		save(account);
		return account;
	}

	public Account updateAccount(Account account) {
//		update(account);
		return account;
	}

	public Account findActiveUserByEmail(String email) {
		return null;
	}
	
	public Account findByName(String name) {
		return null;
	}

	public Account getUserById(Integer userId) {
		return null;
	}

	public Account getUserByActiveFlag() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Account> findAllInactiveUsers() {
		return null;
	}
}
