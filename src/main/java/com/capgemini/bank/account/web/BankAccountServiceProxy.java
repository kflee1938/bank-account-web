package com.capgemini.bank.account.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="bank-account-microservice", url="localhost:8001")
public interface BankAccountServiceProxy {
	@PostMapping("/open")
	public ResponseEntity<Account> openAccount(Account account);
}
