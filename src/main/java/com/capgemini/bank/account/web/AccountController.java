package com.capgemini.bank.account.web;

import java.math.BigDecimal;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private BigDecimal minSavingsBal = new BigDecimal(200);
	private BigDecimal minCheckingBal = new BigDecimal(300);
	private BigDecimal minCDBal = new BigDecimal(2000);

	@Autowired
	private BankAccountServiceProxy proxy;
	
	
	@GetMapping("/")
	public String showForm(Model model) {
		Account account = new Account();
		model.addAttribute("account", account);
		return "index";
	}
	
	@PostMapping("/open-account")
	public ModelAndView openAccount(@Valid Account account, BindingResult bindingResult, RedirectAttributes attributes) {
		logger.info("Using RestTemplate");
		if (bindingResult.hasErrors()) {
			return new ModelAndView("index");
		}
		String accountType = account.getAccountType();
		BigDecimal bal = account.getBalance();
		if ("Savings".equals(accountType) && bal.compareTo(minSavingsBal) < 0) {
			bindingResult.rejectValue("balance", "", "Minimum balance for savings is $200");
			return new ModelAndView("index");
		} else if ("Checking".equals(accountType) && bal.compareTo(minCheckingBal) < 0) {
			bindingResult.rejectValue("balance", "", "Minimum balance for checking is $300");
			return new ModelAndView("index");
		} else if ("CD".equals(accountType) && bal.compareTo(minCDBal) < 0) {
			bindingResult.rejectValue("balance", "", "Minimum balance for CD is $2000");
			return new ModelAndView("index");
		}
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Account> response = restTemplate.postForEntity("http://localhost:8001/open", account, Account.class);
		String accountNumber = response.getBody().getAccountNumber();
		attributes.addFlashAttribute("accountNumber", accountNumber);
		return new ModelAndView("redirect:thanks");
	}

	@PostMapping("/open-account-feign")
	public ModelAndView openAccountFeign(@Valid Account account, BindingResult bindingResult, RedirectAttributes attributes) {
		logger.info("Using Feign");

		if (bindingResult.hasErrors()) {
			return new ModelAndView("index");
		}
		String accountType = account.getAccountType();
		BigDecimal bal = account.getBalance();
		if ("Savings".equals(accountType) && bal.compareTo(minSavingsBal) < 0) {
			bindingResult.rejectValue("balance", "", "Minimum balance for savings is $200");
			return new ModelAndView("index");
		} else if ("Checking".equals(accountType) && bal.compareTo(minCheckingBal) < 0) {
			bindingResult.rejectValue("balance", "", "Minimum balance for checking is $300");
			return new ModelAndView("index");
		} else if ("CD".equals(accountType) && bal.compareTo(minCDBal) < 0) {
			bindingResult.rejectValue("balance", "", "Minimum balance for CD is $2000");
			return new ModelAndView("index");
		}
		
		ResponseEntity<Account> response = proxy.openAccount(account);
		String accountNumber = response.getBody().getAccountNumber();
		attributes.addFlashAttribute("accountNumber", accountNumber);
		return new ModelAndView("redirect:thanks");
	}
	
}
