package com.revature.account;

public class Account {
	
	private static Account currentAccount;

	public static Account getCurrentAccount() {
		return currentAccount;
	}
	public static void setCurrentAccount(Account currentAccount) {
		Account.currentAccount = currentAccount;
	}
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getBalance() {
		return balance;
	}
	
	private String accountType;
	private int balance;

	public void setBalance(int balance) {
	 Account.this.balance = balance;
		
	}
	
}