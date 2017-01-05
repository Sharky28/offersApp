package com.sharky.spring.web.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.sharky.spring.web.DAO.Message;
import com.sharky.spring.web.DAO.MessagesDao;
import com.sharky.spring.web.DAO.User;
import com.sharky.spring.web.DAO.UsersDao;

@Service("usersService")
public class UsersService {
	@Autowired
	private UsersDao usersDao;
	@Autowired
	private MessagesDao messagesDao;



	public void create(User user) {
		usersDao.create(user);
	}

	public boolean exists(String username) {
		return usersDao.exists(username);
	}

	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}
	
	public void sendMessage(Message message){
		messagesDao.saveOrUpdate(message);
	}

	public User getUser(String username){
		return usersDao.getUser(username);
	}
}
