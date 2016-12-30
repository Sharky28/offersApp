package com.sharky.spring.web.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharky.spring.web.DAO.Offer;
import com.sharky.spring.web.DAO.OffersDao;

@Service("offersService")
public class OffersService {
	private OffersDao offersDAO;

	@Autowired
	public void setOffersDAO(OffersDao offersDAO) {
		this.offersDAO = offersDAO;
	}

	public List<Offer> getCurrent() {
		return offersDAO.getOffers();
	}

	public void create(Offer offer) {
		offersDAO.saveOrUpdate(offer);
	}

	public boolean hasOffer(String name) {
		if (name == null) {
			return false;
		}
		List<Offer> offers = offersDAO.getOffers(name);
		if (offers.size() == 0) {
			return false;
		}
		return true;
	}

	public Offer getOffer(String username) {
		List<Offer> offers = offersDAO.getOffers(username);
		if (username == null) {
			return null;
		}
		if (offers.size() == 0) {
			return null;
		}

		return offers.get(0);
	}

	public void saveOrUpdate(Offer offer) {
		
			offersDAO.saveOrUpdate(offer);
			
	}

	public void delete(int id) {
		offersDAO.delete(id);
		
	}

}
