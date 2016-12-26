package com.sharky.spring.web.controllers;

import java.security.Principal;
import java.util.List;

import javax.jws.WebParam.Mode;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

		List<Offer> offers = offersService.getCurrent();

		model.addAttribute("offers", offers);

		return "offers";

	}
	
	/*@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex)
	{
		return "error";
	}
*/
	@RequestMapping("/createOffer")
	public String createOffer(Model model) {
		model.addAttribute("offer", new Offer());
		return "createOffer";

	}

	@RequestMapping(value = "/doCreate", method = RequestMethod.POST)
	public String doCreate(Model model, @Valid Offer offer, BindingResult result,Principal principal) {

		if (result.hasErrors()) {

			return "createOffer";
		}
		
		String username = principal.getName();
		offer.getUser().setUsername(username);
		offersService.create(offer);
		return "offerCreated";

	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String showTest(Model model, @RequestParam("id") String id) {

		System.out.println("ID is: " + id);

		return "home";

	}
}
