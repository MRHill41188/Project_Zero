package com.revature.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.text.View;

import com.revature.account.Account;
import com.revature.account.User;
import com.revature.utll.ConnectionUtll;
import com.revature.view.LoggedInView;
import com.revature.view.MainMenu;

public class AccountDao {

	public int CreateAccount(String accountype, String username) throws SQLException {

		try (Connection connection = ConnectionUtll.getConnection()) {

			connection.setAutoCommit(false);
			String sql = "Insert into account(Balance, account_name ,acc_type) values(?, ?, ?) Returning id;";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, 0);
			statement.setString(2, username);
			statement.setString(3, accountype);

			ResultSet result = statement.executeQuery();

			if (result.next()) {
				int id = result.getInt("id");
				sql = "Insert into useraccounts(client_id, account_id) values(?, ?) Returning id;";

				statement = connection.prepareStatement(sql);

				statement.setInt(1, User.getCurrentUser().getId());
				statement.setInt(2, id);
				;

				result = statement.executeQuery();

				connection.commit();
				return id;
			}

		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		throw new SQLException();

	}
	
	public int Withdrawl(int withdrawlAmount, int id) throws SQLException {

		try (Connection connection = ConnectionUtll.getConnection()) {
			connection.setAutoCommit(false);
			String sql = "select balance from account where id = ?;";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, id);
			//statement.setInt(2, id);
		

			ResultSet result = statement.executeQuery();

			if (result.next()) {
				
				
				int balance = result.getInt("balance");
				sql = "update account set balance = balance - ? where id = ? returning balance";

				statement = connection.prepareStatement(sql);
				statement.setInt(1, withdrawlAmount);
				statement.setInt(2, id);
				
				
			

				result = statement.executeQuery();
				result.next();
				balance = result.getInt("balance");

				Account.getCurrentAccount().setBalance(result.getInt("balance"));
				connection.commit();
				
				
				return balance;
			}

		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		throw new SQLException();

	}
	
	public int Transfer(int withdrawlAmount, int transferId, int id) throws SQLException {

		try (Connection connection = ConnectionUtll.getConnection()) {
			connection.setAutoCommit(false);
			String sql = "select balance from account where id = ?;";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, transferId);
			
		

			ResultSet result = statement.executeQuery();

			if (result.next()) {
				
				
				int balance = result.getInt("balance");
				sql = "update account set balance = balance - ? where id = ? returning balance";

				statement = connection.prepareStatement(sql);
				statement.setInt(1, withdrawlAmount);
				statement.setInt(2, id);
				
				
			

				result = statement.executeQuery();
				result.next();
				balance = result.getInt("balance");

				Account.getCurrentAccount().setBalance(result.getInt("balance"));
				connection.commit();
				
				
				return balance;
			}

		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		throw new SQLException();

	}

	public int LoadAccount() throws SQLException {

		try (Connection connection = ConnectionUtll.getConnection()) {
			String sql = "Select id, acc_type, balance from account where id in (Select account_id from useraccounts where client_id = ?);";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, User.getCurrentUser().getId());
		

			ResultSet result = statement.executeQuery();

			if (result.next()) {
				
				Account acct = new Account();
				acct.setAccountType(result.getString("acc_type"));
				acct.setBalance(result.getInt("balance"));
				acct.setId(result.getInt("id"));
				
		     	Account.setCurrentAccount(acct);

				return result.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new SQLException();

	}
	
	public int Deposit(int depositAmount) throws SQLException {

		try (Connection connection = ConnectionUtll.getConnection()) {
			String sql = "update account set balance = balance + ? where id = ? returning balance;";

			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(2, User.getCurrentUser().getId());
			statement.setInt(1, depositAmount);
		

			ResultSet result = statement.executeQuery();
			result.next();
		int balance = result.getInt("balance");

			Account.getCurrentAccount().setBalance(result.getInt("balance"));
			
			
			
			return balance;
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new SQLException();

	}
	
	public void CloseAccount(int clientId, int accountId) {

		try (Connection connection = ConnectionUtll.getConnection()) {
			connection.setAutoCommit(false);
			String sql = "delete from useraccounts where client_id = ? returning *";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, clientId);
			
			ResultSet result = statement.executeQuery();
		

				sql = "delete from account where id = ? returning *";

				statement = connection.prepareStatement(sql);

				statement.setInt(1, accountId);
				
				result = statement.executeQuery();
			
				connection.commit();
				
						

		} catch (SQLException e) {
			e.printStackTrace(); 
		}

	}

}
