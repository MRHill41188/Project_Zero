package com.revature.view;

import com.revature.account.Account;
import com.revature.account.User;
import com.revature.dao.UserDao;
import com.revature.utll.Input;

public class UserView implements View {


	UserDao userDao = new UserDao();
	

	@Override
	public void showMenu() {
		System.out.println("1. Log into exist account");
		System.out.println("2. Create new user");
		System.out.println("0. Back");
	}

	@Override
	public View selectOption() {
		int selection = Input.getIntInRange(0, 2);
		switch (selection) {
		case 1: loadAccount();
		if(User.getCurrentUser() != null)
			return new LoggedInView();
		else {
			
			return this;
		}
		case 2: createAccount(); return this;
		default:
		case 0: return new MainMenu();
		}
	}

	private void loadAccount() {
		System.out.println("Please enter username: ");
		String userName = Input.getNextString();
		
		System.out.println("Please enter password: ");
		String password = Input.getNextString();
		
		User.setCurrentUser(userDao.getAccount(userName, password));

	}
		
		
	
	private void createAccount() {
		System.out.println("Please enter username: ");
		String userName = Input.getNextString();
		
		System.out.println("Please enter password: ");
		String password = Input.getNextString();
		
		User user = new User(0, userName, password);
		user = userDao.createAccount(user);
		//System.out.println(user);
		
	}
}