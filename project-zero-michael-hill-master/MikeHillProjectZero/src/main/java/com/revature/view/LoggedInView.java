package com.revature.view;

import java.sql.SQLException;

import com.revature.account.Account;
import com.revature.account.User;
import com.revature.dao.AccountDao;
import com.revature.utll.Input;

public class LoggedInView implements View {

	AccountDao userDao = new AccountDao();

	@Override
	public void showMenu() {
		System.out.println("Welcome back " + User.getCurrentUser().getUserName());
		System.out.println("0. Log out and return to main menu");
		System.out.println("1. View eisting bank account");
		System.out.println("2. Create new savings account");
		System.out.println("3. Create new checking account");
	}

	@Override
	public View selectOption() {
		int selection = Input.getIntInRange(0, 3);
		AccountDao accountDao = new AccountDao();
		switch (selection) {
		case 1:
			try {
				accountDao.LoadAccount();
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return new AccountView();
		case 2:

			try {
				accountDao.CreateAccount("saving", User.getCurrentUser().getUserName());
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Successfully created savings account");
			return this;
		case 3:

			try {
				accountDao.CreateAccount("checking", User.getCurrentUser().getUserName());
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Successfully created checking account");
			return this;
		default:
		case 0:
			return new MainMenu();
		}
	}

}
