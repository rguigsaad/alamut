package fr.alamut.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.google.inject.Inject;

import fr.alamut.dao.UserDao;
import fr.alamut.model.User;

public class UserDaoImpl implements UserDao{

	private final DataSource datasource;
	private  Logger logger = Logger.getLogger( UserDaoImpl.class.getName() );

	@Inject
	public UserDaoImpl(DataSource datasource) {
		super();
		this.datasource = datasource;
	}

	public List<User> getAllUsers() throws SQLException {
		logger.log(Level.INFO,"==> getAllUsers");
		List<User> listUser = new ArrayList<User>();
		Connection conn = null;
		try{
			conn = datasource.getConnection();
			String selectSQL = "SELECT USER_NAME, EMAIL_ADDRESS, PHONE_NUMBER FROM USER";
			PreparedStatement prepStmt = conn.prepareStatement(selectSQL);
			ResultSet rs = prepStmt.executeQuery();
			while (rs.next()) {
				String userName = rs.getString(1);
				String emailAddress = rs.getString(2);
				String phoneNumber = rs.getString(3);
				listUser.add(new User(userName, phoneNumber, emailAddress)); 
			}
		} finally {
			if (conn != null){
				conn.close();
			}
		}
		logger.log(Level.INFO,"<== getAllUsers");
		return listUser;
	}

	public User getUserByEmailAddress(String emailAddress) throws SQLException {
		logger.log(Level.INFO,"==> getUserByEmailAddress, email address : {} ",emailAddress);
		// Retrieve a user by email address
		// update user
		Connection conn = null;
		try{
			conn = datasource.getConnection();
			String selectSQL = "SELECT USER_NAME, PHONE_NUMBER FROM USER WHERE EMAIL_ADDRESS=?";
			PreparedStatement prepStmt = conn.prepareStatement(selectSQL);
			prepStmt.setString(1, emailAddress);
			ResultSet rs = prepStmt.executeQuery();
			if (rs.next()) {
				String userName = rs.getString(1);
				String phoneNumber = rs.getString(2);
				User user = new User(userName, phoneNumber, emailAddress);
				logger.log(Level.INFO,"<== getUserByEmailAddress, user : {} ",user.toString());
				return user;
			}
		}finally {
			if (conn != null){
				conn.close();
			}
		}
		logger.log(Level.INFO,"<== getUserByEmailAddress, return null. ");
		return null;
	}

	public void insertUser(User user) throws SQLException {
		logger.log(Level.INFO,"==> insertUser, user : {} ",user.toString());

		// update user
		Connection conn = null;
		try{
			conn = datasource.getConnection();
			PreparedStatement prepStmt = conn.prepareStatement("INSERT INTO USER (USER_NAME, EMAIL_ADDRESS, PHONE_NUMBER) VALUES (?,?,?)");
			prepStmt.setString(1, user.getUserName());
			prepStmt.setString(2, user.getEmailAddress());
			prepStmt.setString(3, user.getPhoneNumber());
			prepStmt.execute();
		}finally {
			if (conn != null){
				conn.close();
			}
		}
		logger.log(Level.INFO,"<== insertUser");
	}

	public void deleteUser(User user) {
		// delete user

	}

}
