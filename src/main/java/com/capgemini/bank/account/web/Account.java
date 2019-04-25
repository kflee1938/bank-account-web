package com.capgemini.bank.account.web;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Account {
    
    @NotBlank(message="First name is required")
    @Size(max=30)
	private String firstName; 

    @NotBlank(message="Last name is required")
    @Size(max=30)
    private String lastName;
    
    @NotNull(message="DOB is requried")
    @DateTimeFormat(pattern="MM/dd/yyyy")
    @Past(message="DOB cannot be in the future")
    private Date dob;
    
    //@NotBlank(message="SSN is required")
    @Pattern(regexp="\\d{3}-\\d{2}-\\d{4}", message="Please enter SSN in this format: ###-##-####")
    private String ssn;
    
    @NotBlank(message="Email address is required")
    @Email(message="Please enter a valid email address")
    private String email; 
    
    @Pattern(regexp="\\d{3}-\\d{3}-\\d{4}", message="Please enter mobile number in this format: ###-###-####")
    private String mobileNumber;
    
    @NotBlank(message="Home address is required")
    @Size(max=100)
    private String homeAddress; 

    @NotBlank(message="Mailing address is required")
    @Size(max=100)
    private String mailingAddress;

    @NotBlank(message="Please select an account type")
    private String accountType;
    
    @NotNull(message="Minimum balance is required")
    @Digits(fraction=2, message="Please enter the minimum balance in this format: ###.##", integer = 6)
    @Positive
    private BigDecimal balance;
    
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getMailingAddress() {
		return mailingAddress;
	}
	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}