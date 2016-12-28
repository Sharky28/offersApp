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

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
	}
	
	@Test
	public void testOffers(){
		User user = new User("sharky123", "sharmarke","helloThere", "sharmarke.ugas@gmail.com", true, "user");
	 usersDao.create(user);

		Offer offer = new Offer(user, "This is a test offer");
		assertTrue("Offer creation should return true", offersDao.create(offer));

		List<Offer> offers = offersDao.getOffers();
		assertEquals("Should only be 1 offer", 1, offers.size());

		assertEquals("Created offer should match retrieved one", offer, offers.get(0));
		
		// Get the offer with id filled in///////////

		offer = offers.get(0);

		offer.setText("Updated offer text");
		assertTrue("Offer updated should return true", offersDao.update(offer));

		Offer updated = offersDao.getOffer(offer.getId());
		assertEquals("Updated offer should match retrieved one", offer, updated);
		
		// test get by id////
		
		Offer offer2 = new Offer(user, "This is a test Offer");

		assertTrue("Offer creation should return true", offersDao.create(offer2));

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
