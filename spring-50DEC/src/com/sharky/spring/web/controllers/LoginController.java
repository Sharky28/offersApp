package com.sharky.spring.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sharky.spring.web.DAO.User;
import com.sharky.spring.web.Service.UsersService;

@Controller
public class LoginController {
	@Autowired
	private UsersService usersService;

	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/login")
	private String showLogin() {
		return "login";
	}
	
	@RequestMapping("/loggedOut")
	private String showLoggedOut() {
		return "loggedOut";
	}

	@RequestMapping("/newAccount")
	private String showNewAccount(Model model) {
		model.addAttribute("user", new User());
		return "newAccount";
	}
	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		
		List<User> users = usersService.getAllUsers();
		model.addAttribute("users",users);
		return "admin";
	}

	@RequestMapping(value = "/createAccount", method = RequestMethod.POST)
	public String createAccount(@Valid User user, BindingResult result) {

		if (result.hasErrors()) {

			return "newAccount";
		}

		user.setAuthority("user");
		user.setEnabled(true);

		if (usersService.exists(user.getUsername())) {

			result.rejectValue("username", "duplicateKey.user.username");
			return "newAccount";
		}

		try {
			usersService.create(user);
		} catch (DuplicateKeyException ex) {
			result.rejectValue("username", "duplicateKey.user.username");
			return "newAccount";
		}

		return "/accountCreated";

	}

}
