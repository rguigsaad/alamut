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

import fr.alamut.dao.GameDao;
import fr.alamut.dao.UserDao;
import fr.alamut.model.Game;
import fr.alamut.model.User;

public class GameDaoImpl implements GameDao{

	private final DataSource datasource;
	private  Logger logger = Logger.getLogger( GameDaoImpl.class.getName() );

	@Inject
	public GameDaoImpl(DataSource datasource) {
		super();
		this.datasource = datasource;
	}
	
	
	public List<Game> getAllGamesALL() throws SQLException {
		logger.log(Level.INFO,"==> getAllUsers");
		List<Game> listGame = new ArrayList<Game>();
		Connection conn = null;
		try{
			conn = datasource.getConnection();
			String selectSQL = "SELECT GAME_ID_ICON, GAME_STATUS, GAME_TITLE, GAME_ID_OWNER_PHONE_NUMBER FROM GAME";
			PreparedStatement prepStmt = conn.prepareStatement(selectSQL);
			ResultSet rs = prepStmt.executeQuery();
			while (rs.next()) {
				String idIcon = rs.getString(1);
				String status = rs.getString(2);
				String title = rs.getString(3);
				String idOwnerPhoneNumber =rs.getString(4);
				listGame.add(new Game(title, idIcon, status, idOwnerPhoneNumber)); 
			}
		} finally {
			if (conn != null){
				conn.close();
			}
		}
		logger.log(Level.INFO,"<== getAllUsers");
		return listGame;
	}

	public List<Game> getAllGames(String idOwnerPhoneNumber) throws SQLException {
		logger.log(Level.INFO,"==> getAllUsers");
		List<Game> listGame = new ArrayList<Game>();
		Connection conn = null;
		try{
			conn = datasource.getConnection();
			String selectSQL = "SELECT GAME_ID_ICON, GAME_STATUS, GAME_TITLE FROM GAME WHERE GAME_ID_OWNER_PHONE_NUMBER=?";
			PreparedStatement prepStmt = conn.prepareStatement(selectSQL);
			prepStmt.setString(1, idOwnerPhoneNumber);
			System.out.println("DEBUG === idPhoneNumber requete " + prepStmt.toString());
			ResultSet rs = prepStmt.executeQuery();
			while (rs.next()) {
				String idIcon = rs.getString(1);
				String status = rs.getString(2);
				String title = rs.getString(3);
				listGame.add(new Game(idIcon, idOwnerPhoneNumber, status, title)); 
			}
		} finally {
			if (conn != null){
				conn.close();
			}
		}
		logger.log(Level.INFO,"<== getAllUsers");
		return listGame;
	}

	public Game getGameByTitle(String idOwnerPhoneNumber, String title) throws SQLException {
		logger.log(Level.INFO,"==> getGameByTitle, idOwnerPhoneNumber, title : {} ", idOwnerPhoneNumber + ", " + title);
		// Retrieve a user by email address
		// update user
		Connection conn = null;
		try{
			conn = datasource.getConnection();
			String selectSQL = "SELECT GAME_ID_ICON, GAME_STATUS FROM GAME WHERE GAME_ID_OWNER_PHONE_NUMBER=? AND GAME_TITLE=?";
			PreparedStatement prepStmt = conn.prepareStatement(selectSQL);
			prepStmt.setString(1, idOwnerPhoneNumber);
			prepStmt.setString(1, title);
			ResultSet rs = prepStmt.executeQuery();
			if (rs.next()) {
				String idIcon = rs.getString(1);
				String status = rs.getString(2);
				Game game = new Game(title, idIcon, status, idOwnerPhoneNumber);
				logger.log(Level.INFO,"<== getGameByTitle, game : {} ",game.toString());
				return game;
			}
		}finally {
			if (conn != null){
				conn.close();
			}
		}
		logger.log(Level.INFO,"<== getGameByTitle, return null. ");
		return null;
	}

	public void insertGame(Game game) throws SQLException {
		logger.log(Level.INFO,"==> insertGame, game : {} ",game.toString());

		// update user
		Connection conn = null;
		try{
			conn = datasource.getConnection();
			PreparedStatement prepStmt = conn.prepareStatement("INSERT INTO GAME (GAME_TITLE, GAME_ID_ICON, GAME_STATUS, GAME_ID_OWNER_PHONE_NUMBER) VALUES (?,?,?,?)");
			prepStmt.setString(1, game.getTitle());
			prepStmt.setString(2, game.getIdIcon());
			prepStmt.setString(3, game.getStatus());
			prepStmt.setString(4, game.getIdOwnerPhoneNumber());
			prepStmt.execute();
		}finally {
			if (conn != null){
				conn.close();
			}
		}
		logger.log(Level.INFO,"<== insertGame");
	}

	public void deleteGame(Game game) {
		// delete user

	}

}
