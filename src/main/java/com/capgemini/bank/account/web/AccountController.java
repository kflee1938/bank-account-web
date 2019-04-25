package com.capgemini.bank.account.web;

import java.math.BigDecimal;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

	private BigDecimal minSavingsBal = new BigDecimal(200);
	private BigDecimal minCheckingBal = new BigDecimal(300);
	private BigDecimal minCDBal = new BigDecimal(2000);

	@GetMapping("/")
	public String showForm(Model model) {
		Account account = new Account();
		model.addAttribute("account", account);
		return "index";
	}
	
	@PostMapping("/")
	public ModelAndView openAccount(@Valid Account account, BindingResult bindingResult) {

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
		
		String accountNumber = "123456789";
		return new ModelAndView("redirect:thanks", "accountNumber", accountNumber);
	}
}
