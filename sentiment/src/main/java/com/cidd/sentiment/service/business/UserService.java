package com.cidd.sentiment.service.business;

import com.cidd.sentiment.dto.SimpleSocialUser;
import com.cidd.sentiment.model.Account;

public interface UserService {

	public void initialize();

	public void signin(Account account);

	public Account getCurrentAccount();

	public Account saveNewAccount(Account createAccount);

	public Account getUserById(Integer userId);
	
	public SimpleSocialUser getSimpleSocialUser();

	public byte[] getByteArrayByFileId(String fileId);

	public Boolean processPassword(String oldPassword, String newPassword, String newPassword2);
	
	public String saveAccountImage(byte[] b);
	
}
