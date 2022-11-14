package com.spring.account.vo;

public class AccountVO {
	private String accountNo;
	private String custName;
	private int balance;
	
	public AccountVO() {
		
	}
	
	public AccountVO(String accountNo, String custName, int balnace) {
		this.accountNo = accountNo;
		this.custName = custName;
		this.balance= balance;
		
	}
	
	public String getAccountNo() {
		return accountNo;
	}
	
	public void SetAccountNo (String accountNo) {
		this.accountNo = accountNo;
		
	}
	
	public String getCustName() {
		return custName;
		
	}
	
	public void SetCustName(String custName) {
		this.custName = custName;
	}
	
	public int getBalance() {
		return balance;
		
	}
	
	public void SetBalance(int balance) {
		this.balance = balance;
	}
	
}
