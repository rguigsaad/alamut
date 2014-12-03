package fr.alamut.controler;

import java.sql.SQLException;
import java.util.List;

import com.google.inject.Inject;

import fr.alamut.dao.GameDao;
import fr.alamut.model.Game;

public class GameControler {
	
	private final GameDao gameDao;
	
	@Inject
	public GameControler(GameDao gameDao) {
		this.gameDao = gameDao;
	}
	
	public List<Game> getAllGamesALL() throws SQLException{
		return gameDao.getAllGamesALL();
	}
	
	public List<Game> getAllGames(String idOwnerPhoneNumber) throws SQLException{
		return gameDao.getAllGames(idOwnerPhoneNumber);
	}
	
	public void createGame(Game game) throws SQLException{
		gameDao.insertGame(game);
	}
	
	public Game getGameByTitle(String idOwnerPhoneNumber, String title) throws SQLException{
		return gameDao.getGameByTitle(idOwnerPhoneNumber, title);
	}
}
