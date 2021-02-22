package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.account.Account;
import com.revature.account.User;
import com.revature.utll.ConnectionUtll;

public class UserDao {


	private User extractAccount(ResultSet result) throws SQLException {
		int id = result.getInt("id");
		String userName = result.getString("username");
		String password = result.getString("password");
		return new User(id, userName, password);
	}
	
	public User getAccount(String userName, String passWord) {
	
		try(Connection connection = ConnectionUtll.getConnection()) {
			String sql = "SELECT id, username, password FROM client"
					+ " WHERE username  = ? and password = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, userName);
			statement.setString(2, passWord);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				int id = result.getInt("id");
				String username = result.getString("username");
				String password = result.getString("password");
				return new User(id, username, password);
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println();
			System.out.println();
		}
		System.out.println("Invalid Username or password");
		return null;
		
	}

	public User createAccount(User user) {
		try(Connection connection = ConnectionUtll.getConnection()) {
			String sql = "INSERT INTO client (username, password) " +
					" VALUES (?, ?) RETURNING *";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassword());
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				
				return extractAccount(result);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}