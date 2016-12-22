package com.sharky.spring.web.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharky.spring.web.DAO.User;
import com.sharky.spring.web.DAO.UsersDao;

@Service("usersService")
public class UsersService {
	private UsersDao usersDao;

	@Autowired
	public void setOffersDAO(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void create(User user) {
		usersDao.create(user);
	}

	public boolean exists(String username) {
		return usersDao.exists(username);
	}

	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}

}
