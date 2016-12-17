package com.sharky.spring.web.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharky.spring.web.DAO.Offer;
import com.sharky.spring.web.DAO.OffersDAO;

@Service("offersService")
public class OffersService {
	private OffersDAO offersDAO;

	@Autowired
	public void setOffersDAO(OffersDAO offersDAO) {
		this.offersDAO = offersDAO;
	}

	public List<Offer> getCurrent() {
		return offersDAO.getOffers();
	}
}
