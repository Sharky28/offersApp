package com.sharky.spring.web.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OffersController {

	@RequestMapping("/")
	public ModelAndView showHome() {
		// session.setAttribute("name", "Sharky");
		ModelAndView mView = new ModelAndView("home");

		Map<String, Object> model = mView.getModel();

		model.put("name", "River");

		return mView;

	}
}
