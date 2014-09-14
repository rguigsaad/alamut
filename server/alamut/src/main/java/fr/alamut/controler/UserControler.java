package fr.alamut.controler;

import com.google.inject.Inject;

import fr.alamut.dao.UserDao;
import fr.alamut.model.User;

public class UserControler {
	
	private final UserDao userDao;
	
	@Inject
	public UserControler(UserDao userDao) {
		this.userDao = userDao;
	}
	 
	public void createUser(User user){
		userDao.insertUser(user);
	}
	
	public User getUserByEmailAdress(String emailAdress){
		return userDao.getUserByEmailAdress(emailAdress);
	}
}
