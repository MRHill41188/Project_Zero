package com.revature.view;

import java.sql.SQLException;

import com.revature.account.Account;
import com.revature.account.User;
import com.revature.dao.AccountDao;
import com.revature.dao.UserDao;
import com.revature.utll.Input;

public class AccountView implements View {
	
	AccountDao accountDao = new AccountDao();

	@Override
	public void showMenu() {
		System.out.println("Account Number: " +Account.getCurrentAccount().getId());
		System.out.println("Account Type"
				+ ": " +Account.getCurrentAccount().getAccountType());
		System.out.println("Account Balance: "+ Account.getCurrentAccount().getBalance()+" Gold Coins");
		System.out.println("0. Log out of account");
		System.out.println("1. Withdrawl Coin");
		System.out.println("2. Deposit Coin");
		System.out.println("3. Transfer Coin");
		System.out.println("4. Check Current balance:");
		System.out.println("5. Cancel Account");
	
		
	}

	@Override
	public View selectOption() {
		
		int selection = Input.getIntInRange(0, 5);
		switch (selection) {
		case 0: return new MainMenu();
		case 1: 
		int withdrawlAmount = Input.getIntInRange(1, 100);
			try {
				accountDao.Withdrawl(withdrawlAmount, Account.getCurrentAccount().getId());
				System.out.println("withdrawl amount: "+ withdrawlAmount);
			} catch (SQLException e) {
				
				e.printStackTrace();
			} return this;
		case 2: int depositAmount = Input.getIntInRange(1, 100);
		try {
			accountDao.Deposit(depositAmount);
			System.out.println("withdrawl amount: "+ depositAmount);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} return this;
		default:
		case 3:
		case 4: return this;
		case 5: 
				accountDao.CloseAccount(User.getCurrentUser().getId(), Account.getCurrentAccount().getId());
			
			
			return new MainMenu(); 
		
	}
		
			
			
		}



}