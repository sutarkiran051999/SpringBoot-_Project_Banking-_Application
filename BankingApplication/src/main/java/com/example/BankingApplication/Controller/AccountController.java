package com.example.BankingApplication.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BankingApplication.Dto.AccountDto;
import com.example.BankingApplication.Service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	//Add Account Rest API 
	
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto)
	{
		return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
	}
	
	//Get Account Rest API
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id)
	{
		AccountDto accountDto = accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
		
	}
	
	// Deposite Rest API
	
	@PutMapping("/{id}/deposite")
	public ResponseEntity<AccountDto> deposite(@PathVariable Long id, @RequestBody Map<String,Double> request)
	{
		Double amount = request.get("amount");
		AccountDto accountDto = accountService.deposite(id, amount);
		return ResponseEntity.ok(accountDto);
		
	}
	
	//withdraw Rest API
	
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String,Double> request)
	{
		double amount = request.get("amount");
		AccountDto accountDto = accountService.withdraw(id, amount);
		return ResponseEntity.ok(accountDto);
		
	}
	
	//Get All Accounts REST API
	
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts()
	{
		List<AccountDto> accounts = accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
	}
	
	//Delete Account Rest API
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id)
	{
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account is deleted Successfuly!!!");
	}
}
