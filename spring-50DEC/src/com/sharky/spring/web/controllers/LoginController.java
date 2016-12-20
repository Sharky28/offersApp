package com.sharky.spring.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping("/newAccount")
	private String showNewAccount(Model model) {
		model.addAttribute("user", new User());
		return "newAccount";
	}

	@RequestMapping(value = "/createAccount", method = RequestMethod.POST)
	public String createAccount(@Valid User user, BindingResult result) {

		if (result.hasErrors()) {

			return "createAccount";
		}
		
		user.setAuthority("user");
		user.setEnabled(true);
		
		usersService.create(user);
		return "/accountCreated";

	}

}
