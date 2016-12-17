package com.sharky.spring.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sharky.spring.web.DAO.Offer;
import com.sharky.spring.web.Service.OffersService;

@Controller
public class OffersController {

	private OffersService offersService;

	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}

	@RequestMapping("/offers")
	public String showOffers(Model model) {
		
		List<Offer> offers= offersService.getCurrent();
		
		model.addAttribute("offers", offers);

		return "offers";

	}
	
	@RequestMapping("/createOffer")
	public String createOffer() {
		return "createOffer";

	}
}
