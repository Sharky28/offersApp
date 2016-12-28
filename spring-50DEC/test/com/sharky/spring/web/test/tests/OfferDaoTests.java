package com.sharky.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sharky.spring.web.DAO.Offer;
import com.sharky.spring.web.DAO.OffersDao;
import com.sharky.spring.web.DAO.User;
import com.sharky.spring.web.DAO.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/sharky/spring/web/config/dao-context.xml",
		"classpath:com/sharky/spring/web/config/security-context.xml",
		"classpath:com/sharky/spring/web/test/config/dataSource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDaoTests {

	@Autowired
	private OffersDao offersDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;

	private User user1 = new User("sharmarke", "sharky123", "helloThere", "sharmarke.ugas@gmail.com", true, "user");
	private User user2 = new User("ardoSalal", "ardo123", "helloThere", "sharmarke.ugas@gmail.com", true, "user");
	private User user3 = new User("filsanUgas", "filsan123", "helloThere", "sharmarke.ugas@gmail.com", true, "user");
	private User user4 = new User("ahmedUgas", "ahmed123", "helloThere", "sharmarke.ugas@gmail.com", false, "user");

	Offer offer1 = new Offer(user1, "This is a test offer......");
	Offer offer2 = new Offer(user1, "This is another test offer......");
	Offer offer3 = new Offer(user2, "Yet another test offer......");
	Offer offer4 = new Offer(user2, "This is a Tricky test offer......");
	Offer offer5 = new Offer(user3, "This is a Happyoffer......");
	Offer offer6 = new Offer(user3, "This is a Sad offer......");
	Offer offer7 = new Offer(user4, "This is a Useless offer.....");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
	}

	@Test
	public void testCreateRetrieve() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);

		offersDao.create(offer1);
		List<Offer> offers1 = offersDao.getOffers();
		assertEquals("Should be one offer", 1, offers1.size());

		assertEquals("Retrieved offer should equal inserted offer", offer1, offers1.get(0));

		offersDao.create(offer2);
		offersDao.create(offer3);
		offersDao.create(offer4);
		offersDao.create(offer5);
		offersDao.create(offer6);
		offersDao.create(offer7);

		List<Offer> offers2 = offersDao.getOffers();
		assertEquals("Should be 6 offers due to one user not being enabled", 6, offers2.size());

	}

	@Test
	public void testGetByUsername() {
		usersDao.create(user3);

		offersDao.create(offer5);
		offersDao.create(offer6);

		List<Offer> offers1 = offersDao.getOffers(user3.getUsername());
		assertEquals("Should retreive 2 offers made by user 3", 2, offers1.size());

		List<Offer> offers2 = offersDao.getOffers("sasasas");
		assertEquals("Should return no offers as user does not exist", 0, offers2.size());
	}
	
	@Test
	public void testUpdateOffer(){
		
	}

	@Test
	public void testOffers() {
		User user = new User("sharky123", "sharmarke", "helloThere", "sharmarke.ugas@gmail.com", true, "user");
		usersDao.create(user);

		Offer offer = new Offer(user, "This is a test offer");
		offersDao.create(offer);

		List<Offer> offers = offersDao.getOffers();
		assertEquals("Should only be 1 offer", 1, offers.size());

		assertEquals("Created offer should match retrieved one", offer, offers.get(0));

		// Get the offer with id filled in///////////
/*
		offer = offers.get(0);

		offer.setText("Updated offer text");
		assertTrue("Offer updated should return true", offersDao.update(offer));

		Offer updated = offersDao.getOffer(offer.getId());
		assertEquals("Updated offer should match retrieved one", offer, updated);

		// test get by id////

		Offer offer2 = new Offer(user, "This is a test Offer");

		offersDao.create(offer2);*/

		List<Offer> userOffers = offersDao.getOffers(user.getUsername());

		assertEquals("shoud be two offers for user", 2, userOffers.size());

		List<Offer> secondList = offersDao.getOffers();

		for (Offer current : secondList) {
			Offer retrieved = offersDao.getOffer(current.getId());
			assertEquals("Offer by id should match from list", current, retrieved);
		}

		// test deletion//////
		offersDao.delete(offer.getId());
		System.out.println(offersDao.getOffers().size());
		List<Offer> empty = offersDao.getOffers();
		assertEquals("Collection should only contain 1", 1, empty.size());
	}

}
