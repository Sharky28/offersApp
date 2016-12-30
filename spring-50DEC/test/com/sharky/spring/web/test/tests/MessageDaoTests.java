package com.sharky.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

import com.sharky.spring.web.DAO.Message;
import com.sharky.spring.web.DAO.MessagesDao;
import com.sharky.spring.web.DAO.Offer;
import com.sharky.spring.web.DAO.OffersDao;
import com.sharky.spring.web.DAO.User;
import com.sharky.spring.web.DAO.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/sharky/spring/web/config/dao-context.xml",
		"classpath:com/sharky/spring/web/config/security-context.xml",
		"classpath:com/sharky/spring/web/test/config/dataSource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageDaoTests {

	@Autowired
	private OffersDao offersDao;

	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private MessagesDao messagesDao;

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
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}

	@Test
	public void save() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);

	
		Message message1 = new Message("testSubjject","testContent","issacNewton","issac@gmail.com",user1.getUsername());
		messagesDao.saveOrUpdate(message1);

	}



	
}
