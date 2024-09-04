package com.example.BankingApplication.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BankingApplication.Dto.AccountDto;
import com.example.BankingApplication.Mapper.AccountMapper;
import com.example.BankingApplication.Model.Account;
import com.example.BankingApplication.Repository.AccountRepository;
import com.example.BankingApplication.Service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exit"));
		
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposite(Long id, double amount) {
		
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exit"));
		
		
		double total = account.getBalance()+amount;
	    account.setBalance(total);
	    Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exit"));
		
		if(account.getBalance()<amount)
		{
			throw new RuntimeException("Insufficient amount");
		}
		
		double total = account .getBalance() - amount;
		account.setBalance(total);
		Account savedaccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedaccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
	}

	@Override
	public void deleteAccount(Long id) {
		
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exit"));
		
		accountRepository.deleteById(id);
	}

}
