package com.sharky.spring.web.test.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sharky.spring.web.DAO.User;
import com.sharky.spring.web.DAO.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/sharky/spring/web/config/dao-context.xml",
		"classpath:com/sharky/spring/web/config/security-context.xml",
		"classpath:com/sharky/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;

	User user1 = new User("sharmarke","sharky","helloThere", "sharmarke.ugas@gmail.com", true, "user");
	User user2 = new User("ardoSalal","ardo", "helloThere", "sharmarke.ugas@gmail.com", true, "user");
	User user3 = new User("filsanUgas","filsan", "helloThere", "sharmarke.ugas@gmail.com", true, "user");
	User user4 = new User("ahmedUgas","ahmed", "helloThere", "sharmarke.ugas@gmail.com", true, "user");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
		
	

	}
	
	@Test
	public void testCreateRetrieve() {
		usersDao.create(user1);

		List<User> users1 = usersDao.getAllUsers();

		assertEquals("one user should have been created and retrieved", 1, users1.size());

		assertEquals("inserted user should match retrieved", user1, users1.get(0));

		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);

		List<User> users2 = usersDao.getAllUsers();

		assertEquals("should be 4 retrieved users", 4, users2.size());
	}
	
	
}
