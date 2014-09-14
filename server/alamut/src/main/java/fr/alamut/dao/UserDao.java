package fr.alamut.dao;

import java.util.List;

import fr.alamut.model.User;


public interface UserDao {
   public List<User> getAllUsers();
   public User getUserByEmailAdress(String emailAdress);
   public void insertUser(User user);
   public void deleteUser(User user);
}
