package fr.alamut.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory; 
import com.google.inject.Inject;

import fr.alamut.dao.UserDao;
import fr.alamut.model.User;

public class UserDaoImpl implements UserDao{

	private final DataSource datasource;
	private Log log = LogFactory.getLog(UserDaoImpl.class);

	@Inject
	public UserDaoImpl(DataSource datasource) {
		super();
		this.datasource = datasource;
	}

	public List<User> getAllUsers() {
		List<User> listUser = new ArrayList<User>();
		try (Connection conn = datasource.getConnection()){
			String selectSQL = "SELECT USER_NAME, EMAIL_ADDRESS, PHONE_NUMBER FROM USER";
			PreparedStatement prepStmt = conn.prepareStatement(selectSQL);
			ResultSet rs = prepStmt.executeQuery();
			while (rs.next()) {
				String userName = rs.getString(1);
				String emailAddress = rs.getString(2);
				String phoneNumber = rs.getString(3);
				listUser.add(new User(userName, phoneNumber, emailAddress)); 
			}
		} catch (SQLException e) {
			log.info("UserDaoImpl SQLException Error Code", e);

		}
		return listUser;
	}

	public User getUserByEmailAddress(String emailAddress) {
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
			log.info("UserDaoImpl SQLException Error Code", e);

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
			log.info("UserDaoImpl SQLException Error Code", e);

		}
	}

	public void deleteUser(User user) {
		// delete user

	}

}
