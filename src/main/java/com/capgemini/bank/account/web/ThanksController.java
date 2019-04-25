package com.capgemini.bank.account.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThanksController {

	@GetMapping("/thanks")
	public String showThanks(@RequestParam(value="accountNumber") String accountNumber, Model model) {
		model.addAttribute("accountNumber", accountNumber);
		return "thanks";
	}
}
