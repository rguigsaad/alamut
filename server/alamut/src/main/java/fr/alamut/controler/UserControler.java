package fr.alamut.controler;

import java.util.List;

import com.google.inject.Inject;

import fr.alamut.dao.UserDao;
import fr.alamut.model.User;

public class UserControler {
	
	private final UserDao userDao;
	
	@Inject
	public UserControler(UserDao userDao) {
		this.userDao = userDao;
	}
	public List<User> getAllUsers(){
		return userDao.getAllUsers();
	}
	
	public void createUser(User user){
		userDao.insertUser(user);
	}
	
	public User getUserByEmailAdress(String emailAdress){
		return userDao.getUserByEmailAddress(emailAdress);
	}
}
