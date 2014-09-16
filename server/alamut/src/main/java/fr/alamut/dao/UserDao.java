package fr.alamut.dao;

import java.sql.SQLException;
import java.util.List;

import fr.alamut.model.User;


/**
 * The Interface UserDao.
 */
public interface UserDao {
   
   /**
    * Gets the all users.
    *
    * @return the all users
 * @throws SQLException 
    */
   public List<User> getAllUsers() throws SQLException;
   
   /**
    * Gets the user by email address.
    *
    * @param emailAdress the email adress
    * @return the user by email address
 * @throws SQLException 
    */
   public User getUserByEmailAddress(String emailAdress) throws SQLException;
   
   /**
    * Insert user in database
    *
    * @param user 
    */
   public void insertUser(User user) throws SQLException;
   
   /**
    * Delete user from database
    *
    * @param user 
    */
   public void deleteUser(User user) throws SQLException;
}
