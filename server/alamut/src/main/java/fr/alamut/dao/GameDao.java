package fr.alamut.dao;

import java.sql.SQLException;
import java.util.List;

import fr.alamut.model.Game;
import fr.alamut.model.User;


/**
 * The Interface UserDao.
 */
public interface GameDao {
	
	
   public List<Game> getAllGamesALL() throws SQLException;
   
   /**
    * Gets the all users.
    *
    * @return the all users
 * @throws SQLException 
    */
   public List<Game> getAllGames(String idOwnerPhoneNumber) throws SQLException;
   
   /**
    * Gets the user by email address.
    *
    * @param emailAdress the email adress
    * @return the user by email address
 * @throws SQLException 
    */
   public Game getGameByTitle(String idOwnerPhoneNumber, String title) throws SQLException;
   
   /**
    * Insert user in database
    *
    * @param user 
    */
   public void insertGame(Game game) throws SQLException;
   
   /**
    * Delete user from database
    *
    * @param user 
    */
   public void deleteGame(Game game) throws SQLException;
}
