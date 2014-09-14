package fr.alamut.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.google.inject.Inject;

import fr.alamut.dao.UserDao;
import fr.alamut.model.User;

public class UserDaoImpl implements UserDao{

	private final DataSource datasource;
	private static Logger logger = Logger.getLogger("UserDaoImpl");

	@Inject
	public UserDaoImpl(DataSource datasource) {
		super();
		this.datasource = datasource;
	}

	public List<User> getAllUsers() {
		// Return list of all users
		return null;
	}

	public User getUserByEmailAdress(String emailAddress) {
		// Retrieve a user by email address
		// update user
		try (Connection conn = datasource.getConnection()){
			String selectSQL = "SELECT USER_NAME, PHONE_NUMBER FROM USER WHERE EMAIL_ADDRESS=?";
			PreparedStatement prepStmt = conn.prepareStatement(selectSQL);
			prepStmt.setString(1, emailAddress);
			ResultSet rs = prepStmt.executeQuery();
			if (rs.next()) {
				String userName = rs.getString(1);
				String phoneNumber = rs.getString(2);
				return new User(userName, phoneNumber, emailAddress);
			}
		} catch (SQLException e) {
			logger.warning("UserDaoImpl SQLException Error Code"+e.getErrorCode());
			logger.warning("UserDaoImpl SQLException Error Msg "+e.getMessage());

		}
		return null;
	}

	public void insertUser(User user) {
		// update user
		try (Connection conn = datasource.getConnection()){
			PreparedStatement prepStmt = conn.prepareStatement("INSERT INTO USER (USER_NAME, EMAIL_ADDRESS, PHONE_NUMBER) VALUES (?,?,?)");
			prepStmt.setString(1, user.getUserName());
			prepStmt.setString(2, user.getEmailAddress());
			prepStmt.setString(3, user.getPhoneNumber());
			prepStmt.execute();
		} catch (SQLException e) {
			logger.warning("UserDaoImpl SQLException Error Code"+e.getErrorCode());
			logger.warning("UserDaoImpl SQLException Error Msg "+e.getMessage());

		}
	}

	public void deleteUser(User user) {
		// delete user

	}

}
