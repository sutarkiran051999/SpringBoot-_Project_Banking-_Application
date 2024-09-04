package com.example.BankingApplication.Service;

import java.util.List;

import com.example.BankingApplication.Dto.AccountDto;

public interface AccountService {

	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposite(Long id , double amount);
	
	AccountDto withdraw(Long id , double amount);
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccount(Long id);
	
}
