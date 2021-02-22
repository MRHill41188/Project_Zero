package com.revature;


import com.revature.view.MainMenu;
import com.revature.view.View;


public class Launcher {
	public static void main(String[] args) {
		View view = new com.revature.view.MainMenu();
		
		while(view != null) {
			view.showMenu();
			view = view.selectOption();
		}
		
		
		System.out.println();
	}
}