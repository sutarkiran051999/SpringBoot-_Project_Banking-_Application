package com.example.BankingApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BankingApplication.Model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

}
