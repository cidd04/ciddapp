package com.cidd.sentiment.service.business;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Collections;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cidd.sentiment.dao.AccountDao;
import com.cidd.sentiment.dto.SimpleSocialUser;
import com.cidd.sentiment.model.Account;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

//	@Inject
//	private PasswordEncoder passwordEncoder;

	@Autowired
	private AccountDao accountDao;

	@PostConstruct
	public void initialize() {
		// accountDao.save(new Account("user", "demo", "ROLE_USER"));
		// accountDao.save(new Account("admin", "admin", "ROLE_ADMIN"));
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Account account = accountDao.findActiveUserByEmail(email);
		if (account == null) {
			throw new UsernameNotFoundException("user not found");
		}

		return createUser(account);
	}

	public void signin(Account account) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
	}

	public Account saveNewAccount(Account account) {
		return accountDao.saveNewAccount(account);
	}

	private Authentication authenticate(Account account) {
		return new UsernamePasswordAuthenticationToken(createUser(account), null,
				Collections.singleton(createAuthority(account)));
	}

	private User createUser(Account account) {
		SimpleSocialUser user = new SimpleSocialUser(account.getName(), account.getPassword(),
				Collections.singleton(createAuthority(account)));
		user.setId(account.getId());
		user.setName(account.getName());
		return user;

		// return new User(account.getEmail(), account.getPassword(),
		// Collections.singleton(createAuthority(account)));
	}

	private GrantedAuthority createAuthority(Account account) {
		return new SimpleGrantedAuthority(account.getRole());
	}

	private SimpleSocialUser getCurrentUser() {
		return (SimpleSocialUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public SimpleSocialUser getSimpleSocialUser() {
		return (SimpleSocialUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public Account getCurrentAccount() {
		return accountDao.getUserById(getCurrentUser().getId());
	}

	public Account getUserById(Integer userId) {
		return accountDao.getUserById(userId);
	}

	public byte[] getByteArrayByFileId(String fileId) {
		File file = new File("/CIDDAPP/ACCOUNT/" + fileId);
		Path path = Paths.get(file.getAbsolutePath());
		try {
			return Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new byte[0];
	}

	@Override
	public Boolean processPassword(String oldPassword, String newPassword, String newPassword2) {
		if (StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)
				&& StringUtils.isNotBlank(newPassword2) && newPassword.equals(newPassword2)) {
			Account account = getCurrentAccount();
//			if (passwordEncoder.matches(oldPassword, account.getPassword())) {
//				account.setPassword(passwordEncoder.encode(newPassword));
//				accountDao.updateAccount(account);
//				return true;
//			}
		}
		return false;
	}

	@Override
	public String saveAccountImage(byte[] b) {
		String fileName = (new BigInteger(130, new SecureRandom()).toString(32)) + ".jpg";
		try {
			FileUtils.writeByteArrayToFile(new File("/CIDDAPP/ACCOUNT/" + fileName), b);
			return fileName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
