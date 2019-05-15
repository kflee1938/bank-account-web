package com.capgemini.bank.account.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.capgemini.bank.account.web")
public class BankAccountWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountWebApplication.class, args);
	}

}
