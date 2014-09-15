package fr.alamut.dao;

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
    */
   public List<User> getAllUsers();
   
   /**
    * Gets the user by email address.
    *
    * @param emailAdress the email adress
    * @return the user by email address
    */
   public User getUserByEmailAddress(String emailAdress);
   
   /**
    * Insert user in database
    *
    * @param user 
    */
   public void insertUser(User user);
   
   /**
    * Delete user from database
    *
    * @param user 
    */
   public void deleteUser(User user);
}
